package task3;

public class main {
    public static void main(String[] args) {
  
        bankAccount userAccount = new bankAccount(1000);
        

        atm atm = new atm(userAccount);
        

        atm.displayMenu();
    }
}