import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class emits the rays from every pixel and generates the PNG image
 *
 * @author      Atharva Sawe
 * 
 */

public class Tracer2 {
	
	public static Color[][] Pixels;			// 2D array of pixels
	public static int depth = 4;			// depth Limit
	public static int nRows;				// number of rows
	public static int nCols;				// number of columns
	
	// Limits of ViewFrame
	public static Point3d VF_TL = null;		// Top left point of View frame
	public static Point3d VF_TR = null;		// Top right point of View frame
	public static Point3d VF_BL = null;		// Bottom left point of View frame
	public static Point3d VF_BR = null;		// Bottom right point of View frame
	
	//Variables for ray marching
	static int Steps = 120;					// Number of particles to check on a ray
	static double delta = 0.002;			// Distance between particles in a ray
	static double enhance = 2.0;			// Factor to scale the pixel color by
	
	static int Method = 1;					// 0 for equidistant Ray Marching and 1 for Random Ray Marching
	
	//-------------------------------------------------------------------------------------------------
	
	/** 
	 * function to render the scene to a PNG and by generating the 2D pixel array and casting 
	 * a ray from every pixel 
	 * 
	 * @param	scene	Scene object
	 * @param	fname	name of PNG file
	 * 
	 */
	
	public static void RenderScene(Scene scene, String fname) throws IOException {
		
		// Generating 2D array of pixels
		nRows = scene.getNumOfRows();
		nCols = scene.getNumOfCols();
		Pixels = new Color[nRows][nCols];
		
		
		// Compute the FOV Height
		double V_angle = scene.getVerAngle()/2.0;
		double Vi = Math.tan(Math.toRadians(V_angle));
		double Vf = -Vi;
		double V_Reso = (Vi - Vf) / ((double)nRows);	// Vertical resolution
		
		
		// Compute the FOV Width
		double H_angle = scene.getHorAngle()/2.0;
		double Hi = Math.tan(Math.toRadians(H_angle));
		double Hf = -Hi;
		double H_Reso = (Hi - Hf) / ((double)nCols);	// Horizontal resolution
		
		
		Vector3d vpn = scene.getVPN().VecNormalize();				// LookAt vector (normalized)
		Vector3d vup = scene.getVUP().VecNormalize();				// Up vector (normalized)
		
		Vector3d Haxis = Math3d.CrossVecs(vup, vpn).VecNormalize();
		Vector3d Vaxis = Math3d.CrossVecs(vpn, Haxis).VecNormalize();
		
		
		// looping through every pixel
		for(int i = 0; i < nRows; i++) {
			double VLen = Hi - (double)i * V_Reso;
			
			for(int j = 0; j < nCols; j++) {
				double HLen = Hi - (double)j * H_Reso;
				
				// convert viewplane coords to World coords and emit rays from each pixel
				Point3d pixel = Engine.ViewToWorld(scene.getVRP(), Haxis, Vaxis, HLen, VLen);
				
				// TopLeft
				if((i == 0) && (j == 0)) {
					VF_TL = pixel;
				}
				
				// TopRight
				if((i == 0) && (j == nCols-1)) {
					VF_TR = pixel;
				}
				
				// BottomLeft
				if((i == nRows-1) && (j == 0)) {
					VF_BL = pixel;
				}
				
				// BottomRight
				if((i == nRows-1) && (j == nCols-1)) {
					VF_BR = pixel;
				}
				
				Vector3d direction = Math3d.SubPoints(pixel, scene.getPRP());
				Ray3d ray = new Ray3d(pixel, direction.VecNormalize());
				
				// Trace the emitted ray
				Pixels[i][j] = Engine.traceRay(ray, scene, depth);
				
			}
		}
		
		
		// Window coordinates
		
		Point3d P1 = new Point3d(-56.0, 10.0, 40.25);
		Point3d P2 = new Point3d(-56.0, 20.0, 40.25);
		Point3d P3 = new Point3d(-50.0, 20.0, 42.5);
		Point3d P4 = new Point3d(-50.0, 10.0, 42.5);
		GodPartclesWindow(P1, P2, P3, P4, scene, delta, enhance);
		
		P1 = new Point3d(-46.0, 10.0, 44.0);
		P2 = new Point3d(-46.0, 20.0, 44.0);
		P3 = new Point3d(-40.0, 20.0, 46.25);
		P4 = new Point3d(-40.0, 10.0, 46.25);
		GodPartclesWindow(P1, P2, P3, P4, scene, delta, enhance);
		
		P1 = new Point3d(-56.0, -2.0, 40.25);
		P2 = new Point3d(-56.0, 7.0, 40.25);
		P3 = new Point3d(-50.0, 7.0, 42.5);
		P4 = new Point3d(-50.0, -2.0, 42.5);
		GodPartclesWindow(P1, P2, P3, P4, scene, delta, enhance);
		
		P1 = new Point3d(-46.0, -2.0, 44.0);
		P2 = new Point3d(-46.0, 7.0, 44.0);
		P3 = new Point3d(-40.0, 7.0, 46.25);
		P4 = new Point3d(-40.0, -2.0, 46.25);
		GodPartclesWindow(P1, P2, P3, P4, scene, delta, enhance);
		
		
		System.out.println("IMAGE COMPLETE :) \n");
		
		// generate Image
		GenImage(fname);
		
		
	}
	
