package murach.business;

import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable {
    private ArrayList<LineItem> items;

    public Cart() {
        items = new ArrayList<LineItem>();
    }

    public ArrayList<LineItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<LineItem> items) {
        this.items = items;
    }

    public int getCount() {
        int count = 0;
        for (LineItem item : items) {
            count += item.getQuantity();
        }
        return count;
    }

    public void addItem(LineItem item) {
        for (LineItem currentItem : items) {
            if (currentItem.getProduct().getCode().equals(item.getProduct().getCode())) {
                currentItem.setQuantity(currentItem.getQuantity() + item.getQuantity());
                return;
            }
        }
        items.add(item);
    }

    public void updateItem(String productCode, int quantity) {
        for (LineItem item : items) {
            if (item.getProduct().getCode().equals(productCode)) {
                if (quantity <= 0) {
                    removeItem(item);
                } else {
                    item.setQuantity(quantity);
                }
                return;
            }
        }
    }

    public void removeItem(LineItem item) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getProduct().getCode().equals(item.getProduct().getCode())) {
                items.remove(i);
                return;
            }
        }
    }
}
