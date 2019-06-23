package com.example.administrator.nbut_wifi.activities;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.example.administrator.nbut_wifi.R;
import com.example.administrator.nbut_wifi.utils.HttpReqestUtils;
import com.example.administrator.nbut_wifi.utils.StringUtils;
import com.example.administrator.nbut_wifi.utils.WIFIUitls;
import org.json.JSONException;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    CheckBox cb_remmenber;
    CheckBox cb_autoLogin;
    EditText et_username;
    EditText et_password;
    Button btn_login;

    SharedPreferences pref = null;
    String username;
    String password;
    static String NBUT_WIFILOGIN="http://10.23.4.165/eportal/InterFace.do";
    static String NBUT_WIFIQUERYSTRING="http://tile-service.weather.microsoft.com/zh-CN/livetile/preinstall?region=CN&appid=C98EA5B0842DBB9405BBF071E1DA76512D21FE36&FORM=Threshold";
    String WIFI_NAME="NBUT-WLAN";
    private WIFIUitls wifiAdmin;
    private String  param = "wlanuserip%253Debab3bb740d399833812047c4b7b670b%2526wlanacname%253D8e98b113152ff1af%2526ssid%253D%2526nasip%253D4013a1c6a367f06166df141778c1eeb4%2526snmpagentip%253D%2526mac%253De4fcfe0306f696e0b9df29db50d59dd9%2526t%253Dwireless-v2%2526url%253Dd91522d922cf13731f7d10614e27a19fc017a1765de201c2%2526apmac%253D%2526nasid%253D8e98b113152ff1af%2526vid%253D459c41cb0534323b%2526port%253D1f59129e28117833%2526nasportid%253D29864c3e14289b8596094d41130e35e27782a973bee018002bb8dd43cdc58a1da3fe53d767942a95";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        btn_login = findViewById(R.id.btn_login);
        et_password = findViewById(R.id.et_passw);
        et_username= findViewById(R.id.et_username);
        cb_autoLogin = findViewById(R.id.cb_logina);
        cb_remmenber = findViewById(R.id.cb_passworda);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        btn_login.setOnClickListener(this);
       //初始化权限
        inti();
    }

    private void inti() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_NETWORK_STATE}, 3);
            }
            return;
        }
        username = pref.getString("name",null);
        et_username.setText(username);
        password= pref.getString("pass", null);
        et_password.setText(password);
        //先确定手机有没有连上NBUT
        //连上了有没有网,
        wifiAdmin = new WIFIUitls(this);
        if (!wifiAdmin.isWifiEnable()) {
            wifiAdmin.openWifi();
        }
        if (wifiAdmin.getmWifiManager().isWifiEnabled()) {

            if (!wifiAdmin.isConnect(WIFI_NAME)) {
                if (wifiAdmin.ifWifiExsits(WIFI_NAME)) {
                    wifiAdmin.addNetwork(wifiAdmin.CreateWifiInfo(WIFI_NAME, null, 1));
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //登录事件
            case R.id.btn_login:
                final String[] s = {null};
                username=et_username.getText().toString();
                password=et_password.getText().toString();
                SharedPreferences.Editor editor= pref.edit();
                    //保存密码
                    editor.putString("name",username);
                    editor.putString("pass",password);
                    editor.commit();
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        final String queryString = HttpReqestUtils.sendGet(NBUT_WIFIQUERYSTRING, null);
                        String s1 = HttpReqestUtils.sendGet(NBUT_WIFILOGIN, "method=login&userId=" + username + "&" +
                                "password=" + password + "&" +
                                "service=%25E5%25A4%2596%25E7%25BD%2591"+"&" +
                                "queryString="+ param +"&" +
                                "operatorPwd=&operatorUserId=&validcode=&passwordEncrypt=false");
                        System.out.println(s1);
                        s[0] = StringUtils.getLoginInfo(s1);
                        System.out.println(s[0]);

                    }
                }.start();
                Toast.makeText(MainActivity.this, s[0], Toast.LENGTH_SHORT).show();

                break;
             //自动登录
            case R.id.cb_logina:
                break;
        }

    }
}
