package com.braspag.quickshop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.braspag.quickshop.adapters.OfferItemListAdapter;
import com.braspag.quickshop.api.async.IResultAsync;
import com.braspag.quickshop.api.async.OAuthAsync;
import com.braspag.quickshop.api.async.OfferAsync;
import com.braspag.quickshop.api.async.ResultAsyncModel;
import com.braspag.quickshop.api.async.TransactionAsync;
import com.braspag.quickshop.api.models.Seller;
import com.braspag.quickshop.api.models.TransactionModel;
import com.braspag.quickshop.models.Cart;
import com.braspag.quickshop.models.CartItem;
import com.braspag.quickshop.models.OAuthModel;

import org.jetbrains.annotations.NotNull;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import cielo.orders.domain.Credentials;
import cielo.orders.domain.Order;
import cielo.sdk.order.OrderManager;
import cielo.sdk.order.payment.PaymentError;
import cielo.sdk.order.payment.PaymentListener;

public class BuyActivity extends AppCompatActivity {
    private static String TAG = "PAYMENT_LISTENER";
    private OrderManager orderManager;
    private cielo.orders.domain.Order currentOrder;
    private Cart currentOffer;
    private TextView offerTitle;
    private TextView offerAmount;
    private RecyclerView offer_list_recycleView;
    private OfferItemListAdapter listAdapter;
    private Button btn_pay;
    private LinearLayout container_progressBar;
    private ScrollView container_rest;

    private final IResultAsync<Cart> cartResultCallback = new IResultAsync<Cart>() {
        @Override
        public void send(ResultAsyncModel<Cart> result) {
            Cart model = result.getModel();
            offerTitle.setText(model.getLabel());
            String price = NumberFormat
                    .getCurrencyInstance(new Locale("pt", "BR"))
                    .format((calculateAmount(model.getCartItems())));
            offerAmount.setText(price);

            offer_list_recycleView.setLayoutManager(new
                    StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
            listAdapter = new OfferItemListAdapter(model.getCartItems());
            offer_list_recycleView.setAdapter(listAdapter);

            currentOffer = model;
            btn_pay.setEnabled(true);

            setVisibility(true);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        Intent intent = getIntent();
        String message = intent.getStringExtra("OFFER_URL");
        int offerId = getOfferId(message);
        initComponents();
        initOrderManager();
        getAuthToken(offerId);
        setVisibility(false);
    }

    private void initComponents() {
        offerTitle = (TextView) findViewById(R.id.offer_title);
        offerAmount = (TextView) findViewById(R.id.offer_amount);
        offer_list_recycleView = (RecyclerView) findViewById(R.id.offer_recyclerView);
        btn_pay = (Button) findViewById(R.id.btn_pay);
        btn_pay.setEnabled(false);
        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makePayment(currentOffer);
            }
        });
        container_progressBar = (LinearLayout) findViewById(R.id.container_progress_bar_buy);
        container_rest = (ScrollView) findViewById(R.id.container_data_offer);
    }

    private void setVisibility(boolean visibility){
        if(visibility == true){
            container_progressBar.setVisibility(View.GONE);
            container_rest.setVisibility(View.VISIBLE);
        }else {
            container_progressBar.setVisibility(View.VISIBLE);
            container_rest.setVisibility(View.GONE);
        }
    }

    private void initOrderManager() {
        Credentials credentials = new Credentials(Constants.LioClientId, Constants.LioClientSecret);
        orderManager = new OrderManager(credentials, this);
        orderManager.bind(this, null);
    }

    private double calculateAmount(List<CartItem> items) {
        double amount = 0.0;
        for (CartItem it : items) {
            amount += (it.getPrice().getAmount() * it.getQuantity());
        }
        return amount / 100;
    }

    private void getAuthToken(final int offerId) {
        OAuthAsync request = new OAuthAsync(new IResultAsync<OAuthModel>() {
            @Override
            public void send(ResultAsyncModel<OAuthModel> result) {
                getOffer(offerId, result.getModel());
            }
        });
        request.execute();
    }

    private void getOffer(int offerId, OAuthModel model) {
        new OfferAsync(cartResultCallback,
                model.getAccessToken(),
                offerId)
                .execute();
    }

    private cielo.orders.domain.Order generateOrder(Cart item) {
        cielo.orders.domain.Order order = orderManager.createDraftOrder(item.getLabel());
        for (CartItem it : item.getCartItems()) {
            order.addItem(it.getSku().getSku(),
                    it.getProductName(),
                    it.getPrice().getAmount(),
                    it.getQuantity(), "1");
        }
        return order;
    }

    public void makePayment(Cart cart) {
        currentOrder = generateOrder(cart);
        orderManager.placeOrder(currentOrder);
        orderManager.checkoutOrder(currentOrder.getId(), new PaymentListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onPayment(@NotNull Order order) {
                currentOrder = order;
                currentOrder.markAsPaid();
                orderManager.updateOrder(currentOrder);
                onSuccessCaptured(BuyActivity.this);
            }

            @Override
            public void onCancel() {
                currentOrder.markAsRejected();
            }

            @Override
            public void onError(@NotNull PaymentError paymentError) {
                currentOrder.markAsRejected();
            }
        });
    }

    public void onSuccessCaptured(final Context context) {
        TransactionAsync transactionAsync = new TransactionAsync(new IResultAsync() {
            @Override
            public void send(ResultAsyncModel result) {

                Toast.makeText(context, "Transação salva com sucesso", Toast.LENGTH_LONG).show();
            }
        });

        TransactionModel model = new TransactionModel()
                .withAmount(currentOrder.getPaidAmount())
                .withOrder(currentOrder)
                .withOffer(currentOffer)
                .withSellers(new ArrayList<Seller>() {{
                    new Seller(3);
                    new Seller(4);
                }});

        transactionAsync.execute(model);
        Toast.makeText(context, "Pagamento realizado com sucesso", Toast.LENGTH_LONG).show();
        finish();
    }

    private int getOfferId(String message) {
        String[] splited = message.split("/");
        try {
            return Integer.parseInt(splited[splited.length - 1]);
        } catch (Exception ex) {

        }
        return -1;
    }
}
