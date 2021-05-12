package grp.dtop.dtopui.servise;

import com.google.protobuf.Empty;
import grp.dtop.dtopui.GRPCServiceGrpc;
import grp.dtop.dtopui.Message;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class TestService {

    @GrpcClient("static://127.0.0.1:8080")
    private GRPCServiceGrpc.GRPCServiceBlockingStub blockingStub;
    private static final Logger logger = Logger.getLogger(TestService.class.getName());

    public  void getAddr(String name){
        Message.StringMessage response;
        try{
            response = blockingStub.getAddr(Empty.newBuilder().build());
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return;
        }
        logger.info("Message from gRPC-Server: " + response.getVal());
    }
}
