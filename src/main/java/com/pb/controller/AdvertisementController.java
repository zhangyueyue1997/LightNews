package com.pb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pb.common.vo.JsonResult;
import com.pb.common.vo.PageObject;
import com.pb.pojo.Advertisement;
import com.pb.service.AdsService;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.attribute.PosixFileAttributes;

@Controller
@RequestMapping("/advertisement")
public class AdvertisementController {
	@Autowired
	private AdsService service = null;

	@RequestMapping("listUI")
	public String listUI() {
		return "sys/advertisement/advertisement_list";
	}

	@RequestMapping("doFindObjects")
	@ResponseBody
	public JsonResult doFindObjects(Integer pageCurrent) {
		PageObject<Advertisement> obj = service.findPageObjects(pageCurrent);
		return new JsonResult(1, obj);
	}

	@RequestMapping("detailsUI")
	public String doDetailsUI() {
		return "sys/advertisement/advertisement_detail";
	}
	
	@RequestMapping("doUploadUI")
	public String doUploadUI(){
		return "sys/advertisement/advertisement_upload";
	}

	@RequestMapping("doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObject(@RequestParam("ids") String ids) {
		System.out.println(ids);
		service.deleteObjects(ids);
		return new JsonResult(1,"delete OK");
	}
	
	@RequestMapping("dofindObjectById")
	@ResponseBody
	public JsonResult dofindObjectById(Integer id){
		Advertisement data = service.findObjectById(id);
		return new JsonResult(1,data);
	}

	@RequestMapping(value = "doUploadObject",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult doUploadObject(@RequestParam("uploadedFiles")MultipartFile[] file,
									 @RequestParam("link")String link) {

		JsonResult result = new JsonResult();
		int state = service.uploadFiles(file[0],link);

		//判断返回结果
		switch (state) {
			case AdsService.FILE_EXTENSION_ERROR:
				result.setState(0);
				result.setMessage("文件类型错误");
				break;
			case AdsService.FILE_SAVE_FAILED:
				result.setState(0);
				result.setMessage("文件保存失败");
				break;
			case AdsService.FILE_SAVE_SUCCESS:
				result.setState(1);
				result.setMessage("文件保存成功");
				break;
			case AdsService.FILE_IS_EMPTY:
				result.setState(0);
				result.setMessage("文件为空");
		}

		return result;
	}

	@RequestMapping(value = "deleteAdsById",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult deleteAdsById(@RequestParam(value = "id") int id){
		Advertisement advertisement = new Advertisement();
		advertisement.setId(id);
		int count = service.deleteAdsById(advertisement);
		return count == 1? new JsonResult(1,"删除成功"):new JsonResult(2,"删除失败");
	}

	@RequestMapping("adsDetail")
	public String adsDetail(@RequestParam("id") Integer adsid){
		return "ads_content";
	}

}
