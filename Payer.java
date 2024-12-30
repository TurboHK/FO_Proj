public interface Payer {
	public String getName();
	public int getDebt();
	public void pay(int amount) throws NegativeSalaryException;
}
