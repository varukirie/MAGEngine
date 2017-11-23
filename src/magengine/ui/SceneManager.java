package magengine.ui;

import org.ietf.jgss.GSSContext;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import magengine.chapter.ChapterDemo;
import magengine.chapter.TestChapter;
import magengine.chapter.TestChapter1;
import magengine.chapter.util.ChapterLoader;
import magengine.chapter.util.QuickDanmuku;
import magengine.control.PlayerControlHandler;
import magengine.control.PlayerLaunchHandler;
import magengine.element.impl.DisplayMessage;
import magengine.element.impl.Player;
import magengine.game.GameSession;
import magengine.game.LogicExecutor;
import magengine.game.MoveHandler;
import magengine.paint.MyCanvas;
import magengine.util.DI;
import magengine.util.ElementUtils;

/**
 * 负责Scene切换
 * 
 * @author Astronic
 *
 */
public class SceneManager {
	public static void init(Stage primaryStage) {
		instance.setPrimaryStage(primaryStage);
	}

	public static SceneManager getInstance() {
		return instance;
	}

	private static SceneManager instance = new SceneManager();
	private Stage primaryStage;
	public void startMenu(){
		primaryStage.setResizable(false);
		StackPane root = new StackPane();
		BorderPane btPane = new BorderPane();
		BackgroundImage bimg = new BackgroundImage(new Image("/img/starbackground.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		root.setBackground(new Background(bimg));
		Canvas canvas = new Canvas(900, 700);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		VBox paneBt = new VBox(100);
		String st = "S T A R T";
		String hp = "H E L P" ;
		String ex = "E X I T";
		Button bStart = new Button(st);
		Button bHelp = new Button(hp);
		Button bExit = new Button(ex);
		bStart.setOnAction((ActionEvent e) -> {
		    SceneManager.getInstance().startGame();
		});
		bHelp.setOnAction((ActionEvent e) -> {
		    loadHelpScene();
		});
		bExit.setOnAction((ActionEvent e) -> {
			System.exit(-1);
		});
		bStart.addEventHandler(MouseEvent.MOUSE_ENTERED,
		        new EventHandler<MouseEvent>() {
		          @Override
		          public void handle(MouseEvent e) {
		        	  bStart.setStyle("-fx-font: 50 arial; -fx-background-color:null; -fx-text-fill:#fff;");
		          }
		        });
		bStart.addEventHandler(MouseEvent.MOUSE_EXITED,
		        new EventHandler<MouseEvent>() {
		          @Override
		          public void handle(MouseEvent e) {
		        	  bStart.setStyle("-fx-font: 50 arial; -fx-background-color:null; -fx-text-fill:grey;");
		          }
		        });
		bHelp.addEventHandler(MouseEvent.MOUSE_ENTERED,
		        new EventHandler<MouseEvent>() {
		          @Override
		          public void handle(MouseEvent e) {
		        	  bHelp.setStyle("-fx-font: 50 arial; -fx-background-color:null; -fx-text-fill:#fff;");
		          }
		        });
		bHelp.addEventHandler(MouseEvent.MOUSE_EXITED,
		        new EventHandler<MouseEvent>() {
		          @Override
		          public void handle(MouseEvent e) {
		        	  bHelp.setStyle("-fx-font: 50 arial; -fx-background-color:null; -fx-text-fill:grey;");
		          }
		        });
		bExit.addEventHandler(MouseEvent.MOUSE_ENTERED,
		        new EventHandler<MouseEvent>() {
		          @Override
		          public void handle(MouseEvent e) {
		        	  bExit.setStyle("-fx-font: 50 arial; -fx-background-color:null; -fx-text-fill:#fff;");
		          }
		        });
		bExit.addEventHandler(MouseEvent.MOUSE_EXITED,
		        new EventHandler<MouseEvent>() {
		          @Override
		          public void handle(MouseEvent e) {
		        	  bExit.setStyle("-fx-font: 50 arial; -fx-background-color:null; -fx-text-fill:grey;");
		          }
		        });
		bStart.setStyle("-fx-font: 50 arial; -fx-background-color:null; -fx-text-fill:grey;");
		bHelp.setStyle("-fx-font: 50 arial; -fx-background-color:null; -fx-text-fill:grey;");
		bExit.setStyle("-fx-font: 50 arial; -fx-background-color:null; -fx-text-fill:grey;");
		paneBt.getChildren().addAll(bStart,bHelp,bExit);
		paneBt.setAlignment(Pos.CENTER);
		btPane.setCenter(paneBt);
//		GameButton bStart = new GameButton(),bExit = new GameButton();
//		bStart.draw(gc, 350, 200, 200, 50);
//		bExit.draw(gc, 350, 400, 200, 50);
//		bStart.addActionListener(new ActionListener(){
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				SceneManager.getInstance().startGame();
//			}
//		});
		root.getChildren().add(btPane);
//		root.getChildren().add(canvas);
		Scene scene = new Scene(root,900,700);
//		scene.setOnKeyReleased((e) -> {
//			if (KeyCode.R.equals(e.getCode())) {
//				SceneManager.getInstance().startGame();
//			}
//		});
//		bStart.addActionListener(new ActionListener(){
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				SceneManager.getInstance().startGame();
//			}
//		});
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}

	public void startGame() {
		startGame(false, false);
	}

	public void startGame(boolean mulplay, boolean mulplayServer) {
		primaryStage.setResizable(false);
		GameSession session = GameSession.startGameSession();
		session.setMulplay(mulplay);
		session.setMulplayServer(mulplayServer);
		session.loadGameScene();
		session.loadChapter(new TestChapter());
		session.setFailureEvent(() -> {
			GameSession.closeGameSession();
			loadFailureScene();
		});
	}
	
	public void loadHelpScene()	{
		StackPane root = new StackPane();
		BorderPane btPane = new BorderPane();
		BackgroundImage bimg = new BackgroundImage(new Image("/img/starbackground.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		root.setBackground(new Background(bimg));
		Canvas canvas = new Canvas(900, 200);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		VBox text1 = new VBox(setTextFont2("方向键移动"));
		VBox text2 = new VBox(setTextFont2("x间发射普通子弹"));
		VBox text3 = new VBox(setTextFont2("c键发射炸弹"));
		VBox help = new VBox(setTextFont("H E L P"));
		String ex = "E X I T";
		Button bExit = new Button(ex);
		bExit.setOnAction((ActionEvent e) -> {
//			System.exit(-1);
			startMenu();
		});
		bExit.addEventHandler(MouseEvent.MOUSE_ENTERED,
		        new EventHandler<MouseEvent>() {
		          @Override
		          public void handle(MouseEvent e) {
		        	  bExit.setStyle("-fx-font: 50 arial; -fx-background-color:null; -fx-text-fill:#fff;");
		          }
		        });
		bExit.addEventHandler(MouseEvent.MOUSE_EXITED,
		        new EventHandler<MouseEvent>() {
		          @Override
		          public void handle(MouseEvent e) {
		        	  bExit.setStyle("-fx-font: 50 arial; -fx-background-color:null; -fx-text-fill:grey;");
		          }
		        });
		bExit.setStyle("-fx-font: 50 arial; -fx-background-color:null; -fx-text-fill:grey;");
		VBox paneBt = new VBox(100);
		paneBt.setAlignment(Pos.TOP_CENTER);
		help.setAlignment(Pos.TOP_CENTER);
		text1.setAlignment(Pos.TOP_CENTER);
		text2.setAlignment(Pos.TOP_CENTER);
		text3.setAlignment(Pos.TOP_CENTER);
		bExit.setAlignment(Pos.BOTTOM_CENTER);
		btPane.setMargin(help, new Insets(100,0,100,0));
		btPane.setTop(help);
		btPane.setBottom(bExit);
		paneBt.getChildren().addAll(text1,text2,text3);
		btPane.setCenter(paneBt);
		root.getChildren().add(btPane);
		Scene scene = new Scene(root,900,700);
		primaryStage.setScene(scene);
//		primaryStage.show();
	}
	
	public void loadFailureScene() {
		primaryStage.setResizable(false);
		StackPane root = new StackPane();
		BorderPane btPane = new BorderPane();
		BackgroundImage bimg = new BackgroundImage(new Image("/img/starbackground.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		root.setBackground(new Background(bimg));
		Canvas canvas = new Canvas(900, 200);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		VBox pause = new VBox(setTextFont("F A I L !"));
		VBox paneBt = new VBox(100);
		String rs = "R E S T A R T";
		String ex = "E X I T";
		Button bReset = new Button(rs);
		Button bExit = new Button(ex);
		bReset.setOnAction((ActionEvent e) -> {
		    SceneManager.getInstance().startGame();
		});
		bExit.setOnAction((ActionEvent e) -> {
//			System.exit(-1);
			startMenu();
		});
		bReset.addEventHandler(MouseEvent.MOUSE_ENTERED,
		        new EventHandler<MouseEvent>() {
		          @Override
		          public void handle(MouseEvent e) {
		        	  bReset.setStyle("-fx-font: 50 arial; -fx-background-color:null; -fx-text-fill:#fff;");
		          }
		        });
		bReset.addEventHandler(MouseEvent.MOUSE_EXITED,
		        new EventHandler<MouseEvent>() {
		          @Override
		          public void handle(MouseEvent e) {
		        	  bReset.setStyle("-fx-font: 50 arial; -fx-background-color:null; -fx-text-fill:grey;");
		          }
		        });
		bExit.addEventHandler(MouseEvent.MOUSE_ENTERED,
		        new EventHandler<MouseEvent>() {
		          @Override
		          public void handle(MouseEvent e) {
		        	  bExit.setStyle("-fx-font: 50 arial; -fx-background-color:null; -fx-text-fill:#fff;");
		          }
		        });
		bExit.addEventHandler(MouseEvent.MOUSE_EXITED,
		        new EventHandler<MouseEvent>() {
		          @Override
		          public void handle(MouseEvent e) {
		        	  bExit.setStyle("-fx-font: 50 arial; -fx-background-color:null; -fx-text-fill:grey;");
		          }
		        });
		bExit.setStyle("-fx-font: 50 arial; -fx-background-color:null; -fx-text-fill:grey;");
		bReset.setStyle("-fx-font: 50 arial; -fx-background-color:null; -fx-text-fill:grey;");
		paneBt.getChildren().addAll(bReset,bExit);
		paneBt.setAlignment(Pos.TOP_CENTER);
		pause.setAlignment(Pos.TOP_CENTER);
		btPane.setMargin(pause, new Insets(100,0,100,0));
		btPane.setTop(pause);
		btPane.setCenter(paneBt);
		root.getChildren().add(btPane);
		Scene scene = new Scene(root,900,700);
		primaryStage.setScene(scene);
//		primaryStage.show();
	}
	
	public void loadPauseScene() {
		Scene gameScene=this.primaryStage.getScene();
		primaryStage.setResizable(false);
		StackPane root = new StackPane();
		BorderPane btPane = new BorderPane();
		BackgroundImage bimg = new BackgroundImage(new Image("/img/starbackground.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		root.setBackground(new Background(bimg));
		Canvas canvas = new Canvas(900, 200);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		VBox pause = new VBox(setTextFont("P A U S E"));
		VBox paneBt = new VBox(100);
		String rs = "R E S U M E";
		String ex = "M E N U"; 
		Button bResume = new Button(rs);
		Button bExit = new Button(ex);
		final Runnable resume = ()->{
			primaryStage.setScene(gameScene);
			MoveHandler.setDeltaTimeFactor(1);
		};
		bResume.setOnAction((ActionEvent e) -> {
			resume.run();
		});
		bExit.setOnAction((ActionEvent e) -> {
			GameSession.closeGameSession();
			startMenu();
		});
		bResume.addEventHandler(MouseEvent.MOUSE_ENTERED,
		        new EventHandler<MouseEvent>() {
		          @Override
		          public void handle(MouseEvent e) {
		        	  bResume.setStyle("-fx-font: 50 arial; -fx-background-color:null; -fx-text-fill:#fff;");
		          }
		        });
		bResume.addEventHandler(MouseEvent.MOUSE_EXITED,
		        new EventHandler<MouseEvent>() {
		          @Override
		          public void handle(MouseEvent e) {
		        	  bResume.setStyle("-fx-font: 50 arial; -fx-background-color:null; -fx-text-fill:grey;");
		          }
		        });
		bExit.addEventHandler(MouseEvent.MOUSE_ENTERED,
		        new EventHandler<MouseEvent>() {
		          @Override
		          public void handle(MouseEvent e) {
		        	  bExit.setStyle("-fx-font: 50 arial; -fx-background-color:null; -fx-text-fill:#fff;");
		          }
		        });
		bExit.addEventHandler(MouseEvent.MOUSE_EXITED,
		        new EventHandler<MouseEvent>() {
		          @Override
		          public void handle(MouseEvent e) {
		        	  bExit.setStyle("-fx-font: 50 arial; -fx-background-color:null; -fx-text-fill:grey;");
		          }
		        });
		bExit.setStyle("-fx-font: 50 arial; -fx-background-color:null; -fx-text-fill:grey;");
		bResume.setStyle("-fx-font: 50 arial; -fx-background-color:null; -fx-text-fill:grey;");
		paneBt.getChildren().addAll(bResume,bExit);
		paneBt.setAlignment(Pos.TOP_CENTER);
		pause.setAlignment(Pos.TOP_CENTER);
		btPane.setMargin(pause, new Insets(100,0,100,0));
		btPane.setTop(pause);
		btPane.setCenter(paneBt);
		root.getChildren().add(btPane);
		Scene scene = new Scene(root,900,700);
		scene.setOnKeyReleased(e->{
			switch(e.getCode()){
			case ESCAPE:
				resume.run();
				break;
			}
		});
		primaryStage.setScene(scene);
	}
	
	
	public static Text setTextFont(String s){
		Text text = TextBuilder.create().text(s).font(Font.font("新宋体", 50)).build();
		text.setFill(new LinearGradient(0, 0, 1, 2, true, CycleMethod.REPEAT, new
		         Stop[]{new Stop(0, Color.YELLOW), new Stop(0.5f, Color.BLUE)}));
		text.setFont(Font.font("黑体", FontWeight.BOLD,50));//斜体
		text.setStrokeWidth(2);
		text.setStroke(Color.WHITE);
		return text;
	}
	
	public static Text setTextFont2(String s){
		Text text = TextBuilder.create().text(s).font(Font.font("新宋体", 30)).build();
		text.setFill(new LinearGradient(0, 0, 1, 2, true, CycleMethod.REPEAT, new
		         Stop[]{new Stop(0, Color.WHITE), new Stop(0.5f, Color.GREY)}));
		text.setFont(Font.font("黑体", FontWeight.BOLD,30));//斜体
		text.setStrokeWidth(2);
		text.setStroke(Color.WHITE);
		return text;
	}

	public void loadMulplaySelectScene() {

		TextField ipTextField = new TextField("127.0.0.1");
		Button singlebtn = new Button("Single");
		Button serverbtn = new Button("multiplay-Server");
		Button clientbtn = new Button("multiplay-Client");
		Button setIPbtn = new Button("set remote IP");
		final StringProperty ipProps = new SimpleStringProperty();
		ipProps.bind(ipTextField.textProperty());
		singlebtn.setOnAction((e)->{
			startMenu();
		});
		serverbtn.setOnAction((e)->{
			startGame(true, true);
		});
		clientbtn.setOnAction((e)->{
			GameSession.remoteHost=ipProps.get();
			startGame(true, false);
		});
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(5);
		grid.setHgap(10);
		grid.setPadding(new Insets(5, 5, 5, 5));
		grid.add(new Label("ip:"), 0, 2);
		grid.add(ipTextField, 1, 2);
		grid.add(singlebtn, 3, 0);
		grid.add(serverbtn, 3, 1);
		grid.add(clientbtn, 3, 2);
		grid.add(setIPbtn, 2, 2);
		
		StackPane root = new StackPane();
		root.getChildren().add(grid);
		Scene scene = new Scene(root, 600, 300);
		primaryStage.setTitle("MAGEngine");
		primaryStage.setScene(scene);
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

}
