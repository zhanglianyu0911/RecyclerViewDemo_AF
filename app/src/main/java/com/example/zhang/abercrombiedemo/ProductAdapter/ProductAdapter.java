package com.example.zhang.abercrombiedemo.ProductAdapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhang.abercrombiedemo.Activity.WebViewActivity;
import com.example.zhang.abercrombiedemo.Items.ProductItem;
import com.example.zhang.abercrombiedemo.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Class for RecyclerView Adapter
 * @author Andrew Zhang
 * @version 1.0
 * @since 2016-10-31
 */

public class ProductAdapter extends   RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private ArrayList<ProductItem> productItems;
    Context context;
    private int focusItem;

    public ProductAdapter(ArrayList<ProductItem> productItems, Context context)
    {
        this.productItems = productItems;
        this.context = context;
    }
    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items,parent,false);

        ProductViewHolder holder = new ProductViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position)
    {
        ProductItem productItem = productItems.get(position);

        holder.itemView.setSelected(focusItem==position);
        holder.getAdapterPosition();

        holder.title.setText(productItem.getTitle());


        //check each item, if empty invisible
        if(productItem.getTopDescription().equals(""))
        {
            holder.topDescription.setVisibility(View.GONE);
        }
        else
        {
            holder.topDescription.setVisibility(View.VISIBLE);
            holder.topDescription.setText(productItem.getTopDescription());
        }

        if(productItem.getPromoMessage().equals(""))
        {
            holder.promoMessage.setVisibility(View.GONE);
        }
        else
        {
            holder.promoMessage.setVisibility(View.VISIBLE);
            holder.promoMessage.setText(productItem.getPromoMessage());
        }

        if(productItem.getBottomDescription().equals("") )
        {
            holder.bottomDescription.setVisibility(View.GONE);
        }
        else
        {
            holder.bottomDescription.setVisibility(View.VISIBLE);
            holder.bottomDescription.setText(Html.fromHtml(productItem.getBottomDescription()));
        }

        if(productItem.getSelectButton_one().equals(""))
        {
            holder.selectButton1.setVisibility(View.GONE);
        }
        else{
            holder.selectButton1.setVisibility(View.VISIBLE);
            holder.selectButton1.setText(productItem.getSelectButton_one());
            holder.butoonLink1 = productItem.getButton_one_link();
        }
        if(productItem.getGetSelectButton_two().equals(""))
        {
            holder.selectButton2.setVisibility(View.GONE);
        }
        else {
            holder.selectButton2.setVisibility(View.VISIBLE);
            holder.selectButton2.setText(productItem.getGetSelectButton_two());
            holder.buttonLink2 = productItem.getButton_two_link();
        }
        Picasso.with(this.context).load(productItem.getBackgroundImage()).into(holder.backgroundImage);
}

    @Override
    public int getItemCount() {
        return productItems.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    /**
     * This is ViewHolder pattern
     */

    public class ProductViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView backgroundImage;
        public TextView topDescription, title, promoMessage, bottomDescription, selectButton1, selectButton2;
        String butoonLink1, buttonLink2;

        public ProductViewHolder(View itemView) {
            super(itemView);

            backgroundImage = (ImageView)itemView.findViewById(R.id.backgroundImage);
            topDescription = (TextView)itemView.findViewById(R.id.topDescription);
            title = (TextView)itemView.findViewById(R.id.title);
            promoMessage = (TextView)itemView.findViewById(R.id.promoMessage);
            bottomDescription =(TextView)itemView.findViewById(R.id.bottomDescription);
            selectButton1 = (TextView)itemView.findViewById(R.id.select_button_a);
            selectButton2 = (TextView)itemView.findViewById(R.id.select_button_b);

            selectButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, WebViewActivity.class);
                    intent.putExtra("webView",butoonLink1);
                    context.startActivity(intent);
                }
            });

            selectButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, WebViewActivity.class);
                    intent.putExtra("webView",buttonLink2);
                    context.startActivity(intent);
                }
            });

            bottomDescription.setMovementMethod(LinkMovementMethod.getInstance());


        }
    }
}
