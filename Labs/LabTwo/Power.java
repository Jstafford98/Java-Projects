/**
* Author: Jordan Stafford
* Problem Statement: This program determines if a number is a power of two or not
*/
class Power {
  public static void main(String[] args) {
    for (int i = 0 ; i < 1_100_000 ; i++){
      if (isPowTwo(i)){ System.out.println(i) ;} 
    }
  }//End main
  public static boolean isPowTwo(int x){
    //Instance Variables
    int counter = 2 ;
    double temp = 2 ;
    if (x < 2 || x % 2 != 0)//Return false if X is less than 2 or it is not an even number
      return false ;
    while(temp < x)//Iterate through powers of 2 until we find one that is equal to or greater than x
      temp = Math.pow(2,counter++) ;
    return temp == x ;//Return comparison
  }//End isPowTwo
}//End class
