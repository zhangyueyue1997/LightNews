package com.pb.controller;

import com.pb.common.Utils.FileHelper;
import com.pb.common.vo.JsonResult;
import com.pb.common.vo.PageObject;
import com.pb.pojo.Data;
import com.pb.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("/data")
public class DataController {
    @Autowired
    private DataService service;

/*
    @RequestMapping(value = "indexUI")
    public String doUpload() {
        return "tables/Upload";
    }
*/
    @RequestMapping(value = "listUI")
    public String listUI() {
        return "sys/data/data_list";
    }

    @RequestMapping(value = "saveUI")
    public String saveUI() {
        return "sys/data/data_upload";
    }

    @RequestMapping(value = "doSave",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult uploadFiles(@RequestParam("uploadedFiles") MultipartFile[] uploadedFiles,
                                  @RequestParam("baiduyun") String address,
                                  HttpServletRequest request) {
        JsonResult result = new JsonResult();
        try {
            //上传文件,返回文件结果
            FileHelper helper = service.uploadFiles(uploadedFiles,address);

            if (helper.getCount() == 0) {
                result.setState(0);
                result.setMessage("文件类型错误");
            } else {
                result.setState(1);
                result.setMessage("成功上传 :" + helper.getCount() + "个文件" + " " + "文件名: " + helper.getFileNames());
            }

        } catch (IOException e) {
            //发生未知错误,返回结果集
            result.setState(0);
            result.setMessage("未知错误");
        }
        //返回结果集
        return result;
    }
    @RequestMapping(value = "doSaveData",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult saveData(@RequestParam("content") String content,
                               @RequestParam("type")Integer type,
                               @RequestParam("address")String address,
                               @RequestParam("baiduyun")String baiduyun,
                               @RequestParam("status") int status) {
        //实例化结果集
        JsonResult result = new JsonResult();

        //实例化Data对象
        Data data = new Data();
        data.setContent(content);
        data.setType(type);
        data.setAddress(address);
        data.setBaiduyun(baiduyun);
        data.setStatus(status);

        int state = service.saveObjects(data);
        if (state == 1) {
            result.setState(1);
            result.setMessage("保存成功");
        } else {
            result.setState(0);
            result.setMessage("保存失败");
        }
        return result;
    }
/*    @RequestMapping(value = "delete")
    public String doDelete() {
        return "tables/delete";
    }*/

    @RequestMapping(value = "delFileByName",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult deleteFileByName(@RequestParam("filename") String filename) {
        JsonResult result = new JsonResult();

        int code = service.deleteDataByName(filename);
        switch (code) {
            case DataService.FILE_DELETED_SUCCESS:
                result.setState(1);
                result.setMessage("文件删除成功");
                break;
            case DataService.FILE_DELETED_FAILED:
                result.setState(0);
                result.setMessage("文件删除失败");
                break;
            case DataService.FILE_NAME_EMPTY:
                result.setState(0);
                result.setMessage("文件名为空");
                break;
            case DataService.FILE_NOT_EXIST:
                result.setState(0);
                result.setMessage("不存在此文件");
        }
        return result;
    }

    @RequestMapping(value = "doDelByID",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult deleteFileByID(@RequestParam("checkedIds") String[] checkedIds) {
        JsonResult result = new JsonResult();

        FileHelper helper = service.deleteDataByID(checkedIds);
        if (helper.getCount() == 0) {
            result.setState(0);
            result.setMessage("删除失败,id为空");
        } else {
            result.setState(1);
            result.setMessage("删除成功" + "共删除" + helper.getCount() + "个文件");
        }

        return result;
    }

/*    @RequestMapping(value = "get/getFiles",method = RequestMethod.GET)
    public String doGetByName() {
        return "tables/getData";
    }*/

    @RequestMapping(value = "get/getDataByNameType",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getDataByNameType(@RequestParam("filename") String filename,
                                        @RequestParam("type") int type,
                                        @RequestParam("pageCurrent") Integer pageCurrent) {
        JsonResult result = new JsonResult();
        PageObject<Data> datalist = service.getDataByNameAndType(filename,type,pageCurrent);
        if (datalist != null) {
            result.setState(1);
            result.setMessage("查询成功");
            result.setData(datalist);
        } else {
            result.setState(0);
            result.setMessage("文件不存在");
        }
        return result;
    }


    @RequestMapping(value = "get/getDataByPages",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getDataByPages(@RequestParam("pageCurrent") Integer pageCurrent) {
        PageObject<Data> dataPageObject = service.findPageObjects(pageCurrent);
        return new JsonResult(1, dataPageObject);
    }

    @RequestMapping(value = "updateUI")
    public String doUpdate() {
        return "sys/data/data_edit";
    }

    @RequestMapping(value = "doUpdate",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult updateData(@RequestParam("id")Integer id,
                                 @RequestParam("content") String content,
                                 @RequestParam("address")String address,
                                 @RequestParam("baiduyun")String baiduyun) {
        JsonResult result = new JsonResult();
        //实例化Data对象
        Data data = new Data();

        data.setId(id);
        data.setContent(content);
        data.setAddress(address);
        data.setBaiduyun(baiduyun);

        int state = service.updateObjects(data);
        if (state == 1) {
            result.setState(1);
            result.setMessage("更新成功");
        } else {
            result.setState(0);
            result.setMessage("更新失败");
        }
        return result;
    }

    @RequestMapping(value = "findObjectById",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult findObjectById(@RequestParam("id") Integer id) {
        JsonResult result = new JsonResult();
        Data data = service.findObjectById(id);
        if (data != null) {
            result.setState(1);
            result.setMessage("查询成功");
            result.setData(data);
        } else {
            result.setState(0);
            result.setMessage("不存在此数据");
        }
        return result;
    }
}
