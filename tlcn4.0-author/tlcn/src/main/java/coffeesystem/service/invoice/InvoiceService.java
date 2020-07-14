package coffeesystem.service.invoice;

import coffeesystem.dto.InvoiceRequestDto;
import coffeesystem.dto.PagingResponseDto;
import coffeesystem.dto.ResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

import java.time.LocalDate;

public interface InvoiceService {
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CASHIER', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto getAllInvoiceByStatus(Integer status);
    ResponseDto getAllInvoiceByStatusAndIdBranchShop(Integer status, Integer idBranchShop);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CASHIER', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto getAllInvoiceStatus0();
    ResponseDto getAllInvoiceStatus0ByBranchShop(Integer idBranchShop);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CASHIER', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto createInvoice(InvoiceRequestDto invoiceRequestDto);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CASHIER', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto getInvoiceById(Integer id);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto editInvoice(InvoiceRequestDto invoiceRequestDto);
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto deleteInvoice(Integer id);
    ResponseDto deleteInvoiceStatus0();
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CASHIER', 'ROLE_MANAGE_ALL_BRANCH')")
    PagingResponseDto getAllInvoicePaging(int page, int size, String sort, String sortColumn);
    //    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CASHIER', 'ROLE_MANAGE_ALL_BRANCH')")
    PagingResponseDto getAllInvoiceStatus2Paging(int page, int size, String sort, String sortColumn);
    PagingResponseDto getAllInvoiceStatus2PagingByBranchShop(int page, int size, String sort,
                                                             String sortColumn, Integer idBranchShop);
    //    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CASHIER', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto getAllInvoiceByFilter(String fromDate, String toDate, Integer branchShopId);
    PagingResponseDto getAllInvoiceDateToDateByBranchShopPaging(int page, int size, String sort, String sortColumn,
                                                    String fromDate, String toDate, Integer branchShopId);
    public PagingResponseDto getAllInvoiceByFilterPaging(int page, int size, String sort, String sortColumn,
                                                         String fromDate, String toDate, Integer branchShopId);
    //    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CASHIER', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto getFullInvoiceById(Integer InvoiceId);
    PagingResponseDto getSalesStatistics(int page, int size, String sort,
                                         String sortColumn,String fromDate, String toDate);
    //    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_BRANCH_MANAGER', 'ROLE_CASHIER', 'ROLE_MANAGE_ALL_BRANCH')")
    ResponseDto getMaxIdInvoice();
    ResponseDto updateAmountMaterialUsed(Integer invoiceId);

}