	//----------------------------------------------------------------------------------------------------
	
	/** 
	 * Function to render GodRays by Ray marching
	 * 
	 * @param	P			Origin point of ray
	 * @param	s			Scene object
	 * @param	delta		distance between each particle in a ray
	 * @param	enhance		factor that scales the pixel color
	 * 
	 */
	
	public static void DisplayGodRay(Point3d P, Scene s, double delta, double enhance) {
		ArrayList Ls = s.getLights();
		Light light = (Light)Ls.get(0);

		//=================================================================

		// computing Ray
		Vector3d dir = Math3d.SubPoints(P, light.getPos());
		double distance = dir.VecMag();
		dir = dir.VecNormalize();
		Ray3d ray1 = new Ray3d(light.getPos(), dir);

		//=================================================================

		double xdiff = (VF_TL.getX() - VF_TR.getX()) / nRows;
		double ydiff = (VF_TL.getY() - VF_BL.getY()) / nCols;

		//=================================================================

		for(int i = 0; i < Steps; i++) {

			distance = distance + delta;

			P = ray1.calIntPoint(distance);

			if(P.getY() < -25.0) {
				continue;
			}

			Vector3d VFDir = Math3d.SubPoints(P, s.getPRP());
			Ray3d VFRay = new Ray3d(P, VFDir);

			// intersection point with VF
			Point3d VFInts = Engine.Tri_collision(VF_BR, VF_TR, VF_TL, VFRay);
			if(VFInts == null) {
				VFInts = Engine.Tri_collision(VF_BR, VF_TL, VF_BL, VFRay);
			}

			// Mapping Intersection point to Pixel coords
			int j = (int)Math.ceil((VF_TL.getX() - VFInts.getX()) / xdiff);
			int k = (int)Math.ceil((VF_TL.getY() - VFInts.getY()) / ydiff);

			if((k <= nCols) && (j <= nRows) && (k > 0) && (j > 0)) {

				Color C = Pixels[k][j];
				Pixels[k][j] = C.ScaleCol(C, enhance);

			}
			if(enhance >= 1.0) {
				enhance = enhance - 0.005;
			}
			delta = delta + 0.02;
		}

	}


	//---------------------------------------------------------------------------------
	
	/** 
	 * Function to render GodRays by randomized version of Ray marching
	 * 
	 * @param	P		Origin point of ray
	 * @param	s		Scene object
	 * 
	 */

