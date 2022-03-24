//Chon Hin Chou
//Project #1 Single player battleship
//
//This class is class that builds the board and prints out
//the board in the console, it tracks which spot has been hit 
//and display if it his a battleship
//Counts from top to bottom. left to right, so top left corner is (0, 0)
//----------------------------------------------------------
//Needs:
//add method for randomize adding ships
//add method for get number of ships left

import java.util.*;

public class ShipBoard{
   
   private int size;
   private Map<Integer, List<Integer>> board;
   private Map<Integer, List<Integer>> storage;  //Store the location of the ships
   private int guess;
   
   //Pre: 10 >= size >= 4 (throw IllegalArgumentException if not)
   //Post: Constructor for the class, construct a board based on given size, and amount of guesses

   public ShipBoard(int size, int guess){
      if(size < 4 || size > 10){
         throw new IllegalArgumentException();
      }
      this.size = size;
      this.guess = guess;
      board = new TreeMap<>();
      storage = new TreeMap<>();
      for(int i = 0; i < size; i++){
         List<Integer> tempt = new ArrayList<>();
         for(int j = 0; j < size; j++){
            tempt.add(0);
         }
         board.put(i, tempt);
      }
   }
   
   //Post: Prints out the current board
   public void printBoard(){
      System.out.print("  ");
      for(int i = 0; i < size; i++){
         System.out.print(i + " ");
      }
      System.out.println();
      for(int n: board.keySet()){
         List<Integer> tempt = board.get(n);
         System.out.print(n + " ");
         for(int i = 0; i < tempt.size(); i++){
            if(tempt.get(i) < 0){   // if the value is less than 0, that means it hit an ship
               System.out.print("X ");   
            }else if(tempt.get(i) > 0){  // if the value is bigger than 10, means it hit nothing
               System.out.print("  ");
            }else{   // else than the spot is untoubled
               System.out.print("- ");
            }
         }
         System.out.println();
      }
      System.out.println();
   }
   
   //Pre: size >= x > 0, size >= y > 0
   //(throw new IllegalArgumentException if not)
   //Post: add an battle ship to the board
   public void add(int x, int y){
      if(x > size || y > size || x <= 0 || y <=0){
         throw new IllegalArgumentException();
      }
      if(storage.containsKey(x)){
         List<Integer> tempt = storage.get(x);;
         tempt.add(y);
      }else{
         List<Integer> tempt = new ArrayList<>();
         tempt.add(y);
         storage.put(x,tempt);
      }
   }
   
   //Pre: size >= x > 0, size >= y > 0
   //(throw new IllegalArgumentException if not)
   //Post: strike the row and colum, and guess -1 if nothing is hit
   public void hit(int x, int y){
      if(x > size - 1 || y > size - 1 || x < 0 || y <0){
         throw new IllegalArgumentException();
      }
      if(storage.containsKey(x)){  //if there is a ship on the same row
         List<Integer> tempt = board.get(x);
         List<Integer> tempt2 = storage.get(x);
         if(tempt2.contains(y)){  //if there is a ship on the spot
            tempt.set(y, -10);
         }else{
            tempt.set(y, 10);
            guess--;  //guess -1 since they didnt get anything
         }
      }else{
         List<Integer> tempt = board.get(x);  //when there is no ship on the row
         tempt.set(y, 10);
         guess--;   //guess -1 since they didnt get anything
      }
   }
   
   //Pre: amount of ships <= size*size
   //(throw IllegalArgumentException if not)
   //Post: randomize ship placement based on how many ship the user wants
   public void randomize (int amount){
      if(amount > (size * size)){
         throw new IllegalArgumentException();
      }
      Random rand = new Random();
      for(int i = 0; i < amount; i++){
         int x = rand.nextInt(size);
         int y = rand.nextInt(size);
         if(storage.containsKey(x)){
            List<Integer> tempt = storage.get(x);
            while(tempt.size() >= size){
               x = rand.nextInt(size);
               tempt = storage.get(x);
            }
            while(tempt.contains(y)){
               y = rand.nextInt(size);
            }
            tempt.add(y);
         }else{
            List<Integer> tempt = new ArrayList<>();
            tempt.add(y);
            storage.put(x, tempt);
         }
      }
   }
   
   //Post: method returns the row where it contains 1 or more ships
   public int hint(){
      Set<Integer> keySet = storage.keySet();
      Iterator<Integer> i = keySet.iterator();
      return i.next();
   }
   //Post: this method returns true when the user has run out of guesses
   //or they hit all the ships
   public boolean gameOver(){ 
      return guess<=0 || storage.isEmpty();
   }
   
   //Post: returns the number of guess left
   public int getGuess(){
      return guess;
   }
}
