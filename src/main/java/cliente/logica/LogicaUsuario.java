package cliente.logica;

import cliente.controller.UsuarioController;
import cliente.model.Usuario;
import cliente.model.Vehiculo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LogicaUsuario {
    static Logger logger = LoggerFactory.getLogger(LogicaUsuario.class);

    static UsuarioController controller = new UsuarioController();
    public static Usuario loggin(String dni){
        return controller.callGetUsuarioByDni(dni);
    }

    public static Usuario singIn(Usuario user){
        logger.info(user.toString());
        return  controller.callAddUsuario(user);
    }

    public static Usuario[] getUsuarios(){
        Usuario[] listaUser = controller.callGetUsuarios();
        logger.info(Arrays.toString(listaUser));
        return listaUser;
    }

    public static Usuario getUsuarioById(String id){
        return controller.callGetUsuarioById(id);
    }

    public static void deleteUsuarioByDni(String dni){
        controller.callDeleteUsuarioByDni(dni);
    }

    public static Usuario updateUsuario(Usuario user){
        return controller.callUpdateUsuarioById(user);
    }

    public static DefaultTableModel getDefaultTableModel() {
        DefaultTableModel model =new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "Id", "Usuario", "DNI", "Nombre", "Apellidos", "Direcci\u00F3n", "Correo", "Tipo"
                }
        );
        Usuario[] listaUsers = getUsuarios();
        for (Usuario u: listaUsers){
            ArrayList<String> listaAtributos= new ArrayList<>();
            listaAtributos.add(u.getId());
            listaAtributos.add(u.getNombreUsuario());
            listaAtributos.add(u.getDni());
            listaAtributos.add(u.getNombre());
            listaAtributos.add(u.getApellidos());
            listaAtributos.add(u.getDireccion());
            listaAtributos.add(u.getCorreo());
            listaAtributos.add(u.getTipoUsuario()==1?"Admin":"Normal");
            model.addRow(listaAtributos.toArray());
        }
        return model;
    }

    public static Usuario updateVehiculos(Usuario user, Vehiculo vehiculoIn, int indexVehiculo, boolean add) {

        logger.info(user.toString() + "  vehiculo:" +vehiculoIn.toString());
        Vehiculo[] lista = user.getListaVehiculos();
        Vehiculo[] listaOut;
        if (add){
            if (null != lista){
                listaOut = new Vehiculo[lista.length+1];
                for (int i = 0; i < lista.length ; i++){
                    listaOut[i] = lista[i];
                }
                listaOut[listaOut.length-1] = vehiculoIn;
            }else{
                listaOut = new Vehiculo[]{vehiculoIn};
            }
            user.setListaVehiculos( listaOut);
        }else{
            if (null != lista && lista.length > 0){
                lista[indexVehiculo] = vehiculoIn;
                user.setListaVehiculos(lista);
            }else{
                user.setListaVehiculos(new Vehiculo[]{vehiculoIn});
            }
        }

        return user;
    }


}
