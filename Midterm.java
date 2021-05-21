/*
 * Jonathan Enriquez 
 */
package midterm;

import java.text.NumberFormat;
import java.util.Scanner;

/**
 * This application allows a user to enter data that will subsequently be
 * manipulated by the program. The application will utilize loop structures and
 * if statement variations in addition to variables, constants, mathematical and
 * relational operators, and Java API classes and their methods. A compound
 * assignment operator is used with a counter.
 */
public class Midterm {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        final int THREE = 3;  //initializes constant to 3
        int count = 0;  // Int variable to keep count of number of times loop runs to disply to user at end of application
        String choice = "y"; // Sets choice to y. 
        Scanner sc = new Scanner(System.in);   //Creates scanner to take in user inputs
        System.out.println("Enter your full name:\n"); //asks for the users name
        String name = sc.nextLine();           //sets the string name to display at end of program
        while (!choice.equals("X")) {    // Let's user enter any key to continue or 'x' to exit. 
            count++; //counts the number of times loop executes to display at end of application 
            System.out.print("\nEnter any numeric value:"); //asks the user for a number
            double numValue = sc.nextDouble(); //takes the number given and saves it as a double
            double munValue = numValue;     //creates another double equal to number given. This number will be manipulated

            if (munValue <= 0) {  //checks if the number given is equal or less then zero

                while (munValue <= 0) { //this loop will continue to run until it is false
                    System.out.print(munValue); //prints out the number
                    munValue++; //adds one to the value given

                }

            } else if (munValue > 0) {   //checks if value given is greater then zero

                while (munValue >= 0) { //this loop will run until the number equals zero
                    System.out.print(munValue); // prints out the number
                    munValue--; //subtracts 1 to the number before loop runs again

                }

            }

            double result = (numValue + THREE) / 2; //adds 3 to number given and the divided by two to give 'result'

            //sets the number of decimals that will be displayed to 4
            NumberFormat number = NumberFormat.getNumberInstance(); //get the number format instance
            number.setMinimumFractionDigits(4);             //sets the instance decimal to 4 place
            String resultString = number.format(result);     //formats the result as number format instance

            System.out.println("\n\nRESULT:" + resultString);  //displays formatted result to user.. Added some escape characters to get output as shown in rubric
            if (result > 0) {                                                    //checks if result is greater then zero
                System.out.println("\nThe result is greater than zero.\n");     //if result is greater then zero displays message staing that       
            } else if (result < 0) {                                             //checks if result is less then zero 
                System.out.println("\nThe result is less than zero.\n");        //if result is less then zero this displays message stating that
            } else {
                System.out.println("\nThe result is equal to zero.\n");        //only else left is equal to zero. Displays message stating that

            }

            System.out.println("Enter anything to cotinue or an X to exit the application"); //asks the user press anything to run application again or type uppercase X to exit
            choice = sc.next(); // takes the users input before returning to top of while loop 

        }
        System.out.println("Goodbye," + name);  //takes the name inputted and adds it to string for goodbye message. This is also the end of the application
        System.out.println("You ran this application " + count + " time(s)"); //lets the user know how many times application by adding the count from while loop.
    }

}
