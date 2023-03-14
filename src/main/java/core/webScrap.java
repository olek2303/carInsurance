package core;

public class webScrap extends calculator{
    private static String url = "https://www.autocentrum.pl/dane-techniczne/";

    public static void getInfo(String brand, String model, String gen) {
        brand.toLowerCase();
        model.toLowerCase();
        gen.toLowerCase();
        url += (brand + "/");
        url += (model + "/");
        url += (gen + "/");



    }
}
