package grp.dtop.dtopjava.servise;

import com.google.protobuf.Empty;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import grp.dtop.dtopjava.proto.APIServiceGrpc;
import grp.dtop.dtopjava.proto.Message;
import grp.dtop.dtopjava.vo.CommandVO;
import grp.dtop.dtopjava.vo.FetchRequestVO;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "{}";
    }

    public String getClusterMetric(FetchRequestVO[] fetchRequestVOArray) {
        Message.FetchRequestArrayMessage.Builder frmArrBuilder = Message.FetchRequestArrayMessage.newBuilder();
        for (FetchRequestVO fetchRequestVO : fetchRequestVOArray) {
            Message.FetchRequestMessage.Builder frmBuilder = Message.FetchRequestMessage.newBuilder();
            frmBuilder.setAddr(fetchRequestVO.getAddr());
            frmBuilder.addAllFutureArr(Arrays.asList(fetchRequestVO.getFutureArr()));
            frmBuilder.addAllParamArr(Arrays.asList(fetchRequestVO.getParamArr()));
            frmArrBuilder.addFetchRequestArr(frmBuilder);
        }
        Message.FetchReplyArrayMessage reply = stub.getClusterMetric(frmArrBuilder.build());
        try {
            return JsonFormat.printer().print(reply);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "{}";
    }

    public String getAggregatedVirtualMemInfo() {
        Message.FetchRequestArrayMessage.Builder frmArrBuilder = Message.FetchRequestArrayMessage.newBuilder();
        Message.FetchReplyArrayMessage reply = stub.getAggregatedVirtualMemInfo(frmArrBuilder.build());
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
                    .setAddr(commandVO.getAddr() == null ? "" : commandVO.getAddr())
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
