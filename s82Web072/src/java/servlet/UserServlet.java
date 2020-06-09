package servlet;

import dao.UserDao;
import pojo.GoodsLover;
import pojo.Userinfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/user1.do")
public class UserServlet extends HttpServlet {

    UserDao userDao=new UserDao();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");


        String p=request.getParameter("p");

        if ("addlover".equals(p)){
            doaddlover(request,response);
        }
        if ("login".equals(p)){
            dologin(request,response);
        }
    }

    private void dologin(HttpServletRequest request, HttpServletResponse response)throws IOException {
        String account=request.getParameter("account");
        String password=request.getParameter("password");

        Userinfo userinfo=userDao.login(account,password);

        if (userinfo==null){
            response.getWriter().println("<script>alert('登陆失败');location='login.jsp';</script>");
            return;
        }
        HttpSession session = request.getSession();
        session.setAttribute("userinfo", userinfo);
        response.sendRedirect("user.do?p=fenye");

    }

    //将商品添加到收藏
    private void doaddlover(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //判断用户是否登录
        Userinfo userinfo= (Userinfo)request.getSession().getAttribute("userinfo");

        if (userinfo==null) {
            response.getWriter().println("<script>alert('请先登录');location='login.jsp';</script>");
            return;
        }

        String fid=request.getParameter("fid");
        String account=userinfo.getAccount();
        //判断是否收藏
        List<GoodsLover> list=userDao.check( account, fid);
        if (list.size()>0){
            response.getWriter().println("<script>alert('该商品已收藏');location='user.do?p=findbyid&fid="+fid+"';</script>");
            return;
        }

        userDao.addlover(account,fid);
        response.sendRedirect("user.do?p=findbyid&fid="+fid);
    }
}
