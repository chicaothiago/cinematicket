package io.github.chicaothiago.cinematicket.servlet;

import io.github.chicaothiago.cinematicket.bean.CinemaBean;
import io.github.chicaothiago.cinematicket.bean.MovieBean;
import io.github.chicaothiago.cinematicket.dao.CinemaDao;
import io.github.chicaothiago.cinematicket.dao.MovieDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "HomeServlet", urlPatterns = {"/home", ""}, loadOnStartup = 1)
public class HomeServlet extends HttpServlet {
    MovieDao movieDao = new MovieDao();
    CinemaDao cinemaDao = new CinemaDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        ArrayList<MovieBean> movieBeans = new ArrayList<>();
        ArrayList<CinemaBean> cinemaBeans = new ArrayList<>();
        try {
            movieBeans = movieDao.selectAllMovies(4);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            cinemaBeans = cinemaDao.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("movies", movieBeans);
        req.setAttribute("cinemas", cinemaBeans);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
