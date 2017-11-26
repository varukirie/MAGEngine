package magengine.control;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import magengine.bullet.Bullet;
import magengine.bullet.impl.PlayerBullet;
import magengine.chapter.util.QuickDanmuku;
import magengine.game.GameSession;
import magengine.util.DI;
import magengine.util.ElementUtils;

public enum PlayerPowerType {
	LOW((x,y)->{
		ElementUtils mEU = (ElementUtils) DI.di().get("mEU");
		{
			Bullet bullet = new PlayerBullet(x-10, y);
			bullet.setVelocityY(-600);
			bullet.setVelocityX(60);
			mEU.add("playerBullet" + GameSession.rand().nextInt(), bullet);
		}
		
		Bullet bullet = new PlayerBullet(x+10, y);
		bullet.setVelocityY(-600);
		bullet.setVelocityX(-60);
		mEU.add("playerBullet" + GameSession.rand().nextInt(), bullet);
	})
	,MID((x,y)->{
		LOW.doShoot.accept(x, y);
		ElementUtils mEU = (ElementUtils) DI.di().get("mEU");
		Consumer<Double> shootByDirection = (angle)->{
			Bullet bullet = new PlayerBullet(x, y);
			bullet.setVelocityY(-600);
			bullet.VRotate(angle);
			mEU.add("playerBullet" + GameSession.rand().nextInt(), bullet);
		};
		shootByDirection.accept(0.0);
//		shootByDirection.accept(-Math.PI/11);
	});
	
	PlayerPowerType(BiConsumer<Double,Double> doShoot){
		this.doShoot=doShoot;
	}
	private BiConsumer<Double,Double> doShoot ; 
	public BiConsumer<Double,Double> getDoShoot(){
		return doShoot;
	}
}
