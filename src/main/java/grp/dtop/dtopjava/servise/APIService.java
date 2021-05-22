package grp.dtop.dtopjava.servise;

import com.google.protobuf.Empty;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import grp.dtop.dtopjava.proto.APIServiceGrpc;
import grp.dtop.dtopjava.proto.Message;
import grp.dtop.dtopjava.vo.CommandVO;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class APIService {
    @GrpcClient("APIService")
    private APIServiceGrpc.APIServiceBlockingStub stub;

    public String getClusterStatus(String[] flags) {
        Message.StringArrayMessage.Builder samBuilder = Message.StringArrayMessage.newBuilder();
        if (flags != null) {
            for (String flag : flags) {
                samBuilder.addArr(flag);
            }
        }
        Message.ServerStatusArrayMessage reply = stub.getClusterStatus(samBuilder.build());
        try {
            return JsonFormat.printer().print(reply);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        return "{}";
    }

    public String getClusterMetric() {
        Message.FetchRequestMessage.Builder frmBuilder = Message.FetchRequestMessage.newBuilder();
        frmBuilder.addRequestArr("MEM_USAGE");
        frmBuilder.addRequestArr("MEM_PER_PROC");
        Message.FetchReplyArrayMessage reply = stub.getClusterMetric(frmBuilder.build());
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

    public String execClusterCommand(List<CommandVO> commandVOList) {
        Message.CommandArrayMessage.Builder camBuilder = Message.CommandArrayMessage.newBuilder();
        for (CommandVO commandVO : commandVOList) {
            camBuilder.addCommandArrBuilder()
                    .setAddr(commandVO.getAddr())
                    .setWorkerName(commandVO.getWorkerName())
                    .setCmdType(commandVO.getCmdType())
                    .build();
        }
        Message.StringArrayMessage reply = stub.execClusterCommand(camBuilder.build());
        try {
            return JsonFormat.printer().print(reply);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        return "{}";
    }
}
