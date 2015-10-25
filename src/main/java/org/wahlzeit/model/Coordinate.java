package org.wahlzeit.model;


/**A class that assigns coordinates 
(defined as longitude and latitude) to a photo **/

public class Coordinate {
	
	//Attributes
	private double latitude;
	private double longitude;
	
	//Constructor
	public Coordinate (double latitude, double longitude) {
		this.setLatitude(latitude);
		this.setLongitude(longitude);
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
	
	public double getLongitudinalDistance (Coordinate coordinate){
		//
		return Math.min(Math.abs(this.longitude-coordinate.getLongitude()), 
					   (180-Math.abs(this.getLongitude()) +
					    Math.abs(coordinate.getLongitude())));
	}
	
	/**
	 * Method to get the latitudinal distance.
	 */
	
	public double getLatitudinalDistance (Coordinate coordinate) {
		return Math.abs(this.latitude-coordinate.getLatitude());
	}
	
	/**
	 * Method that calculates the distance from longitudinal and latitudinal values.
	 */
	public double getDistance (Coordinate coordinate) {
		
		double distance;
		
		distance = Math.sqrt(Math.pow(this.getLatitudinalDistance(coordinate), 2) +
				   Math.pow(this.getLongitudinalDistance(coordinate), 2));
		
		return distance;
	}
	
	/**
	 * Method to check if values to be entered in latitude are valid.
	 */
	
	public void latitudeValidityChecker (double latitude)
	{
		if(latitude > 90 || latitude < -90) {
			throw new IllegalArgumentException ("There are no Latitudes higher than 180");
		}
	}
	
	/**
	 * Method to check if values to be entered in longitude are valid.
	 */
	
	public void longitudeValidityChecker (double longitude){
		if(longitude >=180 || longitude < -180) {
			throw new IllegalArgumentException ("THere are no Longitudes higher than 90");
		}
		
	}
	
	

}
