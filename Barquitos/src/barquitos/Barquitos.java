
package barquitos;

import java.util.Scanner;

public class Barquitos {

    public static Object introducirTexto(String introduccion){
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
    
    public static String barquito(){
        String cad = "";
        cad +=
"                                      I                                        " + "\n" +
"                                   _.$I                                        " + "\n" +
"                                _.$#$$I                                        " + "\n" +
"                   I            $._   I                                        " + "\n" +
"                   I            _.$   I                                        " + "\n" +
"                   I     ...:::'''    I                                        " + "\n" +
"                   I                  IU                                       " + "\n" +
"                   I                  ==                                       " + "\n" +
"                   IU                 IU                                       " + "\n" +
"                   ==           =======U=======                                " + "\n" +
"                   IU           |      U      |                                " + "\n" +
"               =====U=====      |      U      |                                " + "\n" +
"               |    U    |     |       U       |                               " + "\n" +
"               |    U    |     |       U       |                               " + "\n" +
"              |     U     |   |        U        |                              " + "\n" +
"              |     U     |   |        U        |                              " + "\n" +
"             |      U      |  |        U        |         I                    " + "\n" +
"             |      U      | |         U         |    ---~I        //          " + "\n" +
"            |       U       ||         U         | -=~ qp I       //|          " + "\n" +
"            |       U       |         _U____      | }  >< I      // |          " + "\n" +
"           |       _U___    |___----~~/WWWW/~---__|/  ---~I     //  |          " + "\n" +
"           |__---~~YYYYY---__|         U||         ~~~    I    //   |          " + "\n" +
"                    U||    =============||============    I|| //    `.         " + "\n" +
"           ==========||====|            ||           |    ===//      |         " + "\n" +
"           |         ||    |            ||           |    I||/       |         " + "\n" +
"           |         ||    |            ZZ           |    /||        `.        " + "\n" +
"           |         ZZ    |            ZZ           |   //||         |        " + "\n" +
"           |         ZZ    |            ||           |  // ||         |        " + "\n" +
"    I      |         ||    |            ||           | //  ||         `.       " + "\n" +
" ===I===   |         ||    |            ||           |//   ||          |       " + "\n" +
" |  I  |   |         ||    |            ||           //    ||          |       " + "\n" +
" |  I  |   |         ||    |            ZZ          //_____||_-----~~~~~|      " + "\n" +
" |__I__|   |_________||____|            ZZ          /|     ||     !!!!!! |     " + "\n" +
"   .I                ||    |            ZZ           |     ||     ;  A I==+==  " + "\n" +
"   `bo.              ||    |____________||___________|   !!!!!!!!!    /        " + "\n" +
"   ===`bo.===        ||                 ||               ;   888    ,/         " + "\n" +
"   |     `boo.   TTTTTTTTT              ||   !!!!!!!!!!!!   A   A A I          " + "\n" +
"   |     &--`boo/        |______________LL   ;                 iiiiiii         " + "\n" +
"   |     (___        8888 !!!!!!!!!!!!!!!!---'8888888            /             " + "\n" +
"   |________|                                                   /              " + "\n" +
"             |            []   []   []   []   []   []   []     /               " + "\n" +
"              |                                              =||               " + "\n" +
"               |      soy un barquito                        =||               " + "\n" +
"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~     ";

        return cad;
    }
    
    public static void main(String[] args) {
        
        Partida partida = new Partida();
        System.out.println("Introduzca el numero de jugadores humanos: ");
        int numHum = (int)introducirTexto("INT");
        if (numHum < 0 || numHum > 2) {
            do {
                System.out.println("Numero de jugadores no valido, introduzca un valor entre 0 y 2.");
                numHum = (int)introducirTexto("INT");
            } while (numHum < 0 || numHum > 2);
        }
        for (int i = 0; i < numHum; i++) {
            partida.getJugadores()[i].setHumano(true);
            System.out.println("Indique el nombre del Jugador " + (i+1));
            String nomJug = (String)introducirTexto("STRING");
            partida.getJugadores()[i].setNombre(nomJug);
        }
        int numBots = 2 - numHum;
        int botNum = 1;
        for (int i = 0; i < numBots; i++) {
            partida.getJugadores()[botNum].setNombre("Jugador " + (botNum+1) + " [BOT]");
            System.out.println("Indique la dificultad de " + partida.getJugadores()[botNum].getNombre());
            System.out.println(" 1. Normal \n 2. Dificil \n 3. Imposible");
            int dif = (int)introducirTexto("INT");
                if(dif < 1 || dif > 3){
                    do {
                        System.out.println("Dificultad no valida.");
                        System.out.println("Indique la dificultad de " + partida.getJugadores()[botNum].getNombre());
                        System.out.println(" 1. Normal \n 2. Dificil \n 3. Muy dificil");
                        dif = (int)introducirTexto("INT");
                    } while (dif < 1 || dif > 3);
                }
                    partida.getJugadores()[botNum].setDificultad(dif);
                    String difAux = "";
                    if (dif == 1) {
                    difAux = "[Normal]";
                    }
                    if (dif == 2) {
                    difAux = "[Dificil]";
                    }
                    if (dif == 3) {
                    difAux = "[Imposible]";
                    }
                    partida.getJugadores()[botNum].setNombre(partida.getJugadores()[botNum].getNombre() + " " + difAux);
            botNum--;
        }
        System.out.println(barquito());
        partida.ronda();
        
        
    }
    
}
