package magengine.mulplay;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
public class PlayerVO implements Serializable{
	
	private double x;
	private double y;
	private double vx;
	private double vy;
	private boolean isShooting=false;
	private boolean noHurtMode = false;
	
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
	
	public PlayerVO(double x, double y, double vx, double vy, boolean isShooting,
			boolean noHurtMode) {
		super();
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.isShooting = isShooting;
		this.noHurtMode = noHurtMode;
	}

	public PlayerVO(){
		
	}
	
	
	public boolean getShooting() {
		return isShooting;
	}
	public void setShooting(boolean isShooting) {
		this.isShooting = isShooting;
	}
	public boolean getNoHurtMode() {
		return noHurtMode;
	}
	public void setNoHurtMode(boolean noHurtMode) {
		this.noHurtMode = noHurtMode;
	}
	@Override
	public String toString() {
		return "PlayerVO [x=" + x + ", y=" + y + ", vx=" + vx + ", vy=" + vy 
				+ ", isShooting=" + isShooting + ", noHurtMode=" + noHurtMode + "]";
	}
	
	
	
	
}
