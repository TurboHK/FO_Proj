public abstract class Person implements Payer { //The abstract method pay can only be defined by an abstract class
	private String name;
	private int debt;
	
	public Person(String name, int debt) {
		this.name = name;
		this.debt = debt;
	}
	public String getName() {
		return this.name;
	}
	public int getDebt() {
		return this.debt;
	}
	protected void setDebt(int debt) {
		this.debt = debt;
	}
	public abstract void pay(int amount) throws NegativeSalaryException; //abstract method

	public static void testPerson() {
	}
}
