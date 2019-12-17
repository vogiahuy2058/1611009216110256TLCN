import { Component, OnInit, Input, AfterViewInit, ChangeDetectorRef } from '@angular/core';

import * as $ from 'jquery';
import 'datatables.net';
import 'datatables.net-bs4';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { Router } from '@angular/router';
import { BranchshopRestApiService } from '../branchshop-rest-api.service';
import { MatDialog, MatDialogConfig } from "@angular/material";
import { EmployeetypeCreateComponent } from 'src/app/employeetype/employeetype-create/employeetype-create.component';
import { BranchshopCreateComponent } from '../branchshop-create/branchshop-create.component';
import { EmployeeRestApiService } from 'src/app/employee/employee-rest-api.service';
import { JsonAdaptor } from '@syncfusion/ej2-data';
@Component({
  selector: 'app-branchshop-list',
  templateUrl: './branchshop-list.component.html',
  styleUrls: ['./branchshop-list.component.css']
})
export class BranchshopListComponent implements OnInit {

  //khai báo tên gọi cho 
  clients: any[];
  dataTable: any;
  Content: any = [];
  Contentcheck: any = []
  Contentemployee: any = [];
  info: any;
  private roles: string[];
  private authority: string;
  private authorityad: string;
  private authorityhr: string;
  private authoritychef: string;
  private authoritybrm: string;
  private authorityacc: string;
  private authoritycashier: string;
  constructor(public restApi: BranchshopRestApiService,
    public restApiemployee: EmployeeRestApiService,
    private chRef: ChangeDetectorRef,
    private token: TokenStorageService,
    public router: Router, private dialog: MatDialog) { }
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
    } else {
      this.roles = this.token.getAuthorities();
      this.roles.every(role => {
        if (role === 'ROLE_ADMIN') {
          this.authorityad = 'ad';
          return true;
        } if (role === 'ROLE_HR') {
          this.authorityhr = 'hr';
          return true;
        } if (role === 'ROLE_BRANCH_MANAGER') {
          this.authoritybrm = 'brm';
          return true;
        } if (role === 'ROLE_CASHIER') {
          this.authoritycashier = 'cashier';
          return true;
        } if (role === 'ROLE_ACCOUNTANT') {
          this.authorityacc = 'acc';
          return true;
        } if (role === 'ROLE_CHEF') {
          this.authoritychef = 'chef';
          return true;
        }
        this.authority = 'user';
        return false;
      });
    }
    if (this.authorityacc === 'acc' || this.authoritybrm === 'brm' ||
      this.authoritycashier === 'cashier' || this.authorityad === 'ad' || this.authorityhr === 'hr') {
    } else {
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
    // this.restApi.employeetypeDetails.id = null;
    // this.restApi.employeetypeDetails.address = '';
    // this.restApi.employeetypeDetails.name = '';
     //validate s
     this.restApi.initializeFormGroup();
     //validate e
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";
    //Chọn Component làm popup
    this.dialog.open(BranchshopCreateComponent, dialogConfig);
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
    this.restApi.editFormGroup(employeetype);
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";
    //Chọn Component làm popup dùng chung create cho update
    this.dialog.open(BranchshopCreateComponent, dialogConfig);
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
    this.restApiemployee.getEmployeebybranchshopid(id).subscribe((data: {}) => {
      this.Contentemployee = data;
      this.Contentcheck = this.Contentemployee.content;
      console.log('so nhan vien: ' + (this.Contentcheck.length))
      if (this.Contentcheck.length < 1) {
        if (window.confirm('Are you sure, you want to delete?')) {
          this.restApi.deleteEmployeetype(id).subscribe(data => {
            this.loadEmployeetype()
          })
        }
      } else {
        window.confirm('Chi nhánh hiện còn nhân viên bạn không thể xóa')
        // window.alert(this.Contentcheck.length);
        // console.log(this.Contentcheck.length)
      }
    })


  }}
  logout() {
    this.token.signOut();
    this.router.navigate(['login'])
    //window.location.reload();
  }

}

