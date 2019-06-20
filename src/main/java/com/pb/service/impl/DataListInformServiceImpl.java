package com.pb.service.impl;

import com.pb.common.vo.DataListInform;
import com.pb.common.vo.PageObject;
import com.pb.dao.DataListInformDao;
import com.pb.pojo.WxUser;
import com.pb.service.DataListInformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataListInformServiceImpl implements DataListInformService {

	@Autowired
	private DataListInformDao dataListInformDao;

	@Override
	public PageObject<DataListInform> findDataListInfo(int pageCurrent) {
		int pageSize = 8;
		int startIndex = (pageCurrent - 1) * pageSize;
		List<DataListInform> list = dataListInformDao.findDataListInfo(startIndex,pageSize);
		int rowCount = dataListInformDao.selectCount();
		int pageCount = rowCount / pageSize;
		if(rowCount % pageSize != 0){
			pageCount++;
		}

		PageObject<DataListInform> obj = new PageObject<>();//创建PageObject对象用于封装信息
		/**封装信息*/
		obj.setPageCount(pageCount);
		obj.setPageCurrent(pageCurrent);
		obj.setRecords(list);
		obj.setRowCount(rowCount);

		return obj;
	}

	@Override
	public DataListInform findObjectById(Integer id) {
		return dataListInformDao.findObjectById(id);
	}

	@Override
	public DataListInform findBaiDuYunByAddress(DataListInform dataListInform) {
		return dataListInformDao.findBaiDuYunByAddress(dataListInform);
	}

	@Override
	public int insertPreview(String openid, String filename) {
		return dataListInformDao.insertPreview(openid,filename);
	}

	@Override
	public int insertDownload(String openid, String filename) {
		return dataListInformDao.insertDownload(openid,filename);
	}

	@Override
	public int selectFilename(String filename,String openid) {
		return dataListInformDao.selectFilename(filename,openid);
	}

	@Override
	public int selectDownload(String openid, String filename) {
		return dataListInformDao.selectDownload(openid,filename);
	}

	@Override
	public int selectCount() {
		return dataListInformDao.selectCount();
	}

	@Override
	public int selectOneself(WxUser wxUser) {
		return dataListInformDao.selectOneself(wxUser);
	}

	@Override
	public int selectListId(WxUser wxUser) {
		return dataListInformDao.selectListId(wxUser);
	}

	@Override
	public int insertListData(WxUser wxUser) {
		return dataListInformDao.insertListData(wxUser);
	}
}
