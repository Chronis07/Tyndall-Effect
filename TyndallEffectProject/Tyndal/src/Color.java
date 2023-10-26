/**
 * This class decribes the properties and methods of the Color object
 *
 * @author      Atharva Sawe
 * 
 */
public class Color {
	
	// Making red, green and blue components
	private double red;
	private double green;
	private double blue;
	
	
	public Color(){}
	
	//--------------------------------------------------------------------------------------------
	
	/** 
	 * Set functions to edit the properties of the Color object
	 *
	 */
	
	// SET functions
	
	public void setR (double x) {red = x;}
	
	public void setG (double x) {green = x;}
	
	public void setB (double x) {blue = x;}
					
	//--------------------------------------------------------------------------------------------
	
	/** 
	 * Get functions to access the properties of the Point3d object
	 *
	 */
	
	// GET functions
	
	public double getR() {return red;}
		
	public double getG() {return green;}
		
	public double getB() {return blue;}
	
	
	//--------------------------------------------------------------------------------------------
	
	/** 
	 * Constructor function to generate a Color object
	 * 
	 * @param	R	Red component value of color
	 * @param	G	Green component value of color
	 * @param	B	Blue component value of color
	 * 
	 */
	
	public Color(double R, double G, double B) {
		red = R;
		green = G;
		blue = B;
	}
	
	//--------------------------------------------------------------------------------------------
	
	/** 
	 * Constructor function to generate a Color object
	 * 
	 * @param	C	another Color object
	 * 
	 */
		
	public Color(Color C) {
		red = C.getR();
		green = C.getG();
		blue = C.getB();
	}
		
	//--------------------------------------------------------------------------------------------
	
	/** 
	 * function to add two colors
	 * 
	 * @return	resultant sum color from adding the two colors
	 *
	 */
	
	public static Color AddCols(Color X, Color Y) {
		double r = X.getR() + Y.getR();
		double g = X.getG() + Y.getG();
		double b = X.getB() + Y.getB();
		return new Color(r, g, b);
	}
		
	//--------------------------------------------------------------------------------------------
	
	/** 
	 * function to scale each component of a color by a factor
	 * 
	 * @param	C	Color to be scaled
	 * @param	f	Factor to scale the color by
	 * 
	 * @return		resultant new scaled color
	 *
	 */
	
	public static Color ScaleCol(Color X, double f) {
		double r = X.getR() * f;
		double g = X.getG() * f;
		double b = X.getB() * f;
		return new Color(r, g, b);
	}
		
	//--------------------------------------------------------------------------------------------
	
	/** 
	 * function to scale each component of a color by the corresponding 
	 * component of another color
	 * 
	 * @param	C1	Color to be scaled
	 * @param	C2	Color to be scaled
	 * 
	 * @return		resultant new scaled color
	 *
	 */
	
	public static Color ScaleCol(Color C1, Color C2) {
		double r = C1.getR() * C2.getR();
		double g = C1.getG() * C2.getG();
		double b = C1.getB() * C2.getB();
		return new Color(r, g, b);
	}
		
	//--------------------------------------------------------------------------------------------
	
	/** 
	 * function to check if all components of the color are ZERO
	 * 
	 * @return		True/False boolean
	 *
	 */
	
	public boolean isZero() {
		if((red == 0.0) && (green == 0.0) && (blue == 0.0)) {
			return true;
		}
		else {return false;}
	}
		
	//--------------------------------------------------------------------------------------------
	
	/** 
	 * functions to return scaled version of red, green and blue components of color
	 * and capping the max value of each component at 255
	 *
	 */
	
	public int getSR() {
		double res = red * 255.0;
		if(res <= 255.0) {return (int) res;}
		return (int) 255.0;
	}
		
	public int getSG() {
		double res = green * 255.0;
		if(res <= 255.0) {return (int) res;}
		return (int) 255.0;
	}
		
	public int getSB() {
		double res = blue * 255.0;
		if(res <= 255.0) {return (int) res;}
		return (int) 255.0;
	}
		
	//--------------------------------------------------------------------------------------------
	
	/** 
	 * function to represent Color object as string to print to console
	 * 
	 */
	
	public String Stringify() {
		return "C_(" + red + ", " + green + ", " + blue + ")";
	}
		
	//--------------------------------------------------------------------------------------------
	
}
