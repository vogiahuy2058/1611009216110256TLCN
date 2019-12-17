import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { httpInterceptorProviders } from './auth/auth-interceptor';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { DatePickerModule } from '@syncfusion/ej2-angular-calendars';
import { LoginComponent } from './auth/login/login.component';
import { HomeComponent } from './home/home.component';
import { EmployeeCreateComponent } from './employee/employee-create/employee-create.component';
import { EmployeeEditComponent } from './employee/employee-edit/employee-edit.component';
import { EmployeeListComponent } from './employee/employee-list/employee-list.component';
import { EmployeetypeListComponent } from './employeetype/employeetype-list/employeetype-list.component';
import { EmployeetypeCreateComponent } from './employeetype/employeetype-create/employeetype-create.component';
import { EmployeetypeEditComponent } from './employeetype/employeetype-edit/employeetype-edit.component';
import { BranchshopListComponent } from './branchshop/branchshop-list/branchshop-list.component';
import { BranchshopCreateComponent } from './branchshop/branchshop-create/branchshop-create.component';
import { BranchshopEditComponent } from './branchshop/branchshop-edit/branchshop-edit.component';
import { CustomertypeCreateComponent } from './customertype/customertype-create/customertype-create.component';
import { CustomertypeListComponent } from './customertype/customertype-list/customertype-list.component';
import { CustomertypeEditComponent } from './customertype/customertype-edit/customertype-edit.component';
import { CustomerCreateComponent } from './customer/customer-create/customer-create.component';
import { CustomerListComponent } from './customer/customer-list/customer-list.component';
import { CustomerEditComponent } from './customer/customer-edit/customer-edit.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialtypeCreateComponent } from './materialtype/materialtype-create/materialtype-create.component';
import { MaterialtypeEditComponent } from './materialtype/materialtype-edit/materialtype-edit.component';
import { MaterialtypeListComponent } from './materialtype/materialtype-list/materialtype-list.component';
import { OrdertypeCreateComponent } from './ordertype/ordertype-create/ordertype-create.component';
import { OrdertypeListComponent } from './ordertype/ordertype-list/ordertype-list.component';
import { OrdertypeEditComponent } from './ordertype/ordertype-edit/ordertype-edit.component';
import { TabletypeCreateComponent } from './tabletype/tabletype-create/tabletype-create.component';
import { TabletypeEditComponent } from './tabletype/tabletype-edit/tabletype-edit.component';
import { TabletypeListComponent } from './tabletype/tabletype-list/tabletype-list.component';
import { SupplierCreateComponent } from './supplier/supplier-create/supplier-create.component';
import { SupplierEditComponent } from './supplier/supplier-edit/supplier-edit.component';
import { SupplierListComponent } from './supplier/supplier-list/supplier-list.component';
import { SupplierViewComponent } from './supplier/supplier-view/supplier-view.component';
import { TableCreateComponent } from './table/table-create/table-create.component';
import { TableEditComponent } from './table/table-edit/table-edit.component';
import { TableListComponent } from './table/table-list/table-list.component';
import { DrinktypeCreateComponent } from './drinktype/drinktype-create/drinktype-create.component';
import { DrinktypeEditComponent } from './drinktype/drinktype-edit/drinktype-edit.component';
import { DrinktypeListComponent } from './drinktype/drinktype-list/drinktype-list.component';
import { MaterialCreateComponent } from './material/material-create/material-create.component';
import { MaterialEditComponent } from './material/material-edit/material-edit.component';
import { MaterialListComponent } from './material/material-list/material-list.component';
import { SupplycontractCreateComponent } from './supplycontract/supplycontract-create/supplycontract-create.component';
import { SupplycontractEditComponent } from './supplycontract/supplycontract-edit/supplycontract-edit.component';
import { SupplycontractListComponent } from './supplycontract/supplycontract-list/supplycontract-list.component';
import { SupplycontractdetailCreateComponent } from './supplycontractdetail/supplycontractdetail-create/supplycontractdetail-create.component';
import { SupplycontractdetailEditComponent } from './supplycontractdetail/supplycontractdetail-edit/supplycontractdetail-edit.component';
import { SupplycontractdetailListComponent } from './supplycontractdetail/supplycontractdetail-list/supplycontractdetail-list.component';
import { RecipeCreateComponent } from './recipe/recipe-create/recipe-create.component';
import { RecipeEditComponent } from './recipe/recipe-edit/recipe-edit.component';
import { RecipeListComponent } from './recipe/recipe-list/recipe-list.component';
import { MaterialpriceCreateComponent } from './materialprice//materialprice-create/materialprice-create.component';
import { MaterialpriceEditComponent } from './materialprice/materialprice-edit/materialprice-edit.component';
import { MaterialpriceListComponent } from './materialprice/materialprice-list/materialprice-list.component';
import { UnitCreateComponent } from './unit/unit-create/unit-create.component';
import { UnitEditComponent } from './unit/unit-edit/unit-edit.component';
import { UnitListComponent } from './unit/unit-list/unit-list.component';
import { DrinkCreateComponent } from './drink/drink-create/drink-create.component';
import { DrinkListComponent } from './drink/drink-list/drink-list.component';
import { DrinkEditComponent } from './drink/drink-edit/drink-edit.component';
import { InvoiceCreateComponent } from './invoice/invoice-create/invoice-create.component';
import { InvoiceListComponent } from './invoice/invoice-list/invoice-list.component';
import { InvoiceEditComponent } from './invoice/invoice-edit/invoice-edit.component';

