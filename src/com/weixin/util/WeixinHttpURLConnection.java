package com.weixin.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class WeixinHttpURLConnection {

	 /**
     * �ӿڵ��� GET
     */
    public static String httpURLConectionGET(String connectUrl) {
    	 StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(connectUrl);    // ���ַ���ת��ΪURL�����ַ
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();// ������
            connection.connect();// ���ӻỰ
            // ��ȡ������
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
           
            while ((line = br.readLine()) != null) {// ѭ����ȡ��
                sb.append(line);
            }
            br.close();// �ر���
            connection.disconnect();// �Ͽ�����
            System.out.println("StringBuilder��"+sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ʧ��!");
        }
        return sb.toString();
        
    }
    
    

    /**
     * �ӿڵ���  POST
     */
    public static String httpURLConnectionPOST (String connectUrl) {
    	 StringBuilder sb = new StringBuilder(); // �����洢��Ӧ����
        try {
            URL url = new URL(connectUrl);
            
            // ��url �� open�������ص�urlConnection  ����ǿתΪHttpURLConnection����  (��ʶһ��url�����õ�Զ�̶�������)
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();// ��ʱcnnectionֻ��Ϊһ�����Ӷ���,��������
            
            // �������������Ϊtrue,Ĭ��false (post �����������ķ�ʽ��ʽ�Ĵ��ݲ���)
            connection.setDoOutput(true);
            
            // ��������������Ϊtrue
            connection.setDoInput(true);
            
            // ��������ʽΪpost
            connection.setRequestMethod("POST");
            
            // post���󻺴���Ϊfalse
            connection.setUseCaches(false);
            
            // ���ø�HttpURLConnectionʵ���Ƿ��Զ�ִ���ض���
            connection.setInstanceFollowRedirects(true);
            
            // ��������ͷ����ĸ������� (����Ϊ�������ݵ�����,����Ϊ����urlEncoded�������from����)
            // application/x-javascript text/xml->xml���� application/x-javascript->json���� application/x-www-form-urlencoded->������
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            
            // �������� (����δ��ʼ,ֱ��connection.getInputStream()��������ʱ�ŷ���,���ϸ��������������ڴ˷���֮ǰ����)
            connection.connect();
            
            // �������������,�����������������Я���Ĳ���,(�������Ϊ?���������)
            DataOutputStream dataout = new DataOutputStream(connection.getOutputStream());
//            String parm = "storeId=" + URLEncoder.encode("32", "utf-8"); //URLEncoder.encode()����  Ϊ�ַ������б���
            
            // ���������������
//            dataout.writeBytes(parm);
            
            // �����ɺ�ˢ�²��ر���
            dataout.flush();
            dataout.close(); // ��Ҫ���׺��Բ��� (�ر���,�м�!) 
            
            System.out.println(connection.getResponseCode());
            
            // ���ӷ�������,�����������Ӧ  (�����ӻ�ȡ������������װΪbufferedReader)
            BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream())); 
            String line;
           
            
            // ѭ����ȡ��,��������β��
            while ((line = bf.readLine()) != null) {
                sb.append(bf.readLine());
            }
            bf.close();    // ��Ҫ���׺��Բ��� (�ر���,�м�!) 
            connection.disconnect(); // ��������
            System.out.println("httpURLConnectionPOST"+sb.toString());
    
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
