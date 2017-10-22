package magengine.enemy;

import java.util.ArrayList;
import java.util.List;

import magengine.danmuku.ADanmuku;

public abstract class ADelayDanmukuEnemy extends APolygonEnemy {

	public ADelayDanmukuEnemy(double x, double y, double velocityX, double velocityY, double ax, double ay) {
		super(x, y, velocityX, velocityY, ax, ay);
	}

	public ADelayDanmukuEnemy(double x, double y, double velocityX, double velocityY) {
		super(x, y, velocityX, velocityY);
	}

	public ADelayDanmukuEnemy(double x, double y) {
		super(x, y);
	}

	private List<ADanmuku> danmukuList = new ArrayList<ADanmuku>();

	public void addDanmuku(ADanmuku danmuku) {
		danmukuList.add(danmuku);
	}

	@Override
	public void initWhenAdd() {
		danmukuList.forEach(x -> {
			x.setSourceElement(this);
			x.delayExecute();
		});
	}

}
