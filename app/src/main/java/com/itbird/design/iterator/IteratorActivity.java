package com.itbird.design.iterator;


import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.itbird.design.R;
import com.itbird.design.iterator.database.DBHelper;
import com.itbird.design.iterator.usersystem.Userinfo;
import com.itbird.design.iterator.usersystem.UserinfoSystemA;
import com.itbird.design.iterator.usersystem.UserinfoSystemB;
import com.itbird.design.iterator.usersystem.iterator.Iterator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试迭代器模式
 * 迭代器模式，定义，wx与qq的用户系统查询，服务端与客户端的约定（不一定是数据格式，也可以是一个接口），
 * 补存：底部导航栏的实现方式（RadioGroup、BottomNavigationView、自定义View、迭代器模式的TabBottomNavigation）
 * Created by itbird on 2022/7/4
 */
public class IteratorActivity extends AppCompatActivity {

    private static final String TAG = IteratorActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proxy);
        tesPrintSystemUserinfo();
        viewArrayListAndHashmapSource();
        viewCursorSource();
    }

    /**
     * DB的cursor也是迭代器的实现
     */
    private void viewCursorSource() {
        Uri url = Uri.parse("content://com.itbird.design.iterator.database.DataProvider/" + DBHelper.TABLE_NAME);
        Cursor cursor = getContentResolver().query(url, new String[]{"name"}, null, null, null);
        cursor.moveToFirst();
        do {
            //todo
            String name = cursor.getString(0);
        } while (cursor.moveToNext());
    }

    /**
     * 为了查看ArrayList与HashMap的迭代器源码
     */
    private void viewArrayListAndHashmapSource() {
        /**
         * ArrrayList使用
         */
        ArrayList<String> arrayList = new ArrayList<>();
        Iterator<String> iterator = (Iterator<String>) arrayList.iterator();

        /**
         * HashMap使用
         */
        HashMap<String, String> hashMap = new HashMap<>();
        Iterator<Map.Entry<String, String>> k = (Iterator<Map.Entry<String, String>>) hashMap.entrySet().iterator();
    }

    private void tesPrintSystemUserinfo() {
        tesIteratorV1("小爱");
        tesIteratorV2("小爱");
    }

    /**
     * 遍历A、B系统的用户信息
     */
    private void tesIteratorV1(String inputName) {
        /**
         * 打印A系统的用户信息
         */
        UserinfoSystemA userinfoSystemA = new UserinfoSystemA();
        List<Userinfo> list = userinfoSystemA.getUserinfos();
        for (int i = 0; i < list.size(); i++) {
            Log.d(TAG, "name = " + list.get(i).getName() + " age = " + list.get(i).getAge());
            if (TextUtils.equals(list.get(i).getName(), inputName)) {
                Log.d(TAG, "登录成功");
                return;
            }
        }
        /**
         * 打印B系统的用户信息
         */
        UserinfoSystemB userinfoSystemB = new UserinfoSystemB();
        Userinfo[] userinfos = userinfoSystemB.getUserinfos();
        for (int i = 0; i < userinfos.length; i++) {
            Log.d(TAG, "name = " + userinfos[i].getName() + " age = " + userinfos[i].getAge());
            if (TextUtils.equals(userinfos[i].getName(), inputName)) {
                Log.d(TAG, "登录成功");
                return;
            }
        }
        // todo 进行用户注册
    }

    /**
     * 打印所有的用户信息,可以使用共同的遍历方法
     */
    private void tesIteratorV2(String inputName) {
        UserinfoSystemA userinfoSystemA = new UserinfoSystemA();
        searchAllUserInfo(userinfoSystemA.getIterator(), inputName);

        UserinfoSystemB userinfoSystemB = new UserinfoSystemB();
        searchAllUserInfo(userinfoSystemB.getIterator(), inputName);
    }

    public void searchAllUserInfo(Iterator<Userinfo> userinfoIterator, String inputName) {
        while (userinfoIterator.hasNext()) {
            Userinfo userinfo = userinfoIterator.next();
            Log.d(TAG, "name = " + userinfo.getName() + " age = " + userinfo.getAge());
            if (TextUtils.equals(userinfo.getName(), inputName)) {
                Log.d(TAG, "登录成功");
                return;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

