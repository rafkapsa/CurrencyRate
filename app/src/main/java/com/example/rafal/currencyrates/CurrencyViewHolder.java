package com.example.rafal.currencyrates;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Rafal on 2017-05-16.
 */

@Getter
@Setter

public class CurrencyViewHolder extends RecyclerView.ViewHolder{
    private TextView symbol;
    private TextView name;
    private TextView rate;

    public CurrencyViewHolder(View itemView) {
        super(itemView);
        symbol = (TextView) itemView.findViewById(R.id.symbol);
        name = (TextView) itemView.findViewById(R.id.currency);
        rate = (TextView) itemView.findViewById(R.id.mid);
    }


}
