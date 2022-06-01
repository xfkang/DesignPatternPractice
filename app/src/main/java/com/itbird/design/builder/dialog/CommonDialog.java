package com.itbird.design.builder.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;

import com.itbird.design.R;

import java.lang.ref.WeakReference;

/**
 * 公共弹窗框架封装
 * Created by xfkang on 2020/5/23.
 */


public class CommonDialog extends Dialog implements DialogInterface {

    private static final int DIALOG_STYLE_SMALL = 1;
    private static final int DIALOG_STYLE_NORMAL = 2;
    private static final int DIALOG_STYLE_HIGH = 3;

    private ButtonHandler handler;

    private View rootView;
    private int dialogStyle;

    private TextView titleTextView;
    private TextView messageTextView;
    private Button positiveButton;
    private Button negativeButton;

    private Message positiveMessage;
    private Message negativeMessage;


    public CommonDialog(@NonNull Context context) {
        super(context);
    }

    public CommonDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected CommonDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    CommonDialog(Builder builder) {
        super(builder.context, R.style.common_dialog_style);
        rootView = LayoutInflater.from(builder.context).inflate(getInflateLayout(builder), null);
        setContentView(rootView);
        setupView();
        handler = new ButtonHandler(this);
        setWindowStyle();
    }

    private void setWindowStyle() {
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        int width = getContext().getResources().getDimensionPixelOffset(R.dimen.dialog_width);
        int height = 0;
        switch (dialogStyle) {
            case DIALOG_STYLE_SMALL:
                height = getContext().getResources().getDimensionPixelOffset(R.dimen.dialog_small_height);
                break;
            case DIALOG_STYLE_NORMAL:
                height = getContext().getResources().getDimensionPixelOffset(R.dimen.dialog_normal_height);
                break;
            default:
                height = getContext().getResources().getDimensionPixelOffset(R.dimen.dialog_small_height);
                break;
        }
        layoutParams.width = width;
        layoutParams.height = height;
        window.setAttributes(layoutParams);
    }

    private void setupView() {
        titleTextView = (TextView) findViewById(R.id.title);
        messageTextView = (TextView) findViewById(R.id.message);
        positiveButton = (Button) findViewById(R.id.positive_button);
        if (positiveButton != null) {
            positiveButton.setOnClickListener(mButtonHandler);
        }
        negativeButton = (Button) findViewById(R.id.negative_button);
        if (negativeButton != null) {
            negativeButton.setOnClickListener(mButtonHandler);
        }
    }

    public void setTitle(String title) {
        if (titleTextView != null) {
            titleTextView.setText(title);
        }
    }

    public void setMessage(String message) {
        if (messageTextView != null) {
            messageTextView.setText(message);
        }
    }

    public void setPositiveButton(String text, final OnClickListener onClickListener) {
        if (positiveButton != null) {
            positiveButton.setText(text);

            if (onClickListener != null) {
                positiveMessage = handler.obtainMessage(DialogInterface.BUTTON_POSITIVE, onClickListener);
            }
        }
    }

    public void setNegativeButton(String text, final OnClickListener onClickListener) {
        if (negativeButton != null) {
            negativeButton.setText(text);

            if (onClickListener != null) {
                negativeMessage = handler.obtainMessage(DialogInterface.BUTTON_NEGATIVE, onClickListener);
            }
        }
    }

    private int getInflateLayout(Builder builder) {
        if (TextUtils.isEmpty(builder.title)) {
            throw new IllegalStateException("No title for dialog");
        }
        int layoutResID = 0;
        if (!TextUtils.isEmpty(builder.message)
                && TextUtils.isEmpty(builder.positiveText)
                && TextUtils.isEmpty(builder.negativeText)) {
            layoutResID = R.layout.common_no_button_dialog;
            dialogStyle = 1;
        }

        if (TextUtils.isEmpty(builder.message)
                && !TextUtils.isEmpty(builder.positiveText)
                && TextUtils.isEmpty(builder.negativeText)) {
            layoutResID = R.layout.common_no_message_one_button_dialog;
            dialogStyle = 1;
        }

        if (TextUtils.isEmpty(builder.message)
                && !TextUtils.isEmpty(builder.positiveText)
                && !TextUtils.isEmpty(builder.negativeText)) {
            layoutResID = R.layout.common_no_message_two_button_dialog;
            dialogStyle = 1;
        }

        if (!TextUtils.isEmpty(builder.message)
                && !TextUtils.isEmpty(builder.positiveText)
                && !TextUtils.isEmpty(builder.negativeText)) {
            layoutResID = R.layout.common_message_two_button_dialog;
            dialogStyle = 2;
        }

        if (!TextUtils.isEmpty(builder.message)
                && !TextUtils.isEmpty(builder.positiveText)
                && TextUtils.isEmpty(builder.negativeText)) {
            layoutResID = R.layout.common_message_one_button_dialog;
            dialogStyle = 2;
        }

        if (layoutResID == 0) {
            throw new IllegalStateException("Not have this dialog");
        }

        return layoutResID;
    }

