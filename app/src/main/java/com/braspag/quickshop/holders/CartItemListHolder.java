package com.braspag.quickshop.holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.braspag.quickshop.R;
import com.braspag.quickshop.models.CartItem;

/**
 * Created by lmmoreira on 8/18/2017.
 */

public class CartItemListHolder extends RecyclerView.ViewHolder {

    private TextView title;
    private Context context;

    public CartItemListHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        title = itemView.findViewById(R.id.cart_item_title);
    }

    public void setDataOnCard(final CartItem model) {
        title.setText(model.getProductName());
//
//        try {
//            Picasso.with(context).load(model.ImageUrl).into(imageView);
//        } catch (Exception e) {
//            Log.d(RestGenerator.LogAPP, e.getLocalizedMessage());
//        }

    }
}
