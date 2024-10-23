package com.digi.controller;

import com.digi.exceptions.ValidationException;
import com.digi.model.Address;
import com.digi.model.User;
import com.digi.service.AddressService;
import com.digi.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        User login = null;
        String errorMessage = null;

        try {
            login = UserService.login(email, password);
        } catch (Exception e) {
            if (e instanceof ValidationException) {
                errorMessage = e.getMessage();
            }
        }


        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/login-page.jsp").forward(request, response);
        } else {
            session.setAttribute("user", login);
            if (login != null) {
                session.setAttribute("address", AddressService.getAddressByUserId(login.getId()));
            }
            response.sendRedirect("home-page.jsp");
        }

    }
}
