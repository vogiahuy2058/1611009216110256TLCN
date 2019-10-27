package com.springboot.angular.coffeesystem.util;

import com.springboot.angular.coffeesystem.dto.*;
import com.springboot.angular.coffeesystem.model.*;
import com.springboot.angular.coffeesystem.dto.DrinkDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface MapperObject {
    @Mapping(target = "name",source = "name")
    RoleDto RoleEntityToRoleDTO(Role role);
    @Mapping(target = "name",source = "name")
    Role RoleDtoToEntity(RoleDto roleDto);

    @Mapping(target="idEmployee",source = "employee.id")
    AccountResponseDto AccountEntityToAccountResponseDto(Account account);

    @Mapping(target="drinkType",source = "drinkType.name")
    DrinkDto DrinkEntityToDrinkDTO(Drink drink);

    @Mapping(target="drinkType.name",source = "drinkType")
    Drink DrinkDTOToDrinkEntity(DrinkDto drinkDto);

    CustomerType CustomerTypeDtoToEntity(CustomerTypeDto customerTypeDto);
    CustomerTypeDto CustomerTypeEntityToDto(CustomerType customerType);

    DrinkType DrinkTypeDtoToEntity(DrinkTypeDto drinkTypeDto);
    DrinkTypeDto DrinkTypeEntityToDto(DrinkType drinkType);

    EmployeeType EmployeeDtoToEntity(EmployeeTypeDto employeeTypeDto);
    EmployeeTypeDto EmployeeTypeEntityToDto(EmployeeType employeeType);

    OrderType OrderTypeDtoToEntity(OrderTypeDto orderTypeDto);
    OrderTypeDto OrderTypeEntityToDto(OrderType orderType);

    TableType TableTypeDtoToEntity(TableTypeDto tableTypeDto);
    TableTypeDto TableTypeEntityToDto(TableType tableType);

    MaterialType MaterialTypeDtoToEntity(MaterialTypeDto materialTypeDto);
    MaterialTypeDto MaterialTypeEntityToDto(MaterialType materialType);

    Supplier SupplierDtoToEntity(SupplierDto supplierDto);
    SupplierDto SupplierEntityToDto(Supplier supplier);

    @Mapping(target="branchShop.name",source = "branchShop")
    @Mapping(target="employeeType.name",source = "employeeType")
    Employee EmployeeDtoToEntity(EmployeeRequestDto employeeRequestDto);

    @Mapping(target="branchShop",source = "branchShop.name")
    @Mapping(target="employeeType",source = "employeeType.name")
    EmployeeResponseDto EmployeeEntityToDto(Employee employee);

    BranchShop BranchShopDtoToEntity(BranchShopDto branchShopDto);
    BranchShopDto BranchShopEntityToDto(BranchShop branchShop);

    @Mapping(target="customerType.name",source = "customerType")
    Customer CustomerDtoToEntity(CustomerRequestDto customerRequestDto);

    @Mapping(target="customerType",source = "customerType.name")
    @Mapping(target="birthDay",source = "birthDay")
    CustomerResponseDto CustomerEntityToDto(Customer customer);

    @Mapping(target="customerType",source = "customerType.name")
    CustomerRequestDto CustomerEntityToDt1(Customer customer);

    @Mapping(target="customer.phone",source = "customerPhone")
    @Mapping(target="branchShop.name",source = "branchShop")
    @Mapping(target="coffeeTable.name",source = "coffeeTable")
    @Mapping(target="orderType.name",source = "orderType")
    Invoice InvoiceDtoToEntity(InvoiceRequestDto invoiceRequestDto);

    @Mapping(target="customerPhone",source = "customer.name")
    @Mapping(target="customerName",source = "customer.phone")
    @Mapping(target="branchShop",source = "branchShop.name")
    @Mapping(target="coffeeTable",source = "coffeeTable.name")
    @Mapping(target="orderType",source = "orderType.name")
    InvoiceResponseDto InvoiceEntityToDto(Invoice invoice);

    @Mapping(target="invoice.id",source = "invoiceId")
    @Mapping(target="drink.id",source = "drinkId")
    InvoiceDetail InvoiceDetailDtoToEntity(InvoiceDetailDto invoiceDetailDto);

    @Mapping(target="invoiceId",source = "invoice.id")
    @Mapping(target="drinkId",source = "drink.id")
    InvoiceDetailDto InvoiceDetailEntityToDto(InvoiceDetail invoiceDetail);

    @Mapping(target="tableType.name",source = "tableType")
    CoffeeTable CoffeeTableDtoToEntity(CoffeeTableDto coffeeTableDto);

    @Mapping(target="tableType",source = "tableType.name")
    CoffeeTableDto CoffeeTableEntityToDto(CoffeeTable coffeeTable);

    @Mapping(target="materialType.name",source = "materialType")
    Material MaterialDtoToEntity(MaterialDto materialDto);

    @Mapping(target="materialType",source = "materialType.name")
    MaterialDto MaterialEntityToDto(Material material);

    @Mapping(target="branchShop.name",source = "branchShop")
    @Mapping(target="supplier.name",source = "supplier")
    SupplyContract SupplyContractDtoToEntity(SupplyContractDto supplyContractDto);

    @Mapping(target="branchShop",source = "branchShop.name")
    @Mapping(target="supplier",source = "supplier.name")
    SupplyContractDto SupplyContractEntityToDto(SupplyContract supplyContract);

    @Mapping(target="material.name",source = "material")
    @Mapping(target="drink.name",source = "drink")
    Recipe RecipeDtoToEntity(RecipeDto recipeDto);

    @Mapping(target="material",source = "material.name")
    @Mapping(target="drink",source = "drink.name")
    RecipeDto RecipeEntityToDto(Recipe recipe);

    @Mapping(target="material.id",source = "materialId")
    @Mapping(target="supplyContract.id",source = "supplyContractId")
    SupplyContractDetail SupplyContractDetailDtoEntity(SupplyContractDetailDto detailDto);
}
