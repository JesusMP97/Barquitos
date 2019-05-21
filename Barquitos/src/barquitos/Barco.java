
package barquitos;

public class Barco {
    private int tamano;
    private int sectoresTocados;
    private Sector[] sectores;
    private boolean hundido;

    public Barco(int tamano, Sector[] sectores, boolean hundido) {
        this.tamano = tamano;
        this.sectores = sectores;
        this.hundido = hundido;
        this.sectoresTocados = 0;
    }
    
    public Barco(int tamano){
        this.tamano = tamano;
        this.sectores = new Sector[tamano];
        for (int i = 0; i < sectores.length; i++) {
            sectores[i] = new Sector();
        }
        this.hundido = false;
        this.sectoresTocados = 0;
    }
    
    public int actualizarSector(String selec){
        for (int i = 0; i < sectores.length; i++) {
            if (sectores[i].getCoordHor() == selec.charAt(0) - '0' && sectores[i].getCoordVer() == selec.charAt(1) - '0') {
                sectores[i].setTocado(true);
                return i;
            }
        }
        return -1;
    }
    
    public int buscaSector(String selec){
        for (int i = 0; i < sectores.length; i++) {
            if (sectores[i].getCoordHor() == selec.charAt(1) - '0' && sectores[i].getCoordVer() == selec.charAt(0) - '0') {
                return i;
            }
        }
        return -1;
    }

    public int getTamano() {
        return tamano;
    }

    public Sector[] getSectores() {
        return sectores;
    }

    public boolean isHundido() {
        return hundido;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    public void setSectores(Sector[] sectores) {
        this.sectores = sectores;
    }

    public void setHundido(boolean hundido) {
        this.hundido = hundido;
    }
    
    public void vaciarBarco(){
        this.hundido = false;
        for (int i = 0; i < sectores.length; i++) {
            sectores[i].setCoordHor(0);
            sectores[i].setCoordVer(0);
            sectores[i].setTocado(false);
        }
    }

    public int getSectoresTocados() {
        return sectoresTocados;
    }
    
    public void setSectoresTocados(int sectoresTocados) {
        this.sectoresTocados = sectoresTocados;
    }
    
    
    
}
