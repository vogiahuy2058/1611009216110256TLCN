package coffeesystem.util;

import coffeesystem.dto.AccountResponseDto;
import coffeesystem.dto.BranchShopDto;
import coffeesystem.dto.CoffeeTableDto;
import coffeesystem.dto.CustomerRequestDto;
import coffeesystem.dto.CustomerResponseDto;
import coffeesystem.dto.CustomerTypeDto;
import coffeesystem.dto.DemoDto;
import coffeesystem.dto.DrinkDto;
import coffeesystem.dto.DrinkPriceRequestDto;
import coffeesystem.dto.DrinkPriceResponseDto;
import coffeesystem.dto.DrinkTypeDto;
import coffeesystem.dto.EmployeeRequestDto;
import coffeesystem.dto.EmployeeResponseDto;
import coffeesystem.dto.EmployeeTypeDto;
import coffeesystem.dto.InternalSCDetailRequestDto;
import coffeesystem.dto.InternalSCDetailResponseDto;
import coffeesystem.dto.InternalSCRequestDto;
import coffeesystem.dto.InternalSCResponseDto;
import coffeesystem.dto.InventoryRequestDto;
import coffeesystem.dto.InventoryResponseDto;
import coffeesystem.dto.InvoiceAndInvoiceDetailDto;
import coffeesystem.dto.InvoiceDetailRequestDto;
import coffeesystem.dto.InvoiceDetailResponseDto;
import coffeesystem.dto.InvoiceRequestDto;
import coffeesystem.dto.InvoiceResponseDto;
import coffeesystem.dto.MaterialDto;
import coffeesystem.dto.MaterialPriceRequestDto;
import coffeesystem.dto.MaterialPriceResponseDto;
import coffeesystem.dto.MaterialTypeDto;
import coffeesystem.dto.MinMaxInventoryRequestDto;
import coffeesystem.dto.MinMaxInventoryResponseDto;
import coffeesystem.dto.OrderTypeDto;
import coffeesystem.dto.RecipeDto;
import coffeesystem.dto.RoleDto;
import coffeesystem.dto.SupplierDto;
import coffeesystem.dto.SupplyContractDetailRequestDto;
import coffeesystem.dto.SupplyContractDetailResponseDto;
import coffeesystem.dto.SupplyContractRequestDto;
import coffeesystem.dto.SupplyContractResponseDto;
import coffeesystem.dto.TableTypeDto;
import coffeesystem.dto.UnitDto;
import coffeesystem.model.Account;
import coffeesystem.model.BranchShop;
import coffeesystem.model.CoffeeTable;
import coffeesystem.model.Customer;
import coffeesystem.model.CustomerType;
import coffeesystem.model.Demo;
import coffeesystem.model.Drink;
import coffeesystem.model.DrinkPrice;
import coffeesystem.model.DrinkType;
import coffeesystem.model.Employee;
import coffeesystem.model.EmployeeType;
import coffeesystem.model.InternalSC;
import coffeesystem.model.InternalSCDetail;
import coffeesystem.model.Inventory;
import coffeesystem.model.Invoice;
import coffeesystem.model.InvoiceDetail;
import coffeesystem.model.Material;
import coffeesystem.model.MaterialPrice;
import coffeesystem.model.MaterialType;
import coffeesystem.model.MinMaxInventory;
import coffeesystem.model.OrderType;
import coffeesystem.model.Recipe;
import coffeesystem.model.Role;
import coffeesystem.model.RoleName;
import coffeesystem.model.Supplier;
import coffeesystem.model.SupplyContract;
import coffeesystem.model.SupplyContractDetail;
import coffeesystem.model.TableType;
import coffeesystem.model.Unit;
import coffeesystem.model.embedding.DrinkPriceId;
import coffeesystem.model.embedding.InternalSCDetailId;
import coffeesystem.model.embedding.InventoryId;
import coffeesystem.model.embedding.InvoiceDetailId;
import coffeesystem.model.embedding.MaterialPriceId;
import coffeesystem.model.embedding.MinMaxInventoryId;
import coffeesystem.model.embedding.SupplyContractDetailId;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-06-25T22:20:31+0700",
    comments = "version: 1.2.0.CR2, compiler: javac, environment: Java 1.8.0_231 (Oracle Corporation)"
)
@Component
public class MapperObjectImpl implements MapperObject {

    @Override
    public RoleDto RoleEntityToRoleDTO(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        if ( role.getName() != null ) {
            roleDto.setName( role.getName().name() );
        }

        return roleDto;
    }

    @Override
    public Role RoleDtoToEntity(RoleDto roleDto) {
        if ( roleDto == null ) {
            return null;
        }

        Role role = new Role();

        if ( roleDto.getName() != null ) {
            role.setName( Enum.valueOf( RoleName.class, roleDto.getName() ) );
        }

        return role;
    }

    @Override
    public AccountResponseDto AccountEntityToAccountResponseDto(Account account) {
        if ( account == null ) {
            return null;
        }

        AccountResponseDto accountResponseDto = new AccountResponseDto();

        Integer id = accountEmployeeId( account );
        if ( id != null ) {
            accountResponseDto.setIdEmployee( id );
        }
        accountResponseDto.setId( account.getId() );
        accountResponseDto.setUsername( account.getUsername() );
        accountResponseDto.setEmail( account.getEmail() );
        accountResponseDto.setRole( roleSetToRoleDtoSet( account.getRole() ) );

        return accountResponseDto;
    }

    @Override
    public DrinkDto DrinkEntityToDrinkDTO(Drink drink) {
        if ( drink == null ) {
            return null;
        }

        DrinkDto drinkDto = new DrinkDto();

        String name = drinkDrinkTypeName( drink );
        if ( name != null ) {
            drinkDto.setDrinkType( name );
        }
        drinkDto.setId( drink.getId() );
        drinkDto.setName( drink.getName() );
        drinkDto.setDescription( drink.getDescription() );

        return drinkDto;
    }

    @Override
    public Drink DrinkDTOToDrinkEntity(DrinkDto drinkDto) {
        if ( drinkDto == null ) {
            return null;
        }

        Drink drink = new Drink();

        drink.setDrinkType( drinkDtoToDrinkType( drinkDto ) );
        drink.setId( drinkDto.getId() );
        drink.setName( drinkDto.getName() );
        drink.setDescription( drinkDto.getDescription() );

        return drink;
    }

    @Override
    public CustomerType CustomerTypeDtoToEntity(CustomerTypeDto customerTypeDto) {
        if ( customerTypeDto == null ) {
            return null;
        }

        CustomerType customerType = new CustomerType();

        customerType.setId( customerTypeDto.getId() );
        customerType.setName( customerTypeDto.getName() );
        customerType.setDiscountName( customerTypeDto.getDiscountName() );
        customerType.setDiscountValue( customerTypeDto.getDiscountValue() );

        return customerType;
    }

    @Override
    public CustomerTypeDto CustomerTypeEntityToDto(CustomerType customerType) {
        if ( customerType == null ) {
            return null;
        }

        CustomerTypeDto customerTypeDto = new CustomerTypeDto();

        customerTypeDto.setId( customerType.getId() );
        customerTypeDto.setName( customerType.getName() );
        customerTypeDto.setDiscountName( customerType.getDiscountName() );
        customerTypeDto.setDiscountValue( customerType.getDiscountValue() );

        return customerTypeDto;
    }

    @Override
    public DrinkType DrinkTypeDtoToEntity(DrinkTypeDto drinkTypeDto) {
        if ( drinkTypeDto == null ) {
            return null;
        }

        DrinkType drinkType = new DrinkType();

        drinkType.setId( drinkTypeDto.getId() );
        drinkType.setName( drinkTypeDto.getName() );

        return drinkType;
    }

    @Override
    public DrinkTypeDto DrinkTypeEntityToDto(DrinkType drinkType) {
        if ( drinkType == null ) {
            return null;
        }

        DrinkTypeDto drinkTypeDto = new DrinkTypeDto();

        drinkTypeDto.setId( drinkType.getId() );
        drinkTypeDto.setName( drinkType.getName() );

        return drinkTypeDto;
    }

    @Override
    public EmployeeType EmployeeDtoToEntity(EmployeeTypeDto employeeTypeDto) {
        if ( employeeTypeDto == null ) {
            return null;
        }

        EmployeeType employeeType = new EmployeeType();

        employeeType.setId( employeeTypeDto.getId() );
        employeeType.setName( employeeTypeDto.getName() );

        return employeeType;
    }

