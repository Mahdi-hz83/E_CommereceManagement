package ECommerceManagementSystem;

import Persons.Customer;
import Products.Order;
import Products.Product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ECommerceManagementSystem {
    private List<Customer> customers;
    private List<Product> products;
    private List<Order> orders;

    public ECommerceManagementSystem(List<Customer> customers, List<Product> products, List<Order> orders) {
        this.customers = new ArrayList<>();
        this.products = new ArrayList<>();
        this.orders = new ArrayList<>();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void createOrder(String orderId , Customer customer , LocalDateTime orderTime){
        Order order = new Order(orderId , customer , orderTime);
        orders.add(order);
    }

    public void updateOrderStatus(String orderId , String status){
        for (Order order : orders) {
            if(order.getOrderId().equals(orderId)){
                order.setStatus(status);
                break;
            }
        }
    }

    public Customer getCustomerById(String customerId){
        for (Customer customer : customers) {
            if(customer.getCustomerId().equals(customerId)){
                return customer;
            }
        }
        return null;
    }

    public Product getProductById(String productId){
        for (Product product : products) {
            if(product.getProductId().equals(productId)){
                return product;
            }
        }
        return null;
    }

    public Order getOrderById(String orderId){
        for (Order order : orders) {
            if(order.getOrderId().equals(orderId)){
                return order;
            }
        }
        return null;
    }

    public void addProductToOrder(String orderId , Product product , int quantity){
        for (Order order : orders) {
            if(order.getOrderId().equals(orderId)){
                order.addProduct(product, quantity);
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "E-Commerce Management System: " + "\nCustomers: " + customers + "\nProducts: " + products + "\nOrders: " + orders;
    }
}
