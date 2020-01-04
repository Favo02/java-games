import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.ArrayList;

public class TextFieldListener implements DocumentListener {

    private Field field;
    private Cell[][] userField;
    private JTextField[][] gui;
    private int x,y;
    private JLabel[] sequencesLabel;

    TextFieldListener(Field field, Cell[][] userField, JTextField[][] gui, int x, int y, JLabel[] sequencesLabel){
        this.field = field;
        this.userField = userField;
        this.gui = gui;
        this.x = x;
        this.y = y;
        this.sequencesLabel = sequencesLabel;
    }

    public void insertUpdate(DocumentEvent e) {
        play(e);
        sequenceCheck();
    }
    public void removeUpdate(DocumentEvent e) {
        play(e);
        sequenceCheck();
    }
    public void changedUpdate(DocumentEvent e) {
        play(e);
        sequenceCheck();
    }

    private void sequenceCheck(){
        ArrayList<String> sequencesSorted = field.getSequencesSorted();
        ArrayList<String> sequencesUser = field.getUserSequences();

        for (int ss = 0; ss < sequencesSorted.size(); ss++) {
            for (int su = 0; su < sequencesUser.size(); su++) {
                if(sequencesSorted.get(ss).equalsIgnoreCase(sequencesUser.get(su))){
                    sequencesLabel[ss].setOpaque(true);
                    sequencesLabel[ss].setBackground(Color.BLUE);
                }
            }
        }
    }

    private void play(DocumentEvent e){
        Methods.println("CONTROLLOOOOOOOOOO");

        userField[x][y].setNum((gui[x][y].getText()));

        if(field.winCheck()){
            Gui.showError("You win!", "Game over");
            for (int i = 0; i < gui[0].length; i++) {
                for (int j = 0; j < gui.length; j++) {
                    gui[i][j].setEditable(false);
                }
            }
        }
    }


}
