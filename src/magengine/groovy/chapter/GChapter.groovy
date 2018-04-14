chapter{
	load enemy({
		setX 200
		setY 300
		setDanmukuStartDelay(1000)
		addDanmuku(new TriArcDanmuku(), TriArcDanmuku.DURATION)
		setHP(10)
	},"magengine.enemy.impl.ButterflyElfEnemy")
	
	BGMClasspathLoad("/music/arrival.mp3")
	BGMPlay()
}
println "Groovy sheet executed"