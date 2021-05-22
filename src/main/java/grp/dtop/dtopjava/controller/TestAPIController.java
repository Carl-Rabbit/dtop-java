package grp.dtop.dtopjava.controller;

import grp.dtop.dtopjava.servise.TestAPIService;
import grp.dtop.dtopjava.vo.CommandVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TestAPIController {
//    private final TestAPIService testAPIService;
//
//    public TestAPIController(TestAPIService testAPIService) {
//        this.testAPIService = testAPIService;
//    }
//
//    @GetMapping("api/test/grpc/cluster_stats")
//    public String getClusterStatus(@RequestParam(value = "futures", required = false) boolean withFutures,
//                                  @RequestParam(value = "future_desc", required = false) boolean withFutureDesc) {
//        return testAPIService.getClusterStatus();
//    }
//
//    @GetMapping("api/test/grpc/cluster_aggregated_stats")
//    public String getAggregatedVirtualMemInfo() {
//        return testAPIService.getAggregatedVirtualMemInfo();
//    }
//
//    @PostMapping("api/test/grpc/cluster_exec_cmd")
//    public String execClusterCommand(@RequestBody List<CommandVO> commandVOList) {
//        return testAPIService.execClusterCommand(commandVOList);
//    }
}
