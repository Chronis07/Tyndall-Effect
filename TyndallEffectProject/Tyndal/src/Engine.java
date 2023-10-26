import java.util.ArrayList;

/**
 * This class defines the physics of collisions of the ray
 *
 * @author      Atharva Sawe
 * 
 */

public class Engine {
	
	public static final double Tolerance = 10E-14;
	
	
	//---------------------------------------------------------------------------------------------
	
	/** 
	 * function to identify the intersection of a ray with a triangle and calculate the point of intersection
	 * 
	 * @param	tri		Triangle object
	 * @param	ray		Ray Object
	 * 
	 * @return			point of intersection
	 * 
	 */

	public static Point3d Tri_collision(Point3d v1, Point3d v2, Point3d v3, Ray3d ray) {
		
		Vector3d u = Math3d.SubPoints(v2, v1);
		Vector3d v = Math3d.SubPoints(v3, v1);
		
		Vector3d normal = Math3d.CrossVecs(u, v).VecNormalize();
		Vector3d uPerp = Math3d.CrossVecs(normal, u);
		Vector3d vPerp = Math3d.CrossVecs(normal, v);
		
		Vector3d tempv = Math3d.SubPoints(v1, new Point3d(0,0,0));
		double dOrig = -(Math3d.DotVecs(normal, tempv));

		double den = Math3d.DotVecs(normal, ray.getDir());				// denominator

		if(den == 0) {return null;}

		Vector3d v0 = Math3d.SubPoints(ray.getOrigin(), new Point3d(0,0,0));	// ray origin Vector
		double num = -(Math3d.DotVecs(normal, v0) + dOrig);						// numerator
		double dis = num/den;													// distance
		Point3d ip = ray.calIntPoint(dis);										// intersection point
		
		return ip;
		
	}

	
	//---------------------------------------------------------------------------------------------
	
	/** 
	 * function to identify the intersection of a ray with a triangle and calculate the distance
	 * the ray travelled
	 * 
	 * @param	tri		Triangle object
	 * @param	ray		Ray Object
	 * 
	 * @return			distance if intersection was successful OR MAX_VALUE of double if no 
	 * 					inetrsection
	 * 
	 */
	
	public static double Tri_intersect(Triangle tri, Ray3d ray) {
		
		double den = Math3d.DotVecs(tri.getNormal(), ray.getDir());				// denominator
		
		if(den == 0) {return Double.MAX_VALUE;}
		
		Vector3d v0 = Math3d.SubPoints(ray.getOrigin(), new Point3d(0,0,0));	// ray origin Vector
		double num = -(Math3d.DotVecs(tri.getNormal(), v0) + tri.getDOrig());	// numerator
		double dis = num/den;													// distance
		Point3d ip = ray.calIntPoint(dis);										// intersection point
		
		// Checking if it is inside the triangle
		Vector3d tempv = Math3d.SubPoints(ip, tri.getPoint1());
		double sParam = Math3d.DotVecs(tri.getVPerp(), tempv) / tri.getDisU();
		double tParam = Math3d.DotVecs(tri.getUPerp(), tempv) / tri.getDisV();
		
		if((sParam >= Tolerance) && (tParam >= Tolerance) && ((sParam  + tParam) <= 1)) {
			return dis;
		}
		else {
		    return Double.MAX_VALUE;
		}
	}
	
	//----------------------------------------------------------------------------------------------
	
	/** 
	 * function to identify the intersection of a ray with a sphere and calculate the distance
	 * the ray travelled
	 * 
	 * @param	sph		Sphere object
	 * @param	ray		Ray object
	 * 
	 * @return			distance if intersection was successful OR MAX_VALUE of double if no 
	 * 					inetrsection
	 * 
	 */
	
	public static double Sph_intersect(Sphere sph, Ray3d ray) {
		
		Vector3d COrg = Math3d.SubPoints(sph.getCenter(), ray.getOrigin());			// Center Origin
		
		double v = Math3d.DotVecs(ray.getDir(), COrg);
		
		double vsq = Math.pow(v,2.0);												// v squared
		double rsq = Math.pow(sph.getRadius(), 2.0);								// r squared
		
		double disc = rsq - (Math3d.DotVecs(COrg, COrg) - vsq);
		
		// Checking for intersection
		if (disc < 0) {
			return Double.MAX_VALUE;
		}
		else{
				
			if(!ray.getInside()) {
				return (v - Math.sqrt(disc));
			}
			else {
				return (v + Math.sqrt(disc));
			}
		}
				
	}
	
	
	//----------------------------------------------------------------------------------------------
	
