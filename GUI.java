
// ============================================================================
//     Taken From: http://programmingnotes.org/
// ============================================================================
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame implements ActionListener
{
    // setting up ALL the variables
    JFrame window = new JFrame("Nate's Tic Tac Toe Game");

    JMenuBar mnuMain = new JMenuBar();
    JMenuItem   mnuNewGame = new JMenuItem("  New Game"), 
    mnuGameTitle = new JMenuItem("|Tic Tac Toe|  "),
    mnuStartingPlayer = new JMenuItem(" Starting Player"),
    mnuExit = new JMenuItem("    Quit");

    JButton btnEmpty[];
    private int size;
    private int AI;

    JPanel  pnlNewGame = new JPanel(),
    pnlNorth = new JPanel(),
    pnlSouth = new JPanel(),
    pnlTop = new JPanel(),
    pnlBottom = new JPanel(),
    pnlPlayingField = new JPanel();
    JPanel radioPanel = new JPanel();

    private JRadioButton SelectX = new JRadioButton("User Plays X", false);
    private  JRadioButton SelectO = new JRadioButton("User Plays O", false);
    private ButtonGroup radioGroup;
    private  String startingPlayer= "";
    final int X = 800, Y = 480, color = 190; // size of the game window
    private boolean inGame = false;
    private boolean win = false;
    private boolean btnEmptyClicked = false;
    private boolean setTableEnabled = false;
    private String message;
    private Font font = new Font("Rufscript", Font.BOLD, 100);
    private int remainingMoves = 1;

    //===============================  GUI  ========================================//
    public GUI() //This is the constructor
    {
        //Setting window properties:
        size = Integer.parseInt(JOptionPane.showInputDialog("n X n?"));
        AI = JOptionPane.showConfirmDialog(null, "Would you like to play VS AI?"
        , "Game Type?" ,JOptionPane.YES_NO_OPTION);

        window.setSize(X, Y);
        window.setLocation(300, 180);
        window.setResizable(true);
        window.setLayout(new BorderLayout());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  

        //------------  Sets up Panels and text fields  ------------------------//
        // setting Panel layouts and properties
        pnlNorth.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlSouth.setLayout(new FlowLayout(FlowLayout.CENTER));

        pnlNorth.setBackground(Color.white);
        pnlSouth.setBackground(new Color(color, color, color));

        pnlTop.setBackground(new Color(color, color, color));
        pnlBottom.setBackground(new Color(color, color, color));

        pnlTop.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlBottom.setLayout(new FlowLayout(FlowLayout.CENTER));

        radioPanel.setBackground(new Color(color, color, color));
        pnlBottom.setBackground(new Color(color, color, color));
        radioPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Who Goes First?"));

        // adding menu items to menu bar
        mnuMain.add(mnuGameTitle);
        mnuGameTitle.setEnabled(false);
        mnuGameTitle.setFont(new Font("Purisa",Font.BOLD,18));
        mnuMain.add(mnuNewGame);
        mnuNewGame.setFont(new Font("Purisa",Font.BOLD,18));
        mnuMain.add(mnuStartingPlayer);
        mnuStartingPlayer.setFont(new Font("Purisa",Font.BOLD,18));
        mnuMain.add(mnuExit);
        mnuExit.setFont(new Font("Purisa",Font.BOLD,18));//---->Menu Bar Complete

        // adding X & O options to menu
        SelectX.setFont(new Font("Purisa",Font.BOLD,18));
        SelectO.setFont(new Font("Purisa",Font.BOLD,18));
        radioGroup = new ButtonGroup(); // create ButtonGroup
        radioGroup.add(SelectX); // add plain to group
        radioGroup.add(SelectO);
        radioPanel.add(SelectX);
        radioPanel.add(SelectO);

        // adding Action Listener to all the Buttons and Menu Items
        mnuNewGame.addActionListener(this);
        mnuExit.addActionListener(this);
        mnuStartingPlayer.addActionListener(this);

        // setting up the playing field
        pnlPlayingField.setLayout(new GridLayout(size, size, 6, 6));
        pnlPlayingField.setBackground(Color.black);
        btnEmpty = new JButton[(size * size) + 1];
        for(int x=1; x <= (size * size); ++x)   
        {
            btnEmpty[x] = new JButton();
            btnEmpty[x].setBackground(new Color(200, 200, 200));
            btnEmpty[x].addActionListener(this);
            pnlPlayingField.add(btnEmpty[x]);
            btnEmpty[x].setEnabled(setTableEnabled);
        }

        // adding everything needed to pnlNorth and pnlSouth
        pnlNorth.add(mnuMain);
        BusinessLogic.ShowGame(pnlSouth,pnlPlayingField);

        // adding to window and Showing window
        window.add(pnlNorth, BorderLayout.NORTH);
        window.add(pnlSouth, BorderLayout.CENTER);
        window.setVisible(true);
    }// End GUI

    // ===========  Start Action Performed  ===============//
    public void actionPerformed(ActionEvent click)  
    {
        // get the mouse click from the user
        Object source = click.getSource();
        // check if a button was clicked on the gameboard
        if(AI == JOptionPane.NO_OPTION){
            for(int currentMove=1; currentMove <= (size * size); ++currentMove) 
            {
                if(source == btnEmpty[currentMove] && remainingMoves < (size * size) + 1)  
                {
                    btnEmptyClicked = true;
                    BusinessLogic.GetMove(currentMove, remainingMoves, font, 
                        btnEmpty, startingPlayer);              
                    btnEmpty[currentMove].setEnabled(false);
                    pnlPlayingField.requestFocus();
                    ++remainingMoves;
                }
            }
        }
        else{
            for(int currentMove=1; currentMove <= (size * size); ++currentMove) 
            {
                if(source == btnEmpty[currentMove] && remainingMoves < (size * size) + 1)  
                {
                    btnEmptyClicked = true;
                    BusinessLogic.GetMove(currentMove, remainingMoves, font, 
                        btnEmpty, startingPlayer);              
                    btnEmpty[currentMove].setEnabled(false);
                    pnlPlayingField.requestFocus();
                    remainingMoves++;

                    if(remainingMoves < (size * size) + 1 && !CheckWin2()){
                        AI(currentMove, remainingMoves, 
                            btnEmpty, startingPlayer);
                        remainingMoves++;
                    }
                }
            }
        }

        // if a button was clicked on the gameboard, check for a winner
        if(btnEmptyClicked) 
        {
            inGame = true;
            if(AI == JOptionPane.YES_OPTION && remainingMoves < (size * size) + 2){
                remainingMoves--;
                CheckWin();
                remainingMoves++;
            }
            CheckWin();
            btnEmptyClicked = false;
        }

        // check if the user clicks on a menu item
        if(source == mnuNewGame)    
        {
            System.out.println(startingPlayer);
            BusinessLogic.ClearPanelSouth(pnlSouth,pnlTop,pnlNewGame,
                pnlPlayingField,pnlBottom,radioPanel);
            if(startingPlayer.equals(""))
            {
                JOptionPane.showMessageDialog(null, "Please Select a Starting Player", 
                    "Oops..", JOptionPane.ERROR_MESSAGE);
                BusinessLogic.ShowGame(pnlSouth,pnlPlayingField);
            }
            else
            {
                if(inGame)  
                {
                    int option = JOptionPane.showConfirmDialog(null, "If you start a new game," +
                            " your current game will be lost..." + "Are you sure you want to continue?"
                        , "New Game?" ,JOptionPane.YES_NO_OPTION);
                    if(option == JOptionPane.YES_OPTION)    
                    {
                        inGame = false;
                        startingPlayer = "";
                        setTableEnabled = false;
                    }
                    else
                    {
                        BusinessLogic.ShowGame(pnlSouth,pnlPlayingField);
                    }
                }
                // redraw the gameboard to its initial state
                if(!inGame) 
                {
                    RedrawGameBoard();
                }
            }       
        }       
        // exit button
        else if(source == mnuExit)  
        {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", 
                    "Quit" ,JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION)
            {
                System.exit(0);
            }
        }
        // select X or O player 
        else if(source == mnuStartingPlayer)  
        {
            if(inGame)  
            {
                JOptionPane.showMessageDialog(null, "Cannot select a new Starting "+
                    "Player at this time.nFinish the current game, or select a New Game "+
                    "to continue", "Game In Session..", JOptionPane.INFORMATION_MESSAGE);
                BusinessLogic.ShowGame(pnlSouth,pnlPlayingField);
            }
            else
            {
                setTableEnabled = true;
                BusinessLogic.ClearPanelSouth(pnlSouth,pnlTop,pnlNewGame,
                    pnlPlayingField,pnlBottom,radioPanel);

                SelectX.addActionListener(new RadioListener());
                SelectO.addActionListener(new RadioListener());
                radioPanel.setLayout(new GridLayout(2,1));

                radioPanel.add(SelectX);
                radioPanel.add(SelectO);
                pnlSouth.setLayout(new GridLayout(2, 1, 2, 1));
                pnlSouth.add(radioPanel);
                pnlSouth.add(pnlBottom);
            }
        }
        pnlSouth.setVisible(false); 
        pnlSouth.setVisible(true);  
    }// End Action Performed

    // ===========  Start RadioListener  ===============//  
    private class RadioListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent event) 
        {
            JRadioButton theButton = (JRadioButton)event.getSource();
            if(theButton.getText().equals("User Plays X")) 
            {
                startingPlayer = "X";
            }
            if(theButton.getText().equals("User Plays O"))
            {
                startingPlayer = "O";
            }

            // redisplay the gameboard to the screen
            pnlSouth.setVisible(false); 
            pnlSouth.setVisible(true);          
            RedrawGameBoard();
        }
    }// End RadioListener
    /*
    ----------------------------------
    Start of all the other methods. |
    ----------------------------------
     */
    private void RedrawGameBoard()  
    {
        BusinessLogic.ClearPanelSouth(pnlSouth,pnlTop,pnlNewGame,
            pnlPlayingField,pnlBottom,radioPanel);
        BusinessLogic.ShowGame(pnlSouth,pnlPlayingField);       

        remainingMoves = 1;

        for(int x=1; x <= (size * size); ++x)   
        {
            btnEmpty[x].setText("");
            btnEmpty[x].setEnabled(setTableEnabled);
        }

        win = false;        
    }

    private void CheckWin() 
    {   
        String player;
        if(startingPlayer.equals("X"))
        {
            if((remainingMoves - 1) % 2 != 0)
            {				
                player = "X";
            }
            else
            {
                player  = "O";
            }
        }
        else
        {
            if((remainingMoves - 1) % 2 != 0)
            {
                player = "O";
            }
            else
            {
                player = "X";
            }
        }
        for(int i = 1; i < btnEmpty.length; i += size){
            boolean a = true;
            for(int j = i; j < i + size; j++){
                if(!btnEmpty[j].getText().equals(player)){
                    a = false;
                    break;
                }
            }
            if(a){
                if(player.equals("X")){
                    JOptionPane.showMessageDialog(null, "Player X Wins!!!!!");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Player O Wins!!!!!");
                }
                startingPlayer = "";
                setTableEnabled = false;
                inGame = false;
                RedrawGameBoard();
            }
        }
        for(int i = 1; i < btnEmpty.length / size + 1; i ++){
            boolean a = true;
            for(int j = i; j < btnEmpty.length; j += size){
                if(!btnEmpty[j].getText().equals(player)){
                    a = false;
                    break;
                }
            }
            if(a){
                if(player.equals("X")){
                    JOptionPane.showMessageDialog(null, "Player X Wins!!!!!");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Player O Wins!!!!!");
                }
                startingPlayer = "";
                setTableEnabled = false;
                inGame = false;
                RedrawGameBoard();
            }
        }
        boolean a = true;
        for(int i = 1; i < btnEmpty.length; i += (size + 1)){
            if(!btnEmpty[i].getText().equals(player)){
                a = false;
                break;
            }
        }
        if(a){
            if(player.equals("X")){
                JOptionPane.showMessageDialog(null, "Player X Wins!!!!!");
            }
            else{
                JOptionPane.showMessageDialog(null, "Player O Wins!!!!!");
            }
            startingPlayer = "";
            setTableEnabled = false;
            inGame = false;
            RedrawGameBoard();
        }
        a = true;
        for(int i = btnEmpty.length - size; i > 1; i -= (size - 1)){
            if(!btnEmpty[i].getText().equals(player)){
                a = false;
                break;
            }
        }
        if(a){
            if(player.equals("X")){
                JOptionPane.showMessageDialog(null, "Player X Wins!!!!!");
            }
            else{
                JOptionPane.showMessageDialog(null, "Player O Wins!!!!!");
            }
            startingPlayer = "";
            setTableEnabled = false;
            inGame = false;
            RedrawGameBoard();
        }
        a = true;
        for(int i = 1; i < btnEmpty.length; i++){
            if(btnEmpty[i].getText().equals("")){
                a = false;
                break;
            }
        }
        if(a){
            JOptionPane.showMessageDialog(null, "Cats Game");
            startingPlayer = "";
            setTableEnabled = false;
            inGame = false;
            RedrawGameBoard();
        }
    }
    private boolean CheckWin2() 
    {   
        String player;
        if(startingPlayer.equals("X"))
        {
            if((remainingMoves - 1) % 2 != 0)
            {				
                player = "X";
            }
            else
            {
                player  = "O";
            }
        }
        else
        {
            if((remainingMoves - 1) % 2 != 0)
            {
                player = "O";
            }
            else
            {
                player = "X";
            }
        }
        for(int i = 1; i < btnEmpty.length; i += size){
            boolean a = true;
            for(int j = i; j < i + size; j++){
                if(!btnEmpty[j].getText().equals(player)){
                    a = false;
                    break;
                }
            }
            if(a){
                return true;
            }
        }
        for(int i = 1; i < btnEmpty.length / size + 1; i ++){
            boolean a = true;
            for(int j = i; j < btnEmpty.length; j += size){
                if(!btnEmpty[j].getText().equals(player)){
                    a = false;
                    break;
                }
            }
            if(a){
                return true;
            }
        }
        boolean a = true;
        for(int i = 1; i < btnEmpty.length; i += (size + 1)){
            if(!btnEmpty[i].getText().equals(player)){
                a = false;
                break;
            }
        }
        if(a){
            return true;
        }
        a = true;
        for(int i = btnEmpty.length - size; i > 1; i -= (size - 1)){
            if(!btnEmpty[i].getText().equals(player)){
                a = false;
                break;
            }
        }
        if(a){
            return true;
        }
        a = true;
        for(int i = 1; i < btnEmpty.length; i++){
            if(btnEmpty[i].getText().equals("")){
                a = false;
                break;
            }
        }
        if(a){
            return true;
        }
        return false;
    }
    public void AI(int currentMove, int remainingMoves, JButton btnEmpty[], 
    String startingPlayer){
        String player;
        player = playerSetter(remainingMoves);
        int p = 0;
        if(size == 3 && btnEmpty[5].getText().equals("")){
            btnEmpty[5].setFont(font);
            btnEmpty[5].setText(player);
            btnEmpty[5].setEnabled(false);
            return;
        }
        for(int i = 1; i < btnEmpty.length; i += size){
            p = 0;
            for(int j = i; j < i + size; j++){
                if(!btnEmpty[j].getText().equals(player)){
                    p++;
                }
            }
            if(p == 1){
                for(int j = i; j < i + size; j++){
                    if(btnEmpty[j].getText().equals("")){
                        btnEmpty[j].setFont(font);
                        btnEmpty[j].setText(player);
                        btnEmpty[j].setEnabled(false);
                        return;
                    }
                }
            }
        }
        for(int i = 1; i < btnEmpty.length / size + 1; i ++){
            p = 0;
            for(int j = i; j < btnEmpty.length; j += size){
                if(!btnEmpty[j].getText().equals(player)){
                    p++;
                }
            }
            if(p == 1){
                for(int j = i; j < btnEmpty.length; j += size){
                    if(btnEmpty[j].getText().equals("")){
                        btnEmpty[j].setFont(font);
                        btnEmpty[j].setText(player);
                        btnEmpty[j].setEnabled(false);
                        return;
                    }
                }
            }
        }
        p = 0;
        for(int i = 1; i < btnEmpty.length; i += (size + 1)){
            if(!btnEmpty[i].getText().equals(player)){
                p++;
            }
        }
        if(p == 1){
            for(int j = 1; j < btnEmpty.length; j += (size + 1)){
                if(btnEmpty[j].getText().equals("")){
                    btnEmpty[j].setFont(font);
                    btnEmpty[j].setText(player);
                    btnEmpty[j].setEnabled(false);
                    return;
                }
            }
        }
        p = 0;
        for(int i = btnEmpty.length - size; i > 1; i -= (size - 1)){
            if(!btnEmpty[i].getText().equals(player)){
                p++;
            }
        }
        if(p == 1){
            for(int j = btnEmpty.length - size; j > 1; j -= (size - 1)){
                if(btnEmpty[j].getText().equals("")){
                    btnEmpty[j].setFont(font);
                    btnEmpty[j].setText(player);
                    btnEmpty[j].setEnabled(false);
                    return;
                }
            }
        }
        String eplayer = playerSetter(remainingMoves + 1);
        for(int i = 1; i < btnEmpty.length; i += size){
            p = 0;
            for(int j = i; j < i + size; j++){
                if(!btnEmpty[j].getText().equals(eplayer)){
                    p++;
                }
            }
            if(p == 1){
                for(int j = i; j < i + size; j++){
                    if(btnEmpty[j].getText().equals("")){
                        btnEmpty[j].setFont(font);
                        btnEmpty[j].setText(player);
                        btnEmpty[j].setEnabled(false);
                        return;
                    }
                }
            }
        }
        for(int i = 1; i < btnEmpty.length / size + 1; i ++){
            p = 0;
            for(int j = i; j < btnEmpty.length; j += size){
                if(!btnEmpty[j].getText().equals(eplayer)){
                    p++;
                }
            }
            if(p == 1){
                for(int j = i; j < btnEmpty.length; j += size){
                    if(btnEmpty[j].getText().equals("")){
                        btnEmpty[j].setFont(font);
                        btnEmpty[j].setText(player);
                        btnEmpty[j].setEnabled(false);
                        return;
                    }
                }
            }
        }
        p = 0;
        for(int i = 1; i < btnEmpty.length; i += (size + 1)){
            if(!btnEmpty[i].getText().equals(eplayer)){
                p++;
            }
        }
        if(p == 1){
            for(int j = 1; j < btnEmpty.length; j += (size + 1)){
                if(btnEmpty[j].getText().equals("")){
                    btnEmpty[j].setFont(font);
                    btnEmpty[j].setText(player);
                    btnEmpty[j].setEnabled(false);
                    return;
                }
            }
        }
        p = 0;
        for(int i = btnEmpty.length - size; i > 1; i -= (size - 1)){
            if(!btnEmpty[i].getText().equals(eplayer)){
                p++;
            }
        }
        if(p == 1){
            for(int j = btnEmpty.length - size; j > 1; j -= (size - 1)){
                if(btnEmpty[j].getText().equals("")){
                    btnEmpty[j].setFont(font);
                    btnEmpty[j].setText(player);
                    btnEmpty[j].setEnabled(false);
                    return;
                }
            }
        }
        do{
            p = (int)(Math.random() * (size * size)) + 1;
        }while(!btnEmpty[p].getText().equals(""));
        btnEmpty[p].setFont(font);
        btnEmpty[p].setText(player);
        btnEmpty[p].setEnabled(false);
    }

    public String playerSetter(int i){
        String player;
        if(startingPlayer.equals("X"))
        {
            if((i) % 2 != 0)
            {				
                player = "X";
            }
            else
            {
                player  = "O";
            }
        }
        else
        {
            if((i) % 2 != 0)
            {
                player = "O";
            }
            else
            {
                player = "X";
            }
        }
        return player;
    }
}	