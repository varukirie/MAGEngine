package magengine.bullet;

import java.util.Random;
import java.util.function.Function;

import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Paint;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;

public enum PresetColor {

	redOpacity((cirb) -> {
		return new RadialGradient(0, .1, cirb.getX(), cirb.getY(), cirb.getR(), false, CycleMethod.NO_CYCLE,
				new Stop(0, Color.rgb(255, 99, 71, 0.0)),
				new Stop(0.1, Color.rgb(255, 99, 71, 0.0)), 
				new Stop(0.4, Color.rgb(255, 99, 71, 0.7)), 
				new Stop(0.7, Color.rgb(245, 245, 245, 1)),
				new Stop(1, Color.rgb(245, 245, 245, 1)));
	}), 
	whiteOpacity((cirb) -> {
		return new RadialGradient(0, .1, cirb.getX(), cirb.getY(), cirb.getR(), false, CycleMethod.NO_CYCLE,
				new Stop(0, Color.rgb(245, 245, 245,0)),
				new Stop(0.3, Color.rgb(245, 245, 245,0)),
				new Stop(0.7, Color.rgb(245, 245, 245,1)),
				new Stop(1, Color.WHITE));
	}), 
	blueOpacity((cirb) -> {
		return new RadialGradient(0, .1, cirb.getX(), cirb.getY(), cirb.getR(), false, CycleMethod.NO_CYCLE,
				new Stop(0, Color.rgb(67, 110, 238, 0.4)), new Stop(1, Color.rgb(245, 245, 245, 0.7)));
	}),
	blueOpacityWhite((cirb) -> {
		return new RadialGradient(0, .1, cirb.getX(), cirb.getY(), cirb.getR(), false, CycleMethod.NO_CYCLE,
				new Stop(0, Color.BLUE), new Stop(0.5, Color.rgb(245, 245, 245, 0.0)), new Stop(1, Color.WHITESMOKE));
	});
	private PresetColor(Function<RadiusSupplier, Paint> colorSP) {
		this.colorSupplier = colorSP;
	}
	
	public static Function<RadiusSupplier, Paint> getByStops(Stop[] stops){
		return (cirb) -> {
			return new RadialGradient(0, .1, cirb.getX(), cirb.getY(), cirb.getR(), false, CycleMethod.NO_CYCLE,stops);
		};
	}
	
	public static Function<RadiusSupplier, Paint> getOpacityByColor(Color color){
		return (cirb) -> {
			return new RadialGradient(0, .1, cirb.getX(), cirb.getY(), cirb.getR(), false, CycleMethod.NO_CYCLE,
					new Stop(0, Color.rgb(245, 245, 245,0)),
					new Stop(0.3, Color.rgb(245, 245, 245,0)),
					new Stop(0.7, color),
					new Stop(1, Color.WHITE));
		};
	}
	public static Function<RadiusSupplier, Paint> getRandomColorOpacityIn(Color[] colors){
		int random = new Random().nextInt(colors.length);
		return (cirb) -> {
			return new RadialGradient(0, .1, cirb.getX(), cirb.getY(), cirb.getR(), false, CycleMethod.NO_CYCLE,
					new Stop(0, Color.rgb(245, 245, 245,0)),
					new Stop(0.3, Color.rgb(245, 245, 245,0)),
					new Stop(0.7, colors[random]),
					new Stop(1, Color.WHITE));
		};
	}
	private Function<RadiusSupplier, Paint> colorSupplier;
	
	public Function<RadiusSupplier, Paint> get() {
		return this.colorSupplier;
	}
}
