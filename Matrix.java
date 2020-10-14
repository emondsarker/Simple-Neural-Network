package neuralNetwork;

import java.util.ArrayList;
import java.util.List;

//Class contains all Matrix Operations
//Easily modifiable if needed

public class Matrix {
	
	double[][] data;
	int rows;
	int columns;
	
	public Matrix(int rows, int columns) {
		//initializes size of matrix
		data = new double[rows][columns];
		this.rows=rows;
		this.columns=columns;
		
		//creates random values to matrix
		for(int i=0; i<rows;i++) {
			for(int j=0;j<columns;j++) {
				data[i][j]=Math.random()*2-1; //generates {x|-1<x<1}
			}
		}
	}
	
	//Addition with matrix with same size
	//All elements in matrix= scaler
	public void add(double scaler){
	    for(int i=0;i<rows;i++){
	        for(int j=0;j<columns;j++){
	            this.data[i][j]+=scaler;
	        } 
	    }
	}
	
	//Adds an existing Matrix object
	public void add(Matrix m){
		//Checks if sizes are same
	    if(columns!=m.columns || rows!=m.rows) {
	        System.out.println("Matrixes of diff sizes");
	        return;
	    }
	    
	    for(int i=0;i<rows;i++){
	        for(int j=0;j<columns;j++){
	            this.data[i][j]+=m.data[i][j];
	        }
	    }
	}
	
	//subtracts two matrixes taken in parameters
	public static Matrix subtract(Matrix a, Matrix b) {
		//Checks if sizes are same
	    if(a.columns!=b.columns || a.rows!=b.rows) {
	        System.out.println("Matrixes of diff sizes");
	        return null;
	    }
	    
		Matrix temp=new Matrix(a.rows,a.columns);//holds answer
		
        for(int i=0;i<a.rows;i++){
            for(int j=0;j<a.columns;j++){
                temp.data[i][j]=a.data[i][j]-b.data[i][j];
            }
        }
        return temp;
    }
	
	//Transposes (rows->columns & columns->rows)
	public static Matrix transpose(Matrix a) {
        Matrix temp=new Matrix(a.columns,a.rows); //holds answer
        
        for(int i=0;i<a.rows;i++){
            for(int j=0;j<a.columns;j++){
                temp.data[j][i]=a.data[i][j];
            }
        }
        return temp;
    }

	//Multiplies two matrixes
	public static Matrix multiply(Matrix a, Matrix b) {
        Matrix temp=new Matrix(a.rows,b.columns);//holds answer
        
        for(int i=0;i<temp.rows;i++){
            for(int j=0;j<temp.columns;j++){
                double sum=0;
                for(int k=0;k<a.columns;k++){
                    sum+=a.data[i][k]*b.data[k][j]; //multiplication step
                }
                temp.data[i][j]=sum; //assigns answer
            }
        }
        return temp;
    }
    
	//Multiplies with an existing Matrix Object
    public void multiply(Matrix a) {
    	//Checks if sizes are same
	    if(columns!=a.columns || rows!=a.rows) {
	        System.out.println("Matrixes of diff sizes");
	        return;
	    }
        for(int i=0;i<a.rows;i++){
            for(int j=0;j<a.columns;j++){
                this.data[i][j]*=a.data[i][j];
            }
        }
    }
    
    //Multiplies with a constant k
    public void multiply(double k) {
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                this.data[i][j]*=k;
            }
        }
    }
    
    //Sigmoid function 
    public void sigmoid() {
        for(int i=0;i<rows;i++) {
            for(int j=0;j<columns;j++)
                this.data[i][j] = 1/(1+Math.exp(-this.data[i][j])); 
        }
        
    }
    
    public Matrix derivativeOfSigmoid() {
        Matrix temp=new Matrix(rows,columns);
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++)
                temp.data[i][j] = this.data[i][j] * (1-this.data[i][j]);
        }
        return temp;
        
    }
    
    //creates Matrix from an Array, takes x as row
    public static Matrix fromArray(double[]x)
    {
        Matrix temp = new Matrix(x.length,1);
        for(int i =0;i<x.length;i++)
            temp.data[i][0]=x[i];
        return temp;
        
    }
    
    //creates an Array from a Matrix
    public List<Double> toArray() {
        List<Double> temp= new ArrayList<Double>()  ;
        
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                temp.add(data[i][j]);
            }
        }
        return temp;
   }
        
	
}
