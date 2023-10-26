
/**
 * This class decribes the properties and methods of the 3D Ray object
 *
 * @author      Atharva Sawe
 * 
 */
public class Ray3d {
	
	private Point3d origin;				// Origin point of ray
	private Vector3d direction;			// Direction of ray
	private Point3d intPoint;			// Intersection point of ray with an object
	
	private Objects obj = null;			// The object that the ray intersected with
	
	private int step = 1;				// int to keep track of depth of recursion
	
	private boolean inside = false;		// bool to keep track of whether ray is inside or outside object
	
	public Ray3d(){}
	
	//--------------------------------------------------------------------------------------------
	
	/** 
	 * Set functions to edit the properties of the Ray3d object
	 *
	 */
	
	// SET functions
	
	public void setIntPoint(Point3d p) {intPoint = p;}
	
	public void setSceneObject(Objects OBJ) {obj = OBJ;}

	public void setStep(int s) {step = s;}
	
	public void setInside(boolean b) {inside = b;}
		
	//--------------------------------------------------------------------------------------------
	
	/** 
	 * Get functions to access the properties of the Ray3d object
	 *
	 */
		
	// GET functions
	
	public Point3d getOrigin() {return origin;}
	
	public Vector3d getDir() {return direction;}
	
	public Point3d getIntPoint() {return intPoint;}
	
	public Objects getSceneObject() {return obj;}
	
	public int getStep() {return step;}
	
	public boolean getInside() {return inside;}
	
	//--------------------------------------------------------------------------------------------
	
	/** 
	 * Constructor function to generate a Ray3d object
	 * 
	 * @param	org		Origin point of ray
	 * @param	dir		Direction vector of ray
	 * 
	 */
	
	
	public Ray3d(Point3d org, Vector3d dir){
		origin = org;
		direction = dir;
	}
	
	//--------------------------------------------------------------------------------------------
	
	/** 
	 * function to calculate intersection point of Ray3d object
	 * 
	 * @param	dist	distance from ray origin to intersection point
	 * 
	 * @return			intersection point
	 */
	
	
	public Point3d calIntPoint(double dist) {
		double px = origin.getX() + dist * direction.getX();
		double py = origin.getY() + dist * direction.getY();
		double pz = origin.getZ() + dist * direction.getZ();
		return new Point3d(px, py, pz);
	}
	
	//--------------------------------------------------------------------------------------------
	
	/** 
	 * function to calculate and set intersection point of Ray3d object
	 * 
	 * @param	dist	distance from ray origin to intersection point
	 * 
	 */
	
	
	public void calSetIntPoint(double dist) {
		double px = origin.getX() + dist * direction.getX();
		double py = origin.getY() + dist * direction.getY();
		double pz = origin.getZ() + dist * direction.getZ();
		intPoint = new Point3d(px, py, pz);
	}
	
	//--------------------------------------------------------------------------------------------
	
	
}
