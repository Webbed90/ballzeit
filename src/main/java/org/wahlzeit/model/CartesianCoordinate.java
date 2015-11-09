package org.wahlzeit.model;

public class CartesianCoordinate extends AbstractCoordinate {
	private double x;
	private double y;
	private double z;
	
	
	public CartesianCoordinate (double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	


	/**
	 * @methodtype get
	 */
	
	public double getX() {
		return x;
	}

	/**
	 * @methodtype set
	 */
	
	void setX(double x) {
		this.x = x;
	}

	/**
	 * @methodtype get
	 */
	
	public double getY() {
		return y;
	}

	/**
	 * @methodtype set
	 */
	
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * @methodtype get
	 */
	
	double getZ() {
		return z;
	}

	/**
	 * @methodtype set
	 */
	
	public void setZ(double z) {
		this.z = z;
	}
	
	public SphericCoordinate getCoordinate() {
		return this.convertCartesianToSpheric();
	}
	
	public double getLatitude() {
		return this.convertCartesianToSpheric().getLatitude();
	}
	
	public double getLongitude() {
		return this.convertCartesianToSpheric().getLongitude();
	}
	
	public double getRadius() {
		return this.convertCartesianToSpheric().getRadius();
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
	
	/**
	 * @methodtype Conversion
	 * @param coordinate
	 * @return
	 */
	
	public SphericCoordinate convertCartesianToSpheric () {
		double latitude = Math.atan2(getZ(), Math.sqrt(Math.pow(getX(), 2) + 
													   Math.pow(getY(), 2)))
													   * 180 / Math.PI;
		
		double longitude = Math.atan2(getY(), getX()) * 180 / Math.PI;
		
		double radius = Math.sqrt(Math.pow(getX(), 2) + 
								  Math.pow(getY(), 2) + 
								  Math.pow(getZ(), 2));
		
		return new SphericCoordinate(latitude, longitude, radius);
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(z);
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
		CartesianCoordinate other = (CartesianCoordinate) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z))
			return false;
		return true;
	}
	
	
}


