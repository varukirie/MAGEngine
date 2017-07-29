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
	
	public double[][] transform(double[][] in){
		double[][] result = new double[2][in[0].length];
		for(int i=0;i<in[0].length;i++){
			result[0][i]=in[0][i]*transformMartix[0][0]+in[1][i]*transformMartix[1][0];
			result[1][i]=in[0][i]*transformMartix[0][1]+in[1][i]*transformMartix[1][1];
		}
		return result;
	}
	
	public Coodinate transform(Coodinate inCo){
		Coodinate ansCo= new Coodinate(0, 0);
		ansCo.setX(inCo.getX()*this.transformMartix[0][0]+inCo.getY()*this.transformMartix[1][0]);
		ansCo.setY(inCo.getX()*this.transformMartix[0][1]+inCo.getY()*this.transformMartix[1][1]);
		return ansCo;
	}
	
	public void delta(double[][] in,double x,double y){
		for(int i=0;i<in[0].length;i++){
			in[0][i]=in[0][i]+x;
			in[1][i]=in[1][i]+y;
		}
	}
}
