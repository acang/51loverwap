package com.lover.mng;

import com.web.common.*;
import com.common.*;
import javax.servlet.http.HttpServletRequest;
import com.web.obj.*;
import com.web.common.beanutil.*;
import hibernate.db.*;
import java.text.SimpleDateFormat;
import java.util.*;
import com.web.bean.QueryRecord;
import com.web.bean.QueryResult;
import org.apache.commons.beanutils.DynaBean;
import java.io.*;

public class BBSMng {
	public BBSMng() {
	}

	public static final String[] gradeDes = new String[] { "新手", "★", "★★",
			"★★★", "★★★★", "★★★★★", "★★★★★★", "★★★★★★★", "★★★★★★★★" };
	public static final int[] gradeMongy = new int[] { 0, 10, 100, 300, 1000,
			2000, 4000, 7000, 10000 };

	public static String userAddReTopic(HttpServletRequest request,
			Userinfo user, String content, String bbsid) {
		String s = null;
		String lsendtime = (String) request.getSession().getAttribute("lsendtime");
		if (lsendtime != null) {
			long time = Long.parseLong(lsendtime);
			if ((System.currentTimeMillis() - time) < 1000 * 60 * 3)
				return "三分钟之内不能连续发贴，请过会再发";
		}

		String sqlt = "select id from bbs where re_id > 0  and user_id = "
				+ user.getHyid()
				+ " and  round(to_number(sysdate-s_time)*1440)  < 3";
		if (QueryRecord.query(sqlt).size() > 0)
			return "三分钟之内不能连续发贴，请过会再发";

		Vector saveList = new Vector();
		Bbsuser buser = BBSMng.getBbsuserByHyid(user.getHyid().toString());
		Bb topicBb = BBSMng.getBBSById(bbsid);
		Date cdate = new Date(System.currentTimeMillis());
		// //增加判断回帖重复
		sqlt = "select * from Bcb where id in (select content from bbs where user_id= "
				+ user.getHyid()+ ")";
        // + " and re_id = " + topicBb.getId() + ")"
		List rList = QueryRecord.query(sqlt);
        if(rList!=null){
            for (int i = 0; i < rList.size(); i++) {
                DynaBean tempB = (DynaBean) rList.get(i);
                String ts = SysCommonFunc.getStringFromBlob((java.sql.Blob) tempB.get("content"));
                if (ts.equals(content))
                    return "请不要重复您的回帖！";

            }
        }

        //查询发帖的人的user_id,用于给他加金币，沈凯健2015-5-17
        String  findUserId = "select b.user_id,u.username,u.lcname from bbs b left join userinfo u   on u.hyid = b.user_id where b.id= "+topicBb.getId();
        List fatierenId_list = QueryRecord.query(findUserId);
        String fatieren_Id = "0";
        String uname = "";
        String lcname = "";
          if(fatierenId_list!=null && fatierenId_list.size()>0){
              DynaBean tempB = (DynaBean) fatierenId_list.get(0);
              fatieren_Id = tempB.get("user_id").toString();
              uname =  tempB.get("username").toString();
              lcname =  tempB.get("lcname").toString();
          }
		//增加判断回帖是否连续2次
		List bbsList = QueryRecord.queryByHbm(
	            "from Bb where re_id = "+topicBb.getId()+" and s_time<=to_date('"+DateTools.dateToString(cdate,true)+"','YYYY-MM-DD HH24:MI:SS') order by s_time desc", 2, 1, false, 0).resultList;
		if (bbsList!=null && bbsList.size()==2){
			if (((Bb)bbsList.get(0)).getUserId().longValue()==((Bb)bbsList.get(1)).getUserId().longValue() && user.getHyid()==((Bb)bbsList.get(0)).getUserId().longValue()){
				return "同一主题帖只能连续回复两次！";
			}
		}
        if(!"0".equals(fatieren_Id)){
            Bbsuser fatie_user = BBSMng.getBbsuserByHyid(fatieren_Id);
                try {
            if (fatie_user == null) {
                fatie_user = new Bbsuser();
                fatie_user.setCommend(new Long(0));
                fatie_user.setGrade(new Long(0));
                fatie_user.setHyid(new Long(fatieren_Id));
                fatie_user.setLcname(lcname);
                fatie_user.setMoneys(new Long(0));
                fatie_user.setRegtime(user.getRegtime());
                fatie_user.setResumnew(new Long(1));
                fatie_user.setReplynum(new Long(0));
                fatie_user.setStaytime(new Long(0));
                fatie_user.setTopics(new Long(0));
                fatie_user.setMoneynew(new Long(8));
                fatie_user.setUsername(uname);
                MutSeaObject mso = new MutSeaObject();
                mso.setHbmSea(fatie_user, mso.SEA_HBM_INSERT);
                saveList.add(mso);
            } else {
                if(fatie_user.getResumnew() == null){
                    fatie_user.setResumnew(new Long(1));
                }else{
                    fatie_user.setResumnew(new Long(fatie_user.getResumnew().longValue() + 1));
                }
                if(fatie_user.getMoneynew()== null){
                    fatie_user.setMoneynew(new Long(8));
                }else{
                    fatie_user.setMoneynew(new Long(fatie_user.getMoneynew().longValue() + 8));
                }
                MutSeaObject mso = new MutSeaObject();
                mso.setHbmSea(fatie_user, mso.SEA_HBM_UPDATE);
                saveList.add(mso);
            }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                s = "回复帖子出错，请与管理员联系！";
            }
        }

		try {
            if (buser == null) {
 				buser = new Bbsuser();
				buser.setCommend(new Long(0));
				buser.setGrade(new Long(0));
				buser.setHyid(user.getHyid());
				buser.setLcname(user.getLcname());
				buser.setMoneys(new Long(0));
				buser.setRegtime(user.getRegtime());
				buser.setResum(new Long(0));
				buser.setStaytime(new Long(0));
				buser.setTopics(new Long(0));
				buser.setUsername(user.getUsername());
                buser.setReplynum(new Long("1"));
                buser.setMoneynew(new Long("8"));
				MutSeaObject mso = new MutSeaObject();
				mso.setHbmSea(buser, mso.SEA_HBM_INSERT);
				saveList.add(mso);
            } else {
                if(buser.getMoneynew()==null){
                    buser.setMoneynew(new Long("8"));
                }else{
                    buser.setMoneynew(new Long(buser.getMoneynew().longValue() + 8));
                }
                 if(buser.getReplynum() == null){
                     buser.setReplynum(new Long("1"));
                 }else{
                     buser.setReplynum(new Long(buser.getReplynum().longValue() + 1));
                 }



                MutSeaObject mso = new MutSeaObject();
				mso.setHbmSea(buser, mso.SEA_HBM_UPDATE);
				saveList.add(mso);


			}
			// 修改主题帖信息
			topicBb.setReCount(new Long(topicBb.getReCount().longValue() + 1));
			topicBb.setReTime(cdate);
			// topicBb.setStime(cdate);

			MutSeaObject mso = new MutSeaObject();
			mso.setHbmSea(topicBb, mso.SEA_HBM_UPDATE);
			saveList.add(mso);

			Bb bb = new Bb();
			bb.setAuthor(user.getLcname());
			bb.setCensor("");
			bb.setCommend(new Integer(bbsid));
			bb.setHits(new Long(0));
			bb.setId(SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_BBS));
			bb.setIscheck("0");
			bb.setOntop(new Integer(0));
			bb.setReCount(new Long(0));
			bb.setReId(topicBb.getId());
			bb.setSortid(topicBb.getSortid());
			bb.setStime(cdate);
			bb.setReTime(cdate);
			bb.setTitle(topicBb.getTitle());
			bb.setUserId(user.getHyid());
			bb.setUserName(user.getUsername());
			bb.setReason("");
			mso = new MutSeaObject();
			mso.setHbmSea(bb, mso.SEA_HBM_INSERT);
			saveList.add(mso);

			Bcb bcb = new Bcb();
			bcb.setId(SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_BCB));
			bb.setContent(bcb.getId());
			mso = new MutSeaObject();
			mso.setHbmSea(bcb, mso.SEA_HBM_INSERT);
			mso.setBlobStream(new ByteArrayInputStream(content.getBytes()),	"content");
			saveList.add(mso);

