package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import gui.geometryObj;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.management.InvalidAttributeValueException;
import javax.naming.directory.InvalidAttributesException;
import javax.swing.DefaultComboBoxModel;
import java.awt.FlowLayout;
import java.awt.Insets;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import geometry_2D.Circle;
import geometry_2D.RegularPolygon;
import geometry_3D.Cone;
import geometry_3D.RightPyramid;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JTextField txtRadius;
	private JTextField txtNumberofedges;
	private JTextField txtEdgelendth;
	private JTextField txtHeight;
	private JTextField txtAreaValue;
	private JTextField txtPerimeterValue;
	private JTextField txtSideAreaValue;
	private JTextField txtTotalAreaValue;
	private JTextField txtVolumeValue;
	
	private int numberOfEdges;
	private double radius, edgeLength, height;
	private Circle Circle;
	private RegularPolygon RegularPolygon;
	private Cone Cone;
	private RightPyramid RightPyramid;
	private JTextField txtError;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void clearValueTextBoxes(){
		txtAreaValue.setText("");
		txtPerimeterValue.setText("");
		txtSideAreaValue.setText("");
		txtTotalAreaValue.setText("");
		txtVolumeValue.setText("");
		txtError.setText("");
	}
	
	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setTitle("Simple Geometry Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 324);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(geometryObj.values()));
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				switch ((geometryObj)comboBox.getSelectedItem()) {
				case CIRCLE:
					txtRadius.setEditable(true);
					txtHeight.setEditable(false);
					txtNumberofedges.setEditable(false);
					txtEdgelendth.setEditable(false);
					break;
					
				case REGULAR_POLYGON:
					txtRadius.setEditable(false);
					txtHeight.setEditable(false);
					txtNumberofedges.setEditable(true);
					txtEdgelendth.setEditable(true);
					break;
					
				case CONE:
					txtRadius.setEditable(true);
					txtHeight.setEditable(true);
					txtNumberofedges.setEditable(false);
					txtEdgelendth.setEditable(false);
					break;
					
				case RIGHT_PYRAMID:
					txtRadius.setEditable(false);
					txtHeight.setEditable(true);
					txtNumberofedges.setEditable(true);
					txtEdgelendth.setEditable(true);
					break;
					
				default:
					txtRadius.setEditable(false);
					txtHeight.setEditable(false);
					txtNumberofedges.setEditable(false);
					txtEdgelendth.setEditable(false);
					break;
				}
				clearValueTextBoxes();
			}
		});
		
		JLabel lblRadius = new JLabel("Radius");
		
		txtRadius = new JTextField();
		txtRadius.setText("1.0");
		txtRadius.setColumns(10);
		
		JLabel lblNumberOfEdges = new JLabel("Number of Edges");
		
		txtNumberofedges = new JTextField();
		txtNumberofedges.setEditable(false);
		txtNumberofedges.setText("3");
		txtNumberofedges.setColumns(10);
		
		JLabel lblEdgeLength = new JLabel("Edge Length");
		
		txtEdgelendth = new JTextField();
		txtEdgelendth.setEditable(false);
		txtEdgelendth.setText("1.0");
		txtEdgelendth.setColumns(10);
		
		JButton btnCalculate = new JButton("Calculate");	
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearValueTextBoxes();
				try{
					switch ((geometryObj)comboBox.getSelectedItem()) {
					case CIRCLE:
						radius = Double.parseDouble(txtRadius.getText());
						if (Circle == null || Circle.getRadius() != radius)	
							Circle = new Circle(radius);
						
						txtAreaValue.setText(Double.toString(Circle.getArea()));
						txtPerimeterValue.setText(Double.toString(Circle.getPerimeter()));
						break;
					
					case REGULAR_POLYGON:
						numberOfEdges = Integer.parseInt(txtNumberofedges.getText());
						edgeLength = Double.parseDouble(txtEdgelendth.getText());
						if(RegularPolygon == null || (RegularPolygon.getNumberOfEdges() != numberOfEdges || RegularPolygon.getEdgeLength() != edgeLength));
							RegularPolygon = new RegularPolygon(numberOfEdges, edgeLength);
							
					    txtAreaValue.setText(Double.toString(RegularPolygon.getArea()));
					    txtPerimeterValue.setText(Double.toString(RegularPolygon.getPerimeter()));
					    break;
					
					case CONE:
						radius = Double.parseDouble(txtRadius.getText());
						height = Double.parseDouble(txtHeight.getText());
						if(Cone == null || (Cone.getHeight() != height || Cone.getBase().getRadius() != radius)){
							Circle = new Circle(radius);
							Cone = new Cone(Circle, height);
						}
						
						txtAreaValue.setText(Double.toString(Cone.getBase().getArea()));
						txtPerimeterValue.setText(Double.toString(Cone.getBase().getPerimeter()));
						txtSideAreaValue.setText(Double.toString(Cone.getSideArea()));
						txtTotalAreaValue.setText(Double.toString(Cone.getTotalArea()));
						txtVolumeValue.setText(Double.toString(Cone.getVolume()));						
						break;
					
					case RIGHT_PYRAMID:
						numberOfEdges = Integer.parseInt(txtNumberofedges.getText());
						edgeLength = Double.parseDouble(txtEdgelendth.getText());
						height = Double.parseDouble(txtHeight.getText());
						if(RightPyramid == null || (RightPyramid.getHeight() != height || RightPyramid.getBase().getEdgeLength() != edgeLength || RightPyramid.getBase().getNumberOfEdges() != numberOfEdges)){
							RegularPolygon = new RegularPolygon(numberOfEdges, edgeLength);
							RightPyramid = new RightPyramid(RegularPolygon, height);
						}
						txtAreaValue.setText(Double.toString(RightPyramid.getBase().getArea()));
						txtPerimeterValue.setText(Double.toString(RightPyramid.getBase().getPerimeter()));
						txtSideAreaValue.setText(Double.toString(RightPyramid.getSideArea()));
						txtTotalAreaValue.setText(Double.toString(RightPyramid.getTotalArea()));
						txtVolumeValue.setText(Double.toString(RightPyramid.getVolume()));
						break;
						
					default:
						break;
					}
				} 
				catch (InvalidAttributeValueException atr) {
					txtError.setText(atr.getMessage());
				}
				catch (NullPointerException nil) {
					if(nil.getMessage().isEmpty())
						txtError.setText("Null pointer Exception");
					else
						txtError.setText(nil.getMessage());
				}
				catch (Exception ex) {
					txtError.setText(ex.getMessage());
				}
			}
		});
		
		JLabel lblHeight = new JLabel("Height");
		
		txtHeight = new JTextField();
		txtHeight.setEditable(false);
		txtHeight.setText("1.0");
		txtHeight.setColumns(10);
		
		JSeparator separator = new JSeparator();
		
		JLabel lblAreabaseArea = new JLabel("Area/Base Area:");
		
		JLabel lblPerimeter = new JLabel("Perimeter:");
		
		JSeparator separator_1 = new JSeparator();
		
		JLabel lblSideArea = new JLabel("Side Area:");
		
		JLabel lblTotalArea = new JLabel("Total Area:");
		
		JLabel lblVolume = new JLabel("Volume:");
		
		txtAreaValue = new JTextField();
		txtAreaValue.setEditable(false);
		txtAreaValue.setColumns(10);
		
		txtPerimeterValue = new JTextField();
		txtPerimeterValue.setEditable(false);
		txtPerimeterValue.setColumns(10);
		
		txtSideAreaValue = new JTextField();
		txtSideAreaValue.setEnabled(true);
		txtSideAreaValue.setEditable(false);
		txtSideAreaValue.setText("");
		txtSideAreaValue.setColumns(10);
		
		txtTotalAreaValue = new JTextField();
		txtTotalAreaValue.setEditable(false);
		txtTotalAreaValue.setColumns(10);
		
		txtVolumeValue = new JTextField();
		txtVolumeValue.setEditable(false);
		txtVolumeValue.setColumns(10);
		
		txtError = new JTextField();
		txtError.setEditable(false);
		txtError.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(txtError, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.addComponent(separator_1, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 554, GroupLayout.PREFERRED_SIZE)
						.addComponent(separator, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNumberOfEdges)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtNumberofedges, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblRadius)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblHeight)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(txtHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblEdgeLength)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtEdgelendth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblAreabaseArea)
								.addComponent(lblPerimeter))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtPerimeterValue)
								.addComponent(txtAreaValue, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTotalArea)
								.addComponent(lblVolume)
								.addComponent(lblSideArea))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtSideAreaValue, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
								.addComponent(txtTotalAreaValue)
								.addComponent(txtVolumeValue))
							.addGap(24))
						.addComponent(btnCalculate, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.addComponent(comboBox, 0, 554, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRadius)
						.addComponent(lblHeight)
						.addComponent(txtHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNumberOfEdges)
						.addComponent(txtNumberofedges, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEdgeLength)
						.addComponent(txtEdgelendth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCalculate)
					.addGap(3)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblAreabaseArea)
								.addComponent(txtAreaValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPerimeter)
								.addComponent(txtPerimeterValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSideArea)
								.addComponent(txtSideAreaValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTotalArea)
								.addComponent(txtTotalAreaValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblVolume)
								.addComponent(txtVolumeValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtError, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(194))
		);
		panel.setLayout(gl_panel);
	}
}
