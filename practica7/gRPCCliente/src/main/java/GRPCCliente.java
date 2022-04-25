import com.uac.gRPC.User.APIResponse;
import com.uac.gRPC.User.LoginRequest;
import com.uac.gRPC.userGrpc;
import com.uac.gRPC.userGrpc.userBlockingStub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
public class GRPCCliente {

	public static void main(String[] args) {
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090).usePlaintext().build();
		
		userBlockingStub userStub = userGrpc.newBlockingStub(channel);
		LoginRequest loginrequest = LoginRequest.newBuilder().setUsername("RAM").setPassword("RAM").build();
		APIResponse response = userStub.login(loginrequest);
		
		System.out.println(response.getResponsemessage());
		channel.shutdown();
	}

}
