package grp.dtop.dtopjava.controller;

import grp.dtop.dtopjava.servise.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("api/test/echo")
    public String echo(@RequestParam(value = "str", required = false) String str) {
        return testService.echo(str);
    }
}
