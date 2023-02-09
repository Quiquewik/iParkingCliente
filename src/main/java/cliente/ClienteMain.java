package cliente;

import cliente.view.FrmLogin;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@SpringBootApplication
public class ClienteMain {

    public static RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) throws IOException {
        new FrmLogin();
    }
}
