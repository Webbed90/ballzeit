package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate {

	public double getDistance (Coordinate coordinate) {
		return this.getCoordinate().doGetDistance(coordinate.getCoordinate());
	}
	
	public boolean isEqual (Coordinate coordinate) {
		return this.getCoordinate().isEqual(coordinate.getCoordinate());
	}
	
	public abstract SphericCoordinate getCoordinate();
	
	public abstract double getLatitude();
	public abstract double getLongitude();
	public abstract double getRadius();
}
