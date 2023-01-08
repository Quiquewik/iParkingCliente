package cliente.controller;

import cliente.ClienteMain;
import cliente.model.Parking;
import cliente.model.Plaza;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

public class ParkingController {
    Logger logger = LoggerFactory.getLogger(ParkingController.class);

    private static final String ADD_PARKING_URL = "http://localhost:8080/parking/";
    private static final String GET_PARKING_URL = "http://localhost:8080/parking/plazas";
    private static final String GET_PARKING_BY_ID_URL = "http://localhost:8080/parking/getById/";
    private static final String GET_PARKING_BY_NOMBREPARKING_URL = "http://localhost:8080/parking/getByNombreParking/";

    private static final String UPDATE_PARKING_URL = "http://localhost:8080/parking/";

    private static final String DELETE_PARKING_URL = "http://localhost:8080/parking/deleteById/";

    public ParkingController() {
    }

    public Parking callAddParking(Parking parking){
        return ClienteMain.restTemplate.postForObject(ADD_PARKING_URL,parking, Parking.class);
    }

    public Parking[] callGetParkings(){
        return ClienteMain.restTemplate.getForEntity(GET_PARKING_URL, Parking[].class).getBody();
    }

    public Parking callGetParkingById(String id) {
        return ClienteMain.restTemplate.getForObject(GET_PARKING_BY_ID_URL+id, Parking.class);
    }

    public Parking callGetParkingByName(String nombre) {
        return ClienteMain.restTemplate.getForObject(GET_PARKING_BY_NOMBREPARKING_URL+nombre, Parking.class);
    }

    public Parking callUpdateParkingById(Parking parking) {
        return ClienteMain.restTemplate.postForEntity(UPDATE_PARKING_URL, parking, Parking.class).getBody();
    }


    public boolean callDeletePlazaById(String id){
        Parking parking = callGetParkingById(id);
        boolean resultado = false;
        if (null != parking){
            ClienteMain.restTemplate.delete(DELETE_PARKING_URL +parking.getId());
            resultado = true;
        }
        return resultado;
    }

}
