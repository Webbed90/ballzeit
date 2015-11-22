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
	 * @methodtype set
	 */

	public void setLongitude(double longitude) {
		//precondition
		assertLongitudeValidity(longitude);
		this.longitude = longitude;
		assertClassInvariants();
	}
	
	/**
	 * @methodtype get
	 */
	
	public SphericCoordinate getSphericCoordinate() {
		assertClassInvariants();
		return this;
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
	 * Method to check if values to be entered in latitude are valid.
	 * @methodtype assertion
	 */
	
	public void assertLatitudeValidity (double latitude)
	{
		if(latitude >= 90 || latitude < -90) {
			throw new IllegalArgumentException ("There are no Latitudes higher than 90.");
		}
	}
	
	/**
	 * Method to check if values to be entered in longitude are valid.
	 * @methodtype assertion
	 */
	
	public void assertLongitudeValidity (double longitude){
		if(longitude >=180 || longitude < -180) {
			throw new IllegalArgumentException ("THere are no Longitudes higher than 180.");
		}
		
	}

}
