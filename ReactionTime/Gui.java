import javax.swing.*;
import java.awt.*;

public class Gui {

    private JFrame window;
    private Container content;
    private JPanel infoArea, playArea;

    private Field field;
    private JLabel button;
    private JLabel lastResult;
    private JLabel bestResult;
    private JLabel attempt;

    private int lastMsResult;
    private int bestMsResult = 1000000;
    private int attemptInt;

    private final Color OFF = new Color(238, 238, 238);
    private final Color ON = new Color(0, 0, 0);

    Gui(Field field){
        this.field = field;


        window = new JFrame("Reaction time by Favo");
        window.setBounds(433,5, 500, 500);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        content = window.getContentPane();
        playArea = new JPanel();
        infoArea = new JPanel();

        content.setLayout(new BorderLayout());
        playArea.setLayout(new BorderLayout());
        infoArea.setLayout(new GridLayout(1,3));

        content.add(infoArea, BorderLayout.NORTH);
        content.add(playArea, BorderLayout.CENTER);

        button = new JLabel("Wait...", SwingConstants.CENTER);
        lastResult = new JLabel("Last result: --", SwingConstants.CENTER);
        bestResult = new JLabel("Best result: --", SwingConstants.CENTER);
        attempt = new JLabel("Attempt n°: --", SwingConstants.CENTER);

        playArea.add(button, BorderLayout.CENTER);
        infoArea.add(lastResult);
        infoArea.add(bestResult);
        infoArea.add(attempt);
        infoArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        button.setBorder(BorderFactory.createLineBorder(Color.RED, 15));
        button.setOpaque(true);
    }

    public JLabel getButton() {
        return button;
    }

    public void start(){
        button.setText("Wait...");
        button.setBackground(OFF);
        attemptInt++;
        attempt.setText("Attempt n°: " + attemptInt);

        showInfo("Click when you are ready", "Waiting...");

        field.start();
        button.setBackground(ON);
        button.setText("Click now!");
    }

    public void finish(int reactionMs){
        this.lastMsResult = reactionMs;
        lastResult.setText("Last result: " + lastMsResult);
        if(lastMsResult < bestMsResult){
            bestMsResult = lastMsResult;
            bestResult.setText("Best result: " + bestMsResult);
        }
    }

    static void showInfo(String message, String title){
        JOptionPane.showMessageDialog(
                null,
                message,
                title,
                JOptionPane.INFORMATION_MESSAGE );
    }

    static void showError(String message, String title){
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE );
    }
}
