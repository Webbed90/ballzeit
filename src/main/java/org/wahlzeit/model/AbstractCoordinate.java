package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate {


	/**
	 * @methodtype conversion
	 */

	public abstract SphericCoordinate getSphericCoordinate();
	
	/**
	 * @methodtype get
	 */

	public double getLatitude() {
		return this.getSphericCoordinate().getLatitude();
	}

	/**
	 * @methodtype get
	 */

	public double getLongitude() {
		return this.getSphericCoordinate().getLongitude();
	}

	/**
	 * @methodtype get
	 */

	public double getRadius() {
		return this.getSphericCoordinate().getRadius();
	}

	/**
	 * A getDistance Method that converts all coordinates to SphericOnes and then calls doGetDistance.
	 * @methodtype get
	 */

	public double getDistance (Coordinate coordinate) {

		//precondition
		assert (coordinate != null);

		double distance = this.getSphericCoordinate().doGetDistance(coordinate.getSphericCoordinate());

		//postconditions
		assert (!Double.isNaN(distance));
		assert (distance <= Math.PI *this.getRadius());

		return distance;
	}

	/**
	 * A method that calculates the distance from longitude and latitude in kilometers
	 * @methodtype
	 * @methodproperties 
	 */

	protected double doGetDistance (SphericCoordinate coordinate) {

		//preconditions
		assertClassInvariants();
		coordinate.assertClassInvariants();
		assertHasSameRadius(coordinate);

		double radLatitudeThisPosition = Math.toRadians(getLatitude());
		double radLongitudeThisPosition = Math.toRadians(getLongitude());
		double radLatitudeOtherPosition = Math.toRadians(coordinate.getLatitude());
		double radLongitudeOtherPosition = Math.toRadians(coordinate.getLongitude());

		double distance = Math.acos(Math.sin(radLatitudeThisPosition)*Math.sin(radLatitudeOtherPosition) + 
				Math.cos(radLatitudeThisPosition)*Math.cos(radLatitudeOtherPosition) *
				Math.cos(radLongitudeOtherPosition-radLongitudeThisPosition))*getRadius();

		//postconditions
		assertClassInvariants();
		assert (distance <= Math.PI *this.getRadius() && distance >= 0);

		return distance;
	}

	/**
	 * Method that checks if two coordinate refer to the same place. If the distance between places is
	 * less than 0.5km, it returns true
	 * 
	 * @methodtype comparison
	 */

	public boolean isEqual (SphericCoordinate coordinate) {

		//preconditions
		assertClassInvariants();
		coordinate.assertClassInvariants();
		assertHasSameRadius(coordinate);
		
		double delta = 0.1;

		if (this.getDistance(coordinate) <= delta) {
			return true;
		}
		return false;		
	}

	/**
	 * @methodtype comparison
	 */

	public boolean isEqual (Coordinate coordinate) {

		//precondition
		assert (coordinate != null);

		return this.getSphericCoordinate().isEqual(coordinate.getSphericCoordinate());
	}
	
	/**
	 * @methodtype helper
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits((double) Math.round(this.getLatitude()*1000)/1000);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits((double) Math.round(this.getLongitude()*1000)/1000);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits((double) Math.round(this.getRadius()*1000)/1000);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/**
	 * @methodtype comparison
	 */
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		
		AbstractCoordinate other = (AbstractCoordinate) obj;
		double delta = 0.1;
	
		if (Math.abs(this.getLatitude())-Math.abs(other.getLatitude()) > delta)
			return false;
		if (Math.abs(this.getLongitude())-Math.abs(other.getLongitude()) > delta)
			return false;
		if (Math.abs(this.getRadius())-Math.abs(other.getRadius()) > delta)
			return false;
		return true;
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

	/**
	 * @methodtype assertion
	 */

	protected void assertClassInvariants() {
		assert (this != null);
		assert (getRadius() >= 0);
		assert (getLatitude() <= 90 && getLatitude() > -90);
		assert (getLongitude() <= 180 && getLongitude() > -180);
	}
}
