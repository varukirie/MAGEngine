package indi.megaastronic.util;

import java.util.ArrayList;

public class CoodinateSet {

	private ArrayList<Coodinate> coList = new ArrayList<>();

	public ArrayList<Coodinate> getCoList() {
		return coList;
	}

	public double[] toXArray(){
		double[] array = new double[coList.size()];
		for(int i=0;i<coList.size();i++){
			array[i]=coList.get(i).getX();
		}
		return array;
	}
	
	public double[] toYArray(){
		double[] array = new double[coList.size()];
		for(int i=0;i<coList.size();i++){
			array[i]=coList.get(i).getY();
		}
		return array;
	}
	public void add(Coodinate co){
		this.coList.add(co);
	}
}
