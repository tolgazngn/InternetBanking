package org.internetbanking.ui.main;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Main{
	public static void main(String[] args) {
		try {
			Document document = Jsoup.connect("https://www.bloomberght.com/doviz").get();

			Elements usdTry = document.select("div.widget-economy-data.type1 > div#HeaderMarkets > a#dolar.marketsCol > li.live-dolar.up.upGreen > small.value.LastPrice");

			String usd = usdTry.text();

			System.out.println(usd);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}