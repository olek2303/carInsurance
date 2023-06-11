package core;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.KeyPair;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class NeuronNet {
    public static Layer[] layers;
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
        /*
            age
            amountOfDrivers
            ocac
            carType // suv, hatchback, sedan, kombi
            carBrand // audi, bmw, citroen, skoda
            carModel //a5, seria 5, c3
            carGen // 1-gen, 2-gen
            carProdYear // 2012, 2006
            engineCap // 2.0, 1.2, 5.0
            counterStatus // 200 000, 150 000, 70 000
            kmInYear // 20 000, 30 000
            wherePark // indoor outdoor
            yearsOfOC // 5
            yearsOfLastAccident // 4
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
                sum += layers[i].neurons[j].bias;
                layers[i].neurons[j].value = Util.Sigmoid(sum);
            }
        }
    }

    public static void backward(float learningRate, carDataConverted tData) { // backward propagation
        int numberLayers = layers.length;
        int outIndex = numberLayers - 1;

        for(int i = 0; i < layers[outIndex].neurons.length; i++) {
            float output = layers[outIndex].neurons[i].value;
            float target = tData.expectedOut[0];
            float derivative = output - target;
            float delta = derivative * (output * (1 - output));
            layers[outIndex].neurons[i].gradient = delta;
            for(int j = 0; j < layers[outIndex].neurons[i].weights.length; j++) {
                float previousOutput = layers[outIndex-1].neurons[j].value;
                float error = delta * previousOutput;
                layers[outIndex].neurons[i].cacheWeights[j] = layers[outIndex].neurons[i].weights[j] - learningRate * error;
            }
            layers[outIndex].neurons[i].cacheBias -= learningRate * delta;
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
                layers[i].neurons[j].cacheBias -= learningRate * delta;
            }
        }

        for(int i = 0; i < layers.length; i++) {
            for(int j = 0; j < layers[i].neurons.length; j++) {
                layers[i].neurons[j].updateWeight();
                layers[i].neurons[j].updateBias();
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
                backward(learningRate, tDataSet[j]);
            }
        }
    }

    public static int count(carDataConverted c) { //wyliczenie skladki ubezpieczenia
        float[] out = new float[6];

        float[][] goodWeights = new float[6][];
        goodWeights[0] = new float[]{0.7123149f, 2.4254904f, 0.34208757f, 1.838024f, 0.047983356f, 1.4891185f, 1.0498626f, -0.3902004f, 2.5760152f,
                1.0553739f, 2.1143622f, 2.561384f, 1.010429f, 1.570311f};
        goodWeights[1] = new float[]{1.3465598f, 1.3785527f, 1.6499888f, 1.5490109f, -0.28853834f, 1.6189553f, 2.030362f, 0.8074467f, 2.3712068f,
                0.5661128f, 1.1013842f, 1.2016382f, 2.2252626f, 2.7071254f};
        goodWeights[2] = new float[]{0.8160266f, 3.010734f, 0.70153606f, 0.8755456f, -0.054225963f, 0.80338126f, 2.1556592f, 0.9226434f, 1.4651897f,
                -0.018350838f, 1.1460001f, 2.9478676f, 1.7734675f, 2.6590085f};
        goodWeights[3] = new float[]{1.2701637f, 1.3885598f, 0.5926916f, 2.583758f, 0.8748194f, 1.0932416f, 1.649107f, -0.44750273f, 1.3489085f,
                -0.20728168f, 2.4918265f, 2.1865585f, 0.893626f, 2.795366f};
        goodWeights[4] = new float[]{0.7577526f, 1.40071f, 1.2083745f, 1.8444356f, -0.021540828f, 1.9544584f, 1.8668042f, 0.40488446f, 2.586721f,
                1.2429404f, 1.7434229f, 2.1429083f, 0.98169804f, 1.5226511f};
        goodWeights[5] = new float[]{1.2210298f, 1.8290845f, 0.9760645f, 2.1629224f, -0.3631694f, 1.9675884f, 1.7246698f, -0.48774132f, 1.91748f,
                0.3555372f, 1.9601693f, 1.3090254f, 1.9552853f, 1.0846368f};

        /*
        for(int i = 0; i < c.data.length; i++) {
            out[0] = out[0] + (c.data[i] * layers[2].neurons[0].weights[i]);
            out[1] = out[1] + (c.data[i] * layers[2].neurons[1].weights[i]);
            out[2] = out[2] + (c.data[i] * layers[2].neurons[2].weights[i]);
            out[3] = out[3] + (c.data[i] * layers[2].neurons[3].weights[i]);
            out[4] = out[4] + (c.data[i] * layers[2].neurons[4].weights[i]);
            out[5] = out[5] + (c.data[i] * layers[2].neurons[5].weights[i]);
        }
        */

        for(int i = 0; i < c.data.length; i++) {
            out[0] = out[0] + (c.data[i] * goodWeights[0][i]);
            out[1] = out[1] + (c.data[i] * goodWeights[1][i]);
            out[2] = out[2] + (c.data[i] * goodWeights[2][i]);
            out[3] = out[3] + (c.data[i] * goodWeights[3][i]);
            out[4] = out[4] + (c.data[i] * goodWeights[4][i]);
            out[5] = out[5] + (c.data[i] * goodWeights[5][i]);
        }

        out[0] += layers[2].neurons[0].bias;
        out[1] += layers[2].neurons[1].bias;
        out[2] += layers[2].neurons[2].bias;
        out[3] += layers[2].neurons[3].bias;
        out[4] += layers[2].neurons[4].bias;
        out[5] += layers[2].neurons[5].bias;

        System.out.println("-----wagi------");
        for(int i = 0; i < 6; i++) {
            System.out.println("Neuron " + i);
            for(int j = 0; j < layers[2].neurons[i].weights.length; j++) {
                System.out.print(layers[2].neurons[i].weights[j] + "   ");
            }
            System.out.println("");
        }

        System.out.println("-----bias------");
        for(int i = 0; i < layers[2].neurons.length; i++) {
            System.out.println("bias dla " + i + " neurona: " + layers[2].neurons[i].bias);
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

        return i;
    }

    public static float predict(carDataConverted c) {
        Neuron.setRangeWeight(-1,1);
        layers = new Layer[3];
        layers[0] = null;
        layers[1] = new Layer(14,14);
        layers[2] = new Layer(14,6);

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

        train(1000000, 0.01f);

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
