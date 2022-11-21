
package gm.com.mx.modelo;

public class Mesas {

    private int numMesa;
    private boolean mesaDisponible;

    public Mesas(int numMesa) {
        this.numMesa = numMesa;
    }

    public Mesas(int numMesa, boolean mesaDisponible) {
        this.numMesa = numMesa;
        this.mesaDisponible = mesaDisponible;
    }

    public int getNumMesa() {
        return numMesa;
    }

    public void setNumMesa(int numMesa) {
        this.numMesa = numMesa;
    }

    public boolean isMesaDisponible() {
        return mesaDisponible;
    }

    public void setMesaDisponible(boolean mesaDisponible) {
        this.mesaDisponible = mesaDisponible;
    }
    
    
}
