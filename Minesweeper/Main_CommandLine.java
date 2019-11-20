public class Main_CommandLine {
    public static void main(String[] args){

        int x = Methods.getInt("Enter field length: ");
        int y = Methods.getInt("Enter field height: ");
        int bombs = Methods.getInt("Enter bombs number (0 to generate an appropriate number): ");


        Field field = new Field(x,y,bombs);
        boolean gameOver = false;

        do{
            Methods.print(field.print(gameOver));

            x = Methods.getInt("Enter X: ");
            y = Methods.getInt("Enter Y: ");


            String temp = Methods.getString("Type A to open the cell or F to flag the cell [A/F]: ");
            if(temp.equalsIgnoreCase("A")){
                int res = field.open(x,y);
                /*
                    -1: becca una bomba
                    -2: clicca su una cella flaggata
                 */
                if(res == -1){
                    Methods.printerr("You open a bomb! Game Over!");
                    gameOver = true;
                    Methods.print(field.print(gameOver));
                    return;
                }
                else if (res == -2){
                    Methods.printerr("You can't open a flagged cell!");
                }
            }
            else if(temp.equalsIgnoreCase("F")){
                int flagResult = field.flag(x,y);
                if (flagResult == -1) {
                    Methods.printerr("You can't flag an open cell!");
                }
            }

            gameOver = field.check();

            if(gameOver){
                Methods.printerr("You win! Game Over!");
            }

        } while (!gameOver);

    }
}
