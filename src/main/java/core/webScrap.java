package core;

import org.htmlunit.html.DomText;
import org.htmlunit.html.HtmlPage;
import org.htmlunit.WebClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class webScrap {
    private static String url = "https://www.autocentrum.pl/dane-techniczne/";
    private static WebClient client = new WebClient();

    public static void main(String[] args) throws IOException {
        String z = "";
        List<String> s  = getInfoBrands(z);
    }

    public static List<String> getInfoBrands(String b) throws IOException {
        HtmlPage page = client.getPage(url);
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
        String xpath = "//span[@class='make']";
        List<DomText> brands = page.getByXPath(xpath);
        List<String> s = new ArrayList<>();
        for (DomText dt : brands) {
            String text = dt.toString();
            if(text != null && text.length() > 0) {
                if(Character.isDigit(text.charAt(0))) {
                    s.add(text);
                    System.out.println(text);
                }
            }
        }
        url += (b + "/");
        return s;
    }

    public static void getInfoModel() {

    }
}
