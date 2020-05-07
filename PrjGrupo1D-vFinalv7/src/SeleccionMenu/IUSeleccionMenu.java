package SeleccionMenu;

import java.io.IOException;
import java.text.ParseException;

import org.json.JSONException;

import GestionDatos.TiposDatos.Factura;
import GestionDatos.TiposDatos.Plato;

public interface IUSeleccionMenu {
    public void verPlatosMenuDia() throws IOException, JSONException;
    public Factura seleccionarPlatos(Plato  primero, Plato segundo, Plato postre, String bebida) throws IllegalArgumentException, IOException, JSONException, ParseException, InterruptedException;
}
