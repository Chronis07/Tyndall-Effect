import java.io.IOException;
import java.util.Scanner;

/**
 * This is the main class. It adds all the objects into the scene for the program to render
 *
 * @author      Atharva Sawe
 * 
 */


public class Master {
	
	public static int count = 1;
	
	//-----------------------------------------------------------------------------------
	
	/** 
	 * main function that adds all the objects into the scene to render them
	 * 
	 */
	
	
	public static void main(String[] args) throws IOException{
		
		Scene scene = new Scene();
		
		Color col = new Color(0.8, 0.8, 0.8);
		Point3d pos = new Point3d(-95.0, 130.0, 150.0);
		
		Light light = new Light(col, pos);
    	scene.addLight(col, pos);
    	
    	
    	// Adding camera to scene
    	Vector3d CamLA = new Vector3d(0.0, 0.0, 1.0);
    	Vector3d CamUP = new Vector3d(0.0, 1.0, 0.0);
    	int nr = 1000;
    	int nc = 1000;
    	Point3d CamRP = new Point3d(-49.0, -10.0, -20.0);
    	double VA = 85.0;
    	double HA = 85.0;
    	scene.addCam(CamLA, CamUP, nr, nc, CamRP, VA, HA);
    	
    	
    	// Adding floor to scene
    	Point3d P1 = new Point3d(-150.0, -25.0, -20.0);
    	Point3d P2 = new Point3d(-150.0, -25.0, 80.0);
    	Point3d P3 = new Point3d(50.0, -25.0, 80.0);
    	Point3d P4 = new Point3d(50.0, -25.0, -20.0);
    	Color diff_f = new Color(0.54, 0.4, 0.13);
    	//Color diff_f = new Color(0.5, 0.5, 0.0);
    	Color spec_f = new Color(0.2, 0.2, 0.2);
    	Color refl_f = new Color(0.0, 0.0, 0.0);
    	Color refr_f = new Color(0.0, 0.0, 0.0);
    	double phong_f = 256;
    	double eta_f = 1.5;
    	scene = addQuad(P1, P2, P3, P4, scene, diff_f, spec_f, phong_f);
    	
    	
    	// Adding window to scene
    	Color diff_w = new Color(0.41, 0.3, 0.11);
    	//Color diff_w = new Color(0.0, 0.6, 0.6);
    	Color spec_w = new Color(0.2, 0.2, 0.2);
    	Color refl_w = new Color(0.0, 0.0, 0.0);
    	Color refr_w = new Color(0.0, 0.0, 0.0);
    	double phong_w = 256;
    	double eta_w = 1.5;
    	
    	P1 = new Point3d(-150.0, -25.0, 5.0);
    	P2 = new Point3d(-150.0, -2.0, 5.0);
    	P3 = new Point3d(50.0, -2.0, 80.0);
    	P4 = new Point3d(50.0, -25.0, 80.0);
    	scene = addQuad(P1, P2, P3, P4, scene, diff_w, spec_w, phong_w);
    	P1 = new Point3d(-150.0, -2.0, 5.0);
    	P2 = new Point3d(-150.0, 20.0, 5.0);
    	P3 = new Point3d(-56.0, 20.0, 40.25);
    	P4 = new Point3d(-56.0, -2.0, 40.25);
    	scene = addQuad(P1, P2, P3, P4, scene, diff_w, spec_w, phong_w);
    	
    	P1 = new Point3d(-40.0, -2.0, 46.25);
    	P2 = new Point3d(-40.0, 20.0, 46.25);
    	P3 = new Point3d(50.0, 20.0, 80.0);
    	P4 = new Point3d(50.0, -2.0, 80.0);
    	scene = addQuad(P1, P2, P3, P4, scene, diff_w, spec_w, phong_w);
    	
    	P1 = new Point3d(-150.0, 20.0, 5.0);
    	P2 = new Point3d(-150.0, 120.0, 5.0);
    	P3 = new Point3d(50.0, 120.0, 80.0);
    	P4 = new Point3d(50.0, 20.0, 80.0);
    	scene = addQuad(P1, P2, P3, P4, scene, diff_w, spec_w, phong_w);
    	
    	P1 = new Point3d(-50.0, -2.0, 42.5);
    	P2 = new Point3d(-50.0, 20.0, 42.5);
    	P3 = new Point3d(-46.0, 20.0, 44.0);
    	P4 = new Point3d(-46.0, -2.0, 44.0);
    	scene = addQuad(P1, P2, P3, P4, scene, diff_w, spec_w, phong_w);
    	
    	P1 = new Point3d(-56.0, 7.0, 40.25);
    	P2 = new Point3d(-56.0, 10.0, 40.25);
    	P3 = new Point3d(-40.0, 10.0, 46.25);
    	P4 = new Point3d(-40.0, 7.0, 46.25);
    	scene = addQuad(P1, P2, P3, P4, scene, diff_w, spec_w, phong_w);
    	
    	
    	// Running the Tracer
    	Tracer2 trace = new Tracer2();
    	trace.RenderScene(scene, "IMAGE");
		
		
	}
	
	
	//---------------------------------------------------------------------------------
	
	/** 
	 * function to render a Quadrilateral
	 * 
	 * @param	p1		Point 1 of quad
	 * @param	p2		Point 2 of quad
	 * @param	p3		Point 3 of quad
	 * @param	p4		Point 4 of quad
	 * @param	s		Scene object
	 * @param	diff	diffuse color
	 * @param	Spec	specular color
	 * @param	ph		phong factor
	 * 
	 */
	
	
	public static Scene addQuad(Point3d p1, Point3d p2, Point3d p3, Point3d p4, Scene s, Color diff, Color spec, double ph) {
		
		Color DIFF = diff;
		Color SPEC = spec;
		double Phong = ph;
		
		Triangle T1 = new Triangle(p1, p2, p3, DIFF, SPEC, Phong);
		Triangle T2 = new Triangle(p1, p3, p4, DIFF, SPEC, Phong);
		
		T1.setId(count);
		T2.setId(count+1);
		T1.setType(1);
		T2.setType(1);
		
		s.addTriangle(T1);
		s.addTriangle(T2);
		
		count = count + 2;
		
		return s;
	}
	
	
	
	
	
	
	
	
	
}
