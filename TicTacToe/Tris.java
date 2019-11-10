/*
    Autore: Luca Favini, Giacopo Macias
    Data: 30/10/2019

 */

import java.util.Scanner;

public class Tris {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        boolean ai = false;
        boolean facile = false;
        boolean difficile = false;
        GiocatoreUmano giocatore1 = new GiocatoreUmano('o');
        GiocatoreUmano giocatore2 = new GiocatoreUmano('x');
        GiocatoreArtificiale giocatore3 = new GiocatoreArtificiale('x');
        System.out.print("Vuoi giocare contro il computer o contro un altra persona? [Persona/Computer]");
        String temp = in.next();
        while(true){
            if(temp.equalsIgnoreCase("persona")){
                break;
            } else if(temp.equalsIgnoreCase("computer")){
                ai = true;
                System.out.print("Vuoi giocare livello Facile o Difficile? [Facile/Difficile]");
                temp = in.next();
                if(temp.equalsIgnoreCase("facile")) {
                    facile = true;
                } else if(temp.equalsIgnoreCase("difficile")){
                    difficile = true;
                }else {
                    System.err.print("Errore, riprova");
                }
                break;
            } else {
                System.err.print("Rispondi \"Persona\" o \"Computer\"");
            }
        }


        Campo campo = new Campo();
        campo.riempiSpazi(campo); //per non sminchiare le colonne, quelle occupate da x o O sarebbero più larghe dato
        //che gli altri char sarebbero null, invece così sono spazi
        System.out.print(campo.campoVuoto());
        int numMosse = 0;
        while(true){
            giocatore1.gioca(campo);
            numMosse++;
            System.out.print(campo);
            if(numMosse>=5){
                if(campo.controllo('x')) {
                    System.out.println("\nVince la X!");
                    break;
                }
                if(campo.controllo('o')) {
                    System.out.println("\nVince la O!");
                    break;
                }
            }
            if(numMosse==9){
                System.out.println("\nPareggio!");
                break;
            }
            if(ai){
                if(facile){
                    System.out.println("\nMossa Giocatore Artificiale...");
                    pausa(2000);
                    giocatore3.giocaFacile(campo);
                    numMosse++;
                    System.out.print(campo);
                }
                if(difficile){
                    System.out.println("\nMossa Giocatore Artificiale...");
                    pausa(2000);
                    giocatore3.giocaDifficile(campo, numMosse);
                    numMosse++;
                    System.out.print(campo);
                }
            }
            if(!ai){
                giocatore2.gioca(campo);
                numMosse++;
                System.out.print(campo);
            }
            if(numMosse>=5){
                if(campo.controllo('x')) {
                    System.out.println("\nVince la X!");
                    break;
                }
                if(campo.controllo('o')) {
                    System.out.println("\nVince la O!");
                    break;
                }
            }
            if(numMosse==9){
                System.out.println("Pareggio!");
                break;
            }
        }
    }

    public static void pausa(long tempo) {
        try {
            Thread.sleep(tempo);
        } catch (InterruptedException e) {
        }
    }
}

class Giocatore{
    private char segno;
    public Giocatore(char segno){
        this.segno=segno;
    }
    public char getSegno() {
        return segno;
    }
}

class GiocatoreUmano extends Giocatore{

    public GiocatoreUmano(char segno){
        super(segno);
    }

    public Campo gioca(Campo campo){
        Scanner in = new Scanner(System.in);
        System.out.print("\nIl tuo segno è " + getSegno() + ", dove vuoi inserire il segno? ");
        int input = in.nextInt();
        if(input<0 || input>8){
            System.err.print("Non esiste la cella in cui stai inserende il segno, riprova!");
            return gioca(campo);
        }
        if(campo.campo[input]=='x' || campo.campo[input]=='o'){
            System.err.print("La cella in cui stai inserendo il segno è già occupata, riprova!");
            return gioca(campo);
        }
        campo.campo[input] = getSegno();
        return campo;
    }
}

class GiocatoreArtificiale extends Giocatore{
    public GiocatoreArtificiale(char segno){
        super(segno);
    }
    public Campo giocaFacile(Campo campo){
        int random = -1;
        while(true){
            random=(int)(Math.random()*(9));
            if(!(campo.campo[random]=='x' || campo.campo[random]=='o')){
                campo.campo[random] = getSegno();
                break;
            }
            giocaFacile(campo);
            return campo;
        }
        return campo;
    }
    
