package indi.megaastronic.chapter;

import java.util.Timer;
import java.util.TimerTask;

import indi.megaastronic.paint.MyCanvas;
import indi.megaastronic.util.ElementUtils;

public class Chapter1 extends AChapter {

	@Override
	public void design(Timer timer, MyCanvas staticCanvas, ElementUtils moveableEU) {
		ChapterUtils cu = new ChapterUtils(moveableEU);
				
		for(int i=1;i<=10;i++){
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					cu.slashDown(320, 240, 1);
				}
			}, i*100);
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					cu.slashDown(160, 200, 1);
				}
			}, i*500);
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					cu.slashDown(480, 280, 1);
				}
			}, i*50+10*250);
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					cu.slashDown(80, 280, 1);
				}
			}, (i+10)*150);
		}
	}

	

}
