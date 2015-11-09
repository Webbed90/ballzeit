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
	
	public static Coordinate createCoordinate (double latOrX, double longOrY, double radOrZ, boolean isSpheric) {
		if (isSpheric) {
			return new SphericCoordinate (latOrX,longOrY,radOrZ);
		}
			return new CartesianCoordinate (latOrX, longOrY, radOrZ);
	}
	
	

}