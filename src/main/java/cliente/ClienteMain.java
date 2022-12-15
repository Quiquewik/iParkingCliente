package cliente;

import cliente.controller.UsuarioController;
import cliente.logica.LogicaUsuario;
import cliente.model.Usuario;
import cliente.view.FrmLogin;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ClienteMain {
    public static RestTemplate restTemplate = new RestTemplate();
    public static void main(String[] args) {
        UsuarioController usuarioController = new UsuarioController();
        Usuario usuarioTest = new Usuario("pene","1234","3","falete","santana","unacalle","pepee@123",1);

        new FrmLogin();
    }
}
