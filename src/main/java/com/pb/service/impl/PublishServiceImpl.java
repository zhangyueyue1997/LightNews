package com.pb.service.impl;

import com.pb.common.vo.PageObject;
import com.pb.dao.PublishDao;
import com.pb.pojo.Publish;
import com.pb.service.PublishService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/***
 * 业务层的具体实现类
 * @author Yang
 *
 */
@Service
public class PublishServiceImpl implements PublishService {
    @Autowired
    private PublishDao dao;

    @Override
    public PageObject<Publish> findPageObjects(Integer pageCurrent,Integer type) {
        int pageSize = 8;//设置单页显示的数据条目数为10.
        int startIndex = (pageCurrent - 1) * pageSize;//计算获得startIndex用于sql查询
        List<Publish> records = dao.findPageObjects(startIndex, pageSize,type);//获取数据
        int rowCount = dao.getRowCount();//获取总记录数
        int pageCount = rowCount / pageSize; //计算获得总页数

        /**总记录数不能除尽单页显示条目数，则总页数增加一页，用于显示零头信息*/
        if(rowCount % pageSize != 0){
            pageCount++;
        }

        PageObject<Publish> obj = new PageObject<>();//创建PageObject对象用于封装信息
        /**封装信息*/
        obj.setPageCount(pageCount);
        obj.setPageCurrent(pageCurrent);
        obj.setRecords(records);
        obj.setRowCount(rowCount);

        return obj;//返回PageObject对象(到控制层)
    }


    @Override
    public int deleteObjects(String[] checkIds) {
        return dao.deleteObjects(checkIds);
    }

    @Override
    public int saveObjects(Publish entity) {
        return dao.saveObjects(entity);
    }

    @Override
    public int updateObjects(Publish entity) {
        return dao.updateObjects(entity);
    }

    @Override
    public Publish findObjectById(Integer id) {
        return dao.findObjectById(id);
    }

    /***
     * 上传图片
     * @param file 待上传的文件
     * @return 如保存成功 返回图片相对路径 否则返回null
     */
    @Override
    public String uploadFile(MultipartFile file) {
        //声明可接受文件类型
        String[] types = {".jpg", ".JPG",".JPEG",".jpeg",".PNG",".png"};

        //声明服务器资源保存路径
        String resourcePath = null;
        //获得文件完全名
        String originalFileName = file.getOriginalFilename();
        //获得文件名最后一个.的字符串索引值
        int lastIndexOfDot = originalFileName.lastIndexOf(".");
        //获得文件扩展名
        String fileType = originalFileName.substring(lastIndexOfDot);

        //文件类型是否正确
        boolean corrType = false;
        for (String type : types) {
            if (type.equals(fileType)) {
                corrType = true;
                break;
            }
        }

        //判断是否是正确的图片格式
        if (corrType) {
            //生成UUID作为保存在服务器的文件名
            String UUID = java.util.UUID.randomUUID().toString();
            String path = UUID + fileType;
            //生成服务器资源保存路径
            resourcePath = "/zl/" + path;
            try {
                //复制文件到服务器
                File imgFile = new File(resourcePath);
                FileUtils.copyInputStreamToFile(file.getInputStream(), imgFile);
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
        return resourcePath;
    }

    @Override
    public int selectCountByType(Publish publish) {
        return dao.selectCountByType(publish);
    }

    @Override
    public int updateStatus(Publish publish) {
        return dao.updateStatus(publish);
    }

    @Override
    public PageObject<Publish> selectPageObjectsByStatus(Integer pageCurrent, Integer type) {
        int pageSize = 8;//设置单页显示的数据条目数为10.
        int startIndex = (pageCurrent - 1) * pageSize;//计算获得startIndex用于sql查询
        List<Publish> records = dao.selectPageObjectsByStatus(startIndex, pageSize,type);//获取数据
        int rowCount = dao.getRowCount();//获取总记录数
        int pageCount = rowCount / pageSize; //计算获得总页数

        /**总记录数不能除尽单页显示条目数，则总页数增加一页，用于显示零头信息*/
        if(rowCount % pageSize != 0){
            pageCount++;
        }

        PageObject<Publish> obj = new PageObject<>();//创建PageObject对象用于封装信息
        /**封装信息*/
        obj.setPageCount(pageCount);
        obj.setPageCurrent(pageCurrent);
        obj.setRecords(records);
        obj.setRowCount(rowCount);
        return obj;//返回PageObject对象(到控制层)

    }

    @Override
    public int selectStatusById(int id) {



        return dao.selectStatusById(id);
    }
}
