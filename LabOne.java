public class LabOne{
    public static void main(String[] args){
        for (int i = 1 ; i < 101 ; i++){
            fizzBuzz(i) ;
        }
    }
    private static void fizzBuzz(int n){
        if (n % 3 == 0)
            if (n % 5 == 0)
                System.out.println("fizzbuzz");
            else
                System.out.println("fizz") ;
        else if ( n % 5 == 0 )
            System.out.println("buzz");
        else
            System.out.println(n);
    }
}
