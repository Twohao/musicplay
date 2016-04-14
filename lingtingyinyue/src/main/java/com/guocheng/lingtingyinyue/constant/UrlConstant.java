package com.guocheng.lingtingyinyue.constant;

/**
 * Created by Administrator on 2016/3/19.
 */
public class UrlConstant {

    public static final String MUSICLIB_PARTDESC_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=qianqian&version=2.1.0&method=baidu.ting.plaza.getPartDesc&format=json";
    public static final String MUSICLIB_BILLLIST_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=qianqian&version=2.1.0&method=baidu.ting.billboard.billList&format=json&offset=0&size=50&type=";
    public static final String MUSICLIB_ALBUMLIST_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.diy.gedan&page_size=10&page_no=";
    public static final String MUSICLIB_SONG_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=webapp_music&method=baidu.ting.song.downWeb&format=json&songid=";
    public static final String MUSICLIB_HOT_SEARCH_URL = "http://api.dongting.com/misc/sug/billboard?size=20";
    public static final String MUSICLIB_TOP_FOCUS_IMG_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.7.2.0&channel=wandoujia&operator=0&method=baidu.ting.plaza.index&cuid=1BD5188AD6958738E2A93D0D97A88449";
    public static final String MUSICLIB_MV_VIDEO_URL = "http://api.dongting.com/channel/channel/mvs?size=15&page=";
    public static final String MUSICLIB_HOT_MV_VIDEO_URL = "http://api.dongting.com/song/video/type/0?order=2&size=20&page=";
    public static final String MUSICLIB_NEW_MUSIC_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=qianqian&version=2.1.0&method=baidu.ting.plaza.getNewSongs&format=json";
    public static final String MUSICLIB_GEDAN_MUSIC_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.diy.gedanInfo&format=json&listid=";

}
