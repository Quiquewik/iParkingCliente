package cliente.controller;

import cliente.ClienteMain;
import cliente.model.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


public class UsuarioController {
    Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    private static final String ADD_USUARIO_URL = "http://localhost:8080/usuario/";
    private static final String GET_USUARIOS_URL = "http://localhost:8080/usuario/usuarios";
    private static final String GET_USUARIO_BY_ID_URL = "http://localhost:8080/usuario/usuario/";
    private static final String GET_USUARIO_BY_DNI_URL = "http://localhost:8080/usuario/usuario/dni/"; // String dni
    private static final String UPDATE_USUARIO_URL = "";
    private static final String DELETE_USUARIO_URL = "";

    public UsuarioController() {
    }

    public Usuario callAddUsuario(Usuario usuario){
        System.out.println(usuario.toString());
        Usuario returnUsuario = new Usuario();
        //if (callGetUsuarioByDni(usuario.getDni()) == null){
            returnUsuario = cliente.ClienteMain.restTemplate.postForObject(ADD_USUARIO_URL,usuario, Usuario.class);
            logger.debug("Usuario: "+usuario.getDni()+" added.");
        //}else{
            logger.debug("Ya existe el DNI introducido.");
       //}

        return returnUsuario;
    }

    public Usuario[] callGetUsuarios() {

        ResponseEntity<Usuario[]> arrayUsuarioResponse =
                cliente.ClienteMain.restTemplate.getForEntity(GET_USUARIOS_URL,
                        Usuario[].class);

        logger.debug(GET_USUARIOS_URL);

        return arrayUsuarioResponse.getBody();
    }

    public Usuario callGetUsuarioById(String id) {

        String urlEnvio = (GET_USUARIO_BY_ID_URL+id).trim();
        logger.debug(urlEnvio);

        return ClienteMain.restTemplate.getForObject(urlEnvio, Usuario.class);
    }

    public Usuario callGetUsuarioByDni(String dni) {

        String urlEnvio = (GET_USUARIO_BY_DNI_URL+dni).trim();
        logger.debug(urlEnvio);

        return ClienteMain.restTemplate.getForObject(urlEnvio, Usuario.class);
    }
}
