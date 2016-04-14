package com.guocheng.lingtingyinyue.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/3/22.
 */
public class QueryInfo implements Serializable {

    private int pageCount;
    private int totalCount;


    private List<DataEntity> data;

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity implements Serializable  {
        private int songId;
        private String name;
        private String alias;
        private String remarks;
        private boolean firstHit;
        private int librettistId;
        private Object librettistName;
        private int composerId;
        private Object composerName;
        private int singerId;
        private String singerName;
        private int singerSFlag;
        private int albumId;
        private String albumName;
        private int favorites;
        private int originalId;
        private int type;
        private Object tags;
        private int releaseYear;
        private int producer;
        private int publisher;
        private int status;
        private int audit;
        private int lang;
        private Object llList;
        private int mvPickCount;
        private int mvBulletCount;
        private int outFlag;
        private Object outList;
        private int commentCount;
        private int riskRank;


        private RightKeyEntity rightKey;
        private int operType;
        private String level;
        private int isExclusive;
        private String picUrl;
        private int listenCount;
        /**
         * bitRate : 32
         * duration : 273000
         * size : 1134439
         * suffix : m4a
         * url : http://om32.alicdn.com/199/175346199/1234026480/1774407942_16798532_l.m4a?auth_key=b1af8cba68425334d9323512634a4945-1458950400-0-null
         * typeDescription : 流畅品质
         */

        private List<AuditionListEntity> auditionList;
        /**
         * bitRate : 32
         * duration : 273000
         * size : 1134439
         * suffix : m4a
         * url : http://om32.alicdn.com/199/175346199/1234026480/1774407942_16798532_l.m4a?auth_key=b1af8cba68425334d9323512634a4945-1458950400-0-null
         * typeDescription : 流畅品质
         */

        private List<UrlListEntity> urlList;
        /**
         * id : 0
         * songId : 0
         * videoId : 2000103
         * picUrl : http://3p.pic.ttdtweb.com/3p.ttpod.com/video/mv_pic/mv_pic_20/160_90/7843/285729/2000103.jpg
         * durationMilliSecond : 271000
         * duration : 271000
         * bitRate : 1001
         * path : 5e51bebe6e52a1a63c96eabd541d6d72
         * size : 38072655
         * suffix : mp4
         * horizontal : 960
         * vertical : 540
         * url : http://otmv.alicdn.com/new/mv_2_20/5e/72/5e51bebe6e52a1a63c96eabd541d6d72.mp4?k=060d09eb0ed1fa0a&t=1459324500
         * type : 2
         * typeDescription : 1080P
         */

        private List<MvListEntity> mvList;
        private List<?> outLinks;
        /**
         * singerId : 1022413
         * singerName : TFBOYS
         * singerSFlag : 1
         * shopId : 0
         */

        private List<SingersEntity> singers;

        public void setSongId(int songId) {
            this.songId = songId;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public void setFirstHit(boolean firstHit) {
            this.firstHit = firstHit;
        }

        public void setLibrettistId(int librettistId) {
            this.librettistId = librettistId;
        }

        public void setLibrettistName(Object librettistName) {
            this.librettistName = librettistName;
        }

        public void setComposerId(int composerId) {
            this.composerId = composerId;
        }

        public void setComposerName(Object composerName) {
            this.composerName = composerName;
        }

        public void setSingerId(int singerId) {
            this.singerId = singerId;
        }

        public void setSingerName(String singerName) {
            this.singerName = singerName;
        }

        public void setSingerSFlag(int singerSFlag) {
            this.singerSFlag = singerSFlag;
        }

        public void setAlbumId(int albumId) {
            this.albumId = albumId;
        }

        public void setAlbumName(String albumName) {
            this.albumName = albumName;
        }

        public void setFavorites(int favorites) {
            this.favorites = favorites;
        }

        public void setOriginalId(int originalId) {
            this.originalId = originalId;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setTags(Object tags) {
            this.tags = tags;
        }

        public void setReleaseYear(int releaseYear) {
            this.releaseYear = releaseYear;
        }

        public void setProducer(int producer) {
            this.producer = producer;
        }

        public void setPublisher(int publisher) {
            this.publisher = publisher;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public void setAudit(int audit) {
            this.audit = audit;
        }

        public void setLang(int lang) {
            this.lang = lang;
        }

        public void setLlList(Object llList) {
            this.llList = llList;
        }

        public void setMvPickCount(int mvPickCount) {
            this.mvPickCount = mvPickCount;
        }

        public void setMvBulletCount(int mvBulletCount) {
            this.mvBulletCount = mvBulletCount;
        }

        public void setOutFlag(int outFlag) {
            this.outFlag = outFlag;
        }

        public void setOutList(Object outList) {
            this.outList = outList;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public void setRiskRank(int riskRank) {
            this.riskRank = riskRank;
        }

        public void setRightKey(RightKeyEntity rightKey) {
            this.rightKey = rightKey;
        }

        public void setOperType(int operType) {
            this.operType = operType;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public void setIsExclusive(int isExclusive) {
            this.isExclusive = isExclusive;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public void setListenCount(int listenCount) {
            this.listenCount = listenCount;
        }

        public void setAuditionList(List<AuditionListEntity> auditionList) {
            this.auditionList = auditionList;
        }

        public void setUrlList(List<UrlListEntity> urlList) {
            this.urlList = urlList;
        }

        public void setMvList(List<MvListEntity> mvList) {
            this.mvList = mvList;
        }

        public void setOutLinks(List<?> outLinks) {
            this.outLinks = outLinks;
        }

        public void setSingers(List<SingersEntity> singers) {
            this.singers = singers;
        }

        public int getSongId() {
            return songId;
        }

        public String getName() {
            return name;
        }

        public String getAlias() {
            return alias;
        }

        public String getRemarks() {
            return remarks;
        }

        public boolean isFirstHit() {
            return firstHit;
        }

        public int getLibrettistId() {
            return librettistId;
        }

        public Object getLibrettistName() {
            return librettistName;
        }

        public int getComposerId() {
            return composerId;
        }

        public Object getComposerName() {
            return composerName;
        }

        public int getSingerId() {
            return singerId;
        }

        public String getSingerName() {
            return singerName;
        }

        public int getSingerSFlag() {
            return singerSFlag;
        }

        public int getAlbumId() {
            return albumId;
        }

        public String getAlbumName() {
            return albumName;
        }

        public int getFavorites() {
            return favorites;
        }

        public int getOriginalId() {
            return originalId;
        }

        public int getType() {
            return type;
        }

        public Object getTags() {
            return tags;
        }

        public int getReleaseYear() {
            return releaseYear;
        }

        public int getProducer() {
            return producer;
        }

        public int getPublisher() {
            return publisher;
        }

        public int getStatus() {
            return status;
        }

        public int getAudit() {
            return audit;
        }

        public int getLang() {
            return lang;
        }

        public Object getLlList() {
            return llList;
        }

        public int getMvPickCount() {
            return mvPickCount;
        }

        public int getMvBulletCount() {
            return mvBulletCount;
        }

        public int getOutFlag() {
            return outFlag;
        }

        public Object getOutList() {
            return outList;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public int getRiskRank() {
            return riskRank;
        }

        public RightKeyEntity getRightKey() {
            return rightKey;
        }

        public int getOperType() {
            return operType;
        }

        public String getLevel() {
            return level;
        }

        public int getIsExclusive() {
            return isExclusive;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public int getListenCount() {
            return listenCount;
        }

        public List<AuditionListEntity> getAuditionList() {
            return auditionList;
        }

        public List<UrlListEntity> getUrlList() {
            return urlList;
        }

        public List<MvListEntity> getMvList() {
            return mvList;
        }

        public List<?> getOutLinks() {
            return outLinks;
        }

        public List<SingersEntity> getSingers() {
            return singers;
        }

        public static class RightKeyEntity implements Serializable  {
            private int price;
            private Object vipFree;
            private int paymentUnite;
            private int orderType;
            private int loginStatus;
            private Object promotionPackage;
            /**
             * bitRate : 32
             * downFlag : true
             * listenFlag : true
             * downBuyFlag : false
             * listenBuyFlag : false
             * downloadRightFlag : 0
             * auditionRightFlag : 0
             */

            private List<SongRightsEntity> songRights;
            private List<?> musicPackage;
            private List<?> albumPackage;

            public void setPrice(int price) {
                this.price = price;
            }

            public void setVipFree(Object vipFree) {
                this.vipFree = vipFree;
            }

            public void setPaymentUnite(int paymentUnite) {
                this.paymentUnite = paymentUnite;
            }

            public void setOrderType(int orderType) {
                this.orderType = orderType;
            }

            public void setLoginStatus(int loginStatus) {
                this.loginStatus = loginStatus;
            }

            public void setPromotionPackage(Object promotionPackage) {
                this.promotionPackage = promotionPackage;
            }

            public void setSongRights(List<SongRightsEntity> songRights) {
                this.songRights = songRights;
            }

            public void setMusicPackage(List<?> musicPackage) {
                this.musicPackage = musicPackage;
            }

            public void setAlbumPackage(List<?> albumPackage) {
                this.albumPackage = albumPackage;
            }

            public int getPrice() {
                return price;
            }

            public Object getVipFree() {
                return vipFree;
            }

            public int getPaymentUnite() {
                return paymentUnite;
            }

            public int getOrderType() {
                return orderType;
            }

            public int getLoginStatus() {
                return loginStatus;
            }

            public Object getPromotionPackage() {
                return promotionPackage;
            }

            public List<SongRightsEntity> getSongRights() {
                return songRights;
            }

            public List<?> getMusicPackage() {
                return musicPackage;
            }

            public List<?> getAlbumPackage() {
                return albumPackage;
            }

            public static class SongRightsEntity implements Serializable  {
                private int bitRate;
                private boolean downFlag;
                private boolean listenFlag;
                private boolean downBuyFlag;
                private boolean listenBuyFlag;
                private int downloadRightFlag;
                private int auditionRightFlag;

                public void setBitRate(int bitRate) {
                    this.bitRate = bitRate;
                }

                public void setDownFlag(boolean downFlag) {
                    this.downFlag = downFlag;
                }

                public void setListenFlag(boolean listenFlag) {
                    this.listenFlag = listenFlag;
                }

                public void setDownBuyFlag(boolean downBuyFlag) {
                    this.downBuyFlag = downBuyFlag;
                }

                public void setListenBuyFlag(boolean listenBuyFlag) {
                    this.listenBuyFlag = listenBuyFlag;
                }

                public void setDownloadRightFlag(int downloadRightFlag) {
                    this.downloadRightFlag = downloadRightFlag;
                }

                public void setAuditionRightFlag(int auditionRightFlag) {
                    this.auditionRightFlag = auditionRightFlag;
                }

                public int getBitRate() {
                    return bitRate;
                }

                public boolean isDownFlag() {
                    return downFlag;
                }

                public boolean isListenFlag() {
                    return listenFlag;
                }

                public boolean isDownBuyFlag() {
                    return downBuyFlag;
                }

                public boolean isListenBuyFlag() {
                    return listenBuyFlag;
                }

                public int getDownloadRightFlag() {
                    return downloadRightFlag;
                }

                public int getAuditionRightFlag() {
                    return auditionRightFlag;
                }
            }
        }

        public static class AuditionListEntity implements Serializable  {
            private int bitRate;
            private int duration;
            private int size;
            private String suffix;
            private String url;
            private String typeDescription;

            public void setBitRate(int bitRate) {
                this.bitRate = bitRate;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public void setSize(int size) {
                this.size = size;
            }

            public void setSuffix(String suffix) {
                this.suffix = suffix;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public void setTypeDescription(String typeDescription) {
                this.typeDescription = typeDescription;
            }

            public int getBitRate() {
                return bitRate;
            }

            public int getDuration() {
                return duration;
            }

            public int getSize() {
                return size;
            }

            public String getSuffix() {
                return suffix;
            }

            public String getUrl() {
                return url;
            }

            public String getTypeDescription() {
                return typeDescription;
            }
        }

        public static class UrlListEntity implements Serializable  {
            private int bitRate;
            private int duration;
            private int size;
            private String suffix;
            private String url;
            private String typeDescription;

            public void setBitRate(int bitRate) {
                this.bitRate = bitRate;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public void setSize(int size) {
                this.size = size;
            }

            public void setSuffix(String suffix) {
                this.suffix = suffix;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public void setTypeDescription(String typeDescription) {
                this.typeDescription = typeDescription;
            }

            public int getBitRate() {
                return bitRate;
            }

            public int getDuration() {
                return duration;
            }

            public int getSize() {
                return size;
            }

            public String getSuffix() {
                return suffix;
            }

            public String getUrl() {
                return url;
            }

            public String getTypeDescription() {
                return typeDescription;
            }
        }

        public static class MvListEntity implements Serializable  {
            private int id;
            private int songId;
            private int videoId;
            private String picUrl;
            private int durationMilliSecond;
            private int duration;
            private int bitRate;
            private String path;
            private int size;
            private String suffix;
            private int horizontal;
            private int vertical;
            private String url;
            private int type;
            private String typeDescription;

            public void setId(int id) {
                this.id = id;
            }

            public void setSongId(int songId) {
                this.songId = songId;
            }

            public void setVideoId(int videoId) {
                this.videoId = videoId;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }

            public void setDurationMilliSecond(int durationMilliSecond) {
                this.durationMilliSecond = durationMilliSecond;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public void setBitRate(int bitRate) {
                this.bitRate = bitRate;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public void setSize(int size) {
                this.size = size;
            }

            public void setSuffix(String suffix) {
                this.suffix = suffix;
            }

            public void setHorizontal(int horizontal) {
                this.horizontal = horizontal;
            }

            public void setVertical(int vertical) {
                this.vertical = vertical;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public void setType(int type) {
                this.type = type;
            }

            public void setTypeDescription(String typeDescription) {
                this.typeDescription = typeDescription;
            }

            public int getId() {
                return id;
            }

            public int getSongId() {
                return songId;
            }

            public int getVideoId() {
                return videoId;
            }

            public String getPicUrl() {
                return picUrl;
            }

            public int getDurationMilliSecond() {
                return durationMilliSecond;
            }

            public int getDuration() {
                return duration;
            }

            public int getBitRate() {
                return bitRate;
            }

            public String getPath() {
                return path;
            }

            public int getSize() {
                return size;
            }

            public String getSuffix() {
                return suffix;
            }

            public int getHorizontal() {
                return horizontal;
            }

            public int getVertical() {
                return vertical;
            }

            public String getUrl() {
                return url;
            }

            public int getType() {
                return type;
            }

            public String getTypeDescription() {
                return typeDescription;
            }
        }

        public static class SingersEntity implements Serializable {
            private int singerId;
            private String singerName;
            private int singerSFlag;
            private int shopId;

            public void setSingerId(int singerId) {
                this.singerId = singerId;
            }

            public void setSingerName(String singerName) {
                this.singerName = singerName;
            }

            public void setSingerSFlag(int singerSFlag) {
                this.singerSFlag = singerSFlag;
            }

            public void setShopId(int shopId) {
                this.shopId = shopId;
            }

            public int getSingerId() {
                return singerId;
            }

            public String getSingerName() {
                return singerName;
            }

            public int getSingerSFlag() {
                return singerSFlag;
            }

            public int getShopId() {
                return shopId;
            }
        }
    }
}
