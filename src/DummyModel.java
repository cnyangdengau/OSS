import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DummyModel implements Model {

	ArrayList<Product> productList = new ArrayList<Product>();
	HashMap<String, String> passwords = new HashMap<>();
	HashMap<String, Customer> details = new HashMap<>();
	HashMap<String, Admin> AdminDetails = new HashMap<>();

	
	public DummyModel(){
		
		passwords.put("Admin", "Admin");
		//AdminDetails.put("admink", new Admin("john", "9547"));
		
		passwords.put("Customer", "Customer");
		details.put("Customer", new Customer("Fred","1221","dadasd","dsdsada"));
		

		for(int i = 0; i < 6; i++){
			Product birb = new Product("Item #"+i);
			birb.setProperty("price", "Price ($)", 100f);
			birb.setImage("https://files.pccasegear.com/images/BX80662I56400-thumb.jpg");
			productList.add(birb);
		}
	}
	
//	public void setProductList(Product p){
//		productList.add(p);
//	}
	
	public boolean setProductList(Product p) {
		productList.add(p);
		return true;
	}
	
	public List<Product> getProducts() {
		return productList;
	}

	public boolean login(String username, String password) {
		if(passwords.containsKey(username)){
			return passwords.get(username).equals(password);
		}
		return false;
	}

	public boolean signup(String username, String password) {
		if(passwords.containsKey(username)) return false;
		passwords.put(username, password);
		details.put(username, new Customer(username, "", "", ""));
		return true;
	}

	public Customer getUserInfo(String username) {
		return details.get(username);
	}

	public boolean setUserInfo(String username, Customer info) {
		details.put(username, info);
		return true;
	}

	public float getPrice(Cart cart) {
		float total = 0;
		for(CartItem item : cart.getList()) if(item.product.hasProperty("price")) total += ((float) item.product.getPropertyValue("price")) * item.quantity;
		return total;
	}
	

	public boolean processOrder(String currentUserID, Cart cart) {
		return true;
	}

}
