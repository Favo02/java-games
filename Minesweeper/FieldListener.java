import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FieldListener implements MouseListener {
    private Gui fieldGui;
    private Field field;
    private boolean gameOver = false;

    FieldListener(Gui fieldGui, Field field){
        this.fieldGui = fieldGui;
        this.field = field;
    }

    public void mouseClicked(MouseEvent e) {
        if(gameOver){
            return;
        }

        int[] labelClicked = calculateLabel(e.getX(), e.getY());
        int x = labelClicked[0];
        int y = labelClicked[1];

        boolean isFlagging = false;

        if(SwingUtilities.isRightMouseButton(e)){
            isFlagging = true;
        }

        play(x,y, isFlagging);
        Methods.println(field.print(false));
    }

    private int[] calculateLabel(int x, int y){
        int[] res = new int[2];
        int widthField = fieldGui.getLabelField()[0][0].getWidth();
        int heightField = fieldGui.getLabelField()[0][0].getHeight();
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
        Methods.println("Ho finito! Sono calculateLabel di Listener");
        return res;
    }

    private void play(int x, int y, boolean isFlagging){
        if(!isFlagging) {
            int playResult = field.open(x, y);
            fieldGui.refresh(field, false);
            if (playResult == -1) {
                gameOver = true;
                fieldGui.refresh(field, true);
                Gui.showError("You open a bomb!", "Game over");
            } else if (playResult == -2) {
                Gui.showError("You can't open a flagged cell!", "Input error");
            }
        }
        else {
            int flagResult = field.flag(x,y);
            if (flagResult == -1) {
                Gui.showError("You can't flag an open cell!", "Input Error");
            }
            fieldGui.refresh(field, false);
        }
        if(field.check()){
            fieldGui.refresh(field, true);
            Gui.showError("You win!", "Game over");
            gameOver=true;
        }
    }

    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mousePressed(MouseEvent e) { }
    public void mouseReleased(MouseEvent e) { }
}
