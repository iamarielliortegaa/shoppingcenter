package com.shopping.data;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

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

    }

    @Test
    public void testProductSave() throws IOException
    {

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
