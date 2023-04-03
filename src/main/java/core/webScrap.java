package core;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.*;

public class webScrap {
    public static void main(String[] args) throws IOException {
        getModels("audi");
    }

    public static List<String> getBrandsModel() throws IOException {
        String url = "https://www.autocentrum.pl/dane-techniczne/";
        Document document = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                .get();

        Elements brands = document.select("div.popular-make");
        List<String> b = new ArrayList<>();
        for(Element e : brands.select("a.make")) {
            String s = e.select("span").text();
            s = s.replace(" ", "-").toLowerCase();
            b.add(s);
            //System.out.println(s);
        }
        //System.out.print("\nBrands: " + brands.size() + " " + b.size());
        return b;
    }

    public static List<String> getModels(String brand) throws IOException {
        String url = "https://www.autocentrum.pl/dane-techniczne/";
        url = url + brand + "/";
        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                .get();
        Elements models = doc.select("a.big-box");
        List<String> m = new ArrayList<>();
        for(Element e : models) {
            String s = e.select("h2.name-of-the-car").text();
            s = s.replace(" ", "-").toLowerCase();
            m.add(s);
            //System.out.println(s);
        }
        return m;
    }
}
