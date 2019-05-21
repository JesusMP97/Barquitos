
package barquitos;

public class Sector {
    private int coordVer;
    private int coordHor;
    private boolean tocado;

    public Sector(int coordHor, int coordVer, boolean tocado) {
        this.coordHor = coordHor;
        this.coordVer = coordVer;
        this.tocado = tocado;
    }
    
    public Sector(){
        this.coordHor = 0;
        this.coordVer = 0;
        this.tocado = false;
    }

    public int getCoordVer() {
        return coordVer;
    }

    public int getCoordHor() {
        return coordHor;
    }

    public boolean isTocado() {
        return tocado;
    }
    
    public void setCoord(int coordX, int coordY){
        this.coordHor = coordX;
        this.coordVer = coordY;
    }

    public void setCoordVer(int coordVer) {
        this.coordVer = coordVer;
    }

    public void setCoordHor(int coordHor) {
        this.coordHor = coordHor;
    }

    public void setTocado(boolean tocado) {
        this.tocado = tocado;
    }
    
    
}
