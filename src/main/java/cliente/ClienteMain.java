package cliente;

import cliente.view.FrmLogin;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ClienteMain {
    public static RestTemplate restTemplate = new RestTemplate();
    public static void main(String[] args) {
        new FrmLogin();
    }
}
