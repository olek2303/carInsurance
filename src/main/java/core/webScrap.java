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
                .userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36")
                .timeout(5000)
                .cookie("cookiename", "val234")
                .referrer("https://google.com")
                .get();

        Elements brands = document.select("div.popular-name");
        for(Element e : brands.select("a.make")) {
            String s = e.select("span").text();
            System.out.println(s);
        }
    }
}
