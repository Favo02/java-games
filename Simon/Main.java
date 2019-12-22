public class Main {
    public static void main(String[] args) {

        Field field = new Field();
        Gui gui = new Gui(field);

        gui.getPlayArea().addMouseListener(new FieldListener(gui, field));
        gui.start();
    }
}
