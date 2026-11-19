package com.cdlist.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cart implements Serializable {
    private final List<LineItem> items = new ArrayList<>();

    public void addItem(Product product) {
        for (LineItem item : items) {
            if (item.getProduct().getCode().equals(product.getCode())) {
                item.setQuantity(item.getQuantity() + 1);
                return;
            }
        }
        items.add(new LineItem(product, 1));
    }

    public void updateItem(String productCode, int quantity) {
        for (LineItem item : items) {
            if (item.getProduct().getCode().equals(productCode)) {
                if (quantity <= 0) {
                    removeItem(productCode);
                } else {
                    item.setQuantity(quantity);
                }
                return;
            }
        }
    }

    public void removeItem(String productCode) {
        Iterator<LineItem> iterator = items.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getProduct().getCode().equals(productCode)) {
                iterator.remove();
                return;
            }
        }
    }

    public List<LineItem> getItems() { return items; }
    public boolean isEmpty() { return items.isEmpty(); }
}
