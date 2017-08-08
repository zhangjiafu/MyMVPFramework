package com.zjf.framework.mvp.mymvpframework.ui.login;


/**
 * Created by Administrator on 2017/7/7.
 */

public class LoginContract {

    public interface LoginView {
        String getUserName();

        String getPwd();


        void loginFail(String failMsg);
    }

    public interface LoginPresenter {
        void login(String name, String pwd); // 业务逻辑
    }
}
