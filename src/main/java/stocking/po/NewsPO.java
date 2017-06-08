package stocking.po;

import java.util.*;

/**
 * Created by xjwhhh on 2017/6/3.
 */
public class NewsPO {
    private String[] classify;//类别
    private String[] title;
    private String[] time;
    private String[] url;

    public NewsPO(String[] classify, String[] title,String[] time, String[] url) {
        this.classify = classify;
        this.title = title;
        this.time = time;
        this.url = url;
    }

    public String[] getClassify() {
        return this.classify;
    }

    public void setClassify(String[] classify) {
        this.classify = classify;
    }

    public String[] getTitle() {
        return this.title;
    }

    public void setTitle(String[] title) {
        this.title = title;
    }

    public String[] getTime() {
        return this.time;
    }

    public void setTime(String[] time) {
        this.time = time;
    }

    public String[] getUrl() {
        return this.url;
    }

    public void setUrl(String[] url) {
        this.url = url;
    }


}
