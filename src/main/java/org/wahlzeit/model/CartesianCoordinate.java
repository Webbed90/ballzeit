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
		assertClassInvariants();
		return x;
	}

	/**
	 * @methodtype set
	 */
	
	void setX(double x) {
		assert (!Double.isNaN(x));
		this.x = x;
		assertClassInvariants();
	}

	/**
	 * @methodtype get
	 */
	
	public double getY() {
		assertClassInvariants();
		return y;
	}

	/**
	 * @methodtype set
	 */
	
	public void setY(double y) {
		assert (!Double.isNaN(y));
		this.y = y;
		assertClassInvariants();

	}

	/**
	 * @methodtype get
	 */
	
	double getZ() {
		assertClassInvariants();
		return z;
	}

	/**
	 * @methodtype set
	 */
	
	public void setZ(double z) {
		assert (!Double.isNaN(z));
		this.z = z;
		assertClassInvariants();
	}
	
	public SphericCoordinate getCoordinate() {
		assertClassInvariants();
		return this.convertCartesianToSpheric();
	}
	
	public double getLatitude() {
		assertClassInvariants();
		return this.convertCartesianToSpheric().getLatitude();
	}
	
	public double getLongitude() {
		assertClassInvariants();
		return this.convertCartesianToSpheric().getLongitude();
	}
	
	public double getRadius() {
		assertClassInvariants();
		return this.convertCartesianToSpheric().getRadius();
	}
	

	
	/**
	 * @methodtype Conversion
	 * @param coordinate
	 * @return
	 */
	
	public SphericCoordinate convertCartesianToSpheric () {
				
		assertClassInvariants();
		
		
		double latitude = Math.atan2(getZ(), Math.sqrt(Math.pow(getX(), 2) + 
													   Math.pow(getY(), 2)))
													   * 180 / Math.PI;
		
		double longitude = Math.atan2(getY(), getX()) * 180 / Math.PI;
		
		double radius = Math.sqrt(Math.pow(getX(), 2) + 
								  Math.pow(getY(), 2) + 
								  Math.pow(getZ(), 2));
		
		//postconditions
		assert (latitude <= 90 && latitude > -90);
		assert (longitude <= 180 && longitude > 180);
		
		assertClassInvariants();
		
		return new SphericCoordinate(latitude, longitude, radius);
	}


	
	protected void assertClassInvariants() {
		assert (this != null);
		assert(!Double.isNaN(this.x));
		assert(!Double.isNaN(this.y));
		assert(!Double.isNaN(this.z));
	}
	
	
}


