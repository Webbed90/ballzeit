package org.wahlzeit.model;

public class Location {
	private String name;
	private Coordinate coordinates;
	
	/**
	 * @methodtype constructor
	 */
	public Location (String name, Coordinate coordinate) {
		this.name = name;
		this.coordinates = coordinate;
	}
	
	/**
	 * @methodtype get
	 * @methodproperty primitive
	 */
	
	public String getName() {
		return name;
	}
	
	/**
	 * @methodtype set
	 * @methodproperty primitive
	 */
	
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @methodtype get
	 * @methodproperty primitive
	 */
	
	public Coordinate getCoordinate() {
		return this.coordinates;
	}
	
	/**
	 * @methodtype set
	 * @methodproperty primitive
	 */
	
	public void setCoordinate(double latitude, double longitude) {
		this.coordinates.setLatitude(latitude);
		this.coordinates.setLongitude(longitude);
	}
	
	

}
