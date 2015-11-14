package gui;

public enum geometryObj {
	CIRCLE("Circle"), REGULAR_POLYGON("Regular Polygon"), CONE("Cone"), RIGHT_PYRAMID("Right Pyramid");
	private String value;
	
	private geometryObj(String value){
		this.value = value;
	}
}
