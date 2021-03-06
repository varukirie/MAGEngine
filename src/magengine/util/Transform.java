package magengine.util;

public class Transform {
	
	public static double[] transform(double[] in,double[][] t){
		double[] result=new double[2];	
		result[0]=in[0]*t[0][0]+in[1]*t[1][0];
		result[1]=in[0]*t[0][1]+in[1]*t[1][1];
		return result;
	}
	public static double[] transform(double[][] t,double... in){
		double[] result=new double[2];	
		result[0]=in[0]*t[0][0]+in[1]*t[1][0];
		result[1]=in[0]*t[0][1]+in[1]*t[1][1];
		return result;
	}
	
	public static double[][] martixInTransform(double[][] transformMartix,double[][] in){
		double[][] result = new double[2][in[0].length];
		for(int i=0;i<in[0].length;i++){
			result[0][i]=in[0][i]*transformMartix[0][0]+in[1][i]*transformMartix[1][0];
			result[1][i]=in[0][i]*transformMartix[0][1]+in[1][i]*transformMartix[1][1];
		}
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
	

	public static void delta(double[][] in,double x,double y){
		for(int i=0;i<in[0].length;i++){
			in[0][i]=in[0][i]+x;
			in[1][i]=in[1][i]+y;
		}
	}
	public static void toVertices(double[][] in,float[] target){
		for(int i=0;i<in[0].length;i++){
			target[i*2]=(float) in[0][i];
			target[i*2+1]=(float) in[1][i];
		}
	}
	
}
