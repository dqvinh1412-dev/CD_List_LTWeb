package murach.data;

import murach.business.Product;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ProductIO {
    public static Product getProduct(String code, String path) {
        ArrayList<Product> products = getProducts(path);
        for (Product product : products) {
            if (product.getCode().equals(code)) return product;
        }
        return null;
    }

    public static ArrayList<Product> getProducts(String path) {
        ArrayList<Product> products = new ArrayList<Product>();
        try {
            BufferedReader in = new BufferedReader(new FileReader(path));
            String line;
            while ((line = in.readLine()) != null) {
                StringTokenizer tokens = new StringTokenizer(line, "|");
                products.add(new Product(tokens.nextToken(), tokens.nextToken(),
                        Double.parseDouble(tokens.nextToken())));
            }
            in.close();
        } catch (IOException e) { }
        return products;
    }
}
