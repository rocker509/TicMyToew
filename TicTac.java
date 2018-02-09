import java.util.*;
/**
 * Write a description of class TicTac here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TicTac
{
    private String[][] board = new String[3][3];
    private Scanner console;
    private int a;
    public TicTac(){
        console = new Scanner(System.in);
        int u;
        do{
            reset();
            System.out.println("What do you want to play?");
            System.out.println();
            System.out.println("(0) 2 player");
            System.out.println();
            System.out.println("(1) VS AI");
            System.out.println();
            System.out.println("(2) quit");
            System.out.println();
            System.out.print("Choice ----> ");
            System.out.println();
            System.out.println();
            System.out.println();
            u = console.nextInt();
            if(u == 0){
                System.out.println("Player 1 = X      Player 2 = O");
                int player = 0;
                int x;
                int y;
                print();
                do{
                   
                    if(player == 0){
                        System.out.println("Player 1:");
                        do{
                            System.out.println();
                            System.out.print("X Coordinate: ");
                            x = console.nextInt();
                            System.out.print("Y Coordinate: ");
                            y = console.nextInt();
                            System.out.println();
                            if(checkSpot(x , y)){
                                move(player , x , y);
                                a = 1;
                            }
                            else{
                                 System.out.println("Spot taken");
                            }
                        }while(a == 0);
                        player++;
                        a = 0;
                    }
                    else{
                        System.out.println("Player 2:");
                        do{
                            System.out.println();
                            System.out.print("X Coordinate: ");
                            x = console.nextInt();
                            System.out.print("Y Coordinate: ");
                            y = console.nextInt();
                            System.out.println();
                            if(checkSpot(x , y)){
                                move(player , x , y);
                                a = 1;
                            }
                            else{
                                 System.out.println("Spot taken");
                            }
                        }while(a == 0);
                        player++;
                        a = 0;
                    }
                     print();
                }while(!check());
            }
            else if(u == 1){
                
            }
        }while(u < 2 && u > -1);
    }
    public void reset(){
        for(int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                board[i][j] = "nope";
            }
        }
    }
    public void print(){
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("0      1      2");
        for(int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                System.out.print(board[i][j] + "    ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();
    }
    public boolean check(){
        for(int i = 0; i < 3; i++){
            if(board[i][0].equals(board[i][1]) && board[i][0].equals(board[i][2]) && !board[0][i].equals("nope")){
                System.out.println("1");
                return true;
            }
        }
        for(int i = 0; i < 3; i++){
            if(board[0][i].equals(board[1][i]) && board[0][i].equals(board[2][i]) && !board[0][i].equals("nope")){
                System.out.println("2");
                return true;
            }
        }
        if(board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2]) && !board[0][0].equals("nope")){
            System.out.println("3");
            return true;
        }
        if(board[2][0].equals(board[1][1]) && board[2][0].equals(board[2][0]) && !board[0][2].equals("nope")){
            System.out.println("4");
            return true;
        }
        return false;
    }
    public void move(int player ,int x , int y){
        if(board[y][x].equals("nope")){
            board[y][x] = getMove(player);
        }
    }
    public String getMove(int x){
        if(x == 0){
            return "X   ";
        }
        else{
            return "O   ";
        }
    }
    public boolean checkSpot(int x, int y){
        if(board[y][x].equals("nope")){
            return true;
        }
        return false;
    }
}
