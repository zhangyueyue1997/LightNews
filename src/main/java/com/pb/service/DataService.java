package com.pb.service;

import com.pb.common.Utils.FileHelper;
import com.pb.common.vo.PageObject;
import com.pb.pojo.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/***
 * 业务层接口用于对接控制层（Controller）和持久层（dao）
 * @author zgl
 *
 */
public interface DataService {
    int FILE_NOT_EXIST = -1;
    int FILE_DELETED_SUCCESS = 200,FILE_SAVE_SUCCESS = 100;
    int FILE_DELETED_FAILED = 201,FILE_SAVE_FAILED = 101;
    int FILE_EXTENSION_ERROR= 301;
    int USER_NAME_EMPTY = 302;
    int FILE_NAME_EMPTY = 303;
    int ID_EMPTY = 304;

    /**查找页面信息*/
    PageObject<Data> findPageObjects(Integer pageCurrent);
    /**根据id值删除对象*/
    int deleteObjects(String[] ids);
    /**保存信息*/
    int saveObjects(Data entity);
    /**更新信息*/
    int updateObjects(Data entity);
    /**根据id查找数据*/
    Data findObjectById(Integer id);
    /**文件上传*/
    FileHelper uploadFiles(MultipartFile[] files,String address) throws IOException;
    /**根据Name删除文件*/
    int deleteDataByName(String filename);
    /**根据id删除文件*/
    FileHelper deleteDataByID(String[] id);
    /**根据文件名,拓展名查询文件*/
    PageObject<Data> getDataByNameAndType(String name, int type,Integer pageCurrent);

}
