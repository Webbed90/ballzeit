package org.wahlzeit.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FootballPlayerManager {
	
	private static FootballPlayerManager instance;
	
	protected Map<Integer, FootballPlayer> footballPlayers = new HashMap<Integer, FootballPlayer>();
	protected Map<Integer, FootballPlayerType> footballPlayerTypes = new HashMap<Integer, FootballPlayerType>();

	/**
	 * 
	 */
	
	public static FootballPlayerManager getInstance() {
		if(instance == null) {
			instance = new FootballPlayerManager();
		}
		return instance;
	}
	
	/**
	 * 
	 */
	
	public void setInstance(FootballPlayerManager manager) {
		if(instance != null) {
			throw new IllegalStateException("Attempt to initialize second PlayerManager");
		}
		instance = manager;
	}
	
	/**
	 * 
	 */
	
	public FootballPlayer getFootballPlayer(String name) {
		if(footballPlayers.containsKey(name.hashCode())) {
			return footballPlayers.get(name.hashCode());
		}
		return null;
	}
	
	/**
	 * 
	 */
	
	public FootballPlayer createFootballPlayer (String position, String name, int backNumber) {
		FootballPlayerType fpt = getFootballPlayerType(position);
		assert (fpt != null);
		FootballPlayer result = fpt.createInstance(name, backNumber);
		footballPlayers.put(result.getID(),result);
		return result;
	}
	
	/**
	 * 
	 */
	
	public FootballPlayerType getFootballPlayerType(String position) {
		if (footballPlayerTypes.containsKey(position.hashCode())) {
			return footballPlayerTypes.get(position.hashCode());
		}
		return null;
	}
	
	/**
	 * 
	 */
	
	public FootballPlayerType createFootballPlayerType(String position, String preferredFoot, boolean isProfessional) {
		assert (getFootballPlayerType(position) == null) : "PlayerType already exists";
		FootballPlayerType result = new FootballPlayerType(position, preferredFoot, isProfessional);
		footballPlayerTypes.put(result.getID(), result);
		return result;
	}

}
