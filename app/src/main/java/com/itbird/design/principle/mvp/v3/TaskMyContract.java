package com.itbird.design.principle.mvp.v3;


/**
 * my Demo
 * Created by itbird on 2022/2/25
 */
public interface TaskMyContract {
    interface View {
        //界面UI刷新方法
        void updateTextView(String s);
    }

    interface Presenter {
        void loadDataFromModel();
    }
}
