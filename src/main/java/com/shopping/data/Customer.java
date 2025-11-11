package com.shopping.data;

public class Customer
{
    private int id;
    private String name;
    private String address;
    private String cardDetail;

    /**
     * Constructor
     */
    public Customer() {

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
}
