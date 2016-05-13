package com.web.map;

import hibernate.db.*;

import java.util.*;

import com.web.obj.*;


public class DicMap {
    public DicMap() {
    }

    public static List getDicList(String dictype) {
        String hsql = "from o in class "+Dic.class.getName()+" where o.dictype='" + dictype + "' order by dicid asc";
        return HbmOperator.list(hsql);
    }

    public static Dic getDic(String dicid) {
        Dic dic = null;
        String hsql = "from o in class "+Dic.class.getName()+" where  o.dicid=" + dicid;
        List list = HbmOperator.list(hsql);
        if (list != null && list.size() > 0)
            return (Dic) list.get(0);
        return dic;
    }

    /**
     * 后台插入或更新一条数据
     *
     * @param dic Dic
     * @return Dic
     * @throws Exception
     */
//    public static Dic insertOrUpdateDic(Dic dic) throws Exception {
//        if (dic.getDicid().intValue() > 0)
//            HbmOperator.update(dic);
//        else {
//
//            dic.setDicid(new Integer(SysCommonFunction.getSequenceId(SysParaDefine.WEB_ALL_SEQU_NAME)));
//            HbmOperator.insert(dic);
//        }
//        return dic;
//    }

//    public static void delDic(String did) throws Exception {
//        String hsql = "from o in class "+Dic.class.getName()+" where o.dicid=" + did;
//
//        HbmOperator.delete(hsql);
//    }



//    public static void loadAllDics(){
//        SysParaDefine.DIC_VALUES_HASH=new HashMap();
//        Session session=null;
//        try {
//            session=HbmUtil.getActiveSession();
//            String hql_dictype="from o in class " +Dictype.class.getName();
//            List dicTypeList=session.createQuery(hql_dictype).list();
//            for(int i=0;dicTypeList!=null&&i<dicTypeList.size();i++){
//                Dictype dictype=(Dictype)dicTypeList.get(i);
//                String hql_dic="from o in class "+Dic.class.getName()+" where o.dictype='"+dictype.getTypeid()+"'";
//                List dicList=session.createQuery(hql_dic).list();
//                SysParaDefine.DIC_VALUES_HASH.put(dictype.getTypeid(),dicList);
//            }
//        } catch (Exception e) {
//
//        }finally{
//            HbmUtil.closeSession();
//        }
//    }
}
