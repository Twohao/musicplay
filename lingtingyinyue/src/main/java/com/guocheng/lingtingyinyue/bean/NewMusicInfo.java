package com.guocheng.lingtingyinyue.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/25.
 */
public class NewMusicInfo {

    /**
     * song_id : 100900023
     * title : 生来彷徨
     * ting_uid : 1157
     * artist_id : 256
     * author : 汪峰
     * album_id : 100900024
     * album_title : 生来彷徨
     * language : 国语
     * pic_big : http://musicdata.baidu.com/data2/pic/
     * pic_small : http://musicdata.baidu.com/data2/pic/
     * country : 内地
     * area : 0
     * publishtime : 2013-12-02
     * album_no : 2
     * lrclink : /data2/lrc/238618371/238618371.lrc
     * versions :
     * copy_type : 0
     * file_duration : 269
     * hot : 43087
     * charge : 0
     * havehigh : 2
     * all_artist_ting_uid : 1157
     * is_first_publish : 1
     * pic_premium :
     * pic_huge :
     * pic_singer :
     * has_mv : 0
     * learn : 1
     * song_source :
     * all_rate : 128,flac,320,256,192,64
     * resource_type : 2
     * piao_id : 0
     * korean_bb_song : 0
     * resource_type_ext : 0
     * mv_provider :
     * aliasname :
     * translatename :
     * synonym :
     * all_artist_id : 256
     * compose :
     * songwriting :
     * lrclink_k : /data2/lrc/121487548/121487548.lrc
     * is_ksong : 1
     * isgenuine : 0
     * versionid : 0
     * style : 流行
     * is_hot : 0
     * is_new : 0
     * del_status : 0
     * license_number :
     * listen_nums : 7347
     * total_listen_nums : 53435411
     * relate_status : 0
     * high_rate : 320
     * lossless_audio : 1
     * is_charge : 0
     * info :
     * song_relate_ids :
     * toneid : 600907000002877283
     * multiterminal_copytype :
     * category : 0
     * link : http://pan.baidu.com/share/link?shareid=3149246993&uk=777248339
     * sound_effect : 3
     * distribution : 0000000000,0000000000,0000000000,0000000000,0000000000,0000000000,0000000000,1111110000,1111110000,0000000000
     * has_filmtv : 0
     * group_id : 106312950
     * main_song_id : 100900023
     * yyr_song_id : 0
     * resource_provider : 1
     * updatetime : 2015-12-28 19:05:46
     * complaint_times : 0
     * bitrate_fee : {"0":"0|0","1":"0|0"}
     * mis_create_time : 2013-11-20 12:02:34
     * has_mv_mobile : 0
     */

    private List<SongListEntity> song_list;

    public void setSong_list(List<SongListEntity> song_list) {
        this.song_list = song_list;
    }

    public List<SongListEntity> getSong_list() {
        return song_list;
    }

    public static class SongListEntity {
        private String song_id;
        private String title;
        private String ting_uid;
        private String artist_id;
        private String author;
        private String album_id;
        private String album_title;
        private String language;
        private String pic_big;
        private String pic_small;
        private String country;
        private String area;
        private String publishtime;
        private String album_no;
        private String lrclink;
        private String versions;
        private String copy_type;
        private String file_duration;
        private String hot;
        private int charge;
        private int havehigh;
        private String all_artist_ting_uid;
        private String is_first_publish;
        private String pic_premium;
        private String pic_huge;
        private String pic_singer;
        private int has_mv;
        private int learn;
        private String song_source;
        private String all_rate;
        private String resource_type;
        private String piao_id;
        private String korean_bb_song;
        private String resource_type_ext;
        private String mv_provider;
        private String aliasname;
        private String translatename;
        private String synonym;
        private String all_artist_id;
        private String compose;
        private String songwriting;
        private String lrclink_k;
        private String is_ksong;
        private String isgenuine;
        private String versionid;
        private String style;
        private String is_hot;
        private String is_new;
        private String del_status;
        private String license_number;
        private String listen_nums;
        private String total_listen_nums;
        private String relate_status;
        private String high_rate;
        private String lossless_audio;
        private String is_charge;
        private String info;
        private String song_relate_ids;
        private String toneid;
        private String multiterminal_copytype;
        private String category;
        private String link;
        private String sound_effect;
        private String distribution;
        private String has_filmtv;
        private String group_id;
        private String main_song_id;
        private String yyr_song_id;
        private String resource_provider;
        private String updatetime;
        private String complaint_times;
        private String bitrate_fee;
        private String mis_create_time;
        private int has_mv_mobile;

