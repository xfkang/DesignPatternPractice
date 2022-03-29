package com.itbird.design.principle.mvp.v1;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.itbird.design.R;


public class TaskMyActivity extends BaseActivity implements TaskMyContract.View {

    private static final String TAG = TaskMyActivity.class.getSimpleName();
    TextView mTextView;
    TaskMyPresenter mTaskPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.textview);
        Log.e(TAG, TAG + " onCreate");
    }

    @Override
    IPresenter createPresenter() {
        mTaskPresenter = new TaskMyPresenter();
        return mTaskPresenter;
    }

    @Override
    public void updateTextView(String s) {
        mTextView.setText(s);
    }
}