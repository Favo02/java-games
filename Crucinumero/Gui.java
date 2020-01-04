import com.sun.istack.internal.NotNull;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class Gui {

    private JFrame window;
    private Container content;
    private JPanel fieldArea, sequenceArea;

    private Field field;
    private Cell[][] userField;
    private JTextField[][] labelField;
    private int x, y;

    private JLabel[] sequencesLabel;

    private final Color WHITE = Color.WHITE;
    private final Color BLACK = Color.BLACK;
    private Border border = BorderFactory.createLineBorder(Color.BLACK, 2);

    Gui(Field field){
        this.field = field;
        this.userField = field.getUserField();
        this.x = field.getX();
        this.y = field.getY();

        window = new JFrame("Crucinumero by Favo");
        window.setBounds(5,5, 800, 700);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        content = window.getContentPane();
        fieldArea = new JPanel();
        sequenceArea = new JPanel();

        content.setLayout(new BorderLayout());
        fieldArea.setLayout(new GridLayout(x,y));
        sequenceArea.setLayout(new BoxLayout(sequenceArea, BoxLayout.Y_AXIS));

        setupSequenceArea();
        setupFieldArea();


        content.add(fieldArea, BorderLayout.CENTER);
        content.add(sequenceArea, BorderLayout.EAST);

        window.setVisible(true);
    }

    private void setupFieldArea(){
        fieldArea.setBorder(border);
        labelField = new JTextField[x][y];
        for (int iy = 0; iy < y; iy++) {
            for (int ix = 0; ix < x; ix++) {
                labelField[ix][iy] = new JTextField();
                labelField[ix][iy].setBorder(border);
                labelField[ix][iy].setOpaque(true);
                labelField[ix][iy].setHorizontalAlignment(SwingConstants.CENTER);
                if (field.getField()[ix][iy].isBlock()) {
                    labelField[ix][iy].setBackground(BLACK);
                    labelField[ix][iy].setEditable(false);
                }
                else {
                    labelField[ix][iy].setBackground(WHITE);
                    labelField[ix][iy].setForeground(BLACK);
                    labelField[ix][iy].setText("");
                    labelField[ix][iy].getDocument().addDocumentListener(new TextFieldListener(field, userField, labelField, ix, iy, sequencesLabel));
                }
                fieldArea.add(labelField[ix][iy]);
            }
        }
    }

    private void setupSequenceArea(){
        sequencesLabel =  new JLabel[field.getSequences().size()];
        int index = 2;
        for (int i = 0; i < field.getSequencesSorted().size(); i++) {
            if (i==0){
                sequenceArea.add(new JLabel("Cifre: 1"));
            }
            else {
                int lenght1 = (""+field.getSequencesSorted().get(i)).length();
                int lenght2 = (""+field.getSequencesSorted().get(i-1)).length();
                if(lenght1!=lenght2){
                    sequenceArea.add(new JLabel("Cifre: " + index));
                    index++;
                }
            }
            sequencesLabel[i] = new JLabel("" + field.getSequencesSorted().get(i));
            sequenceArea.setAlignmentX(Component.CENTER_ALIGNMENT);
            sequenceArea.add(sequencesLabel[i]);
        }
    }

    /*public void sequenceCheck(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < field.getSequenceSorted()[i].size(); j++) {
                for (int k = 0; k < field.getUserSequences().size(); k++) {
                    if((""+field.getSequenceSorted()[i].get(j)).equalsIgnoreCase(field.getUserSequences().get(k))){
                        Methods.printerr("UHAUSDHAJSdS");
                    }
                }
            }
        }
    }*/

    static void showError(String message, String title){
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE );
    }

    /*
    private void setupSequenceArea(){
        seq1 = new JPanel();
        seq2 = new JPanel();
        seq3 = new JPanel();
        seq4 = new JPanel();
        seq5 = new JPanel();
        seq6 = new JPanel();
        seq7 = new JPanel();
        seq8 = new JPanel();

        seq1.setLayout(new BoxLayout(seq1, BoxLayout.Y_AXIS));
        seq2.setLayout(new BoxLayout(seq2, BoxLayout.Y_AXIS));
        seq3.setLayout(new BoxLayout(seq3, BoxLayout.Y_AXIS));
        seq4.setLayout(new BoxLayout(seq4, BoxLayout.Y_AXIS));
        seq5.setLayout(new BoxLayout(seq5, BoxLayout.Y_AXIS));
        seq6.setLayout(new BoxLayout(seq6, BoxLayout.Y_AXIS));
        seq7.setLayout(new BoxLayout(seq7, BoxLayout.Y_AXIS));
        seq8.setLayout(new BoxLayout(seq8, BoxLayout.Y_AXIS));

        sequenceArea.setBorder(border);
        labelSequences = new JLabel[field.getSequences().size()+8];
        int index = 0;
        ArrayList[] sequenceSorted = field.getSequenceSorted();
        labelSequences[index] = new JLabel("1 Cifra:");
        labelSequences[index].setBackground(Color.BLACK);
        labelSequences[index].setForeground(Color.WHITE);
        seq1.add(labelSequences[index]);
        index++;
        for (int i = 0; i < sequenceSorted[0].size(); i++) {
            labelSequences[index] = new JLabel("" + sequenceSorted[0].get(i));
            seq1.add(labelSequences[index]);
            index++;
        }
        labelSequences[index] = new JLabel("2 Cifre:");
        labelSequences[index].setBackground(Color.BLACK);
        labelSequences[index].setForeground(Color.WHITE);
        seq2.add(labelSequences[index]);
        index++;
        for (int i = 0; i < sequenceSorted[1].size(); i++) {
            labelSequences[index] = new JLabel("" + sequenceSorted[1].get(i));
            seq2.add(labelSequences[index]);
            index++;
        }
        labelSequences[index] = new JLabel("3 Cifre:");
        labelSequences[index].setBackground(Color.BLACK);
        labelSequences[index].setForeground(Color.WHITE);
        seq3.add(labelSequences[index]);
        index++;
        for (int i = 0; i < sequenceSorted[2].size(); i++) {
            labelSequences[index] = new JLabel("" + sequenceSorted[2].get(i));
            seq3.add(labelSequences[index]);
            index++;
        }
        labelSequences[index] = new JLabel("4 Cifre:");
        labelSequences[index].setBackground(Color.BLACK);
        labelSequences[index].setForeground(Color.WHITE);
        seq4.add(labelSequences[index]);
        index++;
        for (int i = 0; i < sequenceSorted[3].size(); i++) {
            labelSequences[index] = new JLabel("" + sequenceSorted[3].get(i));
            seq4.add(labelSequences[index]);
            index++;
        }
        labelSequences[index] = new JLabel("5 Cifre:");
        labelSequences[index].setBackground(Color.BLACK);
        labelSequences[index].setForeground(Color.WHITE);
        seq5.add(labelSequences[index]);
        index++;
        for (int i = 0; i < sequenceSorted[4].size(); i++) {
            labelSequences[index] = new JLabel("" + sequenceSorted[4].get(i));
            seq5.add(labelSequences[index]);
            index++;
        }
        labelSequences[index] = new JLabel("6 Cifre:");
        labelSequences[index].setBackground(Color.BLACK);
        labelSequences[index].setForeground(Color.WHITE);
        seq6.add(labelSequences[index]);
        index++;
        for (int i = 0; i < sequenceSorted[5].size(); i++) {
            labelSequences[index] = new JLabel("" + sequenceSorted[5].get(i));
            seq6.add(labelSequences[index]);
            index++;
        }
        labelSequences[index] = new JLabel("7 Cifre:");
        labelSequences[index].setBackground(Color.BLACK);
        labelSequences[index].setForeground(Color.WHITE);
        seq7.add(labelSequences[index]);
        index++;
        for (int i = 0; i < sequenceSorted[6].size(); i++) {
            labelSequences[index] = new JLabel("" + sequenceSorted[6].get(i));
            seq7.add(labelSequences[index]);
            index++;
        }
        labelSequences[index] = new JLabel("8 Cifre:");
        labelSequences[index].setBackground(Color.BLACK);
        labelSequences[index].setForeground(Color.WHITE);
        seq8.add(labelSequences[index]);
        index++;
        for (int i = 0; i < sequenceSorted[7].size(); i++) {
            labelSequences[index] = new JLabel("" + sequenceSorted[7].get(i));
            seq8.add(labelSequences[index]);
            index++;
        }

        sequenceArea.add(seq1);
        sequenceArea.add(seq2);
        sequenceArea.add(seq3);
        sequenceArea.add(seq4);
        sequenceArea.add(seq5);
        sequenceArea.add(seq6);
        sequenceArea.add(seq7);
        sequenceArea.add(seq8);

        for (int i = 0; i < labelSequences.length; i++) {
            if (labelSequences[i] == null){
                labelSequences[i] = new JLabel();
            }
            labelSequences[i].setOpaque(true);
        }

    }

     */

}
