package pojo;

import java.util.HashSet;
import java.util.Set;

public class Goods {
    private String fname;
    private String director;
    private String actor;
    private String type;
    private String area;
    private String language;
    private int length;
    private String text;
    private int fid;
    private String img;
    private Set<Picture> set =new HashSet<Picture>();

    public Set<Picture> getSet() {
        return set;
    }

    public void setSet(Set<Picture> set) {
        this.set = set;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int hashCode() {
        return fid;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Goods){
            if (((Goods)obj).getFid()==this.fid){
                return true;
            }
        }
        return false;
    }
}
