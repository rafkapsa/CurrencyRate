package com.example.rafal.currencyrates;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.rafal.currencyrates.domain.CurrencyTable;
import com.example.rafal.currencyrates.service.CurrencyProvider;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, CurrencyProvider.CurrencyAsyncTaskListener {
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView tableNumber;
    private TextView tableDate;

    private CurrencyAdapter currencyAdapter;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tableNumber = (TextView) findViewById(R.id.table_number);
        tableDate = (TextView) findViewById(R.id.table_date);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_dark, android.R.color.holo_green_dark, android.R.color.holo_orange_dark, android.R.color.holo_red_dark);

        recyclerView = (RecyclerView)  findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(false);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        currencyAdapter = new CurrencyAdapter();

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(currencyAdapter);

        swipeRefreshLayout.setRefreshing(false);

        onRefresh();
    }

    @Override
    public void onRefresh() {
        new CurrencyProvider(this).execute("A");
    }

    @Override
    public void onDataLoaded(CurrencyTable currencyTable) {
        tableNumber.setText(currencyTable.getNumber());
        tableDate.setText(currencyTable.getEffectiveDate());
        currencyAdapter.setData(currencyTable.getCurrencies());
        swipeRefreshLayout.setRefreshing(false);

    }
}
