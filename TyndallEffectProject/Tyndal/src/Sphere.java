
/**
 * This class decribes the properties and methods of the Sphere object
 *
 * @author      Atharva Sawe
 * 
 */
public class Sphere extends Objects {
	
	private Point3d center;		// Center of sphere
	private double radius;		// Radius of Sphere
	
	public Sphere(){}
	
	//--------------------------------------------------------------------------------------------
	
	/** 
	 * Get functions to access the properties of the Sphere object
	 *
	 */
	
	// GET functions
	
	public Point3d getCenter() {return center;}
	
	public double getRadius() {return radius;}
	
	//--------------------------------------------------------------------------------------------
	
	/** 
	 * Constructor function to generate a Sphere object
	 * 
	 * @param	cen		Center point of sphere
	 * @param	rad		Radius of sphere
	 * @param	diff	diffuse color for sphere
	 * @param	spe		specular color for sphere
	 * @param	phong	Phong constant for sphere
	 * 
	 */
	
	
	public Sphere(Point3d cen, double rad, Color dif, Color spe, double phong) {
		center = cen;
		radius = rad;
		
		diffuse = dif;
		specular = spe;
		Phong = phong;
	}
	
	
	//--------------------------------------------------------------------------------------------
	
	/** 
	 * Function to calculate a normal for the sphere
	 * 
	 * @param	p			Point on sphere
	 * @param	isInside	boolean to see in point is to be inside or outside the sphere
	 * 
	 * @return				Normal vector for the sphere object
	 * 
	 */
	
	
	public Vector3d normal(Point3d p, boolean isInside) {
		double px;
		double py;
		double pz;
		
		if(isInside) {
			px = (center.getX() - p.getX()) / radius;
			py = (center.getY() - p.getY()) / radius;
			pz = (center.getZ() - p.getZ()) / radius;
			return new Vector3d(px, py, pz);
		}
		
		else {
			px = (p.getX() - center.getX()) / radius;
			py = (p.getY() - center.getY()) / radius;
			pz = (p.getZ() - center.getZ()) / radius;
			return new Vector3d(px, py, pz);
		}
	}
	
	//--------------------------------------------------------------------------------------------
	
	
	
}
