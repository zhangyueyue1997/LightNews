package com.pb.controller;

import com.pb.common.vo.JsonResult;
import com.pb.common.vo.PageObject;
import com.pb.pojo.Data;
import com.pb.pojo.Publish;
import com.pb.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@Controller
@RequestMapping("/publish/")
public class PublishController {
	@Autowired
	private PublishService service = null;

	@RequestMapping("listUI")
	public String listUI() {
		return "sys/publish/publish_list";
	}
	
	@RequestMapping("detailUI")
	public String detailUI(){
		return "sys/publish/publish_detail";
	}

	@RequestMapping("dofindPageObjects")
	@ResponseBody
	public JsonResult dofindPageObjects(@RequestParam("pageCurrent") Integer pageCurrent) {
		PageObject<Publish> obj = service.findPageObjects(pageCurrent,null);
		return new JsonResult(1, obj);
	}

	@RequestMapping(value = "doDeleteObject",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult doDeleteObject(@RequestParam(value = "ids") String[] ids) {
		service.deleteObjects(ids);
		return new JsonResult(1, "删除成功");
	}

	@RequestMapping("dofindObjectById")
	@ResponseBody
	public JsonResult dofindObjectById(Integer id) {
		Publish publish = service.findObjectById(id);
		return new JsonResult(1,publish);
	}


	@RequestMapping(value = "updateStatus",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult updateStatus(@RequestParam("id") int id){
		Publish publish =  new Publish();
		publish.setId(id);
		int count = service.updateStatus(publish);
		return count == 1?new JsonResult(1,"ok"):new JsonResult(0,"error");
	}


	@RequestMapping(value = "selectStatusById",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult selectStatusById(@RequestParam("publishId") int publishId){

		int count = service.selectStatusById(publishId);

		return count == 1?new JsonResult(1,1):new JsonResult(0,0);
	}
}
