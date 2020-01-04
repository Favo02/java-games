public class Cell {
    private String num;

    private boolean isNum = false;
    private boolean isBlock = false;

    public void setBlock() {
        isBlock = true;
        num = "10";
    }

    public void setNum(String num) {
        isNum = true;
        this.num = num;
    }

    public boolean isBlock() {
        return isBlock;
    }

    public boolean isNum() {
        return isNum;
    }

    public String getNum() {
        return num;
    }
}
