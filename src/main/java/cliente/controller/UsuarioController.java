package cliente.controller;

import cliente.ClienteMain;
import cliente.model.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;


public class UsuarioController {
    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    private static final String ADD_USUARIO_URL = "http://localhost:8080/usuario/";
    private static final String GET_USUARIOS_URL = "http://localhost:8080/usuario/usuarios";
    private static final String GET_USUARIO_BY_ID_URL = "http://localhost:8080/usuario/getById/";
    private static final String GET_USUARIO_BY_DNI_URL = "http://localhost:8080/usuario/getByDni/";
    private static final String UPDATE_USUARIO_URL = "http://localhost:8080/usuario/";
    private static final String DELETE_USUARIO_URL = "http://localhost:8080/usuario/deleteById/";

    public UsuarioController() {
    }

    public Usuario callAddUsuario(Usuario usuario){
        Usuario returnUsuario = new Usuario();
        if (callGetUsuarioByDni(usuario.getDni()) == null){
            returnUsuario = cliente.ClienteMain.restTemplate.postForObject(ADD_USUARIO_URL,usuario, Usuario.class);
            logger.debug("Usuario: "+usuario.getDni()+" added.");
        }else{
            logger.debug("Ya existe el DNI introducido.");
        }

        return returnUsuario;
    }

    public Usuario[] callGetUsuarios() {

        ResponseEntity<Usuario[]> arrayUsuarioResponse =
                cliente.ClienteMain.restTemplate.getForEntity(GET_USUARIOS_URL,
                        Usuario[].class);

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

    public Usuario callUpdateUsuarioById(Usuario usuario) {
        Usuario updatedUsuario = new Usuario();
        Usuario OldUsuario = callGetUsuarioByDni(usuario.getDni());

        if(OldUsuario != null && OldUsuario.getDni() != null){
            usuario.setId(OldUsuario.getId());
            ResponseEntity<Usuario> response = ClienteMain.restTemplate.postForEntity(UPDATE_USUARIO_URL, usuario, Usuario.class);
            updatedUsuario = response.getBody();
            logger.debug("usuario: "+ usuario.getDni()+" ha sido actualizado.");
        }else {
            logger.debug("El usuario no se ha actualizado");
        }

        return updatedUsuario;
    }

    public boolean callDeleteUsuarioByDni(String dni){
        Usuario usuario = callGetUsuarioByDni(dni);
        boolean resultado = false;
        Map<String,String> params = new HashMap<String,String>();
        if (null != usuario){
            params.put("id", usuario.getId());
            ClienteMain.restTemplate.delete(DELETE_USUARIO_URL+params.get("id"));
            resultado = true;
        }

        return resultado;
    }
}
