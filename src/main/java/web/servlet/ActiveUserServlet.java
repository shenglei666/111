package web.servlet;

import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/activeUserServlet")
public class ActiveUserServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //获取激活码
        String code = request.getParameter("code");
        if(code!=null){
            //调用service完成激活
            UserService service =new UserServiceImpl();
            boolean flag= service.active(code);
            //判断标记
            String mag =null ;

            if (flag){
                //激活成功http://localhost:8080//activeUserServlet

                mag ="<a href ='http://localhost:8080/login.html'>激活成功，点击登录</a>";

            }else{
                mag="激活失败，请练习管理员";

            }
            response.setContentType("text/html;charset=utf-8");
//            response.getWriter().write(json);
//            response.setContentType("text/html,charset=utf-8");
            response.getWriter().write(mag);
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

}


