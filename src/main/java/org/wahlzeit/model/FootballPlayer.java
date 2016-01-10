package org.wahlzeit.model;

public class FootballPlayer {
	
	
	protected FootballPlayerType type;
	protected String name;
	protected int backNumber;
	
	/**
	 * @methodtype constructor
	 */
	
	protected FootballPlayer (FootballPlayerType type, String name, int backNumber) {
		this.type = type;
		this.name = name;
		this.backNumber = backNumber;
	}
	
	public int getID() {
		return name.hashCode();
	}
	
	
	/**
	 * @methodtype get
	 */
	
	public FootballPlayerType getType() {
		return type;
	}
	
	/**
	 * @methodtype set
	 */
	
	public void setType(FootballPlayerType type) {
		this.type = type;
	}
	
	/**
	 * @methodtype get
	 */
	
	public String getName() {
		return name;
	}
	
	/**
	 * @methodtype set
	 */
	
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @methodtype get
	 */
	
	public int getBackNumber() {
		return backNumber;
	}
	
	/**
	 * @methodtype set
	 */
	
	public void setBackNumber(int backNumber) {
		this.backNumber = backNumber;
	}
	
	
	

}
