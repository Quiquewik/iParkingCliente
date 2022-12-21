package cliente.logica;

import cliente.controller.UsuarioController;
import cliente.model.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LogicaUsuario {
    static Logger logger = LoggerFactory.getLogger(LogicaUsuario.class);

    static UsuarioController controller = new UsuarioController();
    public static Usuario loggin(String dni){
        return controller.callGetUsuarioByDni(dni);
    }

    public static Usuario singIn(Usuario user){
        logger.info(user.toString());
        return  controller.callAddUsuario(user);
    }

}
