package com.guocheng.lingtingyinyue.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/3/25.
 */
public class SongUrlInfo implements Serializable {

    /**
     * error_code : 22000
     * bitrate : [{"file_bitrate":64,"file_link":"http://yinyueshiting.baidu.com/data2/music/084aed2783a335db9a9b676d5408193a/262916183/262916183.mp3?xcode=e41667ce40af63359614b4474386abae","file_extension":"mp3","original":0,"file_size":1851822,"file_duration":231,"show_link":"http://zhangmenshiting.baidu.com/data2/music/084aed2783a335db9a9b676d5408193a/262916183/262916183.mp3?xcode=e41667ce40af63359614b4474386abae","song_file_id":262916183,"replay_gain":"1.849998","free":1},{"file_bitrate":128,"file_link":"http://yinyueshiting.baidu.com/data2/music/b862601b6df6811bf1b36191d15e2861/262916179/262916179.mp3?xcode=e41667ce40af63359614b4474386abae","file_extension":"mp3","original":0,"file_size":3701136,"file_duration":231,"show_link":"http://zhangmenshiting.baidu.com/data2/music/b862601b6df6811bf1b36191d15e2861/262916179/262916179.mp3?xcode=e41667ce40af63359614b4474386abae","song_file_id":262916179,"replay_gain":"1.470001","free":1}]
     * songinfo : {"artist_id":"73363378","all_artist_id":"73363378","album_no":"0","pic_big":"http://musicdata.baidu.com/data2/pic/852a818df272e76593f100da8e54886d/262909423/262909423.jpg","pic_small":"http://musicdata.baidu.com/data2/pic/c4ffed02de647f334aee37145c3ccc8c/262909424/262909424.jpg","relate_status":"1","resource_type":"0","copy_type":"1","lrclink":"http://musicdata.baidu.com/data2/lrc/129525c769ee868d41e2fd862dac2a1b/262909420/%E8%B6%8A%E4%BA%BA%E6%AD%8C.lrc","pic_radio":"","toneid":"0","all_rate":"64,128","play_type":"","has_mv_mobile":0,"pic_premium":"","pic_huge":"","resource_type_ext":"0","bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","publishtime":"2016-03-04","si_presale_flag":"0","song_id":"74118507","title":"越人歌","ting_uid":"110968818","author":"河图","album_id":"0","album_title":"","is_first_publish":0,"havehigh":0,"charge":0,"has_mv":0,"learn":0,"song_source":"yyr","piao_id":"0","korean_bb_song":"0","mv_provider":"0000000000","special_type":0}
     */

    private int error_code;
    /**
     * artist_id : 73363378
     * all_artist_id : 73363378
     * album_no : 0
     * pic_big : http://musicdata.baidu.com/data2/pic/852a818df272e76593f100da8e54886d/262909423/262909423.jpg
     * pic_small : http://musicdata.baidu.com/data2/pic/c4ffed02de647f334aee37145c3ccc8c/262909424/262909424.jpg
     * relate_status : 1
     * resource_type : 0
     * copy_type : 1
     * lrclink : http://musicdata.baidu.com/data2/lrc/129525c769ee868d41e2fd862dac2a1b/262909420/%E8%B6%8A%E4%BA%BA%E6%AD%8C.lrc
     * pic_radio :
     * toneid : 0
     * all_rate : 64,128
     * play_type :
     * has_mv_mobile : 0
     * pic_premium :
     * pic_huge :
     * resource_type_ext : 0
     * bitrate_fee : {"0":"0|0","1":"0|0"}
     * publishtime : 2016-03-04
     * si_presale_flag : 0
     * song_id : 74118507
     * title : 越人歌
     * ting_uid : 110968818
     * author : 河图
     * album_id : 0
     * album_title :
     * is_first_publish : 0
     * havehigh : 0
     * charge : 0
     * has_mv : 0
     * learn : 0
     * song_source : yyr
     * piao_id : 0
     * korean_bb_song : 0
     * mv_provider : 0000000000
     * special_type : 0
     */

