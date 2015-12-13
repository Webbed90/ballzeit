package org.wahlzeit.model;

public class CartesianCoordinate extends AbstractCoordinate {
	private final double X;
	private final double Y;
	private final double Z;
	
	
	public CartesianCoordinate (double x, double y, double z) {
		this.X = x;
		this.Y = y;
		this.Z = z;
	}
	
	/**
	 * @methodtype get
	 */
	
	public double getX() {
		assertClassInvariants();
		return X;
	}

	/**
	 * @methodtype set
	 */
	
	public CartesianCoordinate setX(double x) {
		assert (!Double.isNaN(x));
		return new CartesianCoordinate(x, this.Y, this.Z);
	}

	/**
	 * @methodtype get
	 */
	
	public double getY() {
		assertClassInvariants();
		return Y;
	}

	/**
	 * @methodtype set
	 */
	
	public CartesianCoordinate setY(double y) {
		assert (!Double.isNaN(y));
		return new CartesianCoordinate(this.X, y, this.Z);

	}

	/**
	 * @methodtype get
	 */
	
	double getZ() {
		assertClassInvariants();
		return Z;
	}

	/**
	 * @methodtype set
	 */
	
	public CartesianCoordinate setZ(double z) {
		assert (!Double.isNaN(z));
		return new CartesianCoordinate(this.X,this.Y,z);
	}
	
	/**
	 * @methodtype conversion
	 */
	
	@Override
	public SphericCoordinate getSphericCoordinate() {
		assertClassInvariants();
		return this.convertCartesianToSpheric();
	}

	/**
	 * @methodtype Conversion
	 * @param coordinate
	 * @return
	 */
	
	private SphericCoordinate convertCartesianToSpheric () {
		
		//preconditions
		assertClassInvariants();
		
		double latitude = Math.atan2(getZ(), Math.sqrt(Math.pow(getX(), 2) + 
													   Math.pow(getY(), 2)))
													   * 180 / Math.PI;
		double longitude = Math.atan2(getY(), getX()) * 180 / Math.PI;
		double radius = Math.sqrt(Math.pow(getX(), 2) + 
								  Math.pow(getY(), 2) + 
								  Math.pow(getZ(), 2));
		
		SphericCoordinate convertedCoordinate = new SphericCoordinate(latitude, longitude, radius);
		
		//postconditions
		convertedCoordinate.assertClassInvariants();
		
		return 	convertedCoordinate;
		
	}


	@Override
	protected void assertClassInvariants() {
		assert (this != null);
		assert(!Double.isNaN(this.X));
		assert(!Double.isNaN(this.Y));
		assert(!Double.isNaN(this.Z));
		assert (getRadius() >= 0);
		assert (getLatitude() <= 90 && getLatitude() > -90);
		assert (getLongitude() <= 180 && getLongitude() > -180);
	}
	
	
}


