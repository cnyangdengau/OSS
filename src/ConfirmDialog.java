import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ConfirmDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private static int OrderNumber;
	private Cart CartA;

	public static void display(ShopController c){
		ConfirmDialog dialog = new ConfirmDialog(c);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setLocationRelativeTo(c.getWindow());
		dialog.setVisible(true);
	}
	
	public static boolean fileExists(String filePath){
		File file=new File(filePath); 
		if(!file.exists()) { 
		return false;
		} else{
		return true;
		}
	} 
	//Generate Order details to a .txt file
	public static void generateOrderFile(ShopController c)
    {
        try {
        	OrderNumber = c.getOrderNumber();
        	String OrderFileName = "The Order " + OrderNumber + " Details.txt";
        	if (!fileExists(OrderFileName))
        	{
            PrintWriter writer = new PrintWriter(OrderFileName, "UTF-8");
            Customer CustomerA = c.getCurrentCustomerDetails();
            String CustomerName = CustomerA.name;
            String CardNumber = CustomerA.cardNumber;
            String text = "The OrderID is " + OrderNumber +"\n";
            text += "The CustomerID is " + CustomerName +"\n";
            text += "Order Items listed: \n";
            for (CartItem item : c.getCart().getList())
            {       
                Cart Cartdemo = new Cart();
                Cartdemo.add(item);
                text += "ITEM: "+item.product.getName() +";QTY: " + item.quantity + ";PRICE:" + c.getBackend().getPrice(Cartdemo) + "\n";
            }
            text += "\n The Total Price is: " + c.getBackend().getPrice(c.getCart()) + "\n"; 
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			text += "\n" + "Your Order Date is: "+(String)df.format(new Date());
			text += "\n" + "Your Card Number is: " + CardNumber;
            writer.println(text);  
            writer.close();
        	}
        	} catch (Exception e) {  
        		e.printStackTrace();  
        	}  
    }
	
	//
	public static void RefreshOrderNumber(ShopController c){
        PrintWriter writer2 = null;
        OrderNumber = c.getOrderNumber()+1;
		try {
			writer2 = new PrintWriter("OrderNumber.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String text2 = Integer.toString(OrderNumber);
        writer2.println(text2); 
        writer2.close();
	}
	
	public ConfirmDialog(ShopController c) {
		JDialog me = this;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel panel = new JPanel();
			panel.setBorder(new EmptyBorder(10, 10, 10, 10));
			getContentPane().add(panel, BorderLayout.CENTER);
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			{
				JLabel lblNewLabel = new JLabel("ORDER DETAILS:");
				panel.add(lblNewLabel);
			}
			{
				JLabel spacer = new JLabel("  ");
				panel.add(spacer);
			}
			{
				OrderNumber = c.getOrderNumber();
				String text = "<html>Your Order Number is: "+ OrderNumber + "<br>";
				Customer CustomerA = c.getCurrentCustomerDetails();
                String CustomerName = CustomerA.name;
                String CardNumber = CustomerA.cardNumber;
                text += "CustomerName:  " + CustomerName + "  " + "<br>";
                text += "Your Items Listed:" + "<br>" + "<br>";
				for(CartItem item : c.getCart().getList()){
					Cart thisItemInACart = new Cart();
					thisItemInACart.add(item);
					text += "ITEM: "+item.product.getName() +
							"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;QTY: " + item.quantity +
							"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;PRICE: " + c.getBackend().getPrice(thisItemInACart) + "<br>";
				}
				CartA = c.getCart();
				text += "<br>"+ "The Total Price is:  " + c.getBackend().getPrice(CartA);
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				text += "<br>" + "Your Order Date is: "+(String)df.format(new Date());
				text += "<br>" + "Your Card Number is: " + CardNumber;
				text += "</html>";
				JLabel details = new JLabel(text);
				panel.add(details);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton confirmButton = new JButton("Confirm order");
				confirmButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//Call the function of generate order file
						generateOrderFile(c);
						//Call the function of refresh Order number
						RefreshOrderNumber(c);
						c.attemptTransaction();
						me.dispose();
					}
				});
				confirmButton.setActionCommand("OK");
				buttonPane.add(confirmButton);
				getRootPane().setDefaultButton(confirmButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
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
