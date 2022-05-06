import com.uac.grpc.User.APIResponse;
import com.uac.grpc.User.Resultado;
import com.uac.grpc.userGrpc;
import com.uac.grpc.userGrpc.userBlockingStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import javax.swing.JOptionPane;
public class GRPCCliente {

	public static void main(String[] args) {
                int numUno,numDos,operacion;
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090).usePlaintext().build();
		
		userBlockingStub userStub = userGrpc.newBlockingStub(channel);

              
                numUno = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el primer numero"));
     
                numDos = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el segundo numero"));

                operacion= Integer.parseInt(JOptionPane.showInputDialog("Escoja la operacion a realizar \n1:Suma \n2:Resta \n3:Multiplicacion \n4:Division "));

		Resultado resultado = Resultado.newBuilder().setNumeroUno(numUno).setNumeroDos(numDos).setOperacion(operacion).build();
		APIResponse response = userStub.operacion(resultado);
		
		JOptionPane.showMessageDialog(null,response.getRespuesta());
		channel.shutdown();
	}

}
