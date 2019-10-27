import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
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
    MaterialListComponent
  ],
  imports: [  
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    NgbModule,
    ReactiveFormsModule,
    DatePickerModule,
    FormsModule
  ],
  providers: [httpInterceptorProviders],
  bootstrap: [
    AppComponent,
    LoginComponent,
    HomeComponent
  ]
})
export class AppModule { }
