package com.lover.mng;

import hibernate.db.HbmOperator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.common.SysDefine;
import com.web.common.SysCommonFunc;
import com.web.common.beanutil.BeanInitializer;
import com.web.obj.Admininfo;

public class AdminMng {
	public AdminMng() {
	}

	public static Admininfo adminLogin(String adminname,String password)
	  {
	      Admininfo adminInfo = null;

	      List list =HbmOperator.list("from Admininfo as o where o.adminname='"+adminname+"'");
	      if(list.size() > 0)
	      {
	        Admininfo temp = (Admininfo) list.get(0);
	        if(temp.getAdminpass().equals(password))
	        {
	           adminInfo = temp;
	           adminInfo.setLasttime(new Date(System.currentTimeMillis()));
	           adminInfo.setDlcs(new Long(adminInfo.getDlcs().longValue()+1));
	           try
	           {
	             HbmOperator.update(adminInfo);
	           }catch(Exception e){System.out.println(e.getMessage());}
	           ///记录登陆信息
	        }
	      }
	      return adminInfo;
	  }
	public static List getAdminList() {
		return HbmOperator.list("from Admininfo as o");
	}

	public static Admininfo getAdmin(String sql) {
		List list = HbmOperator.list(sql);
		if (list != null && list.size() > 0)
			return (Admininfo) list.get(0);
		return null;
	}

	public static String addAdmin(HttpServletRequest request) {
		String s = null;
		Admininfo admin = (Admininfo) BeanInitializer.initBean(Admininfo.class,
				request);
		if (admin.getAdminname() == null || admin.getAdminname().trim().length() == 0)
            return "请输入用户名!";
		if (admin.getAdminpass() == null || admin.getAdminpass().trim().length() == 0)
			return "请输入密码！";
		// /查询数据库中是否存在改用户
		if (AdminMng.getAdmin("from Admininfo as o where o.adminname='" + admin.getAdminname() + "'") != null)
			return "该用户名已经被使用！";
		admin.setDlcs(new Long(0));
		admin.setId(SysCommonFunc
				.getSequenceIdForOracle(SysDefine.SEQ_ADMININFO));
		admin.setRegtime(new Date(System.currentTimeMillis()));
		try {
			HbmOperator.insert(admin);
		} catch (Exception e) {
			s = "增加管理员出错，请与开发商联系！";
			System.out.println(e.getMessage());
		}
		return s;
	}

	public static String modAdmin(HttpServletRequest request) {
		String s = null;

		// /查询数据库中是否存在改用户
		Admininfo admin = AdminMng.getAdmin("from Admininfo as o where o.id="
				+ SysCommonFunc.getStrParameter(request, "mid"));
		if (admin == null)
			return "没有找到该用户！";
		BeanInitializer.updateBean(admin, request);
		if (admin.getAdminname() == null
				|| admin.getAdminname().trim().length() == 0)
			return "请输入用户名!";
		if (admin.getAdminpass() == null
				|| admin.getAdminpass().trim().length() == 0)
			return "请输入密码!";
		Admininfo adminTemp = AdminMng
				.getAdmin("from Admininfo as o where o.adminname='"
						+ admin.getAdminname() + "'");
		if (adminTemp.getId().longValue() != admin.getId().longValue()
				&& admin.getAdminname().equals(adminTemp.getAdminname()))
			return "该用户名已经被使用！";
		try {
			HbmOperator.update(admin);
		} catch (Exception e) {
			s = "修改用户信息出错，请与开发商联系!";
			System.out.println(e.getMessage());
		}
		return s;
	}

}

