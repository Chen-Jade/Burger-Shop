
public class Decorator extends Drink {
	protected Drink drink;
	public Decorator(String d, float p, int n, Drink dd) {
		super(d, p, n);
		this.drink = dd;
		// TODO Auto-generated constructor stub
	}

	@Override
	public float cost() {
		// TODO Auto-generated method stub
		return super.readPrice() + drink.cost(); 
	}
	
	public String readDescription() {
		return super.description + "*" + super.num + "\n" + drink.readDescription();
	}
	
}
