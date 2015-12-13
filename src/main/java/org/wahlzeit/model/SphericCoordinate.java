package org.wahlzeit.model;

public class SphericCoordinate extends AbstractCoordinate {
	
	private final double LATITUDE;
	private final double LONGITUDE;
	private final double RADIUS;
	
	public SphericCoordinate (double latitude, double longitude, double radius) {
		this.LATITUDE = latitude;
		this.LONGITUDE = longitude;
		this.RADIUS = radius;
		
		assertClassInvariants();
		
	}
		
	/**
	 * @methodtype get
	 */
	@Override
	public double getLatitude() {
		assertClassInvariants();
		return LATITUDE;
	}
	
	/**
	 * @methodtype set
	 */

	public SphericCoordinate setLatitude(double latitude) {
		assertLatitudeValidity(latitude);
		return new SphericCoordinate(latitude, this.LONGITUDE, this.RADIUS);
	}
	
	/**
	 * @methodtype get
	 */
	@Override
	public double getLongitude() {
		assertClassInvariants();
		return LONGITUDE;
	}
	
	/**
	 * @methodtype set
	 */

	public SphericCoordinate setLongitude(double longitude) {
		//precondition
		assertLongitudeValidity(longitude);
		return new SphericCoordinate(this.LATITUDE, longitude, this.RADIUS);
	}
	
	/**
	 * @methodtype get
	 */
	@Override
	public SphericCoordinate getSphericCoordinate() {
		assertClassInvariants();
		return this;
	}
	

	/**
	 * @methodtype get
	 */
	@Override
	public double getRadius() {
		assertClassInvariants();
		return RADIUS;
	}
	
	/**
	 * @methodtype set
	 */

	public SphericCoordinate setRadius(double radius) {
		assert (radius >= 0);
		return new SphericCoordinate(this.LATITUDE, this.LONGITUDE, radius);
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
