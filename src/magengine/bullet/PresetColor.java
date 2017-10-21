package magengine.bullet;

import java.util.function.Function;

import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Paint;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import magengine.bullet.impl.CircleBullet;

public enum PresetColor {

	redOpacity((cirb) -> {
		return new RadialGradient(0, .1, cirb.getX(), cirb.getY(), cirb.getR(), false, CycleMethod.NO_CYCLE,
				new Stop(0, Color.rgb(255, 99, 71, 0.4)), new Stop(1, Color.rgb(245, 245, 245, 0.9)));
	}), blueOpacity((cirb) -> {
		return new RadialGradient(0, .1, cirb.getX(), cirb.getY(), cirb.getR(), false, CycleMethod.NO_CYCLE,
				new Stop(0, Color.rgb(67, 110, 238, 0.4)), new Stop(1, Color.rgb(245, 245, 245, 0.7)));
	}), blueOpacityWhite((cirb) -> {
		return new RadialGradient(0, .1, cirb.getX(), cirb.getY(), cirb.getR(), false, CycleMethod.NO_CYCLE,
				new Stop(0, Color.BLUE), new Stop(0.5, Color.rgb(245, 245, 245, 0.0)), new Stop(1, Color.WHITESMOKE));
	});
	private PresetColor(Function<CircleBullet, Paint> colorSP) {
		this.colorSupplier = colorSP;
	}

	private Function<CircleBullet, Paint> colorSupplier;

	public Function<CircleBullet, Paint> get() {
		return this.colorSupplier;
	}
}
