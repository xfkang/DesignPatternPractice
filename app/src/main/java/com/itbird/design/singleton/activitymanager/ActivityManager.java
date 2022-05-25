package com.itbird.design.singleton.activitymanager;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * activity栈管理，用于销毁指定的页面，例如，注册、登录逻辑
 * Created by itbird on 2022/5/24
 */
public class ActivityManager {
    private static Stack<Activity> stack;

    private static class ActivityManagerHolder {
        private static final ActivityManager activityManager = new ActivityManager();
    }

    public static ActivityManager getInstance() {
        return ActivityManagerHolder.activityManager;
    }

    private ActivityManager() {
        stack = new Stack<>();
    }

    /**
     * 添加activity
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (activity == null) {
            return;
        }
        stack.push(activity);
    }

    /**
     * 获取栈顶的activity
     *
     * @return
     */
    public Activity getTopActivity() {
        if (!stack.isEmpty()) {
            return stack.peek();
        }
        return null;
    }

    /**
     * 销毁所有的activity
     */
    public void clearAllStack() {
        while (!stack.isEmpty()) {
            Activity activity = stack.pop();
            activity.finish();
        }
    }

    /**
     * 销毁指定的activity
     *
     * @param activity
     */
    public void finishActivity(Activity activity) {
        if (activity == null) {
            return;
        }
        int size = stack.size();
        //遍历过程中不可以通过itor去remove，不然会有modException
        for (int i = 0; i < size; i++) {
            Activity activity1 = stack.get(i);
            if (activity == activity1) {
                stack.remove(i);
                activity1.finish();
                size--;
                i--;
            }
        }
    }

    /**
     * 在其他界面，销毁指定的activity
     *
     * @param activity
     */
    public void finishActivity(Class activity) {
        if (activity == null) {
            return;
        }

        int size = stack.size();
        //遍历过程中不可以通过itor去remove，不然会有modException
        for (int i = 0; i < size; i++) {
            Activity activity1 = stack.get(i);
            if (activity1.getClass().getCanonicalName().equals(activity.getCanonicalName())) {
                stack.remove(i);
                activity1.finish();
                size--;
                i--;
            }
        }
    }
}
