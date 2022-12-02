package cliente.controller;

import cliente.ClienteMain;
import cliente.model.Plaza;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

public class PlazaController {
    Logger logger = LoggerFactory.getLogger(PlazaController.class);

    private static final String ADD_PLAZA_URL = "http://localhost:8080/parking/";
    private static final String GET_PLAZAS_URL = "http://localhost:8080/parking/plazas";
    private static final String GET_PLAZA_BY_ID_URL = "http://localhost:8080/parking/getById/";
    private static final String GET_PLAZA_BY_TIPOPLAZA_URL = "http://localhost:8080/parking/getByTipo/";
    private static final String UPDATE_PLAZA_URL = "http://localhost:8080/parking/";
    private static final String DELETE_PLAZA_URL = "http://localhost:8080/parking/deleteById/";

    public PlazaController() {
    }

    public Plaza callAddPlaza(Plaza plazaIn){
        Plaza plazaOut = new Plaza();

        plazaOut = ClienteMain.restTemplate.postForObject(ADD_PLAZA_URL,plazaIn, Plaza.class);

        return plazaOut;
    }

    public Plaza[] callGetPlazas(){

        ResponseEntity<Plaza[]> plazasResponse = ClienteMain.restTemplate.getForEntity(GET_PLAZAS_URL, Plaza[].class);

        return plazasResponse.getBody();
    }

    public Plaza callGetPlazaById(String id) {

        String urlEnvio = (GET_PLAZA_BY_ID_URL+id).trim();
        logger.debug(urlEnvio);

        return ClienteMain.restTemplate.getForObject(urlEnvio, Plaza.class);
    }

    public Plaza callGetPlazaByTipo(String tipo) {

        String urlEnvio = (GET_PLAZA_BY_TIPOPLAZA_URL+tipo).trim();
        logger.debug(urlEnvio);

        return ClienteMain.restTemplate.getForObject(urlEnvio, Plaza.class);
    }

    public Plaza callUpdatePlazaById(Plaza plazaIn) {
        Plaza plazaOut;
        ResponseEntity<Plaza> response = ClienteMain.restTemplate.postForEntity(UPDATE_PLAZA_URL, plazaIn, Plaza.class);
        plazaOut = response.getBody();
        logger.debug("plazaIn: "+ plazaIn.getId()+" ha sido actualizado.");

        return plazaOut;
    }

    public boolean callDeletePlazaById(String id){
        Plaza plazaIn = callGetPlazaById(id);
        boolean resultado = false;
        if (null != plazaIn){
            ClienteMain.restTemplate.delete(DELETE_PLAZA_URL+plazaIn.getId());
            resultado = true;
        }
        return resultado;
    }

}
