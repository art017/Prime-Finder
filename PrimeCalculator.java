/* Author: Arthur Tran
 * Date: 12/20/2019
 * Filename: PrimeCalculator.java
 */ 
import java.util.*;
import java.awt.*;
import java.lang.*;
import java.io.*;

/* Class Name: PrimeCalculator
 * Purpose: Contains all the methods to process what is needed to operate the 
 * Prime Calculator program.
 * Instance Variables: None
 */ 
public class PrimeCalculator{
  
  /* Method Name: fillPrimes()
   * Description: Reads in the primes text file and copies them into an Arraylist.
   * This is because Arraylists are dynamic in case more are going to be added on
   * later.
   * Variables: None
   * Returns: ArrayList of all primes from 0 - 10000.
   */
  public static ArrayList<Integer> fillPrimes() throws FileNotFoundException{
    ArrayList<Integer> primes = new ArrayList<Integer>();
    int nextPrime = 0;
    
    //Scan through the allPrimes file and add to the arraylist
    try (Scanner primeScanner = new Scanner(new File("allPrimes2.txt"))){
      while(primeScanner.hasNextInt()){
        nextPrime = primeScanner.nextInt();
        primes.add(nextPrime);
      }
      primeScanner.close();
    }
    catch(FileNotFoundException fnfe){
    }
    return primes;
  }
  
  /* Method Name: findNextPrime()
   * Description: Finds the next largest prime number and adds onto the ArrayList.
   * Variables: largestPrime - current largest prime, list - ArrayList of primes
   * Returns: the value of the newfound prime number
   */
  public static int findNextPrime(int largestPrime, ArrayList<Integer> list){
    
    //all prime numbers except 2 are odd so skip over the even numbers
    int nextPrime = largestPrime + 2;
    while(nextPrime != 0){
      int i = 0;
      int primeDivide = list.get(i);
      
      //stop when the divisor gets to half the amount of dividend because that's
      //the same thing as dividing by 2.
      while(primeDivide < nextPrime/2){
        if(nextPrime % primeDivide == 0){
          nextPrime+=2;
          break;
        }
        else{
          primeDivide = list.get(++i);
        }
      }
      
      //add to prime list if divisor exceeds half of the value
      if(primeDivide >= nextPrime/2){
        list.add(nextPrime);
        return nextPrime;
      }
    }
    return 0;
  }
  
  /* Method Name: primeChecker()
   * Description: Takes in a positive input and determines whether or not the
   * input is a prime number. If the input is larger than the highest prime 
   * number, find new prime numbers until it is possible to check.
   * Variables: input - value to check, list - ArrayList of primes
   * Returns: true if input is prime, false otherwise
   */
  public static boolean primeChecker(int input, ArrayList<Integer> list){
    
    //check to see if input is within range
    if(input < 0){
      System.out.println("Not a valid Input");
      return false;
    }
     
    //if input is greater than largest prime, find larger primes
    int largestPrime = getLargestPrime(list);
    while(input > largestPrime){
      largestPrime = findNextPrime(largestPrime, list);
    }
    
    //see if the ArrayList contains the input
    boolean found = list.contains(input);    
    if(found){
      System.out.println(input + " is a prime number!");
    }
    else{
      System.out.println(input + " is not a prime number!");
    }
    return found;
  }
  
  /* Method Name: getLargestPrime()
   * Description: Helper method to get largest prime number.
   * Variables: list - ArrayList of primes
   * Returns: value of the largest prime number in the ArrayList
   */ 
  public static int getLargestPrime(ArrayList<Integer> list){
    return list.get(list.size()-1);
  }
  
  /* Method Name: factorCalculator()
   * Description: Takes in an input and computes the prime factorization of the
   * input. Since every number can be factorized into only primes, it can be done
   * by just dividing the input by primes recursively.
   * Variables: input - value to factorize, list - ArrayList of primes
   * Returns: Void
   */ 
  public static void factorCalculator(int input, ArrayList<Integer> list){  
    //base case check to see if input is in the list of primes 
    if(list.contains(input)){
      System.out.println(input);
      return;
    }
    //loop through the ArrayList of primes & divide by smallest prime at a time
    int divider;
    for(int i = 0; i < list.size(); i++){
      divider = list.get(i);
      
      //if division is possible, print and recursive call on the result
      if(input % divider == 0){
        input = input/divider;
        System.out.print(divider + " x ");
        factorCalculator(input, list);
        return;
      }
    }
    return;
  }
  
  /* Method Name: primeRange()
   * Description: Find the prime numbers within a given input range. If the upper
   * bound exceeds the highest prime number known, find new prime numbers until
   * the range can be met. Negative lower bounds is not permitted
   * Variables: inputMin - lower bound of range, inputMax - upper bound of range
   * list - ArrayList of primes
   * Returns: Void
   */
  public static void primeRange(int inputMin, int inputMax, ArrayList<Integer> list){
    if(inputMin < 0){
      System.out.println("Invalid input of range minimum!");
      return;
    }
    
    int largestPrime = list.get(list.size()-1);
    while(inputMax > largestPrime){
      largestPrime = findNextPrime(largestPrime, list);
    }
    
    int prime;
    for(int i = 0; i < list.size(); i++){
      prime = list.get(i);
      if( prime >= inputMin && prime <= inputMax){
        System.out.print(prime + " ");
      }
      if(prime > inputMax){
        System.out.println();
        return;
      }
    }
    return;
  }
}