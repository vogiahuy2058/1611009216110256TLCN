import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EmployeetypeListComponent } from './employeetype/employeetype-list/employeetype-list.component';
import { EmployeetypeCreateComponent } from './employeetype/employeetype-create/employeetype-create.component';
import { EmployeetypeEditComponent } from './employeetype/employeetype-edit/employeetype-edit.component';
import { LoginComponent } from './auth/login/login.component';
import { HomeComponent } from './home/home.component';
import { BranchshopListComponent } from './branchshop/branchshop-list/branchshop-list.component';
import { BranchshopCreateComponent } from './branchshop/branchshop-create/branchshop-create.component';
import { BranchshopEditComponent } from './branchshop/branchshop-edit/branchshop-edit.component';
import { EmployeeCreateComponent } from './employee/employee-create/employee-create.component';
import { EmployeeEditComponent } from './employee/employee-edit/employee-edit.component';
import { EmployeeListComponent } from './employee/employee-list/employee-list.component';
import { CustomertypeCreateComponent } from './customertype/customertype-create/customertype-create.component';
import { CustomertypeListComponent } from './customertype/customertype-list/customertype-list.component';
import { CustomertypeEditComponent } from './customertype/customertype-edit/customertype-edit.component';
import { CustomerCreateComponent } from './customer/customer-create/customer-create.component';
import { CustomerListComponent } from './customer/customer-list/customer-list.component';
import { CustomerEditComponent } from './customer/customer-edit/customer-edit.component';
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
const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'employeetype-create', component: EmployeetypeCreateComponent },
  { path: 'employeetype-edit/:id', component: EmployeetypeEditComponent },
  { path: 'login', component: LoginComponent },
  { path: 'employeetype-list', component: EmployeetypeListComponent },
  { path: 'home', component: HomeComponent },
  { path: 'branchshop-list', component: BranchshopListComponent },
  { path: 'branchshop-edit/:id', component: BranchshopEditComponent },
  { path: 'branchshop-create', component: BranchshopCreateComponent },
  { path: 'employee-create', component: EmployeeCreateComponent },
  { path: 'employee-edit/:id', component: EmployeeEditComponent },
  { path: 'employee-list', component: EmployeeListComponent },
  { path: 'customertype-create', component: CustomertypeCreateComponent },
  { path: 'customertype-edit/:id', component: CustomertypeEditComponent },
  { path: 'customertype-list', component: CustomertypeListComponent },
  { path: 'customer-create', component: CustomerCreateComponent },
  { path: 'customer-edit/:id', component: CustomerEditComponent },
  { path: 'customer-list', component: CustomerListComponent },
  { path: 'materialtype-create', component: MaterialtypeCreateComponent },
  { path: 'materialtype-edit/:id', component: MaterialtypeEditComponent },
  { path: 'materialtype-list', component: MaterialtypeListComponent },
  { path: 'ordertype-create', component: OrdertypeCreateComponent },
  { path: 'ordertype-edit/:id', component: OrdertypeEditComponent },
  { path: 'ordertype-list', component: OrdertypeListComponent },
  { path: 'tabletype-create', component: TabletypeCreateComponent },
  { path: 'tabletype-edit/:id', component: TabletypeEditComponent },
  { path: 'tabletype-list', component: TabletypeListComponent },
  { path: 'supplier-list', component: SupplierListComponent },
  { path: 'supplier-create', component: SupplierCreateComponent },
  { path: 'supplier-edit/:id', component: SupplierEditComponent },
  { path: 'supplier-view/:id', component: SupplierViewComponent },
  { path: 'table-list', component: TableListComponent },
  { path: 'table-create', component: TableCreateComponent },
  { path: 'table-edit/:id', component: TableEditComponent },
  { path: 'drinktype-list', component: DrinktypeListComponent },
  { path: 'drinktype-create', component: DrinktypeCreateComponent },
  { path: 'drinktype-edit/:id', component: DrinktypeEditComponent },
  { path: 'material-list', component: MaterialListComponent },
  { path: 'material-create', component: MaterialCreateComponent },
  { path: 'material-edit/:id', component: MaterialEditComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
