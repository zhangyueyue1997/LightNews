package com.pb.service;


import com.pb.common.vo.PageObject;
import com.pb.pojo.Advertisement;
import org.springframework.web.multipart.MultipartFile;

public interface AdsService {
    int FILE_EXTENSION_ERROR= 301;
    int FILE_SAVE_SUCCESS = 100;
    int FILE_SAVE_FAILED = 101;
    int FILE_IS_EMPTY = 201;
    int PDF_TYPE = 1;
    int JPG_TYPE = 2;
    int DEFAULT_TYPE = 0;
    /**查找页面信息*/
    PageObject<Advertisement> findPageObjects(Integer pageCurrent);
    /**根据id值删除对象*/
    int deleteObjects(String checkedIds);
    /**保存信息*/
    int saveObjects(Advertisement entity);
    /**更新信息*/
    int updateObjects(Advertisement entity);
    /**根据id查找数据*/
    Advertisement findObjectById(Integer id);
    /**上传文件*/
    int uploadFiles(MultipartFile file,String link);
    /**删除广告*/
    int deleteAdsById(Advertisement advertisement);
}
