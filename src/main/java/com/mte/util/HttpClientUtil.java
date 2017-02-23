package com.mte.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.auth.params.AuthPNames;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.AuthPolicy;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {

	public static void passNTLM() throws ClientProtocolException, IOException{
		DefaultHttpClient hclient = new DefaultHttpClient(); 
		NTCredentials creds = new NTCredentials("hwpmgr10@us.ibm.com", "tenp1208", "BasicRegistry", "etpb-dev.w3ibm.mybluemix.net"); 
		System.out.println(creds.getUserPrincipal().getName());  
		System.out.println(creds.getPassword());
		((AbstractHttpClient) hclient).getCredentialsProvider().setCredentials(AuthScope.ANY, creds); 
		HttpHost target = new HttpHost("etpb-dev.w3ibm.mybluemix.net", 80, "http"); 
		HttpContext localContext = new BasicHttpContext(); 
		HttpGet httpget = new HttpGet("/ntlm-protected/info"); 
		HttpResponse response = hclient.execute(target, httpget, localContext);
		HttpEntity entity1 = response.getEntity();
		
		if (entity1 != null) { 
			entity1.consumeContent(); 
			} 
	}
	

	
	public static void main(String[] args) throws ClientProtocolException, IOException{
        HttpClient httpclient = new DefaultHttpClient();
        List<String> authpref = new ArrayList<String>();
        authpref.add(AuthPolicy.NTLM);
        httpclient.getParams().setParameter(AuthPNames.TARGET_AUTH_PREF,    authpref);
        //�����ֱ�Ϊ�û��������롢������url������������
        NTCredentials creds = new NTCredentials("hwpmgr10@us.ibm.com", "tenp1208", "etpb-dev.w3ibm.mybluemix.net", null); 
        ((AbstractHttpClient) httpclient).getCredentialsProvider().setCredentials(AuthScope.ANY, creds);

        
        //����Ҫ���ӵ�Ŀ�����ơ��˿�
        HttpHost target = new HttpHost("etpb-dev.w3ibm.mybluemix.net", 80, "http");

        // Make sure the same context is used to execute logically related requests
        HttpContext localContext = new BasicHttpContext();

        // Execute a cheap method first. This will trigger NTLM authentication
        HttpGet httpget = new HttpGet("/TalentPlayBookService/rest/tpservicedata");
        //������Ϊ�������һЩheader��Ϣ����αװ�����
//       httpget.addHeader("Accept-Language", "zh-Hans-CN,zh-Hans;q=0.8,en-US;q=0.5,en;q=0.3");
 //       httpget.addHeader("Accept","image/jpeg, application/x-ms-application, image/gif, application/xaml+xml, image/pjpeg, application/x-ms-xbap, */*");
 //       httpget.addHeader("DNT","1");
 //       httpget.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.2; WOW64; Trident/6.0; .NET4.0E; .NET4.0C; .NET CLR 3.5.30729; .NET CLR 2.0.50727; .NET CLR 3.0.30729)");
 //       httpget.addHeader("Accept-Encoding","gzip, deflate");
  //      System.out.println("Start"); 

            HttpResponse response = httpclient.execute(target,httpget,localContext);
            System.out.println(response);
            HttpEntity entity = response.getEntity();
            System.out.println(EntityUtils.toString(entity));

	}
}
