package com.android.volley;

import java.io.Serializable;

/**
 * Created by 13071505 on 2017/5/24.
 * UserAgent实体对象
 */
public class UserAgentEntity implements Serializable{

    public String appId;// APP类型：PPTVSports
    public String appVersion;//app版本号
    public String terminal;// 手机型号
    public String operation;// 终端类型，IOS 或 Android
    public String osVersion;// 客户端操作系统版本号
}
