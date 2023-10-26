
/**
 * This class decribes the properties and methods of the 3D Triangle object
 *
 * @author      Atharva Sawe
 * 
 */
public class Triangle extends Objects {
	
	private Point3d v1;			// vertex 1
	private Point3d v2;			// vertex 2
	private Point3d v3;			// vertex 3
	
	private Vector3d normal;	// Normal to triangular plane
	private Vector3d u;			// edge vector v2-v1
	private Vector3d v;			// edge vector v3-v1
	
	private Vector3d uPerp;		// vector perpendicular to u
	private Vector3d vPerp;		// vector perpendicular to v
	
	
	private double dOrig;		// Distance from Origin
	private double disU;		// Dot of vPerp and u
	private double disV;		// Dot of uPerp and v
	
	public Triangle(){}
	
	
	//--------------------------------------------------------------------------------------------
	
	/** 
	 * Get functions to access the properties of the Triangle object
	 *
	 */
	
	// GET functions
	
	public Point3d getPoint1() {return v1;}
	public Point3d getPoint2() {return v2;}
	public Point3d getPoint3() {return v3;}
	
	public Vector3d getNormal() {return normal;}
		
	public Vector3d getUPerp() {return uPerp;}
	public Vector3d getVPerp() {return vPerp;}
		
	public double getDOrig() {return dOrig;}
	public double getDisU() {return disU;}
	public double getDisV() {return disV;}
	
	//--------------------------------------------------------------------------------------------
	
	/** 
	 * Constructor function to generate a Triangle object
	 * 
	 * @param	V1		Vertex 1 of triangle
	 * @param	V2		Vertex 2 of triangle
	 * @param	V3		Vertex 3 of triangle
	 * @param	diff	diffuse color for triangle
	 * @param	spe		specular color for triangle
	 * @param	phong	Phong constant for triangle
	 * 
	 */
	
	// making Triangle constructor function
	public Triangle(Point3d V1, Point3d V2, Point3d V3, Color dif, Color spe, double phong) {
		
		v1 = V1;
		v2 = V2;
		v3 = V3;
		
		diffuse = dif;
		specular = spe;
		Phong = phong;
		
		u = Math3d.SubPoints(v2, v1);
		v = Math3d.SubPoints(v3, v1);
		
		normal = Math3d.CrossVecs(u, v).VecNormalize();
		uPerp = Math3d.CrossVecs(normal, u);
		vPerp = Math3d.CrossVecs(normal, v);
		
		Vector3d tempv = Math3d.SubPoints(v1, new Point3d(0,0,0));
		dOrig = -(Math3d.DotVecs(normal, tempv));
		
		disU = Math3d.DotVecs(vPerp, u);
		disV = Math3d.DotVecs(uPerp, v);
		
	}
	
	/** 
	 * Function to calculate a normal for the triangle
	 * 
	 * @return		Normal vector for the Triangle object
	 * 
	 */
	
	
	public Vector3d normal(Point3d p, boolean inside) {
		return normal;
	}
	
	//--------------------------------------------------------------------------------------------
	
	
	
	
}
