package geometry_2D;

import javax.naming.directory.InvalidAttributesException;

import geometry_2D.Figure;

/**
 * @author Krzysztof Kopiñski
 *
 */
public class Circle extends Figure {
	private double radius;
	/**
	 * Create default Circle. Radius = 1.0
	 */
	public Circle(){
		this.radius = 1.0;
		this.area = calculateArea();
		this.perimeter = calculatePerimeter();
	}
	/**
	 * 
	 * @param radius - must be greater then 0.0
	 * @throws InvalidAttributesException
	 */
	public Circle (double radius) throws InvalidAttributesException{
		if (radius <= 0.0)
			throw new InvalidAttributesException("Radius must be greater then 0.0");
		this.radius = radius;
		this.area = calculateArea();
	}
	
	public double getRadius(){
		return radius;
	}
	
	@Override
	protected double calculateArea() {
		return (Math.PI * Math.pow(getRadius(), 2));
	}
	
	@Override
	protected double calculatePerimeter() {
		return (2 * Math.PI * getRadius());
	}

}
