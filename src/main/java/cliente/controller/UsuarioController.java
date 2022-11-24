package cliente.controller;

import cliente.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class UsuarioController {



    private static final String ADD_USUARIO_URL = "";
    private static final String GET_USUARIOS_URL = "http://localhost:8080/usuario/usuaros";
    private static final String GET_USUARIO_BY_ID_URL = "";
    private static final String GET_USUARIO_BY_DNI_URL = "";
    private static final String UPDATE_USUARIO_URL = "";
    private static final String DELETE_USUARIO_URL = "";

    public UsuarioController() {
    }

    public List<ResponseEntity<Usuario[]>> callGetUsuarios() {

//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//
//        HttpEntity<String> entity = new HttpEntity<>("parameters",headers);

        //ResponseEntity<String> result =  restTemplate.exchange(GET_USUARIOS_URL, HttpMethod.GET, entity,  String.class)
        ResponseEntity<Usuario[]> mov =
                restTemplate.getForEntity(
                        "http://services-movies/api/movies/",
                        Usuario[].class);
        List<ResponseEntity<Usuario[]>> m = Collections.singletonList(mov);

        return m;
    }

    @Autowired
    public RestTemplate restTemplate;
}
