package org.wahlzeit.model;

public class FootballPhoto extends Photo {
	
	private String club;
	private int birthyear;
	private FootballPlayer player;
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
	 * @methodtype Constructor
	 */
	public FootballPhoto (FootballPlayer player) {
		this.player = player;
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

	public FootballPlayer getPlayer() {
		return player;
	}
	
	/**
	 * @methodtype set
	 */

	public void setPlayerName(FootballPlayer player) {
		this.player = player;
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
