
package gm.com.mx.modelo;

public class Productos {
    
    private int codProducto;
    private String seccionProducto;
    private String nomProducto;
    private int preProducto;

    public Productos(int codProducto) {
        this.codProducto = codProducto;
    }

    public Productos(String seccionProducto, String nomProducto, int preProducto) {
        this.seccionProducto = seccionProducto;
        this.nomProducto = nomProducto;
        this.preProducto = preProducto;
    }

    public Productos(int codProducto, String seccionProducto, String nomProducto, int preProducto) {
        this.codProducto = codProducto;
        this.seccionProducto = seccionProducto;
        this.nomProducto = nomProducto;
        this.preProducto = preProducto;
    }

    public int getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(int codProducto) {
        this.codProducto = codProducto;
    }

    public String getSeccionProducto() {
        return seccionProducto;
    }

    public void setSeccionProducto(String seccionProducto) {
        this.seccionProducto = seccionProducto;
    }

    public String getNomProducto() {
        return nomProducto;
    }

    public void setNomProducto(String nomProducto) {
        this.nomProducto = nomProducto;
    }

    public int getPreProducto() {
        return preProducto;
    }

    public void setPreProducto(int preProducto) {
        this.preProducto = preProducto;
    }
    
    
}
