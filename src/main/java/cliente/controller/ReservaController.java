package cliente.controller;

import cliente.model.Reserva;
import org.springframework.web.client.RestTemplate;

public class ReservaController {

    private static final String ADD_RESERVA_URL = "http://localhost:8080/reserva/";
    private static final String GET_RESERVA_BY_ID_URL = "http://localhost:8080/reserva/getById/";
    private static final String GET_RESERVA_BY_DNI_URL = "http://localhost:8080/reserva/getReservaByDni/";
    private static final String GET_RESERVA_BY_MATRICULA_URL = "http://localhost:8080/reserva/getReservaByMatricula/";
    private static final String DELETE_RESERVA_URL = "http://localhost:8080/reserva/deleteById/";

    public static RestTemplate restTemplate = new RestTemplate();

    public ReservaController() {
    }

    public Reserva callAddReserva(Reserva reserva) {
        return restTemplate.postForObject(ADD_RESERVA_URL, reserva, Reserva.class);
    }

    public Reserva callGetReservaById(String id) {
        return restTemplate.getForObject(GET_RESERVA_BY_ID_URL + id, Reserva.class);
    }

    public Reserva[] callGetReservaByDni(String dni) {
        return restTemplate.getForEntity(GET_RESERVA_BY_DNI_URL + dni, Reserva[].class).getBody();
    }

    public Reserva callGetReservaByMatricula(String matricula) {
        return restTemplate.getForObject(GET_RESERVA_BY_MATRICULA_URL + matricula, Reserva.class);
    }

    public void callDeleteReservaById(String id) {
        Reserva reserva = callGetReservaById(id);
        if (null != reserva) {
            restTemplate.delete(DELETE_RESERVA_URL + reserva.getIdReserva());
        }
    }
}
