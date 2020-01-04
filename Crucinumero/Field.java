import java.util.ArrayList;
import java.util.Arrays;

public class Field {

    private Cell[][] field;
    private Cell[][] userField;
    private int x, y, blocks;
    private ArrayList<String> sequences;
    private ArrayList<String> userSequences;
    private int sequencesNumber;
    private ArrayList<String> sequencesSorted;

    Field(int x, int y){
        this.x = x;
        this.y = y;
        blocks = (x*y)/6;
        Methods.println("Blocks: "+ blocks);

        setupField();
        placeBlocks();
        placeNumbers();
        countSequencesNumber();
        readSequences();
        sortSequences();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Cell[][] getField() {
        return field;
    }

    public ArrayList<String> getSequences() { return sequences; }

    public Cell[][] getUserField() {
        return userField;
    }

    public ArrayList<String> getSequencesSorted() {
        return sequencesSorted;
    }

    public ArrayList<String> getUserSequences() {
        return userSequences;
    }

    private void setupField(){
        field = new Cell[x][y];
        userField = new Cell[x][y];
        for (int iy = 0; iy < y; iy++) {
            for (int ix = 0; ix < x; ix++) {
                field[ix][iy] = new Cell();
                userField[ix][iy] = new Cell();
            }
        }
    }

    private void placeBlocks(){
        Methods.println("Piazzamento blocks");
        for (int i = blocks; i > 0; i--) {
            int rx = random(0, x-1);
            int ry = random(0, y-1);
            if (field[rx][ry].isBlock()) {
                i++;
            }
            else {
                field[rx][ry].setBlock();
                userField[rx][ry].setBlock();
            }
        }
    }

    private void placeNumbers(){
        for (int iy = 0; iy < y; iy++) {
            for (int ix = 0; ix < x; ix++) {
                if(!field[ix][iy].isBlock()){
                    int r = random(0,9);
                    field[ix][iy].setNum(""+r);
                    field[ix][iy].setNum(""+r);
                }
            }
        }
    }

    private void countSequencesNumber(){
        int cont = 0;
        for (int iy = 0; iy < y; iy++) {
            for (int ix = 0; ix < x; ix++) {
                if(ix==x-1 && !(field[ix][iy].isBlock())){
                    sequencesNumber++;
                    cont = 0;
                    continue;
                }
                else if (field[ix][iy].isBlock()){
                    if(cont>0){
                        sequencesNumber++;
                    }
                    cont=0;
                    continue;
                }
                cont++;
            }
        }
        Methods.println("Numero di sequenze orizzontali: " + sequencesNumber);

        for (int ix = 0; ix < x; ix++) {
            for (int iy = 0; iy < y; iy++) {
                if(iy==y-1 && !(field[ix][iy].isBlock())){
                    sequencesNumber++;
                    cont = 0;
                    continue;
                }
                else if (field[ix][iy].isBlock()){
                    if(cont>0){
                        sequencesNumber++;
                    }
                    cont=0;
                    continue;
                }
                cont++;
            }
        }
        Methods.println("Numero di sequenze orizzontali+verticali: " + sequencesNumber);

    }
    
    private void readSequences(){
        sequences = new ArrayList<>();
        setupSequences();
        int index = 0;
        for (int iy = 0; iy < y; iy++) {
            for (int ix = 0; ix < x; ix++) {
                if(ix==x-1 && !(field[ix][iy].isBlock())){
                    sequences.set(index, sequences.get(index)+field[ix][iy].getNum());
                    index++;
                    continue;
                }
                else if(field[ix][iy].isBlock()){
                    if(!sequences.get(index).equalsIgnoreCase("")){
                        index++;
                    }
                    continue;
                }
                sequences.set(index, sequences.get(index)+field[ix][iy].getNum());
            }
        }
        for (int ix = 0; ix < x; ix++) {
            for (int iy = 0; iy < y; iy++) {
                if(iy==y-1 && !(field[ix][iy].isBlock())){
                    sequences.set(index, sequences.get(index)+field[ix][iy].getNum());
                    index++;
                    continue;
                }
                else if(field[ix][iy].isBlock()){
                    if(!sequences.get(index).equalsIgnoreCase("")){
                        index++;
                    }
                    continue;
                }
                sequences.set(index, sequences.get(index)+field[ix][iy].getNum());
            }
        }
        Methods.println("Sequenze: "+sequences);
    }

    private void readUserSequences(){
        userSequences = new ArrayList<>();
        setupUserSequences();
        int index = 0;
        for (int iy = 0; iy < y; iy++) {
            for (int ix = 0; ix < x; ix++) {
                if(ix==x-1 && !(userField[ix][iy].isBlock())){
                    userSequences.set(index, userSequences.get(index)+userField[ix][iy].getNum());
                    index++;
                    continue;
                }
                else if(userField[ix][iy].isBlock()){
                    if(!userSequences.get(index).equalsIgnoreCase("")){
                        index++;
                    }
                    continue;
                }
                userSequences.set(index, userSequences.get(index)+userField[ix][iy].getNum());
            }
        }
        for (int ix = 0; ix < x; ix++) {
            for (int iy = 0; iy < y; iy++) {
                if(iy==y-1 && !(userField[ix][iy].isBlock())){
                    userSequences.set(index, userSequences.get(index)+userField[ix][iy].getNum());
                    index++;
                    continue;
                }
                else if(userField[ix][iy].isBlock()){
                    if(!userSequences.get(index).equalsIgnoreCase("")){
                        index++;
                    }
                    continue;
                }
                userSequences.set(index, userSequences.get(index)+userField[ix][iy].getNum());
            }
        }
        Methods.println("UserSequenze: "+userSequences);
    }

    private void setupSequences(){
        for (int i = 0; i < sequencesNumber; i++) {
            sequences.add("");
        }
    }

    private void setupUserSequences(){
        for (int i = 0; i < sequencesNumber; i++) {
            userSequences.add("");
        }
    }

    public boolean winCheck(){
        readUserSequences();
        for (int i = 0; i < sequences.size(); i++) {
            if(!sequences.get(i).equalsIgnoreCase(userSequences.get(i))){
                return false;
            }
        }
        return true;
    }

    private int random(int min, int max){
        return (int)(Math.random() * ((max - min) + 1) + min);
    }

    private void sortSequences(){
        sequencesSorted = new ArrayList<>();
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j < sequencesNumber; j++) {
                if(sequences.get(j).length()==i){
                    sequencesSorted.add(sequences.get(j));
                }
            }
        }

        Methods.println("SequenzeS: " + sequencesSorted);
    }

}
