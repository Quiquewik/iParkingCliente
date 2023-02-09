package cliente.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Plaza {

    private String numeroPlaza;
    private int tipoPlaza;
    private boolean ocupado;
    private boolean reservada;

}
