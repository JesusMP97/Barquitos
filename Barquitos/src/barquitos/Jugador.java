
package barquitos;

public class Jugador {
    private Tablero tablero;
    private boolean humano;
    private String nombre;
    private int dificultad;
    private int casillasIdentificadas;
    private int aciertos;

    public Jugador() {
        this.tablero = new Tablero();
        this.humano = false;
        this.nombre = "";
        this.dificultad = 1;
        this.casillasIdentificadas = 0;
        this.aciertos = 0;
    }

    public int getCasillasIdentificadas() {
        return casillasIdentificadas;
    }

    public void setCasillasIdentificadas(int casillasIdentificadas) {
        this.casillasIdentificadas = casillasIdentificadas;
    }

    public int getAciertos() {
        return aciertos;
    }

    public void setAciertos(int aciertos) {
        this.aciertos = aciertos;
    }
    
    public int getDificultad() {
        return dificultad;
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public boolean isHumano() {
        return humano;
    }

    public void setHumano(boolean humano) {
        this.humano = humano;
    }
    
    
}
