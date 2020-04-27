import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Listener implements MouseListener {

    private Field field;
    private Gui gui;
    private int reactionMs;

    Listener(Field field, Gui gui){
        this.field = field;
        this.gui = gui;
    }

    public void mouseClicked(MouseEvent e) {
        reactionMs = field.finish();
        Methods.println(reactionMs);
        if(reactionMs<100){
            Gui.showError("You are cheating! :|", "Too early");
        }
        else {
            gui.finish(reactionMs);
            Gui.showInfo("Result: " + reactionMs + "ms", "Result");
        }
        gui.start();
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
