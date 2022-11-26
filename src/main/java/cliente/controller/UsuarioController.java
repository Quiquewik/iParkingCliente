package cliente.controller;

import cliente.ClienteMain;
import cliente.model.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


public class UsuarioController {
    Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    private static final String ADD_USUARIO_URL = "";
    private static final String GET_USUARIOS_URL = "http://localhost:8080/usuario/usuarios";
    private static final String GET_USUARIO_BY_ID_URL = "http://localhost:8080/usuario/usuario/";
    private static final String GET_USUARIO_BY_DNI_URL = "http://localhost:8080/usuario/dni/"; // String dni
    private static final String UPDATE_USUARIO_URL = "";
    private static final String DELETE_USUARIO_URL = "";

    public UsuarioController() {
    }


    public Usuario[] callGetUsuarios() {

        ResponseEntity<Usuario[]> arrayUsuarioResponse =
                cliente.ClienteMain.restTemplate.getForEntity(GET_USUARIOS_URL,
                        Usuario[].class);
        logger.info(GET_USUARIOS_URL);

        return arrayUsuarioResponse.getBody();
    }

    public Usuario callGetUsuarioById(String id) {
        String urlEnvio = (GET_USUARIO_BY_ID_URL+id).trim();
        logger.info(urlEnvio);

        return ClienteMain.restTemplate.getForObject(urlEnvio, Usuario.class);
    }


}
