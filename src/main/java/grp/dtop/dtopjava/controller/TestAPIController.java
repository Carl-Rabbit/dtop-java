package grp.dtop.dtopjava.controller;

import grp.dtop.dtopjava.servise.TestAPIService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestAPIController {
    private final TestAPIService testAPIService;

    public TestAPIController(TestAPIService testAPIService) {
        this.testAPIService = testAPIService;
    }

    @GetMapping("api/test/grpc/cluster_stats")
    public String getClusterStatus(@RequestParam(value = "futures", required = false) boolean withFutures,
                                  @RequestParam(value = "future_desc", required = false) boolean withFutureDesc) {
        return testAPIService.getClusterStatus();
    }
}