    @Override
    public EmployeeTypeDto EmployeeTypeEntityToDto(EmployeeType employeeType) {
        if ( employeeType == null ) {
            return null;
        }

        EmployeeTypeDto employeeTypeDto = new EmployeeTypeDto();

        employeeTypeDto.setId( employeeType.getId() );
        employeeTypeDto.setName( employeeType.getName() );

        return employeeTypeDto;
    }

    @Override
    public OrderType OrderTypeDtoToEntity(OrderTypeDto orderTypeDto) {
        if ( orderTypeDto == null ) {
            return null;
        }

        OrderType orderType = new OrderType();

        orderType.setId( orderTypeDto.getId() );
        orderType.setName( orderTypeDto.getName() );

        return orderType;
    }

    @Override
    public OrderTypeDto OrderTypeEntityToDto(OrderType orderType) {
        if ( orderType == null ) {
            return null;
        }

        OrderTypeDto orderTypeDto = new OrderTypeDto();

        orderTypeDto.setId( orderType.getId() );
        orderTypeDto.setName( orderType.getName() );

        return orderTypeDto;
    }

    @Override
    public TableType TableTypeDtoToEntity(TableTypeDto tableTypeDto) {
        if ( tableTypeDto == null ) {
            return null;
        }

        TableType tableType = new TableType();

        tableType.setId( tableTypeDto.getId() );
        tableType.setName( tableTypeDto.getName() );

        return tableType;
    }

    @Override
    public TableTypeDto TableTypeEntityToDto(TableType tableType) {
        if ( tableType == null ) {
            return null;
        }

        TableTypeDto tableTypeDto = new TableTypeDto();

        tableTypeDto.setId( tableType.getId() );
        tableTypeDto.setName( tableType.getName() );

        return tableTypeDto;
    }

    @Override
    public MaterialType MaterialTypeDtoToEntity(MaterialTypeDto materialTypeDto) {
        if ( materialTypeDto == null ) {
            return null;
        }

        MaterialType materialType = new MaterialType();

        materialType.setId( materialTypeDto.getId() );
        materialType.setName( materialTypeDto.getName() );

        return materialType;
    }

    @Override
    public MaterialTypeDto MaterialTypeEntityToDto(MaterialType materialType) {
        if ( materialType == null ) {
            return null;
        }

        MaterialTypeDto materialTypeDto = new MaterialTypeDto();

        materialTypeDto.setId( materialType.getId() );
        materialTypeDto.setName( materialType.getName() );

        return materialTypeDto;
    }

    @Override
    public Supplier SupplierDtoToEntity(SupplierDto supplierDto) {
        if ( supplierDto == null ) {
            return null;
        }

        Supplier supplier = new Supplier();

        supplier.setId( supplierDto.getId() );
        supplier.setName( supplierDto.getName() );
        supplier.setEmail( supplierDto.getEmail() );
        supplier.setPhone( supplierDto.getPhone() );
        supplier.setAddress( supplierDto.getAddress() );
        supplier.setTaxCode( supplierDto.getTaxCode() );
        supplier.setNote( supplierDto.getNote() );
        supplier.setTotalPurchase( supplierDto.getTotalPurchase() );

        return supplier;
    }

    @Override
    public SupplierDto SupplierEntityToDto(Supplier supplier) {
        if ( supplier == null ) {
            return null;
        }

        SupplierDto supplierDto = new SupplierDto();

        supplierDto.setId( supplier.getId() );
        supplierDto.setName( supplier.getName() );
        supplierDto.setEmail( supplier.getEmail() );
        supplierDto.setPhone( supplier.getPhone() );
        supplierDto.setAddress( supplier.getAddress() );
        supplierDto.setTaxCode( supplier.getTaxCode() );
        supplierDto.setNote( supplier.getNote() );
        supplierDto.setTotalPurchase( supplier.getTotalPurchase() );

        return supplierDto;
    }

    @Override
    public Unit UnitDtoToEntity(UnitDto unitDto) {
        if ( unitDto == null ) {
            return null;
        }

        Unit unit = new Unit();

        unit.setId( unitDto.getId() );
        unit.setName( unitDto.getName() );

        return unit;
    }

    @Override
    public UnitDto UnitEntityToDto(Unit unit) {
        if ( unit == null ) {
            return null;
        }

        UnitDto unitDto = new UnitDto();

        unitDto.setId( unit.getId() );
        unitDto.setName( unit.getName() );

        return unitDto;
    }

