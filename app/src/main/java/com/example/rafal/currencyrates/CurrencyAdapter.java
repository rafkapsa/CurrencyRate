package com.example.rafal.currencyrates;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafal on 2017-05-16.
 */

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyViewHolder> {
    private List<Currency> data = new ArrayList<>();

    @Override
    public CurrencyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_currency,parent,false);
        return new CurrencyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CurrencyViewHolder holder, int position) {
        Currency currency = data.get(position);
        holder.getName().setText(currency.getName());
        holder.getSymbol().setText(currency.getSymbol());
        holder.getRate().setText(String.valueOf(currency.getRate()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Currency> data){
        this.data = data;
        notifyDataSetChanged();
    }
}
