
package barquitos;

import java.util.Scanner;

public class Partida {
    private Jugador[] jugadores;
    
    public Partida() {
        this.jugadores = new Jugador[2];
        for (int i = 0; i < jugadores.length; i++) {
            jugadores[i] = new Jugador();
        }
    }

    public Jugador[] getJugadores() {
        return jugadores;
    }

    public void setJugadores(Jugador[] jugadores) {
        this.jugadores = jugadores;
    }
    
    public void ronda(){
        boolean terminado = false;
        int turno = 1;
        for (int i = 0; i < jugadores.length; i++) {
            
            if (jugadores[i].isHumano()) {
                System.out.println(jugadores[i].getNombre() + ", te toca colocar tus barcos.");
                esperar(1000);
                jugadores[i].getTablero().rellenaManual(jugadores[i].getNombre());
//                jugadores[i].getTablero().rellenaAleatorio();
                System.out.println(jugadores[i].getNombre() + " esta listo.");
                esperar(1000);
            }else{
                System.out.println(jugadores[i].getNombre() + " esta colocando sus barcos.");
                esperar(1000);
                jugadores[i].getTablero().rellenaAleatorio();
                System.out.println(jugadores[i].getTablero().toString(true, jugadores[i].getNombre()) + "\n " + jugadores[i].getNombre() + " esta listo.");
                esperar(1000);
            }
        }
        System.out.println("\n Todos los jugadores han rellenado sus tableros, empieza el juego.");
        esperar(1000);
        
        do {
            String cadTur = "                ╔════════════╗\n";
            cadTur += "                ║     Turno " + turno;
            if (turno<10) {
                cadTur += " ";
            }
            cadTur += "           ║\n";
            cadTur += "                ╚════════════╝";
            System.out.println(cadTur);
            esperar(1000);
            for (int i = 0; i < jugadores.length; i++) {
                this.turno(i);
            }
            turno++;
            for (int i = 0; i < jugadores.length; i++) {
                int hundidos = 0;
                for (int j = 0; j < jugadores[i].getTablero().getBarcos().length; j++) {
                    if (jugadores[i].getTablero().getBarcos()[j].isHundido()) {
                        hundidos++;
                    }
                }
                if (hundidos >= 4) {
                    System.out.println(jugadores[i].getNombre() + " ha perdido, fin de la partida.");
                    estadisticas();
                    terminado = true;
                }
            }
        } while (!terminado);
        
    }
    
    public void estadisticas(){
        String cad = "";
        int porcentajeAcierto = 100;
        for (int i = 0; i < jugadores.length; i++) {
            if (jugadores[i].getAciertos() != 0) {
                porcentajeAcierto = (jugadores[i].getAciertos()*100)/jugadores[i].getCasillasIdentificadas();
            }else{
                porcentajeAcierto = 0;
            }
            cad += "\n- Estadisticas de " + jugadores[i].getNombre() + " - \n";
            cad += "Casillas descubiertas: " + jugadores[i].getCasillasIdentificadas() + "\n";
            cad += "Aciertos: " + jugadores[i].getAciertos() + "\n";
            cad += "Fallos: " + (jugadores[i].getCasillasIdentificadas() - jugadores[i].getAciertos()) + "\n";
            cad += "Porcentaje de acierto: " + porcentajeAcierto + "%\n\n";
            System.out.println(cad);
            cad = "";
        }
    }
    
