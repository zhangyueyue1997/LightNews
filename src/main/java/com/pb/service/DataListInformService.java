package com.pb.service;

import com.pb.common.vo.DataListInform;
import com.pb.common.vo.PageObject;
import com.pb.pojo.WxUser;

public interface DataListInformService {
	
	/**查询Data表中 content字段和createdUser字段用于前端列表显示*/
	PageObject<DataListInform> findDataListInfo(int pageCount);
	/**根据id值查询内容*/
	DataListInform findObjectById(Integer id);
	/**根据address查询百度云地址*/
	DataListInform findBaiDuYunByAddress(DataListInform dataListInform);
	/**用户使用积分预览后添加字段*/
	int insertPreview(String openid,String filename);
	/**用户使用积分下载后添加字段*/
	int insertDownload(String openid,String filename);
	/**查询用户是否浏览过*/
	int selectFilename(String filename,String openid);
	/**查询用户是否曾经下载过*/
	int selectDownload(String openid,String filename);
	/**查询图书总数*/
	int selectCount();
	/**查询用户其他资料是否曾经浏览*/
	int selectListId(WxUser wxUser);
	/**用户使用积分预览其他资料后添加字段*/
	int insertListData(WxUser wxUser);
	/**查询是否是自己添加的商机信息*/
	int selectOneself(WxUser wxUser);

}