import * as Material from "@angular/material";
import { CommonModule } from '@angular/common';
import { TestpopupsComponent } from './testpopups/testpopups.component';
import { TestpopupListComponent } from './testpopups/testpopup-list/testpopup-list.component';
import { TestpopupComponent } from './testpopups/testpopup/testpopup.component';
import { DatePipe } from '@angular/common';
import { PopupService } from './testpopups/popup.service';
import { DepartmentService } from './testpopups/department.service';
import { InvoicedetailComponent } from './invoice/invoicedetail/invoicedetail.component';
import { InvoiceRestApiService } from './invoice/invoice-rest-api.service';
import { ScrollingModule } from '@angular/cdk/scrolling';
import { EmployeetypeRestApiService } from './employeetype/employeetype-rest-api.service';
import { AccountCreateComponent } from './account/account-create/account-create.component';
import { AccountListComponent } from './account/account-list/account-list.component';
import { DrinkpriceCreateComponent } from './drinkprice/drinkprice-create/drinkprice-create.component';
import {
  DateAdapter,
  MAT_DATE_FORMATS,
  MAT_DATE_LOCALE
} from '@angular/material';
import {
  MomentDateModule,
  MomentDateAdapter
} from '@angular/material-moment-adapter';
export const MY_FORMATS = {
  parse: {
    dateInput: 'DD/MM/YYYY',
  },
  display: {
    dateInput: 'DD/MM/YYYY',
    monthYearLabel: 'MM YYYY',
    dateA11yLabel: 'DD/MM/YYYY',
    monthYearA11yLabel: 'MM YYYY',
  },
};
import { MatSelectModule } from '@angular/material';