			String sql = "update bbssort set s_time=sysdate where id="	+ topicBb.getSortid();
			mso = new MutSeaObject();
			mso.setSqlSea(sql, mso.SEA_SQL_UPDATE);
			saveList.add(mso);

			HbmOperator.SeaMutDataWithBlob(saveList);
			request.getSession().setAttribute("lsendtime",
					System.currentTimeMillis() + "");

			BBSMng.modBbsAllInfo(bb.getSortid().toString(), false, bb
					.getUserId().toString(), bb.getReId().toString());

		} catch (Exception e) {
			System.out.println(e.getMessage());
			s = "回复帖子出错，请与管理员联系！";
		}

		return s;

	}
    public static String addReTopic(String hyid, String content, String bbsid) {
        String s = null;
//        String lsendtime = (String) request.getSession().getAttribute("lsendtime");
//        if (lsendtime != null) {
//            long time = Long.parseLong(lsendtime);
//            if ((System.currentTimeMillis() - time) < 1000 * 60 * 2)
//                return "两分钟之内不能连续发贴，请过会再发";
//        }
         Userinfo user=HYRegMng.getUserinfoByHyid(hyid);

        String sqlt = "select id from bbs where re_id > 0  and user_id = "
                + hyid
                + " and  round(to_number(sysdate-s_time)*1440)  < 2";
        if (QueryRecord.query(sqlt).size() > 0){
            return "两分钟之内不能连续发贴，请过会再发";
        }


        Vector saveList = new Vector();
        Bbsuser buser = BBSMng.getBbsuserByHyid(hyid);
        Bb topicBb = BBSMng.getBBSById(bbsid);
        Date cdate = new Date(System.currentTimeMillis());
        // //增加判断回帖重复
        sqlt = "select * from Bcb where id in (select content from bbs where user_id= "
                + hyid+ ")";
        // + " and re_id = " + topicBb.getId() + ")"
        List rList = QueryRecord.query(sqlt);
        for (int i = 0; i < rList.size(); i++) {
            DynaBean tempB = (DynaBean) rList.get(i);
            String ts = SysCommonFunc.getStringFromBlob((java.sql.Blob) tempB.get("content"));
            if (ts.equals(content)){
                return "请不要重复您的回帖！";
            }


        }
        //查询发帖的人的user_id,用于给他加金币，沈凯健2015-5-17
        String  findUserId = "select b.user_id,u.username,u.lcname from bbs b left join userinfo u   on u.hyid = b.user_id where b.id= "+topicBb.getId();
        List fatierenId_list = QueryRecord.query(findUserId);
        String fatieren_Id = "0";
        String uname = "";
        String lcname = "";
        if(fatierenId_list!=null && fatierenId_list.size()>0){
            DynaBean tempB = (DynaBean) fatierenId_list.get(0);
            fatieren_Id = tempB.get("user_id").toString();
            uname =  tempB.get("username").toString();
            lcname =  tempB.get("lcname").toString();
        }
        //增加判断回帖是否连续2次
        List bbsList = QueryRecord.queryByHbm(
                "from Bb where re_id = "+topicBb.getId()+" and s_time<=to_date('"+DateTools.dateToString(cdate,true)+"','YYYY-MM-DD HH24:MI:SS') order by s_time desc", 2, 1, false, 0).resultList;
        if (bbsList!=null && bbsList.size()==2){
            if (((Bb)bbsList.get(0)).getUserId().longValue()==((Bb)bbsList.get(1)).getUserId().longValue() && Long.parseLong(hyid)==((Bb)bbsList.get(0)).getUserId().longValue()){
                return "同一主题帖只能连续回复两次！";
            }
        }
        if(!"0".equals(fatieren_Id)){
            Bbsuser fatie_user = BBSMng.getBbsuserByHyid(fatieren_Id);
            try {
                if (fatie_user == null) {
                    fatie_user = new Bbsuser();
                    fatie_user.setCommend(new Long(0));
                    fatie_user.setGrade(new Long(0));
                    fatie_user.setHyid(new Long(fatieren_Id));
                    fatie_user.setLcname(lcname);
                    fatie_user.setMoneys(new Long(2));
                    fatie_user.setRegtime(user.getRegtime());
                    fatie_user.setResum(new Long(0));
                    fatie_user.setStaytime(new Long(0));
                    fatie_user.setTopics(new Long(0));
                    fatie_user.setUsername(uname);
                    MutSeaObject mso = new MutSeaObject();
                    mso.setHbmSea(fatie_user, mso.SEA_HBM_INSERT);
                    saveList.add(mso);
                } else {
                    fatie_user.setMoneys(new Long(fatie_user.getMoneys().longValue() + 5));
                    fatie_user.setResum(new Long(fatie_user.getResum().longValue() + 1));
                    MutSeaObject mso = new MutSeaObject();
                    mso.setHbmSea(fatie_user, mso.SEA_HBM_UPDATE);
                    saveList.add(mso);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                s = "回复帖子出错，请与管理员联系！";
            }
        }

        try {
            if (buser == null) {
                buser = new Bbsuser();
                buser.setCommend(new Long(0));
                buser.setGrade(new Long(0));
                buser.setHyid(Long.parseLong(hyid));
                buser.setLcname(user.getLcname());
                buser.setMoneys(new Long(6));
                buser.setRegtime(user.getRegtime());
                buser.setResum(new Long(1));
                buser.setStaytime(new Long(0));
                buser.setTopics(new Long(0));
                buser.setUsername(user.getUsername());
                MutSeaObject mso = new MutSeaObject();
                mso.setHbmSea(buser, mso.SEA_HBM_INSERT);
                saveList.add(mso);
            } else {
                buser.setMoneys(new Long(buser.getMoneys().longValue() + 6));
                buser.setResum(new Long(buser.getResum().longValue() + 1));
                MutSeaObject mso = new MutSeaObject();
                mso.setHbmSea(buser, mso.SEA_HBM_UPDATE);
                saveList.add(mso);


            }
            // 修改主题帖信息
            topicBb.setReCount(new Long(topicBb.getReCount().longValue() + 1));
            topicBb.setReTime(cdate);
            // topicBb.setStime(cdate);

            MutSeaObject mso = new MutSeaObject();
            mso.setHbmSea(topicBb, mso.SEA_HBM_UPDATE);
            saveList.add(mso);
            Bb bb = new Bb();
            bb.setAuthor(user.getLcname());
            bb.setCensor("");
            bb.setCommend(new Integer(bbsid));
            bb.setHits(new Long(0));
            bb.setId(SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_BBS));
            bb.setIscheck("0");
            bb.setOntop(new Integer(0));
            bb.setReCount(new Long(0));
            bb.setReId(topicBb.getId());
            bb.setSortid(topicBb.getSortid());
            bb.setStime(cdate);
            bb.setReTime(cdate);
            bb.setTitle(topicBb.getTitle());
            bb.setUserId(user.getHyid());
            bb.setUserName(user.getUsername());
            bb.setReason("");
            mso = new MutSeaObject();
            mso.setHbmSea(bb, mso.SEA_HBM_INSERT);
            saveList.add(mso);

            Bcb bcb = new Bcb();
            bcb.setId(SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_BCB));
            bb.setContent(bcb.getId());
            mso = new MutSeaObject();
            mso.setHbmSea(bcb, mso.SEA_HBM_INSERT);
            mso.setBlobStream(new ByteArrayInputStream(content.getBytes()),	"content");
            saveList.add(mso);

            String sql = "update bbssort set s_time=sysdate where id="	+ topicBb.getSortid();
            mso = new MutSeaObject();
            mso.setSqlSea(sql, mso.SEA_SQL_UPDATE);
            saveList.add(mso);

            HbmOperator.SeaMutDataWithBlob(saveList);


            BBSMng.modBbsAllInfo(bb.getSortid().toString(), false, bb
                    .getUserId().toString(), bb.getReId().toString());

            String sql2 = "select b.author,b.user_id,b.s_time,b.commend,b.zan,bu.grade,bu.moneys ,ui.regtime " +
                    "from bbs b  " +
                    "left join bbsuser bu on (b.user_id = bu.hyid) " +
                    "left join Userinfo ui on (b.user_id=ui.hyid) "+
                    "where b.user_id ="+hyid;
            QueryResult qr = QueryRecord.queryByDynaResultSet(sql2, 1, 1);
            String info = "";
            if(qr!=null && qr.resultList!=null && qr.resultList.size()==1)
            {
                DynaBean db = (DynaBean)qr.resultList.get(0);
                info = db.get("author").toString()+db.get("user_id").toString()+
                       db.get("regtime").toString()+db.get("grade").toString()+
                       db.get("moneys").toString();
            }
             s = info;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            s = "回复帖子出错，请与管理员联系！";
        }

        return s;

    }
	
	public static String reAddReTopic(HttpServletRequest request,
			Userinfo user, String content, String bbsid, String id) {
		String s = null;
		String lsendtime = (String) request.getSession().getAttribute(
				"lsendtime2");
		// System.out.println(lsendtime);
		if (lsendtime != null) {
			long time = Long.parseLong(lsendtime);
			if ((System.currentTimeMillis() - time) < 1000 * 60 * 3)
				return "三分钟之内不能连续发贴，请过会再发";
		}

		String sqlt = "select id from bbs where re_id > 0  and user_id = "
				+ user.getHyid()
				+ " and  round(to_number(sysdate-s_time)*1440)  < 3";
		if (QueryRecord.query(sqlt).size() > 0)
			return "三分钟之内不能连续发贴，请过会再发";

		Vector saveList = new Vector();
		Bbsuser buser = BBSMng.getBbsuserByHyid(user.getHyid().toString());
		Bb topicBb = BBSMng.getBBSById(bbsid);
		Bb topicBb2 = BBSMng.getBBSById(id);
		Date cdate = new Date(System.currentTimeMillis());
		// //增加判断回帖重复
		sqlt = "select * from Bcb where id in (select content from bbs where user_id= "
				+ user.getHyid()+ ")";
        // + " and re_id = " + topicBb.getId() + ")"
		List rList = QueryRecord.query(sqlt);
		// System.out.println(rList.size());
		for (int i = 0; i < rList.size(); i++) {
			DynaBean tempB = (DynaBean) rList.get(i);
			String ts = SysCommonFunc.getStringFromBlob((java.sql.Blob) tempB
					.get("content"));
			if (ts.equals(content))
				return "请不要重复您的回帖！";

		}

		try {
			if (buser == null) {
				buser = new Bbsuser();
				buser.setCommend(new Long(0));
				buser.setGrade(new Long(0));
				buser.setHyid(user.getHyid());
				buser.setLcname(user.getLcname());
				buser.setMoneys(new Long(0));
				buser.setRegtime(user.getRegtime());
				buser.setResum(new Long(0));
				buser.setStaytime(new Long(0));
				buser.setTopics(new Long(0));
				buser.setUsername(user.getUsername());
				MutSeaObject mso = new MutSeaObject();
				mso.setHbmSea(buser, mso.SEA_HBM_INSERT);
				saveList.add(mso);
			} else {
				buser.setMoneynew(new Long(buser.getMoneynew().longValue() +5));
				buser.setResumnew(new Long(buser.getResumnew().longValue() + 1));
				MutSeaObject mso = new MutSeaObject();
				mso.setHbmSea(buser, mso.SEA_HBM_UPDATE);
				saveList.add(mso);
			}
			// 修改主题帖信息
			topicBb2.setReCount(new Long(topicBb2.getReCount().longValue()+1));
			System.out.println();
			MutSeaObject mso = new MutSeaObject();
			mso.setHbmSea(topicBb2, mso.SEA_HBM_UPDATE);
			saveList.add(mso);
			topicBb.setReTime(cdate);
			// topicBb.setStime(cdate);
			mso= new MutSeaObject();
			mso.setHbmSea(topicBb, mso.SEA_HBM_UPDATE);
			saveList.add(mso);
			Bb bb = new Bb();
			bb.setAuthor(user.getLcname());
			bb.setCensor("");
			bb.setCommend(new Integer(id));
			bb.setHits(new Long(0));
			bb.setId(SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_BBS));
			bb.setIscheck("0");
			bb.setOntop(new Integer(0));
			bb.setReCount(new Long(0));
			bb.setReId(topicBb.getId());
			bb.setSortid(topicBb.getSortid());
			bb.setStime(cdate);
			bb.setReTime(cdate);
			bb.setTitle(topicBb.getTitle());
			bb.setUserId(user.getHyid());
			bb.setUserName(user.getUsername());
			bb.setReason("");
			mso = new MutSeaObject();
			mso.setHbmSea(bb, mso.SEA_HBM_INSERT);
			saveList.add(mso);

			Bcb bcb = new Bcb();
			bcb.setId(SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_BCB));
			bb.setContent(bcb.getId());
			mso = new MutSeaObject();
			mso.setHbmSea(bcb, mso.SEA_HBM_INSERT);
			mso.setBlobStream(new ByteArrayInputStream(content.getBytes()),
					"content");
			saveList.add(mso);

			String sql = "update bbssort set s_time=sysdate where id="
					+ topicBb.getSortid();
			mso = new MutSeaObject();
			mso.setSqlSea(sql, mso.SEA_SQL_UPDATE);
			saveList.add(mso);

			HbmOperator.SeaMutDataWithBlob(saveList);
			request.getSession().setAttribute("lsendtime2",
					System.currentTimeMillis() + "");

			BBSMng.modBbsAllInfo(bb.getSortid().toString(), false, bb
					.getUserId().toString(), bb.getReId().toString());

		} catch (Exception e) {
			System.out.println(e.getMessage());
			s = "回复帖子出错，请与管理员联系！";
		}

		return s;

	}
	
	
    //前台用户新增主题帖
	public static String userAddTopic(HttpServletRequest request,
			Userinfo user, String content, String sortid, String title) {
		String s = null;
		Vector saveList = new Vector();
		Bbsuser buser = BBSMng.getBbsuserByHyid(user.getHyid().toString());
  		try {
			if (buser == null) {
				buser = new Bbsuser();
				buser.setCommend(new Long(0));
				buser.setGrade(new Long(0));
				buser.setHyid(user.getHyid());
				buser.setLcname(user.getLcname());
				buser.setMoneys(new Long(0));
				buser.setRegtime(user.getRegtime());
				buser.setResum(new Long(0));
				buser.setStaytime(new Long(0));
				buser.setTopicsnew(new Long(0));
                buser.setMoneynew(new Long(0));
                buser.setDeltopics(new Long(0));
                buser.setResumnew(new Long(0));
                buser.setReplynum(new Long(0));
                buser.setZans(new Long(0));
				buser.setUsername(user.getUsername());
				MutSeaObject mso = new MutSeaObject();
				mso.setHbmSea(buser, mso.SEA_HBM_INSERT);
				saveList.add(mso);
			}
			Bb bb = new Bb();
			bb.setAuthor(user.getLcname());
			bb.setCensor("");
			bb.setCommend(new Integer(0));
			bb.setHits(new Long(0));
			bb.setId(SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_BBS));
			bb.setIscheck("0");
			bb.setOntop(new Integer(0));
			bb.setReCount(new Long(0));
			bb.setReId(new Long(0));
			bb.setSortid(new Long(sortid));
			bb.setStime(new Date(System.currentTimeMillis()));
			bb.setReTime(new Date(System.currentTimeMillis()));
			bb.setTitle(title);
			bb.setUserId(user.getHyid());
			bb.setUserName(user.getUsername());
			bb.setReason("");
			MutSeaObject mso = new MutSeaObject();
			mso.setHbmSea(bb, mso.SEA_HBM_INSERT);
			saveList.add(mso);

			Bcb bcb = new Bcb();
			bcb.setId(SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_BCB));
			bb.setContent(bcb.getId());
			mso = new MutSeaObject();
			mso.setHbmSea(bcb, mso.SEA_HBM_INSERT);
			mso.setBlobStream(new ByteArrayInputStream(content.getBytes()),
					"content");
			saveList.add(mso);
  			HbmOperator.SeaMutDataWithBlob(saveList);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			s = "发表帖子出错，请与管理员联系！";
		}
		return s;
	}



	public static String getGradeDes(Bbsuser buser) {
		if (buser == null || buser.getMoneys() == null)
			return BBSMng.gradeDes[0];
		if (buser.getMoneys().longValue() <= 0)
			return BBSMng.gradeDes[0];
		for (int i = 0; i < BBSMng.gradeMongy.length; i++) {
			if (buser.getMoneys().longValue() >= BBSMng.gradeMongy[i]) {
				int temp = i + 1;
				if (temp < BBSMng.gradeMongy.length
						&& buser.getMoneys().longValue() < BBSMng.gradeMongy[temp])
					return BBSMng.gradeDes[i];
			}
		}
		return BBSMng.gradeDes[BBSMng.gradeDes.length - 1];
	}

	public static String getGradeDes(long money) {
		if (money <= 0)
			return BBSMng.gradeDes[0];
		for (int i = 0; i < BBSMng.gradeMongy.length; i++) {
			if (money >= BBSMng.gradeMongy[i]) {
				int temp = i + 1;
				if (temp < BBSMng.gradeMongy.length
						&& money < BBSMng.gradeMongy[temp])
					return BBSMng.gradeDes[i];
			}
		}
		return BBSMng.gradeDes[BBSMng.gradeDes.length - 1];

	}

	public static String modBbsuser(HttpServletRequest request, Bbsuser buser) {
		String s = null;
		try {
			BeanInitializer.updateBean(buser, request);
			HbmOperator.update(buser);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			s = "修改等级信息出错！";
		}
		return s;
	}

	public static Bbsuser getBbsuserByHyid(String hyid) {
		String sql = "from Bbsuser  where  hyid=" + hyid;
		List list = HbmOperator.list(sql);
		if (list.size() > 0)
			return (Bbsuser) list.get(0);
		return null;
	}

	public static List getAllSort() {
		String hsql = "from Bbssort as o order by o.orders asc";
		return HbmOperator.list(hsql);
	}

	public static Bbssort getBbssort(String id) {
		List list = HbmOperator.list("from Bbssort as o where o.id=" + id);
		if (list.size() > 0)
			return (Bbssort) list.get(0);
		return null;
	}

	public static String modBbssort(Bbssort bs, HttpServletRequest request) {
		String s = null;
		try {
			BeanInitializer.updateBean(bs, request);
			HbmOperator.update(bs);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			s = "修改分区出错!";
		}
		return s;
	}

	public static String addBbssort(HttpServletRequest request) {
		String s = null;
		try {
			Bbssort bs = (Bbssort) BeanInitializer.initBean(Bbssort.class,
					request);
			bs.setId(SysCommonFunc
					.getSequenceIdForOracle(SysDefine.SEQ_BBSSORT));
			HbmOperator.insert(bs);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			s = "增加分区出错!";
		}
		return s;
	}

	public static Bb getBBSById(String bbsid) {
		List list = HbmOperator.list("from Bb as o where o.id = " + bbsid);
		if (list.size() > 0)
			return (Bb) list.get(0);
		return null;
	}
    //后台审核主题帖
	public static String bbsAcc(HttpServletRequest request, Admininfo admin,
			String bizaction, String del) {
		String s = null;
		String bbsid = request.getParameter("cbbsid");
		Bb b = null;
		if (bbsid != null && bbsid.trim().length() > 0)
			b = BBSMng.getBBSById(bbsid);
		try {
			if (bizaction.equals("01")) { // 通过
  				List checkList = HbmOperator
						.list("from Bb as o where o.reId = 0 and o.title = '"
								+ b.getTitle()
								+ "' and ischeck =1 and o.id <> " + b.getId());
				if (checkList.size() > 0)
					return "已经存在该主题";
				Vector saveList = new Vector();
				MutSeaObject mso = null;

				b.setIscheck("1");
				b.setCensor(admin.getAdminname());
				b.setStime(new Date(System.currentTimeMillis()));
				mso = new MutSeaObject();
				mso.setHbmSea(b, mso.SEA_HBM_UPDATE);
				saveList.add(mso);

				String updatehyinfo ="update bbsuser set moneynew = moneynew + 100,topicsnew = topicsnew + 1 where hyid = "+b.getUserId();
				mso = new MutSeaObject();
				mso.setSqlSea(updatehyinfo, mso.SEA_SQL_UPDATE);
				 saveList.add(mso);
				HbmOperator.SeaMutData(saveList);
 				BBSMng.modBbsAllInfo(b.getSortid().toString(), true, b
						.getUserId().toString(), null);

			} else if (bizaction.equals("02")) // /取消置顶
			{
				HbmOperator.executeSql("update bbs set ontop = 0 where id = "	+ bbsid);
			} else if (bizaction.equals("03")) // 置顶 ，现在改为新帖
			{
				b.setOntop(new Integer(1));
				b.setStime(new Date(System.currentTimeMillis()));
				HbmOperator.update(b);
				String sql = "update bbssort set s_time=sysdate where id="
						+ b.getSortid();
				HbmOperator.executeSql(sql);
			} else if (bizaction.equals("04")) // 取消推荐
			{

				String sql = "update bbs set commend  = 0 where id = " + bbsid;
				HbmOperator.executeSql(sql);
    			BBSMng.modBbsAllInfo(null, false, b.getUserId().toString(),
						null);
			} else if (bizaction.equals("05")) // 推荐
			{
				String sql = "update bbsuser set grade = grade+120 where hyid = "+ b.getUserId();
				b.setCommend(new Integer(1));

				b.setCensor(admin.getAdminname());
				b.setReTime(new Date(System.currentTimeMillis()));
				Commend ncommend =new Commend();
				ncommend.setId(SysCommonFunc.getSequenceIdForOracle("SEQ_COMMEND"));
				ncommend.setBbsid(new Long(bbsid));
				ncommend.setCommend(new Long(1));
				ncommend.setUserid(b.getUserId());
				Vector saveList = new Vector();
				MutSeaObject mso = new MutSeaObject();
				mso.setHbmSea(b, mso.SEA_HBM_UPDATE);
				saveList.add(mso);
				
				mso = new MutSeaObject();
				mso.setHbmSea(ncommend, MutSeaObject.SEA_HBM_INSERT);
				saveList.add(mso);
				
				mso = new MutSeaObject();
				mso.setSqlSea(sql, mso.SEA_SQL_UPDATE);
				saveList.add(mso);
				HbmOperator.SeaMutData(saveList);

				BBSMng.modBbsAllInfo(b.getSortid().toString(), true, b
						.getUserId().toString(), null);

			} else if (bizaction.equals("06")) // /删除
			{
				String[] dbbsid = request.getParameterValues("bbsid");
                if (dbbsid != null) {
					for (int i = 0; i < dbbsid.length; i++) {
						String[] array = dbbsid[i].split(":");
                        Bb bb = (Bb)HbmOperator.select(Bb.class,Long.parseLong(array[0]));
                       if("0".equals(bb.getIscheck())){
                           HbmOperator.delete(bb);
                       }else{
                           String  dsql = "update bbs set re_time=sysdate,ischeck = '2',censor='"
                                    + admin.getAdminname()
                                    + "',reason='"+del+"' where re_id = 0 and  id = " + array[0];
                           HbmOperator.executeSql(dsql);
                        }
						BBSMng.modBbsAllInfoDel(array[2], false, array[1], null);
                        Bbsuser buser = BBSMng.getBbsuserByHyid(bb.getUserId().toString());
                        if(buser.getDeltopics() == null){
                            buser.setDeltopics(new Long(1));
                        }else{
                            buser.setDeltopics(buser.getDeltopics()+1);
                        }
                        HbmOperator.update(buser);
                    }
				}

			} else if (bizaction.equals("07")) // /恢复
			{
 				String sql = "update bbs set ischeck = '0' where re_id = 0 and  id = "
						+ bbsid;
				HbmOperator.executeSql(sql);
				BBSMng.modBbsAllInfo(bbsid, false, null, null);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			s = "处理出错！";
		}

		return s;
	}

	public static String delReTopic(HttpServletRequest request,String del) {
		Admininfo adminLoginInfo = (Admininfo) request.getSession()
				.getAttribute(SysDefine.SESSION_ADMIN_LOGIN_NAME);
		String s = null;
		try {

			String[] dbbsid = request.getParameterValues("bbsid");
			if (dbbsid != null) {
				for (int i = 0; i < dbbsid.length; i++) {
					String[] array = dbbsid[i].split(":");
					String sql = "update bbs set re_time=sysdate,ischeck='1',censor='"
							+ adminLoginInfo.getAdminname()
							+ "',reason='"+del+"' where id="
							+ array[0];
					HbmOperator.executeSql(sql);
                    String sql2 = "update bbsuser set delresum=delresum+1 , moneynew =moneynew-10 where hyid="
                            + array[1];
                    HbmOperator.executeSql(sql2);
			//		BBSMng.modBbsAllInfo(null, false, array[1], array[3]);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			s = "删除回复出错！";
		}

		return s;

	}

	public static String modTopic(Bb b, HttpServletRequest request) {
		String s = null;
		try {
			Bcb bcb = null;
			if (b.getContent() != null)
				bcb = BCBMng.getBcbFromid(b.getContent().toString());
			String content = SysCommonFunc.getStrParameter(request, "content");
			String title = SysCommonFunc.getStrParameter(request, "title");
			String sortid = SysCommonFunc.getStrParameter(request, "sortid");

			Date tempd = new Date(System.currentTimeMillis());
			//b.setStime(tempd);
			b.setReTime(tempd);
			b.setTitle(title);
			b.setSortid(new Long(sortid));

			Vector saveList = new Vector();
			MutSeaObject mso = new MutSeaObject();
			mso.setHbmSea(b, mso.SEA_HBM_UPDATE);
			saveList.add(mso);
			if (bcb != null) {
				mso = new MutSeaObject();
				mso.setHbmSea(bcb, mso.SEA_HBM_UPDATE);
				mso.setBlobStream(new ByteArrayInputStream(content.getBytes()),
						"content");
				saveList.add(mso);
			} else {
				bcb = new Bcb();
				bcb.setId(SysCommonFunc
						.getSequenceIdForOracle(SysDefine.SEQ_BCB));
				b.setContent(bcb.getId());
				mso = new MutSeaObject();
				mso.setHbmSea(bcb, mso.SEA_HBM_INSERT);
				mso.setBlobStream(new ByteArrayInputStream(content.getBytes()),
						"content");
				saveList.add(mso);
			}

			String sql1 = "update bbssort set s_time=sysdate where id = "
					+ sortid;
			mso = new MutSeaObject();
			mso.setSqlSea(sql1, mso.SEA_SQL_UPDATE);
			saveList.add(mso);

			HbmOperator.SeaMutDataWithBlob(saveList);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			s = "修改主题出错！";
		}
		return s;
	}

	public static String modReTopic(Bb b, HttpServletRequest request) {
		String s = null;
		try {
			Bcb bcb = BCBMng.getBcbFromid(b.getContent().toString());
			String content = SysCommonFunc.getStrParameter(request, "content");

			b.setStime(new Date(System.currentTimeMillis()));
			Vector saveList = new Vector();
			MutSeaObject mso = new MutSeaObject();
			mso.setHbmSea(b, mso.SEA_HBM_UPDATE);
			saveList.add(mso);
			if (bcb != null) {
				mso = new MutSeaObject();
				mso.setHbmSea(bcb, mso.SEA_HBM_UPDATE);
				mso.setBlobStream(new ByteArrayInputStream(content.getBytes()),
						"content");
				saveList.add(mso);
			} else {
				bcb = new Bcb();
				bcb.setId(SysCommonFunc
						.getSequenceIdForOracle(SysDefine.SEQ_BCB));
				b.setContent(bcb.getId());
				mso = new MutSeaObject();
				mso.setHbmSea(bcb, mso.SEA_HBM_INSERT);
				mso.setBlobStream(new ByteArrayInputStream(content.getBytes()),
						"content");
				saveList.add(mso);
			}

			HbmOperator.SeaMutDataWithBlob(saveList);



		} catch (Exception e) {
			System.out.println(e.getMessage());
			s = "修改回复出错！";
		}
		return s;
	}
    //后台管理新增论坛主题帖
	public static String addTopTopic(HttpServletRequest request) {
		String s = null;
		String title = SysCommonFunc.getStrParameter(request, "title");
		String content = SysCommonFunc.getStrParameter(request, "content");
		String sortid = SysCommonFunc.getStrParameter(request, "sortid");
		String author = "论坛管理员";
		long hyid = 749693;
		String username = "mahe88";

		Bb b = new Bb();
		b.setAuthor(author);
		b.setCensor("");
		b.setCommend(new Integer(0));
		b.setHits(new Long(0));
		b.setId(SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_BBS));
		b.setIscheck("0");
		b.setOntop(new Integer(0));
		b.setReCount(new Long(0));
		b.setReId(new Long(0));
		b.setSortid(new Long(sortid));
		b.setStime(new Date(System.currentTimeMillis()));
		b.setTitle(title);
		b.setUserId(new Long(hyid));
		b.setUserName(username);

		Bcb bcb = new Bcb();
		bcb.setId(SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_BCB));
		b.setContent(bcb.getId());
		try {
			Vector saveList = new Vector();
			MutSeaObject mso = new MutSeaObject();
			mso.setHbmSea(b, mso.SEA_HBM_INSERT);
			saveList.add(mso);

			mso = new MutSeaObject();
			mso.setHbmSea(bcb, mso.SEA_HBM_INSERT);
			mso.setBlobStream(new ByteArrayInputStream(content.getBytes()),
					"content");
			saveList.add(mso);

			HbmOperator.SeaMutDataWithBlob(saveList);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			s = "发表帖子出错";
		}
		return s;
	}

	public static void modBbsAllInfo(String sortid, boolean modsorttime,
			String bbsuserid, String bbsid) {
		try {
			if (sortid != null && sortid.trim().length() > 0) {
				sortid = sortid.trim();
				String sql = "update BBSsort "
						+ "set topics = (select count(id) " + " from bbs "
						+ " where re_id = 0 " + " and ischeck = '1'"
						+ " and sortid = " + sortid + ")";
				if (modsorttime)
					sql = sql + ",s_time=sysdate";
				sql = sql + " where id = " + sortid;
				HbmOperator.executeSql(sql);
			}

//			if (bbsuserid != null && bbsuserid.trim().length() > 0) {
//				bbsuserid = bbsuserid.trim();
//				String sql = "" + "update bbsuser "
//						+ "set topics  = (select count(id) "
//						+ "              from bbs "
//						+ "               where re_id = 0 "
//						+ "                  and ischeck = '1'  "
//						+ "                  and user_id = " + bbsuserid + "),"
//						+ "    commend = (select count(id)"
//						+ "                 from bbs"
//						+ "                where re_id = 0"
//						+ "                  "
//						+ "                  and (commend = 1 or commend= -2)"
//						+ "                  and user_id = " + bbsuserid + "),"
//						+ "    resum = (select count(id)"
//						+ "                 from bbs"
//						+ "                where ischeck='0' and re_id >0"
//						+ "                  and user_id = " + bbsuserid + ")"
//						+ "    where hyid = " + bbsuserid;
//				HbmOperator.executeSql(sql);
//				sql = ""
//						+ "update bbsuser "
//						+ "   set moneys = topics * 30 + resum * 6 + grade -nvl((select count(*) from bbs where  ischeck='1' and re_id >0 and user_id="
//						+ bbsuserid + "),0)*6" + " where hyid = " + bbsuserid;
//  			}

			if (bbsid != null && bbsid.trim().length() > 0) {
				bbsid = bbsid.trim();
				String sql = "update BBS set re_count=(select count(*) from bbs where ischeck='0' and re_id="
						+ bbsid + ") where id  =" + bbsid;
				HbmOperator.executeSql(sql);

			}

		} catch (Exception e) {
			System.out.println("统一设置BBS信息出错" + e.getMessage());
		}
	}
	
	public static void modBbsAllInfoDel(String sortid, boolean modsorttime,
			String bbsuserid, String bbsid) {
		try {
			if (sortid != null && sortid.trim().length() > 0) {
				sortid = sortid.trim();
				String sql = "update BBSsort "
						+ "set topics = (select count(id) " + " from bbs "
						+ " where re_id = 0 " + " and ischeck = '1'"
						+ " and sortid = " + sortid + ")";
				if (modsorttime)
					sql = sql + ",s_time=sysdate";
				sql = sql + " where id = " + sortid;
				HbmOperator.executeSql(sql);
			}

//			if (bbsuserid != null && bbsuserid.trim().length() > 0) {
//				bbsuserid = bbsuserid.trim();
//				String sql = "" + "update bbsuser "
//						+ "set topics  = (select count(id) "
//						+ "              from bbs "
//						+ "               where re_id = 0 "
//						+ "                  and ischeck = '1'  "
//						+ "                  and user_id = " + bbsuserid + "),"
//						+ "    commend = (select count(id)"
//						+ "                 from bbs"
//						+ "                where re_id = 0"
//						+ "                  "
//						+ "                  and (commend = 1 or commend= -2)"
//						+ "                  and user_id = " + bbsuserid + "),"
//						+ "    resum = (select count(id)"
//						+ "                 from bbs"
//						+ "                where ischeck='0' and re_id >0"
//						+ "                  and user_id = " + bbsuserid + ")"
//						+ "    where hyid = " + bbsuserid;
//				HbmOperator.executeSql(sql);
//			}

			if (bbsid != null && bbsid.trim().length() > 0) {
				bbsid = bbsid.trim();
				String sql = "update BBS set re_count=(select count(*) from bbs where ischeck='0' and re_id="
						+ bbsid + ") where id  =" + bbsid;
				HbmOperator.executeSql(sql);

			}

		} catch (Exception e) {
			System.out.println("统一设置BBS信息出错" + e.getMessage());
		}
	}


    public static String userAddZan(HttpServletRequest request,
                                    String bbsReId,String bbsid, String commendId, String userId) {
        if(userId.equals(commendId)){
            return "您不能给自己点赞！";
        }
        String add_zan_sql = "";
        String modify_user_sql = "";
        Bbsuser buser = BBSMng.getBbsuserByHyid(commendId.toString());

        try {
            if(buser.getZans() == null){
                buser.setZans(new Long(1));
                }else{
                buser.setZans(new Long(buser.getZans().longValue() + 1));
            }
            if(buser.getMoneynew() == null){
                buser.setMoneynew(new Long(2));
            }else{
                buser.setMoneynew(new Long(buser.getMoneynew().longValue() + 2));
            }
            HbmOperator.update(buser);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        String sql = "select b.id,b.user_id,b.re_id,b.commend,b.zan,b.zan_time from bbs b where id = "+ bbsReId;
        QueryResult qr = QueryRecord.queryByDynaResultSet(sql, 1, 1);
        String user_zan = "select u.hyid,u.zan_date,u.zan_ids,u.zan_user_id from userother u where hyid = "+ userId;
        QueryResult zan_qr = QueryRecord.queryByDynaResultSet(user_zan, 1, 1);
        if(qr!=null && qr.resultList!=null && zan_qr!=null && zan_qr.resultList!=null){
            DynaBean comment = (DynaBean)qr.resultList.get(0);
            DynaBean user_other = (DynaBean)zan_qr.resultList.get(0);
            Date today = new Date();
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            String today_str = sf.format(today);
            String cun_date_str = "";
            if(user_other.get("zan_date")!=null){
                Date cun_date =  (Date)user_other.get("zan_date");
                cun_date_str = sf.format(cun_date);
            }
            if(user_other.get("zan_date")==null || !cun_date_str.equals(today_str)){
                modify_user_sql = "update userother set zan_date = to_date('"+today_str+"','yyyy-MM-dd'),zan_ids='"+bbsReId+"',zan_user_id='"+commendId+"' where hyid = "+userId;
            }else{
                String zan_ids_str = "";
                String zan_userids_str = "";
                String old_zan_ids = user_other.get("zan_ids").toString();
                String old_zan_userids = user_other.get("zan_user_id").toString();
                String[] old_zan_ids_array = old_zan_ids.split("_");
                String[] old_zan_userids_array = old_zan_userids.split("_");
                if(old_zan_ids_array.length>=20){
                    return "您今天已多次点赞，请明天再点！";
                }
                if(old_zan_ids.contains(bbsReId)){
                     return "你已经对该评论点赞！";
                }
                int count = 0;
               if(old_zan_userids_array!=null && old_zan_userids_array.length>0){
                 for(int i=0;i<old_zan_userids_array.length;i++){
                   if(commendId.equals(old_zan_userids_array[i])){
                       count++;
                   }
                }
                if(count>=3){
                    return "你已经对该好友3次点赞！";
                }
                   }
                if(user_other.get("zan_date").toString().indexOf(today_str)>=0){
                    zan_ids_str = old_zan_ids+"_"+bbsReId;
                    zan_userids_str = old_zan_userids+"_"+commendId;
                }else{
                    zan_ids_str = bbsReId;
                    zan_userids_str = commendId;
                }
                modify_user_sql = "update userother set zan_date = to_date('"+today_str+"','yyyy-MM-dd'),zan_ids='"+zan_ids_str+"',zan_user_id='"+zan_userids_str+"' where hyid = "+userId;
            }

            if(comment.get("zan")==null){
                add_zan_sql = "update BBS set zan=1 where id  =" + bbsReId;
            }else{
                add_zan_sql = "update BBS set zan=zan+1 where id  =" + bbsReId;
            }

        }
        try {
            HbmOperator.executeSql(add_zan_sql);
            HbmOperator.executeSql(modify_user_sql);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


        return "ok";
    }
}

// 增加减少金币
// sub modmoney(x,userid)
// Exstr="update bbsuser set moneys=moneys+"&x&" where userid=" & userid
// conn.Execute Exstr
// end sub
//
// 会员等级
// sub modgrade(userid)
// set Checkrs=server.CreateObject("adodb.recordset")
// Checksql="select moneys from bbsuser where userid="&userid
// Checkrs.open Checksql,conn,1,1
// moneys=checkrs("moneys")
// checkrs.close
// set Checkrs=nothing
// if moneys<10 then
// Exstr="update bbsuser set grade=1 where userid=" & userid
// elseif moneys>10 and moneys<100 then
// Exstr="update bbsuser set grade=2 where userid=" & userid
// elseif moneys>100 and moneys<300 then
// Exstr="update bbsuser set grade=3 where userid=" & userid
// elseif moneys>300 and moneys<1000 then
// Exstr="update bbsuser set grade=4 where userid=" & userid
// elseif moneys>1000 and moneys<2000 then
// Exstr="update bbsuser set grade=5 where userid=" & userid
// elseif moneys>2000 and moneys<4000 then
// Exstr="update bbsuser set grade=6 where userid=" & userid
// elseif moneys>4000 and moneys<7000 then
// Exstr="update bbsuser set grade=7 where userid=" & userid
// elseif moneys>7000 and moneys<10000 then
// Exstr="update bbsuser set grade=8 where userid=" & userid
// elseif moneys>10000 then
// Exstr="update bbsuser set grade=9 where userid=" & userid
// end if
// conn.Execute Exstr
// 'response.write
// "<script language='javascript'>alert('恭喜您,已升为论坛等级。');</scrip>"
// end sub
//
// 显示等级
// function showgrade(grade)
// if grade<>"" then
// showgrade=""
// if grade=1 or grade=0 then
// showgrade="新手"
// else
// for ii=1 to grade-1
// showgrade=showgrade&"★"
// next
// end if
// end if
// end function
//

// sub modsum(x,zd,userid)
// Exstr="update bbsuser set "&zd&"="&zd&"+"&x&"  where userid=" & userid
// conn.Execute Exstr
// end sub

