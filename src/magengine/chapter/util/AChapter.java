package magengine.chapter.util;

import javafx.application.Platform;
import magengine.control.PlayerControlHandler;
import magengine.game.GameSession;
import magengine.game.LogicExecutor;
import magengine.paint.MyCanvas;
import magengine.ui.SceneManager;
import magengine.util.ElementUtils;

public abstract class AChapter {
	public abstract void design(LogicExecutor executor,MyCanvas staticCanvas,ElementUtils mEU);

	/**
	 * 通关时调用该函数
	 */
	public static void clearGame(){
		PlayerControlHandler.getInstance().doBomb();
		Platform.runLater(() -> {
			SceneManager.getInstance().shakeInScene();
		});
		LogicExecutor.getLogicExecutor().schedule(() -> {
			Platform.runLater(() -> {
				SceneManager.getInstance().loadClearScene();
				GameSession.closeGameSession();
			});
		}, 2000);
	}
}
