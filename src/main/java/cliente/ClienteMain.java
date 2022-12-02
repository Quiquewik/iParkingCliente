package cliente;

import cliente.controller.UsuarioController;
import cliente.model.Usuario;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ClienteMain {
    public static RestTemplate restTemplate = new RestTemplate();
    public static void main(String[] args) {
        UsuarioController usuarioController = new UsuarioController();
        Usuario usuarioTest = new Usuario("pene","1234","33","falete","santana","unacalle","pepee@123",1);
        System.out.println(usuarioController.callDeleteUsuarioByDni(usuarioTest.getDni()));
    }
}
