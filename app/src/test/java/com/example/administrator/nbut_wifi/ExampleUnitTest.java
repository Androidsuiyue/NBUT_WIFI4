package com.example.administrator.nbut_wifi;

import com.example.administrator.nbut_wifi.utils.HttpReqestUtils;
import com.example.administrator.nbut_wifi.utils.StringUtils;
import com.example.administrator.nbut_wifi.utils.WIFIUitls;

import org.json.JSONException;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void test() throws JSONException {

        final String username="18401210137";
        final String password="170015";
        final String NBUT_WIFILOGIN = "http://10.23.4.165/eportal/InterFace.do";
        final String NBUT_WIFIQUERYSTRING = "http://tile-service.weather.microsoft.com/zh-CN/livetile/preinstall?region=CN&appid=C98EA5B0842DBB9405BBF071E1DA76512D21FE36&FORM=Threshold";
        String WIFI_NAME = "NBUT-WLAN";
        WIFIUitls wifiAdmin;
        final String param = "wlanuserip%253Debab3bb740d399833812047c4b7b670b%2526wlanacname%253D8e98b113152ff1af%2526ssid%253D%2526nasip%253D4013a1c6a367f06166df141778c1eeb4%2526snmpagentip%253D%2526mac%253De4fcfe0306f696e0b9df29db50d59dd9%2526t%253Dwireless-v2%2526url%253Dd91522d922cf13731f7d10614e27a19fc017a1765de201c2%2526apmac%253D%2526nasid%253D8e98b113152ff1af%2526vid%253D459c41cb0534323b%2526port%253D1f59129e28117833%2526nasportid%253D29864c3e14289b8596094d41130e35e27782a973bee018002bb8dd43cdc58a1da3fe53d767942a95";
    


        System.out.println("33333333");

        final String queryString = HttpReqestUtils.sendGet(NBUT_WIFIQUERYSTRING, null);
        String s1 = HttpReqestUtils.sendGet(NBUT_WIFILOGIN, "method=login&userId=" + username + "&" +
                "password=" + password + "&" +
                "service=%25E5%25A4%2596%25E7%25BD%2591" + "&" +
                "queryString=" + param + "&" +
                "operatorPwd=&operatorUserId=&validcode=&passwordEncrypt=false");

        System.out.println(s1);


        final String loginInfo = StringUtils.getLoginInfo(s1);
        System.out.println(loginInfo);

    }
}






