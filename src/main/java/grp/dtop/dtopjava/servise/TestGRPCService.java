package grp.dtop.dtopjava.servise;

import com.google.protobuf.Empty;
import grp.dtop.dtopjava.proto.Message;
import grp.dtop.dtopjava.proto.GRPCServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class TestGRPCService {
    @GrpcClient("GRPCService")
    private GRPCServiceGrpc.GRPCServiceBlockingStub stub;

    public String getAddr() {
        Message.StringMessage response = stub.getAddr(Empty.newBuilder().build());
        return response.getVal();
    }
}
