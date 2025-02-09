package com.example.demo.example.model;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CurrencyExchangeResponseModel {
    public String From;
    public String To;
    public double Amount;
    public double ConvertedAmount;


}