    public Campo giocaDifficile(Campo campo, int numMosse){

        if(numMosse==1){
            if(campo.campo[4]=='o'){
                if(!(campo.campo[0]=='x' || campo.campo[0]=='o')) {
                    campo.campo[0] = 'x';
                    return campo;
                }
                if(!(campo.campo[2]=='x' || campo.campo[2]=='o')) {
                    campo.campo[2] = 'x';
                    return campo;
                }
            }
            campo.campo[4] = 'x';
            return campo;
        }

        /*  TUTTI I CASI:
            0-1-2
            0-1->2
            0-2->1
            1-2->0

            3-4-5
            3-4->5
            3-5->4
            4-5->3

            6-7-8
            6-7->8
            6-8->7
            7-8->6

            0-3-6
            0-3->6
            0-6->3
            3-6->0

            1-4-7
            1-4->7
            1-7->4
            4-7->1

            2-5-8
            2-5->8
            2-8->5
            5-8->2

            0-4-8
            0-4->8
            0-8->4
            4-8->0

            2-4-6
            2-4->6
            2-6->4
            4-6->2
         */
        /*CASI ORDINATI:
            0-1->2
            0-2->1
            0-3->6
            0-6->3
            0-4->8
            0-8->4
            1-2->0
            1-4->7
            1-7->4
            2-5->8
            2-8->5
            2-4->6
            2-6->4
            3-4->5
            3-5->4
            3-6->0
            4-5->3
            4-7->1
            4-8->0
            4-6->2
            5-8->2
            6-7->8
            6-8->7
            7-8->6


         */
        if(campo.campo[0]=='o') {
            if(campo.campo[1]=='o'){
                if(!(campo.campo[2]=='x' || campo.campo[2]=='o')) {
                    campo.campo[2] = 'x';
                    return campo;
                }
            }
            if(campo.campo[2]=='o'){
                if(!(campo.campo[1]=='x' || campo.campo[1]=='o')) {
                    campo.campo[1] = 'x';
                    return campo;
                }
            }
            if(campo.campo[3]=='o'){
                if(!(campo.campo[6]=='x' || campo.campo[6]=='o')) {
                    campo.campo[6] = 'x';
                    return campo;
                }
            }
            if(campo.campo[4]=='o'){
                if(!(campo.campo[8]=='x' || campo.campo[8]=='o')) {
                    campo.campo[8] = 'x';
                    return campo;
                }
            }
            if(campo.campo[6]=='o'){
                if(!(campo.campo[3]=='x' || campo.campo[3]=='o')) {
                    campo.campo[3] = 'x';
                    return campo;
                }
            }
            if(campo.campo[8]=='o'){
                if(!(campo.campo[4]=='x' || campo.campo[4]=='o')) {
                    campo.campo[4] = 'x';
                    return campo;
                }
            }
        }
        if(campo.campo[1]=='o') {
            if (campo.campo[2]=='o') {
                if(!(campo.campo[0]=='x' || campo.campo[7]=='o')) {
                    campo.campo[0] = 'x';
                    return campo;
                }
            }
            if (campo.campo[4]=='o') {
                if(!(campo.campo[7]=='x' || campo.campo[7]=='o')) {
                    campo.campo[7] = 'x';
                    return campo;
                }
            }
            if (campo.campo[7]=='o') {
                if(!(campo.campo[4]=='x' || campo.campo[4]=='o')) {
                    campo.campo[4] = 'x';
                    return campo;
                }
            }
        }
        if(campo.campo[2]=='o') {
            if (campo.campo[5] == 'o') {
                if (!(campo.campo[8] == 'x' || campo.campo[8] == 'o')) {
                    campo.campo[8] = 'x';
                    return campo;
                }
            }
            if (campo.campo[8] == 'o') {
                if (!(campo.campo[5] == 'x' || campo.campo[5] == 'o')) {
                    campo.campo[5] = 'x';
                    return campo;
                }
            }
            if (campo.campo[4] == 'o') {
                if (!(campo.campo[6] == 'x' || campo.campo[6] == 'o')) {
                    campo.campo[6] = 'x';
                    return campo;
                }
            }
            if (campo.campo[6] == 'o') {
                if (!(campo.campo[4] == 'x' || campo.campo[4] == 'o')) {
                    campo.campo[4] = 'x';
                    return campo;
                }
            }
        }
        if(campo.campo[3]=='o') {
            if (campo.campo[4]=='o') {
                if(!(campo.campo[5]=='x' || campo.campo[5]=='o')) {
                    campo.campo[5] = 'x';
                    return campo;
                }
            }
            if (campo.campo[5]=='o') {
                if(!(campo.campo[4]=='x' || campo.campo[4]=='o')) {
                    campo.campo[4] = 'x';
                    return campo;
                }
            }
            if (campo.campo[6]=='o') {
                if(!(campo.campo[0]=='x' || campo.campo[0]=='o')) {
                    campo.campo[0] = 'x';
                    return campo;
                }
            }
        }
        if(campo.campo[4]=='o') {
            if (campo.campo[5]=='o') {
                if(!(campo.campo[3]=='x' || campo.campo[3]=='o')) {
                    campo.campo[3] = 'x';
                    return campo;
                }
            }
            if (campo.campo[7]=='o') {
                if(!(campo.campo[1]=='x' || campo.campo[1]=='o')) {
                    campo.campo[1] = 'x';
                    return campo;
                }
            }
            if (campo.campo[8]=='o') {
                if(!(campo.campo[0]=='x' || campo.campo[0]=='o')) {
                    campo.campo[0] = 'x';
                    return campo;
                }
            }
            if (campo.campo[6]=='o') {
                if(!(campo.campo[2]=='x' || campo.campo[2]=='o')) {
                    campo.campo[2] = 'x';
                    return campo;
                }
            }
        }
        if(campo.campo[5]=='o') {
            if (campo.campo[8]=='o') {
                if(!(campo.campo[2]=='x' || campo.campo[2]=='o')) {
                    campo.campo[2] = 'x';
                    return campo;
                }
            }
        }
        if(campo.campo[6]=='o') {
            if (campo.campo[7]=='o') {
                if(!(campo.campo[8]=='x' || campo.campo[8]=='o')) {
                    campo.campo[8] = 'x';
                    return campo;
                }
            }
            if (campo.campo[8]=='o') {
                if(!(campo.campo[7]=='x' || campo.campo[7]=='o')) {
                    campo.campo[7] = 'x';
                    return campo;
                }
            }
        }
        if(campo.campo[7]=='o') {
            if (campo.campo[8]=='o') {
                if (!(campo.campo[6] == 'x' || campo.campo[6] == 'o')) {
                    campo.campo[6] = 'x';
                    return campo;
                }
            }
        }

        giocaFacile(campo);
        System.out.println("random");
        return campo;
    }
}

