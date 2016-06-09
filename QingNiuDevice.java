package com.wt.wutang.main.db.qingniu;

/*******************************************************************************
 * Author  : Yankai
 * Date    : 2016-04-21 17:49
 * Email   : yk_yang@wesugarfree.com
 * Company : 上海无糖运动
 ******************************************************************************/

/**
 * 在查询中轻牛没有set权限的所以这里自己定义了一个实体
 */
public class QingNiuDevice {
    public QingNiuDevice(String n, String m) {
        name = n;
        mac = m;
    }

    public QingNiuDevice() {

    }

    public String name;
    public String mac;
}
