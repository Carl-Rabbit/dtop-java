package grp.dtop.dtopjava.servise;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import grp.dtop.dtopjava.proto.APIServiceGrpc;
import grp.dtop.dtopjava.proto.Message;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class TestAPIService {
    @GrpcClient("APIService")
    private APIServiceGrpc.APIServiceBlockingStub stub;

    public String getClusterStatus() {
        Message.FetchRequestMessage.Builder frmBuilder = Message.FetchRequestMessage.newBuilder();
        frmBuilder.addRequestArr("MEM_USAGE");
        frmBuilder.addRequestArr("MEM_PER_PROC");
        Message.FetchReplyArrayMessage reply = stub.getStats(frmBuilder.build());
        try {
            return JsonFormat.printer().print(reply);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        return "{}";
    }

    public String getAggregatedVirtualMemInfo() {
        Message.FetchRequestMessage.Builder frmBuilder = Message.FetchRequestMessage.newBuilder();
        Message.FetchReplyArrayMessage reply = stub.getAggregatedVirtualMemInfo(frmBuilder.build());
        try {
            return JsonFormat.printer().print(reply);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        return "{}";
    }
}
