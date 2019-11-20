
public class Main_GuiNotClickable {

    public static void main(String[] args) {
        int x, y, bombs;
        final int maxX, maxY;
        boolean gameOver;

        while (true){
            x = Gui.showInputInt( "Enter field length: ");
            if(x<4){
                Gui.showError("You can't build a smaller field than 4 :(", "Input Error");
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
            break;
        }
        while (true){
            bombs = Gui.showInputInt("Enter bombs number (0 to generate an appropriate number): ");
            if (bombs >= (x * y - 1)) {
                Gui.showError("You can't insert so many bombs :(", "Input Error");
                continue;
            }
            if (bombs<0) {
                Gui.showError("You can't insert so few bombs :(", "Input Error");
                continue;
            }
            break;
        }

        maxX = x-1;
        maxY = y-1;

        Field field = new Field(x, y, bombs);
        Gui fieldGui = new Gui(x, y, true, field);
        gameOver = false;

        do{
            fieldGui.refresh(field, gameOver);

            while(true){
                x = Gui.showInputInt("Enter X");
                if(x>maxX || x<0){
                    Gui.showError("This cell doesn't exists O.o", "Input Error");
                    continue;
                }
                break;
            }
            while(true){
                y = Gui.showInputInt("Enter Y");
                if(y>maxY || y<0){
                    Gui.showError("This cell doesn't exists O.o", "Input Error");
                    continue;
                }
                break;
            }
            int selectionResult = Gui.showInputSelection("Do you want to flag or open the cell?",
                    "Open",
                    "Flag");

            //Decide di aprire
            if(selectionResult==1){
                int playResult = field.open(x,y);
                //Ha preso una bomba
                if(playResult == -1){
                    gameOver =  true;
                    fieldGui.refresh(field, true);
                    Gui.showError("You open a bomb!", "Game over");
                    return;
                }
                //Ha aperto una cella flaggata
                else if(playResult == -2){
                    Gui.showError("You can't open a flagged cell!", "Input error");
                }
            }
            //Decide di flaggare
            else if(selectionResult==2){
                int flagResult = field.flag(x,y);
                if (flagResult == -1) {
                    Gui.showError("You can't flag an open cell!", "Input Error");
                }
                fieldGui.refresh(field, false);
            }

            gameOver = field.check();

            if(gameOver){
                Gui.showError("You win!", "Game over");
            }

        } while (!gameOver);



    }
}
