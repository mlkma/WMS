/**  
 * @Title: ReceiveServiceImp.java
 * @Package com.make.service.imp
 * @Description: TODO
 * @author zhengjf
 * @date 2016-4-21
 */
package com.make.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.make.bean.LjxxglBean;
import com.make.bean.PackBean;
import com.make.bean.ReceiveBean;
import com.make.mapper.ILjxxglMapper;
import com.make.mapper.IPackMapper;
import com.make.mapper.IReceiveMapper;
import com.make.service.IReceiveService;

/**
 * ClassName: ReceiveServiceImp
 * 
 * @Description: TODO
 * @author zhengjf
 * @date 2016-4-21
 */
@Service
public class ReceiveServiceImp implements IReceiveService {

	@Autowired
	IReceiveMapper receiveDao;

	@Autowired
	ILjxxglMapper ljxxDao;

	@Autowired
	IPackMapper packDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.make.service.IReceiveService#loadInfo(com.make.bean.ReceiveBean)
	 */
	public List<ReceiveBean> loadInfo(ReceiveBean item) {
		return receiveDao.loadInfo(item);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.make.service.IReceiveService#insertInfo(com.make.bean.ReceiveBean)
	 */
	public int insertInfo(ReceiveBean item) {
		return receiveDao.insertInfo(item);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.make.service.IReceiveService#updateInfo(com.make.bean.ReceiveBean)
	 */
	public int updateInfo(ReceiveBean item) {
		return receiveDao.updateInfo(item);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.make.service.IReceiveService#linkPage(int)
	 */
	public Map<String, Object> linkPage(int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		ReceiveBean item = new ReceiveBean();
		item.setId(id);
		if (id > 0) {
			item = receiveDao.loadInfo(item).get(0);
		}
		List<LjxxglBean> ljxxList = ljxxDao.selectljxxglBeanall();
		List<PackBean> packList = packDao.loadPackInfo(null);
		map.put("item", item);
		map.put("ljxxList", ljxxList);
		map.put("packList", packList);
		return map;
	}
}
