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
                    
                    if(a == 0){
                        System.out.println("Player 1:");
                        do{
                            System.out.println();
                            System.out.print("X Coordinate: ");
                            x = console.nextInt();
                            System.out.print("Y Coordinate: ");
                            y = console.nextInt();
                            System.out.println();
                            if(checkSpot(x , y)){
                                move(a , x , y);
                                a++;
                            }
                            else{
                                 System.out.println("Spot not Available");
                            }
                        }while(a == 0);
                        
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
                                move(a , x , y);
                                a--;
                            }
                            else{
                                 System.out.println("Spot not Available");
                                 System.out.println();
                            }
                        }while(a == 1);
                        
                    }
                     print();
                }while(!check(a));
            }
            else if(u == 1){
                System.out.println("Player 1 = X      Computer 2 = O");
                int player = 0;
                int x;
                int y;
                print();
                do{
                    if(a == 0){
                        System.out.println("Player 1:");
                        do{
                            System.out.println();
                            System.out.print("X Coordinate: ");
                            x = console.nextInt();
                            System.out.print("Y Coordinate: ");
                            y = console.nextInt();
                            System.out.println();
                            if(checkSpot(x , y)){
                                move(a , x , y);
                                a++;
                            }
                            else{
                                 System.out.println("Spot not Available");
                            }
                        }while(a == 0);
                        
                    }
                    else{
                        System.out.println("Computer:");
                        do{
                            System.out.println();
                            do{
                                x = (int)(Math.random() * 3);
                                y = (int)(Math.random() * 3);
                            }while(!checkSpot(x , y));
                            move(a , x , y);
                            a--;
                        }while(a == 1);
                        
                    }
                     print();
                }while(!check(a));
            }
        }while(u < 2 && u > -1);
    }
    public void reset(){
        for(int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                board[i][j] = "open";
            }
        }
    }
    public void print(){
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("  0       1       2");
        for(int i = 0; i < board.length; i++){
            System.out.print(i + " ");
            for (int j = 0; j < board[0].length; j++){
                System.out.print(board[i][j] + "    ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();
    }
    public boolean check(int a){
        if(a == 0){
            a++;
        }
        else{
            a--;
        }
        for(int i = 0; i < 3; i++){
            if(board[i][0].equals(board[i][1]) && board[i][0].equals(board[i][2]) && !board[i][0].equals("open")){
                System.out.println("Player " + getMove1(a) + " Wins!!!");
                System.out.println();
                System.out.println();
                return true;
            }
        }
        for(int i = 0; i < 3; i++){
            if(board[0][i].equals(board[1][i]) && board[0][i].equals(board[2][i]) && !board[0][i].equals("open")){
                System.out.println("Player " + getMove1(a) + " Wins!!!");
                System.out.println();
                System.out.println();
                return true;
            }
        }
        if(board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2]) && !board[0][0].equals("open")){
            System.out.println("Player " + getMove1(a) + " Wins!!!");
            System.out.println();
            System.out.println();
            return true;
        }
        if(board[2][0].equals(board[1][1]) && board[2][0].equals(board[0][2]) && !board[0][2].equals("open")){
            System.out.println("Player " + getMove1(a) + " Wins!!!");
            System.out.println();
            System.out.println();
            return true;
        }
        if(cats()){
            System.out.println("Cats Game!");
            System.out.println();
            System.out.println();
            return true;
        }
        return false;
    }
    public void move(int player ,int x , int y){
        if(board[y][x].equals("open")){
            board[y][x] = getMove(player);
        }
    }
    public boolean cats(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(board[i][j].equals("open")){
                    return false;
                }
            }
        }
        return true;
    }
    public String getMove(int x){
        if(x == 0){
            return "X   ";
        }
        else{
            return "O   ";
        }
    }
    public String getMove1(int x){
        if(x == 0){
            return "1";
        }
        else{
            return "2";
        }
    }
    public boolean checkSpot(int x, int y){
        if(x < 0 && x > 2 && y < 0 && y > 2){
            return false;
        }
        if(board[y][x].equals("open")){
            return true;
        }
        return false;
    }
}

