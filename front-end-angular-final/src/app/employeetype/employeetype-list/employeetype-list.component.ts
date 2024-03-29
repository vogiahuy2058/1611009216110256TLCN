import { Component, OnInit, Input, AfterViewInit, ChangeDetectorRef, ElementRef } from '@angular/core';
import { EmployeetypeRestApiService } from '../employeetype-rest-api.service';
import * as $ from 'jquery';
import 'datatables.net';
import 'datatables.net-bs4';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { Router } from '@angular/router';
//popupstart
import { MatDialog, MatDialogConfig } from "@angular/material";
//Chọn Component làm popup
import { EmployeetypeCreateComponent } from '../employeetype-create/employeetype-create.component';
//popupend
@Component({
  selector: 'app-employeetype-list',
  templateUrl: './employeetype-list.component.html',
  styleUrls: ['./employeetype-list.component.css']
})
export class EmployeetypeListComponent implements OnInit, AfterViewInit {

  //khai báo tên gọi cho 
  clients: any[];
  dataTable: any;
  metismenu1: any;
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

  constructor(public restApi: EmployeetypeRestApiService,
    private chRef: ChangeDetectorRef,
    private token: TokenStorageService,
    //popupstart
    private dialog: MatDialog,
    //popupend
    public router: Router, private elementRef: ElementRef) { }
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
    if (this.authorityad === 'ad' || this.authorityhr === 'hr') {

    } else {
      this.router.navigate(['home'])
    }
    this.loadEmployeetype();
  }
  ngAfterViewInit() {

  }

  loadEmployeetype() {
    return this.restApi.getEmployeetypes().subscribe((data: {}) => {
      this.Content = data;
      this.chRef.detectChanges();
      const table: any = $('table');
      this.dataTable = table.DataTable();
      console.log(JSON.stringify(this.dataTable));
    })
  }
  //popupstart
  onCreate() {
    //token start
    this.token.checklogin()
    if (!this.token.getToken()) {
      this.router.navigate(['login'])
    } else {
      //token end
     
      this.restApi.employeetypeDetails.id = null;
      this.restApi.employeetypeDetails.name = '';
      this.restApi.initializeFormGroup();
      const dialogConfig = new MatDialogConfig();
      dialogConfig.disableClose = true;
      dialogConfig.autoFocus = true;
      dialogConfig.width = "60%";
      //Chọn Component làm popup
      this.dialog.open(EmployeetypeCreateComponent, dialogConfig);
    }  // thêm dấu ngoặc
  }
  onUpdate(employeetype) {
    this.token.checklogin()
    if (!this.token.getToken()) {
      this.router.navigate(['login'])
    } else {
    this.restApi.employeetypeDetails = employeetype;
    this.restApi.editFormGroup(employeetype);
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";
    //Chọn Component làm popup dùng chung create cho update
    this.dialog.open(EmployeetypeCreateComponent, dialogConfig);
  }}
  //popupend
  // Delete employee
  deleteEmployeetype(id) {
    this.token.checklogin()
    if (!this.token.getToken()) {
      this.router.navigate(['login'])
    } else {
    if (window.confirm('Are you sure, you want to delete?')) {
      this.restApi.deleteEmployeetype(id).subscribe(data => {
        this.loadEmployeetype()
      })
    }
  }}
  logout() {
    this.token.signOut();
    this.router.navigate(['login'])
    //window.location.reload();
  }

}
