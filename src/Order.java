import java.util.HashMap;

public class Order {
	public String OrderNumber;
	public String CustomerName;
	//public ArrayList<CartItem> items;
	HashMap<String, Float> Items = new HashMap<>();
	public Float TotalPrice;
	public String OrderDate;
	public String CardNumber;

//	public Order(int orderNumber2, String customerName2, List<CartItem> list, String format, String cardNumber2) {
//		// TODO Auto-generated constructor stub
//		this.OrderNumber = orderNumber2;
//		this.CustomerName = customerName2;
//		this.items = list;
//		//this.TotalPrice = price;
//		this.OrderDate = format;
//		this.CardNumber = cardNumber2;
//	}

	public void setOrderNumber(String number) {
		// TODO Auto-generated method stub
		this.OrderNumber = number; 
	}
	
	public void setCustomerName(String cusname) {
		// TODO Auto-generated method stub
		this.CustomerName = cusname; 
	}
	
	public void setItems(String A, Float B) {
		// TODO Auto-generated method stub
		this.Items.put(A, B); 
	}
	
	public void setOrderDate(String format) {
		// TODO Auto-generated method stub
		this.OrderDate = format; 
	}
	
	public void setCardNumber(String cardNumber2) {
		// TODO Auto-generated method stub
		this.CardNumber = cardNumber2; 
	}
	
	public void setTotalPrice(Float Price){
		this.TotalPrice = Price;
	}
	
	public String getOrderNumber() {
		// TODO Auto-generated method stub
		return this.OrderNumber;
	}
	
	public String getCustomerName() {
		// TODO Auto-generated method stub
		return this.CustomerName; 
	}
	
	public HashMap<String, Float> getItems() {
		// TODO Auto-generated method stub
		return this.Items; 
	}
	
	public String getOrderDate() {
		// TODO Auto-generated method stub
		return this.OrderDate; 
	}
	
	public String getCardNumber() {
		// TODO Auto-generated method stub
		return this.CardNumber; 
	}
	
	public Float getTotalPrice(){
		return this.TotalPrice;
	}

}