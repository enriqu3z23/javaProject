package projectquiz2;

import java.util.*;
import java.text.NumberFormat;

/**
 * This application is simple gambling game that lets the user pick 3 numbers
 * and if the on of the users numbers matches one of the three random numbers
 * generated the user wins the amount wager. But if the user does not guess
 * right the user loses the wager amount
 *
 * @author Jonathan Enriquez 11/13/2019
 */
public class ProjectQuiz2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {                                                            //main method start
        boolean keepPlaying = true;                                                                     //boolean for loop to keep playing game
        Scanner sc = new Scanner(System.in);                                                            //scanner to take input
        double tableMoney = startMoney(sc, "How much are you willing to put on the table? ");           //calls method that will display welcome and get users table money

        while (keepPlaying == true) {                                                                   //while loop to keep game going

            System.out.println("\nYou must play three numbers at a time.");
            System.out.println("The valid numbers are between 0 and 20 inclusive");

            int guess1 = guess(sc, "Enter your number? ");                                               // calls user input to play the game method to ger user betting number 
            int guess2 = guess(sc, "Enter your number? ");
            int guess3 = guess(sc, "Enter your number? ");

            double bet = getWager(sc, "How much do you wager? ", tableMoney);                           //calls wage validation and input method to ger user wagers

            System.out.println("\nVery good. The wheel is spinning.");

            final int LIMIT = 20;                                                                       //limit to set range of random number
            int rando1 = getRandomNumber(LIMIT);                                                        //calls random number generator method to set random numbers
            int rando2 = getRandomNumber(LIMIT);
            int rando3 = getRandomNumber(LIMIT);

            tableMoney = winningNums(rando1, rando2, rando3, guess1, guess2, guess3, tableMoney, bet);  //calls display winning numbers and user's table information method  

            keepPlaying = endGame(keepPlaying);                                                         //calls user continue input and ending information method.

        }
        NumberFormat currency = NumberFormat.getCurrencyInstance();                                     //number format object created to print out table money in currency format
        System.out.println("\nTake your " + currency.format(tableMoney) + " off the table when you leave. Come back soon");
    }

    public static int getRandomNumber(int limit) {          //Static method for generating random numbers. 
        double d = Math.random() * limit;                   // sets d to random number between 0 and 20
        int randomInt = (int) d;                            // changes d to integar
        return randomInt;                                   //returns the random integer when called

    }

    public static double startMoney(Scanner sc, String prompt) {                //Static Method for displaying starting information and taking user input for start money
        double d = 0;
        boolean isValid = false;

        System.out.println("Welcome to Casino Royal");
        System.out.println("++++++++++++++++++++++++");

        while (!isValid) {                                                      // While loop used for validating user input
            System.out.print(prompt);

            if (sc.hasNextDouble()) {
                d = sc.nextDouble();

                if (d < 0) {                                                                            //if statement that discards numbers less then zero
                    System.out.println("You must enter a positive number to play. Try again.");
                    sc.nextLine(); // discards invalid number                                       
                    continue;
                }
                isValid = true;
            } else {
                System.out.println("Invalid input. You must enter a number to play. Try again.");       //if statement that discards characters
                sc.nextLine();
                continue;
            }
            sc.nextLine();         //discard any other data entered on the line

        }
        return d;               //returns d when called

    }

    public static int guess(Scanner sc, String prompt) {                        //user input static method to play the game

        boolean isValid = false;
        int guess = 0;

        System.out.print(prompt);
        while (!isValid) {                                                      //loop for data validation                        

            if (sc.hasNextInt()) {
                guess = sc.nextInt();
                isValid = true;

            } else {
                System.out.print("Invalid input. You must enter a number to play. Try again.\nEnter your number? ");       //if user doesnt type a number prompts the user to try again 
                isValid = false;

            }
            sc.nextLine();
            if (isValid && guess <= -1) {
                System.out.print("Invalid input. You must enter a number between 0 and 20 to play. Try again.\nEnter your number? ");   //if number is not within 0 and 20 gives prompt for user to try again
                isValid = false;
            } else if (isValid && guess >= 21) {
                System.out.print("Invalid input. You must enter a number between 0 and 20 to play. Try again.\nEnter your number? ");
                isValid = false;
            }

        }
        return guess;           //returns guess when called

    }

    public static double getWager(Scanner sc, String prompt, double wallet) {       // Static method for user wager validation and input        
        NumberFormat currency = NumberFormat.getCurrencyInstance();                 //Numberformat object to turn wager into currency format
        boolean isValid = false;                                                    //boolean value used for loop data validation
        double wager = 0;                                                           //initializes wager variable for user input

        System.out.println("\nYou can now place a single bet of any size on these numbers of any size on these number \nas long as you have that much money on the table.");
        while (!isValid) {
            System.out.print("How much do you wager? ");

            if (sc.hasNextDouble()) {                                               //takes in user wager if the input is of double value type
                wager = sc.nextDouble();                                            //gives wager the double that is entered next
                isValid = true;                                                     //data validation turns to true to exit while loop

            } else {
                System.out.println("Invalid input. You must enter a number to play. Try again.");
            }
            sc.nextLine();                                                          //clears line if input is not a number

            if (isValid && wager <= 0) {
                System.out.println("You can not wager less then 0 dollars. Try a higher waher."); //data validation to make sure wager is not a negative number
                isValid = false;
            } else if (wallet < wager) {                                                            //data validation to make sure wager is not more then user table money amount
                System.out.println("You did not have " + currency.format(wager) + " on the table to bet. Try a lower wager.");  //print wager in number format
                isValid = false;
            }

        }
        return wager;

    }

    //Display winning numbers and user's table information- This method is to determine the winning numbers, determine if the user wins or loses, and displays the appropiate information to the user
    public static double winningNums(int randomNum1, int randomNum2, int randomNum3, int guess1, int guess2, int guess3, double wallet, double wager) {     //sets all the variable that the method can take in

        System.out.println("\nThe winning numbers are: " + randomNum1 + ", " + randomNum2 + ", " + randomNum3 + ", ");          //displays the winning numbers

        if (randomNum1 == guess1 || randomNum1 == guess2 || randomNum1 == guess3) {                         //checks to see if the user has a winnning number and display winning message
            System.out.println("\nCongratulations you have a winning number");
            wallet = wallet + wager;                                                                    //adds wager to table money
        } else if (randomNum2 == guess1 || randomNum2 == guess2 || randomNum2 == guess3) {
            System.out.println("\nCongratulations you have a winning number");
            wallet = wallet + wager;                                                                    //adds wager to table money
        } else if (randomNum3 == guess1 || randomNum3 == guess2 || randomNum3 == guess3) {
            System.out.println("\nCongratulations you have a winning number");
            wallet = wallet + wager;                                                                    //adds wager to table money
        } else {                                                                                        //if any guess numbers are not equal to random number then loss is displayed
            System.out.print("\nSorry you lose. ");
            wallet = wallet - wager;                                                                    //subtracts wager from table money
        }

        NumberFormat currency = NumberFormat.getCurrencyInstance();                                //creates object for currency formatter
        System.out.println("You now have " + currency.format(wallet) + " on the table.");          //displays money with currecy format method

        return wallet;

    }

    public static boolean endGame(boolean keepPlaying) {        //Display iser continue input and ending information- This method is to prompt, accept and validate the user's continue input and prints appropiate information in response
        boolean isValid = false;                                //initializes boolean used for data validation loop

        Scanner sc = new Scanner(System.in);

        while (!isValid) {
            System.out.print("Do you want to continue(yes/no)?");

            String choice = sc.next();
            if (null == choice) {
                System.out.println("You must enter yes or no. Try again.");         //if no input asks user for input again

            } else {
                switch (choice) {                                                   //switch statement to accept only yes or no answer to after continue statement
                    case "yes":
                        isValid = true;
                        keepPlaying = true;
                        break;
                    case "no":
                        isValid = true;
                        keepPlaying = false;
                        break;
                    default:
                        System.out.println("You must enter yes or no. Try again.");
                        break;
                }
            }

        }
        return keepPlaying;             //returns boolean value when called for while main while loop to keep application going

    }
}
