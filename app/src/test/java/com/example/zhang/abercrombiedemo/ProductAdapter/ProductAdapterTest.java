package com.example.zhang.abercrombiedemo.ProductAdapter;

import android.content.Context;

import com.example.zhang.abercrombiedemo.Items.ProductItem;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by zhang on 10/31/2016.
 */
public class ProductAdapterTest
{
    ProductItem item;
    ProductItem item2;
    private ProductAdapter productAdapter;
    private ArrayList<ProductItem> itemList;

    Context context ;

    @Before
    public void setUp()
    {
        item = new ProductItem();
        item.setTitle("Title");
        item.setBottomDescription("BottomDescription");
        item.setPromoMessage("PromoMessage");
        item.setTopDescription("TopDescription");

        item2 = new ProductItem();
        item2.setBottomDescription("BottomDescription");
        item2.setPromoMessage("PromoMessage");
        item2.setTopDescription("TopDescription");
        item.setTitle("Title");

        itemList = new ArrayList<ProductItem>();
        itemList.add(item);
        itemList.add(item2);
        productAdapter = new ProductAdapter(itemList,context);

    }

    @Test
    public void shouldNotBeNull() throws Exception
    {
        assertNotNull( productAdapter);

    }

    @Test
    public void getCount_shouldReturnProperCount() throws Exception
    {
        assertEquals( productAdapter.getItemCount(), itemList.size() );
    }

    @Test
    public void getItemId_shouldReturnProperItemId() throws Exception
    {
        for ( int index = 0; index < itemList.size(); index++ )
        {
            assertEquals(productAdapter.getItemId( index ),index);
        }
    }

}