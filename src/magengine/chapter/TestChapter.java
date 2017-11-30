package magengine.chapter;

import java.util.Random;
import java.util.function.Consumer;

import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import magengine.chapter.util.AChapter;
import magengine.chapter.util.QuickDanmuku;
import magengine.control.PlayerControlHandler;
import magengine.danmuku.ReisenNonSpellCardDanmuku;
import magengine.danmuku.RunAwayNuclearDanmuku;
import magengine.danmuku.TriArcDanmuku;
import magengine.danmuku.UtsuhoNonSpellCard1;
import magengine.danmuku.WaveParticleDanmuku;
import magengine.danmuku.yt.PinkBlueRainDanmuku;
import magengine.element.BaseElement;
import magengine.enemy.ALoopDanmukuEnemy;
import magengine.enemy.BossbuEnemy;
import magengine.enemy.ButterflyElfEnemy;
import magengine.game.GameSession;
import magengine.game.LogicExecutor;
import magengine.paint.MyCanvas;
import magengine.ui.SceneManager;
import magengine.util.BGMUtil;
import magengine.util.C;
import magengine.util.DI;
import magengine.util.ElementUtils;

public class TestChapter extends AChapter {
	int midX = MyCanvas.CANVAS_WIDTH / 2;
	int midY = 200;
	QuickDanmuku quick = QuickDanmuku.getQuickDanmuku();
	Random r = new Random(C.SEED);
	LogicExecutor exec = LogicExecutor.getLogicExecutor();
	ElementUtils mEU = ((ElementUtils) DI.di().get("mEU"));
	
	@Override
	public void design(LogicExecutor exec, MyCanvas staticCanvas, ElementUtils mEU) {
		BGMUtil.loadResource("/music/arrival.mp3");
		BGMUtil.play();
		createElf(elf1 -> {
			exec.schedule(()->{
				createElf(elf2 -> {
					createStageBoss(boss1 -> {
						clearGame();
					});
				},4,1500,false);
			}, 2000);
		}, 4, 1500,true);
//		createBossTest((e) -> {
//			clearGame();
//		});
		// 性能测试
		// for(int i=1;i<=1000;i++){
		// new StarDanmuku().setDelay(700*i).delayExecute();
		// }

	}

	private void createBossTest(Consumer<BaseElement> onEnd) {
		// ALoopDanmukuEnemy boss = new BeisimaiEnemy(300, 100);

		ALoopDanmukuEnemy boss = new BossbuEnemy(300, 100);
		boss.setMoveLoop(new double[][] { { 300, 350, 240, 370 }, { 100, 110, 110, 100 } }, 3000, 2000);
		// ALoopDanmukuEnemy boss = new NandaketaEnemy(300,100);
		boss.setHP(20);
		boss.setDanmukuStartDelay(1);
		boss.addDanmuku(new WaveParticleDanmuku(), WaveParticleDanmuku.DURATION);
		// boss.addDanmuku(new BoomDemoDanmuku(),BoomDemoDanmuku.DURATION);
		boss.setOnRemoveEvent(onEnd);
		mEU.add("testBoss", boss);
	}

	private void createStageBoss(Consumer<BaseElement> onEnd) {
		// ALoopDanmukuEnemy bossM1 = new BeisimaiEnemy(300, -100);
		ALoopDanmukuEnemy bossM1 = new BossbuEnemy(300, -50);
		// ALoopDanmukuEnemy boss = new NandaketaEnemy(300,100);
		bossM1.setHP(300);
		bossM1.setDanmukuStartDelay(1000);
		// boss.addDanmuku(new FllowerArrayDanmuku(),
		// FllowerArrayDanmuku.DURATION);
		// boss.addDanmuku(new DeepSeaDanmuku(), DeepSeaDanmuku.DURATION);
		// bossM1.addDanmuku(new PinkBlueRainDanmuku(),
		// PinkBlueRainDanmuku.DURATION);
		bossM1.addDanmuku(new UtsuhoNonSpellCard1(), UtsuhoNonSpellCard1.DURATION);
		bossM1.addDanmuku(new RunAwayNuclearDanmuku(), RunAwayNuclearDanmuku.DURATION + 2000);
		bossM1.setOnRemoveEvent((bsM1) -> {// 一阶段结束
			Platform.runLater(() -> {
				SceneManager.getInstance().shakeInScene(150);
			});
			ALoopDanmukuEnemy bossM2 = new BossbuEnemy(bsM1.getX(), bsM1.getY());
			bossM2.setDanmukuStartDelay(1500).addDanmuku(new WaveParticleDanmuku(), WaveParticleDanmuku.DURATION)
					.addDanmuku(new ReisenNonSpellCardDanmuku(), ReisenNonSpellCardDanmuku.DURATION + 1000)
					.addDanmuku(new PinkBlueRainDanmuku(), PinkBlueRainDanmuku.DURATION)
					.setHP(700);
			bossM2.setOnRemoveEvent(bsM2 -> {
				onEnd.accept(bsM2);
			});
			mEU.add("bossM2", bossM2);
			quick.moveTo(bossM2, 500, 300, 110);
		});
		bossM1.setMoveLoop(new double[][] { { 100, 500, 300, 340 }, { 120, 100, 130, 90 } }, 4000, 1000);
		mEU.add("bossM1", bossM1);
	}

	/**
	 * 接受的consumer将会在最后一个 敌人 被remove时通过onRemove调用
	 */
	private void createElf(Consumer<BaseElement> onEnd, int elfCount, long interval,boolean left) {
		ALoopDanmukuEnemy elf = null;
		for (int i = 1; i <= elfCount; i++) {
			if(left){
				elf = new ButterflyElfEnemy(20, -110);
			}else{
				elf = new ButterflyElfEnemy(MyCanvas.CANVAS_WIDTH-20, -110);
			}
			
			elf.setDanmukuStartDelay(1000).addDanmuku(new TriArcDanmuku(), TriArcDanmuku.DURATION).setHP(10);
			final ALoopDanmukuEnemy fElf = elf;
			final int fi = i;
			exec.schedule(() -> {
				mEU.add("elf:" + r.nextInt(), fElf);
				if(left){
					quick.moveTo(fElf, 1200, 150, 185, () -> {
						fElf.setVelocityX(58);
					});
				}else{
					quick.moveTo(fElf, 1200, MyCanvas.CANVAS_WIDTH-150, 185, () -> {
						fElf.setVelocityX(-58);
					});
				}
				if (fi == elfCount) {
					fElf.setOnRemoveEvent(onEnd);
				}
			}, interval * i);
		}

	}
	
	
	private void clearGame(){
		PlayerControlHandler.getInstance().doBomb();
		Platform.runLater(() -> {
			SceneManager.getInstance().shakeInScene();
		});
		exec.schedule(() -> {
			Platform.runLater(() -> {
				SceneManager.getInstance().loadClearScene();
				GameSession.closeGameSession();
			});

		}, 2000);
	}
}
