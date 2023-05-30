package core;

import java.util.Arrays;
import java.util.Collections;

public class NeuronNet {
    static Layer[] layers;
    static carDataConverted[] tDataSet;

    public static void main(String[] args) {
        carData c1 = new carData(21, 1, 1, "coupe", "volkswagen", "eos", "2", 2006,
                2.0f, 140000, 15000, "outdoor", 0, 0);
        carDataConverted c = new carDataConverted(c1);
        System.out.println(predict(c));
    }

    public static void CreateTrainingData() { // tworzenie listy prawdopodnych konfiguracji danych oraz oczekiwanego wyjscia
        /*      ZNIZKI (bez szkody)
        >=8 lat         -60% (0.4)
        >=7 and < 8     -55% (0.45)
        >=6 and < 7     -50% (0.5)
        >=5 and < 6     -40% (0.6)
        >=4 and < 5     -30% (0.7)
        >=3 and < 4     -25% (0.75)
        >=2 and < 3     -20% (0.8)
        >=1 and < 2     -10% (0.9)
        */

        tDataSet = new carDataConverted[20];
        float in1[] = new float[] {0.25f, 0.2f, 1.0f, 0.6f, 0.9f, 0.4f, 0.2f, 0.1999f, 0.2f, 0.2f, 0.2f, 0.9f, 0.07f, 0.5f};
        float in2[] = new float[] {0.30f, 0.1f, 1.0f, 0.8f, 0.9f, 0.9f, 0.7f, 0.2020f, 0.5f, 0.2f, 0.1f, 0.5f, 0.10f, 0.4f};
        float in3[] = new float[] {0.20f, 0.1f, 0.0f, 0.3f, 0.5f, 0.4f, 0.3f, 0.2006f, 0.12f, 0.2f, 0.2f, 0.9f, 0.02f, 0.9f};
        float in4[] = new float[] {0.40f, 0.2f, 0.1f, 0.4f, 0.7f, 0.9f, 0.5f, 0.2012f, 0.5f, 0.15f, 0.35f, 0.9f, 0.2f, 0.4f};
        float in5[] = new float[] {0.20f, 0.1f, 0.0f, 0.3f, 0.5f, 0.4f, 0.7f, 0.2020f, 0.2f, 0.15f, 0.35f, 0.9f, 0.0f, 1f};
        float in6[] = new float[] {0.50f, 0.1f, 0.1f, 0.8f, 0.5f, 0.4f, 0.7f, 0.2019f, 0.2f, 0.20f, 0.35f, 0.5f, 0.20f, 0.4f};
        float in7[] = new float[] {0.20f, 0.1f, 0.1f, 0.8f, 0.9f, 0.9f, 0.6f, 0.2010f, 0.3f, 0.10f, 0.3f, 0.9f, 0.0f, 1f}; //1 rok ocac, 1 bez szkody
        float in8[] = new float[] {0.40f, 0.2f, 0.1f, 0.4f, 0.5f, 0.4f, 0.6f, 0.2008f, 0.2f, 0.16f, 0.25f, 0.9f, 0.15f, 0.4f}; // 10 lat ocac, 10 bez szkody
        float in9[] = new float[] {0.32f, 0.1f, 0.0f, 0.2f, 0.7f, 0.4f, 0.8f, 0.2014f, 0.18f, 0.14f, 0.2f, 0.9f, 0.05f, 0.7f}; // 8 lat ocac, 7 bez szkody
        float in10[] = new float[] {0.55f, 0.1f, 0.1f, 0.9f, 0.7f, 0.4f, 0.99f, 0.2023f, 0.2f, 0.02f, 0.2f, 0.5f, 0.30f, 0.4f}; //20 lat oaca, 28 bez szkody
        float in11[] = new float[] {0.42f, 0.1f, 0.0f, 0.3f, 0.5f, 0.4f, 0.4f, 0.2000f, 0.16f, 0.35f, 0.3f, 0.9f, 0.22f, 0.4f}; //20 lat ocac, 18 bez szkody
        float in12[] = new float[] {0.25f, 0.1f, 0.1f, 0.6f, 0.9f, 0.9f, 0.6f, 0.2012f, 0.35f, 0.11f, 0.2f, 0.9f, 0.1f, 0.9f}; //5 lat ocac, 1 bez szkody
        float in13[] = new float[] {0.37f, 0.1f, 0.0f, 0.8f, 0.9f, 0.4f, 0.8f, 0.2016f, 0.22f, 0.13f, 0.35f, 0.5f, 0.1f, 0.45f}; //15 lat ocac, 12 bez szkody
        float in14[] = new float[] {0.60f, 0.1f, 0.1f, 0.9f, 0.5f, 0.4f, 0.5f, 0.2006f, 0.16f, 0.18f, 0.2f, 0.9f, 0.45f, 0.4f}; //35 lat ocac, 35 bez szkody
        float in15[] = new float[] {0.25f, 0.1f, 0.0f, 0.2f, 0.5f, 0.4f, 0.6f, 0.2008f, 0.14f, 0.25f, 0.3f, 0.9f, 0.3f, 0.85f}; // 1 lat ocac, 1 bez szkody
        float in16[] = new float[] {0.26f, 0.1f, 0.1f, 0.6f, 0.7f, 0.9f, 0.8f, 0.2016f, 0.34f, 0.16f, 0.2f, 0.5f, 0.03f, 0.75f}; //6 lat ocac, 6 bez szkody
        float in17[] = new float[] {0.39f, 0.1f, 0.1f, 0.2f, 0.5f, 0.4f, 0.5f, 0.2006f, 0.12f, 0.07f, 0.2f, 0.9f, 0.15f, 0.4f}; //13 lat ocac, 1 bez szkody
        float in18[] = new float[] {0.23f, 0.1f, 0.0f, 0.4f, 0.7f, 0.4f, 0.6f, 0.2009f, 0.25f, 0.3f, 0.25f, 0.5f, 0.0f, 1f}; // 2 lat, ocac, 2 bez szkody
        float in19[] = new float[] {0.62f, 0.1f, 0.1f, 0.2f, 0.5f, 0.4f, 0.8f, 0.2017f, 0.14f, 0.02f, 0.2f, 0.9f, 0.40f, 0.4f}; //40 lat ocac, 40 bez szkody
        float in20[] = new float[] {0.45f, 0.1f, 0.1f, 0.9f, 0.9f, 0.9f, 0.99f, 0.2023f, 0.4f, 0.01f, 0.35f, 0.5f, 0.15f, 0.45f}; //20 lat ocac, 20 bez szkody

        float[] ex1 = new float[] {1400}; //1400 (1440)
        float[] ex2 = new float[] {2500}; //2500 (2500)
        float[] ex3 = new float[] {2000}; //2000 (2000)
        float[] ex4 = new float[] {1400}; //1400 (1450)
        float[] ex5 = new float[] {3000}; //3000 (3000)
        float[] ex6 = new float[] {1400}; //1400 (1300)
        float[] ex7 = new float[] {3000}; //3000 (3200)
        float[] ex8 = new float[] {1800}; //1800 (1800)
        float[] ex9 = new float[] {1800}; //1800 (1700)
        float[] ex10 = new float[] {1400}; //1400 (1500)
        float[] ex11 = new float[] {900}; //900 (900)
        float[] ex12 = new float[] {2500}; //2500 (2700)
        float[] ex13 = new float[] {1400}; //1400 (1350)
        float[] ex14 = new float[] {900}; //900 (1050)
        float[] ex15 = new float[] {1800}; //1800 (1750)
        float[] ex16 = new float[] {2000}; //2000 (2100)
        float[] ex17 = new float[] {1400}; //1400 (1500)
        float[] ex18 = new float[] {1800}; //1800 (1700)
        float[] ex19 = new float[] {900}; //900(950)
        float[] ex20 = new float[] {1800}; //1800 (1850)
        //900, 1400, 1800, 2000, 2500, 3000

        tDataSet[0] = new carDataConverted(in1, ex1);
        tDataSet[1] = new carDataConverted(in2, ex2);
        tDataSet[2] = new carDataConverted(in3, ex3);
        tDataSet[3] = new carDataConverted(in4, ex4);
        tDataSet[4] = new carDataConverted(in5, ex5);
        tDataSet[5] = new carDataConverted(in6, ex6);
        tDataSet[6] = new carDataConverted(in7, ex7);
        tDataSet[7] = new carDataConverted(in8, ex8);
        tDataSet[8] = new carDataConverted(in9, ex9);
        tDataSet[9] = new carDataConverted(in10, ex10);
        tDataSet[10] = new carDataConverted(in11, ex11);
        tDataSet[11] = new carDataConverted(in12, ex12);
        tDataSet[12] = new carDataConverted(in13, ex13);
        tDataSet[13] = new carDataConverted(in14, ex14);
        tDataSet[14] = new carDataConverted(in15, ex15);
        tDataSet[15] = new carDataConverted(in16, ex16);
        tDataSet[16] = new carDataConverted(in17, ex17);
        tDataSet[17] = new carDataConverted(in18, ex18);
        tDataSet[18] = new carDataConverted(in19, ex19);
        tDataSet[19] = new carDataConverted(in20, ex20);
    }

