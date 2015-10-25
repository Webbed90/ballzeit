package org.wahlzeit.model;

public class Coordinate {
	
	/**A class that assigns coordinates 
	(defined as longitude and latitude) to a photo **/
	
	//Attributes
	private double latitude;
	private double longitude;
	
	//Constructor
	public Coordinate (double latitude, double longitude) {
		this.setLatitude(latitude);
		this.setLongitude(longitude);
	}
	
	//Getters & Setters

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	//Functionality
	
	public double getLongitudinalDistance (Coordinate coordinate){
		//TO-DO
		return Math.min(Math.abs(this.longitude-coordinate.getLongitude()), 
						(180-Math.abs(this.getLongitude())+Math.abs(coordinate.getLongitude())));
	}
	
	public double getLatitudinalDistance (Coordinate coordinate) {
		//TO-DO
		return Math.abs(this.latitude-coordinate.getLatitude());
	}
	
	public Coordinate getDistance (Coordinate coordinate) {
		coordinate.validityChecker();;
		this.validityChecker();
		
		
		//TO-DO
		return coordinate;
	}
	
	public void validityChecker (){
		if(this.getLatitude() > 180 || this.getLatitude() < -180) {
			throw new IllegalArgumentException ("There are no Latitudes higher than 180");
		}
		if(this.getLongitude() >=90 || this.getLongitude() < -90) {
			throw new IllegalArgumentException ("THere are no Longitudes higher than 90");
		}
		
	}
	
	

}
