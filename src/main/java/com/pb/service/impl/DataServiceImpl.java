package com.pb.service.impl;

import com.pb.common.Utils.FileHelper;
import com.pb.common.vo.PageObject;
import com.pb.dao.DataDao;
import com.pb.pojo.Data;
import com.pb.service.DataService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


/***
 * 业务层的具体实现类
 * @author zgl
 *
 */
@Service
public class DataServiceImpl implements DataService {
    private final int PDF_TYPE = 1;
//    private final int DOC_DOCX_TYPE = 2;

    @Autowired
    private DataDao dao = null;
    //服务器资源目录
    private final String resourcesPath = "/zl/";
    @Override
    public PageObject<Data> findPageObjects(Integer pageCurrent) {
        int pageSize = 8;//设置单页显示的数据条目数为10.
        int startIndex = (pageCurrent - 1) * pageSize;//计算获得startIndex用于sql查询
        List<Data> records = dao.findPageObjects(startIndex, pageSize);//获取数据
        int rowCount = dao.getRowCount();//获取总记录数
        int pageCount = rowCount / pageSize; //计算获得总页数

        /**总记录数不能除尽单页显示条目数，则总页数增加一页，用于显示零头信息*/
        if(rowCount % pageSize != 0){
            pageCount++;
        }

        PageObject<Data> obj = new PageObject<Data>();//创建PageObject对象用于封装信息
        /**封装信息*/
        obj.setPageCount(pageCount);
        obj.setPageCurrent(pageCurrent);
        obj.setRecords(records);
        obj.setRowCount(rowCount);

        return obj;//返回PageObject对象(到控制层)
    }

    @Override
    public int deleteObjects(String[] ids) {
        return dao.deleteObjects(ids);
    }

    @Override
    public int saveObjects(Data entity) {
        return dao.saveObjects(entity);
    }

    @Override
    public int updateObjects(Data entity) {
        return dao.updateObjects(entity);
    }

    @Override
    public Data findObjectById(Integer id) {
        return dao.findObjectById(id);
    }

    /***
     * 文件批量上传
     * @param uploadedFiles 待上传文件组
     * @return 成功上传文件个数
     * @throws IOException
     */
    @Override
    public FileHelper uploadFiles(MultipartFile[] uploadedFiles,String address) throws IOException {
        FileHelper helper = new FileHelper();
        for (MultipartFile file : uploadedFiles){
            if (!file.getOriginalFilename().equals("")) {
                int result;
                try {
                    result = saveSingleFile(file,address);
                } catch (IOException e) {
                    throw new IOException();
                }
                if (result == FILE_SAVE_SUCCESS) {
                    helper.add(file.getOriginalFilename());
                }
            }
        }
        return helper;
    }

    /***
     * 保存单个文件
     * @param uploadedFile 待上传文件
     * @return 状态码
     */
    private int saveSingleFile(MultipartFile uploadedFile,String address) throws IOException {
        //获得文件完全名
        String fileFullName = uploadedFile.getOriginalFilename();
        String fileName = fileFullName.substring(0,fileFullName.lastIndexOf("."));
        //获得文件扩展名
        String fileExtName = fileFullName.substring(fileFullName.lastIndexOf("."));

        //文件类型
        // 1: pdf
        // 2: doc docx
        int type = 0;

        switch (fileExtName) {
            case ".pdf":
                type = PDF_TYPE;
                break;
//            case ".doc":
//            case ".docx":
//                type = DOC_DOCX_TYPE;
        }
        //不属于指定文件拓展名
        if (type == 0) {
            //文件类型不匹配,返回结果
            return FILE_EXTENSION_ERROR;
        }
        String uuid = UUID.randomUUID().toString();
        //拼接待保存文件路径
        String filePath = resourcesPath + "data/" + uuid + fileExtName;
        System.out.println("/////////////////////////////////////文件保存路径///////////////");
        System.out.println(filePath);
        //保存文件到硬盘
        try {
            FileUtils.copyInputStreamToFile(uploadedFile.getInputStream(), new File(filePath));
        } catch (IOException e) {
            //保存失败
            throw new IOException();
        }
        //声明Data实例
        Data data = new Data();
        //todo :设置创建人地址
        data.setCreatedUser("管理员");
        data.setType(type);
        data.setContent(fileName);
        data.setAddress(filePath);
        //todo :设置百度云地址
        data.setBaiduyun(address);
        //保存到数据库
        return saveObjects(data) == 1 ? FILE_SAVE_SUCCESS : FILE_SAVE_FAILED;
    }

    /***
     * 根据文件名删除文件
     * @param filename 文件名
     * @return 状态码
     */
    @Override
    public int deleteDataByName(String filename) {
        //判断文件名是否存在
        if (filename != null && !filename.equals("")) {
            //拼接文件全路径
            String filePath = resourcesPath + "/" + filename;
            //实例化待删除文件
            File deletedFile = new File(filePath);
            //如果文件存在
            if (deletedFile.exists()) {
                //更改数据库记录,删除文件
                int state = dao.updateStatusByNameOrID(filePath,"");
/*                if (state == 1) {
                    return deletedFile.delete() ? FILE_DELETED_SUCCESS : FILE_DELETED_FAILED;
                }*/
                return state == 1 ? FILE_DELETED_SUCCESS : FILE_DELETED_FAILED;
            } else {
                return FILE_NOT_EXIST;
            }
        }
        return FILE_NAME_EMPTY;
    }

    @Override
    public FileHelper deleteDataByID(String[] id) {
        FileHelper helper = new FileHelper();

        //遍历id
        for (String index : id) {
            int state = dao.updateStatusByNameOrID("", index);
            if (state == 1) {
                helper.add(index);
            }
        }
        return helper;
    }

    /***
     * 根据文件名或类型查询文件
     * @param name 文件名
     * @param type 类型 1: pdf 2: doc,docx
     * @return 结果集
     */
    @Override
    public PageObject<Data> getDataByNameAndType(String name,int type,Integer pageCurrent) {
        int pageSize = 10;//设置单页显示的数据条目数为3.
        int startIndex = (pageCurrent - 1) * pageSize;//计算获得startIndex用于sql查询

        List<Data> records = dao.findByNameAndType(name,type,startIndex, pageSize);//获取数据
        int rowCount = dao.getRowCount();//获取总记录数
        int pageCount = rowCount / pageSize; //计算获得总页数

        /**总记录数不能除尽单页显示条目数，则总页数增加一页，用于显示零头信息*/
        if(rowCount % pageSize != 0){
            pageCount++;
        }

        PageObject<Data> obj = new PageObject<Data>();//创建PageObject对象用于封装信息
        /**封装信息*/
        obj.setPageCount(pageCount);
        obj.setPageCurrent(pageCurrent);
        obj.setRecords(records);
        obj.setRowCount(rowCount);
        return obj;
    }


}