	/** 
	 * function to identify the intersection of a ray with the nearest object and calculate 
	 * the distance the ray travelled. It also updates the ray with the object it intersected
	 * 
	 * @param	ray		Ray object
	 * @param	scene	Scene Object
	 * @param	ID		ID of object
	 * 
	 * 
	 * @return			distance if intersection was successful OR MAX_VALUE of double if no 
	 * 					inetrsection
	 * 
	 */
	private static double Obj_intersect(Ray3d ray, Scene scene, int ID) {
		
		double d = Double.MAX_VALUE;
		
		
		// Triangle Check =================================================================
		ArrayList triangles = scene.getTriangles();
				
		// Browse through all the triangles
		for(int i = 0; i < triangles.size(); i++) {	
			Triangle triangle = (Triangle)triangles.get(i);
			double dist = Engine.Tri_intersect(triangle, ray);
			
			if(dist < d && dist > Tolerance && triangle.getID() != ID) {
				d = dist;
				ray.setSceneObject(triangle);
			}
		}
		
		
		// Sphere Check  ==================================================================
		ArrayList spheres = scene.getSpheres();
		
		// Browse through all the spheres
		for(int i = 0; i < spheres.size(); i++) {
			Sphere sphere = (Sphere)spheres.get(i);
			double dist = Engine.Sph_intersect(sphere, ray);
			
			if(dist < d && dist > Tolerance && sphere.getID() != ID) {
				d = dist;
				ray.setSceneObject(sphere);
			}
		}
				
		return d;
	}
	
	
	//----------------------------------------------------------------------------------------------
	
	/** 
	 * function to convert ViewPlane coordinates to World coordinates
	 * 
	 * @param	P		ViewPlane coordinates
	 * @param	Haxis	Horizontal vector with mag = 1 
	 * @param	Vaxis	Vertical vector with mag = 1
	 * @param	Hlen	Horizontal length travelled by point
	 * @param	Vlen	Vertical length travelled by point
	 * 
	 * @return			World Coordinates
	 * 
	 */
	
	public static Point3d ViewToWorld(Point3d P, Vector3d Haxis, Vector3d Vaxis, double Hlen, double Vlen) {
		double x = P.getX() + Hlen * Haxis.getX() + Vlen * Vaxis.getX();
		double y = P.getY() + Hlen * Haxis.getY() + Vlen * Vaxis.getY();
		double z = P.getZ() + Hlen * Haxis.getZ() + Vlen * Vaxis.getZ();
		return new Point3d(x,y,z);
	}
	
	//----------------------------------------------------------------------------------------------
	
	/** 
	 * function to trace a ray
	 * 
	 * @param	ray		Ray object
	 * @param	scene	Scene object 
	 * @param	depth	recursion counter
	 * 
	 * @return			Color of the pixel where the ray originated
	 * 
	 */
	
	
	public static Color traceRay(Ray3d ray, Scene scene, int depth) {
		
		if(ray.getStep() > depth)
			return new Color(0,0,0);
		
		// Looking for nearest object the ray intersected
		double t = Obj_intersect(ray, scene, 0);
		
		if(t ==  Double.MAX_VALUE) {
			return scene.getBgdColor();
		} 
		
		else {
			
			ray.calSetIntPoint(t);
			
			// Normal from point of intersection
			Vector3d inc_normal = ray.getSceneObject().normal(ray.getIntPoint(), ray.getInside());
			
			// reverse of incident Ray
			Vector3d incRayRev = Math3d.ReverseVec(ray.getDir());
			
			// Angle of incidence
			double Ang_inc = Math3d.DotVecs(inc_normal, incRayRev);

			
			// Adding Ambient color to pixel color
			Color PixelColor = Color.ScaleCol(scene.getAmbColor(), ray.getSceneObject().getKd());
			
			Light light = (Light)scene.getLights().get(0);
			Point3d LightPos = light.getPos();

			// Checking for light obstruction =============================================
			double LightRayDist = Math3d.SubPoints(LightPos, ray.getIntPoint()).VecMag();
			Vector3d LightRayDir = Math3d.SubPoints(LightPos, ray.getIntPoint()).VecNormalize();

			// generating light ray towards the object
			Ray3d lightRay = new Ray3d(ray.getIntPoint(), LightRayDir);
			double s = Obj_intersect(lightRay, scene, ray.getSceneObject().getID());

			// Looking for any obstructing objects in the scene
			if((((s < LightRayDist)) && (lightRay.getSceneObject().getID() != ray.getSceneObject().getID())) == false) {
				

				// Adding Diffuse color to pixel color ========================================
				double diff = Math.max(Math3d.DotVecs(inc_normal, LightRayDir), 0.0);
				Color diffuse = Color.ScaleCol(light.getColor(), diff);
				diffuse = Color.ScaleCol(diffuse, ray.getSceneObject().getKd());
				PixelColor = Color.AddCols(PixelColor, diffuse);

				// Adding Specular color to pixel color =======================================
				double ang = Math3d.DotVecs(inc_normal, LightRayDir);
				Vector3d Scaled_normal = Math3d.ScaleVec(inc_normal, ang*2.0);
				Vector3d ReflectedLightDir = Math3d.SubVecs(Scaled_normal, LightRayDir);

				double spec = Math.max(Math3d.DotVecs(ReflectedLightDir, incRayRev), 0.0);
				double phong = Math.pow(spec, ray.getSceneObject().getPhong());
				Color specular = Color.ScaleCol(light.getColor(), phong);
				specular = Color.ScaleCol(specular, ray.getSceneObject().getKs());
				PixelColor = Color.AddCols(PixelColor, specular);

			}
			
			// Returning pixel color
			return PixelColor;
		}
	}
	
	
	
	//----------------------------------------------------------------------------------------------
	
	
	
	
	
}

