public class Cell {

    private int num = 0;
    private boolean isNum = false;
    private boolean isEmpty = true;
    private boolean isBomb = false;
    private boolean isFlagged = false;
    private boolean isVisible = false;
    private boolean isFlagCorrect = false;

    public int getNum(){
        return num;
    }
    public boolean isNum(){
        return isNum;
    }
    public boolean isEmpty(){
        return isEmpty;
    }
    public boolean isBomb(){
        return isBomb;
    }
    public boolean isFlagged(){
        return isFlagged;
    }
    public boolean isVisible(){
        return isVisible;
    }
    public boolean isFlagCorrect() {
        return isFlagCorrect;
    }

    public void increaseNum(){
        num++;
    }
    public void setNum(){
        isNum = true;
    }
    public void setNum(int num){
        setNum();
        this.num = num;
    }
    public void setEmpty(){
        isEmpty = false;
    }
    public void setBomb(){
        isBomb = true;
    }
    public void setFlagged(){
        isFlagged = !isFlagged;
        if(isFlagged && isBomb){
            isFlagCorrect = true;
        }
        else {
            isFlagCorrect = false;
        }
    }
    public void setVisible(){
        isVisible = true;
    }

}