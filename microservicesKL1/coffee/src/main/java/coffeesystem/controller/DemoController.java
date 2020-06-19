package coffeesystem.controller;

import coffeesystem.dto.ResponseDto;
import coffeesystem.service.demo.DemoService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/demo")
public class DemoController {
    @Autowired
    DemoService demoService;
    @GetMapping("/get-all")
    public ResponseEntity<ResponseDto> getAllDemoByLabelAndStatus(@RequestParam String label,
                                                                  @RequestParam String status){
        return  ResponseEntity.ok(this.demoService.getDemoByLabelAndStatus(label, status));
    }
}
