package cliente.controller;

import cliente.ClienteMain;
import cliente.model.Reserva;

public class ReservaController {

    private static final String ADD_RESERVA_URL = "http://localhost:8080/reserva/";
    private static final String GET_RESERVA_BY_ID_URL = "http://localhost:8080/reserva/getById/";
    private static final String GET_RESERVA_BY_DNI_URL = "http://localhost:8080/reserva/getReservaByDni/";
    private static final String GET_RESERVA_BY_MATRICULA_URL = "http://localhost:8080/reserva/getReservaByMatricula/";
    private static final String DELETE_RESERVA_URL = "http://localhost:8080/reserva/deleteById/";

    public ReservaController() {
    }

    public Reserva callAddReserva(Reserva reserva) {
        return ClienteMain.restTemplate.postForObject(ADD_RESERVA_URL, reserva, Reserva.class);
    }

    public Reserva callGetReservaById(String id) {
        return ClienteMain.restTemplate.getForObject(GET_RESERVA_BY_ID_URL + id, Reserva.class);
    }

    public Reserva[] callGetReservaByDni(String dni) {
        return ClienteMain.restTemplate.getForEntity(GET_RESERVA_BY_DNI_URL + dni, Reserva[].class).getBody();
    }

    public Reserva callGetReservaByMatricula(String matricula) {
        return ClienteMain.restTemplate.getForObject(GET_RESERVA_BY_MATRICULA_URL + matricula, Reserva.class);
    }

    public void callDeleteReservaById(String id) {
        Reserva reserva = callGetReservaById(id);
        if (null != reserva) {
            ClienteMain.restTemplate.delete(DELETE_RESERVA_URL + reserva.getIdReserva());
        }
    }
}
