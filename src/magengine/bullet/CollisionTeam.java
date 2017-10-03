package magengine.bullet;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public enum CollisionTeam {
		ENEMY_BULLET,PLAYER,PLAYER_BULLET,ENEMY;	
	private static final HashMap<CollisionTeam,ArrayList<CollisionTeam>> rules = new HashMap<>();
	static{
		Arrays.asList(CollisionTeam.values()).forEach((e)->{
			rules.put(e, new ArrayList<>());
		});
		rules.get(ENEMY_BULLET).add(PLAYER);
		rules.get(PLAYER).add(CollisionTeam.ENEMY_BULLET);
	}
	public static boolean shouldCollision(CollisionTeam team1,CollisionTeam team2){
		return rules.get(team1).contains(team2);
	}
}