	public static void DisplayGodRayRand(Point3d P, Scene s) {
		ArrayList Ls = s.getLights();
		Light light = (Light)Ls.get(0);

		//=================================================================

		// computing Ray
		Vector3d dir = Math3d.SubPoints(P, light.getPos());
		double distance = dir.VecMag();
		dir = dir.VecNormalize();
		Ray3d ray1 = new Ray3d(light.getPos(), dir);

		// Computing distance of godRays
		double dist2floor = 0.0;

		ArrayList tris = s.getTriangles();

		for(int i=0; i < tris.size(); i++) {
			dist2floor = Engine.Tri_intersect((Triangle)tris.get(i), ray1);
			if(dist2floor != Double.MAX_VALUE) {
				dist2floor = dist2floor - distance;
				break;
			}
		}
		
		//=================================================================

		double xdiff = (VF_TL.getX() - VF_TR.getX()) / nRows;
		double ydiff = (VF_TL.getY() - VF_BL.getY()) / nCols;

		//=================================================================

		for(int i = 0; i < Steps; i++) {


			double d = Math.random();

			if(d == 0.0) {d = 0.0000001;}

			double decayFactor = (dist2floor) / (d);
			if (decayFactor > 1.5) {decayFactor = 1.5;}

			d = (dist2floor * d);

			P = ray1.calIntPoint(distance + d);

			if(P.getY() < -25.0) {
				continue;
			}

			Vector3d VFDir = Math3d.SubPoints(P, s.getPRP());
			Ray3d VFRay = new Ray3d(P, VFDir);

			// intersection point with VF
			Point3d VFInts = Engine.Tri_collision(VF_BR, VF_TR, VF_TL, VFRay);
			if(VFInts == null) {
				VFInts = Engine.Tri_collision(VF_BR, VF_TL, VF_BL, VFRay);
			}

			// Mapping Intersection point to Pixel coords
			int j = (int)Math.ceil((VF_TL.getX() - VFInts.getX()) / xdiff);
			int k = (int)Math.ceil((VF_TL.getY() - VFInts.getY()) / ydiff);

			if((k <= nCols) && (j <= nRows) && (k > 0) && (j > 0)) {

				Color C = Pixels[k][j];
				Pixels[k][j] = C.ScaleCol(C, decayFactor);

			}
		}

	}


	//---------------------------------------------------------------------------------
	
	/** 
	 * Function to generate all the rays emitted from a section of the window
	 * 
	 * @param	BR			Bottom right point of window section
	 * @param	TR			Top right point of window section
	 * @param	TL			Top left point of window section
	 * @param	BL			Bottom left point of window section
	 * @param	s			Scene object
	 * @param	delta		distance between each particle in a ray
	 * @param	enhance		stores the factor that scales the pixel color
	 * @param	fname		name of PNG file
	 * 
	 */

	public static void GodPartclesWindow(Point3d BR, Point3d TR, Point3d TL, Point3d BL, Scene s, double delta, double enhance) {
		
		// Window Boundary
		Vector3d TopDir = Math3d.SubPoints(TL, TR);
		double TopLen = TopDir.VecMag();
		TopDir = TopDir.VecNormalize();
		Ray3d TopRay = new Ray3d(TR, TopDir);

		int Horizontal_Segments = 40;
		double TopDiv = TopLen / Horizontal_Segments;

		int Vertical_Segments = 80;
		double SideDiv = (TR.getY() - BR.getY()) / Vertical_Segments;

		double zDist = 0.0;
		double yDist = 0.0;

		for(int z=0; z < Horizontal_Segments; z++) {
			Point3d P = TopRay.calIntPoint(zDist);

			for(int y=0; y < Vertical_Segments; y++) {
				
				if(Method == 0) {
					DisplayGodRay(P, s, delta, enhance);
				}
				
				else {
					DisplayGodRayRand(P, s);
				}
				
				P.setY(P.getY() - SideDiv);
			}

			zDist = zDist + TopDiv;
		}



	}
		
		
	//-------------------------------------------------------------------------------------------------
	
	/** 
	 * function to generate a PNG image
	 * 
	 * @param	fname	name of PNG file
	 * 
	 */
	
	private static void GenImage(String fname) throws IOException {
		
        BufferedImage BuffIm = new BufferedImage(nCols, nRows, BufferedImage.TYPE_INT_RGB);
		File file = new File(fname);
        File filePNG = new File(file + ".png");
		
		for(int i = 0; i < nRows; i++) {
			for(int j = 0; j < nCols; j++) {
				
				int r = Pixels[i][j].getSR();
				int g = Pixels[i][j].getSG();
				int b = Pixels[i][j].getSB();
				
				java.awt.Color c = new java.awt.Color(r,g,b);
				
				BuffIm.setRGB(j, i, (c.getRGB() & 0x00ffffff));
			}		
		}
		javax.imageio.ImageIO.write(BuffIm, "PNG", filePNG);
	}
	
	
	//-------------------------------------------------------------------------------------------------
	
	
	
	
	
	
	
	
	
}
