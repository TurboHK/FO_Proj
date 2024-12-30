public class FacultyMember extends Employee { //subclass of Employee
	
	public FacultyMember(String name, int salary) throws NegativeSalaryException {
		super(name, salary);
	}
	
	@Override
	public void pay(int amount) {
		setDebt(getDebt() + amount); //without throwing any exception
	}

	public static void testFacultyMember() {
		try {
			FacultyMember f = new FacultyMember("Daniel", -10000);
			System.out.println(false);
		} catch (NegativeSalaryException e) {
			System.out.println(e.getMessage() == "An employee cannot have a negative salary!");
		}
		try {
			FacultyMember f = new FacultyMember("Daniel", 0);
		} catch (NegativeSalaryException e) {
			System.out.println(false);
		}
		try {
			FacultyMember f = new FacultyMember("Daniel", 10000);
			System.out.println(f.getName() == "Daniel");
			System.out.println(f.getDebt() == -10000);
			f.setDebt(20000);
			System.out.println(f.getDebt() == 20000);
			f.setDebt(-10000);
			System.out.println(f.getDebt() == -10000);
			f.pay(2000);
			System.out.println(f.getDebt() == -8000);
			f.pay(-2000);
			System.out.println(f.getDebt() == -10000);
			f.pay(10000);
			System.out.println(f.getDebt() == 0);
			f.pay(10000);
			System.out.println(f.getDebt() == 10000);
		} catch (NegativeSalaryException e) {
			System.out.println(false);
		}
	}
}
