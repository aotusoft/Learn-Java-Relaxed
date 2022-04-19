package servlet;

import bean.currentNumDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import sql.InitDatabase;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletTb", value = "/ServletTb")
public class ServletTb extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InitDatabase idb = new InitDatabase();
        String rowJson = "";
        idb.con = idb.getConnection();
        try {
            idb.CrateTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        currentNumDao cnd = new currentNumDao();
        try {
            rowJson = cnd.rowsJson(idb.con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
