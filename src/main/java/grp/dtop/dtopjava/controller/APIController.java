package grp.dtop.dtopjava.controller;

import grp.dtop.dtopjava.servise.APIService;
import grp.dtop.dtopjava.vo.CommandVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class APIController {
    private final APIService apiService;

    public APIController(APIService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("api/cluster_status")
    public String getClusterStatus(@RequestParam(required = false) String[] flags) {
        return apiService.getClusterStatus(flags);
    }

    @GetMapping("api/cluster_metric")
    public String getClusterMetric() {
        return apiService.getClusterMetric();
    }

    @GetMapping("api/cluster_aggregated_stats")
    public String getAggregatedVirtualMemInfo() {
        return apiService.getAggregatedVirtualMemInfo();
    }

    @PostMapping("api/cluster_exec_cmd")
    public String execClusterCommand(@RequestBody List<CommandVO> commandVOList) {
        return apiService.execClusterCommand(commandVOList);
    }
}