    public void turno(int jugador){
        int jugadorEne = 0;
        jugadores[jugador].setCasillasIdentificadas(jugadores[jugador].getCasillasIdentificadas()+1);
        if (jugador == 0) {
            jugadorEne = 1;
        }
        if (jugadores[jugador].isHumano()) {
            boolean valido = false;
            String selec = "";
            do {
                selec = selecCasilla(jugador);
                if(!(selec == "E")){
                if (jugadores[jugadorEne].getTablero().getCasillas()[(int)selec.charAt(0) - '0'][(int)selec.charAt(1) - '0'].isDescubierta()) {
                    System.out.println("Esta casilla ya esta descubierta.");
                }else{
                    jugadores[jugadorEne].getTablero().getCasillas()[(int)selec.charAt(0) - '0'][(int)selec.charAt(1) - '0'].setDescubierta(true);
                    
                    if (jugadores[jugadorEne].getTablero().getCasillas()[(int)selec.charAt(0) - '0'][(int)selec.charAt(1) - '0'].isOcupada()) {
                        System.out.println("¡Tocado!");
                        jugadores[jugador].setAciertos(jugadores[jugador].getAciertos()+1);
                        esperar(1000);
                        jugadores[jugadorEne].getTablero().actualizarBarcos(selec);
                        esperar(1000);
                    }else{
                        System.out.println("¡Agua!");
                        esperar(1000);
                    }
                    System.out.println(jugadores[jugadorEne].getTablero().toString(true, jugadores[jugadorEne].getNombre()));
                    valido = true;
                }
                }else{
                    
                }
            } while (!valido);
            
        }else{
            String selec = botEligeCasilla(jugador);
            esperar(1000);
            jugadores[jugadorEne].getTablero().getCasillas()[(int)selec.charAt(0) - '0'][(int)selec.charAt(1) - '0'].setDescubierta(true);
            if (jugadores[jugadorEne].getTablero().getCasillas()[(int)selec.charAt(0) - '0'][(int)selec.charAt(1) - '0'].isOcupada()) {
                        System.out.println("¡Tocado!");
                        jugadores[jugador].setAciertos(jugadores[jugador].getAciertos()+1);
                        esperar(1000);
                        jugadores[jugadorEne].getTablero().actualizarBarcos(selec);
                    }else{
                        System.out.println("¡Agua!");
                        esperar(1000);
                    }
                    System.out.println(jugadores[jugadorEne].getTablero().toString(true, jugadores[jugadorEne].getNombre()));
                    esperar(1000);
        }
    }
    
