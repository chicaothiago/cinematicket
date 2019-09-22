package io.github.chicaothiago.cinematicket.servlet.cinema;

import io.github.chicaothiago.cinematicket.bean.AddressBean;
import io.github.chicaothiago.cinematicket.bean.CinemaBean;
import io.github.chicaothiago.cinematicket.dao.AddressDao;
import io.github.chicaothiago.cinematicket.dao.CinemaDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "CinemaCrudServlet", urlPatterns = {"/cinema/crud"})
public class CinemaCrudServlet extends HttpServlet {
    CinemaDao cinemaDao = new CinemaDao();
    AddressDao addressDao = new AddressDao();

    @Override
    protected void doGet(
        HttpServletRequest req, HttpServletResponse resp
    ) throws ServletException, IOException {
        if (req.getParameter("delete_id") != null ) {
            try {
                this.cinemaDao.delete(req);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resp.sendRedirect(req.getContextPath() + "/cinema");
        }

        String id = req.getParameter("id");
        if (id != null) {
            try {
                CinemaBean cinemaBean = this.cinemaDao.findOne(Integer.parseInt(id));
                req.setAttribute("cinema", cinemaBean);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        req.getRequestDispatcher("/WEB-INF/views/cinema/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") != null) {
            try {
                this.addressDao.updateAddress(req);
                this.cinemaDao.updateCinema(req);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            AddressBean addressBean = new AddressBean();
            addressBean.street = req.getParameter("street");
            addressBean.number = req.getParameter("number");
            addressBean.zipcode = req.getParameter("zipcode");
            addressBean.city = req.getParameter("city");
            addressBean.state = req.getParameter("state");
            addressBean.country = req.getParameter("country");
            try {
                addressBean.id = this.addressDao.insertBean(addressBean, true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            CinemaBean cinemaBean = new CinemaBean();
            cinemaBean.name = req.getParameter("name");
            cinemaBean.address = addressBean;
            try {
                cinemaDao.insertBean(cinemaBean);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        resp.sendRedirect(req.getContextPath() + "/cinema");
    }

    @Override protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        System.out.println("aff");
    }
}
