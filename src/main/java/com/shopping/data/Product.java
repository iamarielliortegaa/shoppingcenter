package com.shopping.data;

public class Product
{
    private int id;
    private String name;
    private double price;
    private int stock;

    /**
     * Empty Constructor
     */
    public Product() {

    }

    /**
     * Constructor
     *
     * @param  id tobe set
     * @param  name tobe set
     * @param  price tobe set
     * @param  stock tobe set
     */
    public Product(int id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
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
     * API to look up price
     *
     * @return look up price
     */
    public double getPrice() {
        return price;
    }

    /**
     * API to set price
     *
     * @param  price price tobe set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * API to look up stock
     *
     * @return look up stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * API to set stock
     *
     * @param  stock tobe set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
}
