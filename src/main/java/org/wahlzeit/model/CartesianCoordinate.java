package org.wahlzeit.model;

public class CartesianCoordinate implements Coordinate{
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
	
	
	/**
	 * @methodtype get
	 * @methodproperty composed
	 */
	
	public double getDistance (Coordinate coordinate) {
		//TO-DO
		if(coordinate instanceof CartesianCoordinate) {
			return doGetDistance((CartesianCoordinate) coordinate);
		}
			return doGetDistance((SphericCoordinate) coordinate);
	}
	
	/**
	 * 
	 * @methodtyper get
	 * @methodproperty primitive
	 */
	public double doGetDistance (CartesianCoordinate cCoordinate) {
		return convertCartesianToSpheric(this).doGetDistance(convertCartesianToSpheric(cCoordinate));
	}
	
	/**
	 * 
	 * @methodtyper get
	 * @methodproperty primitive
	 */
	
	public double doGetDistance (SphericCoordinate sCoordinate) {
		return sCoordinate.doGetDistance(convertCartesianToSpheric(this));
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
	 * 
	 * @param coordinate
	 * @return
	 */
	
	public static SphericCoordinate convertCartesianToSpheric (CartesianCoordinate coordinate) {
		double latitude = Math.atan2(coordinate.z, Math.sqrt(Math.pow(coordinate.getX(), 2) + 
															 Math.pow(coordinate.getY(), 2)))
															 * 180 / Math.PI;
		
		double longitude = Math.atan2(coordinate.getY(), coordinate.getX()) * 180 / Math.PI;
		
		double radius = Math.sqrt(Math.pow(coordinate.getX(), 2) + 
								  Math.pow(coordinate.getY(), 2) + 
								  Math.pow(coordinate.getZ(),2));
		
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


