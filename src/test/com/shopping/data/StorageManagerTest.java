package com.shopping.data;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageManagerTest
{
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
    public void testConstructor() throws IOException
    {
        StorageManager storageManager = new StorageManager();

        assertTrue(new File(PRODUCT_FILE_NAME).exists(), "Product file should be created");
        assertTrue(new File(CUSTOMER_FILE_NAME).exists(), "Customer file should be created");
    }

    @Test
    public void testProductSave() throws IOException
    {
        StorageManager storageManager = new StorageManager();

        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product(1, "Laptop", 49999.99, 5));
        products.add(new Product(2, "Mouse", 999.00, 20));

        storageManager.SaveProduct(products);

        FileReader fReader = new FileReader(new File(PRODUCT_FILE_NAME));
        BufferedReader reader = new BufferedReader(fReader);

        String line  = reader.readLine();
        assertEquals("1,Laptop,49999.99,5", line,"Product file should contain first product.");

        line  = reader.readLine();
        assertEquals("2,Mouse,999.00,20", line,"Product file should contain second product.");
    }

    @Test
    public void testCustomerSave() throws IOException
    {
        StorageManager storageManager = new StorageManager();

        ArrayList<Customer> customers = new ArrayList<>();
        customers.add(new Customer(1, "John Cena", "45606 Darcel Station", "1234-5678-8765-4321"));
        customers.add(new Customer(2, "Will Smith", "247 Kertzmann Walks", "8765-4321-1234-5678"));

        storageManager.SaveCustomer(customers);

        FileReader fReader = new FileReader(new File(CUSTOMER_FILE_NAME));
        BufferedReader reader = new BufferedReader(fReader);

        String line  = reader.readLine();
        assertEquals("1,John Cena,45606 Darcel Station,1234-5678-8765-4321", line,"Product file should contain first product.");

        line  = reader.readLine();
        assertEquals("2,Will Smith,247 Kertzmann Walks,8765-4321-1234-5678", line, "Product file should contain second product.");
    }

    @Test
    public void testLoadProduct() throws IOException {
        FileWriter fWriter = new FileWriter(PRODUCT_FILE_NAME);
        PrintWriter writer = new PrintWriter(fWriter);
        writer.println("1,Laptop,49999.99,5");
        writer.println("2,Mouse,999.00,20");
        writer.close();

        StorageManager storageManager = new StorageManager();

        ArrayList<Product> products = storageManager.LoadProduct();

        assertEquals(2, products.size(), "File should contains 2 products.");

        assertEquals(1, products.get(0).getId(), "First product ID should be 1.");
        assertEquals("Laptop", products.get(0).getName(), "First product name should be Laptop.");
        assertEquals(49999.99, products.get(0).getPrice(), "First product price should be 49999.99.");
        assertEquals(5, products.get(0).getStock(), "First product stock should be 5.");

        assertEquals(2, products.get(1).getId(), "First product ID should be 2.");
        assertEquals("Mouse", products.get(1).getName(), "First product name should be Mouse.");
        assertEquals(999.00, products.get(1).getPrice(), "First product price should be 999.00.");
        assertEquals(20, products.get(1).getStock(), "First product stock should be 20.");
    }

    @Test
    public void testLoadCustomer() throws IOException {
        FileWriter fWriter = new FileWriter(CUSTOMER_FILE_NAME);
        PrintWriter writer = new PrintWriter(fWriter);
        writer.println("1,John Cena,45606 Darcel Station,1234-5678-8765-4321");
        writer.println("2,Will Smith,247 Kertzmann Walks,8765-4321-1234-5678");
        writer.close();

        StorageManager storageManager = new StorageManager();

        ArrayList<Customer> customers = storageManager.LoadCustomer();

        assertEquals(2, customers.size(), "File should contains 2 customers.");

        assertEquals(1, customers.get(0).getId(), "First customer ID should be 1.");
        assertEquals("John Cena", customers.get(0).getName(), "First customer name should be John Cena.");
        assertEquals("45606 Darcel Station", customers.get(0).getAddress(), "First customer address should be 45606 Darcel Station.");
        assertEquals("1234-5678-8765-4321", customers.get(0).getCardDetail(), "First customer card detail should be 1234-5678-8765-4321.");

        assertEquals(2, customers.get(1).getId(), "First customer ID should be 2.");
        assertEquals("Will Smith", customers.get(1).getName(), "First customer name should be Will Smith.");
        assertEquals("247 Kertzmann Walks", customers.get(1).getAddress(), "First customer address should be 247 Kertzmann Walks.");
        assertEquals("8765-4321-1234-5678", customers.get(1).getCardDetail(), "First customer card detail should be 8765-4321-1234-5678.");
    }

    private void cleanUp()
    {
        File productFile = new File(PRODUCT_FILE_NAME);

        if (!productFile.exists())
        {
            productFile.delete();
        }

        File customerFile = new File(CUSTOMER_FILE_NAME);

        if (!customerFile.exists())
        {
            customerFile.delete();
        }
    }
}
