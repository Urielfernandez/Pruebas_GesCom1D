package GestionDatos.TiposDatos;

public class Estadistica {
    private int idVale;
    private String plato;
    private int puntuacion;

    public Estadistica(int idVale, String plato, int puntuacion) {
        this.idVale = idVale;
        this.plato = plato;
        this.puntuacion = puntuacion;
    }

	public int getIdVale() {
		return idVale;
	}

	public String getPlato() {
		return plato;
	}

	public int getPuntuacion() {
		return puntuacion;
	}
    
    
}
