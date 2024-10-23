package com.digi.controller;

import com.digi.exceptions.UserNotFoundException;
import com.digi.exceptions.ValidationException;
import com.digi.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ForgotPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        HttpSession session = request.getSession();

        String errorMessage = null;

        try {
            UserService.setResetToken(email);
        } catch (Exception e) {
            if (e instanceof UserNotFoundException) {
                errorMessage = e.getMessage();
            }
        }

        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/set-reset-token.jsp").forward(request, response);
        } else {
            session.setAttribute("email", email);
            response.sendRedirect("/forgot-password.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String resetToken = request.getParameter("resetToken");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String email = (String) session.getAttribute("email");

        String errorMessage = null;

        try {
            UserService.forgotPassword(email, resetToken, password, confirmPassword);
        } catch (Exception e) {
            if (e instanceof ValidationException) {
                errorMessage = e.getMessage();
            }
            if (e instanceof UserNotFoundException) {
                errorMessage = e.getMessage();
            }
        }

        if(errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/forgot-password.jsp").forward(request, response);
        }
        else {
            session.removeAttribute("email");
            response.sendRedirect("/login-page.jsp");
        }

    }
}
