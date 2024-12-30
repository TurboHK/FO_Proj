import java.util.ArrayList;
public class FinanceOffice {
    
    private String name;
    private ArrayList<Payer> payers;

    public FinanceOffice(String name) {
        this.name = name;
        this.payers = new ArrayList<>(); //Create an ArrayList to store strings
    }

    public void addPayer(Payer payer) {
        payers.add(payer); //Adds the payer to the list of payers
    }

    public int totalDebt() {
        int total = 0;
        for (Payer payer : payers) {
            total += payer.getDebt();
        }
        return total; //Returns the total debt of all payers
    }

    public int getDebt(String name) throws UnknownPayerException {
        for (Payer payer : payers) {
            if (payer.getName().equals(name)) {
                return payer.getDebt();
            }
        }
        throw new UnknownPayerException("Payer " + name + " unknown"); // Throws exception if the payer is not found
    }

    public void pay(String name, int amount) throws NegativeSalaryException, UnknownPayerException {
        for (Payer payer : payers) {
            if (payer.getName().equals(name)) {
                payer.pay(amount);  // Calls the appropriate pay method based on the type of Payer
                return;
            }
        }
        throw new UnknownPayerException("Payer " + name + " unknown");
    }

	public static void testFinanceOffice() {
		FinanceOffice f = new FinanceOffice("UIC FO");

		System.out.println(f.totalDebt() == 0);
		//f.addPayer(new Student("Ms. Li", 1000));
		try {
			f.addPayer(new Student("Ms. Li", 1000));
			System.out.println(f.getDebt("Ms. Li") == 1000);
			System.out.println(f.totalDebt() == 1000);
			f.addPayer(new Employee("Daniel", 10000));
			System.out.println(f.getDebt("Ms. Li") == 1000);
			System.out.println(f.getDebt("Daniel") == -10000);
			System.out.println(f.totalDebt() == -9000);
			f.getDebt("Mr. Li");
			System.out.println(false);
		} catch (UnknownPayerException ex) {
			System.out.println(ex.getMessage().equals("Payer Mr. Li unknown"));
		} catch (NegativeSalaryException e) {
			System.out.println(false);
		}
		try {
			f.pay("Daniel", 5000);
			System.out.println(f.getDebt("Ms. Li") == 1000);
			System.out.println(f.getDebt("Daniel") == -5000);
			f.pay("Mr. Li", 1);
			System.out.println(false);
		} catch (UnknownPayerException ex) {
			System.out.println(ex.getMessage().equals("Payer Mr. Li unknown"));
		} catch (NegativeSalaryException e) {
			System.out.println(false);
		}
		try {
			f.pay("Daniel", 10000);
		} catch (UnknownPayerException ex) {
			System.out.println(false);
		} catch (NegativeSalaryException e) {
			System.out.println(e.getMessage().equals("An employee cannot be overpaid by 5000 yuans!"));
		}
		try {
			System.out.println(f.getDebt("Ms. Li") == 1000);
			System.out.println(f.getDebt("Daniel") == -5000);
		} catch (UnknownPayerException ex) {
			System.out.println(false);
		}
	}
}
