package com.zjf.framework.mvp.mymvpframework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zjf.framework.mvp.mymvpframework.ui.login.LoginContract;


public class MainActivity extends AppCompatActivity implements LoginContract.LoginView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

    }

    @Override
    public String getUserName() {
        return null;
    }

    @Override
    public String getPwd() {
        return null;
    }


    @Override
    public void loginFail(String failMsg) {

    }
}
