package cliente.controller;

import cliente.ClienteMain;
import cliente.model.Parking;

public class ParkingController {

    private static final String GET_PARKING_URL = "http://localhost:8080/parking/getAll";
    private static final String GET_PARKING_BY_NOMBREPARKING_URL = "http://localhost:8080/parking/getByNombreParking/";

    private static final String UPDATE_PARKING_URL = "http://localhost:8080/parking/";

    public ParkingController() {
    }

    public Parking[] callGetParkings() {
        return ClienteMain.restTemplate.getForEntity(GET_PARKING_URL, Parking[].class).getBody();
    }

    public Parking callGetParkingByName(String nombre) {
        return ClienteMain.restTemplate.getForObject(GET_PARKING_BY_NOMBREPARKING_URL + nombre, Parking.class);
    }

    public void callUpdateParkingById(Parking parking) {
        Parking parkingOut = callGetParkingByName(parking.getNombre());

        if (null != parkingOut.getId()) {
            ClienteMain.restTemplate.postForEntity(UPDATE_PARKING_URL, parking, Parking.class);
        }

    }

}
