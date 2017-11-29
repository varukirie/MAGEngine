package magengine.launcher.yt;

import java.util.UUID;

import magengine.chapter.util.QuickDanmuku;
import magengine.launcher.ALauncherGroup;
import magengine.launcher.Launcher;

public class LineLauncherGroup extends ALauncherGroup {

	private double midX=200;
	private double midY=200;
	private int amount=3;
	private double bulletSpeed = 50.0;
	private int r = 30;
	private int R = 90;
	
	private double direction = 0;

	
	
	public LineLauncherGroup(double midX, double midY) {
		super();
		this.midX = midX;
		this.midY = midY;
	}
	public LineLauncherGroup(double midX, double midY,int amount) {
		super();
		this.midX = midX;
		this.midY = midY;
		setAmount(amount);
	}

	public LineLauncherGroup( double direction) {
		super();
		this.direction = direction;
		
	}
	@Override
	public void execute() {
		  QuickDanmuku quick =QuickDanmuku.getQuickDanmuku();
		
		 Launcher launcher1 = new Launcher(midX+Math.cos(Math.PI/2-direction)*r, midY-Math.sin(Math.PI/2-direction)*r, direction, interval, duration);
		 launcher1.setBulletSpeed(bulletSpeed );
	     configLauncher(launcher1);
		 mEU.add(UUID.randomUUID().toString(), launcher1);
		 
		Launcher launcher2 = new Launcher(midX-Math.cos(Math.PI/2-direction)*r, midY+Math.sin(Math.PI/2-direction)*r, direction, interval, duration);
		launcher2.setBulletSpeed(bulletSpeed );
		quick.VRotate(launcher2, direction);
		configLauncher(launcher2);
		mEU.add(UUID.randomUUID().toString(), launcher2);
			
				
		Launcher launcher3 = new Launcher(midX+Math.cos(direction)*0.5*R+Math.cos(Math.PI/2-direction)*r/2, midY+Math.sin(direction)*0.5*R-Math.sin(Math.PI/2-direction)*r/2, direction, interval, duration);
		launcher3.setBulletSpeed(bulletSpeed );
		configLauncher(launcher3);
		mEU.add(UUID.randomUUID().toString(), launcher3);
		
		
		Launcher launcher4 = new Launcher(midX+Math.cos(direction)*0.5*R-Math.cos(Math.PI/2-direction)*r/2, midY+Math.sin(direction)*0.5*R+Math.sin(Math.PI/2-direction)*r/2, direction, interval, duration);
		launcher4.setBulletSpeed(bulletSpeed );
		configLauncher(launcher4);
		mEU.add(UUID.randomUUID().toString(), launcher4);
		
				
		Launcher launcher5 = new Launcher(midX+Math.cos(direction)*R,midY+Math.sin(direction)*R, direction, interval, duration);
		launcher5.setBulletSpeed(bulletSpeed);
		configLauncher(launcher5);
		mEU.add(UUID.randomUUID().toString(), launcher5);
		
		
		
		
	
	}


	public double getMidX() {
		return midX;
	}


	public void setMidX(double midX) {
		this.midX = midX;
	}


	public double getMidY() {
		return midY;
	}


	public void setMidY(double midY) {
		this.midY = midY;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public double getDirection() {
		return midX;
	}


	public void setDirection(double direction) {
		this.direction = direction;
	}
	public double getBulletSpeed() {
		return bulletSpeed;
	}
	public void setBulletSpeed(double bulletSpeed) {
		this.bulletSpeed = bulletSpeed;
	}


	
}