    @Override
    public Employee EmployeeDtoToEntity(EmployeeRequestDto employeeRequestDto) {
        if ( employeeRequestDto == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setBranchShop( employeeRequestDtoToBranchShop( employeeRequestDto ) );
        employee.setEmployeeType( employeeRequestDtoToEmployeeType( employeeRequestDto ) );
        employee.setId( employeeRequestDto.getId() );
        employee.setName( employeeRequestDto.getName() );
        employee.setEmail( employeeRequestDto.getEmail() );
        employee.setIdkey( employeeRequestDto.getIdkey() );

        return employee;
    }

    @Override
    public EmployeeResponseDto EmployeeEntityToDto(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();

        String name = employeeBranchShopName( employee );
        if ( name != null ) {
            employeeResponseDto.setBranchShop( name );
        }
        String name1 = employeeEmployeeTypeName( employee );
        if ( name1 != null ) {
            employeeResponseDto.setEmployeeType( name1 );
        }
        employeeResponseDto.setId( employee.getId() );
        employeeResponseDto.setName( employee.getName() );
        employeeResponseDto.setEmail( employee.getEmail() );

        return employeeResponseDto;
    }

    @Override
    public Employee EmployeeDtoToEntity1(EmployeeRequestDto employeeRequestDto) {
        if ( employeeRequestDto == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setBranchShop( employeeRequestDtoToBranchShop1( employeeRequestDto ) );
        employee.setEmployeeType( employeeRequestDtoToEmployeeType1( employeeRequestDto ) );
        employee.setId( employeeRequestDto.getId() );
        employee.setName( employeeRequestDto.getName() );
        employee.setEmail( employeeRequestDto.getEmail() );
        employee.setIdkey( employeeRequestDto.getIdkey() );

        return employee;
    }

    @Override
    public EmployeeResponseDto EmployeeEntityToDto1(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();

        String name = employeeBranchShopName( employee );
        if ( name != null ) {
            employeeResponseDto.setBranchShop( name );
        }
        String name1 = employeeEmployeeTypeName( employee );
        if ( name1 != null ) {
            employeeResponseDto.setEmployeeType( name1 );
        }
        employeeResponseDto.setId( employee.getId() );
        employeeResponseDto.setName( employee.getName() );
        employeeResponseDto.setEmail( employee.getEmail() );

        return employeeResponseDto;
    }

    @Override
    public BranchShop BranchShopDtoToEntity(BranchShopDto branchShopDto) {
        if ( branchShopDto == null ) {
            return null;
        }

        BranchShop branchShop = new BranchShop();

        branchShop.setId( branchShopDto.getId() );
        branchShop.setName( branchShopDto.getName() );
        branchShop.setAddress( branchShopDto.getAddress() );

        return branchShop;
    }

    @Override
    public BranchShopDto BranchShopEntityToDto(BranchShop branchShop) {
        if ( branchShop == null ) {
            return null;
        }

        BranchShopDto branchShopDto = new BranchShopDto();

        branchShopDto.setId( branchShop.getId() );
        branchShopDto.setName( branchShop.getName() );
        branchShopDto.setAddress( branchShop.getAddress() );

        return branchShopDto;
    }

    @Override
    public Customer CustomerDtoToEntity(CustomerRequestDto customerRequestDto) {
        if ( customerRequestDto == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setCustomerType( customerRequestDtoToCustomerType( customerRequestDto ) );
        customer.setId( customerRequestDto.getId() );
        customer.setName( customerRequestDto.getName() );
        customer.setPhone( customerRequestDto.getPhone() );
        customer.setAddress( customerRequestDto.getAddress() );
        customer.setBirthDay( customerRequestDto.getBirthDay() );
        customer.setSex( customerRequestDto.isSex() );
        customer.setEmail( customerRequestDto.getEmail() );
        customer.setTotalPurchase( customerRequestDto.getTotalPurchase() );
        customer.setNote( customerRequestDto.getNote() );

        return customer;
    }

    @Override
    public CustomerResponseDto CustomerEntityToDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerResponseDto customerResponseDto = new CustomerResponseDto();

        if ( customer.getBirthDay() != null ) {
            customerResponseDto.setBirthDay( DateTimeFormatter.ISO_LOCAL_DATE.format( customer.getBirthDay() ) );
        }
        String name = customerCustomerTypeName( customer );
        if ( name != null ) {
            customerResponseDto.setCustomerType( name );
        }
        customerResponseDto.setId( customer.getId() );
        customerResponseDto.setName( customer.getName() );
        customerResponseDto.setPhone( customer.getPhone() );
        customerResponseDto.setAddress( customer.getAddress() );
        customerResponseDto.setSex( customer.isSex() );
        customerResponseDto.setEmail( customer.getEmail() );
        customerResponseDto.setNote( customer.getNote() );
        customerResponseDto.setTotalPurchase( customer.getTotalPurchase() );

        return customerResponseDto;
    }

    @Override
    public CustomerRequestDto CustomerEntityToDt1(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerRequestDto customerRequestDto = new CustomerRequestDto();

        String name = customerCustomerTypeName( customer );
        if ( name != null ) {
            customerRequestDto.setCustomerType( name );
        }
        customerRequestDto.setId( customer.getId() );
        customerRequestDto.setName( customer.getName() );
        customerRequestDto.setPhone( customer.getPhone() );
        customerRequestDto.setAddress( customer.getAddress() );
        customerRequestDto.setBirthDay( customer.getBirthDay() );
        customerRequestDto.setSex( customer.isSex() );
        customerRequestDto.setEmail( customer.getEmail() );
        customerRequestDto.setNote( customer.getNote() );
        customerRequestDto.setTotalPurchase( customer.getTotalPurchase() );

        return customerRequestDto;
    }

    @Override
    public Invoice InvoiceDtoToEntity(InvoiceRequestDto invoiceRequestDto) {
        if ( invoiceRequestDto == null ) {
            return null;
        }

        Invoice invoice = new Invoice();

        invoice.setCustomer( invoiceRequestDtoToCustomer( invoiceRequestDto ) );
        invoice.setOrderType( invoiceRequestDtoToOrderType( invoiceRequestDto ) );
        invoice.setId( invoiceRequestDto.getId() );
        invoice.setVat( invoiceRequestDto.getVat() );
        invoice.setTotalPrice( invoiceRequestDto.getTotalPrice() );
        invoice.setRealPay( invoiceRequestDto.getRealPay() );
        invoice.setTotalDiscount( invoiceRequestDto.getTotalDiscount() );
        invoice.setDate( invoiceRequestDto.getDate() );
        invoice.setStatus( invoiceRequestDto.getStatus() );
        invoice.setNumberPosition( invoiceRequestDto.getNumberPosition() );

        return invoice;
    }

    @Override
    public InvoiceResponseDto InvoiceEntityToDto(Invoice invoice) {
        if ( invoice == null ) {
            return null;
        }

        InvoiceResponseDto invoiceResponseDto = new InvoiceResponseDto();

        String name = invoiceOrderTypeName( invoice );
        if ( name != null ) {
            invoiceResponseDto.setOrderType( name );
        }
        String phone = invoiceCustomerPhone( invoice );
        if ( phone != null ) {
            invoiceResponseDto.setCustomerPhone( phone );
        }
        String name1 = invoiceCustomerName( invoice );
        if ( name1 != null ) {
            invoiceResponseDto.setCustomerName( name1 );
        }
        String name2 = invoiceBranchShopName( invoice );
        if ( name2 != null ) {
            invoiceResponseDto.setBranchShop( name2 );
        }
        invoiceResponseDto.setId( invoice.getId() );
        if ( invoice.getDate() != null ) {
            invoiceResponseDto.setDate( DateTimeFormatter.ISO_DATE_TIME.format( invoice.getDate() ) );
        }
        invoiceResponseDto.setNumberPosition( invoice.getNumberPosition() );
        invoiceResponseDto.setVat( invoice.getVat() );
        invoiceResponseDto.setTotalDiscount( invoice.getTotalDiscount() );
        invoiceResponseDto.setTotalPrice( invoice.getTotalPrice() );
        invoiceResponseDto.setRealPay( invoice.getRealPay() );
        invoiceResponseDto.setStatus( invoice.getStatus() );

        return invoiceResponseDto;
    }

    @Override
    public InvoiceDetail InvoiceDetailDtoToEntity(InvoiceDetailRequestDto invoiceDetailRequestDto) {
        if ( invoiceDetailRequestDto == null ) {
            return null;
        }

        InvoiceDetail invoiceDetail = new InvoiceDetail();

        invoiceDetail.setInvoice( invoiceDetailRequestDtoToInvoice( invoiceDetailRequestDto ) );
        invoiceDetail.setDrink( invoiceDetailRequestDtoToDrink( invoiceDetailRequestDto ) );
        invoiceDetail.setInvoiceDetailId( invoiceDetailRequestDtoToInvoiceDetailId( invoiceDetailRequestDto ) );
        invoiceDetail.setAmount( invoiceDetailRequestDto.getAmount() );
        invoiceDetail.setPrice( invoiceDetailRequestDto.getPrice() );
        invoiceDetail.setUnitPrice( invoiceDetailRequestDto.getUnitPrice() );
        invoiceDetail.setDiscount( invoiceDetailRequestDto.getDiscount() );
        invoiceDetail.setNote( invoiceDetailRequestDto.getNote() );

        return invoiceDetail;
    }

    @Override
    public InvoiceAndInvoiceDetailDto InvoiceEntityToDtoFull(Invoice invoice) {
        if ( invoice == null ) {
            return null;
        }

        InvoiceAndInvoiceDetailDto invoiceAndInvoiceDetailDto = new InvoiceAndInvoiceDetailDto();

        String name = invoiceOrderTypeName( invoice );
        if ( name != null ) {
            invoiceAndInvoiceDetailDto.setOrderType( name );
        }
        String phone = invoiceCustomerPhone( invoice );
        if ( phone != null ) {
            invoiceAndInvoiceDetailDto.setCustomerPhone( phone );
        }
        String name1 = invoiceCustomerName( invoice );
        if ( name1 != null ) {
            invoiceAndInvoiceDetailDto.setCustomerName( name1 );
        }
        String name2 = invoiceBranchShopName( invoice );
        if ( name2 != null ) {
            invoiceAndInvoiceDetailDto.setBranchShop( name2 );
        }
        invoiceAndInvoiceDetailDto.setId( invoice.getId() );
        if ( invoice.getDate() != null ) {
            invoiceAndInvoiceDetailDto.setDate( DateTimeFormatter.ISO_DATE_TIME.format( invoice.getDate() ) );
        }
        invoiceAndInvoiceDetailDto.setNumberPosition( invoice.getNumberPosition() );
        invoiceAndInvoiceDetailDto.setVat( invoice.getVat() );
        invoiceAndInvoiceDetailDto.setTotalDiscount( invoice.getTotalDiscount() );
        invoiceAndInvoiceDetailDto.setTotalPrice( invoice.getTotalPrice() );
        invoiceAndInvoiceDetailDto.setRealPay( invoice.getRealPay() );

        return invoiceAndInvoiceDetailDto;
    }

    @Override
    public InvoiceDetailResponseDto InvoiceDetailEntityToDto(InvoiceDetail invoiceDetail) {
        if ( invoiceDetail == null ) {
            return null;
        }

        InvoiceDetailResponseDto invoiceDetailResponseDto = new InvoiceDetailResponseDto();

        Integer id = invoiceDetailDrinkId( invoiceDetail );
        if ( id != null ) {
            invoiceDetailResponseDto.setDrinkId( id );
        }
        Integer id1 = invoiceDetailInvoiceId( invoiceDetail );
        if ( id1 != null ) {
            invoiceDetailResponseDto.setInvoiceId( id1 );
        }
        Integer id2 = invoiceDetailInvoiceDetailIdId( invoiceDetail );
        if ( id2 != null ) {
            invoiceDetailResponseDto.setId( id2 );
        }
        invoiceDetailResponseDto.setAmount( invoiceDetail.getAmount() );
        invoiceDetailResponseDto.setPrice( invoiceDetail.getPrice() );
        invoiceDetailResponseDto.setDiscount( invoiceDetail.getDiscount() );
        invoiceDetailResponseDto.setUnitPrice( invoiceDetail.getUnitPrice() );
        invoiceDetailResponseDto.setNote( invoiceDetail.getNote() );

        return invoiceDetailResponseDto;
    }

    @Override
    public CoffeeTable CoffeeTableDtoToEntity(CoffeeTableDto coffeeTableDto) {
        if ( coffeeTableDto == null ) {
            return null;
        }

        CoffeeTable coffeeTable = new CoffeeTable();

        coffeeTable.setTableType( coffeeTableDtoToTableType( coffeeTableDto ) );
        coffeeTable.setId( coffeeTableDto.getId() );
        coffeeTable.setName( coffeeTableDto.getName() );
        coffeeTable.setNumberOfChair( coffeeTableDto.getNumberOfChair() );
        coffeeTable.setNote( coffeeTableDto.getNote() );

        return coffeeTable;
    }

    @Override
    public CoffeeTableDto CoffeeTableEntityToDto(CoffeeTable coffeeTable) {
        if ( coffeeTable == null ) {
            return null;
        }

        CoffeeTableDto coffeeTableDto = new CoffeeTableDto();

        String name = coffeeTableTableTypeName( coffeeTable );
        if ( name != null ) {
            coffeeTableDto.setTableType( name );
        }
        coffeeTableDto.setId( coffeeTable.getId() );
        coffeeTableDto.setName( coffeeTable.getName() );
        coffeeTableDto.setNumberOfChair( coffeeTable.getNumberOfChair() );
        coffeeTableDto.setNote( coffeeTable.getNote() );

        return coffeeTableDto;
    }

    @Override
    public Material MaterialDtoToEntity(MaterialDto materialDto) {
        if ( materialDto == null ) {
            return null;
        }

        Material material = new Material();

        material.setMaterialType( materialDtoToMaterialType( materialDto ) );
        material.setUnit( materialDtoToUnit( materialDto ) );
        material.setId( materialDto.getId() );
        material.setName( materialDto.getName() );

        return material;
    }

    @Override
    public MaterialDto MaterialEntityToDto(Material material) {
        if ( material == null ) {
            return null;
        }

        MaterialDto materialDto = new MaterialDto();

        String name = materialUnitName( material );
        if ( name != null ) {
            materialDto.setUnit( name );
        }
        String name1 = materialMaterialTypeName( material );
        if ( name1 != null ) {
            materialDto.setMaterialType( name1 );
        }
        materialDto.setId( material.getId() );
        materialDto.setName( material.getName() );

        return materialDto;
    }

    @Override
    public SupplyContract SupplyContractDtoToEntity(SupplyContractRequestDto supplyContractRequestDto) {
        if ( supplyContractRequestDto == null ) {
            return null;
        }

        SupplyContract supplyContract = new SupplyContract();

        supplyContract.setSupplier( supplyContractRequestDtoToSupplier( supplyContractRequestDto ) );
        supplyContract.setBranchShop( supplyContractRequestDtoToBranchShop( supplyContractRequestDto ) );
        supplyContract.setDateCreate( supplyContractRequestDto.getDate() );
        supplyContract.setId( supplyContractRequestDto.getId() );
        supplyContract.setTotalPrice( supplyContractRequestDto.getTotalPrice() );
        supplyContract.setStatus( supplyContractRequestDto.getStatus() );
        supplyContract.setDeliveryTime( supplyContractRequestDto.getDeliveryTime() );
        supplyContract.setPaymentTime( supplyContractRequestDto.getPaymentTime() );

        return supplyContract;
    }

    @Override
    public SupplyContractRequestDto SupplyContractEntityToDto(SupplyContract supplyContract) {
        if ( supplyContract == null ) {
            return null;
        }

        SupplyContractRequestDto supplyContractRequestDto = new SupplyContractRequestDto();

        String name = supplyContractBranchShopName( supplyContract );
        if ( name != null ) {
            supplyContractRequestDto.setBranchShop( name );
        }
        String name1 = supplyContractSupplierName( supplyContract );
        if ( name1 != null ) {
            supplyContractRequestDto.setSupplier( name1 );
        }
        supplyContractRequestDto.setId( supplyContract.getId() );
        supplyContractRequestDto.setTotalPrice( supplyContract.getTotalPrice() );
        supplyContractRequestDto.setStatus( supplyContract.getStatus() );
        supplyContractRequestDto.setDeliveryTime( supplyContract.getDeliveryTime() );
        supplyContractRequestDto.setPaymentTime( supplyContract.getPaymentTime() );

        return supplyContractRequestDto;
    }

    @Override
    public SupplyContractResponseDto SupplyContractEntityToDto1(SupplyContract supplyContract) {
        if ( supplyContract == null ) {
            return null;
        }

        SupplyContractResponseDto supplyContractResponseDto = new SupplyContractResponseDto();

        String name = supplyContractBranchShopName( supplyContract );
        if ( name != null ) {
            supplyContractResponseDto.setBranchShop( name );
        }
        String name1 = supplyContractSupplierName( supplyContract );
        if ( name1 != null ) {
            supplyContractResponseDto.setSupplier( name1 );
        }
        supplyContractResponseDto.setId( supplyContract.getId() );
        supplyContractResponseDto.setTotalPrice( supplyContract.getTotalPrice() );
        if ( supplyContract.getDeliveryTime() != null ) {
            supplyContractResponseDto.setDeliveryTime( DateTimeFormatter.ISO_LOCAL_DATE.format( supplyContract.getDeliveryTime() ) );
        }
        if ( supplyContract.getPaymentTime() != null ) {
            supplyContractResponseDto.setPaymentTime( DateTimeFormatter.ISO_LOCAL_DATE.format( supplyContract.getPaymentTime() ) );
        }
        supplyContractResponseDto.setStatus( supplyContract.getStatus() );

        return supplyContractResponseDto;
    }

    @Override
    public Recipe RecipeDtoToEntity(RecipeDto recipeDto) {
        if ( recipeDto == null ) {
            return null;
        }

        Recipe recipe = new Recipe();

        recipe.setUnit( recipeDtoToUnit( recipeDto ) );
        recipe.setDrink( recipeDtoToDrink( recipeDto ) );
        recipe.setMaterial( recipeDtoToMaterial( recipeDto ) );
        recipe.setAmount( recipeDto.getAmount() );

        return recipe;
    }

    @Override
    public RecipeDto RecipeEntityToDto(Recipe recipe) {
        if ( recipe == null ) {
            return null;
        }

        RecipeDto recipeDto = new RecipeDto();

        String name = recipeUnitName( recipe );
        if ( name != null ) {
            recipeDto.setUnit( name );
        }
        String name1 = recipeMaterialName( recipe );
        if ( name1 != null ) {
            recipeDto.setMaterial( name1 );
        }
        String name2 = recipeDrinkName( recipe );
        if ( name2 != null ) {
            recipeDto.setDrink( name2 );
        }
        if ( recipe.getAmount() != null ) {
            recipeDto.setAmount( recipe.getAmount() );
        }

        return recipeDto;
    }

    @Override
    public SupplyContractDetail SupplyContractDetailDtoEntity(SupplyContractDetailRequestDto detailDto) {
        if ( detailDto == null ) {
            return null;
        }

        SupplyContractDetail supplyContractDetail = new SupplyContractDetail();

        supplyContractDetail.setSupplyContractDetailId( supplyContractDetailRequestDtoToSupplyContractDetailId( detailDto ) );
        supplyContractDetail.setUnitPrice( detailDto.getUnitPrice() );
        supplyContractDetail.setAmount( detailDto.getAmount() );

        return supplyContractDetail;
    }

    @Override
    public SupplyContractDetailResponseDto SupplyContractEntityToDto(SupplyContractDetail supplyContractDetail) {
        if ( supplyContractDetail == null ) {
            return null;
        }

        SupplyContractDetailResponseDto supplyContractDetailResponseDto = new SupplyContractDetailResponseDto();

        Integer id = supplyContractDetailSupplyContractDetailIdId( supplyContractDetail );
        if ( id != null ) {
            supplyContractDetailResponseDto.setId( id );
        }
        Integer materialId = supplyContractDetailSupplyContractDetailIdMaterialId( supplyContractDetail );
        if ( materialId != null ) {
            supplyContractDetailResponseDto.setMaterialId( materialId );
        }
        Integer supplyContractId = supplyContractDetailSupplyContractDetailIdSupplyContractId( supplyContractDetail );
        if ( supplyContractId != null ) {
            supplyContractDetailResponseDto.setSupplyContractId( supplyContractId );
        }
        supplyContractDetailResponseDto.setUnitPrice( supplyContractDetail.getUnitPrice() );
        supplyContractDetailResponseDto.setAmount( supplyContractDetail.getAmount() );

        return supplyContractDetailResponseDto;
    }

    @Override
    public DrinkPrice DrinkPriceDtoToEntity(DrinkPriceRequestDto drinkPriceRequestDto) {
        if ( drinkPriceRequestDto == null ) {
            return null;
        }

        DrinkPrice drinkPrice = new DrinkPrice();

        drinkPrice.setDrinkPriceId( drinkPriceRequestDtoToDrinkPriceId( drinkPriceRequestDto ) );
        drinkPrice.setPrice( drinkPriceRequestDto.getPrice() );
        drinkPrice.setInitialPrice( drinkPriceRequestDto.getInitialPrice() );

        return drinkPrice;
    }

    @Override
    public DrinkPriceResponseDto DrinkPriceEntityToDto1(DrinkPrice drinkPrice) {
        if ( drinkPrice == null ) {
            return null;
        }

        DrinkPriceResponseDto drinkPriceResponseDto = new DrinkPriceResponseDto();

        LocalDate date = drinkPriceDrinkPriceIdDate( drinkPrice );
        if ( date != null ) {
            drinkPriceResponseDto.setDate( DateTimeFormatter.ISO_LOCAL_DATE.format( date ) );
        }
        Integer idDrink = drinkPriceDrinkPriceIdIdDrink( drinkPrice );
        if ( idDrink != null ) {
            drinkPriceResponseDto.setDrinkId( idDrink );
        }
        Integer id = drinkPriceDrinkPriceIdId( drinkPrice );
        if ( id != null ) {
            drinkPriceResponseDto.setId( id );
        }
        drinkPriceResponseDto.setPrice( drinkPrice.getPrice() );
        drinkPriceResponseDto.setInitialPrice( drinkPrice.getInitialPrice() );

        return drinkPriceResponseDto;
    }

    @Override
    public MaterialPrice MaterialPriceDtoToEntity(MaterialPriceRequestDto materialPriceRequestDto) {
        if ( materialPriceRequestDto == null ) {
            return null;
        }

        MaterialPrice materialPrice = new MaterialPrice();

        materialPrice.setMaterialPriceId( materialPriceRequestDtoToMaterialPriceId( materialPriceRequestDto ) );
        materialPrice.setCostPrice( materialPriceRequestDto.getCostPrice() );
        materialPrice.setPriceFromSupplier( materialPriceRequestDto.getPriceFromSupplier() );

        return materialPrice;
    }

    @Override
    public MaterialPrice MaterialPriceDtoToEntity1(MaterialPriceRequestDto materialPriceRequestDto) {
        if ( materialPriceRequestDto == null ) {
            return null;
        }

        MaterialPrice materialPrice = new MaterialPrice();

        materialPrice.setMaterialPriceId( materialPriceRequestDtoToMaterialPriceId1( materialPriceRequestDto ) );
        materialPrice.setCostPrice( materialPriceRequestDto.getCostPrice() );
        materialPrice.setPriceFromSupplier( materialPriceRequestDto.getPriceFromSupplier() );

        return materialPrice;
    }

    @Override
    public MaterialPriceRequestDto MaterialPriceEntityToDto(MaterialPrice materialPrice) {
        if ( materialPrice == null ) {
            return null;
        }

        MaterialPriceRequestDto materialPriceRequestDto = new MaterialPriceRequestDto();

        LocalDate firstDate = materialPriceMaterialPriceIdFirstDate( materialPrice );
        if ( firstDate != null ) {
            materialPriceRequestDto.setFirstDate( firstDate );
        }
        Integer id = materialPriceMaterialPriceIdId( materialPrice );
        if ( id != null ) {
            materialPriceRequestDto.setId( id );
        }
        Integer idMaterial = materialPriceMaterialPriceIdIdMaterial( materialPrice );
        if ( idMaterial != null ) {
            materialPriceRequestDto.setMaterialId( idMaterial );
        }
        materialPriceRequestDto.setCostPrice( materialPrice.getCostPrice() );
        materialPriceRequestDto.setPriceFromSupplier( materialPrice.getPriceFromSupplier() );

        return materialPriceRequestDto;
    }

    @Override
    public MaterialPriceResponseDto MaterialPriceEntityToDto1(MaterialPrice materialPrice) {
        if ( materialPrice == null ) {
            return null;
        }

        MaterialPriceResponseDto materialPriceResponseDto = new MaterialPriceResponseDto();

        Integer idMaterial = materialPriceMaterialPriceIdIdMaterial( materialPrice );
        if ( idMaterial != null ) {
            materialPriceResponseDto.setMaterialId( idMaterial );
        }
        Integer id = materialPriceMaterialPriceIdId( materialPrice );
        if ( id != null ) {
            materialPriceResponseDto.setId( id );
        }
        if ( materialPrice.getLastDate() != null ) {
            materialPriceResponseDto.setLastDate( DateTimeFormatter.ISO_LOCAL_DATE.format( materialPrice.getLastDate() ) );
        }
        materialPriceResponseDto.setCostPrice( materialPrice.getCostPrice() );
        materialPriceResponseDto.setPriceFromSupplier( materialPrice.getPriceFromSupplier() );

        return materialPriceResponseDto;
    }

    @Override
    public MinMaxInventory MinMaxInventoryDtoToEntity(MinMaxInventoryRequestDto minMaxInventoryRequestDto) {
        if ( minMaxInventoryRequestDto == null ) {
            return null;
        }

        MinMaxInventory minMaxInventory = new MinMaxInventory();

        minMaxInventory.setMinMaxInventoryId( minMaxInventoryRequestDtoToMinMaxInventoryId( minMaxInventoryRequestDto ) );
        minMaxInventory.setMinInventory( minMaxInventoryRequestDto.getMinInventory() );
        minMaxInventory.setMaxInventory( minMaxInventoryRequestDto.getMaxInventory() );

        return minMaxInventory;
    }

    @Override
    public MinMaxInventoryResponseDto MinMaxInventoryEntityToDto(MinMaxInventory minMaxInventory) {
        if ( minMaxInventory == null ) {
            return null;
        }

        MinMaxInventoryResponseDto minMaxInventoryResponseDto = new MinMaxInventoryResponseDto();

        Integer idBranchShop = minMaxInventoryMinMaxInventoryIdIdBranchShop( minMaxInventory );
        if ( idBranchShop != null ) {
            minMaxInventoryResponseDto.setBranchShopId( idBranchShop );
        }
        Integer id = minMaxInventoryMinMaxInventoryIdId( minMaxInventory );
        if ( id != null ) {
            minMaxInventoryResponseDto.setId( id );
        }
        Integer idMaterial = minMaxInventoryMinMaxInventoryIdIdMaterial( minMaxInventory );
        if ( idMaterial != null ) {
            minMaxInventoryResponseDto.setMaterialId( idMaterial );
        }
        minMaxInventoryResponseDto.setMinInventory( minMaxInventory.getMinInventory() );
        minMaxInventoryResponseDto.setMaxInventory( minMaxInventory.getMaxInventory() );

        return minMaxInventoryResponseDto;
    }

    @Override
    public InternalSC InternalSCDtoToEntity(InternalSCRequestDto internalSCRequestDto) {
        if ( internalSCRequestDto == null ) {
            return null;
        }

        InternalSC internalSC = new InternalSC();

        internalSC.setBranchShop( internalSCRequestDtoToBranchShop( internalSCRequestDto ) );
        internalSC.setDateCreate( internalSCRequestDto.getDate() );
        internalSC.setId( internalSCRequestDto.getId() );
        internalSC.setStatus( internalSCRequestDto.getStatus() );
        internalSC.setDeliveryTime( internalSCRequestDto.getDeliveryTime() );

        return internalSC;
    }

    @Override
    public InternalSCResponseDto InternalSCEntityToDto(InternalSC internalSC) {
        if ( internalSC == null ) {
            return null;
        }

        InternalSCResponseDto internalSCResponseDto = new InternalSCResponseDto();

        String name = internalSCBranchShopName( internalSC );
        if ( name != null ) {
            internalSCResponseDto.setBranchShop( name );
        }
        internalSCResponseDto.setId( internalSC.getId() );
        internalSCResponseDto.setStatus( internalSC.getStatus() );
        if ( internalSC.getDeliveryTime() != null ) {
            internalSCResponseDto.setDeliveryTime( DateTimeFormatter.ISO_LOCAL_DATE.format( internalSC.getDeliveryTime() ) );
        }

        return internalSCResponseDto;
    }

    @Override
    public InternalSCDetail InternalSCDetailDtoEntity(InternalSCDetailRequestDto internalSCDetailRequestDto) {
        if ( internalSCDetailRequestDto == null ) {
            return null;
        }

        InternalSCDetail internalSCDetail = new InternalSCDetail();

        internalSCDetail.setInternalSCDetailId( internalSCDetailRequestDtoToInternalSCDetailId( internalSCDetailRequestDto ) );
        internalSCDetail.setAmount( internalSCDetailRequestDto.getAmount() );

        return internalSCDetail;
    }

    @Override
    public InternalSCDetailResponseDto InternalSCDetailEntityToDto(InternalSCDetail internalSCDetail) {
        if ( internalSCDetail == null ) {
            return null;
        }

        InternalSCDetailResponseDto internalSCDetailResponseDto = new InternalSCDetailResponseDto();

        Integer internalSCId = internalSCDetailInternalSCDetailIdInternalSCId( internalSCDetail );
        if ( internalSCId != null ) {
            internalSCDetailResponseDto.setInternalSCId( internalSCId );
        }
        Integer id = internalSCDetailInternalSCDetailIdId( internalSCDetail );
        if ( id != null ) {
            internalSCDetailResponseDto.setId( id );
        }
        Integer materialId = internalSCDetailInternalSCDetailIdMaterialId( internalSCDetail );
        if ( materialId != null ) {
            internalSCDetailResponseDto.setMaterialId( materialId );
        }
        internalSCDetailResponseDto.setAmount( internalSCDetail.getAmount() );

        return internalSCDetailResponseDto;
    }

    @Override
    public Demo DemoDtoToEntity(DemoDto demoDto) {
        if ( demoDto == null ) {
            return null;
        }

        Demo demo = new Demo();

        demo.setId( demoDto.getId() );
        demo.setLabel( demoDto.getLabel() );
        demo.setStatus( demoDto.getStatus() );

        return demo;
    }

    @Override
    public DemoDto DemoEntityToDto(Demo demo) {
        if ( demo == null ) {
            return null;
        }

        DemoDto demoDto = new DemoDto();

        demoDto.setId( demo.getId() );
        demoDto.setLabel( demo.getLabel() );
        demoDto.setStatus( demo.getStatus() );

        return demoDto;
    }

    @Override
    public Inventory InventoryDtoToEntity1(InventoryRequestDto inventoryRequestDto) {
        if ( inventoryRequestDto == null ) {
            return null;
        }

        Inventory inventory = new Inventory();

        inventory.setInventoryId( inventoryRequestDtoToInventoryId( inventoryRequestDto ) );
        inventory.setImportPeriod( inventoryRequestDto.getImportPeriod() );

        return inventory;
    }

    @Override
    public InventoryResponseDto InventoryEntityToDto(Inventory inventory) {
        if ( inventory == null ) {
            return null;
        }

        InventoryResponseDto inventoryResponseDto = new InventoryResponseDto();

        Integer idMaterial = inventoryInventoryIdIdMaterial( inventory );
        if ( idMaterial != null ) {
            inventoryResponseDto.setMaterialId( idMaterial );
        }
        Integer idBranchShop = inventoryInventoryIdIdBranchShop( inventory );
        if ( idBranchShop != null ) {
            inventoryResponseDto.setBranchShopId( idBranchShop );
        }
        if ( inventory.getLastDate() != null ) {
            inventoryResponseDto.setLastDate( DateTimeFormatter.ISO_LOCAL_DATE.format( inventory.getLastDate() ) );
        }
        inventoryResponseDto.setBacklogFirstDate( inventory.getBacklogFirstDate() );
        inventoryResponseDto.setImportPeriod( inventory.getImportPeriod() );
        inventoryResponseDto.setBacklogLastDate( inventory.getBacklogLastDate() );
        inventoryResponseDto.setQuantitySold( inventory.getQuantitySold() );

        return inventoryResponseDto;
    }

    private Integer accountEmployeeId(Account account) {
        if ( account == null ) {
            return null;
        }
        Employee employee = account.getEmployee();
        if ( employee == null ) {
            return null;
        }
        Integer id = employee.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Set<RoleDto> roleSetToRoleDtoSet(Set<Role> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleDto> set1 = new HashSet<RoleDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Role role : set ) {
            set1.add( RoleEntityToRoleDTO( role ) );
        }

        return set1;
    }

    private String drinkDrinkTypeName(Drink drink) {
        if ( drink == null ) {
            return null;
        }
        DrinkType drinkType = drink.getDrinkType();
        if ( drinkType == null ) {
            return null;
        }
        String name = drinkType.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    protected DrinkType drinkDtoToDrinkType(DrinkDto drinkDto) {
        if ( drinkDto == null ) {
            return null;
        }

        DrinkType drinkType = new DrinkType();

        drinkType.setName( drinkDto.getDrinkType() );

        return drinkType;
    }

    protected BranchShop employeeRequestDtoToBranchShop(EmployeeRequestDto employeeRequestDto) {
        if ( employeeRequestDto == null ) {
            return null;
        }

        BranchShop branchShop = new BranchShop();

        branchShop.setName( employeeRequestDto.getBranchShop() );

        return branchShop;
    }

    protected EmployeeType employeeRequestDtoToEmployeeType(EmployeeRequestDto employeeRequestDto) {
        if ( employeeRequestDto == null ) {
            return null;
        }

        EmployeeType employeeType = new EmployeeType();

        employeeType.setName( employeeRequestDto.getEmployeeType() );

        return employeeType;
    }

    private String employeeBranchShopName(Employee employee) {
        if ( employee == null ) {
            return null;
        }
        BranchShop branchShop = employee.getBranchShop();
        if ( branchShop == null ) {
            return null;
        }
        String name = branchShop.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String employeeEmployeeTypeName(Employee employee) {
        if ( employee == null ) {
            return null;
        }
        EmployeeType employeeType = employee.getEmployeeType();
        if ( employeeType == null ) {
            return null;
        }
        String name = employeeType.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    protected BranchShop employeeRequestDtoToBranchShop1(EmployeeRequestDto employeeRequestDto) {
        if ( employeeRequestDto == null ) {
            return null;
        }

        BranchShop branchShop = new BranchShop();

        branchShop.setName( employeeRequestDto.getBranchShop() );

        return branchShop;
    }

    protected EmployeeType employeeRequestDtoToEmployeeType1(EmployeeRequestDto employeeRequestDto) {
        if ( employeeRequestDto == null ) {
            return null;
        }

        EmployeeType employeeType = new EmployeeType();

        employeeType.setName( employeeRequestDto.getEmployeeType() );

        return employeeType;
    }

    protected CustomerType customerRequestDtoToCustomerType(CustomerRequestDto customerRequestDto) {
        if ( customerRequestDto == null ) {
            return null;
        }

        CustomerType customerType = new CustomerType();

        customerType.setName( customerRequestDto.getCustomerType() );

        return customerType;
    }

    private String customerCustomerTypeName(Customer customer) {
        if ( customer == null ) {
            return null;
        }
        CustomerType customerType = customer.getCustomerType();
        if ( customerType == null ) {
            return null;
        }
        String name = customerType.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    protected Customer invoiceRequestDtoToCustomer(InvoiceRequestDto invoiceRequestDto) {
        if ( invoiceRequestDto == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setPhone( invoiceRequestDto.getCustomerPhone() );

        return customer;
    }

    protected OrderType invoiceRequestDtoToOrderType(InvoiceRequestDto invoiceRequestDto) {
        if ( invoiceRequestDto == null ) {
            return null;
        }

        OrderType orderType = new OrderType();

        orderType.setName( invoiceRequestDto.getOrderType() );

        return orderType;
    }

    private String invoiceOrderTypeName(Invoice invoice) {
        if ( invoice == null ) {
            return null;
        }
        OrderType orderType = invoice.getOrderType();
        if ( orderType == null ) {
            return null;
        }
        String name = orderType.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String invoiceCustomerPhone(Invoice invoice) {
        if ( invoice == null ) {
            return null;
        }
        Customer customer = invoice.getCustomer();
        if ( customer == null ) {
            return null;
        }
        String phone = customer.getPhone();
        if ( phone == null ) {
            return null;
        }
        return phone;
    }

    private String invoiceCustomerName(Invoice invoice) {
        if ( invoice == null ) {
            return null;
        }
        Customer customer = invoice.getCustomer();
        if ( customer == null ) {
            return null;
        }
        String name = customer.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String invoiceBranchShopName(Invoice invoice) {
        if ( invoice == null ) {
            return null;
        }
        BranchShop branchShop = invoice.getBranchShop();
        if ( branchShop == null ) {
            return null;
        }
        String name = branchShop.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    protected Invoice invoiceDetailRequestDtoToInvoice(InvoiceDetailRequestDto invoiceDetailRequestDto) {
        if ( invoiceDetailRequestDto == null ) {
            return null;
        }

        Invoice invoice = new Invoice();

        invoice.setId( invoiceDetailRequestDto.getInvoiceId() );

        return invoice;
    }

    protected Drink invoiceDetailRequestDtoToDrink(InvoiceDetailRequestDto invoiceDetailRequestDto) {
        if ( invoiceDetailRequestDto == null ) {
            return null;
        }

        Drink drink = new Drink();

        drink.setId( invoiceDetailRequestDto.getDrinkId() );

        return drink;
    }

    protected InvoiceDetailId invoiceDetailRequestDtoToInvoiceDetailId(InvoiceDetailRequestDto invoiceDetailRequestDto) {
        if ( invoiceDetailRequestDto == null ) {
            return null;
        }

        InvoiceDetailId invoiceDetailId = new InvoiceDetailId();

        invoiceDetailId.setId( invoiceDetailRequestDto.getId() );

        return invoiceDetailId;
    }

    private Integer invoiceDetailDrinkId(InvoiceDetail invoiceDetail) {
        if ( invoiceDetail == null ) {
            return null;
        }
        Drink drink = invoiceDetail.getDrink();
        if ( drink == null ) {
            return null;
        }
        Integer id = drink.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer invoiceDetailInvoiceId(InvoiceDetail invoiceDetail) {
        if ( invoiceDetail == null ) {
            return null;
        }
        Invoice invoice = invoiceDetail.getInvoice();
        if ( invoice == null ) {
            return null;
        }
        Integer id = invoice.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer invoiceDetailInvoiceDetailIdId(InvoiceDetail invoiceDetail) {
        if ( invoiceDetail == null ) {
            return null;
        }
        InvoiceDetailId invoiceDetailId = invoiceDetail.getInvoiceDetailId();
        if ( invoiceDetailId == null ) {
            return null;
        }
        Integer id = invoiceDetailId.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected TableType coffeeTableDtoToTableType(CoffeeTableDto coffeeTableDto) {
        if ( coffeeTableDto == null ) {
            return null;
        }

        TableType tableType = new TableType();

        tableType.setName( coffeeTableDto.getTableType() );

        return tableType;
    }

    private String coffeeTableTableTypeName(CoffeeTable coffeeTable) {
        if ( coffeeTable == null ) {
            return null;
        }
        TableType tableType = coffeeTable.getTableType();
        if ( tableType == null ) {
            return null;
        }
        String name = tableType.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    protected MaterialType materialDtoToMaterialType(MaterialDto materialDto) {
        if ( materialDto == null ) {
            return null;
        }

        MaterialType materialType = new MaterialType();

        materialType.setName( materialDto.getMaterialType() );

        return materialType;
    }

    protected Unit materialDtoToUnit(MaterialDto materialDto) {
        if ( materialDto == null ) {
            return null;
        }

        Unit unit = new Unit();

        unit.setName( materialDto.getUnit() );

        return unit;
    }

    private String materialUnitName(Material material) {
        if ( material == null ) {
            return null;
        }
        Unit unit = material.getUnit();
        if ( unit == null ) {
            return null;
        }
        String name = unit.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String materialMaterialTypeName(Material material) {
        if ( material == null ) {
            return null;
        }
        MaterialType materialType = material.getMaterialType();
        if ( materialType == null ) {
            return null;
        }
        String name = materialType.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    protected Supplier supplyContractRequestDtoToSupplier(SupplyContractRequestDto supplyContractRequestDto) {
        if ( supplyContractRequestDto == null ) {
            return null;
        }

        Supplier supplier = new Supplier();

        supplier.setName( supplyContractRequestDto.getSupplier() );

        return supplier;
    }

    protected BranchShop supplyContractRequestDtoToBranchShop(SupplyContractRequestDto supplyContractRequestDto) {
        if ( supplyContractRequestDto == null ) {
            return null;
        }

        BranchShop branchShop = new BranchShop();

        branchShop.setName( supplyContractRequestDto.getBranchShop() );

        return branchShop;
    }

    private String supplyContractBranchShopName(SupplyContract supplyContract) {
        if ( supplyContract == null ) {
            return null;
        }
        BranchShop branchShop = supplyContract.getBranchShop();
        if ( branchShop == null ) {
            return null;
        }
        String name = branchShop.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String supplyContractSupplierName(SupplyContract supplyContract) {
        if ( supplyContract == null ) {
            return null;
        }
        Supplier supplier = supplyContract.getSupplier();
        if ( supplier == null ) {
            return null;
        }
        String name = supplier.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    protected Unit recipeDtoToUnit(RecipeDto recipeDto) {
        if ( recipeDto == null ) {
            return null;
        }

        Unit unit = new Unit();

        unit.setName( recipeDto.getUnit() );

        return unit;
    }

    protected Drink recipeDtoToDrink(RecipeDto recipeDto) {
        if ( recipeDto == null ) {
            return null;
        }

        Drink drink = new Drink();

        drink.setName( recipeDto.getDrink() );

        return drink;
    }

    protected Material recipeDtoToMaterial(RecipeDto recipeDto) {
        if ( recipeDto == null ) {
            return null;
        }

        Material material = new Material();

        material.setName( recipeDto.getMaterial() );

        return material;
    }

    private String recipeUnitName(Recipe recipe) {
        if ( recipe == null ) {
            return null;
        }
        Unit unit = recipe.getUnit();
        if ( unit == null ) {
            return null;
        }
        String name = unit.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String recipeMaterialName(Recipe recipe) {
        if ( recipe == null ) {
            return null;
        }
        Material material = recipe.getMaterial();
        if ( material == null ) {
            return null;
        }
        String name = material.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String recipeDrinkName(Recipe recipe) {
        if ( recipe == null ) {
            return null;
        }
        Drink drink = recipe.getDrink();
        if ( drink == null ) {
            return null;
        }
        String name = drink.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    protected SupplyContractDetailId supplyContractDetailRequestDtoToSupplyContractDetailId(SupplyContractDetailRequestDto supplyContractDetailRequestDto) {
        if ( supplyContractDetailRequestDto == null ) {
            return null;
        }

        SupplyContractDetailId supplyContractDetailId = new SupplyContractDetailId();

        supplyContractDetailId.setId( supplyContractDetailRequestDto.getId() );
        supplyContractDetailId.setSupplyContractId( supplyContractDetailRequestDto.getSupplyContractId() );
        supplyContractDetailId.setMaterialId( supplyContractDetailRequestDto.getMaterialId() );

        return supplyContractDetailId;
    }

    private Integer supplyContractDetailSupplyContractDetailIdId(SupplyContractDetail supplyContractDetail) {
        if ( supplyContractDetail == null ) {
            return null;
        }
        SupplyContractDetailId supplyContractDetailId = supplyContractDetail.getSupplyContractDetailId();
        if ( supplyContractDetailId == null ) {
            return null;
        }
        Integer id = supplyContractDetailId.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer supplyContractDetailSupplyContractDetailIdMaterialId(SupplyContractDetail supplyContractDetail) {
        if ( supplyContractDetail == null ) {
            return null;
        }
        SupplyContractDetailId supplyContractDetailId = supplyContractDetail.getSupplyContractDetailId();
        if ( supplyContractDetailId == null ) {
            return null;
        }
        Integer materialId = supplyContractDetailId.getMaterialId();
        if ( materialId == null ) {
            return null;
        }
        return materialId;
    }

    private Integer supplyContractDetailSupplyContractDetailIdSupplyContractId(SupplyContractDetail supplyContractDetail) {
        if ( supplyContractDetail == null ) {
            return null;
        }
        SupplyContractDetailId supplyContractDetailId = supplyContractDetail.getSupplyContractDetailId();
        if ( supplyContractDetailId == null ) {
            return null;
        }
        Integer supplyContractId = supplyContractDetailId.getSupplyContractId();
        if ( supplyContractId == null ) {
            return null;
        }
        return supplyContractId;
    }

    protected DrinkPriceId drinkPriceRequestDtoToDrinkPriceId(DrinkPriceRequestDto drinkPriceRequestDto) {
        if ( drinkPriceRequestDto == null ) {
            return null;
        }

        DrinkPriceId drinkPriceId = new DrinkPriceId();

        drinkPriceId.setIdDrink( drinkPriceRequestDto.getDrinkId() );
        drinkPriceId.setDate( drinkPriceRequestDto.getDate() );
        drinkPriceId.setId( drinkPriceRequestDto.getId() );

        return drinkPriceId;
    }

    private LocalDate drinkPriceDrinkPriceIdDate(DrinkPrice drinkPrice) {
        if ( drinkPrice == null ) {
            return null;
        }
        DrinkPriceId drinkPriceId = drinkPrice.getDrinkPriceId();
        if ( drinkPriceId == null ) {
            return null;
        }
        LocalDate date = drinkPriceId.getDate();
        if ( date == null ) {
            return null;
        }
        return date;
    }

    private Integer drinkPriceDrinkPriceIdIdDrink(DrinkPrice drinkPrice) {
        if ( drinkPrice == null ) {
            return null;
        }
        DrinkPriceId drinkPriceId = drinkPrice.getDrinkPriceId();
        if ( drinkPriceId == null ) {
            return null;
        }
        Integer idDrink = drinkPriceId.getIdDrink();
        if ( idDrink == null ) {
            return null;
        }
        return idDrink;
    }

    private Integer drinkPriceDrinkPriceIdId(DrinkPrice drinkPrice) {
        if ( drinkPrice == null ) {
            return null;
        }
        DrinkPriceId drinkPriceId = drinkPrice.getDrinkPriceId();
        if ( drinkPriceId == null ) {
            return null;
        }
        Integer id = drinkPriceId.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected MaterialPriceId materialPriceRequestDtoToMaterialPriceId(MaterialPriceRequestDto materialPriceRequestDto) {
        if ( materialPriceRequestDto == null ) {
            return null;
        }

        MaterialPriceId materialPriceId = new MaterialPriceId();

        materialPriceId.setId( materialPriceRequestDto.getId() );
        materialPriceId.setIdMaterial( materialPriceRequestDto.getMaterialId() );
        materialPriceId.setFirstDate( materialPriceRequestDto.getFirstDate() );

        return materialPriceId;
    }

    protected MaterialPriceId materialPriceRequestDtoToMaterialPriceId1(MaterialPriceRequestDto materialPriceRequestDto) {
        if ( materialPriceRequestDto == null ) {
            return null;
        }

        MaterialPriceId materialPriceId = new MaterialPriceId();

        materialPriceId.setId( materialPriceRequestDto.getId() );
        materialPriceId.setIdMaterial( materialPriceRequestDto.getMaterialId() );
        materialPriceId.setFirstDate( materialPriceRequestDto.getFirstDate() );

        return materialPriceId;
    }

    private LocalDate materialPriceMaterialPriceIdFirstDate(MaterialPrice materialPrice) {
        if ( materialPrice == null ) {
            return null;
        }
        MaterialPriceId materialPriceId = materialPrice.getMaterialPriceId();
        if ( materialPriceId == null ) {
            return null;
        }
        LocalDate firstDate = materialPriceId.getFirstDate();
        if ( firstDate == null ) {
            return null;
        }
        return firstDate;
    }

    private Integer materialPriceMaterialPriceIdId(MaterialPrice materialPrice) {
        if ( materialPrice == null ) {
            return null;
        }
        MaterialPriceId materialPriceId = materialPrice.getMaterialPriceId();
        if ( materialPriceId == null ) {
            return null;
        }
        Integer id = materialPriceId.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer materialPriceMaterialPriceIdIdMaterial(MaterialPrice materialPrice) {
        if ( materialPrice == null ) {
            return null;
        }
        MaterialPriceId materialPriceId = materialPrice.getMaterialPriceId();
        if ( materialPriceId == null ) {
            return null;
        }
        Integer idMaterial = materialPriceId.getIdMaterial();
        if ( idMaterial == null ) {
            return null;
        }
        return idMaterial;
    }

    protected MinMaxInventoryId minMaxInventoryRequestDtoToMinMaxInventoryId(MinMaxInventoryRequestDto minMaxInventoryRequestDto) {
        if ( minMaxInventoryRequestDto == null ) {
            return null;
        }

        MinMaxInventoryId minMaxInventoryId = new MinMaxInventoryId();

        minMaxInventoryId.setId( minMaxInventoryRequestDto.getId() );
        minMaxInventoryId.setIdMaterial( minMaxInventoryRequestDto.getMaterialId() );
        minMaxInventoryId.setIdBranchShop( minMaxInventoryRequestDto.getBranchShopId() );

        return minMaxInventoryId;
    }

    private Integer minMaxInventoryMinMaxInventoryIdIdBranchShop(MinMaxInventory minMaxInventory) {
        if ( minMaxInventory == null ) {
            return null;
        }
        MinMaxInventoryId minMaxInventoryId = minMaxInventory.getMinMaxInventoryId();
        if ( minMaxInventoryId == null ) {
            return null;
        }
        Integer idBranchShop = minMaxInventoryId.getIdBranchShop();
        if ( idBranchShop == null ) {
            return null;
        }
        return idBranchShop;
    }

    private Integer minMaxInventoryMinMaxInventoryIdId(MinMaxInventory minMaxInventory) {
        if ( minMaxInventory == null ) {
            return null;
        }
        MinMaxInventoryId minMaxInventoryId = minMaxInventory.getMinMaxInventoryId();
        if ( minMaxInventoryId == null ) {
            return null;
        }
        Integer id = minMaxInventoryId.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer minMaxInventoryMinMaxInventoryIdIdMaterial(MinMaxInventory minMaxInventory) {
        if ( minMaxInventory == null ) {
            return null;
        }
        MinMaxInventoryId minMaxInventoryId = minMaxInventory.getMinMaxInventoryId();
        if ( minMaxInventoryId == null ) {
            return null;
        }
        Integer idMaterial = minMaxInventoryId.getIdMaterial();
        if ( idMaterial == null ) {
            return null;
        }
        return idMaterial;
    }

    protected BranchShop internalSCRequestDtoToBranchShop(InternalSCRequestDto internalSCRequestDto) {
        if ( internalSCRequestDto == null ) {
            return null;
        }

        BranchShop branchShop = new BranchShop();

        branchShop.setName( internalSCRequestDto.getBranchShop() );

        return branchShop;
    }

    private String internalSCBranchShopName(InternalSC internalSC) {
        if ( internalSC == null ) {
            return null;
        }
        BranchShop branchShop = internalSC.getBranchShop();
        if ( branchShop == null ) {
            return null;
        }
        String name = branchShop.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    protected InternalSCDetailId internalSCDetailRequestDtoToInternalSCDetailId(InternalSCDetailRequestDto internalSCDetailRequestDto) {
        if ( internalSCDetailRequestDto == null ) {
            return null;
        }

        InternalSCDetailId internalSCDetailId = new InternalSCDetailId();

        internalSCDetailId.setId( internalSCDetailRequestDto.getId() );
        internalSCDetailId.setMaterialId( internalSCDetailRequestDto.getMaterialId() );
        internalSCDetailId.setInternalSCId( internalSCDetailRequestDto.getInternalSCId() );

        return internalSCDetailId;
    }

    private Integer internalSCDetailInternalSCDetailIdInternalSCId(InternalSCDetail internalSCDetail) {
        if ( internalSCDetail == null ) {
            return null;
        }
        InternalSCDetailId internalSCDetailId = internalSCDetail.getInternalSCDetailId();
        if ( internalSCDetailId == null ) {
            return null;
        }
        Integer internalSCId = internalSCDetailId.getInternalSCId();
        if ( internalSCId == null ) {
            return null;
        }
        return internalSCId;
    }

    private Integer internalSCDetailInternalSCDetailIdId(InternalSCDetail internalSCDetail) {
        if ( internalSCDetail == null ) {
            return null;
        }
        InternalSCDetailId internalSCDetailId = internalSCDetail.getInternalSCDetailId();
        if ( internalSCDetailId == null ) {
            return null;
        }
        Integer id = internalSCDetailId.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer internalSCDetailInternalSCDetailIdMaterialId(InternalSCDetail internalSCDetail) {
        if ( internalSCDetail == null ) {
            return null;
        }
        InternalSCDetailId internalSCDetailId = internalSCDetail.getInternalSCDetailId();
        if ( internalSCDetailId == null ) {
            return null;
        }
        Integer materialId = internalSCDetailId.getMaterialId();
        if ( materialId == null ) {
            return null;
        }
        return materialId;
    }

    protected InventoryId inventoryRequestDtoToInventoryId(InventoryRequestDto inventoryRequestDto) {
        if ( inventoryRequestDto == null ) {
            return null;
        }

        InventoryId inventoryId = new InventoryId();

        inventoryId.setFirstDate( inventoryRequestDto.getFirstDate() );
        inventoryId.setIdBranchShop( inventoryRequestDto.getBranchShopId() );
        inventoryId.setIdMaterial( inventoryRequestDto.getMaterialId() );

        return inventoryId;
    }

    private Integer inventoryInventoryIdIdMaterial(Inventory inventory) {
        if ( inventory == null ) {
            return null;
        }
        InventoryId inventoryId = inventory.getInventoryId();
        if ( inventoryId == null ) {
            return null;
        }
        Integer idMaterial = inventoryId.getIdMaterial();
        if ( idMaterial == null ) {
            return null;
        }
        return idMaterial;
    }

    private Integer inventoryInventoryIdIdBranchShop(Inventory inventory) {
        if ( inventory == null ) {
            return null;
        }
        InventoryId inventoryId = inventory.getInventoryId();
        if ( inventoryId == null ) {
            return null;
        }
        Integer idBranchShop = inventoryId.getIdBranchShop();
        if ( idBranchShop == null ) {
            return null;
        }
        return idBranchShop;
    }
}
