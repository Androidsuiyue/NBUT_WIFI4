package com.example.administrator.nbut_wifi;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        final String username = "1";
        final String password ="2";  
        final String[] finalS = new String[0];
        new Thread(){
            @Override
            public void run() {
                super.run();

                finalS[0] = HttpReqestUtils.sendPost(MainActivity.NETWORK_STATS_SERVICE, "userId=" + username + "&" +
                        "password=" + password + "&" +
                        "service=%25E5%25A4%2596%25E7%25BD%2591&" +
                        "queryString=wlanuserip%253Debab3bb740d399833812047c4b7b670b%2526wlanacname%253D8e98b113152ff1af%2526ssid%253D%2526nasip%253D4013a1c6a367f06166df141778c1eeb4%2526snmpagentip%253D%2526mac%253De4fcfe0306f696e0b9df29db50d59dd9%2526t%253Dwireless-v2%2526url%253Dd91522d922cf13731f7d10614e27a19fc017a1765de201c2%2526apmac%253D%2526nasid%253D8e98b113152ff1af%2526vid%253D459c41cb0534323b%2526port%253D1f59129e28117833%2526nasportid%253D29864c3e14289b8596094d41130e35e27782a973bee018002bb8dd43cdc58a1da3fe53d767942a9&" +
                        "operatorPwd=&operatorPwd=&validcode=&passwordEncrypt=false");


            }

        }.start();
    }
}
