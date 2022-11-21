
package gm.com.mx.modelo;

public class Ticket {
    
    private int numTicket;
    private boolean estadoTicket;
    private String fecha;
    private String horaRegistro;
    private int total;
    private int numEmpleado;
    private int numMesa;

    public Ticket() {
    }

    public Ticket(int numTicket) {
        this.numTicket = numTicket;
    }

    public Ticket(boolean estadoTicket, String fecha, String horaRegistro, int total, int numEmpleado, int numMesa) {
        this.estadoTicket = estadoTicket;
        this.fecha = fecha;
        this.horaRegistro = horaRegistro;
        this.total = total;
        this.numEmpleado = numEmpleado;
        this.numMesa = numMesa;
    }

    public Ticket(int numTicket, boolean estadoTicket, String fecha, String horaRegistro, int total, int numEmpleado, int numMesa) {
        this.numTicket = numTicket;
        this.estadoTicket = estadoTicket;
        this.fecha = fecha;
        this.horaRegistro = horaRegistro;
        this.total = total;
        this.numEmpleado = numEmpleado;
        this.numMesa = numMesa;
    }

    public int getNumTicket() {
        return numTicket;
    }

    public void setNumTicket(int numTicket) {
        this.numTicket = numTicket;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHoraRegistro() {
        return horaRegistro;
    }

    public void setHoraRegistro(String horaRegistro) {
        this.horaRegistro = horaRegistro;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getNumEmpleado() {
        return numEmpleado;
    }

    public void setNumEmpleado(int numEmpleado) {
        this.numEmpleado = numEmpleado;
    }

    public int getNumMesa() {
        return numMesa;
    }

    public void setNumMesa(int numMesa) {
        this.numMesa = numMesa;
    }



    public boolean isEstadoTicket() {
        return estadoTicket;
    }

    public void setEstadoTicket(boolean estadoTicket) {
        this.estadoTicket = estadoTicket;
    }
    
    
}
