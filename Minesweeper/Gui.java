import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Gui {

    private JFrame window;
    private Container content;
    private JPanel infoArea, playArea, buttonArea;

    private JLabel remainingBombCount;
    private JLabel timer;
    private JLabel gameStatus;

    private Field field;
    private JLabel[][] labelField;

    private JButton restart;
    private JButton surrender;

    private int maxX, maxY;
    private boolean showCellNumber;
    
    private final Color EMPTY_CELL = new Color(255, 255, 255);
    private final Color NUM_CELL = new Color(180, 180, 180);
    private final Color FLAG_CELL = new Color(255, 101,0);
    private final Color CLOSE_CELL = new Color(75, 75, 75);
    private final Color BOMB_CELL = new Color(255, 18,0);
    private final Color WHITE_TEXT = new Color(255, 255, 255);
    private final Color BLACK_TEXT = new Color(0, 0,0);
    private final Color RED_TEXT = new Color(255, 18,0);

    public Container getPlayArea() {
        return playArea;
    }
    public JButton getRestart(){
        return restart;
    }
    public JButton getSurrender(){
        return surrender;
    }

    Gui(int x, int y, boolean showCellNumber, Field field){
        this.field = field;
        this.maxX = x-1;
        this.maxY = y-1;
        this.showCellNumber = showCellNumber;

        labelField = new JLabel[x][y];
        playArea = new JPanel();
        infoArea = new JPanel();
        buttonArea = new JPanel();

        window = new JFrame("Minesweeper by Favo");
        window.setBounds(5,5, 900, 900);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        content = window.getContentPane();
        content.setLayout(new BorderLayout());
        playArea.setLayout(new GridLayout(x,y));
        infoArea.setLayout(new BorderLayout());
        buttonArea.setLayout(new BorderLayout());

        setupPlayArea();
        setupInfoArea();
        setupButtonArea();

        content.add(infoArea, BorderLayout.NORTH);
        content.add(playArea, BorderLayout.CENTER);
        content.add(buttonArea, BorderLayout.SOUTH);

        window.setVisible(true);
    }

    private void setupPlayArea(){
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        for (int y = 0; y <= maxY ; y++) {
            for (int x = 0; x <= maxX; x++) {
                if (showCellNumber){
                    labelField[x][y] = new JLabel( x + " - " + y, SwingConstants.CENTER);
                }
                else {
                    labelField[x][y] = new JLabel( "", SwingConstants.CENTER);
                }
                labelField[x][y].setBorder(border);
                labelField[x][y].setBackground(Color.DARK_GRAY);
                labelField[x][y].setForeground(WHITE_TEXT);
                labelField[x][y].setOpaque(true);
                playArea.add(labelField[x][y]);
            }
        }
        
    }
    private void setupInfoArea(){
        remainingBombCount = new JLabel("\uD83D\uDCA3: " + (field.getBombs() - field.getFlagged()));

        gameStatus = new JLabel("Click a cell to start the game!", SwingConstants.CENTER);

        timer = new JLabel("Timer");

        infoArea.add(remainingBombCount, BorderLayout.WEST);
        infoArea.add(gameStatus, BorderLayout.CENTER);
        infoArea.add(timer, BorderLayout.EAST);
    }
    private void setupButtonArea(){
        restart = new JButton("Restart");
        surrender = new JButton("Surrender");

        buttonArea.add(restart, BorderLayout.EAST);
        buttonArea.add(surrender, BorderLayout.WEST);
    }

    public void refresh(Field field, boolean gameOver){
        remainingBombCount.setText("\uD83D\uDCA3: " + (field.getBombs() - field.getFlagged()));
        if(gameOver){
            if(field.check()){
                gameStatus.setText("Game over: you win!");
            }
            else {
                gameStatus.setText("Game over: you lose!");
            }
            for (int y = 0; y <= maxY; y++) {
                for (int x = 0; x <= maxX; x++) {
                    if (field.getField()[x][y].isFlagCorrect()) {
                        labelField[x][y].setBackground(FLAG_CELL);
                        labelField[x][y].setForeground(WHITE_TEXT);
                        labelField[x][y].setText("⚑");
                    }
                    else if (!(field.getField()[x][y].isFlagCorrect()) && field.getField()[x][y].isFlagged()) {
                        labelField[x][y].setBackground(CLOSE_CELL);
                        labelField[x][y].setForeground(RED_TEXT);
                        labelField[x][y].setText("⚑");
                    }
                    else if (field.getField()[x][y].isBomb()) {
                        labelField[x][y].setBackground(BOMB_CELL);
                        labelField[x][y].setForeground(BLACK_TEXT);
                        labelField[x][y].setText("\uD83D\uDCA3");
                    }
                    else if (field.getField()[x][y].isVisible()) {
                        if (field.getField()[x][y].isNum()) {
                            labelField[x][y].setBackground(NUM_CELL);
                            labelField[x][y].setForeground(BLACK_TEXT);
                            labelField[x][y].setText(field.getField()[x][y].getNum() + "");
                        }
                        else if (field.getField()[x][y].isEmpty()) {
                            labelField[x][y].setBackground(EMPTY_CELL);
                            labelField[x][y].setForeground(WHITE_TEXT);
                            labelField[x][y].setText("");
                        }
                    }
                    else if(!(field.getField()[x][y].isBomb())){
                        labelField[x][y].setText("");
                        labelField[x][y].setForeground(BLACK_TEXT);
                        labelField[x][y].setBackground(CLOSE_CELL);
                    }
                }
            }
        }
        else {
            gameStatus.setText("Game in progress...");
            for (int y = 0; y <= maxY; y++) {
                for (int x = 0; x <= maxX; x++) {
                    if (field.getField()[x][y].isFlagged()) {
                        labelField[x][y].setBackground(FLAG_CELL);
                        labelField[x][y].setForeground(WHITE_TEXT);
                        labelField[x][y].setText("⚑");
                    }
                    else if (field.getField()[x][y].isVisible()) {
                        if (field.getField()[x][y].isNum()) {
                            labelField[x][y].setBackground(NUM_CELL);
                            labelField[x][y].setForeground(BLACK_TEXT);
                            labelField[x][y].setText(field.getField()[x][y].getNum() + "");
                        }
                        else if (field.getField()[x][y].isEmpty()) {
                            labelField[x][y].setBackground(EMPTY_CELL);
                            labelField[x][y].setForeground(BLACK_TEXT);
                            labelField[x][y].setText("");
                        }
                    }
                    else {
                        if (showCellNumber){
                            labelField[x][y].setText(x + " - " + y);
                        }
                        else {
                            labelField[x][y].setText("");
                        }
                        labelField[x][y].setBackground(CLOSE_CELL);
                        labelField[x][y].setForeground(BLACK_TEXT);
                    }
                }
            }
        }
    }


    static void showError(String message, String title){
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE );
    }
    static int showInputInt(String question){
        int n;
        n = Integer.parseInt(JOptionPane.showInputDialog(null,
                question,
                "Input", JOptionPane.QUESTION_MESSAGE));
        return n;
    }
    static int showInputSelection(String question, String answer1, String answer2){
        String[] options = {answer1, answer2};
        int n = JOptionPane.showOptionDialog(null,
                question,
                "Selection",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        if(n==0){
            return 1;
        }
        if(n==1){
            return 2;
        }
        return -1;
    }

    public JLabel[][] getLabelField() { return labelField; }
}