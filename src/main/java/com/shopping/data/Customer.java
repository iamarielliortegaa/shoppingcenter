package com.shopping.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Customer
{
    private int id;
    private String name;
    private String address;
    private String cardDetail;

    private ArrayList<Product> cartProducts = new ArrayList<>();

    private ObservableList<Product> allcartProducts;

    /**
     * Constructor
     */
    public Customer() {
        allcartProducts = FXCollections.observableArrayList(cartProducts);
    }

    /**
     * Constructor
     *
     * @param  id id tobe set
     * @param  name tobe set
     * @param  address tobe set
     * @param  cardDetail tobe set
     */
    public Customer(int id, String name, String address, String cardDetail) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.cardDetail = cardDetail;

        allcartProducts = FXCollections.observableArrayList(cartProducts);
    }

    /**
     * API to look up id
     *
     * @return look up id
     */
    public int getId() {
        return id;
    }

    /**
     * API to set id
     *
     * @param  id tobe set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * API to look up name
     *
     * @return look up name
     */
    public String getName() {
        return name;
    }

    /**
     * API to set name
     *
     * @param  name tobe set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * API to look up address
     *
     * @return look up address
     */
    public String getAddress() {
        return address;
    }

    /**
     * API to set address
     *
     * @param  address tobe set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * API to look up cardDetail
     *
     * @return look up cardDetail
     */
    public String getCardDetail() {
        return cardDetail;
    }

    /**
     * API to set cardDetail
     *
     * @param  cardDetail tobe set
     */
    public void setCardDetail(String cardDetail) {
        this.cardDetail = cardDetail;
    }

    public void resetCart()
    {
        allcartProducts.clear();
    }

    /**
     * API to add a product to cart
     *
     * @param  product tobe set
     */
    public void addToCart(Product product) {
        allcartProducts.add(product);
    }

    /**
     * API to remove a product from cart
     *
     * @param  product tobe removed
     */
    public void removeFromCart(Product product) {
        allcartProducts.remove(product);
    }

    /**
     * API to look up a list of cart products
     *
     * @return list of cart products
     */
    public ObservableList<Product> getCartProducts()
    {
        return allcartProducts;
    }
}
