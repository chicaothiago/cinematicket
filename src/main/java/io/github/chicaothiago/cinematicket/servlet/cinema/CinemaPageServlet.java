package io.github.chicaothiago.cinematicket.servlet.cinema;

import io.github.chicaothiago.cinematicket.bean.CinemaBean;
import io.github.chicaothiago.cinematicket.dao.CinemaDao;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CinemaPageServlet", urlPatterns = {"/cinema", "/cinema/"})
public class CinemaPageServlet extends HttpServlet {
    CinemaDao cinemaDao = new CinemaDao();

    @Override public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        List<CinemaBean> cinemas = new ArrayList<>();
        try {
            cinemas = this.cinemaDao.selectAll();
            req.setAttribute("cinemas", cinemas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("/WEB-INF/views/cinema/index.jsp")
           .forward(req, res);
    }
}
