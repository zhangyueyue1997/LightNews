package com.pb.controller;

import cn.hutool.crypto.digest.DigestUtil;
import com.pb.common.vo.JsonResult;
import com.pb.common.vo.PageObject;
import com.pb.pojo.Users;
import com.pb.pojo.WxUser;
import com.pb.service.UserService;
import com.pb.service.WxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("/")
public class UserController {
	@Autowired
	private UserService service;
	@Autowired
	private WxUserService wxservice;

	@RequestMapping("index")
	public String index() {
		return "index";
	}

	@RequestMapping("login")
	public String login(){
		return "login";
	}


	@RequestMapping(value = "verify",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult verify(@RequestParam("username")String username,@RequestParam("password") String password) throws IOException {

	    Users verifyingUser = new Users();
	    verifyingUser.setUsername(username);
	    verifyingUser.setPassword(password);

	    JsonResult result = new JsonResult();
	    Users storedUser = service.selectUserNameAndPassword(username, DigestUtil.md5Hex(DigestUtil.md5Hex(password)+username));

		if (storedUser != null){
			result.setState(1);
			result.setMessage("保存成功");
			return result;
		}else {
			result.setState(0);
			result.setMessage("用户名或密码错误");
			return result;
		}
	}

		/** 返回首页starter */
	@RequestMapping("indexUI")
	public String indexUI() {
		return "starter";
	}

	/** 返回sys/user_llist，查询信息并加载到页面 */
	@RequestMapping("listUI")
	public String listUI() {
		return "sys/user/user_list";
	}


	@RequestMapping(value = "doFindPageObjects",method = RequestMethod.GET)
	@ResponseBody
	public JsonResult doFindPageObjects(@RequestParam("name") String name,
										@RequestParam("pageCurrent") Integer pageCurrent) {
		PageObject<Users> records = service.findPageObjects(name, pageCurrent);
		return new JsonResult(1, "OK", records);
	}

	@RequestMapping("doDeleteObjects")
	@ResponseBody
	public JsonResult doDeleteObjects(String[] checkedIds) {
		int row = service.deleteObjects(checkedIds);
		return row == 1 ? new JsonResult(1, "删除成功") :
						  new JsonResult(2, "删除失败");
	}

	@RequestMapping("saveUI")
	public String saveUI() {
		System.out.println("saveUI");
		return "sys/user/user_edit";
	}


	@RequestMapping(value = "doSaveObject",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult doSaveObject(@RequestParam("username") String username,
								   @RequestParam("password") String password) {
            String md5Hex1 = DigestUtil.md5Hex(DigestUtil.md5Hex(password)+username);
           //声明Users实例
           Users entity = new Users();
           entity.setUsername(username);
           entity.setPassword(md5Hex1);
           entity.setCreatedUser("root");

           int count = service.saveObjects(entity);
           return count == 1? new JsonResult(1, "保存成功") :
                   new JsonResult(2, "保存失败");

	}

	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObjects(Users entity) {
		int row = service.updateObjects(entity);
		return row == 1 ? new JsonResult(1, "更新成功") :
				          new JsonResult(2, "更新失败");
	}

	@RequestMapping("findObjectById")
	@ResponseBody
	public JsonResult findObjectById(Integer id){
		Users data = service.findObjectById(id);
		return new JsonResult(1,"OK",data);
	}

	@RequestMapping(value = "doUpdateByNameOrID",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult doUpdateByNameOrID(@RequestParam("ids[]") String[] ids) {
		Integer[] intIDs = new Integer[ids.length];
		for (int i = 0; i < ids.length; i++) {
			intIDs[i] = Integer.valueOf(ids[i]);
		}

		int row = service.updateByNameOrID(intIDs, null);
		return row == 1 ? new JsonResult(1, "删除成功") :
				          new JsonResult(2, "删除失败");
	}


	@RequestMapping("updatePasswordById")
	@ResponseBody
	public JsonResult updatePasswordById(Users entity) {
		String md5Hex1 = DigestUtil.md5Hex(DigestUtil.md5Hex(entity.getPassword()) +entity.getUsername());
		entity.setPassword(md5Hex1);
		int row = service.updatePasswordByUserName(entity);
		return row == 1 ? new JsonResult(1, "更新成功") :
				new JsonResult(2, "更新失败");
	}

	/**管理微信信息*/
	@RequestMapping("wxInformation")
	public String wxInformation(){
		return "sys/user/user_wx";
	}

	@RequestMapping("showWxUser")
	@ResponseBody
	public JsonResult showWxUser(
								@RequestParam("nickName") String openid,
								@RequestParam("pageCurrent") int pageCurrent){
		PageObject<WxUser> wxuser =  wxservice.findPageObjects(openid,pageCurrent);
		return new JsonResult(1,"ok",wxuser);
	}


	@RequestMapping(value = "updateStatusByOpenid",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult updateStatusByOpenid(@RequestParam("ids[]") String[] ids) {
//		String[] intIDs = new String[ids.length];
		for (int i = 0; i < ids.length; i++) {
//			intIDs[i] = String.valueOf(ids[i]);
			int row = wxservice.updateStatusByOpenid(ids[i]);
			return row == 1 ? new JsonResult(1, "删除成功") :
					new JsonResult(2, "删除失败");
		}

		return null;
	}


	@RequestMapping(value = "newsContent")
	public String newsContent() {
		return "news_content";
	}

	@RequestMapping(value = "userCenter")
	public String userCenter() {
		return "user_center";
	}

	@RequestMapping(value = "userHistory")
	public String userHistory() {
		return "user_history";
	}

	@RequestMapping(value = "userMessage")
	public String userMessage() {
		return "user_message";
	}

	@RequestMapping(value = "userPassword")
	public String userPassword() {
		return "user_password";
	}

	@RequestMapping(value = "userLogin")
	public String userLogin() {
		return "dengluzhuce/denglu";
	}

	@RequestMapping(value = "userRegister")
	public String userRegister() {
		return "dengluzhuce/zhuce";
	}
}
