package com.pb.service.impl;

import com.pb.common.vo.PageObject;
import com.pb.dao.AdsDao;
import com.pb.pojo.Advertisement;
import com.pb.service.AdsService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/***
 * 业务层的具体实现类
 * @author zgl
 *
 */
@Service
public class AdsServiceImpl implements AdsService {
    @Autowired
    private AdsDao dao = null;

    @Override
    public PageObject<Advertisement> findPageObjects(Integer pageCurrent) {
        int pageSize = 8;//设置单页显示的数据条目数为10.
        int startIndex = (pageCurrent - 1) * pageSize;//计算获得startIndex用于sql查询
        List<Advertisement> records = dao.findPageObjects(startIndex, pageSize);//获取数据
        int rowCount = dao.getRowCount();//获取总记录数
        int pageCount = rowCount / pageSize; //计算获得总页数

        /**总记录数不能除尽单页显示条目数，则总页数增加一页，用于显示零头信息*/
        if(rowCount % pageSize != 0){
            pageCount++;
        }

        PageObject<Advertisement> obj = new PageObject<>();//创建PageObject对象用于封装信息
        /**封装信息*/
        obj.setPageCount(pageCount);
        obj.setPageCurrent(pageCurrent);
        obj.setRecords(records);
        obj.setRowCount(rowCount);

        return obj;//返回PageObject对象(到控制层)
    }

    @Override
    public int deleteObjects(String checkedIds) {
    	String[] ids = checkedIds.split(",");
        return dao.deleteObjects(ids);
    }

    @Override
    public int saveObjects(Advertisement entity) {
        return dao.saveObjects(entity);
    }

    @Override
    public int updateObjects(Advertisement entity) {
        return dao.updateObjects(entity);
    }

    @Override
    public Advertisement findObjectById(Integer id) {
        return dao.findObjectById(id);
    }

    /***
     * 上传文件
     * @param file 待上传文件
     * @return 保存结果 100: 成功, 200: 失败
     */
    @Override
    public int uploadFiles(MultipartFile file,String link) {
        String path = "/zl/";
        if (file.isEmpty()) {
            return AdsService.FILE_IS_EMPTY;
        }
        String fileName = null;
        String fileType = null;
        //设置默认文件类型
        int type = AdsService.DEFAULT_TYPE;
        //获得文件名
        fileName = file.getOriginalFilename();
        //截取文件类型
        fileType = fileName.substring(fileName.lastIndexOf("."));
        //判断文件类型
        if (fileType.equals(".pdf") || fileType.equals(".PDF")) {
            path += "pdf";
        } else if (fileType.equals(".jpg") ||
                   fileType.equals(".JPG") ||
                   fileType.equals(".PNG") ||
                   fileType.equals(".png")){
            path += "img";
        } else {
            //文件类型错误
            return AdsService.FILE_EXTENSION_ERROR;
        }

        //生成UUID作为文件名
        String uuid = UUID.randomUUID().toString();
        //生成服务器资源保存路径
        String content = path + "/" + uuid + fileType;
        System.out.println("////////////////文件保存路径/////////////////");
        System.out.println(content);
        //保存文件到服务器
        File serverFile = null;
        try {
            serverFile = new File(content);
            FileUtils.copyInputStreamToFile(file.getInputStream(), serverFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //创建Advertisement实例
        Advertisement entity = new Advertisement();
        entity.setContent(content);
        entity.setName(fileName);
        entity.setLink(link);
        //todo:设置创建者
        entity.setCreatedUser("admin");
        //保存实例,返回结果
        return dao.saveObjects(entity) == 1 ? AdsService.FILE_SAVE_SUCCESS : AdsService.FILE_SAVE_FAILED;
    }

    @Override
    public int deleteAdsById(Advertisement advertisement) {
        return dao.deleteAdsById(advertisement);
    }

    @Override
    public List<Advertisement> findObjectsInCount(Integer count) {
        return dao.findObjectsInCount(count);
    }
}
