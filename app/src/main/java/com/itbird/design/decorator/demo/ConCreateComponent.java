package com.itbird.design.decorator.demo;

import android.util.Log;

/**
 * 抽象类的具体实现者
 * Created by itbird on 2022/6/7
 */
public class ConCreateComponent extends Component {
    private static final String TAG = ConCreateComponent.class.getSimpleName();

    @Override
    public void operation() {
        Log.d(TAG, "ConCreateComponent operation");
    }
}
