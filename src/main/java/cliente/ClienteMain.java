package cliente;

import cliente.controller.UsuarioController;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ClienteMain {
    @Bean
    public RestTemplate getresttemplate() {
        return new RestTemplate();
    }
    public static void main(String[] args) {
        UsuarioController usuarioController = new UsuarioController();
        System.out.println(usuarioController.callGetUsuarios());
    }
}
