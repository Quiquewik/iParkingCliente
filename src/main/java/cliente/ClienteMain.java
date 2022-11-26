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
        Usuario usuarioTest = new Usuario("quiquewik","1234","457830952B","enrique","santana","unacalle","pepe@123",1);
        System.out.println(usuarioController.callAddUsuario(usuarioTest));
    }
}
