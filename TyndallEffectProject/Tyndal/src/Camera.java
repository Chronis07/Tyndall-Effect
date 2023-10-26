/**
 * This class decribes the properties and methods of the camera object
 *
 * @author      Atharva Sawe
 * 
 */


public class Camera {
	
	private Vector3d LookAt;		// Vector pointing to the LookAt direction
	private Vector3d Up;			// Vector pointing to the upward direction
	
	private int rows;				// No. of rows
	private int cols;				// No. of columns
	
	private Point3d viewRefPoint;	// View Reference Point
	private Point3d projRefPoint;	// Projection reference Point
	
	private double verAngle;		// Vertical Angle
	private double horAngle;		// Horizontal Angle
	
	public Camera(){}
	
	
	//--------------------------------------------------------------------------------------------
	
	/** 
	 * Get functions to access the properties of the Camera object
	 *
	 */
	
	// GET functions
	
	public Vector3d getVPN() {return LookAt;}
	public Vector3d getVUP() {return Up;}
	
	public int getNumRows() {return rows;}
	public int getNumCols() {return cols;}
	
	public Point3d getVRP() {return viewRefPoint;}
	public Point3d getPRP() {return projRefPoint;}
	
	public double getVerAngle() {return verAngle;}
	public double getHorAngle() {return horAngle;}
	
	//--------------------------------------------------------------------------------------------
	
	/** 
	 * Constructor function to generate a Camera object
	 * 
	 * @param	LA		LookAt vector of camera
	 * @param	UP		Up vector of camera
	 * @param	r		Number of Rows
	 * @param	c		Number of Columns
	 * @param	VRP		View Reference point of camera
	 * @param	VA		Vertical Angle of camera
	 * @param	HA		Horizontal Angle of camera
	 * 
	 */
	
	// making Camera constructor function
	public Camera(Vector3d LA, Vector3d UP, int r, int c, Point3d VRP, double VA, double HA) {
		LookAt = LA;
		Up = UP;
		rows = r;
		cols = c;
		viewRefPoint = VRP;
		verAngle = VA;
		horAngle = HA;
		
		LA.VecNormalize();
		double px = VRP.getX() - LA.getX();
		double py = VRP.getY() - LA.getY();
		double pz = VRP.getZ() - LA.getZ();
		projRefPoint = new Point3d(px, py, pz);
	}
	
	//--------------------------------------------------------------------------------------------
	
	
	

}
