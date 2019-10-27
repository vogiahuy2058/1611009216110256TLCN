package com.springboot.angular.coffeesystem.service.orderType;

import com.springboot.angular.coffeesystem.dto.OrderTypeDto;
import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.model.OrderType;

public interface OrderTypeService {
    ResponseDto createOrderType(OrderTypeDto orderTypeDto);
    ResponseDto getAllOrderType();
    ResponseDto deleteOrderType(Integer id);
    ResponseDto editOrderType(OrderTypeDto orderTypeDto);
    ResponseDto getOrderTypeById(Integer id);
    PagingResponseDto getAllOrderTypePaging(int page, int size, String sort, String sortColumn);
}
