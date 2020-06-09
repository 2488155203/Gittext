package util;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class DBUtil {
    //实例化；连接池
    public static Vector<Connection> connectionpool=new Vector<Connection>();
    //初始化连接池
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            for (int i = 0; i <10 ; i++) {
                Connection connection= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/xiangmu","root","123456");
                connectionpool.add(connection);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //取连接
    public static Connection getConnection(){
        Connection connection=connectionpool.get(0);
        connectionpool.remove(0);
        return connection;
    }
    //释放连接
    public static void relaseConnection(Connection connection){
        connectionpool.add(connection);
    }
    //查
    public static List query(String sql, Class c, Object...p){
        List list=new ArrayList();

        Connection connection=getConnection();
        ResultSet rs= null;
        try {
            PreparedStatement ps=connection.prepareStatement(sql);
            for (int i = 0; i <p.length ; i++) {
                ps.setObject(i+1,p[i]);
            }
            rs = ps.executeQuery();

            ResultSetMetaData rsm=rs.getMetaData();//查询他包含多少个列
            int count=rsm.getColumnCount();//查询总列数

            while (rs.next()){////循环一次就有一条数据,一条记录对应一个对象
                Object object=c.newInstance();
                for (int i=0;i<count;i++){
                    String name=rsm.getColumnLabel(i+1);//得到列名
                    Field field=c.getDeclaredField(name);//根据属性名得到属性
                    field.setAccessible(true);//设置属性可以赋值
                    field.set(object,rs.getObject(i+1));//从数据库查出来的值，赋值给了对象的属性
                }
                list.add(object);//list保存了一张表的数据
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }finally {
            relaseConnection(connection);
        }

        return list;
    }
    //增删改
    public static int update(String sql,Object...l){
        Connection connection=getConnection();
        int n=0;
        try {
            PreparedStatement ps=connection.prepareStatement(sql);
            for (int i = 0; i <l.length ; i++) {
                ps.setObject(i+1,l[i]);
            }
            n=ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            relaseConnection(connection);
        }
        return n;
    }

    //聚合查询
    public static int jh(String sql,Object...m){
        Connection connection=getConnection();
        int n= 0;
        try {
            PreparedStatement ps=connection.prepareStatement(sql);
            for (int i = 0; i <m.length ; i++) {
                ps.setObject(i+1,m[i]);
            }
            ResultSet rs=ps.executeQuery();
            rs.next();
            n = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            relaseConnection(connection);
        }
        return n;
    }
}
