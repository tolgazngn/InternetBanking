package org.internetbanking.business.service.user;

import org.internetbanking.business.entity.exchangerates.ExchangeRates;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class LiveExchangeRateService {
    public ExchangeRates getLiveExchangeRateService(){
        ExchangeRates exchangeRates = new ExchangeRates();

        try {
            Document document = Jsoup.connect("https://www.bloomberght.com/doviz").get();

            String usdTry = document.select("small.class=value LastPrice").text();

            System.out.println(usdTry);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return exchangeRates;
    }
}
