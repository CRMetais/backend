package school.sptech.cr_metais.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.cr_metais.dto.Dashboard.DashboardResponseDto;
import school.sptech.cr_metais.service.DashboardService;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = "*")
@Tag(name = "Dashboard")
@SecurityRequirement(name = "Bearer")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @Operation(summary = "Retorna todos os dados para o dashboard Grafana")
    @GetMapping
    public ResponseEntity<DashboardResponseDto> getDashboard() {
        return ResponseEntity.ok(dashboardService.getDashboard());
    }
}