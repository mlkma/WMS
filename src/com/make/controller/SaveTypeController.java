/**  
 * @Title: SaveTypeController.java
 * @Package com.make.controller
 * @Description: TODO
 * @author zhengjf
 * @date 2016-4-17
 */
package com.make.controller;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.make.bean.SaveTypeBean;
import com.make.bean.UserBean;
import com.make.service.ISaveTypeService;
import com.make.util.DateUtils;

/**
 * ClassName: SaveTypeController
 * 
 * @Description: 基础管理-存储类型信息管理controller
 * @author zhengjf
 * @date 2016-4-17
 */
@Controller
@RequestMapping("save")
public class SaveTypeController {

	Logger log = Logger.getLogger(SaveTypeController.class);

	@Autowired
	ISaveTypeService saveService;

	/**
	 * @Description: 查询存储信息
	 * @param @param req
	 * @param @param item
	 * @param @return
	 * @return String
	 * @throws
	 * @author zhengjf
	 * @date 2016-4-17
	 */
	@RequestMapping("list.do")
	public String loadPackInfo(HttpServletRequest req, SaveTypeBean item) {
		try {
			HttpSession session = req.getSession();
			List<SaveTypeBean> list = saveService.loadInfo(item);
			session.setAttribute("list", list);
			return "base/saveTypeInfo";
		} catch (Exception e) {
			log.error("程序出错：" + e);
		}

		return "error";
	}

	/**
	 * @Description: 跳转到添加或者修改页面
	 * @param
	 * @return void
	 * @throws
	 * @author zhengjf
	 * @date 2016-4-17
	 */
	@RequestMapping("linkPage.do")
	public void linkPage(HttpServletResponse res, int id) {
		try {
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();

			SaveTypeBean item = saveService.linkPage(id);
			JSONObject json = JSONObject.fromObject(item);
			out.print(json.toString());
			out.close();
		} catch (Exception e) {
			log.error("程序出错：" + e);
		}
	}

	/**
	 * @Description: 添加或者修改存储信息
	 * @param @param req
	 * @param @param item
	 * @param @return
	 * @return String
	 * @throws
	 * @author zhengjf
	 * @date 2016-4-17
	 */
	@RequestMapping("save.do")
	public String saveInfo(HttpServletRequest req, SaveTypeBean item) {
		try {
			HttpSession session = req.getSession();
			UserBean user = (UserBean) session.getAttribute("user");
			item.setOwner(user.getRealName());
			item.setStatus(0);
			item.setCreatDate(DateUtils.date2String(new Date(), ""));

			saveService.insertInfo(item);
			return "redirect:list.do";
		} catch (Exception e) {
			log.error("程序出错：" + e);
		}
		return "error";
	}

}
