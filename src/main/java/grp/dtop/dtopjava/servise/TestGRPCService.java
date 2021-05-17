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

    public String queryProfiling() {
        Message.FetchRequestMessage request = Message.FetchRequestMessage.newBuilder()
                .addRequestArr("MEM_USAGE")
                .addRequestArr("MEM_PER_PROC")
                .addParamArr("")
                .addParamArr("param1")
                .build();
        Message.FetchReplyMessage reply = stub.profile(request);
        return reply.getMemUsageMessage().toString();
    }

    public String getServerStatus(boolean withFutures, boolean withFutureDesc) {
        Message.StringArrayMessage.Builder samBuilder = Message.StringArrayMessage.newBuilder();
        if (withFutures) {
            samBuilder.addArr("-wf");       // --with_futures
        }
        if (withFutureDesc) {
            samBuilder.addArr("-wfd");
        }
        Message.ServerStatusMessage reply = stub.getServerStatus(samBuilder.build());
        System.out.println(reply.toString());
        return reply.toString();
    }
}
