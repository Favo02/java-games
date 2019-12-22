import java.util.Arrays;

public class Field {
    private int sequenceLenght = 4;
    private int[] sequence = new int[sequenceLenght];
    private int score = 0;
    private int level = 1;

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

    public void generateSequence(){
        sequence = new int[sequenceLenght];
        for (int i = 0; i < sequenceLenght; i++) {
            sequence[i] = random(3);
        }
        Methods.print("\n" + Arrays.toString(sequence));
    }

    private int random(int max){
        int r;
        r = (int)(Math.random()*(max + 1));
        return r;
    }

    public boolean checkSequence(int[] guess){
        for (int i = 0; i < sequenceLenght; i++) {
            if(!(guess[i] == sequence[i])){
                return false;
            }
        }
        score += sequenceLenght;
        sequenceLenght++;
        level++;
        return true;
    }

    private void pause(int ms){
        try {
            Thread.sleep(ms);
        } catch (Exception ignore){ }
    }

    public int[] getSequence() {
        return sequence;
    }
}
