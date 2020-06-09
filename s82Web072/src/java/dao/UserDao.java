package dao;

import pojo.GoodsLover;
import pojo.Userinfo;
import util.DBUtil;

import java.util.List;

public class UserDao {
    public Userinfo login(String account,String password){
        String sql="select account,password from wo where account=? and password=?";
        List<Userinfo> list= DBUtil.query(sql,Userinfo.class,account,password);
        if (list.size()>0){
            return list.get(0);
        }
        return null;
    }

    public List<GoodsLover> check(String account,String fid) {
        String sql="select account,fid from lover where fid=? and account=?";
        List<GoodsLover> list=DBUtil.query(sql,GoodsLover.class,fid,account);
        return list;
    }

    public int addlover(String account, String fid) {
        String sql="insert into lover(account,fid) values(?,?)";
        int n=DBUtil.update(sql,account,fid);
        return n;
    }
}
