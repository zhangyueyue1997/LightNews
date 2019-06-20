package com.pb.controller;

import java.io.File;
import java.util.List;

import com.pb.common.vo.PageObject;
import com.pb.pojo.Data;
import com.pb.pojo.WxUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pb.common.vo.DataListInform;
import com.pb.common.vo.JsonResult;
import com.pb.service.DataListInformService;

@Controller
@RequestMapping("/wxInformation/dataList/")
public class DataListInformController {
	
	@Autowired
	private DataListInformService service;
	 private String common = "wx" + File.separator + "information" + File.separator;
	
	@RequestMapping(value = "doFindDataListInform",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult doFindDataListInform(@RequestParam("pageCurrent") int pageCurrent){
		PageObject<DataListInform> list = service.findDataListInfo(pageCurrent);
		return new JsonResult(1,"OK",list);	
	}

	@RequestMapping(value = "selectCount",method = RequestMethod.GET)
	@ResponseBody
	public int selectCount(){
		return service.selectCount();
	}

	@RequestMapping(value = "findBaiDuYunByAddress",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult findBaiDuYunByAddress(@RequestParam("address") String address){

		DataListInform dataListInform = new DataListInform();
		dataListInform.setAddress(address);

		DataListInform dataListInform1 = service.findBaiDuYunByAddress(dataListInform);
		return new JsonResult(1,dataListInform1);
	}

	@RequestMapping(value = "insertPreview",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult insertPreview(@RequestParam("openid") String openid,
									@RequestParam("filename") String filename){

		return new JsonResult(service.insertPreview(openid,filename),"ok");
	}

	@RequestMapping(value = "insertDownload",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult insertDownload(@RequestParam("openid") String openid,
									 @RequestParam("filename") String filename){

		return new JsonResult(service.insertDownload(openid,filename),"ok");
	}

	@RequestMapping(value = "selectFilename",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult selectFilename(@RequestParam("filename") String filename,@RequestParam("openid") String openid){
		int count = service.selectFilename(filename,openid);
		return count == 1? new JsonResult(1,"ok"):
				new JsonResult(0,"no");
	}

	@RequestMapping(value = "selectDownload",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult selectDownload(@RequestParam("openid") String openid,@RequestParam("filename") String filename){
		int count = service.selectDownload(openid,filename);
		return count == 1? new JsonResult(1,"成功"): new JsonResult(0,"失败");
	}

	@RequestMapping(value = "selectListId",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult selectListId(@RequestParam("id") int id,@RequestParam("openid") String openid){
		WxUser wxUser = new WxUser();
		wxUser.setOpenid(openid);
		wxUser.setid(id);

		int count = service.selectListId(wxUser);
		return count == 1? new JsonResult(1,"ok"):new JsonResult(2,"no");
	}

	@RequestMapping(value = "insertListData",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult insertListData(@RequestParam("id") int id,@RequestParam("openid") String openid){
		WxUser wxUser = new WxUser();
		wxUser.setid(id);
		wxUser.setOpenid(openid);
		 int count = service.insertListData(wxUser);
		return count == 1? new JsonResult(1,"ok"):new JsonResult(2,"no");
	}

	@RequestMapping(value = "selectOneself",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult selectOneself(@RequestParam("openid") String openid){
	    WxUser wxUser = new WxUser();
	    wxUser.setOpenid(openid);
	    int count = service.selectOneself(wxUser);
        System.out.println(count);
	    return count != 0?new JsonResult(1,"ok"):new JsonResult(2,"no");
    }

}
