package geometry_2D;

import javax.naming.directory.InvalidAttributesException;

import Interfaces.Figure;

public class RegularPolygon implements Figure {

	private int numberOfEdgeds;
    private double edgeLength;
	
	RegularPolygon(int numberOfEdges, double edgeLength) throws InvalidAttributesException{
		if (numberOfEdges < 3)
			throw new InvalidAttributesException("Number of edges must be at least 3");
		this.numberOfEdgeds = numberOfEdges;
		
		if(edgeLength <= 0.0)
			throw new InvalidAttributesException("Length of edge must be greater then 0.0");
		this.edgeLength = edgeLength;
	}
	
	@Override
	public double getArea() {
		double area, radius_square, alpha = 360/numberOfEdgeds;
		//Regular Polygon N size can be divide on N isosceles triangles.
		//From the law of cosines i can get square of radius of Circumcircle of a regular polygon
		//needed in further calculations
		radius_square = Math.pow(edgeLength, 2) / (2* (1 - Math.cos(alpha)));
		//Area of any regular polygon
		//Radius of Circumcircle of a regular polygon is a arm of isoscale triangle
		//Area of triangle is 0.5 * length of triangle arm * length of triangle arm * sin(angle between them).
		//N this triangle creates regular polygon
		area = numberOfEdgeds * 0.5 * radius_square * Math.sin(alpha);
		return area;
	}

	@Override
	public double getPerimeter() {
		return (numberOfEdgeds * edgeLength);
	}

}
