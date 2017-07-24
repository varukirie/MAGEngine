package indi.megaastronic.util;

public class Transform {
	
	public static double[] transform(double[] in,double[][] t){
		double[] result=new double[2];	
		result[0]=in[0]*t[0][0]+in[1]*t[1][0];
		result[1]=in[0]*t[0][1]+in[1]*t[1][1];
		return result;
	}
	
	double[][] transformMartix=null;
	
	public Transform(double[][] t){
		this.transformMartix=t;
	}
	public double[] transform(double... in){
		double[] result=new double[2];	
		result[0]=in[0]*this.transformMartix[0][0]+in[1]*this.transformMartix[1][0];
		result[1]=in[0]*this.transformMartix[0][1]+in[1]*this.transformMartix[1][1];
		return result;
	} 
}
