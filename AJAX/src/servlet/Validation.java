package servlet;

import jakarta.servlet.http.HttpSession;
import sql.InitDatabase;
import sql.userTable;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Validation")
public class Validation extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.setCharacterEncoding("UTF-8");//post方式提交，那必须要指定字符集。
            response.setContentType("text/html;charset=UTF-8");  //所有经由过滤器的数据都使用UTF-8
            String username=request.getParameter("username"); //接收传入的用户名
            String password=request.getParameter("password"); //接收登录界面传入的密码
            if (username==null){
                return;
            }
            PrintWriter out= response.getWriter();
            try{
                InitDatabase idb=new InitDatabase(); //创建数据初始化的对象
                idb.con=idb.getConnection(); //获取数据库连接
                idb.CrateTable();  //切换数据库到 testkc
                userTable utd= new userTable(); //创建用户表操作对象
                boolean dlzt=utd.DlYz(idb.con,username,password);
                //关闭数据库连接
                if (dlzt){  //真值  说明登录成功，那创建session
                    HttpSession session=request.getSession();        //获得一个session对象
                    session.setAttribute("username",username);    //将用户名存入session对象
                    session.setAttribute("password",password);    //将密码存入seession对象
                    idb.closeAll(idb.con); //关闭数据库连接
                    //response.getWriter().write(request.getRequestURL().toString());
                    response.sendRedirect("index.html");
                }else{ //假值   说明登录失败  转到登录错误页面
                    idb.closeAll(idb.con); //关闭数据库连接
                    response.sendRedirect("loginErr.html");
                }
            } catch (Exception e){
                out.print("<H1>添加失败</H1>");
                e.printStackTrace();
            }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       this.doPost(request,response);
    }
}