    private SonginfoEntity songinfo;
    /**
     * file_bitrate : 64
     * file_link : http://yinyueshiting.baidu.com/data2/music/084aed2783a335db9a9b676d5408193a/262916183/262916183.mp3?xcode=e41667ce40af63359614b4474386abae
     * file_extension : mp3
     * original : 0
     * file_size : 1851822
     * file_duration : 231
     * show_link : http://zhangmenshiting.baidu.com/data2/music/084aed2783a335db9a9b676d5408193a/262916183/262916183.mp3?xcode=e41667ce40af63359614b4474386abae
     * song_file_id : 262916183
     * replay_gain : 1.849998
     * free : 1
     */

    private List<BitrateEntity> bitrate;

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public void setSonginfo(SonginfoEntity songinfo) {
        this.songinfo = songinfo;
    }

    public void setBitrate(List<BitrateEntity> bitrate) {
        this.bitrate = bitrate;
    }

    public int getError_code() {
        return error_code;
    }

    public SonginfoEntity getSonginfo() {
        return songinfo;
    }

    public List<BitrateEntity> getBitrate() {
        return bitrate;
    }

    public static class SonginfoEntity implements Serializable {
        private String artist_id;
        private String all_artist_id;
        private String album_no;
        private String pic_big;
        private String pic_small;
        private String relate_status;
        private String resource_type;
        private String copy_type;
        private String lrclink;
        private String pic_radio;
        private String toneid;
        private String all_rate;
        private String play_type;
        private int has_mv_mobile;
        private String pic_premium;
        private String pic_huge;
        private String resource_type_ext;
        private String bitrate_fee;
        private String publishtime;
        private String si_presale_flag;
        private String song_id;
        private String title;
        private String ting_uid;
        private String author;
        private String album_id;
        private String album_title;
        private int is_first_publish;
        private int havehigh;
        private int charge;
        private int has_mv;
        private int learn;
        private String song_source;
        private String piao_id;
        private String korean_bb_song;
        private String mv_provider;
        private int special_type;

        public void setArtist_id(String artist_id) {
            this.artist_id = artist_id;
        }

        public void setAll_artist_id(String all_artist_id) {
            this.all_artist_id = all_artist_id;
        }

        public void setAlbum_no(String album_no) {
            this.album_no = album_no;
        }

        public void setPic_big(String pic_big) {
            this.pic_big = pic_big;
        }

        public void setPic_small(String pic_small) {
            this.pic_small = pic_small;
        }

        public void setRelate_status(String relate_status) {
            this.relate_status = relate_status;
        }

        public void setResource_type(String resource_type) {
            this.resource_type = resource_type;
        }

        public void setCopy_type(String copy_type) {
            this.copy_type = copy_type;
        }

        public void setLrclink(String lrclink) {
            this.lrclink = lrclink;
        }

        public void setPic_radio(String pic_radio) {
            this.pic_radio = pic_radio;
        }

        public void setToneid(String toneid) {
            this.toneid = toneid;
        }

        public void setAll_rate(String all_rate) {
            this.all_rate = all_rate;
        }

        public void setPlay_type(String play_type) {
            this.play_type = play_type;
        }

        public void setHas_mv_mobile(int has_mv_mobile) {
            this.has_mv_mobile = has_mv_mobile;
        }

        public void setPic_premium(String pic_premium) {
            this.pic_premium = pic_premium;
        }

        public void setPic_huge(String pic_huge) {
            this.pic_huge = pic_huge;
        }

        public void setResource_type_ext(String resource_type_ext) {
            this.resource_type_ext = resource_type_ext;
        }

        public void setBitrate_fee(String bitrate_fee) {
            this.bitrate_fee = bitrate_fee;
        }

        public void setPublishtime(String publishtime) {
            this.publishtime = publishtime;
        }

        public void setSi_presale_flag(String si_presale_flag) {
            this.si_presale_flag = si_presale_flag;
        }

