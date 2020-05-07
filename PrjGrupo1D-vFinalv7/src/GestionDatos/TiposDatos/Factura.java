package GestionDatos.TiposDatos;

import java.util.Date;

public class Factura {
    private int idVale;
    private int idBandeja;
    private int idMenu;
    private String primero;
    private String segundo;
    private String postre;
    private String bebida;
    private Date fechaRecogida;
    private Date fechaDevolucion;

    public Factura(int idVale, int idBandeja, int idMenu, String primero, String segundo, String postre, String bebida, Date fechaRecogida, Date fechaDevolucion) {
        this.idVale = idVale;
        this.idBandeja = idBandeja;
        this.idMenu = idMenu;
        this.primero = primero;
        this.segundo = segundo;
        this.postre = postre;
        this.bebida = bebida;
        this.fechaRecogida = fechaRecogida;
        this.fechaDevolucion = fechaDevolucion;
    }

	public int getIdVale() {
		return idVale;
	}

	public int getIdBandeja() {
		return idBandeja;
	}

	public int getIdMenu() {
		return idMenu;
	}

	public String getPrimero() {
		return primero;
	}

	public String getSegundo() {
		return segundo;
	}

	public String getPostre() {
		return postre;
	}

	public String getBebida() {
		return bebida;
	}

	public Date getFechaRecogida() {
		return fechaRecogida;
	}

	public Date getFechaDevolucion() {
		return fechaDevolucion;
	}
    
    public void setFechaDevolucion(Date fecha) {
    	this.fechaDevolucion=fecha;
    }
}
