package stocking.po;

import java.util.*;

/**
 * Created by xjwhhh on 2017/6/3.
 */
public class NewsPO {
    private List<String> classify;//类别
    private List<String> title;
    private List<String> time;
    private List<String> url;

    public NewsPO(List<String> classify, List<String> title, List<String> time, List<String> url) {
        this.classify = classify;
        this.title = title;
        this.time = time;
        this.url = url;
    }

    public List<String> getClassify() {
        return this.classify;
    }

    public void setClassify(List<String> classify) {
        this.classify = classify;
    }

    public List<String> getTitle() {
        return this.title;
    }

    public void setTitle(List<String> title) {
        this.title = title;
    }

    public List<String> getTime() {
        return this.time;
    }

    public void setTime(List<String> time) {
        this.time = time;
    }

    public List<String> getUrl() {
        return this.url;
    }

    public void setUrl(List<String> url) {
        this.url = url;
    }


}
