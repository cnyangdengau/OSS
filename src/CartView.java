import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

public class CartView extends View {

	private static final long serialVersionUID = 1L;
	
	private JPanel scrollPanel;
	private JButton btnClear, btnCheckout;
	private JLabel lblNetTotal;
	public Cart cartNew;

	public CartView() {

		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(panel, BorderLayout.NORTH);
		
		JButton btnBack = new JButton("Back to products");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getController().showProductList();
			}
		});
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.add(btnBack);
		
		btnClear = new JButton("Remove all from cart");
		panel.add(btnClear);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		panel.add(horizontalGlue);
		
		JLabel lblTotal = new JLabel("Total:");
		panel.add(lblTotal);
		
		lblNetTotal = new JLabel();
		panel.add(lblNetTotal);
		
		btnCheckout = new JButton("Checkout");
		panel.add(btnCheckout);

		scrollPanel = new JPanel();
		JScrollPane scroll = new JScrollPane(scrollPanel);
		scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.Y_AXIS));
		
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		add(scroll, BorderLayout.CENTER);
		
	}

	public void initialize() {
		
		scrollPanel.removeAll();
		
		for(CartItem item : getController().getCart().getList()){
			JPanel itemPanel = new JPanel();
			itemPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
			itemPanel.setAlignmentX(LEFT_ALIGNMENT);
			itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
			JPanel titlePanel = new JPanel();
			itemPanel.add(titlePanel);
			titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
			JLabel lblNewLabel_3 = new JLabel(item.product.getName());
			lblNewLabel_3.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			titlePanel.add(lblNewLabel_3);
			Component horizontalGlue_1 = Box.createHorizontalGlue();
			titlePanel.add(horizontalGlue_1);
			JPanel propertiesPanel = new JPanel();
			itemPanel.add(propertiesPanel);
			propertiesPanel.setLayout(new BoxLayout(propertiesPanel, BoxLayout.X_AXIS));
			JPanel quantityPanel = new JPanel();
			propertiesPanel.add(quantityPanel);
			quantityPanel.setLayout(new FlowLayout());
			JLabel lblQuantuty = new JLabel("Quantity: ");
			//Add a Spinner in the Quantity Panel for Editing
			JSpinner NewQuantitySpinner;
			quantityPanel.add(lblQuantuty);
			{
				NewQuantitySpinner = new JSpinner();
				// Set the default number, which should be the quantity customer prefer.
				NewQuantitySpinner.setModel(new SpinnerNumberModel(item.quantity, 1, 100000, 1));
				NewQuantitySpinner.setPreferredSize(new Dimension(100, 30));
				quantityPanel.add(NewQuantitySpinner);
				// Add A button for setting the new quantity
				JButton setButton = new JButton("set");
				//setButton.setActionCommand("OK");
				quantityPanel.add(setButton);
				//Set the ActionListener of the button
				setButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						//Create a QuantityChange Variable to get the change of Value
						float QuantityChange = Float.parseFloat(NewQuantitySpinner.getValue()+" ")-item.quantity;
						getController().addToCart(item.product, QuantityChange);
						//Refresh the Cart View
						getController().showCartView();
					}
				});
			}
			JLabel spacer_1 = new JLabel("          ");
			quantityPanel.add(spacer_1);
			JLabel lblNewLabel = new JLabel("Price ($):   ");
			quantityPanel.add(lblNewLabel);
			
			Cart thisItemInACart = new Cart();
			thisItemInACart.add(item);
			JLabel lblNewLabel_2 = new JLabel("$"+getController().getBackend().getPrice(thisItemInACart));
			
			quantityPanel.add(lblNewLabel_2);
			JLabel label = new JLabel("          ");
			quantityPanel.add(label);
			
			JButton btnRemove = new JButton("Remove");
			btnRemove.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					getController().getCart().remove(item);
					getController().showCartView();
				}
			});
			propertiesPanel.add(btnRemove);
			
			Component horizontalGlue = Box.createHorizontalGlue();
			propertiesPanel.add(horizontalGlue);
			JSeparator separator = new JSeparator();
			itemPanel.add(separator);
			scrollPanel.add(itemPanel);
		}
		
		btnClear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				getController().getCart().setItems(new ArrayList<CartItem>());
				getController().showCartView();
			}
		});
		
		btnCheckout.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				getController().showCheckout();
			}
		});
		
		lblNetTotal.setText("$"+getController().getTotalCartPrice());
		
		Component verticalGlue = Box.createVerticalGlue();
		scrollPanel.add(verticalGlue);
		
	}

}
