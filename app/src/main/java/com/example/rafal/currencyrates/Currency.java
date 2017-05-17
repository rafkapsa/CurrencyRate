package com.example.rafal.currencyrates;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Rafal on 2017-05-16.
 */

@Getter
@Setter
public class Currency {
    private String symbol;
    private String name;
    private double rate;
}
