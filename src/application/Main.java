package application;

import indi.megaastronic.ui.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	public static final boolean DEBUG = false;
	public static final boolean DEBUG_BENCH=false;
	public static final boolean DEBUG_COLLISION=false;
	@Override
	public void start(Stage primaryStage) {
		try {
			SceneManager.startGame(primaryStage);
			SceneManager.shutdownGame();
			SceneManager.startGame(primaryStage);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
