import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class ProductListViewForAdmin extends View {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel scrollPanel;
	
	public ProductListViewForAdmin() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		add(panel, BorderLayout.NORTH);
		
		JButton ReportButton = new JButton("Report");
		panel.add(ReportButton);
		
		ReportButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				AddItemForAdmin.display(getController());
			}
		});
		
		JButton AddItemButton = new JButton("AddItem");
		
		panel.add(AddItemButton);
		
		AddItemButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				AddItemForAdmin.display(getController());
			}
		});
		
		JButton cartButton = new JButton("Logout");
		
		cartButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				getController().showLoginForAdmin();
			}
		});
		
		panel.add(cartButton);
		
		scrollPanel = new JPanel();
		JScrollPane scroll = new JScrollPane(scrollPanel);
		scrollPanel.setLayout(new GridLayout(0, 3, 0, 0));
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		add(scroll, BorderLayout.CENTER);

	}

	public void initialize() {
		scrollPanel.removeAll();
		List<Product> list = getController().getBackend().getProducts();
		for(Product p : list){
			scrollPanel.add(new ProductThumbnailForAdmin(getController(), p));
		}
		revalidate();
	}

}
