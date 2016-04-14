package com.guocheng.lingtingyinyue.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/22.
 */
public class ArtistInfo {


    /**
     * pageCount : 1
     * totalCount : 14
     * data : [{"hot":0,"hit":0,"val":"改变自己","score":0,"url":""},{"hot":0,"hit":0,"val":"我是歌手4","score":0,"url":""},{"hot":0,"hit":0,"val":"平凡之路 / See You Again","score":0,"url":""},{"hot":0,"hit":0,"val":"相爱后动物感伤","score":0,"url":""},{"hot":0,"hit":0,"val":"往事只能回味","score":0,"url":""},{"hot":0,"hit":0,"val":"你只是在比我高的地方","score":0,"url":""},{"hot":0,"hit":0,"val":"Stay With Me","score":0,"url":""},{"hot":0,"hit":0,"val":"Autumn Leaves","score":0,"url":""},{"hot":0,"hit":0,"val":"丑八怪","score":0,"url":""},{"hot":0,"hit":0,"val":"薛之谦","score":0,"url":""},{"hot":0,"hit":0,"val":"徐佳莹","score":0,"url":""},{"hot":0,"hit":0,"val":"问明月","score":0,"url":""},{"hot":0,"hit":0,"val":"五月天","score":0,"url":""},{"hot":0,"hit":0,"val":"林宥嘉","score":0,"url":""}]
     */

    private int pageCount;
    private int totalCount;
    /**
     * hot : 0
     * hit : 0
     * val : 改变自己
     * score : 0.0
     * url :
     */

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

    public static class DataEntity {
        private int hot;
        private int hit;
        private String val;
        private double score;
        private String url;

        public void setHot(int hot) {
            this.hot = hot;
        }

        public void setHit(int hit) {
            this.hit = hit;
        }

        public void setVal(String val) {
            this.val = val;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getHot() {
            return hot;
        }

        public int getHit() {
            return hit;
        }

        public String getVal() {
            return val;
        }

        public double getScore() {
            return score;
        }

        public String getUrl() {
            return url;
        }
    }
}
