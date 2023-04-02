package core;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import java.io.IOException;

import org.jsoup.nodes.Element;
import org.jsoup.select.*;

public class webScrap {
    public static void main(String[] args) throws IOException {
        getBrandsModel();
    }

    public static void getBrandsModel() throws IOException {
        String url = "https://www.autocentrum.pl/dane-techniczne/";
        Document document = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                .get();

        Elements brands = document.select("div.popular-make");
        for(Element e : brands.select("a.make")) {
            String s = e.select("span").text();
            System.out.println(s);
        }
        System.out.print("\nBrands: " + brands.size());
    }
}
