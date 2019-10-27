package com.springboot.angular.coffeesystem.service.tableType;

import com.springboot.angular.coffeesystem.dto.PagingResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.dto.TableTypeDto;

public interface TableTypeService {
    ResponseDto createTableType(TableTypeDto tableTypeDto);
    ResponseDto getAllTableType();
    ResponseDto deleteTableType(Integer id);
    ResponseDto editTableType(TableTypeDto tableTypeDto);
    ResponseDto getTableTypeById(Integer id);
    PagingResponseDto getAllTableTypePaging(int page, int size, String sort, String sortColumn);
}
