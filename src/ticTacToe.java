import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ticTacToe {

    static ArrayList<Integer> playerSpots = new ArrayList<Integer>();
    static ArrayList<Integer> computerSpots = new ArrayList<Integer>();
    //using two arraylists to store the used spots

    public static void main(String[] args)
    {

        char[][] board =
                        {{' ', '|', ' ', '|', ' ' },
                        {'-', '+', '-', '+', '-' },
                        {' ', '|', ' ', '|', ' ' },
                        {'-', '+', '-', '+', '-' },
                        {' ', '|', ' ', '|', ' ' }};

        printBoard(board);
        //calling the method that will print the updated board

        while (true)
        {
            System.out.println("Please select a space (1-9).");

            Scanner input = new Scanner(System.in);
            int userSelection = input.nextInt();
            //creating a scanner and asking for the user position

            while (playerSpots.contains(userSelection) || computerSpots.contains(userSelection))
            {
                //this will run if the user position is already in the arraylists
                System.out.println("Position not available. Pick a new spot.");
                userSelection = input.nextInt();
                //this will ask the user for a new position
            }

            chooseSpot(board, userSelection, "user");
            //calling the method to play the user's piece

            String win = verifyWin();
            //saving the result of verifyWin as a string

            if (win.length() > 0)
            {
                //this will only run if the game is over, and will print the board and the outcome
                printBoard(board);
                System.out.println(win);
                break;
            }

            int computerSelection = (int) (Math.random() * 9) + 1;
            //saving a random number from 1-9 for the computer selection

            while (computerSpots.contains(computerSelection) || playerSpots.contains(computerSelection))
            {
                //this will run if the computer position is already in the arraylists
                computerSelection = (int) (Math.random() * 9) + 1;
                //randomly generating a new number
            }

            chooseSpot(board, computerSelection, "computer");
            //calling the method to place the computer's piece

            win = verifyWin();
            //updating the win string
            if (win.length() > 0)
            {
                //this will only run if the game is over, and will print the board and the outcome
                printBoard(board);
                System.out.println(win);
                break;
            }

            printBoard(board);
            System.out.println("");
            //printing the board and a new line after every move
        }
    }

    public static void printBoard(char[][] board)
    {

        for (char[] row : board) {
            //this finds each row in the game board
            for (char c : row) {
                //finding each piece in each row and printing it
                System.out.print(c);
            }
            System.out.println("");

        }
    }

    public static void chooseSpot(char[][] board, int position, String player)
    {
        char piece = ' ';
        //setting an initial piece symbol
        if (player.equals("user"))
        {
            //this will run if the user calls the method, and add the position to the arraylist
            //and change the symbol
            playerSpots.add(position);
            piece = 'X';
        }
        else if (player.equals("computer"))
        {
            //this will run if the computer calls the method, and add the position to the arraylist
            //and change the symbol
            computerSpots.add(position);
            piece = 'O';
        }

        switch (position) {
                //this will add the piece to the board based on the provided position
                case 1:
                    board[0][0] = piece;
                    break;
                case 2:
                    board[0][2] = piece;
                    break;
                case 3:
                    board[0][4] = piece;
                    break;
                case 4:
                    board[2][0] = piece;
                    break;
                case 5:
                    board[2][2] = piece;
                    break;
                case 6:
                    board[2][4] = piece;
                    break;
                case 7:
                    board[4][0] = piece;
                    break;
                case 8:
                    board[4][2] = piece;
                    break;
                case 9:
                    board[4][4] = piece;
                    break;

            }

    }

    public static String verifyWin()
    {
        List topRow = Arrays.asList(1, 2, 3);
        List middleRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftColumn = Arrays.asList(1, 4, 7);
        List middleColumn = Arrays.asList(2, 5, 8);
        List rightColumn = Arrays.asList(3, 6, 9);
        List leftDiagonal = Arrays.asList(1, 5, 9);
        List rightDiagonal = Arrays.asList(3, 5, 7);
        //creating lists that save the winning positions of each combination

        List<List> totalLists = new ArrayList<List>();
        totalLists.add(topRow);
        totalLists.add(middleRow);
        totalLists.add(botRow);
        totalLists.add(leftColumn);
        totalLists.add(middleColumn);
        totalLists.add(rightColumn);
        totalLists.add(leftDiagonal);
        totalLists.add(rightDiagonal);
        //creating an arraylist that stores each winning combination list

        for (List n : totalLists)
        {
            //iterating through the arraylist
            if (playerSpots.containsAll(n))
            {
                //this will run if the player has a winning combination and return the string
                return "You have won!";
            }
            if (computerSpots.containsAll(n))
            {
                //this will run if the computer has a winning combination and return the string
                return "You lost. Try again.";
            }
        }
        if (playerSpots.size() + computerSpots.size() == 9)
        {
            //this will run if nobody won and all the positions are filled
            return "Tie game.";
        }
        return "";
        //will return a blank string if the game is not over
    }

}