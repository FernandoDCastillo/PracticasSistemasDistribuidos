import io.grpc.ServerBuilder;

import java.io.IOException;

import io.grpc.Server;
import java.util.concurrent.TimeUnit;
import user.UserService;

public class GRPCServer{
	
	public static void main(String[] args) throws IOException, InterruptedException{
		
			Server server = ServerBuilder.forPort(9090).addService(new UserService()).build();
			server.start();
			
			System.out.println("Server iniciado en "+ server.getPort());
			
			server.awaitTermination();
			 
	}
}