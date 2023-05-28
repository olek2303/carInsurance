package core;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

public class Layer {
    public Neuron[] neurons;

    public Layer(int inNeurons, int numberNeurons) {
        this.neurons = new Neuron[numberNeurons];
        for(int i = 0; i < numberNeurons; i++) {
            float[] weights = new float[inNeurons];
            for(int j = 0; j < inNeurons; j++) {
                weights[j] = Util.RandomFloat(Neuron.minWeightValue, Neuron.maxWeightValue);
            }
            neurons[i] = new Neuron(weights, Util.RandomFloat(-1,1));
        }
    }

    public Layer(float input[]) {
        this.neurons = new Neuron[input.length];
        for(int i = 0; i < input.length; i++) {
            this.neurons[i] = new Neuron(input[i]);
        }
    }
}
