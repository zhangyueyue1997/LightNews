package com.pb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pb.common.vo.PageObject;
import com.pb.dao.UserDao;
import com.pb.pojo.Users;
import com.pb.service.UserService;
/***
 * 业务层的具体实现类
 * @author Yang
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao = null;

	/**登录验证*/
	@Override
	public Users selectUserNameAndPassword(String username, String password) {
		return dao.selectUserNameAndPassword(username, password);
	}

	public PageObject<Users> findPageObjects(String username, Integer pageCurrent) {
		int pageSize = 8;//设置单页显示的数据条目数为3.
		int startIndex = (pageCurrent - 1) * pageSize;//计算获得startIndex用于sql查询
		List<Users> records = dao.findPageObjects(username, startIndex, pageSize);//获取数据
		int rowCount = dao.getRowCount();//获取总记录数
		int pageCount = rowCount / pageSize; //计算获得总页数
		
		/**总记录数不能除尽单页显示条目数，则总页数增加一页，用于显示零头信息*/
		if(rowCount % pageSize != 0){
			pageCount++;
		}
	
		PageObject<Users> obj = new PageObject<>();//创建PageObject对象用于封装信息
		/**封装信息*/
		obj.setPageCount(pageCount);
		obj.setPageCurrent(pageCurrent);
		obj.setRecords(records);
		obj.setRowCount(rowCount);
		
		return obj;//返回PageObject对象(到控制层)
	}

	@Override
	public int deleteObjects(String[] ids) {
		return dao.deleteObjects(ids);
	}

	@Override
	public int saveObjects(Users entity) {
		try {
			return dao.saveObjects(entity);
		}catch (Exception e){
			return 0;
		}
	}

	@Override
	public int updateObjects(Users entity) {
		return dao.updateObjects(entity);
	}

	@Override
	public Users findObjectById(Integer id) {
		return dao.findObjectById(id);
	}

	@Override
	public int updateByNameOrID(Integer[] id, String username) {
		int count = 0;
		for (Integer i : id) {
			count += dao.updateByNameOrID(i, username);
		}
		return count == id.length ? 1 : 0;

	}

	@Override
	public int updatePasswordByUserName(Users entity) {
		return dao.updatePasswordByUserName(entity);
	}

	//	@Override
//	public int selectUsername(String username) {
//		return dao.selectUsername(username);
//	}
//	/**解密*/
//	@Override
//	public String selectPassword(String username) {
//		return dao.selectPassword(username)
//;	}
}
