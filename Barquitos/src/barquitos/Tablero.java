
package barquitos;

import java.util.Scanner;

public class Tablero {
    private Barco[] barcos;
    private Casilla[][] casillas;

    public Tablero(Barco[] barcos, Casilla[][] casillas) {
        this.barcos = barcos;
        this.casillas = casillas;
    }
    
    public int generaEnteroAleatorio(int min, int max){
        int rango = max - min + 1;
        int valor = (int) (rango * Math.random()) + min;
        return valor;
    }
    
    public Tablero(){
        this.casillas = new Casilla[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.casillas[i][j] = new Casilla();
            }
        }
        
        this.barcos = new Barco[4];
        for (int i = 0; i < barcos.length; i++) {
            this.barcos[i] = new Barco(i+2);
        }
        
    }

    public Barco[] getBarcos() {
        return barcos;
    }

    public Casilla[][] getCasillas() {
        return casillas;
    }

    public void setBarcos(Barco[] barcos) {
        this.barcos = barcos;
    }

    public void setCasillas(Casilla[][] casillas) {
        this.casillas = casillas;
    }
    
    public void vaciarTablero(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                casillas[i][j].setOcupada(false);
                casillas[i][j].setBarcoCercano(false);
            }
        }
        for (int i = 0; i < barcos.length; i++) {
            barcos[i].vaciarBarco();
        }
    }
    
    public boolean comprobarEspacio(int coordX, int coordY, int tamano, boolean horizontal){
        if (horizontal) {
            if(coordX + tamano <= 10){
                if(coordX + tamano > 9){
                    tamano -= 1;
                }
                int auxTamano = 3;
                if (coordY == 9){
                    auxTamano -= 1;
                }
                
                tamano += 2;
                
                if (coordX != 0) {
                    coordX -= 1;
                }else{
                    tamano -= 1;
                }
                
                if (coordY != 0) {
                    coordY -= 1;
                }else{
                    auxTamano -= 1;
                }
                
                for (int i = 0; i < tamano; i++) {
                    for (int j = 0; j < auxTamano; j++) {
                        if (casillas[coordX + i][coordY + j].isOcupada()) {
                            return false;
                        }
                    }
                }
            }else{
                return false;
            }
        }else{
            if(coordY + tamano <= 10){
                if(coordY + tamano > 9){
                    tamano -= 1;
                }
                int auxTamano = 3;
                if (coordX == 9){
                    auxTamano -= 1;
                }
                
                tamano += 2;
                
                if (coordX != 0) {
                    coordX -= 1;
                }else{
                    auxTamano -= 1;
                }
                
                if (coordY != 0) {
                    coordY -= 1;
                }else{
                    tamano -= 1;
                }
                
                for (int i = 0; i < tamano; i++) {
                    for (int j = 0; j < auxTamano; j++) {
                        if (casillas[coordX + j][coordY + i].isOcupada()) {
                            return false;
                        }
                    }
                }
            }else{
                return false;
            }
        }
        return true;
    }
    
    public void colocarBarco(int coordX, int coordY, int tamano, boolean horizontal, int numBarco){
        if (horizontal) {
            for (int i = 0; i < tamano; i++) {
                casillas[coordX + i][coordY].setOcupada(true);
                casillas[coordX + i][coordY].setBarcoCercano(true);
                this.barcos[numBarco].getSectores()[i].setCoord(coordX + i, coordY);              
            }
            tamano += 2;
            int auxTamano = 3;
            if (coordX == 0 || coordX >= 9){
                tamano -= 1;
            }
            if (coordY == 0 || coordY == 9){
                auxTamano -= 1;
            }
            if (coordX + tamano > 9) {
                tamano -= 1;
            }
            if(coordX != 0){
                coordX -= 1;
            }
            if(coordY != 0){
                coordY -= 1;
            }
            
            for (int i = 0; i < tamano; i++) {
                for (int j = 0; j < auxTamano; j++) {
                    casillas[coordX + i][coordY + j].setBarcoCercano(true);
                }
            }
        }
        if (!horizontal) {
            for (int i = 0; i < tamano; i++) {
                casillas[coordX][coordY + i].setOcupada(true);
                this.barcos[numBarco].getSectores()[i].setCoord(coordX, coordY + i);                  
            }
            tamano += 2;
            int auxTamano = 3;
            if (coordX == 0 || coordX == 9){
                auxTamano -= 1;
            }
            if (coordY == 0 || coordY == 9){
                tamano -= 1;
            }
            if (coordY + tamano > 9) {
                tamano -= 1;
            }
            if(coordX != 0){
                coordX -= 1;
            }
            if(coordY != 0){
                coordY -= 1;
            }
            
            for (int i = 0; i < auxTamano; i++) {
                for (int j = 0; j < tamano; j++) {
                    casillas[coordX + i][coordY + j].setBarcoCercano(true);
                }
            }
        }
    }
    
    public void rellenaManual(String nomJug){
        String seleccion = "";
        System.out.println(this.toString(false, nomJug));
        for (int i = 0; i < barcos.length; i++) {
            boolean reintentar = false;
            do{
            System.out.println("Inserte la coordenada donde colocar el barco de tamaño " + (i + 2));
            seleccion = (String)introducirTexto("STRING");
            String selecTraducida = traducirSeleccion(seleccion);
            if (selecTraducida.compareTo("E") == 0) {
                do {
                    System.out.println("Inserte la coordenada donde colocar el barco de tamaño " + (i + 2));
                seleccion = (String)introducirTexto("STRING");
                selecTraducida = traducirSeleccion(seleccion);
                } while (selecTraducida.compareTo("E") == 0);
            }
            boolean horizontal = true;
            System.out.println("¿Desea colocar el barco en horizontal o en vertical? ( H / V )");
            char selecH = (char)introducirTexto("CHAR");
            if (selecH == 'H' || selecH == 'V') {
                if (selecH == 'H') {
                    horizontal = false;
                }
                if (selecH == 'V') {
                    horizontal = true;
                }                
            }else{
                do {
                    System.out.println("Error, introduzca H o V para seleccionar la horientacion del barco");
                    System.out.println("¿Desea colocar el barco en horizontal o en vertical? ( H / V )");
                    selecH = (char)introducirTexto("CHAR");
                } while (!(selecH == 'H' || selecH == 'V'));
                if (selecH == 'H') {
                    horizontal = false;
                }
                if (selecH == 'V') {
                    horizontal = true;
                } 
            }
            boolean valido = comprobarEspacio((int)selecTraducida.charAt(0) - '0', (int)selecTraducida.charAt(1) - '0', i+2, horizontal);
                if (valido) {
                    colocarBarco((int)selecTraducida.charAt(0) - '0', (int)selecTraducida.charAt(1) - '0', i+2, horizontal, i);
                    reintentar = false;
                    System.out.println(this.toString(false, nomJug));
                }else{
                    System.out.println("Posicion no valida, inserte otra");
                    reintentar = true;
                }
            }while(reintentar == true);
        }
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
    }
    
    public String traducirSeleccion(String seleccion){
        // Para usar su producto es: (int)selecTraducida.charAt(0) - '0', (int)selecTraducida.charAt(1) - '0'
        // Siendo estos 2 int sus coordenadas horizontal y vertical
        int num = 0;
        char letra = 'a';
        int numLetra = 0;
        if (seleccion.length() == 3) {
            if (seleccion.charAt(1) - '0' == 1 && seleccion.charAt(2) - '0' == 0) {
                num = 9;
            }else{
                System.out.println("Coordenada no valida, introduzca una coordenada valida");
                return "E";
            }
            letra = (char)seleccion.charAt(0);
        }else if(seleccion.length() == 2){
        try{
            letra = (char)seleccion.charAt(0);
            num = (int)seleccion.charAt(1) - '0';
            num -= 1;
        }catch(Exception F){
            System.out.println("Error, introduzca ls coordenada con el formato LetraNumero, ejemplo: A1");
            return "E";
        }
        }else{
            System.out.println("Error, introduzca ls coordenada con el formato LetraNumero, ejemplo: A1");
            return "E";
        }
        if(num < 0 || num > 10){
            System.out.println("Coordenada no valida, introduzca una coordenada valida");
            return "E";
        }
        
        switch(letra){
            case 'a': numLetra = 0;
                    break;
            case 'A': numLetra = 0;
                    break;
            case 'b': numLetra = 1;
                    break;
            case 'B': numLetra = 1;
                    break;
            case 'c': numLetra = 2;
                    break;
            case 'C': numLetra = 2;
                    break;
            case 'd': numLetra = 3;
                    break;
            case 'D': numLetra = 3;
                    break;
            case 'e': numLetra = 4;
                    break;
            case 'E': numLetra = 4;
                    break;
            case 'f': numLetra = 5;
                    break;
            case 'F': numLetra = 5;
                    break;
            case 'g': numLetra = 6;
                    break;
            case 'G': numLetra = 6;
                    break;
            case 'h': numLetra = 7;
                    break;
            case 'H': numLetra = 7;
                    break;
            case 'i': numLetra = 8;
                    break;
            case 'I': numLetra = 8;
                    break;
            case 'j': numLetra = 9;
                    break;
            case 'J': numLetra = 9;
                    break;
            default: System.out.println("Error, '" + letra + "' no es una casilla valida.");
                     return "E";
        }
        String retorno = num + "" + numLetra;
        return retorno;
    }
    
    public void rellenaAleatorio(){
        int reintentos = 0;
        for (int i = 0; i < 4; i++) {
            boolean valido = false;
            while(!valido){
            int coordX = generaEnteroAleatorio(0,9);
            int coordY = generaEnteroAleatorio(0,9);
            int horizAux = generaEnteroAleatorio(0,1);
            boolean horizontal = true;
            if (horizAux == 0) {
                horizontal = true;
            }else{
                horizontal = false;
            }
            
            valido = comprobarEspacio(coordX, coordY, barcos[i].getTamano(), horizontal);
            
                if (valido) {
                    colocarBarco(coordX, coordY, barcos[i].getTamano(), horizontal, i);
                    reintentos = 0;
                }else{
                    reintentos++;
                }
                if(reintentos >= 50){
                    vaciarTablero();
                    i = 0;
                }
            }
            
        }
    }
    
    public void actualizarBarcos(String selec){
        for (int i = 0; i < barcos.length; i++) {
            if (barcos[i].actualizarSector(selec) != -1) {
                barcos[i].setSectoresTocados(barcos[i].getSectoresTocados() + 1);
                if (barcos[i].getSectoresTocados() == barcos[i].getTamano()) {
                    barcos[i].setHundido(true);
                    System.out.println("¡Y hundido!");
                    esperar(2000);
                }
            }
        }
    }
    
    public int buscarBarco(String selec){
        for (int i = 0; i < barcos.length; i++) {
            if (barcos[i].buscaSector(selec) != -1) {
                return i;
            }
        }
        return -1;
    }

    public String toString(boolean enemigo, String nomJug) {
        String cad = "╔══════════════════╗\n";
        cad += "║      A B C D E F G H I J       ║   ▓ = Barco\n";
        for (int i = 0; i < 10; i++) {
            cad += "║";
            if (i < 9) {
                cad += " ";
            }
            cad += (i + 1) + " ";
            for (int j = 0; j < 10; j++) {
                
                if(!enemigo || casillas[i][j].isDescubierta()){
                if (casillas[i][j].isOcupada()) {
                    if(!barcos[buscarBarco(j+""+i)].isHundido()){
                        cad += "▓";
                    }else{
                        cad += "▒";
                    }
                    if (j == 9) {
                        cad += "    ";
                    }else{
                        cad += " ";
                    }
//                }else if(casillas[i][j].isBarcoCercano()){
//                    cad += "▒";
//                    if (j == 9) {
//                        cad += "    ";
//                    }else{
//                        cad += " ";
//                    }
//                }else if(!casillas[i][j].isOcupada() && !casillas[i][j].isBarcoCercano()){
                }else if(!casillas[i][j].isOcupada()){
                    cad += "░";
                    if (j == 9) {
                        cad += "    ";
                    }else{
                        cad += " ";
                    }
                }
                }else{
                    cad += " ?   ";
                    if (j == 9) {
                        cad += "    ";
                    }else{
                        cad += " ";
                    }
                }
            }
            
            cad += "║";
            if (i == 1) {
                cad += "   ▒ = Barco hundido";
            }
            if (i == 3) {
                cad += "   ░ = Agua";
            }
            
            if (i == 5){
                cad += "    ?    = Desconocido";
            }
            
            if (i == 7){
                cad += "   Tablero de " + nomJug;
            }
            cad += "\n";
        }
        cad += "╚══════════════════╝\n";
        return cad;
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
    
    public void esperar(int tiempo){
        
        try {	
                Thread.sleep(tiempo); 
        }catch (InterruptedException e) {
                e.printStackTrace();   
	}
        
    }
    
}
