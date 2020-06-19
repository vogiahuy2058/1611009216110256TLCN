package coffeesystem.service.demo;

import coffeesystem.dto.ResponseDto;

public interface DemoService {
    ResponseDto getDemoByLabelAndStatus(String label, String status);
}
