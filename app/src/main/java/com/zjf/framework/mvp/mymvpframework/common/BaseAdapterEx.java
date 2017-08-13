package com.mifpay.mifu.common.views;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.mifpay.mifu.R;
import com.mifpay.toolbox.ToolBox;

/**
 * 系统BaseAdpater的扩展
 * 实现分割线的显示
 * 实现ViewHolder
 * Created by zhul on 2016/2/3.
 */
public abstract class BaseAdapterEx extends BaseAdapter {

    private Context context;
    private boolean isAutoAddDivider = true;
    private boolean isAutoAddNextArrow = false;
    private int middleDividerMarginLeftDp = 16;
    private static final int LAYOUT_ID_ROOT = 0x00111111;
    private static final int LAYOUT_ID_DIVIDER_TOP = 0x00111112;
    private static final int LAYOUT_ID_DIVIDER_BOTTOM = 0x00111113;
    private static final int LAYOUT_ID_NEXT_ARROW = 0x00111114;

    public void setMiddleDividerMarginLeftDp(int middleDividerMarginLeftDp) {
        this.middleDividerMarginLeftDp = middleDividerMarginLeftDp;
    }

    public void setIsAutoAddDivider(boolean isAutoAddDivider) {
        this.isAutoAddDivider = isAutoAddDivider;
    }

    public void setIsAutoAddNextArrow(boolean isAutoAddNextArrow) {
        this.isAutoAddNextArrow = isAutoAddNextArrow;
    }


    public BaseAdapterEx(Context context) {
        this.context = context;
    }

    /**
     * 返回item的布局xml的id
     *
     * @return
     */
    public abstract int getItemLayoutResourceId();

    @Override
    public abstract int getCount();

    @Override
    public abstract Object getItem(int position);

    @Override
    public abstract long getItemId(int position);

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 设置viewHodler
        ViewHolder holder;
        if(null == convertView){
            holder = new ViewHolder();
            View viewItem = LayoutInflater.from(context).inflate(getItemLayoutResourceId(), null);
            convertView = addRootLayout(context, viewItem);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        // 子类自己处理布局
        View view =  getView(position, convertView, parent, holder);

        if(isAutoAddDivider) {
            // 添加分割线
            view = showItemDivider(view, position);
        }
        if(isAutoAddNextArrow) {
            // 添加右边箭头
            view = addItemNextArrow(view);
        }
        return view;
    }

    private View addRootLayout(Context context, View viewItem) {
        View view = viewItem.findViewById(LAYOUT_ID_ROOT);
        if(null == view){
            // 可以添加
            // 添加根布局
            LinearLayout llRootLayout = new LinearLayout(context);
            llRootLayout.setId(LAYOUT_ID_ROOT);
            llRootLayout.setOrientation(LinearLayout.VERTICAL);
//            llRootLayout.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
//            llRootLayout.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
            llRootLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            // 添加上分割线
            View viewDividerTop = new View(context);
            viewDividerTop.setId(LAYOUT_ID_DIVIDER_TOP);
            viewDividerTop.setVisibility(View.GONE);
//            viewDividerTop.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
//            viewDividerTop.getLayoutParams().height = 1;
            viewDividerTop.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1));
            viewDividerTop.setBackgroundColor(context.getResources().getColor(R.color.common_list_item_divider));

            // 添加下分割线
            View viewDividerBottom = new View(context);
            viewDividerBottom.setId(LAYOUT_ID_DIVIDER_BOTTOM);
            viewDividerBottom.setVisibility(View.GONE);
//            viewDividerBottom.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
//            viewDividerBottom.getLayoutParams().height = 1;
            viewDividerBottom.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1));
            viewDividerBottom.setBackgroundColor(context.getResources().getColor(R.color.common_list_item_divider));

            // 配置布局
            llRootLayout.addView(viewDividerTop);
            llRootLayout.addView(viewItem);
            llRootLayout.addView(viewDividerBottom);

            return llRootLayout;
        }else{
            // 已添加过了
            return viewItem;
        }
    }

    /**
     * 添加右边箭头
     * @param view
     * @return
     */
    protected View addItemNextArrow(View view) {
        return view;
    }

    /**
     * 添加分割线
     * @param view
     * @param position
     * @return
     */
    protected View showItemDivider(View view, int position){
        // 上分割线
        View viewDividerTop = view.findViewById(LAYOUT_ID_DIVIDER_TOP);
        LinearLayout.LayoutParams layoutParamsDividerTop = (LinearLayout.LayoutParams) viewDividerTop.getLayoutParams();
        // 下分割线
        View viewDividerBottom = view.findViewById(LAYOUT_ID_DIVIDER_BOTTOM);
        LinearLayout.LayoutParams layoutParamsDividerBottom = (LinearLayout.LayoutParams) viewDividerBottom.getLayoutParams();

        if (position == 0 && getCount() == 1) {
            // 只有唯一一条数据，不处理分割线
            viewDividerTop.setVisibility(View.VISIBLE);
            viewDividerBottom.setVisibility(View.VISIBLE);
            layoutParamsDividerTop.setMargins(0, 0, 0, 0);
            layoutParamsDividerBottom.setMargins(0, 0, 0, 0);

        } else if (position == 0 && getCount() > 1) {

            // 第一条时，下分割线设置marginLeft

            viewDividerTop.setVisibility(View.VISIBLE);
            viewDividerBottom.setVisibility(View.VISIBLE);
            layoutParamsDividerTop.setMargins(0, 0, 0, 0);
            layoutParamsDividerBottom.setMargins(ToolBox.getScreenUtils().dp2px(context, middleDividerMarginLeftDp), 0, 0, 0);

        } else if (position == getCount() - 1) {
            // 最后一条，上分割线不显示

            viewDividerTop.setVisibility(View.GONE);
            viewDividerBottom.setVisibility(View.VISIBLE);
            layoutParamsDividerBottom.setMargins(0, 0, 0, 0);

        } else {
            // 不是第一条也不是最后一条
            // 上分割线不显示，下分割线设置maeginLeft

            viewDividerTop.setVisibility(View.GONE);
            viewDividerBottom.setVisibility(View.VISIBLE);
            layoutParamsDividerBottom.setMargins(ToolBox.getScreenUtils().dp2px(context, middleDividerMarginLeftDp), 0, 0, 0);
        }
        return view;
    }

    /**
     * 使用该getView方法替换原来的getView方法，需要子类实现
     * @param position
     * @param convertView
     * @param parent
     * @param holder
     * @return
     */
    public abstract View getView(int position, View convertView, ViewGroup parent, ViewHolder holder);


    /**
     * 各个控件的缓存
     */
    public class ViewHolder {
        /**
         * 缓存item里的所有view
         *
         * SparseArray的用法和性能参考 http://www.android100.org/html/201506/21/156262.html
         */
        public SparseArray<View> views = new SparseArray<View>();

        /**
         * 指定resId和类型即可获取到相应的view
         *
         * @param convertView
         * @param resId
         * @param <T>
         * @return
         */
        public <T extends View> T obtainView(View convertView, int resId) {
            View v = views.get(resId);
            if (null == v) {
                v = convertView.findViewById(resId);
                views.put(resId, v);
            }
            return (T) v;
        }
    }
}
