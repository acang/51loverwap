package com.common;

import java.io.BufferedReader;
import java.io.FileReader;

public class ReadText {
	
	public static void main(String[] args) {
		String s;   
        s=calculate();
        String l1 = s.substring(0, 12);
        String l2 = s.substring(16,30);
        //String m = s.substring(15,30);
        //String str = String.format("00d", l);
        String k[] = l1.split("\\.");
        String k2[] = l2.split("\\.");
        String s1 = "";
        String s2="";
        String s3="";
        String s4="";
        String s5="";
        String s6="";
        String s7="";
        String s8="";
        String newIp ="";
        String newIp1="";
        for (int i = 0; i < k.length; i++) {
        	System.out.println(k[i]);
        	s1 = k[0];
        	s2 = k[1];
        	s3 = k[2];
        	s4 = k[3];
			if(s1.trim().length()==1){
				
				 s1 = "00"+s1;
			}else if (s1.trim().length()==2){
				 s1 = "0" +s1 ;
			}else {
				 s1 = ""+s1;
			}
			if(s2.trim().length()==1){
				
				 s2 = "00"+s2;
			}else if (s2.trim().length()==2){
				 s2 = "0" +s2 ;
			}else {
				 s2 = ""+s2;
			}
			if(s3.trim().length()==1){
				
				 s3 = "00"+s3;
			}else if (s3.trim().length()==2){
				 s3 = "0" +s3 ;
			}else {
				 s3 = ""+s3;
			}
			if(s4.trim().length()==1){
				
				 s4 = "00"+s4;
			}else if (s4.trim().length()==2){
				 s4 = "0" +s4 ;
			}else {
				 s4 = ""+s4;
			}
			newIp = s1+"."+s2+"."+s3+"."+s4;
		}
        System.out.println(newIp);
        
        for (int j = 0; j < k2.length; j++) {
        	System.out.println(k2[j].trim());
        	s5 = k2[0];
        	s6 = k2[1];
        	s7 = k2[2];
        	s8 = k2[3];
			if(s5.trim().length()==1){
				
				 s5 = "00"+s5;
			}else if (s5.trim().length()==2){
				 s5 = "0" +s5 ;
			}else {
				 s5 = ""+s5;
			}
			if(s6.trim().length()==1){
				
				 s6 = "00"+s6;
			}else if (s6.trim().length()==2){
				 s6 = "0" +s6 ;
			}else {
				 s6 = ""+s6;
			}
			if(s7.trim().length()==1){
				
				 s7 = "00"+s7;
			}else if (s7.trim().length()==2){
				 s7 = "0" +s7 ;
			}else {
				 s7 = ""+s7;
			}
			if(s8.trim().length()==1){
				
				 s8 = "00"+s8;
			}else if (s8.trim().length()==2){
				 s8 = "0" +s8 ;
			}else {
				 s8 = ""+s8;
			}
			newIp1 = s5+"."+s6+"."+s7+"."+s8;
		}
        System.out.println(newIp1);

	}
	
	static String calculate()   
    {   
        StringBuffer sb=new StringBuffer("");   
        try{   
            FileReader reader = new FileReader("D:\\jjj.txt");   
            BufferedReader br = new BufferedReader(reader);   
            String s = null;   
            while((s = br.readLine()) != null) {   
                sb.append(s+'\n');   
            }   
            br.close();   
            reader.close();   
       }catch(Exception e){   
            e.printStackTrace();   
       }   
        return sb.toString();   
    }   

}
