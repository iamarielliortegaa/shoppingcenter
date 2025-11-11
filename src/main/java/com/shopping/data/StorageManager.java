package com.shopping.data;

import java.io.*;
import java.util.ArrayList;

public class StorageManager {

    public final String PRODUCT_FILE_NAME = "product.csv";
    public final String CUSTOMER_FILE_NAME = "customer.csv";

    public StorageManager() throws IOException
    {
        File productFile = new File(PRODUCT_FILE_NAME);

        if (!productFile.exists())
        {
            productFile.createNewFile();
        }

        File customerFile = new File(CUSTOMER_FILE_NAME);

        if (!customerFile.exists())
        {
            customerFile.createNewFile();
        }
    }

    public void SaveProduct(ArrayList<Product> products) throws IOException
    {
        FileWriter fWriter = new FileWriter(PRODUCT_FILE_NAME);
        PrintWriter writer = new PrintWriter(fWriter);

        for (Product product : products) {
            writer.printf("%d,%s,%.2f,%d\n", product.getId(), product.getName(), product.getPrice(), product.getStock());
        }

        writer.close();
    }

    public void SaveCustomer(ArrayList<Customer> customers) throws IOException
    {
        FileWriter fWriter = new FileWriter(CUSTOMER_FILE_NAME);
        PrintWriter writer = new PrintWriter(fWriter);

        for (Customer customer : customers) {
            writer.printf("%d,%s,%s,%s\n", customer.getId(), customer.getName(), customer.getAddress(), customer.getCardDetail());
        }

        writer.close();
    }

    public ArrayList<Product> LoadProduct( ) throws IOException
    {
        ArrayList<Product> products = new ArrayList<Product>();

        FileReader fReader = new FileReader(new File(PRODUCT_FILE_NAME));
        BufferedReader reader = new BufferedReader(fReader);

        String line;
        while ((line = reader.readLine()) != null) {

            if(line.contains(",")) {
                String[] parts = line.split(",");

                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);
                int stock = Integer.parseInt(parts[3]);
                products.add(new Product(id, name, price, stock));
            }
        }

        reader.close();

        return products;
    }

    public ArrayList<Customer> LoadCustomer() throws IOException
    {
        ArrayList<Customer> customers = new ArrayList<Customer>();

        FileReader fReader = new FileReader(new File(CUSTOMER_FILE_NAME));
        BufferedReader reader = new BufferedReader(fReader);

        String line;
        while ((line = reader.readLine()) != null) {

            if(line.contains(",")) {
                String[] parts = line.split(",");

                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String address = parts[2];
                String card = parts[3];
                customers.add(new Customer(id, name, address, card));
            }
        }

        reader.close();

        return customers;
    }

}
