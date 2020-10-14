package neuralNetwork;

import java.util.List;

public class Main {

	
	//Training input
	static double [][] input= {
			{0,0},
			{1,0},	//AND gate input
			{0,1},
			{1,1}
	};
	
	//Training output
	static double [][] output= {
			{0},
			{0},	//AND gate output
			{0},
			{1}
	};

	public static void main(String[] args) {
		int index =0;
		
		NeuronLayer nL = new NeuronLayer(2,10,1);//sets Matrix sizes
		List<Double>predictedOutput;
		
		//trains the neural network
		nL.fit(input, output, 100000);//(i,o,iterations)
		
		//actual input to test system
		double [][] newInput = {
				{1,0},
				{0,1},
				{1,1},
				{0,0}	
		};
		
		for(double temp[]:newInput){
			predictedOutput = nL.predict(temp);
			
			if(predictedOutput.get(index)>=0.90) //Rounds up
				System.out.println("[1]"); //prints a more intuitive answer
			else if(predictedOutput.get(index)<=0.10)//Rounds down
				System.out.println("[0]"); //prints a more intuitive answer
			else
				System.out.println(predictedOutput);
		}		
	}
}
