package io.github.chicaothiago.cinematicket.servlet.movies;

import io.github.chicaothiago.cinematicket.bean.MovieBean;
import io.github.chicaothiago.cinematicket.dao.MovieDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "MoviePage", urlPatterns = {"/movie", "/movie/"})
public class MoviePageServlet extends HttpServlet {
    MovieDao movieDao = new MovieDao();
    @Override protected void service(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        ArrayList<MovieBean> movies = new ArrayList<>();
        try {
            movies = this.movieDao.selectAllMovies();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("movies", movies);
        req.getRequestDispatcher("/WEB-INF/views/movies/index.jsp").forward(req, resp);
    }
}