        public void setSong_id(String song_id) {
            this.song_id = song_id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setTing_uid(String ting_uid) {
            this.ting_uid = ting_uid;
        }

        public void setArtist_id(String artist_id) {
            this.artist_id = artist_id;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public void setAlbum_id(String album_id) {
            this.album_id = album_id;
        }

        public void setAlbum_title(String album_title) {
            this.album_title = album_title;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public void setPic_big(String pic_big) {
            this.pic_big = pic_big;
        }

        public void setPic_small(String pic_small) {
            this.pic_small = pic_small;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public void setPublishtime(String publishtime) {
            this.publishtime = publishtime;
        }

        public void setAlbum_no(String album_no) {
            this.album_no = album_no;
        }

        public void setLrclink(String lrclink) {
            this.lrclink = lrclink;
        }

        public void setVersions(String versions) {
            this.versions = versions;
        }

        public void setCopy_type(String copy_type) {
            this.copy_type = copy_type;
        }

        public void setFile_duration(String file_duration) {
            this.file_duration = file_duration;
        }

        public void setHot(String hot) {
            this.hot = hot;
        }

        public void setCharge(int charge) {
            this.charge = charge;
        }

        public void setHavehigh(int havehigh) {
            this.havehigh = havehigh;
        }

        public void setAll_artist_ting_uid(String all_artist_ting_uid) {
            this.all_artist_ting_uid = all_artist_ting_uid;
        }

        public void setIs_first_publish(String is_first_publish) {
            this.is_first_publish = is_first_publish;
        }

        public void setPic_premium(String pic_premium) {
            this.pic_premium = pic_premium;
        }

        public void setPic_huge(String pic_huge) {
            this.pic_huge = pic_huge;
        }

        public void setPic_singer(String pic_singer) {
            this.pic_singer = pic_singer;
        }

        public void setHas_mv(int has_mv) {
            this.has_mv = has_mv;
        }

        public void setLearn(int learn) {
            this.learn = learn;
        }

        public void setSong_source(String song_source) {
            this.song_source = song_source;
        }

        public void setAll_rate(String all_rate) {
            this.all_rate = all_rate;
        }

        public void setResource_type(String resource_type) {
            this.resource_type = resource_type;
        }

        public void setPiao_id(String piao_id) {
            this.piao_id = piao_id;
        }

        public void setKorean_bb_song(String korean_bb_song) {
            this.korean_bb_song = korean_bb_song;
        }

        public void setResource_type_ext(String resource_type_ext) {
            this.resource_type_ext = resource_type_ext;
        }

        public void setMv_provider(String mv_provider) {
            this.mv_provider = mv_provider;
        }

        public void setAliasname(String aliasname) {
            this.aliasname = aliasname;
        }

        public void setTranslatename(String translatename) {
            this.translatename = translatename;
        }

        public void setSynonym(String synonym) {
            this.synonym = synonym;
        }

        public void setAll_artist_id(String all_artist_id) {
            this.all_artist_id = all_artist_id;
        }

        public void setCompose(String compose) {
            this.compose = compose;
        }

        public void setSongwriting(String songwriting) {
            this.songwriting = songwriting;
        }

        public void setLrclink_k(String lrclink_k) {
            this.lrclink_k = lrclink_k;
        }

        public void setIs_ksong(String is_ksong) {
            this.is_ksong = is_ksong;
        }

        public void setIsgenuine(String isgenuine) {
            this.isgenuine = isgenuine;
        }

        public void setVersionid(String versionid) {
            this.versionid = versionid;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public void setIs_hot(String is_hot) {
            this.is_hot = is_hot;
        }

        public void setIs_new(String is_new) {
            this.is_new = is_new;
        }

        public void setDel_status(String del_status) {
            this.del_status = del_status;
        }

        public void setLicense_number(String license_number) {
            this.license_number = license_number;
        }

        public void setListen_nums(String listen_nums) {
            this.listen_nums = listen_nums;
        }

        public void setTotal_listen_nums(String total_listen_nums) {
            this.total_listen_nums = total_listen_nums;
        }

        public void setRelate_status(String relate_status) {
            this.relate_status = relate_status;
        }

        public void setHigh_rate(String high_rate) {
            this.high_rate = high_rate;
        }

        public void setLossless_audio(String lossless_audio) {
            this.lossless_audio = lossless_audio;
        }

        public void setIs_charge(String is_charge) {
            this.is_charge = is_charge;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public void setSong_relate_ids(String song_relate_ids) {
            this.song_relate_ids = song_relate_ids;
        }

        public void setToneid(String toneid) {
            this.toneid = toneid;
        }

        public void setMultiterminal_copytype(String multiterminal_copytype) {
            this.multiterminal_copytype = multiterminal_copytype;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public void setSound_effect(String sound_effect) {
            this.sound_effect = sound_effect;
        }

        public void setDistribution(String distribution) {
            this.distribution = distribution;
        }

        public void setHas_filmtv(String has_filmtv) {
            this.has_filmtv = has_filmtv;
        }

        public void setGroup_id(String group_id) {
            this.group_id = group_id;
        }

        public void setMain_song_id(String main_song_id) {
            this.main_song_id = main_song_id;
        }

        public void setYyr_song_id(String yyr_song_id) {
            this.yyr_song_id = yyr_song_id;
        }

        public void setResource_provider(String resource_provider) {
            this.resource_provider = resource_provider;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public void setComplaint_times(String complaint_times) {
            this.complaint_times = complaint_times;
        }

        public void setBitrate_fee(String bitrate_fee) {
            this.bitrate_fee = bitrate_fee;
        }

        public void setMis_create_time(String mis_create_time) {
            this.mis_create_time = mis_create_time;
        }

        public void setHas_mv_mobile(int has_mv_mobile) {
            this.has_mv_mobile = has_mv_mobile;
        }

        public String getSong_id() {
            return song_id;
        }

        public String getTitle() {
            return title;
        }

        public String getTing_uid() {
            return ting_uid;
        }

        public String getArtist_id() {
            return artist_id;
        }

        public String getAuthor() {
            return author;
        }

        public String getAlbum_id() {
            return album_id;
        }

        public String getAlbum_title() {
            return album_title;
        }

        public String getLanguage() {
            return language;
        }

        public String getPic_big() {
            return pic_big;
        }

        public String getPic_small() {
            return pic_small;
        }

        public String getCountry() {
            return country;
        }

        public String getArea() {
            return area;
        }

        public String getPublishtime() {
            return publishtime;
        }

        public String getAlbum_no() {
            return album_no;
        }

        public String getLrclink() {
            return lrclink;
        }

        public String getVersions() {
            return versions;
        }

        public String getCopy_type() {
            return copy_type;
        }

        public String getFile_duration() {
            return file_duration;
        }

        public String getHot() {
            return hot;
        }

        public int getCharge() {
            return charge;
        }

        public int getHavehigh() {
            return havehigh;
        }

        public String getAll_artist_ting_uid() {
            return all_artist_ting_uid;
        }

        public String getIs_first_publish() {
            return is_first_publish;
        }

        public String getPic_premium() {
            return pic_premium;
        }

        public String getPic_huge() {
            return pic_huge;
        }

        public String getPic_singer() {
            return pic_singer;
        }

        public int getHas_mv() {
            return has_mv;
        }

        public int getLearn() {
            return learn;
        }

        public String getSong_source() {
            return song_source;
        }

        public String getAll_rate() {
            return all_rate;
        }

        public String getResource_type() {
            return resource_type;
        }

        public String getPiao_id() {
            return piao_id;
        }

        public String getKorean_bb_song() {
            return korean_bb_song;
        }

        public String getResource_type_ext() {
            return resource_type_ext;
        }

        public String getMv_provider() {
            return mv_provider;
        }

        public String getAliasname() {
            return aliasname;
        }

        public String getTranslatename() {
            return translatename;
        }

        public String getSynonym() {
            return synonym;
        }

        public String getAll_artist_id() {
            return all_artist_id;
        }

        public String getCompose() {
            return compose;
        }

        public String getSongwriting() {
            return songwriting;
        }

        public String getLrclink_k() {
            return lrclink_k;
        }

        public String getIs_ksong() {
            return is_ksong;
        }

        public String getIsgenuine() {
            return isgenuine;
        }

        public String getVersionid() {
            return versionid;
        }

        public String getStyle() {
            return style;
        }

        public String getIs_hot() {
            return is_hot;
        }

        public String getIs_new() {
            return is_new;
        }

        public String getDel_status() {
            return del_status;
        }

        public String getLicense_number() {
            return license_number;
        }

        public String getListen_nums() {
            return listen_nums;
        }

        public String getTotal_listen_nums() {
            return total_listen_nums;
        }

        public String getRelate_status() {
            return relate_status;
        }

        public String getHigh_rate() {
            return high_rate;
        }

        public String getLossless_audio() {
            return lossless_audio;
        }

        public String getIs_charge() {
            return is_charge;
        }

        public String getInfo() {
            return info;
        }

        public String getSong_relate_ids() {
            return song_relate_ids;
        }

        public String getToneid() {
            return toneid;
        }

        public String getMultiterminal_copytype() {
            return multiterminal_copytype;
        }

        public String getCategory() {
            return category;
        }

        public String getLink() {
            return link;
        }

        public String getSound_effect() {
            return sound_effect;
        }

        public String getDistribution() {
            return distribution;
        }

        public String getHas_filmtv() {
            return has_filmtv;
        }

        public String getGroup_id() {
            return group_id;
        }

        public String getMain_song_id() {
            return main_song_id;
        }

        public String getYyr_song_id() {
            return yyr_song_id;
        }

        public String getResource_provider() {
            return resource_provider;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public String getComplaint_times() {
            return complaint_times;
        }

        public String getBitrate_fee() {
            return bitrate_fee;
        }

        public String getMis_create_time() {
            return mis_create_time;
        }

        public int getHas_mv_mobile() {
            return has_mv_mobile;
        }
    }
}
