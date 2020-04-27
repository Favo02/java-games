public class Field {

    private long msStart, msFinish;
    private int msReaction;

    public void start(){
        pause(randomTime(200, 3000));
        msStart = System.currentTimeMillis();
    }

    public int finish(){
        msFinish = System.currentTimeMillis();
        msReaction = (int)(msFinish-msStart);
        return msReaction;
    }

    private int randomTime(int min, int max){
        int ms = (int)(Math.random() * ((max - min) + 1) + min);
        Methods.println("Random: " + ms);
        return ms;
    }

    private void pause(int ms){
        try{
            Thread.sleep(ms);
        } catch (Exception ignore){ }
    }

}
