package com.itbird.design.builder.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.itbird.design.R;

import java.text.NumberFormat;

/**
 * 公共进度栏弹窗封装
 * Created by xfkang on 2020/5/23.
 */

public class CommonProgressDialog extends Dialog {

    public static final int STYLE_SPINNER = 0;

    public static final int STYLE_HORIZONTAL = 1;

    private ProgressBar mProgress;
    private TextView mMessageView;
    private TextView mTitleView;

    private int mProgressStyle = STYLE_SPINNER;
    private TextView mProgressPercent;
    private NumberFormat mProgressPercentFormat;

    private int mMax;
    private int mProgressVal;
    private CharSequence mMessage;
    private CharSequence mTitle;

    private boolean mHasStarted;
    private Handler mViewUpdateHandler;

    public CommonProgressDialog(Context context) {
        this(context, R.style.common_progress_dialog_style);
    }

    public CommonProgressDialog(Context context, int theme) {
        super(context, theme);
        initFormats();
    }

    private void initFormats() {
        mProgressPercentFormat = NumberFormat.getPercentInstance();
        mProgressPercentFormat.setMaximumFractionDigits(0);
    }

    public static CommonProgressDialog show(Context context, CharSequence title,
                                            CharSequence message) {
        return show(context, title, message, true);
    }

    public static CommonProgressDialog show(Context context, CharSequence title,
                                            CharSequence message, boolean cancelable) {
        return show(context, title, message, cancelable, null);
    }

    public static CommonProgressDialog show(Context context, CharSequence title,
                                            CharSequence message, boolean cancelable, OnCancelListener cancelListener) {
        CommonProgressDialog dialog = new CommonProgressDialog(context);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setCancelable(cancelable);
        dialog.setOnCancelListener(cancelListener);
        dialog.show();
        return dialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        if (mProgressStyle == STYLE_HORIZONTAL) {

            /* Use a separate handler to update the text views as they
             * must be updated on the same thread that created them.
             */
            mViewUpdateHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);

                    /* Update the number and percent */
                    int progress = mProgress.getProgress();
                    int max = mProgress.getMax();
                    if (mProgressPercentFormat != null) {
                        double percent = (double) progress / (double) max;
                        SpannableString tmp = new SpannableString(mProgressPercentFormat.format(percent));
                        tmp.setSpan(new StyleSpan(android.graphics.Typeface.BOLD),
                                0, tmp.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        mProgressPercent.setText(tmp);
                    }
                }
            };
            View view = inflater.inflate(R.layout.common_progress_dialog_hor, null);
            mTitleView = (TextView) view.findViewById(R.id.title);
            mProgress = (ProgressBar) view.findViewById(R.id.progress);
            mMessageView = (TextView) view.findViewById(R.id.message);
            mProgressPercent = (TextView) view.findViewById(R.id.progress_percent);
            setContentView(view);
        } else {
            View view = inflater.inflate(R.layout.common_progress_dialog_cir, null);
            mTitleView = (TextView) view.findViewById(R.id.title);
            mProgress = (ProgressBar) view.findViewById(R.id.progress);
            mMessageView = (TextView) view.findViewById(R.id.message);
            setContentView(view);
        }
        if (mMax >= 0) {
            setMax(mMax);
        }
        if (mProgressVal >= 0) {
            setProgress(mProgressVal);
        }
        if (mMessage != null) {
            setMessage(mMessage);
        }
        if (mTitle != null) {
            setTitle(mTitle);
        }
        onProgressChanged();
        setWindowStyle();
        super.onCreate(savedInstanceState);
    }

    private void setWindowStyle() {
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        int width = getContext().getResources().getDimensionPixelOffset(R.dimen.dialog_width);
        int height = getContext().getResources().getDimensionPixelOffset(R.dimen.dialog_high_height);
        layoutParams.width = width;
        layoutParams.height = height;
        window.setAttributes(layoutParams);
    }

    @Override
    public void onStart() {
        super.onStart();
        mHasStarted = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        mHasStarted = false;
    }

    public void setProgress(int value) {
        if (mHasStarted) {
            mProgress.setProgress(value);
            onProgressChanged();
        } else {
            mProgressVal = value;
        }
    }

    public int getProgress() {
        if (mProgress != null) {
            return mProgress.getProgress();
        }
        return mProgressVal;
    }

    public int getMax() {
        if (mProgress != null) {
            return mProgress.getMax();
        }
        return mMax;
    }

    public void setMax(int max) {
        if (mProgress != null) {
            mProgress.setMax(max);
            onProgressChanged();
        } else {
            mMax = max;
        }
    }

    public void setTitle(CharSequence title) {
        if (mProgress != null) {
            mTitleView.setText(title);
        } else {
            mTitle = title;
        }
    }

    public void setMessage(CharSequence message) {
        if (mProgress != null) {
            mMessageView.setText(message);
        } else {
            mMessage = message;
        }
    }

    public void setProgressStyle(int style) {
        mProgressStyle = style;
    }

    /**
     * Change the format of the small text showing the percentage of progress.
     * The default is
     * {@link NumberFormat#getPercentInstance() NumberFormat.getPercentageInstnace().}
     * Should not be called during the number is progressing.
     *
     * @param format An instance of a {@link NumberFormat} to generate the
     *               percentage text.  If null, nothing will be shown.
     */
    public void setProgressPercentFormat(NumberFormat format) {
        mProgressPercentFormat = format;
        onProgressChanged();
    }

    private void onProgressChanged() {
        if (mProgressStyle == STYLE_HORIZONTAL) {
            if (mViewUpdateHandler != null && !mViewUpdateHandler.hasMessages(0)) {
                mViewUpdateHandler.sendEmptyMessage(0);
            }
        }
    }
}
