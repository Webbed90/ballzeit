package org.wahlzeit.model;

public class SphericCoordinate extends AbstractCoordinate {
	
	private double latitude;
	private double longitude;
	private double radius;
	
	public SphericCoordinate (double latitude, double longitude, double radius) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.radius = radius;
		
		assertClassInvariants();
		
	}
		
	/**
	 * @methodtype get
	 */

	public double getLatitude() {
		assertClassInvariants();
		return latitude;
	}
	
	/**
	 * @methodtype set
	 */

	public void setLatitude(double latitude) {
		assertLatitudeValidity(latitude);
		this.latitude = latitude;
	}
	
	/**
	 * @methodtype get
	 */

	public double getLongitude() {
		assertClassInvariants();
		return longitude;
	}
	
	/**
	 * 
	 * @param longitude
	 */

	public void setLongitude(double longitude) {
		//precondition
		assertLongitudeValidity(longitude);
		
		this.longitude = longitude;
				
		assertClassInvariants();
	}
	
	public SphericCoordinate getCoordinate() {
		assertClassInvariants();
		return this;
	}
	
	/**
	 * A method that calculates the distance from longitude and latitude in meters
	 * @methodtype
	 * @methodproperties 
	 */
	
	public double doGetDistance (SphericCoordinate coordinate) {
		
		//preconditions
		assertClassInvariants();
		coordinate.assertClassInvariants();
		assertHasSameRadius(coordinate);
		
		double radLatitudeThisPosition = Math.toRadians(this.latitude);
		double radLongitudeThisPosition = Math.toRadians(this.longitude);
		double radLatitudeOtherPosition = Math.toRadians(coordinate.getLatitude());
		double radLongitudeOtherPosition = Math.toRadians(coordinate.getLongitude());
		
		double distance = Math.acos(Math.sin(radLatitudeThisPosition)*Math.sin(radLatitudeOtherPosition) + 
						  Math.cos(radLatitudeThisPosition)*Math.cos(radLatitudeOtherPosition) *
						  Math.cos(radLongitudeOtherPosition-radLongitudeThisPosition))*radius;
		
		//postconditions
		assertClassInvariants();
		assert (distance <= Math.PI *this.getRadius() && distance >= 0);
		
		
		return distance;
	}

	/**
	 * @methodtype get
	 */
	public double getRadius() {
		assertClassInvariants();
		return radius;
	}
	
	/**
	 * @methodtype set
	 */

	public void setRadius(double radius) {
		assert (radius >= 0);
		this.radius = radius;
		assertClassInvariants();
		
	}
	
	/**
	 * Method that checks if two coordinate refer to the same place. If the distance between places is
	 * less than 0.5km, it returns true
	 * 
	 * @methodtype boolean query method
	 */
	
	public boolean isEqual (SphericCoordinate coordinate) {
		
		//preconditions
				assertClassInvariants();
				coordinate.assertClassInvariants();
				assertHasSameRadius(coordinate);
		
		if (this.getDistance(coordinate) <= 0.5) {
			return true;
		}
		
		return false;		
	}
	
	/**
	 * @methodtype assert
	 */
	
	protected void assertClassInvariants() {
		assert (this != null);
		assert (radius >= 0);
		assert (latitude <= 90 && latitude > -90);
		assert (longitude <= 180 && longitude > -180);
	}
	
	/**
	 * Method to check if values to be entered in latitude are valid.
	 * @methodtype assertion
	 */
	
	public void assertLatitudeValidity (double latitude)
	{
		if(latitude >= 90 || latitude < -90) {
			throw new IllegalArgumentException ("There are no Latitudes higher than 180.");
		}
	}
	
	/**
	 * Method to check if values to be entered in longitude are valid.
	 * @methodtype assertion
	 */
	
	public void assertLongitudeValidity (double longitude){
		if(longitude >=180 || longitude < -180) {
			throw new IllegalArgumentException ("THere are no Longitudes higher than 90.");
		}
		
	}
	
	/**
	 * @methodtype assertion
	 */
	
	public void assertHasSameRadius(Coordinate coordinate) throws IllegalArgumentException {
		if(this.getRadius() < coordinate.getRadius()*0.995 || 
		   this.getRadius() > coordinate.getRadius()*1.015) {
			throw new IllegalArgumentException("Can't compare places on different planets!");
		}
	}


}
