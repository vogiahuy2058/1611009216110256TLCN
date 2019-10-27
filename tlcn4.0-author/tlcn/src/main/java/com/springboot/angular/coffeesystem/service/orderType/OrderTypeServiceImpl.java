package com.springboot.angular.coffeesystem.service.orderType;

import com.springboot.angular.coffeesystem.dto.OrderTypeDto;
import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.exception.NotFoundException;
import com.springboot.angular.coffeesystem.model.Invoice;
import com.springboot.angular.coffeesystem.model.OrderType;
import com.springboot.angular.coffeesystem.repository.InvoiceRepository;
import com.springboot.angular.coffeesystem.repository.OrderTypeRepository;
import com.springboot.angular.coffeesystem.service.invoice.InvoiceService;
import com.springboot.angular.coffeesystem.util.MapperObject;
import com.springboot.angular.coffeesystem.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderTypeServiceImpl implements OrderTypeService{
    @Autowired
    MapperObject mapperObject;
    @Autowired
    OrderTypeRepository orderTypeRepository;
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    InvoiceService invoiceService;
    public ResponseDto createOrderType(OrderTypeDto orderTypeDto){
        OrderType orderType = this.mapperObject.OrderTypeDtoToEntity(orderTypeDto);
        orderTypeRepository.save(orderType);
        return new ResponseDto(HttpStatus.OK.value(), "create order type successful", null);
    }
    @Transactional
    public ResponseDto getAllOrderType(){
        List<OrderType> orderTypes =  orderTypeRepository.findAllByEnable(true);
        List<OrderTypeDto> orderTypeDtos = new ArrayList<>();
        orderTypes.forEach(element->{
            OrderTypeDto orderTypeDto =  mapperObject.OrderTypeEntityToDto(element);
            orderTypeDtos.add(orderTypeDto);
        });
        return new ResponseDto(HttpStatus.OK.value(), "All order type", orderTypeDtos);
    }
    @Transactional
    @Override
    public PagingResponseDto getAllOrderTypePaging(int page, int size, String sort, String sortColumn) {
        Pageable pageable = PageUtil.createPageable(page, size, sort, sortColumn);
        List<OrderTypeDto> orderTypeDtos = new ArrayList<>();
        Page<OrderType> orderTypePage = orderTypeRepository.findAllByEnable(true, pageable);
        orderTypePage.forEach(element->{
            OrderTypeDto orderTypeDto =  mapperObject.OrderTypeEntityToDto(element);
            orderTypeDtos.add(orderTypeDto);});
        Page<OrderTypeDto> orderTypeDtoPage = new PageImpl<>(orderTypeDtos, pageable,
                orderTypePage.getTotalElements() );
        return new PagingResponseDto<>(
                orderTypeDtoPage.getContent(), orderTypeDtoPage.getTotalElements(), orderTypeDtoPage.getTotalPages(),
                orderTypeDtoPage.getPageable());
    }
    @Transactional
    public ResponseDto getOrderTypeById(Integer id){
        OrderType orderType = orderTypeRepository.findByIdAndEnable(id, true)
                .orElseThrow(()-> new NotFoundException("Id not found"));
        OrderTypeDto orderTypeDto = mapperObject.OrderTypeEntityToDto(orderType);
        return new ResponseDto(HttpStatus.OK.value(), "Successful", orderTypeDto);
    }
    public ResponseDto deleteOrderType(Integer id){
        OrderType orderType = orderTypeRepository.findByIdAndEnable(id, true)
                .orElseThrow(()-> new NotFoundException("Id not found!"));
        //delete invoice when order typed was deleted
        List<Invoice> invoices = invoiceRepository.findByOrderTypeId(id);
        invoices.forEach(element->{
            invoiceService.deleteInvoice(element.getId());
        });
        orderType.setEnable(false);
        orderTypeRepository.save(orderType);
        return new ResponseDto(HttpStatus.OK.value(),
                "Delete order type successful", null);
    }
    public ResponseDto editOrderType(OrderTypeDto orderTypeDto){
        OrderType orderType = orderTypeRepository.findByIdAndEnable(orderTypeDto.getId(), true)
                .orElseThrow(()-> new NotFoundException("Id not found!"));
        orderType.setName(orderTypeDto.getName());
        orderTypeRepository.save(orderType);
        return new ResponseDto(HttpStatus.OK.value(), "Edit drink type successful", null);
    }

}
