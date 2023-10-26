
/**
 * This class decribes the properties and methods of a 3D vector object
 *
 * @author      Atharva Sawe
 * 
 */

public class Vector3d {

	private double x;		// X coordinate
	private double y;		// Y coordinate
	private double z;		// Z coordinate
	
	public Vector3d(){}
	
	//--------------------------------------------------------------------------
	
	/** 
	 * GET functions to access the properties of the Vector3d object
	 *
	 */
	
	// GET functions
	
	public double getX() {return x;}
	
	public double getY() {return y;}
	
	public double getZ() {return z;}
	
	//--------------------------------------------------------------------------
	
	/** 
	 * Constructor function to generate a Vector3d object
	 * 
	 */
	
	// making a 3D vector constructor
	public Vector3d(double tempx, double tempy, double tempz) {
		x = tempx;
		y = tempy;
		z = tempz;
	}
	
	//--------------------------------------------------------------------------
	
	/** 
	 * Function to Calculate magnitude of the Vector3d object
	 * 
	 * @return		Magnitude value
	 * 
	 */
	
	// calculating magnitude of the vector
	public double VecMag() {
		double sqx = Math.pow(x,2.0);
		double sqy = Math.pow(y,2.0);
		double sqz = Math.pow(z,2.0);
		return Math.sqrt(sqx + sqy + sqz);
	}
	
	//--------------------------------------------------------------------------
	
	/** 
	 * Function to normalize the Vector3d object
	 * 
	 * @return		new normalized Vector3d object
	 * 
	 */
	
	// returning a normalized version of this vector
	public Vector3d VecNormalize() {
		double sqx = Math.pow(x,2.0);
		double sqy = Math.pow(y,2.0);
		double sqz = Math.pow(z,2.0);
		double mag = Math.sqrt(sqx + sqy + sqz);
		return new Vector3d(x/mag, y/mag, z/mag);
	}
	
	//--------------------------------------------------------------------------
	
	/** 
	 * Function to check if Vector3d object is a unit vector
	 * 
	 * @return		True/False boolean
	 * 
	 */
	
	
	public boolean isUnit() {
		double sqx = Math.pow(x,2.0);
		double sqy = Math.pow(y,2.0);
		double sqz = Math.pow(z,2.0);
		double mag = Math.sqrt(sqx + sqy + sqz);
		
		if((mag < (1.0 + Engine.Tolerance)) && (mag > (1.0 - Engine.Tolerance))) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//--------------------------------------------------------------------------
	
	
	
	
	
}
