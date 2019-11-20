public class Main_GuiListenerClickable {

    public static void main(String[] args) {

        int x, y, bombs;
        boolean showCellNumber = false;

        while (true){
            x = Gui.showInputInt( "Enter field length: ");
            if(x<4){
                Gui.showError("You can't build a smaller field than 4 :(", "Input Error");
                continue;
            }
            if(x>50){
                Gui.showError("You can't build a bigger field than 50 due to screen dimension :(", "Input Error");
                continue;
            }
            break;
        }
        while (true){
            y = Gui.showInputInt("Enter field height: ");
            if(y<4){
                Gui.showError("You can't build a smaller field than 4 :(", "Input Error");
                continue;
            }
            if(y>50){
                Gui.showError("You can't build a bigger field than 50 due to screen dimension :(", "Input Error");
                continue;
            }
            break;
        }
        while (true){
            bombs = Gui.showInputInt("Enter bombs number (0 to generate an appropriate number): ");
            if (bombs >= ((x * y) - 1)) {
                Gui.showError("You can't insert so many bombs :(", "Input Error");
                continue;
            }
            if (bombs<0) {
                Gui.showError("You can't insert so few bombs :(", "Input Error");
                continue;
            }
            break;
        }

        Field field = new Field(x, y, bombs);

        Gui fieldGui = new Gui(x, y, showCellNumber, field);

        fieldGui.getPlayArea().addMouseListener(new FieldListener(fieldGui, field));
        fieldGui.getRestart().addMouseListener(new RestartListener(field, x, y, fieldGui));
        fieldGui.getSurrender().addMouseListener(new SurrenderListener(field, fieldGui));
    }
}
