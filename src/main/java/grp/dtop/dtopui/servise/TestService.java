package grp.dtop.dtopui.servise;

import com.google.protobuf.Empty;
import grp.dtop.dtopui.GRPCServiceGrpc;
import grp.dtop.dtopui.Message;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class TestService {

    @GrpcClient("cloud-grpc-server")
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
