import com.web.common.*;
import com.common.*;
import javax.servlet.http.HttpServletRequest;

import com.web.obj.*;
import com.web.common.beanutil.*;
import hibernate.db.*;

import java.security.MessageDigest;
import java.util.*;
import com.web.bean.QueryRecord;
import com.lover.mng.*;
import com.web.servlet.InitEnvironment;
import java.io.*;
import org.apache.commons.beanutils.*;
import java.util.regex.*;

public class test {
  public test() {


  }

  public static void main(String args[]) throws Exception
  {
//    String start = "<div id=\"yfi_quote_summary_data\">";
//    String end = "1y Target Est:</th><td class=\"yfnc_tabledata1\">";
//    String src = "";
//    FileReader fr = new FileReader("d:/aboutus.jsp");
//    BufferedReader br = new BufferedReader(fr);
//    String temp = br.readLine();
//    while(temp != null)
//    {
//      src = src + temp;
//      temp = br.readLine();
//    }
//
//    int startp = src.indexOf(start);
//    int endp   = src.indexOf(end);
//    String s = src.substring(startp+start.length(),endp+end.length()+100);
//    String tempstart = "<table id=\"table2\">";
//    int tempp = s.indexOf(tempstart);
//    s = s.substring(0,tempp);
//
//    System.out.println("------srat");
//    System.out.println(s);
//      File file = new File("d://mapping.xml");
//      File desfile = new File("d://dd/mapping.xml");
//      //desfile.mkdirs();
//      file.renameTo(desfile);
//      String s = file.getPath();
//      System.out.println(s);
//
//      //file.ren

//    String s = "www.fdsaf.com/1213131.html";
//    Pattern pattern = Pattern.compile(".*/[0-9]*[.]html");
//    boolean b =  pattern.matcher(s).matches();
//    pattern.matcher("");
//int bcc = (~((byte) (0x07 + 0x10))) + 1;
//   System.out.println(bcc);
//
//   String s = Integer.toHexString(bcc).toUpperCase();
//   System.out.println(s);

   //09 F0
//      Userinfo hyInfo = HYRegMng.getUserinfoByHyid("6731677");
//
//       Date cdate =  DateTools.stringToDate("2010-09-01");
//       boolean  isvery = false;
// if(hyInfo.getRegtime().after(cdate))
//   isvery = true;
//
//    System.out.println(isvery+DateTools.dateToString(cdate,false)+"   "+hyInfo.getUsername());
//      System.out.println(DateTools.dateToString(hyInfo.getRegtime(),false));

//      String desc = "²é¿´£¨äìÏæ³Ô³Å)hyid=6880982";
//
//          int index = desc.indexOf("hyid=");
//    String hyid="";
//
//
//    if(index  > 0)
//    {
//      hyid = desc.substring(index+5);
//      desc = desc.substring(0,index);
//
//    }
     // System.out.println(hyid);

//      HashSet<Integer> hsa = new HashSet<Integer>();
//            HashSet<Integer> hsb = new HashSet<Integer>();
//            Random ra = new Random();
//          hsa.add(ra.nextInt(1000*60));
//
//          hsb.add(ra.nextInt(1000*60*8));
//          Iterator<Integer> ia = hsa.iterator();
//          Iterator<Integer> ib = hsb.iterator();
//
//       System.out.println(ia.next().longValue()/1000);
//       System.out.println(ib.next().longValue()/1000);

//      Random r2 = new Random();
//
//      int sjs=r2.nextInt(30);
//      System.out.println("sjs = " + sjs);


      String erweima ="weixin://wxpay/bizpayurl?";
      String appid = "wxfb0c41fd39d33468";
      String mch_id = "1239362002";

      Random r2 = new Random();
      long sjs = r2.nextInt(100000);
      String nonce_str = sjs+"";

      String product_id = "vip1";



      Date date = new Date();
      long time_second = date.getTime()/1000;
      String time_stamp = time_second+"";

      String stringA = "appid="+appid+"&mch_id="+mch_id+"&nonce_str="+nonce_str +"&product_id="+product_id + "&time_stamp="+time_stamp;

      String stringSignTemp= stringA+"&key=51loverorg2015mahe0051loverorg2015";

      String sign= MD5Util.MD5(stringSignTemp).toUpperCase();
      System.out.println("stringSignTemp = " + stringSignTemp);
      System.out.println("sign = " + sign);

      erweima += stringA+"&sign="+sign;
      System.out.println("erweima = " + erweima);
  }
}
