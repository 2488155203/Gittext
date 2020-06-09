package dao;

import pojo.Goods;
import pojo.Picture;
import util.DBUtil;

import java.util.List;

public class GoodsDAO {
    public int getcount(){
        String sql="select count(*) from film_information";
        int n= DBUtil.jh(sql);
        return n;
    }

    public List<Goods> fengye(int page,int size){
        String sql="select fid,fname,director,actor,type,area,language,length,text,img from film_information limit ?,?";
        List<Goods> list=DBUtil.query(sql,Goods.class,(page-1)*size,size);
        return list;
    }

    public Goods byid(String fid) {
        String sql="select fid,fname,director,actor,type,area,language,length,text,img from film_information where fid = ?";
        List<Goods> list=DBUtil.query(sql,Goods.class,fid);

        String sql2="select pid,pname from picture where fid=?";
        List<Picture> pictures=DBUtil.query(sql2,Picture.class,fid);

        if (list.size()==0){
            return null;
        }

        //
        list.get(0).getSet().addAll(pictures);

        return list.get(0);
    }
}
