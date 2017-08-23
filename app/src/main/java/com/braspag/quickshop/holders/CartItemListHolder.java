package com.braspag.quickshop.holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.braspag.quickshop.R;
import com.braspag.quickshop.api.RestGenerator;
import com.braspag.quickshop.models.CartItem;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by lmmoreira on 8/18/2017.
 */

public class CartItemListHolder extends RecyclerView.ViewHolder {

    private TextView title;
    private TextView price;
    private TextView quantity;
    private ImageView image;
    private Context context;

    public CartItemListHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        title = itemView.findViewById(R.id.cart_item_title);
        price = itemView.findViewById(R.id.cart_item_price);
        image = itemView.findViewById(R.id.cart_item_image);
        quantity = itemView.findViewById(R.id.cart_item_quantity);
    }

    public void setDataOnCard(final CartItem model) {
        title.setText(model.getProductName());
        price.setText(NumberFormat
                .getCurrencyInstance(new Locale("pt", "BR"))
                .format(model.getPrice().getAmount() / 100));
        quantity.setText("Quantidade: " +model.getQuantity());
        try {
            Picasso.with(context).load(model.getSku().getImages().get(0)).into(image);
        } catch (Exception e) {
            Log.d(RestGenerator.LogAPP, e.getLocalizedMessage());
        }
    }
}