@NgModule({
  declarations: [
    AppComponent,
    EmployeeCreateComponent,
    EmployeeEditComponent,
    EmployeeListComponent,
    EmployeetypeListComponent,
    EmployeetypeCreateComponent,
    EmployeetypeEditComponent,
    LoginComponent,
    HomeComponent,
    BranchshopListComponent,
    BranchshopCreateComponent,
    BranchshopEditComponent,
    CustomertypeCreateComponent,
    CustomertypeListComponent,
    CustomertypeEditComponent,
    CustomerCreateComponent,
    CustomerListComponent,
    CustomerEditComponent,
    MaterialtypeCreateComponent,
    MaterialtypeEditComponent,
    MaterialtypeListComponent,
    OrdertypeCreateComponent,
    OrdertypeListComponent,
    OrdertypeEditComponent,
    TabletypeCreateComponent,
    TabletypeEditComponent,
    TabletypeListComponent,
    SupplierCreateComponent,
    SupplierEditComponent,
    SupplierListComponent,
    SupplierViewComponent,
    TableCreateComponent,
    TableEditComponent,
    TableListComponent,
    DrinktypeCreateComponent,
    DrinktypeEditComponent,
    DrinktypeListComponent,
    MaterialCreateComponent,
    MaterialEditComponent,
    MaterialListComponent,
    SupplycontractCreateComponent,
    SupplycontractEditComponent,
    SupplycontractListComponent,
    SupplycontractdetailCreateComponent,
    SupplycontractdetailEditComponent,
    SupplycontractdetailListComponent,
    RecipeCreateComponent,
    RecipeEditComponent,
    RecipeListComponent,
    MaterialpriceCreateComponent,
    MaterialpriceEditComponent,
    MaterialpriceListComponent,
    UnitCreateComponent,
    UnitEditComponent,
    UnitListComponent,
    DrinkCreateComponent,
    DrinkListComponent,
    DrinkEditComponent,
    InvoiceCreateComponent,
    InvoiceListComponent,
    InvoiceEditComponent,
    TestpopupsComponent,
    TestpopupListComponent,
    TestpopupComponent,
    InvoicedetailComponent,
    AccountCreateComponent,
    AccountListComponent,
    DrinkpriceCreateComponent,

  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    NgbModule,
    ReactiveFormsModule,
    DatePickerModule,
    FormsModule,
    CommonModule,
    Material.MatToolbarModule,
    Material.MatGridListModule,
    Material.MatFormFieldModule,
    Material.MatInputModule,
    Material.MatRadioModule,
    Material.MatSelectModule,
    Material.MatCheckboxModule,
    Material.MatDatepickerModule,
    Material.MatNativeDateModule,
    Material.MatButtonModule,
    Material.MatSnackBarModule,
    Material.MatTableModule,
    Material.MatIconModule,
    Material.MatPaginatorModule,
    Material.MatSortModule,
    Material.MatDialogModule,
    ScrollingModule,
    MatSelectModule

  ],
  exports: [
    Material.MatToolbarModule,
    Material.MatGridListModule,
    Material.MatFormFieldModule,
    Material.MatInputModule,
    Material.MatRadioModule,
    Material.MatSelectModule,
    Material.MatCheckboxModule,
    Material.MatDatepickerModule,
    Material.MatNativeDateModule,
    Material.MatButtonModule,
    Material.MatSnackBarModule,
    Material.MatTableModule,
    Material.MatIconModule,
    Material.MatPaginatorModule,
    Material.MatSortModule,
    Material.MatDialogModule,

  ],
  providers: [
    PopupService,
    DepartmentService,
    DatePipe,
    httpInterceptorProviders,
    InvoiceRestApiService,
    EmployeetypeRestApiService,
    {
      provide: MAT_DATE_LOCALE,
      useValue: 'it'
    },

  ],
  bootstrap: [
    AppComponent,
    LoginComponent,
    HomeComponent
  ],
  entryComponents: [
    //thêm tên của component làm popup
    TestpopupComponent,
    InvoicedetailComponent,
    EmployeetypeCreateComponent,
    BranchshopCreateComponent,
    CustomertypeCreateComponent,
    CustomerCreateComponent,
    MaterialtypeCreateComponent,
    OrdertypeCreateComponent,
    TabletypeCreateComponent,
    SupplierCreateComponent,
    DrinktypeCreateComponent,
    MaterialCreateComponent,
    SupplycontractCreateComponent,
    SupplycontractdetailCreateComponent,
    RecipeCreateComponent,
    MaterialpriceCreateComponent,
    UnitCreateComponent,
    DrinkCreateComponent,
    InvoiceCreateComponent,
    SupplierViewComponent,
    AccountCreateComponent,
  ]
})
export class AppModule { }
