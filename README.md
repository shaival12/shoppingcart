# shoppingcart
Shopping cart implementation using H2 db, Spring boot JPA


Swagger url :
http://localhost:8082/swagger-ui.html#/cart45controller

 1) Adding newCartItem, cart  (POST)
http://localhost:8082/api/shop/{cartid}/{productid}/{quanitity}
http://localhost:8082/api/shop/1/5/2

2) Removing newCartItem, cart  (DELETE)
http://localhost:8082/api/shop/{cartid}/{productid}/{quanitity}
http://localhost:8082/api/shop/1/5/2

3) View Cart, cart  (GET)
http://localhost:8082/api/shop/{cartid}
http://localhost:8082/api/shop/cart/1

4) Get all products (GET)
http://localhost:8082/api/shop/products
