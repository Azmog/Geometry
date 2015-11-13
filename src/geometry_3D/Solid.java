package geometry_3D;

public abstract class Solid {
	protected double sideArea, totalArea, volume;
	
	protected abstract double calculateSideArea();
	protected abstract double calculateTotalArea();
	protected abstract double calculateVolume();
	
	public double getSideArea(){return sideArea;}
	public double getTotalArea(){return totalArea;}
	public double getVolume(){return volume;}
}
