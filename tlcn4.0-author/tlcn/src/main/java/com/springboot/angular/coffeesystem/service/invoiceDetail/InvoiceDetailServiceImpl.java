package com.springboot.angular.coffeesystem.service.invoiceDetail;

import com.springboot.angular.coffeesystem.dto.InvoiceDetailDto;
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
//        invoiceDetail.setUnitPrice(drink.getPrice());
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
        InvoiceDetail invoiceDetail = invoiceDetailRepository.findByDrinkAndInvoice(drink, invoice)
                .orElseThrow(()-> new NotFoundException("Invoice detail not found"));
//        invoiceDetail.setUnitPrice(drink.getPrice());
        invoiceDetail.setPrice(invoiceDetailDto.getPrice());
        invoiceDetail.setAmount(invoiceDetailDto.getAmount());
        invoiceDetail.setDiscount(invoiceDetailDto.getDiscount());
        invoiceDetailRepository.save(invoiceDetail);
        return new ResponseDto(HttpStatus.OK.value(), "Successful", null);
    }

}
