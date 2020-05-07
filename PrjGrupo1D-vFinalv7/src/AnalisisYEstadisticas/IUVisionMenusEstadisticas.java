package AnalisisYEstadisticas;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

import org.json.JSONException;

import GestionDatos.TiposDatos.Plato;

public interface IUVisionMenusEstadisticas {
    public HashMap<String, Double> verPuntuacionesPlatosDia() throws IllegalArgumentException, IOException, JSONException;
    public int verOcupacionActual() throws IOException, JSONException, ParseException;
    public HashMap<Integer, Double> verHorasMasConcurridas() throws IOException, JSONException;
    public HashMap<String, Double> verRankginPlatos() throws IOException, JSONException;
    public HashMap<Plato, Integer> verPlatosMasComidos(Date fecha) throws IllegalArgumentException, IOException, JSONException, ExcepcionFechaAnalisis;
    public Double verDuracionMediaComidas() throws IOException, JSONException;
}
