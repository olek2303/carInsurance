package core;

public class Util { // klasa posiada wszystkie funkcje do wykonania potrzebnych obliczen
    public static float Sigmoid (float val) { return (float) (1/(1+Math.pow(Math.E, -val))); } // funkcja aktywacyjna
    public static float SigmoidDerviative(float val) { return Sigmoid(val)*(1-Sigmoid(val)); }
    public static float Tanh(float val) { // inna funkcja aktywacyjna - niewykorzystana w projekcie
        return (float) (((Math.pow(Math.E, val)) - Math.pow(Math.E, -val)) / ((Math.pow(Math.E, val)) + Math.pow(Math.E, -val)));
    }
    public static float ReLU(float val) { return Float.max(0, val);}
    public static float ReLUGradient(int learningRate, float val) {
        float x = ReLU(val);
        return learningRate * x;
    }
    public static float RandomFloat (float min, float max) { // numbers between min max
        float a = (float) Math.random();
        float num = min + (float) Math.random() * (max-min);
        if(a < 0.5) return num;
        else return -num;
    }
    public static float squaredError (float out, float target) { return (float) (0.5*Math.pow(2, (target-out))); } //backpropagation
    public static float sumSquaredError (float[] outputs, float[] targets) {
        float sum = 0;
        for(int i = 0; i < outputs.length; i++) {
            sum += squaredError(outputs[i], targets[i]);
        }
        return sum;
    }
}
