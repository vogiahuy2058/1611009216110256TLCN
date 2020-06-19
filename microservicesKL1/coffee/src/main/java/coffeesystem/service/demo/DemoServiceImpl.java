package coffeesystem.service.demo;

import coffeesystem.dto.CustomerRequestDto;
import coffeesystem.dto.DemoDto;
import coffeesystem.dto.ResponseDto;
import coffeesystem.model.Demo;
import coffeesystem.repository.DemoRepository;
import coffeesystem.util.MapperObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DemoServiceImpl implements DemoService{
    @Autowired
    DemoRepository demoRepository;
    @Autowired
    MapperObject mapperObject;
    @Override
    public ResponseDto getDemoByLabelAndStatus(String label, String status) {
        List<Demo> demoList = demoRepository.findByLabelAndStatus(label, status);
        List<DemoDto> demoDtos = new ArrayList<>();
        demoList.forEach(element->{
            DemoDto demoDto = mapperObject.DemoEntityToDto(element);
            demoDtos.add(demoDto);
        });
        return new ResponseDto(HttpStatus.OK.value(), "All demo", demoDtos);
    }
}
