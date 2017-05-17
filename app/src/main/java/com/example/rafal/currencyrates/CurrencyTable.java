package com.example.rafal.currencyrates;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Rafal on 2017-05-16.
 */

@Getter
@Setter
public class CurrencyTable {

    private String table;
    private String number;
    private String effectiveDate;
    private List<Currency> currencies;
}
