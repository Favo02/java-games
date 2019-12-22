import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

public class FieldListener implements MouseListener {
    private Gui gui;
    private Field field;
    private boolean gameOver = false;
    private int[] guessSequence;
    private int guess = 0;

    FieldListener(Gui gui, Field field){
        this.gui = gui;
        this.field = field;
        guessSequence = new int[field.getSequence().length];
    }

    public void mouseClicked(MouseEvent e) {
        if(gameOver){
            return;
        }
        int[] labelClicked = calculateLabel(e.getX(), e.getY());
        int x = labelClicked[0];
        int y = labelClicked[1];
        int pos;
        if(x==0){
            if(y==0){
                pos = 0;
            }
            else {
                pos = 2;
            }
        }
        else {
            if(y==0){
                pos = 1;
            }
            else {
                pos = 3;
            }
        }
        guessSequence(pos);

        Methods.print(Arrays.toString(guessSequence));

        if(guess==guessSequence.length){
            boolean res = field.checkSequence(guessSequence);
            Methods.print(Arrays.toString(guessSequence));
            if(res){
                Gui.showInfo("Sequence correct, next level", "Correct");
                guess = 0;
                gui.start();
                guessSequence = new int[field.getSequence().length];
            }
            else {
                gameOver = true;
                Gui.showInfo("Sequence wrong, game over!\nYour score: " +field.getScore(), "Wrong");
            }
        }
    }

    private int[] calculateLabel(int x, int y){
        int[] res = new int[2];
        int widthField = gui.getButtons()[0].getWidth();
        int heightField = gui.getButtons()[0].getHeight();
        int cont = 0;
        while (x>widthField) {
            cont++;
            x-=widthField;
        }
        res[0] = cont;
        cont = 0;
        while (y>heightField) {
            cont++;
            y-=heightField;
        }
        res[1] = cont;
        return res;
    }

    private void guessSequence(int pos){
        guessSequence[guess] = pos;
        guess++;
    }



    public void mousePressed(MouseEvent e) { }
    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
}