        public void setSong_id(String song_id) {
            this.song_id = song_id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setTing_uid(String ting_uid) {
            this.ting_uid = ting_uid;
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

        public void setIs_first_publish(int is_first_publish) {
            this.is_first_publish = is_first_publish;
        }

        public void setHavehigh(int havehigh) {
            this.havehigh = havehigh;
        }

        public void setCharge(int charge) {
            this.charge = charge;
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

        public void setPiao_id(String piao_id) {
            this.piao_id = piao_id;
        }

        public void setKorean_bb_song(String korean_bb_song) {
            this.korean_bb_song = korean_bb_song;
        }

        public void setMv_provider(String mv_provider) {
            this.mv_provider = mv_provider;
        }

        public void setSpecial_type(int special_type) {
            this.special_type = special_type;
        }

        public String getArtist_id() {
            return artist_id;
        }

        public String getAll_artist_id() {
            return all_artist_id;
        }

        public String getAlbum_no() {
            return album_no;
        }

        public String getPic_big() {
            return pic_big;
        }

        public String getPic_small() {
            return pic_small;
        }

        public String getRelate_status() {
            return relate_status;
        }

        public String getResource_type() {
            return resource_type;
        }

        public String getCopy_type() {
            return copy_type;
        }

        public String getLrclink() {
            return lrclink;
        }

        public String getPic_radio() {
            return pic_radio;
        }

        public String getToneid() {
            return toneid;
        }

        public String getAll_rate() {
            return all_rate;
        }

        public String getPlay_type() {
            return play_type;
        }

        public int getHas_mv_mobile() {
            return has_mv_mobile;
        }

        public String getPic_premium() {
            return pic_premium;
        }

        public String getPic_huge() {
            return pic_huge;
        }

        public String getResource_type_ext() {
            return resource_type_ext;
        }

        public String getBitrate_fee() {
            return bitrate_fee;
        }

        public String getPublishtime() {
            return publishtime;
        }

        public String getSi_presale_flag() {
            return si_presale_flag;
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

        public String getAuthor() {
            return author;
        }

        public String getAlbum_id() {
            return album_id;
        }

        public String getAlbum_title() {
            return album_title;
        }

        public int getIs_first_publish() {
            return is_first_publish;
        }

        public int getHavehigh() {
            return havehigh;
        }

        public int getCharge() {
            return charge;
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

        public String getPiao_id() {
            return piao_id;
        }

        public String getKorean_bb_song() {
            return korean_bb_song;
        }

        public String getMv_provider() {
            return mv_provider;
        }

        public int getSpecial_type() {
            return special_type;
        }
    }

    public static class BitrateEntity implements Serializable {
        private int file_bitrate;
        private String file_link;
        private String file_extension;
        private int original;
        private int file_size;
        private int file_duration;
        private String show_link;
        private int song_file_id;
        private String replay_gain;
        private int free;

        public void setFile_bitrate(int file_bitrate) {
            this.file_bitrate = file_bitrate;
        }

        public void setFile_link(String file_link) {
            this.file_link = file_link;
        }

        public void setFile_extension(String file_extension) {
            this.file_extension = file_extension;
        }

        public void setOriginal(int original) {
            this.original = original;
        }

        public void setFile_size(int file_size) {
            this.file_size = file_size;
        }

        public void setFile_duration(int file_duration) {
            this.file_duration = file_duration;
        }

        public void setShow_link(String show_link) {
            this.show_link = show_link;
        }

        public void setSong_file_id(int song_file_id) {
            this.song_file_id = song_file_id;
        }

        public void setReplay_gain(String replay_gain) {
            this.replay_gain = replay_gain;
        }

        public void setFree(int free) {
            this.free = free;
        }

        public int getFile_bitrate() {
            return file_bitrate;
        }

        public String getFile_link() {
            return file_link;
        }

        public String getFile_extension() {
            return file_extension;
        }

        public int getOriginal() {
            return original;
        }

        public int getFile_size() {
            return file_size;
        }

        public int getFile_duration() {
            return file_duration;
        }

        public String getShow_link() {
            return show_link;
        }

        public int getSong_file_id() {
            return song_file_id;
        }

        public String getReplay_gain() {
            return replay_gain;
        }

        public int getFree() {
            return free;
        }
    }
}
