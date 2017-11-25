package magengine.chapter;

import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import javafx.application.Platform;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.BoxBlur;
import javafx.scene.paint.Color;
import magengine.bullet.APolygonBullet;
import magengine.bullet.Bullet;
import magengine.bullet.PresetColor;
import magengine.bullet.impl.ArrowBullet;
import magengine.bullet.impl.CircleBullet;
import magengine.bullet.impl.DefaultBullet;
import magengine.bullet.impl.HexagonBullet;
import magengine.bullet.impl.PlayerBullet;
import magengine.bullet.impl.StarBullet;
import magengine.chapter.util.AChapter;
import magengine.chapter.util.QuickDanmuku;
import magengine.danmuku.ReisenNonSpellCardDanmuku;
import magengine.danmuku.RingDanmuku;
import magengine.danmuku.RunAwayNuclearDanmuku;
import magengine.danmuku.StarDanmuku;
import magengine.danmuku.TriArcDanmuku;
import magengine.danmuku.UtsuhoNonSpellCard1;
import magengine.danmuku.WaveParticleDanmuku;
import magengine.danmuku.gs.Testing;
import magengine.danmuku.yt.FllowerArrayDanmuku;
import magengine.danmuku.yt.MulCircleDanmuku2;
import magengine.danmuku.yt.PinkBlueRainDanmuku;
import magengine.danmuku.yt.DeepSeaDanmuku;
import magengine.danmuku.yt.demo1;
import magengine.element.BaseElement;
import magengine.element.impl.Area;
import magengine.element.impl.BombCircleArea;
import magengine.element.impl.CircleArea;
import magengine.element.impl.InvertCircleArea;
import magengine.element.impl.Player;
import magengine.enemy.ADelayDanmukuEnemy;
import magengine.enemy.ALoopDanmukuEnemy;
import magengine.enemy.BeisimaiEnemy;
import magengine.enemy.DefaultEnemy;
import magengine.enemy.ElfEnemy;
import magengine.enemy.Enemy1;
import magengine.enemy.NandaketaEnemy;
import magengine.enemy.YanzhanEnemy;
import magengine.game.LogicExecutor;
import magengine.helper.Helper;
import magengine.helper.OvalHelper;
import magengine.launcher.ArcLauncherGroup;
import magengine.launcher.BurstLauncher;
import magengine.launcher.Launcher;
import magengine.launcher.OvalLauncherGroup;
import magengine.paint.MyCanvas;
import magengine.paint.MyCanvasSwitcher;
import magengine.ui.SceneManager;
import magengine.util.C;
import magengine.util.CollisionTeam;
import magengine.util.DI;
import magengine.util.ElementUtils;

import static java.lang.Math.*;

public class TestChapter extends AChapter {
	int midX = MyCanvas.CANVAS_WIDTH / 2;
	int midY = 200;
	QuickDanmuku quick = QuickDanmuku.getQuickDanmuku();
	Random r = new Random(C.SEED);
	LogicExecutor executor = LogicExecutor.getLogicExecutor();
	ElementUtils mEU = ((ElementUtils) DI.di().get("mEU"));
	@Override
	public void design(LogicExecutor exec, MyCanvas staticCanvas, ElementUtils mEU) {
		createElf(e1->{
			createStageBoss(e2->{});
		},4,1500);
//		createBossTest((e)->{});
//		性能测试
//		 for(int i=1;i<=1000;i++){
//		 new StarDanmuku().setDelay(700*i).delayExecute();
//		 }

	}
	
	
	private void createBossTest(Consumer<BaseElement> onEnd) {
		ALoopDanmukuEnemy boss = new BeisimaiEnemy(300, 100);
//		 ALoopDanmukuEnemy boss = new YanzhanEnemy(300,100);
//		 ALoopDanmukuEnemy boss = new NandaketaEnemy(300,100);
		boss.setHP(100);
		boss.setDanmukuStartDelay(1);
		boss.addDanmuku(new WaveParticleDanmuku(), WaveParticleDanmuku.DURATION+1000);
		mEU.add("testBoss", boss);
	}
	private void createStageBoss(Consumer<BaseElement> onEnd) {
		ALoopDanmukuEnemy bossM1 = new BeisimaiEnemy(300, -100);
		// ALoopDanmukuEnemy boss = new YanzhanEnemy(300,100);
		// ALoopDanmukuEnemy boss = new NandaketaEnemy(300,100);
		bossM1.setHP(200);
		bossM1.setDanmukuStartDelay(1000);
//		boss.addDanmuku(new FllowerArrayDanmuku(), FllowerArrayDanmuku.DURATION);
//		boss.addDanmuku(new DeepSeaDanmuku(), DeepSeaDanmuku.DURATION);
//		bossM1.addDanmuku(new PinkBlueRainDanmuku(), PinkBlueRainDanmuku.DURATION);
		bossM1.addDanmuku(new WaveParticleDanmuku(),WaveParticleDanmuku.DURATION);
		bossM1.addDanmuku(new UtsuhoNonSpellCard1(), UtsuhoNonSpellCard1.DURATION);
		bossM1.addDanmuku(new RunAwayNuclearDanmuku(), RunAwayNuclearDanmuku.DURATION + 2000);
		bossM1.setOnRemoveEvent((bsM1) -> {//一阶段结束
			ALoopDanmukuEnemy bossM2 = new BeisimaiEnemy(bsM1.getX(), bsM1.getY());
			bossM2.setDanmukuStartDelay(2000)
			.addDanmuku(new ReisenNonSpellCardDanmuku(), ReisenNonSpellCardDanmuku.DURATION + 1000)
			.addDanmuku(new PinkBlueRainDanmuku(), PinkBlueRainDanmuku.DURATION)
			.setHP(200);
			bossM2.setOnRemoveEvent(bsM2->{
				onEnd.accept(bsM2);
			});
			mEU.add("bossM2", bossM2);
			quick.moveTo(bossM2, 500, 300, 110);
		});
		bossM1.setMoveLoop(new double[][]{{100,500,300,340},{120,100,130,90}}, 4000, 1000);
		mEU.add("bossM1", bossM1);
	}
	
	/**
	 * 接受的consumer将会在最后一个 敌人 被remove时通过onRemove调用
	 */
	private void createElf(Consumer<BaseElement> onEnd,int elfCount,long interval) {
		ALoopDanmukuEnemy elf = null;
		for(int i = 1;i<=elfCount;i++){
			elf = new ElfEnemy(20, -110);
			elf.setDanmukuStartDelay(1000)
			.addDanmuku(new TriArcDanmuku(), TriArcDanmuku.DURATION)
			.setHP(10);
			final ALoopDanmukuEnemy fElf = elf;
			final int fi = i;
			executor.schedule(()->{
				mEU.add("elf:"+r.nextInt(), fElf);
				quick.moveTo(fElf, 1200, 150, 185, ()->{
						fElf.setVelocityX(58);
					});
				if(fi==elfCount){
					fElf.setOnRemoveEvent(onEnd);
				}
			}, interval*i);
		}

	}
}
