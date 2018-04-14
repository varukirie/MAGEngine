package magengine.chapter;

import java.util.Random;
import java.util.function.Consumer;


import javafx.application.Platform;
import magengine.chapter.util.AChapter;
import magengine.chapter.util.QuickDanmuku;
import magengine.danmuku.RayzerDanmuku;
import magengine.danmuku.PinkBlueRainDanmuku;
import magengine.danmuku.ReisenNonSpellCardDanmuku;
import magengine.danmuku.RingDanmuku;
import magengine.danmuku.RunAwayNuclearDanmuku;
import magengine.danmuku.StarDanmuku;
import magengine.danmuku.TriArcDanmuku;
import magengine.danmuku.TriBurstDmk;
import magengine.danmuku.UtsuhoNonSpellCard1;
import magengine.danmuku.WaveParticleDanmuku;
import magengine.element.BaseElement;
import magengine.enemy.ALoopDanmukuEnemy;
import magengine.enemy.BossbuEnemy;
import magengine.enemy.ButterflyElfEnemy;
import magengine.game.LogicExecutor;

import magengine.paint.MyCanvas;
import magengine.ui.SceneManager;
import magengine.util.BGMUtil;
import magengine.util.C;
import magengine.util.DI;
import magengine.util.ElementUtils;
import magengine.util.SoundUtil;

public class TestChapter extends AChapter{
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
					createMidBossElf(elf3->{
						createStageBoss(boss1 -> {
							clearGame();
						});
					});
				},4,1500,false);
			}, 500);
		}, 4, 1500,true);
//		createMidBossElf(elf3->{
//		});
		
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
//		boss.addDanmuku(new WaveParticleDanmuku(), WaveParticleDanmuku.DURATION);
		 boss.addDanmuku(new UtsuhoNonSpellCard1(),UtsuhoNonSpellCard1.DURATION);
		boss.setOnRemoveEvent(onEnd);
		mEU.add("testBoss", boss);
	}

	private void createStageBoss(Consumer<BaseElement> onEnd) {
		ALoopDanmukuEnemy bossM1 = new BossbuEnemy(300, -50);
		bossM1.setHP(400);
		bossM1.setDanmukuStartDelay(1000);

		bossM1.addDanmuku(new UtsuhoNonSpellCard1(), UtsuhoNonSpellCard1.DURATION);
		bossM1.addDanmuku(new RunAwayNuclearDanmuku(), RunAwayNuclearDanmuku.DURATION + 2000);
		bossM1.setOnRemoveEvent((bsM1) -> {// 一阶段结束
			SoundUtil.getInstance().play("glass");
			Platform.runLater(() -> {
				SceneManager.getInstance().shakeInScene(150);
			});
			ALoopDanmukuEnemy bossM2 = new BossbuEnemy(bsM1.getX(), bsM1.getY());
			bossM2.setDanmukuStartDelay(1500).addDanmuku(new WaveParticleDanmuku(), WaveParticleDanmuku.DURATION)
					.addDanmuku(new ReisenNonSpellCardDanmuku(), ReisenNonSpellCardDanmuku.DURATION + 1000)
					.setHP(550);
			bossM2.setOnRemoveEvent(bsM2 -> {
				SoundUtil.getInstance().play("glass");
				Platform.runLater(() -> {
					SceneManager.getInstance().shakeInScene(250);
				});
				ALoopDanmukuEnemy bossM3 = new BossbuEnemy(bsM2.getX(), bsM2.getY());
				bossM3.setDanmukuStartDelay(1000)
				.addDanmuku(new RayzerDanmuku(),RayzerDanmuku.DURATION)	
				.addDanmuku(new PinkBlueRainDanmuku(), PinkBlueRainDanmuku.DURATION)		
				.setHP(500);
				bossM3.setOnRemoveEvent(onEnd);
				bossM3.setMoveLoop(new double[][] { { 100, 500, 300, 340 }, { 120, 100, 130, 90 } }, 4000, 1000);
				mEU.add("bossM3", bossM3);
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
	
	public void createMidBossElf(Consumer<BaseElement> onEnd){
		createMidBossElfAround(50);
		createMidBossElfAround(550);
		ALoopDanmukuEnemy elf = null;
		elf = new ButterflyElfEnemy(300, -110);
		elf.setDanmukuStartDelay(1500);
		elf.setMoveLoop(new double[][]{{250,350,300},{90,120,100}}, 1000, 2000);
		elf.addDanmuku(new TriBurstDmk(), TriBurstDmk.DURATION)
		.addDanmuku(new StarDanmuku(),StarDanmuku.DURATION)
		.addDanmuku(new StarDanmuku(),StarDanmuku.DURATION+3000)
		.addDanmuku(new RingDanmuku(), RingDanmuku.DURATION)
		.setHP(600);
		elf.setOnRemoveEvent((e)->{
			quick.createBombItem(e.getX(), e.getY());
			SceneManager.getInstance().shakeInScene();
			onEnd.accept(e);
		});
		mEU.add("midBossElf", elf);
	}
	
	private void createMidBossElfAround(double x){
		ALoopDanmukuEnemy elf = null;
		elf = new ButterflyElfEnemy(x, -60);
		elf.setVelocityY(120);
		elf.setDanmukuStartDelay(2000);
		elf.addDanmuku(new TriBurstDmk(), TriBurstDmk.DURATION+1000)
		.setHP(10);
		mEU.add("midBossElfAround"+r.nextInt(), elf);
	}



}
