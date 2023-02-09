import cliente.view.FrmLogin;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class ClienteMain {

    public static void main(String[] args) throws IOException {
        new FrmLogin();
    }
}
