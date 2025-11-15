package com.shopping.data;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataHandlerTest {

    public final String PRODUCT_FILE_NAME = "product.csv";
    public final String CUSTOMER_FILE_NAME = "customer.csv";

    @BeforeEach
    public void setUp() throws IOException {
        cleanUp();
    }

    @AfterEach
    public void tearDown() throws IOException {
        cleanUp();
    }

    @Test
    public void testConstructor_withAllowedToBeRemovedTrue() throws IOException
    {
        DataHandler dataHandler = new DataHandler(true);

        assertTrue(dataHandler.getAllowedToBeRemoved(), "AllowedToBeRemoved should return true.");

        assertTrue(new File(PRODUCT_FILE_NAME).exists(), "Product file should be created");
        assertTrue(new File(CUSTOMER_FILE_NAME).exists(), "Customer file should be created");
    }

    @Test
    public void testConstructor_withAllowedToBeRemovedFalse() throws IOException
    {
        DataHandler dataHandler = new DataHandler(false);

        assertFalse(dataHandler.getAllowedToBeRemoved(), "AllowedToBeRemoved should return false.");

        assertTrue(new File(PRODUCT_FILE_NAME).exists(), "Product file should be created");
        assertTrue(new File(CUSTOMER_FILE_NAME).exists(), "Customer file should be created");
    }

    @Test
    public void addProduct_WithOneProductShouldBeAdded() throws IOException
    {
        DataHandler dataHandler = new DataHandler(false);

        dataHandler.addProduct(new Product(1, "Laptop", 49999.99, 5));

        ObservableList<Product> products = dataHandler.getAllProducts();

        assertEquals(1, products.size(), "File should contains 1 products.");

        assertEquals(1, products.get(0).getId(), "First product ID should be 1.");
        assertEquals("Laptop", products.get(0).getName(), "First product name should be Laptop.");
        assertEquals(49999.99, products.get(0).getPrice(), "First product price should be 49999.99.");
        assertEquals(5, products.get(0).getStock(), "First product stock should be 5.");
    }

    @Test
    public void addCustomer_WithOneCustomerShouldBeAdded() throws IOException
    {
        DataHandler dataHandler = new DataHandler(false);

        dataHandler.addCustomer(new Customer(1, "John Cena", "45606 Darcel Station", "1234-5678-8765-4321"));

        ObservableList<Customer> customers = dataHandler.getAllCustomers();

        assertEquals(1, customers.size(), "File should contains 1 customers.");

        assertEquals(1, customers.get(0).getId(), "First customer ID should be 1.");
        assertEquals("John Cena", customers.get(0).getName(), "First customer name should be John Cena.");
        assertEquals("45606 Darcel Station", customers.get(0).getAddress(), "First customer address should be 45606 Darcel Station.");
        assertEquals("1234-5678-8765-4321", customers.get(0).getCardDetail(), "First customer card detail should be 1234-5678-8765-4321.");
    }

    @Test
    public void deleteProduct_WithOneProductShouldBeAdded() throws IOException
    {
        DataHandler dataHandler = new DataHandler(true);

        Product product = new Product(1, "Laptop", 49999.99, 5);
        dataHandler.addProduct(product);

        dataHandler.deleteProduct(product);

        ObservableList<Product> products = dataHandler.getAllProducts();

        assertEquals(0, products.size(), "File should contains 0 products.");
    }

    @Test
    public void deleteCustomer_WithOneCustomerShouldBeAdded() throws IOException
    {
        DataHandler dataHandler = new DataHandler(true);

        Customer customer = new Customer(1, "John Cena", "45606 Darcel Station", "1234-5678-8765-4321");
        dataHandler.addCustomer(customer);

        dataHandler.deleteCustomer(customer);

        ObservableList<Customer> customers = dataHandler.getAllCustomers();

        assertEquals(0, customers.size(), "File should contains 0 customers.");
    }

    @Test
    public void deleteProduct_withAllowedToBeRemovedFalse_WithOneProductShouldBeAdded() throws IOException
    {
        DataHandler dataHandler = new DataHandler(false);

        Product product = new Product(1, "Laptop", 49999.99, 5);
        dataHandler.addProduct(product);

        boolean removed = dataHandler.deleteProduct(product);

        ObservableList<Product> products = dataHandler.getAllProducts();

        assertFalse(removed, "Removed status should be false");

        assertEquals(1, products.size(), "File should contains 1 products.");
    }

    @Test
    public void deleteCustomer_withAllowedToBeRemovedFalse_WithOneCustomerShouldBeAdded() throws IOException
    {
        DataHandler dataHandler = new DataHandler(false);

        Customer customer = new Customer(1, "John Cena", "45606 Darcel Station", "1234-5678-8765-4321");
        dataHandler.addCustomer(customer);

        boolean removed = dataHandler.deleteCustomer(customer);

        ObservableList<Customer> customers = dataHandler.getAllCustomers();

        assertFalse(removed, "Removed status should be false");

        assertEquals(1, customers.size(), "File should contains 1 customers.");
    }

    private void cleanUp()
    {
        File productFile = new File(PRODUCT_FILE_NAME);

        if (productFile.exists())
        {
            productFile.delete();
        }

        File customerFile = new File(CUSTOMER_FILE_NAME);

        if (customerFile.exists())
        {
            customerFile.delete();
        }
    }
}
