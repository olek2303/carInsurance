package core;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static core.carData.changeStrings;

public class carDataConverted {
    float[] data; // dane dotyczace jednego samochodu oraz wlasciciela przekonwertowane do postaci flaot
    float[] expectedOut; // oczekiwana cena ubezpieczenia dla danej listy danych DATA

    public carDataConverted(carData c) {
        changeStrings(c);
        Class<?> x = c.getClass();
        Field[] fields = x.getDeclaredFields();
        Map<String, Object> temp = new HashMap<String, Object>();
        for (Field field : fields) {
            try {
                temp.put(field.getName().toString(), field.get(c));
            } catch (IllegalArgumentException e1) {
            } catch (IllegalAccessException e1) {
            }
        }
        data = new float[fields.length];
        int i = 0;
        for (Map.Entry<String, Object> set : temp.entrySet()) {
            if (i == 14) break;
            if (set.getValue() instanceof String)
                data[i] = Float.parseFloat((String) set.getValue());
            else data[i] = (float) set.getValue();
            i++;
            System.out.println("added: " + set.getKey() + " and val " + set.getValue());
        }
        System.out.println();
    }

    public carDataConverted(float[] in, float[] ex) {
        this.data = in;
        this.expectedOut = ex;
    }
}
