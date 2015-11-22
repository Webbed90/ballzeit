package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate {
	
	/**
	 * @methodtype get
	 */

	public double getDistance (Coordinate coordinate) {
		
		//precondition
		assert (coordinate != null);
		
		double distance = this.getCoordinate().doGetDistance(coordinate.getCoordinate());
		
		//postconditions
		assert (!Double.isNaN(distance));
		assert (distance <= Math.PI *this.getRadius());
		
		return distance;
	}
	
	/**
	 * @methodtype compare
	 */
	public boolean isEqual (Coordinate coordinate) {
		
		//precondition
		assert (coordinate != null);
		
		return this.getCoordinate().isEqual(coordinate.getCoordinate());
	}
	
	/**
	 * @methodtype get
	 */
	
	public abstract SphericCoordinate getCoordinate();
	
	/**
	 * @methodtype get
	 */
	
	public abstract double getLatitude();
	
	/**
	 * @methodtype get
	 */
	
	public abstract double getLongitude();
	
	/**
	 * @methodtype get
	 */
	
	public abstract double getRadius();
}
