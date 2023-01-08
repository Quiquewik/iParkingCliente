package cliente.logica;

import cliente.model.Usuario;
import cliente.model.Vehiculo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Arrays;

public class LogicaVehiculo {
    static Logger log = LoggerFactory.getLogger(LogicaVehiculo.class);
    public static DefaultTableModel getDefaultTableModel(Usuario user) {

        DefaultTableModel model = new DefaultTableModel(new Object[][]{}, new String[]{"Matricula", "Marca", "Modelo", "Plaza", "Tipo Vehiculo", "Estacionado"});
        Vehiculo[] listaVehiculos = user.getListaVehiculos();
        for (Vehiculo v : listaVehiculos) {
            ArrayList<String> listaAtributos = new ArrayList<>();
            listaAtributos.add(v.getMatricula());
            listaAtributos.add(v.getMarca());
            listaAtributos.add(v.getModelo());
            listaAtributos.add(v.getId_plaza());
            listaAtributos.add(v.getTipoVehiculo() + "");
            listaAtributos.add(v.isEstacionado() ? "Sí" : "No");
            model.addRow(listaAtributos.toArray());
        }
        log.info(Arrays.toString(user.getListaVehiculos()));
        return model;
    }


    public static Usuario eliminarVehiculo(Usuario user, int row) {
        Vehiculo[] listaIn = user.getListaVehiculos();
        Vehiculo[] listaOut = new Vehiculo[listaIn.length-1];

        for (int i = 0 ; i < listaIn.length-1 ; i++){
            if (i != row){
                listaOut[i] = listaIn[i];
            }
        }
        log.info(Arrays.toString(listaIn));
        log.info(Arrays.toString(listaOut));

        user.setListaVehiculos(listaOut);
        return user;
    }

    public static String[] getMatriculas(Usuario user) {
        String[] listaOut = new String[user.getListaVehiculos().length];
        Vehiculo[] listaVehiculos = user.getListaVehiculos();
        for (int i = 0 ; i <= listaVehiculos.length-1 ; i++){
            listaOut[i] = listaVehiculos[i].getMatricula();
        }

        log.info(Arrays.toString(listaOut));
        log.info(Arrays.toString(listaVehiculos));

        return listaOut;
    }
}
