package ECommerceManagementSystem;

import Persons.Customer;
import Products.Order;
import Products.Product;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ECommerceManagementSystemTest {
    private ECommerceManagementSystem ems;
    private Customer customer1 , customer2;
    private Product product1 , product2;
    private LocalDateTime orderTime;

    @Before
    public void setUp(){
        this.ems = new ECommerceManagementSystem();

        customer1 = new Customer("Alice", "alice@example.com", "1234567890", "C001", "123 Elm Street");
        customer2 = new Customer("Bob", "bob@example.com", "0987654321", "C002", "456 Oak Avenue");
        ems.addCustomer(customer1);
        ems.addCustomer(customer2);

        product1 = new Product("P001", "Laptop", 1200.00, 10);
        product2 = new Product("P002", "Smartphone", 800.00, 20);
        ems.addProduct(product1);
        ems.addProduct(product2);

        orderTime = LocalDateTime.of(2024, 7, 25, 10, 0);
    }

    @Test
    public void testAddCustomer(){
        Customer customer3 = new Customer("Charlie", "charlie@example.com", "5555555555", "C003", "789 Pine Road");
        ems.addCustomer(customer3);
        assertEquals(customer3, ems.getCustomerById("C003"));
    }

    @Test
    public void testAddProduct(){
        Product product3 = new Product("P003", "Tablet", 600.00, 15);
        ems.addProduct(product3);
        assertEquals(product3, ems.getProductById("P003"));
    }

    @Test
    public void testCreateOrder(){
        ems.createOrder("O001", customer1, orderTime);
        Order order = ems.getOrderById("O001");
        assertEquals("Pending", order.getStatus());
    }

    @Test
    public void testAddProductToOrder() {
        ems.createOrder("O001", customer1, orderTime);
        ems.addProductToOrder("O001", product1, 2);
        Order order = ems.getOrderById("O001");
        Map<Product, Integer> products = order.getProducts();
        assertTrue(products.containsKey(product1));
        assertEquals((Integer) 2, products.get(product1));
    }


    @Test
    public void testUpdateOrderStatus() {
        ems.createOrder("O001", customer1, orderTime);
        ems.updateOrderStatus("O001", "Shipped");
        Order order = ems.getOrderById("O001");
        assertEquals("Shipped", order.getStatus());
    }

    @Test
    public void testGetCustomerById() {
        Customer customer = ems.getCustomerById("C001");
        assertNotNull(customer);
        assertEquals("Alice", customer.getName());
    }

    @Test
    public void testGetProductById() {
        Product product = ems.getProductById("P001");
        assertNotNull(product);
        assertEquals("Laptop", product.getProductName());
    }

    @Test
    public void testGetOrderById() {
        ems.createOrder("O001", customer1, orderTime);
        Order order = ems.getOrderById("O001");
        assertEquals("O001", order.getOrderId());
    }

    @Test
    public void testOrderProductQuantityReduction() {
        ems.createOrder("O001", customer1, orderTime);
        ems.addProductToOrder("O001", product1, 2);
        Product product = ems.getProductById("P001");
        assertEquals(8, product.getStock());
    }

    @Test
    public void testOrderMultipleProducts() {
        ems.createOrder("O001", customer1, orderTime);
        ems.addProductToOrder("O001", product1, 2);
        ems.addProductToOrder("O001", product2, 3);
        Order order = ems.getOrderById("O001");
        Map<Product, Integer> products = order.getProducts();
        assertEquals(2, products.size());
        assertTrue(products.containsKey(product1));
        assertTrue(products.containsKey(product2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductOutOfStock() {
        ems.createOrder("O001", customer1, orderTime);
        ems.addProductToOrder("O001", product1, 11);
    }

    @Test
    public void testCustomerNotFound() {
        Customer customer = ems.getCustomerById("C999");
        assertNull(customer);
    }

    @Test
    public void testProductNotFound() {
        Product product = ems.getProductById("P999");
        assertNull(product);
    }

    @Test
    public void testOrderNotFound() {
        Order order = ems.getOrderById("O999");
        assertNull(order);
    }

    @Test
    public void testAddCustomerWithExistingId() {   //Don't add a new customer
        Customer newCustomer = new Customer("Alice 2", "alice2@example.com", "1234567891", "C001", "124 Elm Street");
        ems.addCustomer(newCustomer);
        Customer retrievedCustomer = ems.getCustomerById("C001");
        assertEquals("Alice", retrievedCustomer.getName());
    }

    @Test
    public void testAddProductWithExistingId() {  //Don't add a new product
        Product newProduct = new Product("P001", "Laptop Pro", 1500.00, 5);
        ems.addProduct(newProduct);
        Product retrievedProduct = ems.getProductById("P001");
        assertEquals("Laptop", retrievedProduct.getProductName());
    }
}