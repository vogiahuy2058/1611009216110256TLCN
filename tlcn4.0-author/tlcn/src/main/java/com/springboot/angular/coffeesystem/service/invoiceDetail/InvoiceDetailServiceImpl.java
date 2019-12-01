package com.springboot.angular.coffeesystem.service.invoiceDetail;

import com.springboot.angular.coffeesystem.dto.InvoiceDetailDto;
import com.springboot.angular.coffeesystem.dto.InvoiceDetailResponseDto;
import com.springboot.angular.coffeesystem.dto.InvoiceResponseDto;
import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.exception.NotFoundException;
import com.springboot.angular.coffeesystem.model.Drink;
import com.springboot.angular.coffeesystem.model.Invoice;
import com.springboot.angular.coffeesystem.model.InvoiceDetail;
import com.springboot.angular.coffeesystem.model.embedding.InvoiceDetailId;
import com.springboot.angular.coffeesystem.repository.DrinkRepository;
import com.springboot.angular.coffeesystem.repository.InvoiceDetailRepository;
import com.springboot.angular.coffeesystem.repository.InvoiceRepository;
import com.springboot.angular.coffeesystem.util.MapperObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceDetailServiceImpl implements InvoiceDetailService {
    @Autowired
    MapperObject mapperObject;
    @Autowired
    InvoiceDetailRepository invoiceDetailRepository;
    @Autowired
    DrinkRepository drinkRepository;
    @Autowired
    InvoiceRepository invoiceRepository;
    public ResponseDto createInvoiceDetail(InvoiceDetailDto invoiceDetailDto){
        InvoiceDetail invoiceDetail = this.mapperObject.InvoiceDetailDtoToEntity(invoiceDetailDto);
        Drink drink = drinkRepository.findByIdAndEnable(invoiceDetailDto.getDrinkId(), true)
                .orElseThrow(()-> new NotFoundException("Drink not found"));
        Invoice invoice = invoiceRepository.findById(invoiceDetailDto.getInvoiceId())
                .orElseThrow(()-> new NotFoundException("Invoice not found"));
        InvoiceDetailId invoiceDetailId = new InvoiceDetailId();
        invoiceDetailId.setDrinkId(drink.getId());
        invoiceDetailId.setInvoiceId(invoice.getId());
        Integer idOld = invoiceDetailRepository.findMaxId();
        if(idOld == null){
            idOld = 0;
        }
        invoiceDetailId.setId(idOld+1);
        invoiceDetail.setDrink(drink);
        invoiceDetail.setInvoice(invoice);
        invoiceDetail.setInvoiceDetailId(invoiceDetailId);
        invoiceDetailRepository.save(invoiceDetail);
        return new ResponseDto(HttpStatus.OK.value(), "Successful", null);
    }

    public ResponseDto editInvoiceDetail(InvoiceDetailDto invoiceDetailDto){
        Drink drink = drinkRepository.findByIdAndEnable(invoiceDetailDto.getDrinkId(), true)
                .orElseThrow(()-> new NotFoundException("Drink not found"));
        Invoice invoice = invoiceRepository.findByIdAndEnable(invoiceDetailDto.getInvoiceId(), true)
                .orElseThrow(()-> new NotFoundException("Invoice not found"));
        InvoiceDetail invoiceDetail = invoiceDetailRepository.findByDrinkAndInvoiceAndInvoiceDetailIdId(
                drink, invoice, invoiceDetailDto.getId())
                .orElseThrow(()-> new NotFoundException("Invoice detail not found"));
        invoiceDetail.setUnitPrice(invoiceDetailDto.getUnitPrice());
        invoiceDetail.setPrice(invoiceDetailDto.getPrice());
        invoiceDetail.setAmount(invoiceDetailDto.getAmount());
        invoiceDetail.setDiscount(invoiceDetailDto.getDiscount());
        invoiceDetail.setNote(invoiceDetailDto.getNote());
        invoiceDetailRepository.save(invoiceDetail);
        return new ResponseDto(HttpStatus.OK.value(), "edit successful", null);
    }

    public ResponseDto editListInvoiceDetail(List<InvoiceDetailDto> invoiceDetailDtoList){
        invoiceDetailDtoList.forEach(element->{
            editInvoiceDetail(element);
        });
        return new ResponseDto(HttpStatus.OK.value(), "edit list successful", null);
    }
    public ResponseDto deleteInvoiceDetail(Integer invoiceId, Integer drinkId, Integer id){
        Drink drink = drinkRepository.findByIdAndEnable(drinkId, true)
                .orElseThrow(()-> new NotFoundException("Drink not found"));
        Invoice invoice = invoiceRepository.findByIdAndEnable(invoiceId, true)
                .orElseThrow(()-> new NotFoundException("Invoice not found"));
        InvoiceDetail invoiceDetail = invoiceDetailRepository.findByDrinkAndInvoiceAndInvoiceDetailIdId(
                drink, invoice, id)
                .orElseThrow(()-> new NotFoundException("Invoice detail not found"));
        invoiceDetailRepository.delete(invoiceDetail);
        return new ResponseDto(HttpStatus.OK.value(), "Delete invoice detail successful", null);
    }
    @Transactional
    public ResponseDto getInvoiceDetailByInvoiceId(Integer invoiceId){
        Invoice invoice = invoiceRepository.findByIdAndEnable(invoiceId, true)
                .orElseThrow(()-> new NotFoundException("Invoice not found"));
        List<InvoiceDetail> invoiceDetailList = invoiceDetailRepository.findByInvoice(invoice);
        List<InvoiceDetailResponseDto> invoiceDetailResponseDtos = new ArrayList<>();
        Integer serial = 0;
        for (InvoiceDetail element : invoiceDetailList) {
            InvoiceDetailResponseDto invoiceDetailResponseDto = mapperObject.InvoiceDetailEntityToDto(element);
            invoiceDetailResponseDto.setDrinkId(element.getInvoiceDetailId().getDrinkId());
            invoiceDetailResponseDto.setInvoiceId(element.getInvoiceDetailId().getInvoiceId());
            invoiceDetailResponseDto.setDrinkName(element.getDrink().getName());
            invoiceDetailResponseDto.setSerial(serial + 1);
            serial = serial + 1;
            invoiceDetailResponseDtos.add(invoiceDetailResponseDto);
        }
        return new ResponseDto(HttpStatus.OK.value(), "Successful", invoiceDetailResponseDtos);
    }
    @Transactional
    public ResponseDto getInvoiceDetailByID(Integer id){
        InvoiceDetail invoiceDetail = invoiceDetailRepository.findByInvoiceDetailIdId(id)
                .orElseThrow(()-> new NotFoundException("Id not found"));
        InvoiceDetailResponseDto invoiceDetailResponseDto = mapperObject.InvoiceDetailEntityToDto(invoiceDetail);
        invoiceDetailResponseDto.setDrinkId(invoiceDetail.getInvoiceDetailId().getDrinkId());
        invoiceDetailResponseDto.setInvoiceId(invoiceDetail.getInvoiceDetailId().getInvoiceId());
        invoiceDetailResponseDto.setDrinkName(invoiceDetail.getDrink().getName());
        invoiceDetailResponseDto.setSerial(1);
        return new ResponseDto(HttpStatus.OK.value(), "Successful", invoiceDetailResponseDto );
    }
}
