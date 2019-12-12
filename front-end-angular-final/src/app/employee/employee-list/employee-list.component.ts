import { Component, OnInit, Input, AfterViewInit, ChangeDetectorRef } from '@angular/core';
import { EmployeeRestApiService } from '../employee-rest-api.service';
import * as $ from 'jquery';
import 'datatables.net';
import 'datatables.net-bs4';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { Router } from '@angular/router';
import { MatDialog, MatDialogConfig } from '@angular/material';
import { EmployeeCreateComponent } from '../employee-create/employee-create.component';
import { CheckService } from 'src/app/check.service';
import { SupplierViewComponent } from 'src/app/supplier/supplier-view/supplier-view.component';
@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {

  //khai báo tên gọi cho 
  clients: any[];
  dataTable: any;
  Content: any = [];
  info: any;
  private roles: string[];
  private authority: string;
  private authorityad: string;
  private authorityhr: string;
  private authoritychef: string;
  private authoritybrm: string;
  private authorityacc: string;
  private authoritycashier: string;

  constructor(public restApi: EmployeeRestApiService, public CheckRegion: CheckService, private dialog: MatDialog, private chRef: ChangeDetectorRef, private token: TokenStorageService,
    public router: Router) { }
  ngOnInit() {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()
    };
    //token start
    if (this.token.getToken()) {
      this.token.checklogin()
    }
    //token end
    if (!this.token.getToken()) {
      this.router.navigate(['login'])
    }else{
      this.roles = this.token.getAuthorities();
      this.roles.every(role => {
        if (role === 'ROLE_ADMIN') {
          this.authorityad = 'ad';
          return true;
        }  if (role === 'ROLE_HR') {
          this.authorityhr = 'hr';
          return true;
        }  if (role === 'ROLE_BRANCH_MANAGER') {
          this.authoritybrm = 'brm';
          return true;
        }  if (role === 'ROLE_CASHIER') {
          this.authoritycashier = 'cashier';
          return true;
        }  if (role === 'ROLE_ACCOUNTANT') {
          this.authorityacc = 'acc';
          return true;
        }  if (role === 'ROLE_CHEF') {
          this.authoritychef = 'chef';
          return true;
        }
        this.authority = 'user';
        return false;
      });
    }
    if ( this.authoritybrm === 'brm' ||
       this.authorityad === 'ad' || this.authorityhr === 'hr') {
      
    }else{
      this.router.navigate(['home'])
    }
    this.loadEmployeetype()
  }

  loadEmployeetype() {
    return this.restApi.getEmployeetypes().subscribe((data: {}) => {
      this.Content = data;
      this.chRef.detectChanges();
      const table: any = $('table');
      this.dataTable = table.DataTable();

    })
  }
  onCreate() {
    //token start
    this.token.checklogin()
    if (!this.token.getToken()) {
      this.router.navigate(['login'])
    } else {
      //token end
    this.restApi.employeetypeDetails.id = null;
    this.restApi.employeetypeDetails.name = '';
    this.restApi.employeetypeDetails.employeeType = '';
    this.restApi.employeetypeDetails.email = '';
    this.restApi.employeetypeDetails.branchShop = '';
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";
    //Chọn Component làm popup
    this.dialog.open(EmployeeCreateComponent, dialogConfig);
  }
}
  onUpdate(employeetype) {
    //token start
    this.token.checklogin()
    if (!this.token.getToken()) {
      this.router.navigate(['login'])
    } else {
      //token end
    this.restApi.employeetypeDetails = employeetype;
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";
    //Chọn Component làm popup dùng chung create cho update
    this.dialog.open(EmployeeCreateComponent, dialogConfig);
  }
}
  
  // Delete employee
  deleteEmployeetype(id) {
    //token start
    this.token.checklogin()
    if (!this.token.getToken()) {
      this.router.navigate(['login'])
    } else {
      //token end
    if (window.confirm('Are you sure, you want to delete?')) {
      this.restApi.deleteEmployeetype(id).subscribe(data => {
        this.CheckRegion.danhco = 'employee';
        this.router.navigate(['/home'])
      })
    }
  }
}
  logout() {
    this.token.signOut();
    this.router.navigate(['login'])
    //window.location.reload();
  }

}

