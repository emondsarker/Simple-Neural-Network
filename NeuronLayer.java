package neuralNetwork;

import java.util.*;


public class NeuronLayer {

	Matrix weights_Input_Hidden; //weights for input and hidden layer
	Matrix weights_Hidden_Output; //weights for hidden and output layer
	Matrix biasHidden; //bias for hidden layer
	Matrix biasOutput; //bias for output player
	double LearningRate =0.01; //learning rate, used to control learning steps
	
	public NeuronLayer(int i,int h,int o) {
        weights_Input_Hidden = new Matrix(h,i);
        weights_Hidden_Output = new Matrix(o,h);
        //Biases are randomized cause idk how to optimize lol
        biasHidden= new Matrix(h,1);
        biasOutput= new Matrix(o,1);
        
    }
	
	//Predict is a function that only does forward propagation
	//In short: Cannot be used to train; Is used to predict output
	public List<Double> predict(double[] I){
		
		//"Input" matrix is created from array
		Matrix input = Matrix.fromArray(I);
		
		//"Input" is multiplied with weights in the hidden layer
		Matrix hidden = Matrix.multiply(weights_Input_Hidden, input);
		
		//We add the bias
		hidden.add(biasHidden);
		
		//Sigmoid function is used to squash "hidden" values to be {y|0<y<1}
		//Why? Probabilities exist between 0 and 1
		hidden.sigmoid();
		
		//Repeat for hidden->output
		Matrix output = Matrix.multiply(weights_Hidden_Output, hidden);
		output.add(biasOutput);
		output.sigmoid();
		
		return output.toArray();
	}
	
	public void fit(double[][]input,double[][]output,int epochs){ //epochs=training sets
        for(int i=0;i<epochs;i++){    
            int randomDataPoint =  (int)(Math.random() * input.length ); //randomized to gamble our way to efficiency
            this.train(input[randomDataPoint], output[randomDataPoint]); //gamble gamble gamble
        }
    }
	
	public void train(double[] X, double[] Y) {
		//forward propagation
		Matrix input = Matrix.fromArray(X);
        Matrix hidden = Matrix.multiply(weights_Input_Hidden, input);
        hidden.add(biasHidden);
        hidden.sigmoid();
        
        Matrix output = Matrix.multiply(weights_Hidden_Output,hidden);
        output.add(biasOutput);
        output.sigmoid();
        
        Matrix target = Matrix.fromArray(Y); //end goal of training
        
        //finds difference between current and ideal output
        Matrix error = Matrix.subtract(target, output);
     
        //back propagation begins
        
        Matrix outputGradient = output.derivativeOfSigmoid();
        outputGradient.multiply(error);
        outputGradient.multiply(LearningRate);
        
        Matrix transposedHidden = Matrix.transpose(hidden);
        Matrix deltaOf_weights_Hidden_Output =  Matrix.multiply(outputGradient, transposedHidden);
        
        //nudging output weights and bias (cant spell pls fix)
        weights_Hidden_Output.add(deltaOf_weights_Hidden_Output);
        biasOutput.add(outputGradient);
        
        Matrix transposedWeights_Hidden_Output = Matrix.transpose(weights_Hidden_Output);
        Matrix hiddenErrors = Matrix.multiply(transposedWeights_Hidden_Output, error);
        
        Matrix hiddenGradient = hidden.derivativeOfSigmoid();
        hiddenGradient.multiply(hiddenErrors);
        hiddenGradient.multiply(LearningRate);
        
        Matrix transposedInput = Matrix.transpose(input);
        Matrix deltaOf_weights_Input_Hidden = Matrix.multiply(hiddenGradient, transposedInput);
        
        //nudging hidden weights and bias (pls fix)
        weights_Input_Hidden.add(deltaOf_weights_Input_Hidden);
        biasHidden.add(hiddenGradient);
        
    }
	
}
