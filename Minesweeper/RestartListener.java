import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RestartListener implements MouseListener {
    private Field field;
    private int x,y;
    private Gui gui;

    RestartListener(Field field, int x, int y, Gui gui){
        this.x = x;
        this.y = y;
        this.field = field;
        this.gui = gui;
    }

    public void mouseClicked(MouseEvent e) {
        field.restart(x,y);
        gui.refresh(field, false);
        gui.getPlayArea().addMouseListener(new FieldListener(gui, field));
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
