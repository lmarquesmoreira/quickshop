package com.braspag.quickshop.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.braspag.quickshop.R;
import com.braspag.quickshop.holders.CartItemListHolder;
import com.braspag.quickshop.models.CartItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lmmoreira on 8/18/2017.
 */

public class OfferItemListAdapter extends RecyclerView.Adapter<CartItemListHolder> {

    List<CartItem> _cartItems;

    public OfferItemListAdapter(List<CartItem> orders) {
        if (orders == null)
            orders = new ArrayList<>();
        _cartItems = orders;
    }

    public void swap(List<CartItem> orders) {
        if (_cartItems != null) {
            _cartItems.clear();
            _cartItems.addAll(orders);
        } else {
            _cartItems = orders;
        }
        notifyDataSetChanged();
    }

    @Override
    public CartItemListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
        inflate(R.layout.card_offer_item, parent, false);
        CartItemListHolder holder = new CartItemListHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(CartItemListHolder holder, int position) {
        CartItem model = _cartItems.get(position);
        holder.setDataOnCard(model);
    }

    @Override
    public int getItemCount() {
        return _cartItems.size();
    }
}
