package com.digi.controller;

import com.digi.model.Address;
import com.digi.model.User;
import com.digi.service.AddressService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddAddressServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String country = request.getParameter("country");
        String city = request.getParameter("city");
        String street = request.getParameter("street");
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");


        Address address = new Address(0, country, city, street, user.getId());

        AddressService.saveAddress(address);

        session.setAttribute("address", address);
        request.getRequestDispatcher("/home-page.jsp").forward(request, response);

    }
}
