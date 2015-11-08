package org.wahlzeit.model;

public class SphericCoordinate implements Coordinate {
	
	//Attributes
	private double latitude;
	private double longitude;
	private double radius;
	
	//Constructor
	public SphericCoordinate (double latitude, double longitude, double radius) {
		this.setLatitude(latitude);
		this.setLongitude(longitude);
		this.setRadius(radius);
	}
	
	//Getters & Setters
	
	/**
	 * @methodtype get
	 */

	public double getLatitude() {
		return latitude;
	}
	
	/**
	 * @methodtype set
	 */

	public void setLatitude(double latitude) {
		latitudeValidityChecker(latitude);
		this.latitude = latitude;
	}
	
	/**
	 * @methodtype get
	 */

	public double getLongitude() {
		return longitude;
	}
	
	/**
	 * 
	 * @param longitude
	 */

	public void setLongitude(double longitude) {
		longitudeValidityChecker(longitude);
		this.longitude = longitude;
	}
	
	//Methods
	
	/**
	 * Method to get the longitudinal distance. Appreciates that you can travel
	 * around a globe in two directions.
	 */
	
	public double getLongitudinalDistance (SphericCoordinate coordinate){
		//
		return Math.min(
				
				(Math.abs(this.getLongitude()) + Math.abs(coordinate.getLongitude())),
				(180-Math.abs(this.getLongitude())) + (180-Math.abs(coordinate.getLongitude())));
	}
	
	/**
	 * Method to get the latitudinal distance.
	 */
	
	public double getLatitudinalDistance (SphericCoordinate coordinate) {
		return Math.abs(this.latitude-coordinate.getLatitude());
	}
	
	/**
	 * Method that calculates the distance from longitudinal and latitudinal values.
	 */
	public SphericCoordinate getsimpleDistance (SphericCoordinate coordinate) {
		
		SphericCoordinate result = new SphericCoordinate(this.getLatitudinalDistance(coordinate), 
										   this.getLongitudinalDistance(coordinate), this.getRadius());		
		return result;
	}
	
	/**
	 * A method that determines which getDistanceMethod to use
	 * @methodtype query
	 * @methodproperity composed
	 * 
	 */
	
	public double getDistance (Coordinate coordinate) {
		if (coordinate instanceof SphericCoordinate) {
			 return doGetDistance((SphericCoordinate) coordinate);
		}
		return doGetDistance((CartesianCoordinate) coordinate);		
	}
	
	/**
	 * A method that calculates the distance from longitude and latitude in meters
	 * @methodtype
	 * @methodproperties 
	 */
	
	public double doGetDistance (SphericCoordinate coordinate) {
		
		if(this.radius < coordinate.getRadius()*0.995 || this.radius > coordinate.getRadius()*1.015) {
			throw new IllegalArgumentException("Can't compare places on different planets!");
		}
		
		double radLatitudeThisPosition = asRadiant(this.latitude);
		double radLongitudeThisPosition = asRadiant(this.longitude);
		double radLatitudeOtherPosition = asRadiant(coordinate.getLatitude());
		double radLongitudeOtherPosition = asRadiant(coordinate.getLongitude());
		
		return Math.acos(Math.sin(radLatitudeThisPosition)*Math.sin(radLatitudeOtherPosition) + 
						 Math.cos(radLatitudeThisPosition)*Math.cos(radLatitudeOtherPosition) *
						 Math.cos(radLongitudeOtherPosition-radLongitudeThisPosition))*radius;
	}
	
	public double doGetDistance (CartesianCoordinate cCoordinate) {
		SphericCoordinate sCoord = CartesianCoordinate.convertCartesianToSpheric(cCoordinate);
		return doGetDistance(sCoord);
	}
	
	/**
	 * Method to check if values to be entered in latitude are valid.
	 */
	
	public void latitudeValidityChecker (double latitude)
	{
		if(latitude > 90 || latitude < -90) {
			throw new IllegalArgumentException ("There are no Latitudes higher than 180.");
		}
	}
	
	/**
	 * Method to check if values to be entered in longitude are valid.
	 */
	
	public void longitudeValidityChecker (double longitude){
		if(longitude >=180 || longitude < -180) {
			throw new IllegalArgumentException ("THere are no Longitudes higher than 90.");
		}
		
	}
	
	/**
	 * A method that converts decimal numbers to radians
	 */
	
	public double asRadiant (double decimalNumber) {
		return Math.PI*decimalNumber/180;
	}

	/**
	 * @methodtype get
	 */
	public double getRadius() {
		return radius;
	}
	
	/**
	 * @methodtype set
	 */

	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	/**
	 * Method that checks if two coordinate refer to the same place. If the distance between places is
	 * less than 0.5km, it returns true
	 * 
	 * @methodtype boolean query method
	 */
	
	public boolean isEqual (Coordinate coordinate) {
		if (coordinate == null)
			return false;
		
		if (this.getDistance(coordinate) <= 0.5) {
			return true;
		}
		
		return false;		
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(radius);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SphericCoordinate other = (SphericCoordinate) obj;
		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
			return false;
		if (Double.doubleToLongBits(radius) != Double.doubleToLongBits(other.radius))
			return false;
		return true;
	}


}
