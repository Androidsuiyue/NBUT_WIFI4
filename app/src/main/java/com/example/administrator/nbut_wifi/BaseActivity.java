package com.example.administrator.nbut_wifi;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.administrator.nbut_wifi.fragment.dialogInterface.FragmentDialogInterface;

/**
 * 制作toolbar
 * 设置标
 */
public class BaseActivity extends AppCompatActivity implements FragmentDialogInterface {

    protected boolean isShowToolbar = true;
    protected boolean isSetNavigationIcon = true;
    protected boolean isSetLogo = false;
    private Toolbar toolbar;
    private TextView toolbar_center_title;
    private Toolbar toolbar1;
    private CustomDialog customDialog;

    public static class CustomDialog extends ProgressDialog{

        public CustomDialog(Context context) {
            super(context);
        }

        public CustomDialog(Context context, int theme) {
            super(context, theme);
        }
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

    }

    public void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar_center_title = (TextView) findViewById(R.id.toolbar_center_title);


    }

    public void initProgressDialog() {
        if (customDialog == null) {
            customDialog = new CustomDialog(this);
            //能够返回
            customDialog.setCancelable(true);
            //点击空白返回
            customDialog.setCanceledOnTouchOutside(false);
            //更改此ProgressDialog的不确定模式。不确定模式，忽略进度，对话框用显示无限
            //动画代替
            customDialog.setIndeterminate(true);
        }
    }


    @Override
    public void showDialog(String message) {
        initProgressDialog();
        customDialog.setMessage(message);
        customDialog.show();
    }

    @Override
    public void dismiss() {
        if (customDialog != null && customDialog.isShowing()) {
            customDialog.dismiss();
        }
    }
}
