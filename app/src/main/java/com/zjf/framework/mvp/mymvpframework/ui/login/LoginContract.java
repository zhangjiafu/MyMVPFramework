package com.zjf.framework.mvp.mymvpframework.ui.login;

import com.zjf.framework.mvp.mymvpframework.data.network.model.LoginResponse;

/**
 * Created by Administrator on 2017/7/7.
 */

public class LoginContract {

    public interface LoginView {
        String getUserName();

        String getPwd();

        void loginSuccess(LoginResponse loginBean); // 登录成功，展示数据

        void loginFail(String failMsg);
    }

    public interface LoginPresenter {
        void login(String name, String pwd); // 业务逻辑
    }
}
