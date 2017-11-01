package magengine.chapter;

import javafx.application.Platform;
import javafx.scene.effect.Bloom;
import magengine.bullet.impl.ArrowBullet;
import magengine.bullet.impl.DefaultBullet;
import magengine.bullet.impl.HexagonBullet;
import magengine.bullet.impl.PlayerBullet;
import magengine.bullet.impl.StarBullet;
import magengine.chapter.util.AChapter;
import magengine.game.LogicExecutor;
import magengine.paint.MyCanvas;
import magengine.paint.MyCanvasSwitcher;
import magengine.util.DI;
import magengine.util.ElementUtils;

public class MulChapter extends AChapter{

	@Override
	public void design(LogicExecutor executor, MyCanvas staticCanvas, ElementUtils mEU) {
		Platform.runLater(() -> {
			((MyCanvasSwitcher) DI.di().get("switcher")).configCanvas(DefaultBullet.class, (canvas) -> {
				canvas.setEffect(new Bloom());
			});
			((MyCanvasSwitcher) DI.di().get("switcher")).configCanvas(StarBullet.class, (canvas) -> {
				canvas.setEffect(new Bloom());
			});
			((MyCanvasSwitcher) DI.di().get("switcher")).configCanvas(ArrowBullet.class, (canvas) -> {
				canvas.setEffect(new Bloom());
			});
			((MyCanvasSwitcher) DI.di().get("switcher")).configCanvas(PlayerBullet.class, (canvas) -> {
				canvas.setEffect(new Bloom());
			});
			((MyCanvasSwitcher) DI.di().get("switcher")).configCanvas(HexagonBullet.class, (canvas) -> {
				canvas.setEffect(new Bloom());
			});
			staticCanvas.getGraphicsContext2D().fillRect(0, 0, MyCanvas.CANVAS_WIDTH, MyCanvas.CANVAS_HEIGHT);
		});
		
		
	}

}
