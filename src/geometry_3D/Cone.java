package geometry_3D;

import javax.naming.directory.InvalidAttributesException;

import geometry_2D.Circle;

public class Cone extends Solid{
	protected double height;
	protected Circle base;

	/**
	 * Create default cone. Height = 1.0
	 * Base = default Circle()
	 * @see Circle
	 */
	public Cone(){
		this.height = 1.0;
		this.base = new Circle();
		this.sideArea = calculateSideArea();
		this.totalArea = calculateTotalArea();
		this.volume = calculateVolume();
	}
	
	public Cone(Circle base, double height) throws InvalidAttributesException{
		if(height <= 0.0)
			throw new InvalidAttributesException("Height of cone must be greater then 0.0");
		if(base.equals(null))
			throw new NullPointerException("Base of cone was not passed");
		this.base = base;
		this.height = height;
		this.sideArea = calculateSideArea();
		this.totalArea = calculateTotalArea();
		this.volume = calculateVolume();
	}
	
	public double getHeight(){
		return height;
	}
	
	@Override
	protected double calculateSideArea() {
		double slantHeight;
		slantHeight = Math.sqrt(Math.pow(base.getRadius(), 2) + Math.pow(getHeight(), 2));
		return Math.PI * base.getRadius() * slantHeight;
	}

	@Override
	protected double calculateTotalArea() {
		return base.getArea() + getSideArea();
	}

	@Override
	protected double calculateVolume() {
		return ((base.getArea() * getHeight()) / 3);
	}

}