class Campo{
    char[] campo = new char[9];
    public Campo riempiSpazi(Campo campo){
        for(int i =0; i<9;i++){
            campo.campo[i]=' ';
        }
        return campo;
    }
    public String campoVuoto(){
        String out = "Posizioni del campo: ";
        out += "\n 0 | 1 | 2 ";
        out += "\n-----------";
        out += "\n 3 | 4 | 5 ";
        out += "\n-----------";
        out += "\n 6 | 7 | 8 \n";
        return out;
    }
    public String toString(){
        String out = "";
        out += " " + campo[0] + " | " + campo[1] +" | " + campo[2];
        out += "\n-----------";
        out += "\n " + campo[3] + " | " + campo[4] +" | " + campo[5];
        out += "\n-----------";
        out += "\n " + campo[6] + " | " + campo[7] +" | " + campo[8];
        return out;
    }
    public boolean controllo(char valore){
        /*  V 1-2-3
            V 4-5-6
            V 7-8-9
            V 1-4-7
            V 2-5-8
            V 3-6-9
            V 1-5-9
            V 3-5-7
         */
        if(campo[0]==valore){
            if(campo[1]==valore){
                if (campo[2]==valore) { return true; }
            }
            if(campo[3]==valore){
                if(campo[6]==valore) { return true; }
            }
            if(campo[4]==valore){
                if(campo[8]==valore){ return true; }
            }
        }
        if(campo[3]==valore) {
            if (campo[4]==valore) {
                if (campo[5]==valore) { return true; }
            }
        }
        if(campo[6]==valore) {
            if (campo[7]==valore) {
                if (campo[8]==valore) { return true; }
            }
        }
        if(campo[1]==valore) {
            if (campo[4]==valore) {
                if (campo[7]==valore) { return true; }
            }
        }
        if(campo[2]==valore) {
            if (campo[5]==valore) {
                if (campo[8]==valore) { return true; }
            }
        }
        if(campo[2]==valore) {
            if (campo[4]==valore) {
                if (campo[6]==valore) { return true; }
            }
        }
        return false;
    }
}
