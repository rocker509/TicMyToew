import java.util.*;
/**
 * Write a description of class TicTac here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TicTac
{
    private String[][] board;
    private Scanner console;
    private int a;
    int size = 0;
    public TicTac(){
        console = new Scanner(System.in);
        int u;
        do{
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
            u = console.nextInt();
            a = 0;
            if(u == 2){
                break;
            }
            System.out.println("What size board would you like? (n X n)");
            System.out.println();
            System.out.print("n = ");
            size = console.nextInt();
            board = new String[size][size];
            reset();
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
                            findMove(player);
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
                board[i][j] = "-";
            }
        }
    }
    public void print(){
        System.out.println();
        System.out.println();
        System.out.println();
        for(int i = 0 ; i < board.length; i++){
            System.out.print("-----");
        }
        System.out.println();
        for(int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                System.out.print(" [" + board[i][j] + "] ");
            }
            System.out.println();
        }
        for(int i = 0 ; i < board.length; i++){
            System.out.print("-----");
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
        for(int i = 0; i < board.length; i++){
            boolean b = true;
            for(int j = 0; j < board.length && b; j++){
                if(!board[i][j].equals(getMove(a))){
                     b = false;
                }
            }
            if(b){
                System.out.println("Player " + getMove1(a) + " Wins!!!!");
                return true;
            }
        }
        for(int i = 0; i < board.length; i++){
            boolean b = true;
            for(int j = 0; j < board.length && b; j++){
                if(!board[j][i].equals(getMove(a))){
                     b = false;
                }
            }
            if(b){
                System.out.println("Player " + getMove1(a) + " Wins!!!!");
                return true;
            }
        }
        boolean b = true;
        for(int i = 0; i < board.length; i++){
            if(!board[i][i].equals(getMove(a))){
                     b = false;
            }
        }
        if(b){
            System.out.println("Player " + getMove1(a) + " Wins!!!!");
            return true;
        }
        b = true;
        for(int i = 0; i < board.length; i++){
            if(!board[i][board.length - 1 - i].equals(getMove(a))){
                     b = false;
            }
        }
        if(b){
            System.out.println("Player " + getMove1(a) + " Wins!!!!");
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
        if(board[y][x].equals("-")){
            board[y][x] = getMove(player);
        }
    }
    public boolean cats(){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                if(board[i][j].equals("-")){
                    return false;
                }
            }
        }
        return true;
    }
    public String getMove(int x){
        if(x == 0){
            return "X";
        }
        else{
            return "O";
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
        if(board[y][x].equals("-")){
            return true;
        }
        return false;
    }
    public void findMove(int player){
        for(int i = 0; i < board.length; i ++){
            int a = 0;
            for(int j = 0; j < board[0].length; j++){
                if(!board[i][j].equals("X")){
                    a++;
                }
            }
            if(a == 1){
                for(int j = 0; j < board[0].length; j++){
                    if(board[i][j].equals("-")){
                        move(i , j , player);
                        return;
                    }
                }
            }
        }
        for(int j = 0; j < board.length; j ++){
            int a = 0;
            for(int i = 0; i < board[0].length; i++){
                if(!board[i][j].equals("X")){
                    a++;
                }
            }
            if(a == 1){
                for(int i = 0; i < board[0].length; i++){
                    if(board[i][j].equals("-")){
                        move(i , j , player);
                        return;
                    }
                }
            }
        }
        int i;
        int a = 0;
        for(i = 0; i < board.length; i++){
            if(!board[i][i].equals("X")){
                a++;
            }
        }
        if (a == 1){
            for(int j = 0; j < board.length; j++){
                if(board[j][j].equals("-")){
                    move(i , i , player);
                    return;
                }
            }
        }
        a = 0;
        for(i = 0; i < board.length; i++){
            if(!board[i][board.length - 1 - i].equals("X")){
                a++;
            }
        }
        if (a == 1){
            for(int j = 0; j < board.length; j++){
                if(board[j][board.length - 1 - j].equals("-")){
                    move(i , i , player);
                    return;
                }
            }
        }
        move((int)(Math.random() * size) , (int)(Math.random() * size) , player);
    }
}

