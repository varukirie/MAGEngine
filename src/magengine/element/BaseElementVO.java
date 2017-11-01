package magengine.element;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BaseElementVO {
	
	private double x;
	private double y;
	private double vx;
	private double vy;
	private String keyName;
	private String className ;
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getVx() {
		return vx;
	}
	public void setVx(double vx) {
		this.vx = vx;
	}
	public double getVy() {
		return vy;
	}
	public void setVy(double vy) {
		this.vy = vy;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public BaseElementVO(double x, double y, double vx, double vy,String keyName, String className) {
		super();
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.keyName=keyName;
		this.className = className;
	}
	
	public BaseElementVO(){
		
	}
	
}
