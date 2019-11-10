import java.util.Scanner;

public class TestAI {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        //matrice: 3 righe e 3 colonne
        int[][] campo = new int[3][3];

        //risultato che arriva quando viene chiamata l'AI a muovere, un array di due posizioni in cui verrà inserito x e y dall'ai
        int[] res;

        //stampa il campo (tramite il metodo campoVuoto())per far capire le posizioni all'utente
        System.out.println(campoVuoto());

        //int per far capire il turno
        int turno=0;

        //int che prenderà la mossa dell'utente
        int mossa;

        //int y e x della mossa (AI o dell'utente)
        int y;
        int x;

        //creo l'oggetto ai di classe AI
        AI ai = new AI();

        //inizio a giocare con un ciclo infinito
        while(true){

            //controllo di chi è il turno, se pari dell'utente, se dispari AI
            if(turno%2==0){
                System.out.print("\nMuovi: ");
                mossa = in.nextInt();

                //controllo se la casella esiste
                if(mossa<1 || mossa>9){
                    System.err.print("Non esiste la cella in cui stai inserende il segno, riprova!");
                    continue;
                }

                //controllo se la posizione non è già occupata
                if(campo[convertiX(mossa)][convertiY(mossa)]!=0){
                    System.err.print("La cella in cui stai inserendo il segno è già occupata, riprova!");
                    continue;
                }

                //converto la mossa espressa da casella 1 a 9 in x e y tramite il metodo convertiX/Y
                y = convertiY(mossa);
                x = convertiX(mossa);

                //muovo, la posizione xy del campo diventa 2 (valore del segno dell'utente)
                campo[x][y] = 2;
            }else{
                //faccio aspettare 1.5 secondi all'AI per ordine
                System.out.println("\nMossa artificiale...");
                pausa(1500);

                //richiamo il metodo move, che calcola dove muovere
                res = ai.move(1, campo);

                //assegno al campo la x e la y restituiti dall'ai nel vettore res
                campo[res[0]][res[1]] = 1;
            }
            //aumento il turno
            turno++;

            //stampo il campo aggiornato
            System.out.println(toString(campo));

            //controllo che qualcuno abbia vinto tramite il metodo controllo, passandogli prima 1 (X) e poi 2 (O)
            if(controllo(campo, 1)){
                System.out.println("Vince la X!");
                break;
            }
            if(controllo(campo, 2)){
                System.out.println("Vince la O!");
                break;
            }
            //controllo che non sia un pareggio con 9 mosse fatte
            if(numMosse(campo)==9){
                System.out.println("Pareggio!");
                break;
            }
        }
    }

    //metodo che stampa il campo sulle 3 righe, per maggiore chiarezza converte l'1 in X e il 2 in O tramite il metodo convertiSegno
    private static String toString(int[][] campo){
        String out = "";
        out += " " + convertiSegno(campo[0][0]) + " | " + convertiSegno(campo[1][0]) +" | " + convertiSegno(campo[2][0]);
        out += "\n-----------";
        out += "\n " + convertiSegno(campo[0][1]) + " | " + convertiSegno(campo[1][1]) +" | " + convertiSegno(campo[2][1]);
        out += "\n-----------";
        out += "\n " + convertiSegno(campo[0][2]) + " | " + convertiSegno(campo[1][2]) +" | " + convertiSegno(campo[2][2]);
        return out;
    }

    //stampa il campo vuoto tramite una semplice string
    private static String campoVuoto(){
        String out = "Posizioni del campo: ";
        out += "\n 1 | 2 | 3 ";
        out += "\n-----------";
        out += "\n 4 | 5 | 6 ";
        out += "\n-----------";
        out += "\n 7 | 8 | 9 \n";
        return out;
    }

    //converte ogni possibile valore inserito dall'utente in y
    private static int convertiY(int r){
        if(r==1) return 0;
        if(r==2) return 0;
        if(r==3) return 0;
        if(r==4) return 1;
        if(r==5) return 1;
        if(r==6) return 1;
        if(r==7) return 2;
        if(r==8) return 2;
        if(r==9) return 2;
        return -1;
    }

    //converte ogni possibile valore inserito dall'utente in x
    private static int convertiX(int r){
        if(r==1) return 0;
        if(r==2) return 1;
        if(r==3) return 2;
        if(r==4) return 0;
        if(r==5) return 1;
        if(r==6) return 2;
        if(r==7) return 0;
        if(r==8) return 1;
        if(r==9) return 2;
        return -1;
    }

    //converte 2 in O e 1 in X
    private static char convertiSegno(int num){
        char res = ' ';
        if(num==0)
            return ' ';
        if(num==1)
            return 'X';
        if(num==2)
            return 'O';
        return res;
    }

    //pausa l'esecuzione del programma
    private static void pausa(int ms){
        try{
            Thread.sleep(ms);
        }catch (InterruptedException e){

        }
    }

    //controlla se qualcuno ha vinto
    private static boolean controllo(int[][] campo, int p){
        if(campo[0][0]==p){
            if(campo[1][0]==p){
                if (campo[2][0]==p) { return true; }
            }
            if(campo[0][1]==p){
                if(campo[0][2]==p) { return true; }
            }
            if(campo[1][1]==p){
                if(campo[2][2]==p){ return true; }
            }
        }
        if(campo[0][1]==p) {
            if (campo[1][1]==p) {
                if (campo[2][1]==p) { return true; }
            }
        }
        if(campo[0][2]==p) {
            if (campo[1][2]==p) {
                if (campo[2][2]==p) { return true; }
            }
        }
        if(campo[1][0]==p) {
            if (campo[1][1]==p) {
                if (campo[1][2]==p) { return true; }
            }
        }
        if(campo[2][0]==p) {
            if (campo[2][1]==p) {
                if (campo[2][2]==p) { return true; }
            }
        }
        if(campo[2][0]==p) {
            if (campo[1][1]==p) {
                if (campo[0][2]==p) { return true; }
            }
        }
        return false;
    }

    //conta il numero di mosse scorrendo tutte le caselle e conta quelle occupate
    private static int numMosse(int[][] campo){
        int mosse = 0;
        for(int y=0; y<campo.length; y++){
            for(int x=0; x<campo.length; x++){
                if(campo[x][y]!=0)
                    mosse++;
            }
        }
        return mosse;
    }
}