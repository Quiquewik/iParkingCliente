package cliente.logica;

import cliente.controller.UsuarioController;
import cliente.model.Usuario;

import java.util.Objects;

public class LogicaUsuario {
    static UsuarioController controller = new UsuarioController();
    public static Usuario loggin(String dni, String pass){

        Usuario user = controller.callGetUsuarioByDni(dni);

        return user;
    }
}
