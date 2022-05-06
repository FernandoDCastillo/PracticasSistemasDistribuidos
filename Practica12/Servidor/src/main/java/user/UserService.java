package user;

import com.uac.grpc.User.APIResponse;
import com.uac.grpc.User.Resultado;
import com.uac.grpc.userGrpc.userImplBase;

import io.grpc.stub.StreamObserver;
public class UserService extends userImplBase{

	@Override
	public void operacion(Resultado request, StreamObserver<APIResponse> responseObserver) {
		
		
		int numUno = request.getNumeroUno();
		int numDos = request.getNumeroDos();
		int operacion = request.getOperacion();
		int resultado;
		
		APIResponse.Builder response = APIResponse.newBuilder();
		switch (operacion) {
			case 1:
				resultado= numUno+numDos;
				response.setRespuesta("El resultado es " + resultado);
                                System.out.println("Operacion realizada");
                                break;
                         case 2:
				resultado= numUno-numDos;
				response.setRespuesta("El resultado es " + resultado);
                                System.out.println("Operacion realizada");
                                break;
			case 3:
				resultado= numUno*numDos;
				response.setRespuesta("El resultado es " + resultado);
                                System.out.println("Operacion realizada");
                                break;
			case 4:
				resultado= numUno/numDos;
				response.setRespuesta("El resultado es " + resultado);
                                System.out.println("Operacion realizada");
                                break;	
		}
			
		
		responseObserver.onNext(response.build());
		responseObserver.onCompleted();
            }
}	