    public static void forward(float[] inputs) { // forward propagation
        layers[0] = new Layer(inputs);
        for(int i = 1; i < layers.length; i++) {
            for(int j = 0; j < layers[i].neurons.length; j++) {
                float sum = 0;
                for(int k = 0; k < layers[i-1].neurons.length; k++) {
                    sum += layers[i - 1].neurons[k].value * layers[i].neurons[j].weights[k];
                }
                //sum += layers[i].neurons[j].bias;
                layers[i].neurons[j].value = Util.Sigmoid(sum);
            }
        }
    }

    public static void backward(float learningRate, carDataConverted tData, int forDataEx) { // backward propagation
        int numberLayers = layers.length;
        int outIndex = numberLayers - 1;

        for(int i = 0; i < layers[outIndex].neurons.length; i++) {
            float output = layers[outIndex].neurons[i].value;
            float target = tData.expectedOut[forDataEx];
            float derivative = output - target;
            float delta = derivative * (output * (1 - output));
            layers[outIndex].neurons[i].gradient = delta;
            for(int j = 0; j < layers[outIndex].neurons[i].weights.length; j++) {
                float previousOutput = layers[outIndex-1].neurons[j].value;
                float error = delta * previousOutput;
                layers[outIndex].neurons[i].cacheWeights[j] = layers[outIndex].neurons[i].weights[j] - learningRate * error;
            }
        }

        for(int i = outIndex - 1; i > 0; i--) {
            for(int j = 0; j < layers[i].neurons.length; j++) {
                float output = layers[i].neurons[j].value;
                float gradientSum = sumGradient(j, i+1);
                float delta = (gradientSum) * (output * (1 - output));
                layers[i].neurons[j].gradient = delta;
                for(int k = 0; k < layers[i].neurons[j].weights.length; k++) {
                    float previousOutput = layers[i-1].neurons[k].value;
                    float error = delta * previousOutput;
                    layers[i].neurons[j].cacheWeights[k] = layers[i].neurons[j].weights[k] - learningRate * error;
                }
            }
        }

        for(int i = 0; i < layers.length; i++) {
            for(int j = 0; j < layers[i].neurons.length; j++) {
                layers[i].neurons[j].updateWeight();
            }
        }
    }

