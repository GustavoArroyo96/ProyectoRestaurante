
package gm.com.mx.modelo;

public class HorasTrabajadas {
    private int iD;
    private String fechaEntrada;
    private String hEntrada;
    private String fechaSalida;
    private String hSalida;
    private boolean activoSistema;
    private int numEmpleado;

    public HorasTrabajadas(String hSalida) {
        this.hSalida = hSalida;
    }

    public HorasTrabajadas(String hSalida, int numEmpleado) {
        this.hSalida = hSalida;
        this.numEmpleado = numEmpleado;
    }

    public HorasTrabajadas(int numEmpleado){
        this.numEmpleado = numEmpleado;
    }
    
    public HorasTrabajadas(String fechaEntrada, String hEntrada, int numEmpleado) {
        this.fechaEntrada = fechaEntrada;
        this.hEntrada = hEntrada;
        this.numEmpleado = numEmpleado;
    }

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String gethEntrada() {
        return hEntrada;
    }

    public void sethEntrada(String hEntrada) {
        this.hEntrada = hEntrada;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String gethSalida() {
        return hSalida;
    }

    public void sethSalida(String hSalida) {
        this.hSalida = hSalida;
    }

    public boolean isActivoSistema() {
        return activoSistema;
    }

    public void setActivoSistema(boolean activoSistema) {
        this.activoSistema = activoSistema;
    }

    public int getNumEmpleado() {
        return numEmpleado;
    }

    public void setNumEmpleado(int numEmpleado) {
        this.numEmpleado = numEmpleado;
    }

}
