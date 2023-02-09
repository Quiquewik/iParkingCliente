package cliente.controller;

import cliente.model.Parking;
import org.springframework.web.client.RestTemplate;

public class ParkingController {

    private static final String GET_PARKING_URL = "http://localhost:8080/parking/getAll";
    private static final String GET_PARKING_BY_NOMBREPARKING_URL = "http://localhost:8080/parking/getByNombreParking/";

    private static final String UPDATE_PARKING_URL = "http://localhost:8080/parking/";
    public static RestTemplate restTemplate = new RestTemplate();

    public ParkingController() {
    }

    public Parking[] callGetParkings() {
        return restTemplate.getForEntity(GET_PARKING_URL, Parking[].class).getBody();
    }

    public Parking callGetParkingByName(String nombre) {
        return restTemplate.getForObject(GET_PARKING_BY_NOMBREPARKING_URL + nombre, Parking.class);
    }

    public void callUpdateParkingById(Parking parking) {
        Parking parkingOut = callGetParkingByName(parking.getNombre());

        if (null != parkingOut.getId()) {
            restTemplate.postForEntity(UPDATE_PARKING_URL, parking, Parking.class);
        }

    }

}
