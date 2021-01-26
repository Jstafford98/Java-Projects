/**
* FizzBuzz.java --- This is a program designed to test the fizzyFunction on primitive integer values between 1 and 100.
* @author Jordan Stafford
*/
public class FizzBuzz {

    //Main function
    public static void main(String[] args) {
      for (int i = 1 ; i < 101 ; i++){
        fizzyFunction(i) ;
      }
    }//End main function
  
    /**
    * This function takes a primitive inteeger value and determines if it is a multiple of 3,5, or both. Based on this, it will either print "fizz","buzz", or
    * "fizzbuzz" respectively. If it is not a multiple of either value, then the function will simply print the number. 
    *
    * @param n: this is the primitive integer value that we will be comparing against to determine our output
    */
    public static void fizzyFunction(int n){
  
      boolean m3 = n % 3 == 0, 
              m5 = n % 5 == 0 ; // m3 and m5 are boolean values that represent whether or not n is a multiple of that number 
              
      if (n >= 0){//Check for valid input
        if (m3 && m5){//Multiple of 3 & 5
          System.out.println("fizzbuzz") ;
        } else if (m3){//Multiple of 3
          System.out.println("fizz") ;
        } else if (m5){//Multiple of 5
          System.out.println("buzz") ;
        } else {//Not a multiple of either
          System.out.println(n) ;
        }
      } else {
        System.out.println("ERROR: Invalid number (" + n + ")") ;
      }
  
    }
  
  }
