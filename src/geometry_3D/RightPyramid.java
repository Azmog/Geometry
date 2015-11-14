package geometry_3D;

import javax.management.InvalidAttributeValueException;
import geometry_3D.Solid;
import geometry_2D.RegularPolygon;
/**
 * 
 * @author Krzysztof Kopiñski
 *
 */
public class RightPyramid extends Solid {
	private RegularPolygon base;
	private double height;
	
	/**
	 * Create default pyramid. Base is default RegularPolygon, height = 1.0  
	 * @see RegularPolygon
	 */
	public RightPyramid(){
		this.base = new RegularPolygon();
		this.height = 1.0;
	}
	/**
	 * 
	 * @param base - Base of pyramid. 
	 * @param height - height of pyramid. Must be greater then 0.0.
	 * @throws InvalidAttributesException
	 * @throws NullPointerException
	 */
	public RightPyramid(RegularPolygon base, double height) throws InvalidAttributeValueException{
		if (base.equals(null))
			throw new NullPointerException();
		if (height <= 0.0)
			throw new InvalidAttributeValueException("Height must be greater then 0.0.");
		
		this.base = base;
		this.height = height;
		this.sideArea = calculateSideArea();
		this.totalArea = calculateTotalArea();
		this.volume = calculateVolume();
	}
	
	public double getHeight(){
		return height;
	}
	
	public RegularPolygon getBase(){
		return base;
	}
	
	@Override
	protected double calculateVolume() {
		return ((base.getArea() * getHeight()) / 3);
	}
	@Override
	protected double calculateSideArea() {
		//Face of right pyramid is triangle. Sum of N triangle areas are side area.
		double faceHeight, faceEdge;
		
		//From the Pythagorean theorem i can calculate face edge and face height needed in further calculations
		faceEdge = Math.sqrt(base.getSquareOfRadiusOfCircumcircle() + Math.pow(height, 2));
		
		//faceEdge^2 = faceHeight^2 + (1/2 base.edgeLenght)^2
		//faceHeight = sqrt(faceEdge^2 - (1/2 base.edgeLenght)^2)
		faceHeight = Math.sqrt(Math.pow(faceEdge,2) - Math.pow(base.getEdgeLength()/2,2));

		return base.getNumberOfEdges() * (base.getEdgeLength() * faceHeight)/2;
	}
	@Override
	protected double calculateTotalArea() {
		return getSideArea() + base.getArea();
	}


}
