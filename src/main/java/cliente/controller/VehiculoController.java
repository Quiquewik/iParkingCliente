package cliente.controller;

import cliente.ClienteMain;
import cliente.model.Vehiculo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class VehiculoController {

    private static final Logger logger = LoggerFactory.getLogger(VehiculoController.class);

    private static final String ADD_VEHICULO_URL = "http://localhost:8080/vehiculo/";
    private static final String GET_VEHICULOS_URL = "http://localhost:8080/vehiculo/vehiculos";
    private static final String GET_VEHICULO_BY_ID_URL = "http://localhost:8080/vehiculo/getById/";
    private static final String GET_VEHICULO_BY_MATRICULA_URL = "http://localhost:8080/vehiculo/getByMatricula/";
    private static final String UPDATE_VEHICULO_URL = "http://localhost:8080/vehiculo/";
    private static final String DELETE_VEHICULO_URL = "http://localhost:8080/vehiculo/deleteById/";
    public VehiculoController() {
    }

    public Vehiculo callAddVehiculo(Vehiculo vehiculo){
        Vehiculo returnVehiculo = new Vehiculo();
        if (callGetVehiculoByMatricula(vehiculo.getMatricula()) == null){
            returnVehiculo = cliente.ClienteMain.restTemplate.postForObject(ADD_VEHICULO_URL,vehiculo, Vehiculo.class);
            logger.debug("vehiculo: "+vehiculo.getMatricula()+" added.");
        }else{
            logger.debug("Ya existe el DNI introducido.");
        }

        return returnVehiculo;
    }

    public Vehiculo[] callGetVehiculos() {

        ResponseEntity<Vehiculo[]> arrayVehiculoResponse =
                cliente.ClienteMain.restTemplate.getForEntity(GET_VEHICULOS_URL,
                        Vehiculo[].class);

        return arrayVehiculoResponse.getBody();
    }

    public Vehiculo callGetVehiculoById(String id) {

        String urlEnvio = (GET_VEHICULO_BY_ID_URL+id).trim();
        logger.debug(urlEnvio);

        return ClienteMain.restTemplate.getForObject(urlEnvio, Vehiculo.class);
    }

    private Vehiculo callGetVehiculoByMatricula(String matricula) {

        String urlEnvio = (GET_VEHICULO_BY_MATRICULA_URL+matricula).trim();
        logger.debug(urlEnvio);

        return ClienteMain.restTemplate.getForObject(urlEnvio, Vehiculo.class);
    }

    public Vehiculo callUpdateVehiculoById(Vehiculo vehiculo) {
        Vehiculo updatedVehiculo = new Vehiculo();
        Vehiculo OldVehiculo = callGetVehiculoByMatricula(vehiculo.getMatricula());

        if(OldVehiculo != null && OldVehiculo.getMatricula() != null){
            vehiculo.setId(OldVehiculo.getId());
            ResponseEntity<Vehiculo> response = ClienteMain.restTemplate.postForEntity(UPDATE_VEHICULO_URL, vehiculo, Vehiculo.class);
            updatedVehiculo = response.getBody();
            logger.debug("vehiculo: "+ vehiculo.getMatricula()+" ha sido actualizado.");
        }else {
            logger.debug("El vehiculo no se ha actualizado");
        }

        return updatedVehiculo;
    }

    public boolean callDeleteVsuarioByMatricula(String matricula){
        Vehiculo vehiculo = callGetVehiculoByMatricula(matricula);
        boolean resultado = false;
        Map<String,String> params = new HashMap<>();
        if (null != vehiculo){
            params.put("id", vehiculo.getId());
            ClienteMain.restTemplate.delete(DELETE_VEHICULO_URL+params.get("id"));
            resultado = true;
        }

        return resultado;
    }

}
