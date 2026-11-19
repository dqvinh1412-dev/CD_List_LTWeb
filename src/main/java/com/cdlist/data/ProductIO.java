package com.cdlist.data;

import com.cdlist.business.Product;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public final class ProductIO {
    private ProductIO() { }

    public static List<Product> getProducts() {
        return Arrays.asList(
                new Product("8601", "86 (the band) - True Life Songs and Pictures", new BigDecimal("14.95")),
                new Product("pf01", "Paddlefoot - The first CD", new BigDecimal("12.95")),
                new Product("pf02", "Paddlefoot - The second CD", new BigDecimal("14.95")),
                new Product("jr01", "Joe Rut - Genuine Wood Grained Finish", new BigDecimal("14.95"))
        );
    }

    public static Product getProduct(String code) {
        for (Product product : getProducts()) {
            if (product.getCode().equals(code)) return product;
        }
        return null;
    }
}
