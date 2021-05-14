package grp.dtop.dtopjava.controller;

import grp.dtop.dtopjava.servise.TestGRPCService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestGRPCController {
    private final TestGRPCService testGRPCService;

    public TestGRPCController(TestGRPCService testGRPCService) {
        this.testGRPCService = testGRPCService;
    }

    @GetMapping("api/test/grpc/addr")
    public String getAddr() {
        return testGRPCService.getAddr();
    }

    @GetMapping("api/test/grpc/profile")
    public String queryProfiling() {
        return testGRPCService.queryProfiling();
    }
}
