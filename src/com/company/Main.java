package com.company;

import java.util.Scanner;

public class Main {

    //set the with and length of the board
    static int boardwidth = 22;
    static int boardlength = 22;

    //print board method
    public static void printBoard(boolean[][] board) {
        for (int y = 1; y < boardlength - 1; y++) {
            for (int x = 1; x < boardwidth - 1; x++) {
                if (!board[x][y]) {
                    System.out.print("-");
                } else {
                    System.out.print("8");
                }
            }
            System.out.println();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            System.out.println("End of Round");
        }

    //Method to run an individual round of the game
    public static boolean[][] gameRound(boolean[][] oldBoard){
        int counter1 = 0;

        //create new board
        boolean[][] newBoard = new boolean[boardwidth][boardlength];

        //set all newBoard squares to false including outside edge of squares
        for(int y=0; y<boardlength; y++){
            for(int x=0; x<boardwidth; x++){
                newBoard[x][y] = false;
            }
        }

        //work through the old board
        for(int y=1; y<boardlength-1; y++){
            for(int x=1; x<boardwidth-1; x++){
                //reset counter to nil for each cell
                counter1 = 0;

                //count the number of live cells in the surrounding 8 cells.
                if(oldBoard[x - 1][y - 1]){
                    counter1++;
                }
                if(oldBoard[x][y - 1]){
                    counter1++;
                }
                if(oldBoard[x + 1][y - 1]){
                    counter1++;
                }
                if(oldBoard[x + 1][y]){
                    counter1++;
                }
                if(oldBoard[x + 1][y + 1]){
                    counter1++;
                }
                if(oldBoard[x][y + 1]){
                    counter1++;
                }
                if(oldBoard[x - 1][y + 1]){
                    counter1++;
                }
                if(oldBoard[x - 1][y]){
                    counter1++;
                }

                //If cell is dead and counter is equal to 3 make that cell live
                if(!oldBoard[x][y] && counter1 == 3){
                    newBoard[x][y] = true;
                    System.out.println("bing1");
                }

                //If cell is live and counter does not equal 2 or 3 make dead
                if(oldBoard[x][y] && (counter1 == 2 || counter1 == 3)){
                    newBoard[x][y] = true;
                    System.out.println("bing2");
                }
            }
        }
        return newBoard;
    }
    
    //method to create the initial board
    public static boolean[][] populateBoard(String[] stringArray){
        boolean[][] board = new boolean[boardwidth][boardlength];

        //set all board squares to zero including outside edge of squares
        for(int y=0; y<boardlength; y++){
            for(int x=0; x<boardwidth; x++){
                board[x][y] = false;
            }
        }
        //sets the actually values taken from the string array
        for(int y=1; y<boardlength-1; y++){
            for(int x=1; x<boardwidth-1; x++){
                if(stringArray[y-1].charAt(x-1) == '1'){
                    board[x][y] = true;
                }
            }
        }
        return board;    
    }
    
    //method to count the number of live cells at the end of the game.
    public static int checkLiveCells(boolean[][] board){
        int numLiveCells = 0;

        for(int y = 1; y<boardlength-1; y++){
            for(int x=1; x<boardwidth-1; x++){
                if(board[x][y]){
                    numLiveCells++;
                }
            }
        }
        return numLiveCells;
    }
    
    
    //main method
    public static void main(String[] args) {
        //create instance of scanner class
        Scanner sc = new Scanner(System.in);

        //takes in number of rounds that the game will run for.
        int numRounds = sc.nextInt();
        //clear buffer
        sc.nextLine();
        //create string array to store inputs of opening values.
        String [] stringArray = new String[boardlength-2];

        //take in opening values and store in string array.
        for(int i = 0; i<boardlength-2; i++) {
            stringArray[i] = sc.nextLine();
        }
        
        //call method which creates and populates the board
        boolean[][] board = populateBoard(stringArray);

        printBoard(board);
        //call the game round method equal to the number of rounds.
        for(int l = 0; l < numRounds; l++){
            board = gameRound(board);
            printBoard(board);
        }

        //call method to check the number of live cells & print out result.
        int numLiveCells = checkLiveCells(board);
        System.out.println(numLiveCells);
    }
}
