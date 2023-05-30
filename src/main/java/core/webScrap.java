package core;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.util.Vector;

import org.jsoup.nodes.Element;
import org.jsoup.select.*;

public class webScrap { // klasa pobierajaca dane ze strony http://auto-centrum.pl/
    public static void main(String[] args) throws IOException {
        Vector<String> b = getGenerations("audi", "a3");
        Vector<String> b2 = getType("audi", "a3", "8l");
        Vector<String> b1 = getEngines("audi", "a3", "hatchback", "8l");
    }

    public static Vector<String> getBrandsModel() throws IOException {
        String url = "https://www.autocentrum.pl/dane-techniczne/";
        Document document = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                .get();

        Elements brands = document.select("div.popular-make");
        Vector<String> b = new Vector<>();
        for(Element e : brands.select("a.make")) {
            String s = e.select("span").text();
            s = s.replace(" ", "-").toLowerCase();
            b.add(s);
            //System.out.println(s);
        }
        //System.out.print("\nBrands: " + brands.size() + " " + b.size());
        return b;
    }

    public static Vector<String> getModels(String brand) throws IOException {
        String url = "https://www.autocentrum.pl/dane-techniczne/";
        url = url + brand + "/";
        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                .get();
        Elements models = doc.select("a.big-box");
        Vector<String> m = new Vector<>();
        for(Element e : models) {
            String s = e.select("h2.name-of-the-car").text();
            s = s.replace(" ", "-").toLowerCase();
            m.add(s);
            //System.out.println(s);
        }
        //System.out.println(m.size());
        return m;
    }

    public static Vector<String> getGenerations(String brand, String model) throws IOException {
        String url = "https://www.autocentrum.pl/dane-techniczne/";
        url = url + brand + "/" + model + "/";
        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                .get();
        Elements generations = doc.select("a.car-selector-box");
        Vector<String> g = new Vector<>();
        for(Element e : generations) {
            String s = e.select("h2").text();
            s = s.replaceAll("\\s.*", "").toLowerCase();
            //System.out.println(s);
            g.add(s);
        }
        //System.out.println("Generations: " + g.size());
        return g;
    }

    public static Vector<String> getType(String brand, String model, String gen) throws IOException {
        String url = "https://www.autocentrum.pl/dane-techniczne/";
        url = url + brand + "/" + model + "/" + gen + "/";
        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                .get();
        Elements generations = doc.select("a.car-selector-box");
        Vector<String> e = new Vector<>();
        for(Element el : generations) {
            String s = el.select("h2.name-of-the-car").text();
            s = s.replaceAll("\\p{P}.*","").toLowerCase();
            s = s.replaceAll(" ", "-");
            s = s.substring(0,s.length()-1);
            //System.out.println(s);
            e.add(s);
        }
       // System.out.println("Types: " + e.size());
        return e;
    }

    public static Vector<String> getEngines(String brand, String model, String type, String gen) throws IOException {
        String url = "https://www.autocentrum.pl/dane-techniczne/";
        if(type != null)
            type = type + "/";
        url = url + brand + "/" + model + "/" + gen + "/" + type ;
        System.out.println(url);
        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                .get();
        Elements generations = doc.select("a.engine-link");
        Vector<String> e = new Vector<>();
        for(Element el : generations) {
            String s = el.text();
            //System.out.println(s);
            e.add(s);
        }
        System.out.println("Engines: " + e.size());
        return e;
    }
}
