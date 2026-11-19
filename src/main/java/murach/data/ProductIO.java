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
            if (product.getCode().equals(code)) {
                return product;
            }
        }
        return null;
    }

    public static ArrayList<Product> getProducts(String path) {
        ArrayList<Product> products = new ArrayList<Product>();
        BufferedReader in = null;

        try {
            in = new BufferedReader(new FileReader(path));
            String line;
            while ((line = in.readLine()) != null) {
                StringTokenizer tokens = new StringTokenizer(line, "|");
                String code = tokens.nextToken();
                String description = tokens.nextToken();
                double price = Double.parseDouble(tokens.nextToken());
                products.add(new Product(code, description, price));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return products;
    }
}
