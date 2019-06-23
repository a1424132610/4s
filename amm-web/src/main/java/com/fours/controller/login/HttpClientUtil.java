package com.fours.controller.login;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {
    // http://bugtracker.itsource.cn/wechat/callback?code=222&state=99
    // http://bugtracker.itsource.cn/wechat/callback    code=222&state=99
    public static String doGet(String uri) {
        //1:创建一个HttpClient的实例
        CloseableHttpClient httpclient = HttpClients.createDefault();
        //2:创建一个get请求实例
        HttpGet httpGet = new HttpGet(uri);
        //请求的响应:
        CloseableHttpResponse response1 = null;
        try {
            //3:使用HttpClient的实例执行get请求
            response1 = httpclient.execute(httpGet);
            //http请求的状态:404 500 200
            System.out.println(response1.getStatusLine());
            int statusCode = response1.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                //请求成功:
                HttpEntity entity1 = response1.getEntity();
                String result = EntityUtils.toString(entity1, "utf-8");
                System.out.println(result);
                return result;
            } else {
                //请求失败:自己做自己的业务逻辑
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}