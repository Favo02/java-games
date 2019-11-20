import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SurrenderListener implements MouseListener {
    private Field field;
    private Gui gui;

    SurrenderListener(Field field, Gui gui){
        this.field = field;
        this.gui = gui;
    }

    public void mouseClicked(MouseEvent e) {
        gui.refresh(field,true);
    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
}
