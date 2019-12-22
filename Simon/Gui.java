import javax.swing.*;
import java.awt.*;

public class Gui {
    private JFrame window;
    private Container content;
    private JPanel playArea;
    private JPanel infoArea;

    private Field field;
    private JLabel[] buttons;

    private JLabel level;
    private JLabel score;

    private final Color RED = new Color(255, 0, 0);
    private final Color YELLOW = new Color(255, 255, 0);
    private final Color GREEN = new Color(0, 255, 0);
    private final Color BLUE = new Color(0, 0, 255);

    Gui(Field field){
        this.field = field;
        window = new JFrame("Simon by Favo");
        window.setBounds(610,20, 700, 900);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        content = window.getContentPane();
        content.setLayout(new BorderLayout());

        setupPlayArea();
        setupInfoArea();

        content.add(playArea, BorderLayout.CENTER);
        content.add(infoArea, BorderLayout.NORTH);
    }

    public JLabel[] getButtons(){
        return buttons;
    }

    public JPanel getPlayArea() {
        return playArea;
    }

    private void setupPlayArea(){
        playArea = new JPanel();
        playArea.setLayout(new GridLayout(2, 2));

        buttons = new JLabel[4];
        for (int i = 0; i < 4; i++) {
            buttons[i] = new JLabel();
            buttons[i].setOpaque(true);
            playArea.add(buttons[i]);
        }
        buttons[0].setBackground(RED.darker());
        buttons[1].setBackground(YELLOW.darker());
        buttons[2].setBackground(GREEN.darker());
        buttons[3].setBackground(BLUE.darker());
    }

    private void setupInfoArea(){
        infoArea = new JPanel();
        infoArea.setLayout(new BorderLayout());

        score = new JLabel("Score: " + field.getScore(), SwingConstants.CENTER);
        level = new JLabel("Level " + field.getLevel(), SwingConstants.CENTER);

        infoArea.add(level, BorderLayout.WEST);
        infoArea.add(score, BorderLayout.EAST);
    }

    public void start(){
        score.setText("Score: " + field.getScore());
        level.setText("Level " + field.getLevel());
        field.generateSequence();
        Gui.showInfo("Reproduce the sequence to pass the level!", "Click to start");
        reproduceSequence();
    }

    private void reproduceSequence(){
        for (int i = 0; i < field.getSequence().length; i++) {
            highlight(field.getSequence()[i]);
            buttons[field.getSequence()[i]].setOpaque(true);
            pause(100);
        }
    }

    private void highlight(int button){
        Methods.printerr("PROVO A RIPRODURRE");
        Color temp = buttons[button].getBackground();
        buttons[button].setBackground(temp.brighter());

        int level = field.getLevel();
        int pause;

        pause = 1000 - (level*50);
        if(pause < 200){
            pause = 200 - (level*2);
        }
        if(pause < 50) {
            pause = 50;
        }
        pause(pause);

        buttons[button].setBackground(temp);
    }

    private void pause(int ms){
        try {
            Thread.sleep(ms);
        } catch (Exception ignore){ }
    }

    static void showInfo(String message, String title){
        JOptionPane.showMessageDialog(
                null,
                message,
                title,
                JOptionPane.INFORMATION_MESSAGE );
    }

}
