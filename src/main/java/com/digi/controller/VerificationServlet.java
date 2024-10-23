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

public class VerificationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String verificationCode = request.getParameter("verificationCode");
        HttpSession session = request.getSession();
        String email = (String)session.getAttribute("email");

        String errorMessage = null;

        try {
            UserService.verification(email, verificationCode);
        }
        catch (Exception e) {
            if(e instanceof UserNotFoundException) {
                errorMessage = e.getMessage();
            }
            if(e instanceof ValidationException) {
                errorMessage = e.getMessage();
            }
        }

        if(errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/verification-page.jsp").forward(request, response);
        } else {
            session.removeAttribute("email");
            response.sendRedirect("/login-page.jsp");
        }


    }
}
