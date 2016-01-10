package org.wahlzeit.model;

import java.util.HashSet;
import java.util.Set;

public class FootballPlayerType {
	
	protected String position;
	protected String preferredFoot;
	protected boolean isProfessional;
	
	/**
	 * @methodtype constructor
	 */
	
	protected FootballPlayerType (String position, String preferredFoot, boolean isProfessional) {
		this.position = position;
		this.preferredFoot = preferredFoot;
		this.isProfessional = isProfessional;
	}
	
	public FootballPlayer createInstance (String name, int backNumber) {
		return new FootballPlayer(this, name, backNumber);
	}
	
	public int getID() {
		return position.hashCode();
	}
	
	/**
	 * @methodtype get
	 */

	public String getPosition() {
		return position;
	}
	
	/**
	 * @methodtype set
	 */

	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * @methodtype get
	 */
	
	public String getPreferredFoot() {
		return preferredFoot;
	}

	
	/**
	 * @methodtype set
	 */
	
	public void setPreferredFoot(String preferredFoot) {
		this.preferredFoot = preferredFoot;
	}
	
	/**
	 * @methodtype get
	 */

	public boolean isProfessional() {
		return isProfessional;
	}

	/**
	 * @methodtype set
	 */
	
	public void setProfessional(boolean isProfessional) {
		this.isProfessional = isProfessional;
	}
	
	
	
	

}
