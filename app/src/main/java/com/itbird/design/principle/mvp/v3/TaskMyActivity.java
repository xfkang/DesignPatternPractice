package com.itbird.design.principle.mvp.v3;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.itbird.design.R;

public class TaskMyActivity extends BaseActivity<TaskMyContract.View, TaskMyPresenter> implements TaskMyContract.View {

    private static final String TAG = TaskMyActivity.class.getSimpleName();
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.textview);
        Log.e(TAG, TAG + " onCreate");

        mPresenter.loadDataFromModel();
    }

    @Override
    TaskMyPresenter initPresenter() {
        return new TaskMyPresenter();
    }


    @Override
    public void updateTextView(String s) {
        mTextView.setText(s);
    }
}