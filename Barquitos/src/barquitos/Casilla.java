
package barquitos;

public class Casilla {
    private boolean ocupada;
    private boolean barcoCercano;
    private boolean descubierta;

    public Casilla() {
        this.ocupada = false;
        this.barcoCercano = false;
        this.descubierta = false;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    public boolean isBarcoCercano() {
        return barcoCercano;
    }

    public void setBarcoCercano(boolean barcoCercano) {
        this.barcoCercano = barcoCercano;
    }

    public boolean isDescubierta() {
        return descubierta;
    }

    public void setDescubierta(boolean descubierta) {
        this.descubierta = descubierta;
    }
    
}
