public class Field {

    private int bombs, maxX, maxY;

    private Cell[][] field;

    Field(int x, int y, int bombs){
        if(bombs==0){
            bombs = (x*y)/8;
            Methods.println("Generazione bombe automatica: " + bombs);
        }
        this.bombs = bombs;
        field = new Cell[x][y];
        maxX = x-1;
        maxY = y-1;
        setup();
        placeBombs();
        placeNumbers();
    }

    private void setup(){
        for (int i = 0; i <= maxX ; i++) {
            for (int j = 0; j <= maxY ; j++) {
                field[i][j] = new Cell();
            }
        }
    }

    private void placeBombs(){
        Methods.println("Piazzamento bombe casuale");
        int x, y;
        for(int cont=0; cont<bombs; cont++){
            x=random(maxX);
            y=random(maxY);
            if(field[x][y].isEmpty()) {
                field[x][y].setBomb();
                field[x][y].setEmpty();
            } else {
                cont--;
            }
        }
        Methods.println("Piazzamento bombe completato");
    }

    private void placeNumbers(){
        Methods.println("Piazzamento numeri");
        for(int y=0; y<= maxY; y++){
            for(int x=0; x<= maxX; x++){
                if(field[x][y].isBomb()) {
                    field[x][y].setNum(10);
                    for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            if(i==0 && j==0) { continue; }
                            try{
                                field[x+i][y+j].increaseNum();
                                field[x+i][y+j].setNum();
                                field[x+i][y+j].setEmpty();
                            }
                            catch (ArrayIndexOutOfBoundsException ignore){
                            }
                        }
                    }
                }
            }
        }
        Methods.println("Piazzamento numeri completato");
    }

    private int random(int max){
        int r;
        r = (int)(Math.random()*(max + 1));
        return r;
    }

    public int open(int x, int y){
        Methods.println("Aprire cella: " + x + " " + y);
        /*
        -1: becca una bomba
        -2: clicca su una cella flaggata
         */
        if(field[x][y].isVisible() && field[x][y].isNum()){
            if(openNumber(x,y)==-1){
                return -1;
            }
        }
        if(field[x][y].isFlagged()) {
            Methods.println("Cella flaggata");
            return -2;
        }
        else if(field[x][y].isBomb()) {
            Methods.println("Cella bomba");
            return -1;
        }
        else if(field[x][y].isVisible()) {
            Methods.println("Cella già aperta");
        }
        else if(field[x][y].isNum()) {
            Methods.println("Cella da aprire è un numero");
            field[x][y].setVisible();
        }
        else if(field[x][y].isEmpty()){
            Methods.println("Cella da aprire vuota");
            field[x][y].setVisible();
            openEmpty(x,y);
        }
        return 0;
    }

    public int flag(int x, int y){
        Methods.println("Flaggare cella: " + x + " " + y);
        if (field[x][y].isVisible()){
            return -1;
        }
        field[x][y].setFlagged();
        return 0;
    }

    private void openEmpty(int x, int y){
        Methods.println("Cella aperta vuota, aprire vuoti vicini");

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if(i==0 && j==0) { continue; }
                try{
                    if(field[x+i][y+j].isVisible()){
                        continue;
                    }
                    else if(field[x+i][y+j].isBomb()){
                        continue;
                    }
                    else if(field[x+i][y+j].isEmpty()){
                        field[x+i][y+j].setVisible();
                        openEmpty(x+i, y+j);
                    }
                    else if(field[x+i][y+j].isNum()){
                        field[x+i][y+j].setVisible();
                    }
                    if(field[x+i][y+j].isFlagged()){
                        field[x+i][y+j].setFlagged();
                    }
                }
                catch (ArrayIndexOutOfBoundsException ignore){ }
            }
        }
    }

    private int openNumber(int x, int y){
        Methods.println("Click su numero con bombe già coperte, aprire intorno");
        int bombCount = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                try {
                    if(field[x+i][y+j].isFlagged()){
                        bombCount++;
                    }
                } catch (ArrayIndexOutOfBoundsException ignore) { }
            }
        }
        if(bombCount==field[x][y].getNum()){
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i == 0 && j == 0) {
                        continue;
                    }
                    try {
                        if(field[x+i][y+j].isFlagged()){
                            continue;
                        }
                        else if(field[x+i][y+j].isBomb()){
                            if(open(x+i, y+j)==-1){
                                return -1;
                            }
                        }
                        else if(!(field[x+i][y+j].isVisible())){
                            field[x+i][y+j].setVisible();
                        }
                        if(field[x+i][y+j].isEmpty()){
                            openEmpty(x+i, y+j);
                        }
                    } catch (ArrayIndexOutOfBoundsException ignore) { }
                }
            }
        }
        return 0;
    }

    public boolean check(){
        Methods.println("Controllo vittoria");
        int[] values = countValues();
        /* values:
        [0] = celle visibili
        [1] = celle flaggate
         */
        return ((values[0] + values[1]) == (maxX+1)*(maxY+1));
    }

    private int[] countValues(){
        int[] cont = new int[2];
        for (int y = 0; y <= maxY; y++) {
            for (int x = 0; x <= maxX; x++) {
                if(field[x][y].isVisible()) {
                    cont[0]++;
                }
                if(field[x][y].isFlagged()) {
                    cont[1]++;
                }
            }
        }
        return cont;
    }

    public String print(boolean gameOver) {
        /* gameOver:
        true: becca una bomba, print tutte le bombe + già visibili
        false: print normale
         */
        String res = "";
        if(gameOver){
            res += "     ";
            for (int i = 0; i <= maxY; i++) {
                res += i+ " ";
            }
            res += "\n\n";
            for (int y = 0; y <= maxY; y++) {
                for (int x = 0; x <= maxX; x++) {
                    if(x==0){
                        res += y + "    ";
                    }
                    if (field[x][y].isBomb() && field[x][y].isFlagged()) {
                        res += "C ";
                    }
                    else if (!field[x][y].isBomb() && field[x][y].isFlagged()) {
                        res += "S ";
                    }
                    else if (field[x][y].isBomb()) {
                        res += "B ";
                    }
                    else if (!field[x][y].isVisible()) {
                        res += "- ";
                    }
                    else if (field[x][y].isVisible()) {
                        if (field[x][y].isEmpty()) {
                            res += "0 ";
                        } else if (field[x][y].isNum()) {
                            res += field[x][y].getNum() + " ";
                        }
                    }
                }
                res += "\n";
            }
            return "\nC: Flag corretta" +
                    "\nS: Flag sbagliata" +
                    "\nB: Bomba" +
                    "\n" + res;
        }
        else {
            res += "     ";
            for (int i = 0; i <= maxX; i++) {
                res += i+ " ";
            }
            res += "\n\n";
            for (int y = 0; y <= maxY; y++) {
                for (int x = 0; x <= maxX; x++) {
                    if(x==0){
                        res += y + "    ";
                    }
                    if (field[x][y].isFlagged()) {
                        res += "F ";
                    }
                    else if (!field[x][y].isVisible()) {
                        res += "- ";
                    }
                    else if (field[x][y].isVisible()) {
                        if (field[x][y].isEmpty()) {
                            res += "0 ";
                        } else if (field[x][y].isNum()) {
                            res += field[x][y].getNum() + " ";
                        }
                    }
                }
                res += "\n";
            }
            return "\nF: Flag " +
                    "\n" + res;
        }
    }

    public Cell[][] getField (){
        return field;
    }

    public int getFlagged(){
        return countValues()[1];
    }

    public int getBombs() {
        return bombs;
    }

    public void restart(int x, int y){
        field = new Cell[x][y];
        maxX = x-1;
        maxY = y-1;
        setup();
        placeBombs();
        placeNumbers();
    }

}