import javax.swing.*;

public class Gui {

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

}
