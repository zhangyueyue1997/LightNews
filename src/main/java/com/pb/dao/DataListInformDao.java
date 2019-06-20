package com.pb.dao;

import java.util.List;

import com.pb.common.vo.DataListInform;
import com.pb.pojo.Data;
import com.pb.pojo.WxUser;
import org.apache.ibatis.annotations.Param;

public interface DataListInformDao {
	
	/**查询Data表中 content字段和createdUser字段用于前端列表显示*/
	List<DataListInform> findDataListInfo(@Param("startIndex") int startIndex,@Param("pageSize") int pageSize);
	/**根据id值查询内容*/
	DataListInform findObjectById(Integer id);
	/**根据address查询百度云地址*/
	DataListInform findBaiDuYunByAddress(DataListInform dataListInform);
	/**用户使用积分预览图书资料后添加字段*/
	int insertPreview(@Param("openid") String openid,
					  @Param("filename") String filename);
	/**用户使用积分下载图书资料后添加字段*/
	int insertDownload(@Param("openid") String openid,
					   @Param("filename") String filename);
	/**查询用户的图书资料是否曾经浏览过*/
	int selectFilename(@Param("filename") String filename,@Param("openid") String openid);
	/**查询用户的图书资料是否曾经下载过*/
	int selectDownload(@Param("openid") String openid,@Param("filename") String filename);
	/**查询图书总数*/
	int selectCount();
	/**查询用户其他资料是否曾经浏览*/
	int selectListId(WxUser wxUser);
	/**用户使用积分预览其他资料后添加字段*/
	int insertListData(WxUser wxUser);
	/**查询是否是自己添加的商机信息*/
	int selectOneself(WxUser wxUser);
}
