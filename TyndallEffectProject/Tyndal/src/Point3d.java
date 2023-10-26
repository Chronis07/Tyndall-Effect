
/**
 * This class decribes the properties and methods of the 3D Point object
 *
 * @author      Atharva Sawe
 * 
 */
public class Point3d {

	private double x;		// X coordinate
	private double y;		// Y coordinate
	private double z;		// Z coordinate
	
	public Point3d(){}
	
	//--------------------------------------------------------------------------------------------
	
	/** 
	 * Set functions to edit the properties of the Point3d object
	 *
	 */
	
	// SET functions
		
	public void setX(double tx) {x = tx;}
	
	public void setY(double ty) {y = ty;}
	
	public void setZ(double tz) {z = tz;}
	
	//--------------------------------------------------------------------------
	
	/** 
	 * GET functions to access the properties of the Point3d object
	 *
	 */
	
	// GET functions
	
	public double getX() {return x;}
	
	public double getY() {return y;}
	
	public double getZ() {return z;}
	
	//--------------------------------------------------------------------------
	
	/** 
	 * Constructor function to generate a Point3d object
	 * 
	 */
	
	public Point3d(double tempx, double tempy, double tempz) {
		x = tempx;
		y = tempy;
		z = tempz;
	}
	
	//--------------------------------------------------------------------------
}
