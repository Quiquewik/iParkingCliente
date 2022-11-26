package cliente;

import cliente.controller.UsuarioController;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;

@SpringBootApplication
public class ClienteMain {
    public static RestTemplate restTemplate = new RestTemplate();
    public static void main(String[] args) throws UnsupportedEncodingException {
        UsuarioController usuarioController = new UsuarioController();
        System.out.println(usuarioController.callGetUsuarioById("637dfdd231e06c46ab79626d"));
    }
}
