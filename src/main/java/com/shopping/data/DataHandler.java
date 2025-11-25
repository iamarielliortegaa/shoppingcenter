package com.shopping.data;

import com.shopping.data.Customer;
import com.shopping.data.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.ArrayList;

public class DataHandler {

    private ObservableList<Customer> allCustomers;
    private ObservableList<Product> allProducts;

    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Product> products = new ArrayList<>();

    private StorageManager storage = new StorageManager();

    private boolean allowedToBeRemoved;

    private Customer selectedCustomer;


    /**
     * Constructor
     */
    public DataHandler(boolean allowedToBeRemoved) throws IOException
    {
        this.allowedToBeRemoved = allowedToBeRemoved;

        products = storage.LoadProduct();
        customers = storage.LoadCustomer();

        allCustomers = FXCollections.observableArrayList(customers);
        allProducts = FXCollections.observableArrayList(products);

        if (!allCustomers.isEmpty()) {
            selectedCustomer = allCustomers.get(0);
        }
    }

    /**
     * API to get selected Customer
     *
     * @return selectedCustomer
     */
    public Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    /**
     * API to set selected Customer
     *
     * @param  selectedCustomer tobe added
     */
    public void setSelectedCustomer(Customer selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }

    /**
     * API to add Customer
     *
     * @param  newCustomer tobe added
     */
    public void addCustomer(Customer newCustomer) throws IOException
    {
        if(newCustomer.getId() == 0) {
            newCustomer.setId(allCustomers.size() + 1);
        }
        allCustomers.add(newCustomer);

        storage.SaveCustomer(customers);
    }

    /**
     * API to add product
     *
     * @param  newProduct new product tobe added
     */
    public void addProduct(Product newProduct) throws IOException
    {
        if(newProduct.getId() == 0) {
            newProduct.setId(allProducts.size() + 1);
        }
        allProducts.add(newProduct);

        storage.SaveProduct(products);
    }

    public boolean getAllowedToBeRemoved()
    {
        return this.allowedToBeRemoved;
    }

    /**
     * API to delete a Customer
     *
     * @param  selectedCustomer Customer to be deleted
     *
     * @return status of deletion
     */
    public boolean deleteCustomer(Customer selectedCustomer) throws IOException
    {
        if(!this.allowedToBeRemoved)
        {
            return false;
        }

        boolean result = allCustomers.remove(selectedCustomer);

        storage.SaveCustomer(customers);

        return result;
    }

    /**
     * API to delete a product
     *
     * @param  selectedProduct product to be deleted
     *
     * @return status of deletion
     */
    public boolean deleteProduct(Product selectedProduct) throws IOException
    {
        if(!this.allowedToBeRemoved)
        {
            return false;
        }

        boolean result =  allProducts.remove(selectedProduct);

        storage.SaveProduct(products);

        return result;
    }

    /**
     * API to look up a list of Customers
     *
     * @return list of Customers
     */
    public ObservableList<Customer> getAllCustomers()
    {
        return allCustomers;
    }

    /**
     * API to look up a list of products
     *
     * @return list of products
     */
    public ObservableList<Product> getAllProducts()
    {
        return allProducts;
    }

}
