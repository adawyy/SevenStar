package com.mte.util;

import java.io.*;
import java.net.Authenticator;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/**
 * Project :  mtesense
 * Created :  java
 * Date    :  3/24/15
 */
public class HttpUtils {

    private URL url = null;
    private HttpURLConnection connection = null;

    public BufferedReader sendHttpGetReq(String urlStr, String reqParams,
                                         String cookie, String writerCharset) {

        if (urlStr.startsWith("http://")) {
            try {

                // Send data
                if (reqParams != null && reqParams.length() > 0) {
                    urlStr += "?"
                            + new String(reqParams.getBytes("ISO-8859-1"),
                            writerCharset);
                }

                url = new URL(urlStr);
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setRequestMethod("GET");
                if (cookie != null && cookie.length() > 0) {
                    connection.addRequestProperty("Cookie", cookie);
                }
                connection.setUseCaches(false);
                connection.connect();

                // Get the response
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(),
                                "ISO-8859-1"));
                return reader;
            } catch (Exception e) {
               e.printStackTrace();
            }
        }
        return null;
    }

    public BufferedReader sendHttpPostReq(String urlStr, String reqParams,
                                          String writerCharset) {

        try {
            url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "text/xml");
            connection.setRequestProperty("Connection", "close");
            connection.setUseCaches(false);
            connection.connect();

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                    connection.getOutputStream(), writerCharset));
            out.write(reqParams);
            out.flush();
            out.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), "ISO-8859-1"));
            return reader;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getReqParamsNoCharset(String fileName) {
        String reqParams = "";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    this.getClass().getClassLoader()
                            .getResourceAsStream(fileName)));
            reqParams = this.getReqDataNoCharset(reader);
            reader.close();
            return reqParams;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reqParams;
    }

    public String getReqParamsByCharset(String fileName, String readerCharset) {
        String reqParams = "";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    this.getClass().getClassLoader()
                            .getResourceAsStream(fileName)));
            reqParams = this.getReqDataByCharset(reader, readerCharset);
            reader.close();
            return reqParams;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return reqParams;
    }

    public String getReqDataNoCharset(BufferedReader reader) {
        StringBuffer buffer = new StringBuffer();
        String line = "";
        try {
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
                buffer.append("\n");
            }
            line = new String(buffer.toString().getBytes("ISO-8859-1"));
            return line;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return line;
    }

    public String getReqDataByCharset(BufferedReader reader,
                                      String readerCharset) {
        StringBuffer buffer = new StringBuffer();
        String line = "";
        try {
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
                buffer.append("\n");
            }
            line = new String(buffer.toString().getBytes("ISO-8859-1"),
                    readerCharset);
            return line;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return line;
    }

    public String getRespDataNoCharset(BufferedReader reader) {
        StringBuffer buffer = new StringBuffer();
        String line = "";
        try {
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
                buffer.append("\n");
            }
            line = buffer.toString();
            return line;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return line;
    }

    public String getRespDataByCharset(BufferedReader reader,
                                       String readerCharset) {
        StringBuffer buffer = new StringBuffer();
        String line = "";
        try {
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
                buffer.append("\n");
            }
            line = new String(buffer.toString().getBytes("ISO-8859-1"),
                    readerCharset);
            return line;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return line;
    }

    public void closed(BufferedReader reader) {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public String get_node_text(String respContent, String nodeName) {

        String dest_string = "<" + nodeName;
        int start = respContent.indexOf(dest_string);
        // System.out.println("start=" + start);
        if (-1 == start)
            return "";
        start = respContent.indexOf('>', start + 1);
        // System.out.println("start=" + start);
        if (-1 == start)
            return "";
        start += 1;
        // System.out.println("start=" + start);

        dest_string = "</" + nodeName + ">";
        int end = respContent.indexOf(dest_string, start);
        // System.out.println("end=" + end);
        if (-1 == end)
            return "";

        return respContent.substring(start, end);

    }

    public String getVercode(String url, String reqParams, String cookie,
                             String charset) {
        System.out.println("getVercode URL : " + url);
        BufferedReader reader = sendHttpGetReq(url, reqParams, cookie, charset);
        String respContent = getRespDataByCharset(reader, charset);
        String vercode = get_node_text(respContent, "h1");
        System.out.println("vercode is : " + vercode);
        closed(reader);
        return vercode;
    }
    
    class MyAuthenticator extends Authenticator {
        private String httpUsername;
        private String httpPassword;

        public MyAuthenticator(String httpUsername, String httpPassword) {
            this.httpUsername = httpUsername;
            this.httpPassword = httpPassword;
        }

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(httpUsername, httpPassword.toCharArray());
        }
    }
    
    public String getDataFromNTLM(String Url,String user,String password) {
    	String str = null;
        try {
            CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
            Authenticator.setDefault(new MyAuthenticator(user, password));

            URL url = new URL(Url);
            URLConnection connection = url.openConnection();
            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            while (true) {
                str = br.readLine();
                if (str == null)
                {
                    break;
                }else{
                	return str;
                } 
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return str;
    }
    
    public String getDataFromSSLNTLM(String Url,String user,String password) throws Exception {
    	String str = null;
        TrustManager[] tm = { new MyX509TrustManager() };
        SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
        sslContext.init(null, tm, new java.security.SecureRandom());
        SSLSocketFactory ssf = sslContext.getSocketFactory();
        try {
            CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
            Authenticator.setDefault(new MyAuthenticator(user, password));

            URL url = new URL(Url);
            URLConnection con = url.openConnection();
            HttpsURLConnection  connection = (HttpsURLConnection )con;
            connection.setSSLSocketFactory(ssf);
            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            while (true) {
                str = br.readLine();
                if (str == null)
                {
                    break;
                }else{
                	return str;
                } 
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return str;
    }
    
    private URLConnection openConnection(URL localURL) throws IOException {
        URLConnection connection= localURL.openConnection();
        return connection;
    }
    
    public String doGet(String url) throws Exception {
        
        URL localURL = new URL(url);
        TrustManager[] tm = { new MyX509TrustManager() };
        SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
        sslContext.init(null, tm, new java.security.SecureRandom());
        SSLSocketFactory ssf = sslContext.getSocketFactory();
        
        URLConnection connection = openConnection(localURL);
        HttpsURLConnection  httpURLConnection = (HttpsURLConnection )connection;
        
        httpURLConnection.setSSLSocketFactory(ssf);
        
        httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuffer resultBuffer = new StringBuffer();
        String tempLine = null;
        
        if (httpURLConnection.getResponseCode() >= 300) {
            throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
        }
        
        try {
            inputStream = httpURLConnection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(inputStreamReader);
            
            while ((tempLine = reader.readLine()) != null) {
                resultBuffer.append(tempLine);
            }
            
        } finally {
            
            if (reader != null) {
                reader.close();
            }
            
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            
            if (inputStream != null) {
                inputStream.close();
            }
            
        }

        return resultBuffer.toString();
    }
    
    
    public static void main(String[] args){
    	String t = new HttpUtils().getDataFromNTLM("https://etpb-dev.w3ibm.mybluemix.net/TalentPlayBookService/rest/tpservicedata","hwpmgr10@us.ibm.com","tenp1208");
    	System.out.println(t);
    }
}
