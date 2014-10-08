package com.harukine.currencyconverter.currencyconverter;

import java.util.List;

/**
 * Created by Jacob on 2014-10-07.
 */
public class CurrencyRates {

    public List<Rate> rates;

    public class Rate {
        public float rate;
        public String from;
        public String to;
    }
}