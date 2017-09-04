package indi.megaastronic.chapter;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import application.Main;
import indi.megaastronic.helper.Helper;
import indi.megaastronic.helper.OvalHelper;
import indi.megaastronic.chapter.util.AChapter;
import indi.megaastronic.chapter.util.QuickDanmuku;
import indi.megaastronic.paint.MyCanvas;
import indi.megaastronic.util.ElementUtils;

public class Chapter1 extends AChapter {
	private int i;
	private int count = 51;

	@Override
	public void design(ScheduledExecutorService sES, MyCanvas staticCanvas, ElementUtils mEU) {
		QuickDanmuku cu = new QuickDanmuku(mEU);
		long startTime = System.currentTimeMillis();

		Helper targetHelper = new OvalHelper(400, 300, 100, 20);
		Helper sourceHelper = new OvalHelper(200, 300, 100, 5);
		sES.schedule(() -> {
			mEU.add("targetHelper", targetHelper);
			mEU.add("sourceHelper", sourceHelper);
			// mEU.add("snipe"+r.nextInt(), new MissileBullet(x, y,
			// Player.getPlayer(), v));
		}, 1, TimeUnit.MILLISECONDS);
		double x = 400;// (r.nextDouble()*MyCanvas.CANVAS_WIDTH);
		double y = 300;// 200+(r.nextDouble()*200);
		double v = 0.5;// 0.7*r.nextDouble()+0.3;
		for (i = 1; i <= 50; i++) {
			for (int k = 1; k <= 1; k++) {

//				sES.schedule(() -> {
//					cu.slashTransform(300, 350, 1, new double[][] { { 1, 0 },
//						 { -1, 1 } });
					// mEU.add("snipe"+r.nextInt(),cu.snipe(sourceHelper,
					// targetHelper, 3));
//					 cu.slashTransformAcc(targetHelper.getX(),
//					 targetHelper.getY(), v, new double[][] { { 1, 0 }, { 1, 1
//					 } });
					// cu.slashTransformAcc(targetHelper.getX(),
//					 targetHelper.getY(), v * 1.1, new double[][] { { 1, 0 },
//					 { -1, 1 } });
					// mEU.add("missile"+r.nextInt(), new
					// MissileBullet(sourceHelper.getX(), sourceHelper.getY(),
					// targetHelper, v));
//				}, i * 200, TimeUnit.MILLISECONDS);

				 sES.schedule(() -> {
//				 cu.slashTransformAcc(x, y, v, new double[][] { { 1, 0 }, { 1,
//				 1 } });
				cu.slash(x, y, v);
				 }, i * 2000, TimeUnit.MILLISECONDS);
				
//				 sES.schedule(() -> {
//				 cu.slashTransformAcc(x, y, v * 1.1, new double[][] { { 1, 0
//				 }, { -1, 1 } });
//				 }, i * 2000 + 600, TimeUnit.MILLISECONDS);
			}
		}
		
		//
//		ArrayList<MissileBullet> mblist = new ArrayList<>();
//		for (i = 51; i <= 195; i++) {
//			sES.schedule(() -> {
//				MissileBullet tem = new MissileBullet(sourceHelper.getX(), sourceHelper.getY(), targetHelper, 0.2);
//				mblist.add(tem);
//				mEU.add("missile" + count++, tem);
//			}, i * 200, TimeUnit.MILLISECONDS);
//
//		}
//		sES.schedule(() -> {
//			for (MissileBullet mb : mblist) {
//				mb.setTarget(null);
//			}
//		}, 196 * 200, TimeUnit.MILLISECONDS);
//
//		for (i = 1; i <= 1000; i++) {
//			sES.schedule(() -> {
//				cu.slashTransformAcc(x, y, v, new double[][] { { 1, 0 }, { 1, 1 } });
//
//			},76*200+i * 2000, TimeUnit.MILLISECONDS);
//			sES.schedule(() -> {
//				cu.slashTransformAcc(x, y, v * 1.1, new double[][] { { 1, 0 }, { -1, 1 } });
//			}, 196*200+i * 2000 + 600, TimeUnit.MILLISECONDS);
//		}

		if (Main.DEBUG)
			System.out.println("chapter load completed!" + (System.currentTimeMillis() - startTime) + "ms");

	}

}
