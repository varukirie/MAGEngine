package magengine.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public enum CollisionTeam {
		ENEMY_BULLET,PLAYER,PLAYER_BULLET,ENEMY,NO_TEAM;	
	private static final HashMap<CollisionTeam,ArrayList<CollisionTeam>> rules = new HashMap<>();
	static{
		Arrays.asList(CollisionTeam.values()).forEach((e)->{
			rules.put(e, new ArrayList<>());
		});
		rules.get(ENEMY_BULLET).add(PLAYER);
		rules.get(PLAYER).add(CollisionTeam.ENEMY_BULLET);
		rules.get(PLAYER_BULLET).add(ENEMY);
		rules.get(ENEMY).add(PLAYER_BULLET);
		rules.get(ENEMY).add(PLAYER);
////避免双重判定		rules.get(PLAYER).add(ENEMY);
	}
	public static boolean shouldCollision(CollisionTeam teamFromCheckList,CollisionTeam team2){
		return rules.get(teamFromCheckList).contains(team2)||teamFromCheckList.equals(NO_TEAM);
//		return true;
	}
}
