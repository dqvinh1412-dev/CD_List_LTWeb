package com.cdlist.web;

import com.cdlist.business.Cart;
import com.cdlist.business.Product;
import com.cdlist.data.ProductIO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    private static final String CART = "cart";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        showCart(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        Object lock = session.getId().intern();

        synchronized (lock) {
            Cart cart = getCart(session);
            if ("add".equals(action)) {
                Product product = ProductIO.getProduct(request.getParameter("productCode"));
                if (product != null) cart.addItem(product);
            } else if ("update".equals(action)) {
                cart.updateItem(request.getParameter("productCode"), readQuantity(request));
            } else if ("remove".equals(action)) {
                cart.removeItem(request.getParameter("productCode"));
            }
            session.setAttribute(CART, cart);
        }

        response.sendRedirect(request.getContextPath() + "/cart");
    }

    private Cart getCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute(CART);
        if (cart == null) {
            cart = new Cart();
            session.setAttribute(CART, cart);
        }
        return cart;
    }

    private int readQuantity(HttpServletRequest request) {
        try {
            return Integer.parseInt(request.getParameter("quantity"));
        } catch (NumberFormatException e) {
            return 1;
        }
    }

    private void showCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cart cart = getCart(request.getSession());
        request.setAttribute("cart", cart);
        request.getRequestDispatcher("/cart.jsp").forward(request, response);
    }
}
