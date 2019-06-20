package com.pb.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.pb.pojo.Users;
/***
 * 对应mapper文件的持久（dao）层数据
 * @author Yang
 *
 */
public interface UserDao {
	/**（可以根据用户名）查找页面信息*/
	List<Users> findPageObjects(
			@Param("username") String username,
			@Param("startIndex") Integer startIndex,
			@Param("pageSize") Integer pageSize);
	/**获取总记录数*/
	int getRowCount();
	/**根据选择的id删除数据*/
	int deleteObjects(@Param("ids")String[] ids);
	/**添加数据*/
	int saveObjects(Users entity);
	/**更新数据信息*/
	int updateObjects(Users entity);
	/**根据id查找数据*/
	Users findObjectById(@Param("id")Integer id);
	/**根据用户名/ID逻辑删除数据*/
	int updateByNameOrID(@Param("id") Integer id,@Param("username")String username);
	/**登录验证*/
	Users selectUserNameAndPassword(@Param("username") String username,@Param("password") String password);
	/**根据ID修改密码*/
	int updatePasswordByUserName(Users entity);

}
