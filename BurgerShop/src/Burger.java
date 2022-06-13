
public class Burger extends Drink {

	public Burger(String d, float p, int n) {
		super(d, p, n);
		// TODO Auto-generated constructor stub
	}

	@Override
	public float cost() {
		// TODO Auto-generated method stub
		return super.readPrice();
	}

}
