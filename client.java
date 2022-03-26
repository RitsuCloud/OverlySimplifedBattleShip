//Chon Hin Chou
//Project #1 Single player battleship
//
//This is the main client of the program where user can play the game. 
//This is responsible for building ShipBoard and many other user interaction
//like giving corrdinate to hit and run the game
//NEED
import java.util.*;

public class client{
   
   //Post: the main method that is responsible for running the game
   public static void main(String[] args){
      boolean game = true;
      Scanner console = new Scanner(System.in);
      intro();
      int size = console.nextInt();
      System.out.print("Please enter the amount of guesses you want to have! (size you pick * 2) would be optimal : ");
      int guess = console.nextInt();
      System.out.print("Please enter the amount of ships you wish to have on the board! : ");
      int amount = console.nextInt();
      ShipBoard board = new ShipBoard(size,guess);
      board.randomize(amount);
      while(!board.gameOver() && game){
         System.out.println();
         board.printBoard();
         System.out.println("You have " + board.getGuess() + " guesses left!");
         System.out.print("Please enter x and y corrdinate you wish to strike! Enter in the form x y (Type HINT for help): ");
         String answer = console.next();
         if(answer.equalsIgnoreCase("hint")){
            System.out.println("There is a ship on row " + board.hint());
         }else if(answer.equals("BEST")){
            game = false;
         }else{
            int x = Integer.parseInt(answer);
            int y = console.nextInt();
            board.hit(x,y);
         }
      }
      board.printBoard();
      if(game != false){
         if(board.getGuess() == 0){
            System.out.println("Sorry, you have ran out of guesses, maybe give yourself more guesses since this is too hard for you!!");
         }else{
            System.out.println("YOU WON!!!!, CONGRATULATION!!!, NOW TIME TO PICK A BIGGER BOARD WITH LESS GUESSES TO CHALLENGE YOURSELF!!");
         }
      }else{
         System.out.println("YOU WON!!! SINCE YOU ARE THE BEST!!!");
      }
   }
   
   //Post: prints out the introduction and rule of the game 
   public static void intro(){
      System.out.println("Welcome to the overly simplified single player battleship!!!");
      System.out.println("Spot striked that were empty will appear empty, spot striked that has ship will appear as X!!");
      System.out.println("Follow the given instruction and don't give random responses!!!");
      System.out.println("Have fun and enjoy the game!!!");
      System.out.println();
      System.out.print("Please enter a size for the board! (4 - 6) would be optimal, Don't exceed 10! : ");
   }
}
