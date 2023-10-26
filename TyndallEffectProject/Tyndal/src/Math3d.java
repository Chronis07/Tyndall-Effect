/**
 * This class contains 3D mathematics methods
 *
 * @author      Atharva Sawe
 * 
 */

public class Math3d {
	
	/** 
	 * Function to Add two 3D points
	 * 
	 * @return		Vector3d resultant
	 * 
	 */
	
	
	public static Vector3d AddPoints(Point3d p1, Point3d p2) {
		double sumx = p1.getX() + p2.getX();
		double sumy = p1.getY() + p2.getY();
		double sumz = p1.getZ() + p2.getZ();
		return new Vector3d(sumx, sumy, sumz);
	}
	
	//------------------------------------------------------------------------------------
	
	/** 
	 * Function to Subtract two 3D points
	 * 
	 * @return		Vector3d resultant
	 * 
	 */
	
	
	public static Vector3d SubPoints(Point3d p1, Point3d p2) {
		double difx = p1.getX() - p2.getX();
		double dify = p1.getY() - p2.getY();
		double difz = p1.getZ() - p2.getZ();
		return new Vector3d(difx, dify, difz);
	}
	
	//------------------------------------------------------------------------------------
	
	/** 
	 * Function to Add two 3D Vectors
	 * 
	 * @return		Vector3d resultant
	 * 
	 */
	
	
	public static Vector3d AddVecs(Vector3d v1, Vector3d v2) {
		double sumx = v1.getX() + v2.getX();
		double sumy = v1.getY() + v2.getY();
		double sumz = v1.getZ() + v2.getZ();
		return new Vector3d(sumx, sumy, sumz);
	}
	
	//------------------------------------------------------------------------------------
	
	/** 
	 * Function to Subtract two 3D Vectors
	 * 
	 * @return		Vector3d resultant
	 * 
	 */
	
	
	public static Vector3d SubVecs(Vector3d v1, Vector3d v2) {
		double difx = v1.getX() - v2.getX();
		double dify = v1.getY() - v2.getY();
		double difz = v1.getZ() - v2.getZ();
		return new Vector3d(difx, dify, difz);
	}
	
	//------------------------------------------------------------------------------------
	
	/** 
	 * Function to compute dot product of two 3D vectors
	 * 
	 * @return		Dot product value
	 * 
	 */
	
	
	public static double DotVecs(Vector3d v1, Vector3d v2) {
		double prodx = v1.getX() * v2.getX();
		double prody = v1.getY() * v2.getY();
		double prodz = v1.getZ() * v2.getZ();
		return prodx + prody + prodz;
	}
	
	//------------------------------------------------------------------------------------
	
	/** 
	 * Function to compute cross product of two 3D vectors
	 * 
	 * @return		resultant cross product vector
	 * 
	 */
	
	
	public static Vector3d CrossVecs(Vector3d v1, Vector3d v2) {
		double tx = (v1.getY() * v2.getZ()) - (v1.getZ() * v2.getY());
		double ty = (v1.getZ() * v2.getX()) - (v1.getX() * v2.getZ());
		double tz = (v1.getX() * v2.getY()) - (v1.getY() * v2.getX());
		return new Vector3d(tx, ty, tz);
	}
	
	//------------------------------------------------------------------------------------
	
	/** 
	 * Function to reverse the direction of a vector
	 * 
	 * @return		Vector in reversed direction
	 * 
	 */
	
	
	public static Vector3d ReverseVec(Vector3d tempv) {
		return new Vector3d(-tempv.getX(), -tempv.getY(), -tempv.getZ());
	}
	
	//------------------------------------------------------------------------------------
	
	/** 
	 * Function to scale a vector by a factor
	 * 
	 * @param	tv	vector to be scaled
	 * @param	c	factor to scale the vector by
	 * 
	 * @return		resultant scaled vector
	 * 
	 */
	
	
	public static Vector3d ScaleVec(Vector3d tv, double c) {
		double sx = tv.getX() * c;
		double sy = tv.getY() * c;
		double sz = tv.getZ() * c;
		return new Vector3d(sx, sy, sz);
	}
	
	//------------------------------------------------------------------------------------
	
	
	
}
