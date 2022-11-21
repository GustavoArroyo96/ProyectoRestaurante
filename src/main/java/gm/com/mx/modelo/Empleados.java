
package gm.com.mx.modelo;

public class Empleados {
    private int numEmpleado;
    private String nombre;
    private String aPaterno;
    private String aMaterno;
    private String puesto;
    private int sucursal;

    public Empleados(int numEmpleado) {
        this.numEmpleado = numEmpleado;
    }

    public Empleados(int numEmpleado, String nombre, String aPaterno, String aMaterno, String puesto, int sucursal) {
        this.numEmpleado = numEmpleado;
        this.nombre = nombre;
        this.aPaterno = aPaterno;
        this.aMaterno = aMaterno;
        this.puesto = puesto;
        this.sucursal = sucursal;
    }

    public int getNumEmpleado() {
        return numEmpleado;
    }

    public void setNumEmpleado(int numEmpleado) {
        this.numEmpleado = numEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getaPaterno() {
        return aPaterno;
    }

    public void setaPaterno(String aPaterno) {
        this.aPaterno = aPaterno;
    }

    public String getaMaterno() {
        return aMaterno;
    }

    public void setaMaterno(String aMaterno) {
        this.aMaterno = aMaterno;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public int getSucursal() {
        return sucursal;
    }

    public void setSucursal(int sucursal) {
        this.sucursal = sucursal;
    }
    
    
}
