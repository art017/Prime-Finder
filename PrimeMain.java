/* Name: Arthur Tran
 * Date: 12/27/2019
 * Filename: PrimeMain.java
 */ 
import java.awt.*;
import java.io.*;
import java.util.*;

public class PrimeMain{
  public static void main (String[] args) throws IOException{
    
    //Fill out the primes ArrayList
    PrimeCalculator calc = new PrimeCalculator();
    ArrayList<Integer> primes = calc.fillPrimes();
    
    //Main Menu
    System.out.println("Welcome to Prime Calculator! Choose a function to begin!");
    printMenu();
    
    //Get user input for function selection
    Scanner scanner = new Scanner(System.in);
    while(scanner.hasNext()){
      String s = scanner.next();
      if(!s.equals("1") && !s.equals("2") && !s.equals("3") && !s.equals("q")){
        printMenu();
      }
      
      //User chooses Prime Checker
      while(s.equals("1")){
        System.out.println("Enter a positive number to check whether or not it is prime:");
        
        //Input validation check
        while(!scanner.hasNextInt()){
          System.out.println("Enter a positive number to check whether or not it is prime:");
          scanner.next();
        }
        
        //Run prime check
        int input = scanner.nextInt();
        calc.primeChecker(input, primes);
        System.out.println();
        
        //Continue Options
        System.out.println("Choose an option to continue:");
        System.out.println("1 - Check another number");
        System.out.println("2 - Main Menu");
        
        //Continue validation check
        while(scanner.hasNext()){
          String replay = scanner.next();
          if(!replay.equals("1") && !replay.equals("2")){
            System.out.println("1 - Check another number");
            System.out.println("2 - Main Menu");
          }
          
          //Run Prime Check again
          if(replay.equals("1")){
            break;
          }
          
          //Return to main menu
          if(replay.equals("2")){
             s = "b";
             System.out.println();
             break;
          }
        }
      }
      
      //User chooses primes in a range
      while(s.equals("2")){
        System.out.println("Enter a number for the lower bound:");
        
        //lower bound validation check
        while(!scanner.hasNextInt()){
          System.out.println("Enter a number for the lower bound:");
          scanner.next();
        }
        int lower = scanner.nextInt();
        
        //upper bound validation check
        System.out.println("Enter a number for the upper bound:");
        while(!scanner.hasNextInt()){
          System.out.println("Enter a number for the upper bound:");
          scanner.next();
        }
        int upper = scanner.nextInt();
        
        //Find primes in the range
        calc.primeRange(lower, upper, primes);
        System.out.println();
        
        //Continue Options
        System.out.println("Choose an option to continue:");
        System.out.println("1 - Check another range");
        System.out.println("2 - Main Menu");
        
        //Continue validation check
        while(scanner.hasNext()){
          String replay = scanner.next();
          if(!replay.equals("1") && !replay.equals("2")){
            System.out.println("1 - Check another range");
            System.out.println("2 - Main Menu");
          }
          
          //Find primes in another range
          if(replay.equals("1")){
            break;
          }
          
          //Return to Main Menu
          if(replay.equals("2")){
             s = "b";
             System.out.println();
             break;
          }
        }
      }
      
      //User Chooses Prime Factorization
      while(s.equals("3")){
        System.out.println("Enter a number to factorize:");
        
        //Input Validation Check
        while(!scanner.hasNextInt()){
          System.out.println("Enter a number to factorize:");
          scanner.next();
        }
        int factor = scanner.nextInt();
        
        //Only run factorization if value isn't prime
        if(!calc.primeChecker(factor, primes)){
          calc.factorCalculator(factor, primes);
        }
        System.out.println();
        
        //Continue options
        System.out.println("Choose an option to continue:");
        System.out.println("1 - Factorize another number");
        System.out.println("2 - Main Menu");
        
        //Continue validation check
        while(scanner.hasNext()){
          String replay = scanner.next();
          if(!replay.equals("1") && !replay.equals("2")){
            System.out.println("1 - Factorize another number");
            System.out.println("2 - Main Menu");
          }
          
          //Factorize another number
          if(replay.equals("1")){
            break;
          }
          
          //Return to Main Menu
          if(replay.equals("2")){
             s = "b";
             System.out.println();
             break;
          }
        }
      }
      
      //Continue Helper to return to main menu
      if(s.equals("b")){
        printMenu();
      }
      
      //User chooses to quit the program
      if(s.equals("q")){
        System.exit(0);
      }
    }
    return;
  }
  
  /* Method Name: printMenu()
   * Description: Helper method to print main menu
   * Variables: None:
   * Returns: Void
   */ 
  private static void printMenu(){
    System.out.println("1 - Prime Checker");
    System.out.println("2 - Range of Primes");
    System.out.println("3 - Prime Factorization Calculator");
    System.out.println("q - Quit");
  }
    
}