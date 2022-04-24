package user;

import com.uac.gRPC.User.APIResponse;
import com.uac.gRPC.User.Empty;
import com.uac.gRPC.User.LoginRequest;
import com.uac.gRPC.userGrpc.userImplBase;

import io.grpc.stub.StreamObserver;

public class UserService extends userImplBase{

	@Override
	public void login(LoginRequest request, StreamObserver<APIResponse> responseObserver) {
		System.out.println("Dentro del login");
		String username = request.getUsername();
		String password = request.getPassword();
		
		APIResponse.Builder response = APIResponse.newBuilder();
		if(username.equals(password)) {
			response.setResponseCode(0).setResponsemessage("Exito");
		}else {
			response.setResponseCode(100).setResponsemessage("contraseña incorrecta");
		}
		
		responseObserver.onNext(response.build());
		responseObserver.onCompleted();
	}

	@Override
	public void logout(Empty request, StreamObserver<APIResponse> responseObserver) {
		// TODO Auto-generated method stub
		super.logout(request, responseObserver);
	}
	
}