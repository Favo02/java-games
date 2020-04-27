public class Main {
    public static void main(String[] args) {
        Field field = new Field();
        Gui gui = new Gui(field);
        gui.start();
        gui.getButton().addMouseListener(new Listener(field, gui));
    }
}
