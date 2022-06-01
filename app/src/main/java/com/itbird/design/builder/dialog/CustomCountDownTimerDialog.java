package com.itbird.design.builder.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.itbird.design.R;

/**
 * 自定义倒计时关闭 dialog
 * Created by xfkang on 2016/6/18
 */
public class CustomCountDownTimerDialog extends Dialog {

    public CustomCountDownTimerDialog(Context context, int theme) {
        super(context, theme);
    }

    public CustomCountDownTimerDialog(Context context) {
        super(context);
    }

    public interface CountDownTimerFinsh {
        void onFinish(Dialog dialog);
    }

    public static class Builder {

        private Context context;
        private String title;
        private CountDownTimerFinsh mCountDownTimerFinsh;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setCountDownTimerFinsh(CountDownTimerFinsh message) {
            this.mCountDownTimerFinsh = message;
            return this;
        }

        /**
         * Set the Dialog title from String
         *
         * @param title
         * @return
         */
        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        /**
         * Create the custom dialog
         */
        public CustomCountDownTimerDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final CustomCountDownTimerDialog dialog = new CustomCountDownTimerDialog(context, R.style.common_dialog_style);
            dialog.setCanceledOnTouchOutside(false);
            View layout = inflater.inflate(R.layout.apa_no_button_dialog, null);
            dialog.addContentView(layout, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            WindowManager.LayoutParams p = dialog.getWindow().getAttributes(); // 获取对话框当前的参数值
            p.width = context.getResources().getDimensionPixelOffset(R.dimen.dialog_width);
            p.height = context.getResources().getDimensionPixelOffset(R.dimen.dialog_normal_height);
            //全屏，不弹出状态栏和导航栏
            p.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
            p.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
            dialog.getWindow().setAttributes(p);

            if (!TextUtils.isEmpty(title)) {
                ((TextView) layout.findViewById(R.id.title)).setText(title);
            }

            new CustomCountDownTimer(4 * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {//倒计时设置
                    int temp = (int) (millisUntilFinished / 1000);
                    ((TextView) dialog.findViewById(R.id.message)).setText(String.format(context.getResources().getString(R.string.close_time), temp));
                }

                @Override
                public void onFinish() {
                    if (mCountDownTimerFinsh != null) {
                        mCountDownTimerFinsh.onFinish(dialog);
                    }
                }
            }.start();
            dialog.setContentView(layout);
            return dialog;
        }

    }
}

