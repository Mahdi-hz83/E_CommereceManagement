package Products;

import Persons.Customer;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private String orderId;
    private Customer customer;
    private Map<Product, Integer> products;
    private LocalDateTime orderTime;
    private String status;

    public Order(String orderId, LocalDateTime orderTime, Map<Product, Integer> products, Customer customer) {
        this.orderId = orderId;
        this.orderTime = orderTime;
        this.products = new HashMap<>();
        this.customer = customer;
        this.status = "Pending";
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void addProduct(Product product, int quantity) {
        this.products.put(product, quantity);
    }

    @Override
    public String toString() {
        return "Order ID: " + orderId + ", Customer: " + customer.getName() + ", Order Time: " + orderTime + ", Status: " + status + ", Products: " + products;
    }
}
