import java.io.Serializable;

public abstract class Drink implements Serializable {
	public String description;
	public int num;
	private float price;
	
	public Drink (String d, float p, int n) {
		this.description = d;
		this.price = p;
		this.num = n;
		
	}
	
	public float readPrice() {
		return price*num;
	}
	public String readDescription() {
		return description+"*"+num;
	}
	
	//Calculate price
	public abstract float cost();
}
