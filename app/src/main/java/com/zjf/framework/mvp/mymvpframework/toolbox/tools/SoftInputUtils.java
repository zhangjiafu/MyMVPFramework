package com.zjf.framework.mvp.mymvpframework.toolbox.tools;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by zhul on 2015/12/9.
 */
public class SoftInputUtils {

    /**
     * 获取输入法管理
     *
     * @param context
     * @return
     */
    public final InputMethodManager getInputMethodManager(Context context) {
        return (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    /**
     * 显示软键盘
     *
     * @param act 当前Activity
     * @param view 软键盘对应的view
     */
    public final void showSoftInput(Activity act, View view) {
        InputMethodManager imm = getInputMethodManager(act);
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

    /**
     * 隐藏软键盘
     * @param act 当前Activity
     */
    public final void hideSoftInput(Activity act) {
        View focusdView = act.getCurrentFocus();
        if (null != focusdView) {
            InputMethodManager imm = getInputMethodManager(act);
            imm.hideSoftInputFromWindow(focusdView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } else {
            return;
        }
    }

    /**
     * 输入法是否打开
     * @param act 当前Activity
     */
    public final boolean isSoftInputOpen(Activity act){
        InputMethodManager imm = getInputMethodManager(act);
        return imm.isActive();
    }

    /**
     * 在指定View上，输入法是否打开
     * @param v 指定的View
     */
    public final boolean isSoftInputOpen(Activity act, View v){
        InputMethodManager imm = getInputMethodManager(act);
        return imm.isActive(v);
    }
/*
    public static void hideSoftInput(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view == null) view = new View(activity);
        InputMethodManager imm = (InputMethodManager) activity
                .getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }*/

    public static void showSoftInput(EditText edit, Context context) {
        edit.setFocusable(true);
        edit.setFocusableInTouchMode(true);
        edit.requestFocus();
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(edit, 0);
    }

    public static void toggleSoftInput(Context context) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }
}
