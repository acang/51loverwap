package com.common;

public class SysDefine {



  /**
   * 字典
   */
  /*//性别 血型  星座 身高 体重 肤色 婚姻情况 文化程度 月薪 住房 性格特征 交友意向 业余安排 省份 地市*/
  public static final String DIC_SEX = "00001";
  public static final String DIC_XX= "00002";
  public static final String DIC_XZ = "00003";
  public static final String DIC_SG = "00004";
  public static final String DIC_TZ = "00005";
  public static final String DIC_FS = "00006";
  public static final String DIC_HYZH = "00007";
  public static final String DIC_WHCD = "00008";
  public static final String DIC_YX = "00009";
  public static final String DIC_ZF = "00010";
  public static final String DIC_XGTZ = "00011";
  public static final String DIC_JYYX = "00012";
  public static final String DIC_YYAP = "00013";
  public static final String DIC_SF = "00014";
  public static final String DIC_DS = "00015";
  public static final String DIC_ZY = "00016";
  public static final String DIC_IP = "00017";
  public static final String DIC_ZZSJ= "00018";
  public static final String DIC_DLSJ= "00019";
  public static final String DIC_LFRS = "00020";




  public static String[] xgtzArray=new String[]{"外向开朗", "积极乐观","风趣幽默","直爽干脆","追求刺激","不拘小节","诚实正直","温柔体贴","成熟稳重","小心谨慎","浪漫多情","多愁善感","循规蹈矩","少言寡语","淡薄名利"};
  public static String[] yyapArray=new String[]{"看 电 视","泡 酒 吧","看　书","上　网","卡 拉OK","做 家 务","喝茶聊天" ,"体育运动","打工赚钱","读书进修" ,"结交朋友"};
  //public static String[] jyyxArray=new String[]{"纯友谊","异性恋人","同性恋人","婚 姻","E夜情","陪伴","其它"};
  public static String[] jyyxArray=new String[]{"纯友谊","亲密关系","婚 姻","陪伴","其它"};



  /**
   * 序列号定义
   */
  ///字典序号
   public static final String SEQ_DIC = "SEQ_DIC";
  ///会员ID 序号
   public static final String SEQ_HYID = "SEQ_HYID";
   //会员文集序列号
   public static final String SEQ_HY_ARTICLE="SEQ_HY_ARTICLE";
   //好友管理
   public static final String SEQ_HYGL="SEQ_HYGL";

   //大文本序列号，字符串
   //public static final String SEQ_BCS="SEQ_BCS";
   public static final String SEQ_BCB="SEQ_BCB";


   //会员秋波
   public static final String SEQ_QIUBO="SEQ_QIUBO";

   //会员留言
   public static final String SEQ_HYLY="SEQ_HYLY";

   ///大图标有情连接
   public static final String SEQ_FRIEND="SEQ_FRIEND";

   //SEQ_FRIEND_SITE
   public static final String SEQ_FRIEND_SITE="SEQ_FRIEND_SITE";

   public static final String SEQ_WTJD="SEQ_WTJD";
   public static final String SEQ_KFZX="SEQ_KFZX";
   public static final String SEQ_ADMININFO="SEQ_ADMININFO";
   public static final String SEQ_PRODUCT="SEQ_PRODUCT";
   public static final String SEQ_VIPRECORD = "SEQ_VIPRECORD";
   public static final String SEQ_CARTID = "SEQ_CARTID";
   public static final String SEQ_SHAREID = "SEQ_SHAREID";
   public static final String SEQ_READID = "SEQ_READID";
   public static final String SEQ_BBSSORT = "SEQ_BBSSORT";
   public static final String SEQ_BBS     = "SEQ_BBS";
   public static final String SEQ_IPLOG   = "SEQ_IPLOG";
   
 //信息发送记录
   public static final String SEQ_USERINFOSENDREPLY="SEQ_USERINFOSENDREPLY";
   
   public static final String SEQ_USERVISIT="SEQ_USERVISIT";


/// sessionname 定义
  public static final String DIC_TYPE_NAME = "dtype";
  public static final String DIC_ID_NAME   = "dicid";


  public static final String SESSION_LOGINNAME="LOGINUSER";
  public static final String SESSION_LOGINNAME_OTHER="LOGINUSEROTHER";
  public static final String SESSION_ADMIN_LOGIN_NAME = "ADMINLOGIN";


  ///系统常量定义
  //会员状态
  public static final int SYSTEM_HYSET_HYZT_WAIT=3; //等待中
  public static final int SYSTEM_HYSET_HYZT_NOT_WAIT=6; //暂勿打扰


  //资料开放类型
  public static final int SYSTEM_HYSET_OPEN_ALL=3; //所有人开放
  public static final int SYSTEM_HYSET_OPEN_REG=6; //注册会员
  public static final int SYSTEM_HYSET_OPEN_VIP=9; //VIP会员
  public static final int SYSTEM_HYSET_OPEN_FRIEND=12; //好友开放

  ///好友管理状态定义
  public static final int SYSTEM_HYGL_SQ=4; //申请
  public static final int SYSTEM_HYGL_FRIEND=8; //申请
  public static final int SYSTEM_HYGL_BLACK=12; //申请

  //用户类型
  public static final int SYSTEM_HY_TYPE_NOR=5;
  public static final int SYSTEM_HY_TYPE_vip=30;
  public static final int SYSTEM_HY_TYPE_nvip=25;

  ///img 0 没有照片  1 有照片  isdel 0 正常 1 是删除

  //消费高收费地区、收费标准
  public static final String[] tsdqArray=new String[]{"北京","上海","广东","山东","江苏","浙江","香港","澳门","台湾","国外"};
  public static final double tsdqzk=0.7;
  public static final double ybdqzk=0.5;


  //环境常量定义
  public static final String ENV_WEB_PATH="webPath";
  public static final String ENV_ANNEXPATH_PATH="annexpath";
  
  public SysDefine() {
  }

}
