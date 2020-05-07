package Sensores;

import java.io.IOException;

import org.json.JSONException;

public interface GestionPagosYCalificacionesVerSensores {
    public int leerVale() throws IOException, JSONException;
    public int crearIDBandeja() throws IOException, JSONException;
    public int devolverBandeja() throws IOException, JSONException;
}
