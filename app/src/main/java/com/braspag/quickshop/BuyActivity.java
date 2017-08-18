package com.braspag.quickshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Button;
import android.widget.TextView;

import com.braspag.quickshop.adapters.OfferItemListAdapter;
import com.braspag.quickshop.api.async.IResultAsync;
import com.braspag.quickshop.api.async.OAuthAsync;
import com.braspag.quickshop.api.async.OfferAsync;
import com.braspag.quickshop.api.async.ResultAsyncModel;
import com.braspag.quickshop.models.Cart;
import com.braspag.quickshop.models.CartItem;
import com.braspag.quickshop.models.OAuthModel;

import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

public class BuyActivity extends AppCompatActivity {

    private Cart currentModel;
    private TextView offerTitle;
    private TextView offerAmount;
    private RecyclerView offer_list_recycleView;
    private OfferItemListAdapter listAdapter;
    private Button btn_pay;

    private final IResultAsync<Cart> cartResultCallback = new IResultAsync<Cart>() {
        @Override
        public void send(ResultAsyncModel<Cart> result) {
            Cart model  = result.getModel();
            offerTitle.setText(model.getLabel());
            String price =  NumberFormat
                    .getCurrencyInstance(new Locale("pt", "BR"))
                    .format((calculateAmount(model.getCartItems())));
            offerAmount.setText("R$: " + price);

            offer_list_recycleView.setLayoutManager(new
                    StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
            listAdapter = new OfferItemListAdapter(model.getCartItems());
            offer_list_recycleView.setAdapter(listAdapter);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        Intent intent = getIntent();
        String message = intent.getStringExtra("OFFER_URL");

        initComponents();
        getAuthToken();
    }

    private void initComponents(){
        offerTitle = (TextView) findViewById(R.id.offer_title);
        offerAmount = (TextView)findViewById(R.id.offer_amount);
        offer_list_recycleView = (RecyclerView) findViewById(R.id.offer_recyclerView);
        btn_pay = (Button) findViewById(R.id.btn_pay);
    }

    private double calculateAmount(List<CartItem> items){
        double amount = 0.0;
        for (CartItem it : items){
            amount += (it.getPrice().getAmount() * it.getQuantity());
        }
        return amount / 100;
    }

    private void getAuthToken() {
        OAuthAsync request = new OAuthAsync(new IResultAsync<OAuthModel>() {
            @Override
            public void send(ResultAsyncModel<OAuthModel> result) {
                getOffer("", result.getModel());
            }
        });
        request.execute();
    }

    private void getOffer(String offerUrl, OAuthModel model) {
        new OfferAsync(cartResultCallback,
                model.getAccessToken(),
                50)
                .execute();
    }
}
