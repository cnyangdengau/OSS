import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
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
//	private JTextField homeAddr;
//	private JTextField cardNum;
//	String CustomerNameInTextField = null;

	public static void display(ShopController c){
		GetReportForAdmin dialog = new GetReportForAdmin(c);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setLocationRelativeTo(c.getWindow());
		dialog.setVisible(true);
	}
	
//	public Customer toCustomer(){
//		return new Customer(fullName.getText(), homeAddr.getText(), cardNum.getText(), phoneNumber.getText());
//	}
	
//	public Product toProduct(){
//		Product birb = new Product(fullName.getText());
//		birb.setProperty("price", "Price ($)",Float.valueOf(phoneNumber.getText()));
//		birb.setImage(homeAddr.getText());
//		return birb;
//	}
	
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
			gbc_fullName.gridy = 1;
			contentPanel.add(CustomerName, gbc_fullName);
			CustomerName.setColumns(10);
		}
		{
			GridBagConstraints gbc_lblFullName = new GridBagConstraints();
			gbc_lblFullName.insets = new Insets(0, 0, 5, 5);
			gbc_lblFullName.gridx = 2;
			gbc_lblFullName.gridy = 1;
			JButton CheckCustomerButton = new JButton("Check the Customer Name");
			//okButton.setActionCommand("OK");
			CheckCustomerButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					//System.out.println(CustomerName.getText());
					//ArrayList<HashMap<String,Float>> HashMap = c.GetCustomerOrders(CustomerName.getText());
					//CustomerNameInTextField = CustomerName.getText();
					if(CustomerName.getText().equals("")){
						Component frame = null;
						JOptionPane.showMessageDialog(frame, "Please input a Customer Name"); 
					}
					else{
						for(HashMap<String,Float> hashmap : c.GetCustomerReport(CustomerName.getText()))
						{
							System.out.print(hashmap.toString());
						}
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
			gbc_fullName.gridy = 2;
			contentPanel.add(ProductName, gbc_fullName);
			ProductName.setColumns(10);
		}
		{
			GridBagConstraints gbc_lblFullName = new GridBagConstraints();
			gbc_lblFullName.insets = new Insets(0, 0, 5, 5);
			gbc_lblFullName.gridx = 2;
			gbc_lblFullName.gridy = 2;
			JButton CheckProductButton = new JButton("Check the Product Name");
			///okButton.setActionCommand("OK");
			CheckProductButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					if(ProductName.getText().equals("")){
						Component frame = null;
						JOptionPane.showMessageDialog(frame, "Please input a Product Name"); 
					}
					else{
						for(String CustomerName : c.GetProductReport(ProductName.getText()))
						{
							System.out.println(CustomerName);
						}
					}
					}
			});
			contentPanel.add(CheckProductButton, gbc_lblFullName);
		}

//		{
//			JLabel lblHomeAddress = new JLabel("Set the Image URL");
//			lblHomeAddress.setHorizontalAlignment(SwingConstants.LEFT);
//			GridBagConstraints gbc_lblHomeAddress = new GridBagConstraints();
//			gbc_lblHomeAddress.anchor = GridBagConstraints.EAST;
//			gbc_lblHomeAddress.insets = new Insets(0, 0, 5, 5);
//			gbc_lblHomeAddress.gridx = 1;
//			gbc_lblHomeAddress.gridy = 3;
//			contentPanel.add(lblHomeAddress, gbc_lblHomeAddress);
//		}
//		{
//			homeAddr = new JTextField();
//			GridBagConstraints gbc_homeAddr = new GridBagConstraints();
//			gbc_homeAddr.insets = new Insets(0, 0, 5, 5);
//			gbc_homeAddr.fill = GridBagConstraints.HORIZONTAL;
//			gbc_homeAddr.gridx = 2;
//			gbc_homeAddr.gridy = 3;
//			contentPanel.add(homeAddr, gbc_homeAddr);
//			homeAddr.setColumns(10);
//			//homeAddr.setText(user.address);
//		}
//		{
//			JLabel lblCardNumber = new JLabel("Card number:");
//			GridBagConstraints gbc_lblCardNumber = new GridBagConstraints();
//			gbc_lblCardNumber.anchor = GridBagConstraints.EAST;
//			gbc_lblCardNumber.insets = new Insets(0, 0, 0, 5);
//			gbc_lblCardNumber.gridx = 1;
//			gbc_lblCardNumber.gridy = 4;
//			contentPanel.add(lblCardNumber, gbc_lblCardNumber);
//		}
//		{
//			cardNum = new JTextField();
//			GridBagConstraints gbc_cardNum = new GridBagConstraints();
//			gbc_cardNum.insets = new Insets(0, 0, 0, 5);
//			gbc_cardNum.fill = GridBagConstraints.HORIZONTAL;
//			gbc_cardNum.gridx = 2;
//			gbc_cardNum.gridy = 4;
//			contentPanel.add(cardNum, gbc_cardNum);
//			cardNum.setColumns(10);
//			//cardNum.setText(user.cardNumber);
//		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
//			{
//				JDialog me = this;
//				JButton okButton = new JButton("OK");
//				okButton.setActionCommand("OK");
//				okButton.addActionListener(new ActionListener(){
//					public void actionPerformed(ActionEvent e) {
//						c.setView(new ProductListViewForAdmin());
//						me.dispose();
//					}
//				});
//				buttonPane.add(okButton);
//				getRootPane().setDefaultButton(okButton);
//			}
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
