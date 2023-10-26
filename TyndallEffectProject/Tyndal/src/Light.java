/**
 * This class decribes the properties and methods of the Light source object
 *
 * @author      Atharva Sawe
 * 
 */


public class Light {
	
	// Making color and position of light
	private Color color;
	private Point3d position;
	
	public Light(){}
	
	//--------------------------------------------------------------------------------------------
	
	/** 
	 * Get functions to access the properties of the Light object
	 *
	 */
	
	// GET functions
	
	public Color getColor() {return color;}
	
	public Point3d getPos() {return position;}
	
	//--------------------------------------------------------------------------------------------
	
	/** 
	 * Constructor function to generate a Light object
	 * 
	 * @param	c		Color of Light
	 * @param	pos		Position of Light
	 * 
	 */
	
	
	public Light(Color c, Point3d pos) {
		color = c;
		position = pos;
	}
	
	//--------------------------------------------------------------------------------------------
	

}
