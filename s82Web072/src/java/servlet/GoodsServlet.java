package servlet;

import dao.GoodsDAO;
import pojo.Goods;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/user.do")
public class GoodsServlet extends HttpServlet {
    GoodsDAO goodsDAO=new GoodsDAO();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String p=request.getParameter("p");

       if ("fenye".equals(p)){
           dofenye(request,response);
       }
       if ("findbyid".equals(p)){
           dofindbyid(request,response);
       }
       if ("addCar".equals(p)){
           doaddCar(request,response);
       }
       if ("delfromcar".equals(p)){
           dodelfromcar(request,response);
       }
       if ("addcarnum".equals(p)){
           doaddcarnum(request,response);
       }
       if ("cutcarnum".equals(p)){
           cutcarnum(request,response);
       }
    }

    //实现购物车的减
    private void cutcarnum(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fid=request.getParameter("fid");
        Goods goods=goodsDAO.byid(fid);

        Map map= (Map) request.getSession().getAttribute("map");
        Integer num= (Integer) map.get(goods);

        if (num>1) {
            map.put(goods, num - 1);
            response.sendRedirect("showcar.jsp");
        }
        if (num==1){
            map.put(goods,num);
            response.getWriter().println("宝贝不能再减少了");
            response.sendRedirect("showcar.jsp");
        }
    }

    //实现购物车的加
    private void doaddcarnum(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fid=request.getParameter("fid");
        Goods goods=goodsDAO.byid(fid);

        Map map= (Map) request.getSession().getAttribute("map");
        Integer num= (Integer) map.get(goods);


        map.put(goods,num+1);
        response.sendRedirect("showcar.jsp");
    }

    //在购物车中删除
    private void dodelfromcar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fid=request.getParameter("fid");
        Goods goods=goodsDAO.byid(fid);

        HttpSession session=request.getSession();
        Map map= (Map) session.getAttribute("map");

        map.remove(goods);
        response.sendRedirect("showcar.jsp");
    }

    //把商品添加到购物车
    private void doaddCar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fid=request.getParameter("fid");
        int num=Integer.parseInt(request.getParameter("num"));

        Goods goods=goodsDAO.byid(fid);


        HttpSession session=request.getSession();
        Map<Goods,Integer> map = (Map) session.getAttribute("map");

        if (map==null){
            map=new HashMap<Goods,Integer>();
        }

        Integer n=map.get(goods);

        if (n==null){
            map.put(goods,num);
        }else {
            map.put(goods,n+num);
        }

        session.setAttribute("map",map);
        response.sendRedirect("showcar.jsp");

    }

    //根据编号查找
    private void dofindbyid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fid = request.getParameter("fid");

        Goods goods = goodsDAO.byid(fid);

        request.setAttribute("goods",goods);
        //转发
        request.getRequestDispatcher("showDetail.jsp").forward(request , response);

    }

    private void dofenye(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageString = request.getParameter("page");
        String sizeString = request.getParameter("size");

        int page = 1;
        int size = 4;

        if (pageString != null && pageString.trim().length() > 0) {
            page = Integer.parseInt(pageString);
        }
        if (sizeString != null && sizeString.trim().length() > 0) {
            size = Integer.parseInt(sizeString);
        }
        //越界判断
        if (page < 1) {
            page = 1;
        }

        //总条数
        int count = goodsDAO.getcount();
        //总页数
        int pagecount = count % size == 0 ? count / size : count / size + 1;
        if (page > pagecount) {
            page = pagecount;
        }

        List<Goods> list = goodsDAO.fengye(page, size);
        Map map = new HashMap<>();
        map.put("page", page);
        map.put("size", size);
        map.put("pagecount", pagecount);
        map.put("count", count);
        map.put("list", list);

        request.setAttribute("map", map);
        request.getRequestDispatcher("showGoods.jsp").forward(request, response);
    }

}
