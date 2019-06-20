package com.pb.service;

import com.pb.common.vo.PageObject;
import com.pb.pojo.Users;

/***
 * 业务层接口用于对接控制层（Controller）和持久层（dao）
 * @author Yang
 *
 */
public interface UserService {
	/**（可以根据用户名）查找页面信息*/
	PageObject<Users> findPageObjects(String username,Integer pageCurrent);
	/**根据id值删除对象*/
	int deleteObjects(String[] ids);	
	/**保存用户*/
	int saveObjects(Users entity);
	/**更新用户信息*/
	int updateObjects(Users entity);
	/**根据id查找数据*/
	Users findObjectById(Integer id);
	/**根据用户名/ID逻辑删除数据*/
	int updateByNameOrID(Integer[] id,String username);
	/**登录验证*/
	Users selectUserNameAndPassword(String username,String password);
	/**根据ID修改密码*/
	int updatePasswordByUserName(Users entity);
}
