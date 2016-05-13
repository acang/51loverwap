package com.web.obj.extend;

import java.util.Hashtable;
import java.util.List;

import com.web.map.*;
import com.web.obj.*;
import com.web.obj.extend.*;

public class DicList {

	public static Hashtable dictypelist = new Hashtable();
	static {
		try {
			DicList.initDicList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public DicList() {
	}

	public static void initDicList() throws Exception {
		dictypelist.clear();
		List list = DicTypeMap.getAllTypeList();
		for (int i = 0; list != null && i < list.size(); i++) {
			Dictype dt = (Dictype) list.get(i);
			dictypelist.put(dt.getTypeid(), new DicTypeWithDic(dt));
		}
	}

	public static List getDicList(String dictype) {
		DicTypeWithDic dtwd = (DicTypeWithDic) dictypelist.get(dictype);
		if (dtwd == null)
			return null;
		return dtwd.diclist1;
	}

	public static String getDicListOption(String type, int value) {
		StringBuffer sb = new StringBuffer();
		List list = DicList.getDicList(type);
        for (int i = 0; list != null && i < list.size(); i++) {
			Dic dic = (Dic) list.get(i);
			if (dic.getDicnumber().equals(value + "")) {
				sb.append("<option value ='" + dic.getDicnumber()
						+ "' selected>" + dic.getContent() + "</option>\n");

			} else {
				sb.append("<option value ='" + dic.getDicnumber() + "'>"
						+ dic.getContent() + "</option>\n");
			}

		}
        return sb.toString();
	}

	public static String getDicListOption(String type, String value) {
		StringBuffer sb = new StringBuffer();
		List list = DicList.getDicList(type);
		for (int i = 0; list != null && i < list.size(); i++) {
			Dic dic = (Dic) list.get(i);
			if (dic.getDicnumber().equals(value)) {
				sb.append("<option value ='" + dic.getDicnumber()
						+ "' selected>" + dic.getContent() + "</option>\n");

			} else {
				sb.append("<option value ='" + dic.getDicnumber() + "'>"
						+ dic.getContent() + "</option>\n");
			}

		}
		return sb.toString();
	}

	public static Hashtable getDicListHashtable(String dictype) {
		DicTypeWithDic dtwd = (DicTypeWithDic) dictypelist.get(dictype);
		if (dtwd == null)
			return null;
		return dtwd.diclist;
	}

	public static Dic getDic(String dictype, String dicnumber) {
		DicTypeWithDic dtwd = (DicTypeWithDic) dictypelist.get(dictype);
		if (dtwd == null)
			return null;
		// System.out.println(dtwd);
		return dtwd.getDicByDicNumber(dicnumber);
	}

	public static String getDicValue(String dictype, String dicnumber, int type) {
		Dic dic = getDic(dictype, dicnumber);
		if (dic == null)
			return "";
		if (type == 1)
			return dic.getContent();
		if (type == 2)
			return dic.getExtend1();
		if (type == 3)
			return dic.getExtend2();
		if (type == 4)
			return dic.getExtend3();
		return "";
	}

	public static Dic getSingDicObject(String dictype) {
		DicTypeWithDic dtwd = (DicTypeWithDic) dictypelist.get(dictype);
		if (dtwd == null)
			return null;
		return dtwd.getSingDicObject();
	}

	public static String getSingDicValue(String dictype, int type) {
		DicTypeWithDic dtwd = (DicTypeWithDic) dictypelist.get(dictype);
		if (dtwd == null)
			return "";
		return dtwd.getSingDicValue(type);
	}

}
