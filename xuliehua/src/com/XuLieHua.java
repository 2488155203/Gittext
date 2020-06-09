package com;

import java.io.*;

public class XuLieHua{
    public static void main(String[] args) {
        Student student=new Student();
        student.setId(1);
        student.setName("谭钰颖");
        student.setDate(System.currentTimeMillis());

        System.out.println(student);
        //com.Student@7ef20235
        System.out.println(student.getDate());
        //1590130644194

        //将需要序列化的类，继承Serializable
        //将对象用对象流保存到文件
        File file=new File("D:\\logfile.ser");
        try {
            //创建一个文件输出流
            FileOutputStream fileOutputStream=new FileOutputStream(file);
            //创建一个对象输出流
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
            //将对象通过文件输出流与对象输出流输出到文件中
            objectOutputStream.writeObject(student);
            //刷新
            objectOutputStream.flush();
            //关闭对象流，文件流
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
