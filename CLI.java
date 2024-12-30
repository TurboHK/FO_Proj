import java.util.Scanner;
import java.util.InputMismatchException;

public class CLI {
	private static Scanner input = new Scanner(System.in);
	
	private static String readLine(String message) {
        System.out.print(message);
        return input.nextLine(); //Read the single newline character that comes from the user pressing the Enter
    }
	private static int readPosInt(String message) {
		int result; 
		while (1 < 2){
			try{
				System.out.print(message);
                result = input.nextInt();
                input.nextLine(); //Read the single newline character that comes from the user pressing the Enter
				if (result < 0) {
					System.out.println("Positive integers only!");
				}
				else {
					return result;
				}
			}
			catch(InputMismatchException a){
				System.out.println("You must type an integer!");
				input.nextLine(); //Read (and ignore) the wrong input typed by the user of the program
			}
		}
	}
	
	public static void main(String[] args) {
		 FinanceOffice financeOffice = new FinanceOffice("UIC FO");
		 
		 while(1 < 2) {
			 int action = readPosInt("Type an action (total:1 add:2 get:3 give:4 take:5 quit:6): ");
			 switch(action) { //Decide actions
			 
			 case 1: //printing the total amount of debt
				 System.out.println("Total amount of debt: " + financeOffice.totalDebt()); //Total amount
				 break;
				 
			 case 2: //Adding a new payer to the finance office
				 int type = readPosInt("Enter the payer type (student:1 employee:2 faculty member:3): "); //The type of the payer
				 
                 try { //To handle NegativeSalaryException
                	 if (type == 1) { //Student
                		 String name = readLine("Enter the name of the payer: "); //The name of the payer
                		 int amount = readPosInt("Enter the initial amount of money: "); //The amount of money
                    	 financeOffice.addPayer(new Student(name, amount));
                    	 System.out.println("Student \"" + name + "\" with " + amount + " yuans of debt added");
                     }
                     else if (type == 2) { //Employee
                    	 String name = readLine("Enter the name of the payer: "); //The name of the payer
                    	 int amount = readPosInt("Enter the initial amount of money: "); //The amount of money
                    	 financeOffice.addPayer(new Employee(name, amount));
                    	 System.out.println("Employee \"" + name + "\" with " + amount + " yuans of salary added");
                     }
                     else if (type == 3) { //Faculty member
                    	 String name = readLine("Enter the name of the payer: "); //The name of the payer
                    	 int amount = readPosInt("Enter the initial amount of money: "); //The amount of money
                    	 financeOffice.addPayer(new FacultyMember(name, amount));
                    	 System.out.println("Faculty Member \"" + name + "\" with " + amount + " yuans of salary added");
                     }
                     else { //Error
                    	 System.out.println("Unknown type of payer!");
                     } 
                 }
                 catch(NegativeSalaryException a){
                	 System.out.println("BUG! This must never happen!");
                     System.exit(1);
                 }
                 break;
				 
			 case 3: //Listing the amount of debt for a given payer
				 String PayerToList = readLine("Enter the name of the payer: "); //The name of the payer to list the amount of debt
				 
				 try { //To handle UnknownPayerException
					 System.out.println(PayerToList + " has " + financeOffice.getDebt(PayerToList) + " yuans of debt");
				 }
				 catch(UnknownPayerException a){
					 System.out.println(a.getMessage());
				 }
				 break;
				 
			 case 4: //Paying money to a given payer
				 String PayerToPay = readLine("Enter the name of the payer: "); //The name of the payer to pay the debt
				 int AmountToPay = readPosInt("Enter the amount of money: "); //The amount of money to pay
				 
				 try { //To handle UnknownPayerException and NegativeSalaryException
					 financeOffice.pay(PayerToPay, AmountToPay);
				 }
				 catch(UnknownPayerException | NegativeSalaryException a){
					 System.out.println(a.getMessage());
				 }
				 break;
				 
			 case 5: //Taking money from a given payer
				 String PayerToTake = readLine("Enter the name of the payer: "); //The name of the payer to be taken
				 int AmountToTake = readPosInt("Enter the amount of money: "); //The amount of money to take
				 
				 try { //To handle UnknownPayerException and NegativeSalaryException
					 financeOffice.pay(PayerToTake, -AmountToTake);
				 }
				 catch(UnknownPayerException | NegativeSalaryException a) {
					 System.out.println(a.getMessage());
				 }
				 break;
				 
			 case 6: //Quitting the program
				 System.out.println("Goodbye!");
                 System.exit(0);
				 
			 default:
				System.out.println("Unknown action!");
			 }
		 }
	}
}
