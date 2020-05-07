package GestionDatos.TiposDatos;

import java.util.ArrayList;
import java.util.Date;

public class Menu {
	private int id;
	private ArrayList<String> bebidas;
	private Date fecha;
	private ArrayList<Plato> primeros;
	private ArrayList<Plato> segundos;
	private ArrayList<Plato> postres;

	public Menu(int id, Date fecha, ArrayList<Plato> primeros, ArrayList<Plato> segundos, ArrayList<Plato> postres, ArrayList<String> bebidas) {
		this.id=id;
		this.fecha = fecha;
		this.primeros = primeros;
		this.segundos = segundos;
		this.postres = postres;
		this.bebidas=bebidas;
	}
	
	public Menu(int id, Date fecha, ArrayList<Plato> primeros, ArrayList<Plato> segundos, ArrayList<Plato> postres) {
		this.id=id;
		this.fecha = fecha;
		this.primeros = primeros;
		this.segundos = segundos;
		this.postres = postres;
		this.bebidas=new ArrayList<String>();
	}
	
	public Menu(Date fecha, ArrayList<Plato> primeros, ArrayList<Plato> segundos, ArrayList<Plato> postres) {
		this.fecha = fecha;
		this.primeros = primeros;
		this.segundos = segundos;
		this.postres = postres;
		this.bebidas=new ArrayList<String>();
	}

	public ArrayList<Plato> getPrimeros() {
		return primeros;
	}
	
	public void setPrimeros(ArrayList<Plato> primeros) {
		this.primeros=primeros;
	}

	public ArrayList<Plato> getSegundos() {
		return segundos;
	}
	
	public void setSegundos(ArrayList<Plato> segundos) {
		this.segundos=segundos;
	}

	public ArrayList<Plato> getPostres() {
		return postres;
	}
	
	public void setPostres(ArrayList<Plato> postres) {
		this.postres=postres;
	}

	public Date getFecha() {
		return fecha;
	}

	public int getId() {
		return id;
	}

	public ArrayList<String> getBebidas() {
		return bebidas;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	
}
