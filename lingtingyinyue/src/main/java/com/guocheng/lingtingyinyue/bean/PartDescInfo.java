package com.guocheng.lingtingyinyue.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/19.
 */
public class PartDescInfo {

    /**
     * id : 1
     * content : 掌握最新乐坛流行动向
     * type : 新歌榜
     * mis_editor_name : zhaoyu01
     * mis_edit_time : 2013-04-03 19:29:38
     */

    private List<DescEntity> desc;

    public void setDesc(List<DescEntity> desc) {
        this.desc = desc;
    }

    public List<DescEntity> getDesc() {
        return desc;
    }

    public static class DescEntity {
        private int id;
        private String content;
        private String type;
        private String mis_editor_name;
        private String mis_edit_time;

        public void setId(int id) {
            this.id = id;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setMis_editor_name(String mis_editor_name) {
            this.mis_editor_name = mis_editor_name;
        }

        public void setMis_edit_time(String mis_edit_time) {
            this.mis_edit_time = mis_edit_time;
        }

        public int getId() {
            return id;
        }

        public String getContent() {
            return content;
        }

        public String getType() {
            return type;
        }

        public String getMis_editor_name() {
            return mis_editor_name;
        }

        public String getMis_edit_time() {
            return mis_edit_time;
        }
    }
}
