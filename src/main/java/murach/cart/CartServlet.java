package murach.cart;

import murach.business.Cart;
import murach.business.LineItem;
import murach.business.Product;
import murach.data.ProductIO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class CartServlet extends HttpServlet {
    private String path;

    @Override
    public void init() throws ServletException {
        path = getServletContext().getRealPath("/WEB-INF/products.txt");
        ArrayList<Product> products = ProductIO.getProducts(path);
        getServletContext().setAttribute("products", products);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "shop";
        }

        String productCode = request.getParameter("productCode");
        if (productCode != null) {
            final Object lock = request.getSession().getId().intern();
            synchronized (lock) {
                HttpSession session = request.getSession();
                Cart cart = (Cart) session.getAttribute("cart");
                if (cart == null) {
                    cart = new Cart();
                }

                Product product = ProductIO.getProduct(productCode, path);
                if (product != null) {
                    // add item to cart
                    cart.addItem(new LineItem(product, 1));
                }
                session.setAttribute("cart", cart);
            }
            action = "cart";
        }

        String url = "/index.jsp";
        if (action.equals("cart")) {
            url = "/cart.jsp";
        } else if (action.equals("checkout")) {
            url = "/checkout.jsp";
        }

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productCode = request.getParameter("productCode");
        String quantityString = request.getParameter("quantity");
        int quantity = 1;

        if (quantityString != null) {
            try {
                quantity = Integer.parseInt(quantityString);
            } catch (NumberFormatException e) {
                quantity = 0;
            }

            if (quantity < 0) {
                quantity = 0;
            }
        }

        final Object lock = request.getSession().getId().intern();
        synchronized (lock) {
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                cart = new Cart();
            }

            Product product = ProductIO.getProduct(productCode, path);
            if (product != null) {
                if (quantityString == null) {
                    // add item to cart
                    cart.addItem(new LineItem(product, 1));
                } else {
                    // update item quantity
                    cart.updateItem(productCode, quantity);
                }
            }
            session.setAttribute("cart", cart);
        }
        getServletContext().getRequestDispatcher("/cart.jsp").forward(request, response);
    }
}
