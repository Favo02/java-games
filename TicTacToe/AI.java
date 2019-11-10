public class AI {
    //vettore che restituirà la x e la y in cui muovere
    int[] res = new int[2];

    public int[] move(int p, int[][] campo){
        //conto quante mosse sono state fatte
        int mosse = contMosse(campo);

        //capisco quale numero è l'avversario
        int pa = avversario(p);

        //controllo se posso vincere io tramite il metodo controllo passando il mio segno
        res = controllo(p, campo);
        //se è diverso da -1 vuol dire che ha trovato dove muovere e ritorna dove muovere
        if(res[0]!=-1){
            return res;
        }

        //se arrivo qui non posso vincere io e controllo se può vincere avversario e blocco tramite
        //il metodo controllo passando il segno avversario
        res = controllo(pa, campo);
        //se è diverso da -1 vuol dire che ha trovato dove muovere e ritorna dove muovere
        if(res[0]!=-1){
            return res;
        }

        //se non c'è nulla da bloccare creo io una coppia per non farmi
        //fare il giochetto della doppia soluzione quindi muovo vicino ad un mio segno
        res=vicino(p, campo, mosse);
        //se è diverso da -1 vuol dire che ha trovato dove muovere e ritorna dove muovere
        if(res[0]!=-1){
            return res;
        }

        //se arrivo fino a qui vuol dire che non so che fare ma porca porca e quindi vado random
        res = random(campo);
        return res;
    }

    //se io (p) sono 1 l'avversario è 2 e viceversa
    private int avversario(int p){

        if(p==1)
            return 2;
        if(p==2)
            return 1;
        return -1;
    }

    //conta il numero di mosse scorrendo tutte le caselle e conta quelle occupate
    private int contMosse(int[][] campo){
        int mosse = 0;
        for (int[] ints : campo) {
            for (int j = 0; j < campo.length; j++) {
                if (ints[j] != 0) mosse++;
            }
        }
        return mosse;
    }

    //controllo che ci sia una coppia su una riga, colonna o in verticale, per vincere o bloccarla
    private int[] controllo(int p, int[][] campo){
        /*  
            [0][0] | [1][0] | [2][0]
            ------------------------
            [0][1] | [1][1] | [2][1]
            ------------------------
            [0][2] | [1][2] | [2][2]
         */
        
        //controllo orizzontale righe
        for(int y=0; y<campo.length; y++){
            if((campo[0][y] == p) && (campo[1][y] == p)){
                if(campo[2][y]==0) {
                    res[0] = 2;
                    res[1] = y;
                    return res;
                }
            }
            if((campo[1][y] == p) && (campo[2][y] == p)){
                if(campo[0][y]==0) {
                    res[0] = 0;
                    res[1] = y;
                    return res;
                }
            }
            if((campo[0][y] == p) && (campo[2][y] == p)){
                if(campo[1][y]==0) {
                    res[0] = 1;
                    res[1] = y;
                    return res;
                }
            }
        }
        //controllo verticale colonne
        for(int x=0; x<campo.length; x++){
            if((campo[x][0] == p) && (campo[x][1] == p)){
                if(campo[x][2]==0) {
                    res[0] = x;
                    res[1] = 2;
                    return res;
                }
            }
            if((campo[x][1] == p) && (campo[x][2] == p)){
                if(campo[x][0]==0) {
                    res[0] = x;
                    res[1] = 0;
                    return res;
                }
            }
            if((campo[x][0] == p) && (campo[x][2] == p)){
                if(campo[x][1]==0) {
                    res[0] = x;
                    res[1] = 1;
                    return res;
                }
            }
        }
        //controllo diagonale
        if((campo[0][0] == p) && (campo[1][1] == p)){
            if(campo[2][2]==0) {
                res[0] = 2;
                res[1] = 2;
                return res;
            }
        }
        if((campo[0][0] == p) && (campo[2][2] == p)){
            if(campo[1][1]==0) {
                res[0] = 1;
                res[1] = 1;
                return res;
            }
        }
        if((campo[1][1] == p) && (campo[2][2] == p)){
            if(campo[0][0]==0) {
                res[0] = 0;
                res[1] = 0;
                return res;
            }
        }
        if((campo[2][0] == p) && (campo[1][1] == p)){
            if(campo[0][2]==0) {
                res[0] = 0;
                res[1] = 2;
                return res;
            }
        }
        if((campo[2][0] == p) && (campo[0][2] == p)){
            if(campo[1][1]==0) {
                res[0] = 1;
                res[1] = 1;
                return res;
            }
        }
        if((campo[1][1] == p) && (campo[0][2] == p)){
            if(campo[2][0]==0) {
                res[0] = 2;
                res[1] = 0;
                return res;
            }
        }

        //se non posso vincere o bloccare faccio capire con -1
        res[0] = -1;
        return res;
    }

    //se non ci sono coppie ne creo una muovendo vicino ad un mio segno
    private int[] vicino(int p, int[][] campo, int mosse){
        //se ci sono meno di due mosse vuol dire che non posso creare una coppia e quindi vado al centro o in alto a sinista
        if(mosse<2){
            if(campo[1][1]==0){
                res[0]=1;
                res[1]=1;
            }
            else{
                res[0]=0;
                res[1]=0;
            }
            return res;
        }

        //altrimenti cerco un mio segno e ci piazzo vicino un altro mio segno
        for(int y=0; y<campo.length; y++){
            for(int x=0; x<campo.length; x++){
                if(campo[x][y] == p){
                    if(x==0){
                        if(campo[1][y]==0 && campo[2][y]==0) {
                            res[0]=2;
                            res[1]=y;
                        }
                        return res;
                    }
                    if(x==1){
                        if(campo[0][y]==0 && campo[2][y]==0) {
                            res[0]=2;
                            res[1]=y;
                        }
                        return res;
                    }
                    if(x==2){
                        if(campo[0][y]==0 && campo[1][y]==0) {
                            res[0]=0;
                            res[1]=y;
                        }
                        return res;
                    }
                }
            }
        }

        //se non riesco nè a piazzare in centro o angolo alto sx faccio capire tornando -1
        res[0]=-1;
        return res;
    }

    //gioco in casella random
    private int[] random(int[][] campo){
        if(campo[1][1]==0){
            res[0]=1;
            res[1]=1;
            return res;
        }
        //genero numero da 1 a 9
        int random=(int)(Math.random()*(9));

        //lo converto in xy
        int x = convertiX(random);
        int y = convertiY(random);

        //controllo se è già occupato
        if(campo[x][y]==0){
            res[0] = x;
            res[1] = y;
            return res;
        }
        //in caso lo sia ri-richiamo questo metodo (ricorsivo)
        else {
            res = random(campo);
            return res;
        }
    }

    //converte valore di y espresso da 1 a 9 in matrice
    private int convertiY(int r){
        if(r==0) return 0;
        if(r==1) return 0;
        if(r==2) return 0;
        if(r==3) return 1;
        if(r==4) return 1;
        if(r==5) return 1;
        if(r==6) return 2;
        if(r==7) return 2;
        if(r==8) return 2;
        return -1;
    }

    //converte valore di x espresso da 1 a 9 in matrice
    private int convertiX(int r){
        if(r==0) return 0;
        if(r==1) return 1;
        if(r==2) return 2;
        if(r==3) return 0;
        if(r==4) return 1;
        if(r==5) return 2;
        if(r==6) return 0;
        if(r==7) return 1;
        if(r==8) return 2;
        return -1;
    }

}