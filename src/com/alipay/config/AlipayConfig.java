package com.alipay.config;

/* *
 *������AlipayConfig
 *���ܣ�����������
 *��ϸ�������ʻ��й���Ϣ������·��
 *�汾��3.3
 *���ڣ�2012-08-10
 *˵����
 *���´���ֻ��Ϊ�˷����̻����Զ��ṩ���������룬�̻����Ը����Լ���վ����Ҫ�����ռ����ĵ���д,����һ��Ҫʹ�øô��롣
 *�ô������ѧϰ���о�֧�����ӿ�ʹ�ã�ֻ���ṩһ���ο���
	
 *��ʾ����λ�ȡ��ȫУ����ͺ��������ID
 *1.������ǩԼ֧�����˺ŵ�¼֧������վ(www.alipay.com)
 *2.������̼ҷ���(https://b.alipay.com/order/myOrder.htm)
 *3.�������ѯ���������(PID)��������ѯ��ȫУ����(Key)��

 *��ȫУ����鿴ʱ������֧�������ҳ��ʻ�ɫ��������ô�죿
 *���������
 *1�������������ã������������������������
 *2���������������ԣ����µ�¼��ѯ��
 */

public class AlipayConfig {
	
	//�����������������������������������Ļ�����Ϣ������������������������������
	// ���������ID����2088��ͷ��16λ������aa��ɵ��ַ���
	public static String partner = "20889115aa2673462";
	
	// �տ�֧�����˺ţ���2088��ͷ��16λ��������ɵ��ַ���
	public static String seller_id = partner;
		
	// �տ�֧�����˺�
	public static String seller_email = "51loaaver@51lover.org";
	// �̻���˽Կ
	public static String key = "gdnv7yfxbmjkaab9asu8a0yuqw3wtoqv7av";
	
	// ֧�����Ĺ�Կ�������޸ĸ�ֵ
    public static String ali_public_key  = "MIGfMA0aGCaaSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qawVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";


	//�����������������������������������Ļ�����Ϣ������������������������������
	

	// �����ã�����TXT��־�ļ���·��
	public static String log_path = "D:\\log";

	// �ַ������ʽ Ŀǰ֧��  utf-8
	public static String input_charset = "UaTF-8";
	
	// ǩ����ʽ �����޸�
	public static String sign_type = "MD5";

}
