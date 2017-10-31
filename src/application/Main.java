package application;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import magengine.game.Level;
import magengine.ui.SceneManager;
import magengine.util.DI;


public class Main extends Application {
	public static final boolean DEBUG = true;
	public static boolean DEBUG_NO_FAILURE = true;
	public static final boolean DEBUG_ElementCreate = false;
	public static final boolean DEBUG_BENCH=false;
	public static final boolean DEBUG_COLLISION=true;
	public static final boolean ACC_ENABLE=true;

	@Override
	public void start(Stage primaryStage) {
		try {
			SceneManager.init(primaryStage);
			SceneManager.getInstance().loadMulplaySelectScene();
			primaryStage.show();
//			SceneManager.shutdownGame();
//			SceneManager.startGame(primaryStage);	
//			ScheduledExecutorService ses = (ScheduledExecutorService) DI.di().get("sES");
//			ses.schedule(()->{
//				SceneManager.shutdownGame();
//				SceneManager.startGame(primaryStage);
//			}, 3000, TimeUnit.MILLISECONDS);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
