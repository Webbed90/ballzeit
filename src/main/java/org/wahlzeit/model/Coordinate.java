/*
 * Copyright (c) 2006-2009 by Dirk Riehle, http://dirkriehle.com
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */


package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;


/**
 * Created by Michael Weber on 24.10.2015
 * 
 * A class that assigns coordinates 
(defined as longitude and latitude) to a photo **/

public class Coordinate extends DataObject {
	
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
		return Math.min(
				
				(Math.abs(this.getLongitude()) + Math.abs(coordinate.getLongitude())),
				(180-Math.abs(this.getLongitude())) + (180-Math.abs(coordinate.getLongitude())));
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
	public Coordinate getsimpleDistance (Coordinate coordinate) {
		
		Coordinate result = new Coordinate(this.getLatitudinalDistance(coordinate), 
										   this.getLongitudinalDistance(coordinate));		
		return result;
	}
	
	/**
	 * A method that calculates the distance from longitude and latitude in meters
	 * @methodtype
	 * @methodproperties 
	 */
	
	public double getDistance (Coordinate coordinate) {
		double radLatitudeThisPosition = asRadiant(this.latitude);
		double radLongitudeThisPosition = asRadiant(this.longitude);
		double radLatitudeOtherPosition = asRadiant(coordinate.getLatitude());
		double radLongitudeOtherPosition = asRadiant(coordinate.getLongitude());
		
		return Math.acos(Math.sin(radLatitudeThisPosition)*Math.sin(radLatitudeOtherPosition) + 
						 Math.cos(radLatitudeThisPosition)*Math.cos(radLatitudeOtherPosition) *
						 Math.cos(radLongitudeOtherPosition-radLongitudeThisPosition))*6371;
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
	
	public double asRadiant (double decimalNumber) {
		return Math.PI*decimalNumber/180;
	}

}
