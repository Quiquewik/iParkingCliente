package cliente.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Plaza {

    @Id
    private String id;
    private int numeroPlaza;
    private int tipoPlaza;
    private boolean ocupado;
    private Date inicioAparcamiento;

    public Plaza(int numeroPlaza, int tipoPlaza, boolean ocupado, Date inicioAparcamiento) {
        this.numeroPlaza = numeroPlaza;
        this.tipoPlaza = tipoPlaza;
        this.ocupado = ocupado;
        this.inicioAparcamiento = inicioAparcamiento;
    }
}
