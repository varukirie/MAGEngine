chapter{
	def enemyConfig = {
		setX 200
		setY 100
		setDanmukuStartDelay(1000)
		addDanmuku(new TriArcDanmuku(), TriArcDanmuku.DURATION)
		setHP(100)
	}
	def enemy1 = enemy(enemyConfig,"magengine.enemy.impl.ButterflyElfEnemy")
	def enemy2 = enemy(enemyConfig,"magengine.enemy.impl.ButterflyElfEnemy")
	def enemy3 = enemy(enemyConfig,"magengine.enemy.impl.ButterflyElfEnemy")
	
	enemy2.setX(400)
	enemy3.setX(300)//站中间
	
	enemy3.setOnRemoveEvent($Con{//打死站中间的敌人后结束游戏
		clearGame();
	});
	//装载敌人
	load enemy1
	load enemy2
	load enemy3
	BGMClasspathLoad("/music/arrival.mp3")
	BGMPlay()
}
println "Groovy sheet executed"