    public static float sumGradient(int nIndex, int lIndex) {
        float gradientSum = 0;
        Layer currentLayer = layers[lIndex];
        for(int i = 0; i < currentLayer.neurons.length; i++) {
            Neuron currentNeuron = currentLayer.neurons[i];
            gradientSum += currentNeuron.weights[nIndex] * currentNeuron.gradient;
            //gradientSum += Util.ReLU(currentNeuron.weights[nIndex]) * currentNeuron.gradient;
        }
        return gradientSum;
    }

    public static void train(int trainIter, float learningRate) { // metoda trenujaca siec neuronowa
        for(int i = 0; i < trainIter; i++) {
            for(int j = 0; j < tDataSet.length; j++) {
                forward(tDataSet[j].data);
                backward(learningRate, tDataSet[j], 0);
            }
        }
    }

    public static float count(carDataConverted c) { //wyliczenie skladki ubezpieczenia
        float[] out = new float[6];
        for(int j = 0; j < layers[2].neurons.length; j++) {
            for (int i = 0; i < c.data.length; i++) {
                out[0] = out[0] + (c.data[i] * layers[2].neurons[0].weights[i]) + layers[2].neurons[0].bias;
                out[1] = out[1] + (c.data[i] * layers[2].neurons[1].weights[i]) + layers[2].neurons[1].bias;
                out[2] = out[2] + (c.data[i] * layers[2].neurons[2].weights[i]) + layers[2].neurons[2].bias;
                out[3] = out[3] + (c.data[i] * layers[2].neurons[3].weights[i]) + layers[2].neurons[3].bias;
                out[4] = out[4] + (c.data[i] * layers[2].neurons[4].weights[i]) + layers[2].neurons[4].bias;
                out[5] = out[5] + (c.data[i] * layers[2].neurons[5].weights[i]) + layers[2].neurons[5].bias;
            }
        }
        System.out.println("==Out==");
        for(int i = 0; i < out.length; i++) {
            System.out.println(out[i]);
        }

        float max = 0.0f;
        for(int i = 0; i < out.length; i++) {
            if(max < out[i])
                max = out[i];
        }

        int i;
        for(i = 0; i < out.length; i++) {
            if(out[i] == max)
                break;
        }
        //900, 1400, 1800, 2000, 2500, 3000
        System.out.println("max: " + max + " on position: " + i);
        switch(i) {
            case 0:
                System.out.println("Price is in bounds of (0, 900)");
                break;
            case 1:
                System.out.println("Price is in bounds of (900, 1400)");
                break;
            case 2:
                System.out.println("Price is in bounds of (1400, 1800)");
                break;
            case 3:
                System.out.println("Price is in bounds of (1800,2000)");
                break;
            case 4:
                System.out.println("Price is in bounds of (2000, 2500)");
                break;
            case 5:
                System.out.println("Price is in bounds of (2500,3000)");
                break;
        }

        return max;
    }

    public static float predict(carDataConverted c) {
        Neuron.setRangeWeight(-1,1);
        layers = new Layer[3];
        layers[0] = null;
        layers[1] = new Layer(14,42);
        layers[2] = new Layer(42,6);

        CreateTrainingData();
        System.out.println("======================");
        System.out.println("Output before training");
        System.out.println("======================");

        for(int i = 0; i < tDataSet.length; i++) {
            forward(tDataSet[i].data);
            for(int j = 0; j < layers[2].neurons.length; j++) {
                System.out.println(layers[2].neurons[j].value);
            }
            System.out.println("\n");
        }

        train(1000000, 0.002f);

        System.out.println("=====================");
        System.out.println("Output after training");
        System.out.println("=====================");

        for(int i = 0; i < tDataSet.length; i++) {
            forward(tDataSet[i].data);
            for(int j = 0; j < layers[2].neurons.length; j++) {
                System.out.println(layers[2].neurons[j].value);
            }
            System.out.println("\n");
        }
        float cost = count(c);
        return cost;
    }
}
