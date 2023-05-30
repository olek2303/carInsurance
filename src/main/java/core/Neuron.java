package core;

public class Neuron { // klasa pojedyneczego neuronu
    static float minWeightValue;
    static float maxWeightValue;
    float[] weights;
    float[] cacheWeights;
    float gradient;
    float bias;
    float value = 0;

    public Neuron(float[] weights, float bias) { //hidden output layer
        this.weights = weights;
        this.bias = bias;
        this.cacheWeights = weights;
        this.gradient = 0;
    }

    public Neuron (float value) { //input
        this.weights = null;
        this.bias = -1;
        this.cacheWeights = null;
        this.gradient = -1;
        this.value = value;
    }

    public static void setRangeWeight (float min, float max) {
        minWeightValue = min;
        maxWeightValue = max;
    }

    public void updateWeight() { this.weights = this.cacheWeights; } // at the end of backprop
}
