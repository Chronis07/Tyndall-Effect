import java.util.ArrayList;

/**
 * This class keeps a record of all the objects in the scene along with their properties
 *
 * @author      Atharva Sawe
 * 
 */

public class Scene {
		
	private Camera cam;									// Camera object
	
	private Color bgd = new Color(0.55, 0.75, 1.0);		// Background color of scene
	private Color amb = new Color(0.4, 0.4, 0.4);		// Ambient color of scene
	
	private ArrayList lights = new ArrayList();			// List to store all the lights in the scene
	private ArrayList triangles = new ArrayList();		// List to store all the Triangles in the scene
	private ArrayList spheres = new ArrayList();		// List to store all the Spheres in the scene
	
	public Scene(){}
	
	//-----------------------------------------------------------------------------
	
	/** 
	 * Get functions to access the properties of the Scene object
	 *
	 */
	
	// GET functions
	
	public int getNumOfRows() {return cam.getNumRows();}
	public int getNumOfCols() {return cam.getNumCols();}
	
	public double getHorAngle() {return cam.getHorAngle();}
	public double getVerAngle() {return cam.getVerAngle();}

	public Point3d getPRP() {return cam.getPRP();}
	
	public Vector3d getVPN() {return cam.getVPN();}
	public Vector3d getVUP() {return cam.getVUP();}
	
	public Point3d getVRP() {return cam.getVRP();}
		
	public Color getBgdColor() {return bgd;}
	public Color getAmbColor() {return amb;}
		
	public int getNumOfObjects() {return triangles.size() + spheres.size();}
		
	public ArrayList getSpheres() {return spheres;}
	public ArrayList getTriangles() {return triangles;}
	public ArrayList getLights() {return lights;}
	
	
	//-----------------------------------------------------------------------------
	
	/** 
	 * Set functions to access the properties of the Scene object
	 *
	 */

	// SET functions

	public void setBGD(Color col) {bgd = col;}

	public void setAMB(Color col) {amb = col;}
		
	
	//-----------------------------------------------------------------------------
		
	public void addBGD(Color col) {
		bgd = col;
	}
		
	//-----------------------------------------------------------------------------
		
	public void addAMB(Color col) {
		amb = col;
	}
	
	//-----------------------------------------------------------------------------
	
	/** 
	 * functions add a Camera to the Scene object
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
	
	public void addCam(Vector3d LA, Vector3d UP, int r, int c, Point3d RP, double VA, double HA) {
		cam = new Camera(LA, UP, r, c, RP, VA, HA);
	}
	
	//-----------------------------------------------------------------------------
	
	/** 
	 * functions add a Light to the Scene object
	 * 
	 * @param	c		Color of Light
	 * @param	pos		Position of Light
	 *
	 */
	
	public void addLight(Color col, Point3d pos) {
		Light light = new Light(col, pos);
	    lights.add(light);
	}
	
	//-----------------------------------------------------------------------------
	
	/** 
	 * functions add a Triangle to the Scene object
	 * 
	 * @param	T	Triangle object
	 *
	 */
	
	public void addTriangle(Triangle T) {
		triangles.add(T);
	}
	
	//-----------------------------------------------------------------------------
	
	/** 
	 * functions add a Sphere to the Scene object
	 * 
	 * @param	S	Sphere object
	 *
	 */
	
	public void addSphere(Sphere S) {
		spheres.add(S);
	}
	
	//-----------------------------------------------------------------------------

		
	
}