    public String botEligeCasilla(int jugador){
        String retorno = "";
        String muestra = "";
        int jugadorEne = 0;
        if (jugador == 0) {
            jugadorEne = 1;
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (jugadores[jugadorEne].getTablero().getCasillas()[i][j].isDescubierta() && jugadores[jugadorEne].getTablero().getCasillas()[i][j].isOcupada()) {
                    if (!(jugadores[jugadorEne].getTablero().getBarcos()[jugadores[jugadorEne].getTablero().buscarBarco("" + j + i)].isHundido())) {
                        //El bot encuentra una casilla de un barco tocado, pero no hundido
                        int cercano = buscarAlrededor(i + "" + j, jugadorEne);
                        if (cercano == 0) {// No encuentra otra casilla cercana tocada y vista
                            int hrandom = generaEnteroAleatorio(0,1);
                            if (hrandom == 0) {
                                if (i>0 && i <9) {
                                    int dirRandom = generaEnteroAleatorio(0,1);
                                    if (dirRandom == 0) {
                                        if (!(jugadores[jugadorEne].getTablero().getCasillas()[i-1][j].isDescubierta())) {
                                        System.out.println(jugadores[jugador].getNombre() + " elige la casilla " + selecDesTrad((i-1) + "" + j));
                                        retorno = (i-1) + "" + j;
                                        return retorno;
                                    }
                                    }else{
                                        if (!(jugadores[jugadorEne].getTablero().getCasillas()[i+1][j].isDescubierta())) {
                                        System.out.println(jugadores[jugador].getNombre() + " elige la casilla " + selecDesTrad((i+1) + "" + j));
                                        retorno = (i+1) + "" + j;
                                        return retorno;
                                    }
                                    }
                                }
                                if (i>0) {
                                    if (!(jugadores[jugadorEne].getTablero().getCasillas()[i-1][j].isDescubierta())) {
                                        System.out.println(jugadores[jugador].getNombre() + " elige la casilla " + selecDesTrad((i-1) + "" + j));
                                        retorno = (i-1) + "" + j;
                                        return retorno;
                                    }
                                }
                                if (i<9) {
                                    if (!(jugadores[jugadorEne].getTablero().getCasillas()[i+1][j].isDescubierta())) {
                                        System.out.println(jugadores[jugador].getNombre() + " elige la casilla " + selecDesTrad((i+1) + "" + j));
                                        retorno = (i+1) + "" + j;
                                        return retorno;
                                    }
                                }
                            }else{
                                if (j>0 && j<9) {
                                int dirRandom = generaEnteroAleatorio(0,1);
                                    if (dirRandom == 0) {
                                        if (!(jugadores[jugadorEne].getTablero().getCasillas()[i][j-1].isDescubierta())) {
                                        System.out.println(jugadores[jugador].getNombre() + " elige la casilla " + selecDesTrad(i + "" + (j-1)));
                                        retorno = i + "" + (j-1);
                                        return retorno;
                                    }
                                    }else{
                                        if (!(jugadores[jugadorEne].getTablero().getCasillas()[i][j+1].isDescubierta())) {
                                        System.out.println(jugadores[jugador].getNombre() + " elige la casilla " + selecDesTrad(i + "" + (j+1)));
                                        retorno = i + "" + (j+1);
                                        return retorno;
                                    }
                                    }
                                }
                                if (j>0) {
                                    if (!(jugadores[jugadorEne].getTablero().getCasillas()[i][j-1].isDescubierta())) {
                                        System.out.println(jugadores[jugador].getNombre() + " elige la casilla " + selecDesTrad(i + "" + (j-1)));
                                        retorno = i + "" + (j-1);
                                        return retorno;
                                    }
                                }
                                if (j<9) {
                                    if (!(jugadores[jugadorEne].getTablero().getCasillas()[i][j+1].isDescubierta())) {
                                        System.out.println(jugadores[jugador].getNombre() + " elige la casilla " + selecDesTrad(i + "" + (j+1)));
                                        retorno = i + "" + (j+1);
                                        return retorno;
                                    }
                                }
                            }
                            
                        }else if(cercano == -2 && i < 9){
                            if (!(jugadores[jugadorEne].getTablero().getCasillas()[i+1][j].isDescubierta())) {
                                System.out.println(jugadores[jugador].getNombre() + " elige la casilla " + selecDesTrad((i+1) + "" + j));
                                retorno = (i+1) + "" + j;
                                return retorno;
                            }
                        }else if(cercano == -1 && j < 9){
                            if (!(jugadores[jugadorEne].getTablero().getCasillas()[i][j+1].isDescubierta())) {
                                System.out.println(jugadores[jugador].getNombre() + " elige la casilla " + selecDesTrad(i + "" + (j+1)));
                                retorno = i + "" + (j+1);
                                return retorno;
                            }
                        }else if(cercano == 2 && i > 0){
                            if (!(jugadores[jugadorEne].getTablero().getCasillas()[i-1][j].isDescubierta())) {
                                System.out.println(jugadores[jugador].getNombre() + " elige la casilla " + selecDesTrad((i-1) + "" + j));
                                retorno = (i-1) + "" + j;
                                return retorno;
                            }else{
                                for (int k = 1; k < 9-i; k++) {
                                    if (!(jugadores[jugadorEne].getTablero().getCasillas()[i+k][j].isDescubierta())) {
                                    System.out.println(jugadores[jugador].getNombre() + " elige la casilla " + selecDesTrad((i+k) + "" + j));
                                    retorno = (i+k) + "" + j;
                                    return retorno;
                                    }
                                }
                            }
                        }else if(cercano == 1 && j > 0){
                            if (!(jugadores[jugadorEne].getTablero().getCasillas()[i][j-1].isDescubierta())) {
                                System.out.println(jugadores[jugador].getNombre() + " elige la casilla " + selecDesTrad(i + "" + (j-1)));
                                retorno = i + "" + (j-1);
                                return retorno;
                            }else{
                                for (int k = 1; k < 9-j; k++) {
                                    if (!(jugadores[jugadorEne].getTablero().getCasillas()[i][j+k].isDescubierta())) {
                                    System.out.println(jugadores[jugador].getNombre() + " elige la casilla " + selecDesTrad(i + "" + (j+k)));
                                    retorno = i + "" + (j+k);
                                    return retorno;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        boolean valido = false;
        if (jugadores[jugador].getDificultad() == 2) { // Dificultad DIFICIL, probabilidad de acertar por suerte
            int suerte = generaEnteroAleatorio(0,100);
            if (suerte > 80) {
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if (!(jugadores[jugadorEne].getTablero().getCasillas()[i][j].isDescubierta()) && jugadores[jugadorEne].getTablero().getCasillas()[i][j].isOcupada()) {
                            suerte = generaEnteroAleatorio(0,100);
                            if(suerte > 70){
                            muestra += selecDesTrad(i + "" + j);
                            System.out.println(jugadores[jugador].getNombre() + " elige la casilla " + muestra);
                            retorno = i + "" + j;
                            return retorno;
                            }
                        }
                    }
                }
            }
        }
        if (jugadores[jugador].getDificultad() == 3) { // Dificultad IMPOSIBLE, posibilidad de acertar por suerte
            int suerte = generaEnteroAleatorio(0,100);
            if (suerte > 60) {
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if (!(jugadores[jugadorEne].getTablero().getCasillas()[i][j].isDescubierta()) && jugadores[jugadorEne].getTablero().getCasillas()[i][j].isOcupada()) {
                            suerte = generaEnteroAleatorio(0,100);
                            if(suerte > 70){
                            muestra += selecDesTrad(i + "" + j);
                            System.out.println(jugadores[jugador].getNombre() + " elige la casilla " + muestra);
                            retorno = i + "" + j;
                            return retorno;
                            }
                        }
                    }
                }
            }
        }
        if(!valido){
        do { // Elegir casilla ALEATORIA
            int coordX = generaEnteroAleatorio(0,9);
            int coordY = generaEnteroAleatorio(0,9);
            if (!(jugadores[jugadorEne].getTablero().getCasillas()[coordX][coordY].isDescubierta())) {
                if (!barcoVistoAlrededor(coordX + "" + coordY, jugadorEne)) {
                muestra += selecDesTrad(coordX + "" + coordY);
                System.out.println(jugadores[jugador].getNombre() + " elige la casilla " + muestra);
                retorno = coordX + "" + coordY;
                valido = true;
                }
            }
        } while (!valido);
        }
            
        return retorno;
    }
    
    public boolean barcoVistoAlrededor(String selec, int jugadorEne){
        int ancho = 3;
        int largo = 3;
        int coordX = selec.charAt(0) - '0';
        int coordY = selec.charAt(1) - '0';
        if (coordX == 0 || coordX == 9) {
            ancho--;
        }
        if (coordY == 0 || coordY == 9) {
            largo--;
        }
        if (coordX != 0) {
            coordX--;
        }
        if (coordY != 0) {
            coordY--;
        }
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < largo; j++) {
                if (jugadores[jugadorEne].getTablero().getCasillas()[coordX + i][coordY + j].isDescubierta() && jugadores[jugadorEne].getTablero().getCasillas()[coordX + i][coordY + j].isOcupada()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public int buscarAlrededor(String selec, int jugadorEne){
        int coordX = selec.charAt(0) - '0';
        int coordY = selec.charAt(1) - '0';
        
        if(coordX>0){
        if (jugadores[jugadorEne].getTablero().getCasillas()[coordX-1][coordY].isDescubierta() && jugadores[jugadorEne].getTablero().getCasillas()[coordX-1][coordY].isOcupada()) {
            // x - 1, izquierda
            return -2;
        }
        }
        
        if(coordX<9){
            if (jugadores[jugadorEne].getTablero().getCasillas()[coordX+1][coordY].isDescubierta() && jugadores[jugadorEne].getTablero().getCasillas()[coordX+1][coordY].isOcupada()) {
            // x + 1, derecha
            return 2;
        }
        }
        
        
        if(coordY>0){
        if (jugadores[jugadorEne].getTablero().getCasillas()[coordX][coordY-1].isDescubierta() && jugadores[jugadorEne].getTablero().getCasillas()[coordX][coordY-1].isOcupada()) {
            // y - 1, arriba
            return -1;
        }
        }
        
        if(coordY<9){
        if (jugadores[jugadorEne].getTablero().getCasillas()[coordX][coordY+1].isDescubierta() && jugadores[jugadorEne].getTablero().getCasillas()[coordX][coordY+1].isOcupada()) {
            // y + 1, abajo
            return 1;
        }
        }
        return 0;
    }
    
    public String selecCasilla(int jugador){
        if (jugador == 0) {
            System.out.println(jugadores[1].getTablero().toString(true, jugadores[1].getNombre()));
        }else{
            System.out.println(jugadores[0].getTablero().toString(true, jugadores[0].getNombre()));
        }
        System.out.println("Seleccione una casilla para atacar.");
        String selec = (String)introducirTexto("STRING");
        String selecTrad = jugadores[jugador].getTablero().traducirSeleccion(selec);
        if (selecTrad == "E") {
            return selecTrad;
        }
        return selecTrad;
    }
    
    public String selecDesTrad(String selec){
        String retorno = "";
        int coordX = selec.charAt(1) - '0';
            switch(coordX){
                case 0: retorno += "A";
                        break;
                case 1: retorno += "B";
                        break;
                case 2: retorno += "C";
                        break;
                case 3: retorno += "D";
                        break;
                case 4: retorno += "E";
                        break;
                case 5: retorno += "F";
                        break;
                case 6: retorno += "G";
                        break;
                case 7: retorno += "H";
                        break;
                case 8: retorno += "I";
                        break;
                case 9: retorno += "J";
                        break;
            }
            retorno += selec.charAt(0) - '0' + 1;
        return retorno;
    }
    
    public void esperar(int tiempo){
        
        try {	
                Thread.sleep(tiempo); 
        }catch (Exception F) {
	}
        
    }
    
    public Object introducirTexto(String introduccion){
        Scanner sc=new Scanner (System.in);
        boolean continuar = false;
        Object SelecFinal = 0;
        
        do{
        try{
            
            if (introduccion.compareTo("CHAR") == 0) {
                char SelecChar = sc.nextLine().charAt(0);
                String charAux = "" + SelecChar;
                charAux = charAux.toUpperCase();
                SelecFinal = charAux.charAt(0);
                
            }
            
            if (introduccion.compareTo("INT") == 0) {
                int SelecInt = Integer.parseInt(sc.nextLine());
                SelecFinal = SelecInt;
            }
            
            if (introduccion.compareTo("DOUBLE") == 0){
                double SelecDouble = Double.parseDouble(sc.nextLine());
                SelecFinal = SelecDouble;
            }
            
            if (introduccion.compareTo("STRING") == 0) {
                String SelecString = sc.nextLine();
                SelecFinal = SelecString;
            }
            continuar = false;
            Thread.sleep(100);
        }catch(Exception F){
            System.out.println("Introduzca un valor valido.");
            continuar = true;
        }
        }while(continuar);
        
        return SelecFinal;
    }
    
    public int generaEnteroAleatorio(int min, int max){
        int rango = max - min + 1;
        int valor = (int) (rango * Math.random()) + min;
        return valor;
    }
}
