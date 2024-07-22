package Persons;

public class Customer extends Person {
    private String customerId;
    private String address;

    public Customer(String name , String email , String phoneNumber , String customerId , String address) {
        super(name , email , phoneNumber);
        this.customerId = customerId;
        this.address = address;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return super.toString() + ", Customer ID: " + customerId + ", Address: " + address;
    }
}
