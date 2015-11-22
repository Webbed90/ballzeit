package org.wahlzeit.model;

public class FootballPhoto extends Photo {
	
	private String club;
	private int birthyear;
	private String playerName;
	private boolean isActive;
	private String nationality;
	private int scoredGoals;
	
	/**
	 * @methodtype Constructor
	 * @methodproperty primitive
	 */
	
	public FootballPhoto () {
		super();
		//do nothing
	}
	
	/**
	 * @methodtype Constructor
	 * @methodproperty primitive
	 */
	
	public FootballPhoto (PhotoId id) {
		super(id);
	}
	
	/**
	 * @methodtype Constructor
	 */
	public FootballPhoto (PhotoId id, Location location) {
		super(id, location);
	}
	
	/**
	 * @methodtype Constructor
	 */
	public FootballPhoto (Location location) {
		super(location);
	}
	
	/**
	 * @methodtype get
	 */

	public String getClub() {
		return club;
	}
	
	/**
	 * @methodtype set
	 */

	public void setClub(String club) {
		this.club = club;
	}
	
	/**
	 * @methodtype get
	 */

	public int getBirthyear() {
		return birthyear;
	}
	
	/**
	 * @methodtype set
	 */

	public void setBirthyear(int birthyear) {
		this.birthyear = birthyear;
	}
	
	/**
	 * @methodtype get
	 */

	public boolean isActive() {
		return isActive;
	}
	
	/**
	 * @methodtype set
	 */

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	/**
	 * @methodtype get
	 */

	public String getPlayerName() {
		return playerName;
	}
	
	/**
	 * @methodtype set
	 */

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	/**
	 * @methodtype get
	 */

	public int getScoredGoals() {
		return scoredGoals;
	}
	
	/**
	 * @methodtype set
	 */

	public void setScoredGoals(int scoredGoals) {
		this.scoredGoals = scoredGoals;
	}
	
	/**
	 * @methodtype get
	 */

	public String getNationality() {
		return nationality;
	}
	
	/**
	 * @methodtype set
	 */

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	

}
