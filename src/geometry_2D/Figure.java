package geometry_2D;

public abstract class Figure {
	protected double perimeter, area;
	protected abstract double calculatePerimeter(); 
	protected abstract double calculateArea();
	
	public double getPerimeter(){return perimeter;}
	public double getArea(){return area;}
}
