# CD List

Bài tập Chapter 7 sử dụng Java Servlet/JSP để làm trang danh sách CD và giỏ hàng.

## Chạy project

```bash
mvn clean package
```

Deploy `target/ch07cart.war` vào Tomcat 9 hoặc chạy bằng server Tomcat trong IDE. Mở:

`http://localhost:8080/ch07cart/`

## Domain model

- `Product`: mã, mô tả và giá CD.
- `LineItem`: một CD và số lượng trong giỏ.
- `Cart`: danh sách `LineItem`, có thêm, cập nhật và xóa sản phẩm.

Servlet lưu `Cart` vào `HttpSession` với attribute name `cart`, đúng cách làm trong Chapter 7.
