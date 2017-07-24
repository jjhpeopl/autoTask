package com.jjh.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.IOException;

/**
 * Created by jiajianhong on 17/7/23.
 */
public class XYBTask {

    private String username = "18501378458";

    private String password = "h2o198727";

    private String url = "http://api.xybqianbao.com/service/base/login/loginPhoneByPwd/18501378458-h2o198727";

    public String login() {
        int status = 0;
        try {
            HttpClient httpClient = new HttpClient();

            GetMethod getMethod = new GetMethod(url);

            status = httpClient.executeMethod(getMethod);

            System.out.println("登录返回的结果:" + getMethod.getResponseBodyAsString());

            // 对结果进行解析
            JSONObject jsonObject = JSON.parseObject(getMethod.getResponseBodyAsString());

            if (jsonObject.getString("msg").equalsIgnoreCase("success")) {
                System.out.println("登录成功,获取到token值");

                // 通过token,uid获取用户信息
                getMethod.setURI(new URI("http://api.xybqianbao.com/service/user/myInfo"));
//                GetMethod myInfoGet = new GetMethod("http://api.xybqianbao.com/service/user/myInfo");
                httpClient.executeMethod(getMethod);
                System.out.println(getMethod.getResponseBodyAsString());
            } else {
                System.out.println("登录失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        XYBTask xybTask = new XYBTask();
        String token = xybTask.login();
    }
}