    private final View.OnClickListener mButtonHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final Message m;
            if (v == positiveButton && positiveMessage != null) {
                m = Message.obtain(positiveMessage);
            } else if (v == negativeButton && negativeMessage != null) {
                m = Message.obtain(negativeMessage);
            } else {
                m = null;
            }

            if (m != null) {
                m.sendToTarget();
            }

            handler.obtainMessage(ButtonHandler.MSG_DISMISS_DIALOG, CommonDialog.this)
                    .sendToTarget();
        }
    };


    public static class Builder {
        private String title;
        private String message;
        private String positiveText;
        private OnClickListener positiveOnClickListener;
        private String negativeText;
        private OnClickListener negativeOnCLickListener;
        private boolean cancelable = true;
        private OnCancelListener onCancelListener;
        private OnDismissListener onDismissListener;
        private OnKeyListener onKeyListener;

        private final Context context;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setTitle(@StringRes int resID) {
            this.title = context.getResources().getString(resID);
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setMessage(@StringRes int resID) {
            this.message = context.getResources().getString(resID);
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setPositiveButton(@StringRes int resID, OnClickListener onClickListener) {
            this.positiveText = context.getResources().getString(resID);
            this.positiveOnClickListener = onClickListener;
            return this;
        }

        public Builder setPositiveButton(String text, OnClickListener onClickListener) {
            this.positiveText = text;
            this.positiveOnClickListener = onClickListener;
            return this;
        }

        public Builder setNegativeButton(@StringRes int resID, OnClickListener onClickListener) {
            this.negativeText = context.getResources().getString(resID);
            this.negativeOnCLickListener = onClickListener;
            return this;
        }

        public Builder setNegativeButton(String text, OnClickListener onClickListener) {
            this.negativeText = text;
            this.negativeOnCLickListener = onClickListener;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder setOnCancelListener(OnCancelListener onCancelListener) {
            this.onCancelListener = onCancelListener;
            return this;
        }

        public Builder setOnDismissListener(OnDismissListener onDismissListener) {
            this.onDismissListener = onDismissListener;
            return this;
        }

        public Builder setOnKeyListener(OnKeyListener onKeyListener) {
            this.onKeyListener = onKeyListener;
            return this;
        }

        public CommonDialog create() {
            CommonDialog commonDialog = new CommonDialog(this);
            apply(commonDialog);
            commonDialog.setCancelable(cancelable);
            if (cancelable) {
                commonDialog.setCanceledOnTouchOutside(true);
            }
            commonDialog.setOnCancelListener(onCancelListener);
            commonDialog.setOnDismissListener(onDismissListener);
            if (onKeyListener != null) {
                commonDialog.setOnKeyListener(onKeyListener);
            }
            return commonDialog;
        }

        public CommonDialog show() {
            CommonDialog commonDialog = create();
            commonDialog.show();
            return commonDialog;
        }

        private void apply(CommonDialog commonDialog) {
            commonDialog.setTitle(title);
            commonDialog.setMessage(message);
            commonDialog.setPositiveButton(positiveText, positiveOnClickListener);
            commonDialog.setNegativeButton(negativeText, negativeOnCLickListener);
        }

    }

    /**
     * 使用handler进行事件转发
     * 为了防止内存泄露，使用弱引用
     */
    private static final class ButtonHandler extends Handler {
        private static final int MSG_DISMISS_DIALOG = 1;

        private WeakReference<DialogInterface> mDialog;

        public ButtonHandler(DialogInterface dialog) {
            mDialog = new WeakReference<>(dialog);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DialogInterface.BUTTON_POSITIVE:
                case DialogInterface.BUTTON_NEGATIVE:
                case DialogInterface.BUTTON_NEUTRAL:
                    ((OnClickListener) msg.obj).onClick(mDialog.get(), msg.what);
                    break;
                case MSG_DISMISS_DIALOG:
                    ((DialogInterface) msg.obj).dismiss();
            }
        }
    }
}
