package geometry_2D;

import javax.management.InvalidAttributeValueException;

import geometry_2D.Figure;
/**
 * 
 * @author Krzysztof Kopiñski
 *
 */
public class RegularPolygon extends Figure {

	private int numberOfEdges;
    private double edgeLength;
    private double squareOfRadiusOfCircumcircle;
	
    /**
     * Create default regular polygon. Square, edge length = 1.
     */
    public RegularPolygon(){
    	this.numberOfEdges = 4;
    	this.edgeLength = 1.0;
    	this.area = calculateArea();
    	this.perimeter = calculatePerimeter();
    	this.squareOfRadiusOfCircumcircle = calculateSquareOfRadiusOfCircumcircle();
    }
    
    /**
     * 
     * @param numberOfEdges - must be greater then 3
     * @param edgeLength - must be greater then 0.0
     * @throws InvalidAttributesException
     */
	public RegularPolygon(int numberOfEdges, double edgeLength) throws InvalidAttributeValueException{
		if (numberOfEdges < 3)
			throw new InvalidAttributeValueException("Number of edges must be at least 3");
		this.numberOfEdges = numberOfEdges;
		
		if(edgeLength <= 0.0)
			throw new InvalidAttributeValueException("Length of edge must be greater then 0.0");
		this.edgeLength = edgeLength;
		this.squareOfRadiusOfCircumcircle = calculateSquareOfRadiusOfCircumcircle();
		this.area = calculateArea();
    	this.perimeter = calculatePerimeter();
    	this.squareOfRadiusOfCircumcircle = calculateSquareOfRadiusOfCircumcircle();
	}
	
	protected double calculateSquareOfRadiusOfCircumcircle(){
		double alpha = (2*Math.PI)/numberOfEdges;
		//Regular Polygon N size can be divide on N isosceles triangles.
		//From the law of cosines i can get square of radius of Circumcircle of a regular polygon
		//needed in further calculations
		return (Math.pow(edgeLength, 2) / (2* (1 - Math.cos(alpha))));
	}
	
	protected double getSquareOfRadiusOfCircumcircle(){
		return squareOfRadiusOfCircumcircle;
	}
	
	public int getNumberOfEdges(){
		return numberOfEdges;
	}
	
	public double getEdgeLength(){
		return edgeLength;
	}
	
	@Override
	protected double calculateArea() {
		double area, radius_square, alpha = (2*Math.PI)/numberOfEdges;
		radius_square = getSquareOfRadiusOfCircumcircle();
		
		//Area of any regular polygon
		//Radius of Circumcircle of a regular polygon is a arm of isoscale triangle
		//Area of triangle is 0.5 * length of triangle arm * length of triangle arm * sin(angle between them).
		//N this triangle creates regular polygon
		area = (numberOfEdges * radius_square * Math.sin(alpha))/2;
		return area;
	}

	@Override
	protected double calculatePerimeter() {
		return (getNumberOfEdges() * getEdgeLength());
	}
}
