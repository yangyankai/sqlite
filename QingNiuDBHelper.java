package com.wt.wutang.main.db.qingniu;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.baidu.platform.comapi.map.C;
import com.kitnew.ble.QNBleDevice;
import com.wt.wutang.qingniu.QingNiuActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/*******************************************************************************
 * Author  : Yankai
 * Date    : 2016-04-21 17:27
 * Email   : yk_yang@wesugarfree.com
 * Company : 上海无糖运动
 ******************************************************************************/
//暂时没用
public class QingNiuDBHelper {
    private Context context;

    public QingNiuDBHelper(Context context) {
        this.context = context;
    }

    public void add(QingNiuDevice qingniudevice) {
        if (isExist(qingniudevice)) return;
        //打开或创建test.db数据库
        SQLiteDatabase db = context.openOrCreateDatabase("sugarfree.db", Context.MODE_PRIVATE, null);
        //创建qingniudevice表
        db.execSQL("CREATE TABLE IF NOT EXISTS qingniudevice (_id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, mac VARCHAR)");
        //插入数据
        db.execSQL("INSERT INTO qingniudevice VALUES (NULL, ?, ?)", new Object[]{qingniudevice.name, qingniudevice.mac});
        //关闭当前数据库
        db.close();
    }

    public boolean isExist(QingNiuDevice qingniudevice) {
        boolean isExt = false;
        //打开或创建test.db数据库
        SQLiteDatabase db = context.openOrCreateDatabase("sugarfree.db", Context.MODE_PRIVATE, null);
        Cursor c = db.rawQuery("SELECT * FROM qingniudevice WHERE name == ?", new String[]{qingniudevice.name});
        if (c.moveToNext()) {
            isExt = true;
        }
        c.close();
        //关闭当前数据库
        db.close();
        return isExt;
    }

    public List<QingNiuDevice> getDevices() {
        List<QingNiuDevice> deviceList = new ArrayList<>();
        //打开或创建test.db数据库
        SQLiteDatabase db = context.openOrCreateDatabase("sugarfree.db", Context.MODE_PRIVATE, null);
        Cursor c = db.query("qingniudevice", null, null, null, null, null, null);//查询并获得游标
        if (c.moveToFirst()) {//判断游标是否为空
            for (int i = 0; i < c.getCount(); i++) {
                c.move(i);//移动到指定记录
                QingNiuDevice qingNiuDevice = new QingNiuDevice();
                qingNiuDevice.name = c.getString(c.getColumnIndex("name"));
                qingNiuDevice.mac = c.getString(c.getColumnIndex("mac"));
                deviceList.add(qingNiuDevice);
            }
        }
        c.close();
        //关闭当前数据库
        db.close();
        return deviceList;
    }
}
