
/**
 * This class defines all the common properties for objects in the scene
 *
 * @author      Atharva Sawe
 * 
 */

public class Objects {
	
	protected Color diffuse;				// kd
	protected Color specular;				// ks
	protected double Phong;					// Phong factor
	
	protected int Type;						// 0 if Sphere and 1 if Triangle
	protected int ID;						// ID of object
	
	
	public Vector3d normal(Point3d p, boolean inside) {
		return null;
	}
	
	
	//--------------------------------------------------------------------------------------------
	
	/** 
	 * Set functions to edit the properties of the Objects object
	 *
	 */
	
	// SET functions
	
	public void setId(int id) {ID = id;}
		
	public void setType(int t) {Type = t;}
	
	//--------------------------------------------------------------------------------------------
	
	/** 
	 * Get functions to access the properties of the Objects object
	 *
	 */
	
	// GET functions
	public Color getKd() {return diffuse;}
	
	public Color getKs() {return specular;}
		
	public double getPhong() {return Phong;}
	
	public int getID() {return ID;}
	
	public int getType() {return Type;}
	
	//--------------------------------------------------------------------------------------------
	

}
