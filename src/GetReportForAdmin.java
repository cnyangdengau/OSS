import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class GetReportForAdmin extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField CustomerName;
	private JTextField ProductName;
	

	public static void display(ShopController c){
		GetReportForAdmin dialog = new GetReportForAdmin(c);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setLocationRelativeTo(c.getWindow());
		dialog.setVisible(true);
	}
	
	
	public GetReportForAdmin(ShopController c) {
				
		setTitle("Report");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			CustomerName = new JTextField();
			GridBagConstraints gbc_fullName = new GridBagConstraints();
			gbc_fullName.insets = new Insets(0, 0, 5, 5);
			gbc_fullName.fill = GridBagConstraints.HORIZONTAL;
			gbc_fullName.gridx = 1;
			gbc_fullName.gridy = 2;
			contentPanel.add(CustomerName, gbc_fullName);
			CustomerName.setColumns(10);
		}
		{
			GridBagConstraints gbc_lblFullName = new GridBagConstraints();
			gbc_lblFullName.insets = new Insets(0, 0, 5, 5);
			gbc_lblFullName.gridx = 2;
			gbc_lblFullName.gridy = 2;
			JButton CheckCustomerButton = new JButton("Check the Customer Name");
			CheckCustomerButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					if(CustomerName.getText().equals("")){
						Component frame = null;
						JOptionPane.showMessageDialog(frame, "Please input a Customer Name"); 
					}
					else{
						String Message = "The Customer: " + CustomerName.getText() + "   buy these things: " + "\n\n";
						Message += "He has " + c.GetCustomerReport(CustomerName.getText()).size() + "  orders. \n\n";
						for(HashMap<String,Float> hashmap : c.GetCustomerReport(CustomerName.getText()))
						{
							Iterator<String> iter;
							for (iter = hashmap.keySet().iterator(); iter.hasNext();) {
								  String str = iter.next();
								  Message += "Product: " + str + "   " + "Quantity:" + hashmap.get(str) + "\n";
							}
							Message += "\n\n";
						}
						Component frame = null;
						JOptionPane.showMessageDialog(frame, Message);
					}
				}
			});
			contentPanel.add(CheckCustomerButton, gbc_lblFullName);	
		}

		
		{
			ProductName = new JTextField();
			GridBagConstraints gbc_fullName = new GridBagConstraints();
			gbc_fullName.insets = new Insets(0, 0, 5, 5);
			gbc_fullName.fill = GridBagConstraints.HORIZONTAL;
			gbc_fullName.gridx = 1;
			gbc_fullName.gridy = 3;
			contentPanel.add(ProductName, gbc_fullName);
			ProductName.setColumns(10);
		}
		{
			GridBagConstraints gbc_lblFullName = new GridBagConstraints();
			gbc_lblFullName.insets = new Insets(0, 0, 5, 5);
			gbc_lblFullName.gridx = 2;
			gbc_lblFullName.gridy = 3;
			JButton CheckProductButton = new JButton("Check the Product Name");
			CheckProductButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					if(ProductName.getText().equals("")){
						Component frame = null;
						JOptionPane.showMessageDialog(frame, "Please input a Product Name"); 
					}
					else{
						String Message = "The name of customer who buy the product: " + ProductName.getText() + "\n\n";
						for(String CustomerName : c.GetProductReport(ProductName.getText()))
						{
							Message += CustomerName + "\n";
						}
						Component frame = null;
						JOptionPane.showMessageDialog(frame, Message);
					}
					}
			});
			contentPanel.add(CheckProductButton, gbc_lblFullName);
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JDialog me = this;
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						me.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
