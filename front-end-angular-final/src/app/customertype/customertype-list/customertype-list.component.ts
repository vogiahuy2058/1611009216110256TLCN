import { Component, OnInit, Input, AfterViewInit,ChangeDetectorRef } from '@angular/core';
import * as $ from 'jquery';
import 'datatables.net';
import 'datatables.net-bs4';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { Router } from '@angular/router';
import { CustomertypeRestApiService } from '../customertype-rest-api.service';
//popupstart
import { MatDialog, MatDialogConfig } from "@angular/material";
//Chọn Component làm popup
import { CustomertypeCreateComponent } from '../customertype-create/customertype-create.component';
//popupend
@Component({
  selector: 'app-customertype-list',
  templateUrl: './customertype-list.component.html',
  styleUrls: ['./customertype-list.component.css']
})
export class CustomertypeListComponent implements OnInit,AfterViewInit{

  //khai báo tên gọi cho 
  clients: any[];
  dataTable: any;
  Content:any = [];
  info: any;
  private roles: string[];
  private authority: string;
  private authorityad: string;
  private authorityhr: string;
  private authoritychef: string;
  private authoritybrm: string;
  private authorityacc: string;
  private authoritycashier: string;
  errorMessage = '';
  constructor(public restApi: CustomertypeRestApiService, private chRef: ChangeDetectorRef,private token: TokenStorageService,
    public router: Router,private dialog: MatDialog) { }
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
    // this.token.setuptime();
    if(!this.token.getToken()){
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
    if (this.authorityacc === 'acc' || this.authoritybrm === 'brm' ||
        this.authorityad === 'ad' || this.authorityhr === 'hr') {
     
    }
    else{
      this.router.navigate(['home'])
    }
     this.loadEmployeetype()
     
  }
  ngAfterViewInit(){
   
  }

  loadEmployeetype() {
    return this.restApi.getEmployeetypes().subscribe((data: {}) => {
      this.Content = data;
      this.chRef.detectChanges();
      const table: any = $('table');
      this.dataTable = table.DataTable();
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
      }
    })
  }
  onCreate() {
    //token start
    this.token.checklogin()
    if (!this.token.getToken()) {
      this.router.navigate(['login'])
    } else {
    // this.restApi.employeetypeDetails.id = null;
    // this.restApi.employeetypeDetails.name = '';
    //validate s
    this.restApi.initializeFormGroup();
    //validate e
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";
    //Chọn Component làm popup
    this.dialog.open(CustomertypeCreateComponent, dialogConfig);
  }
}
  onUpdate(employeetype){
    //token start
    this.token.checklogin()
    if (!this.token.getToken()) {
      this.router.navigate(['login'])
    } else {
    this.restApi.employeetypeDetails = employeetype;
     //validate s
     this.restApi.editFormGroup(employeetype);
     //validate e
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";
    //Chọn Component làm popup dùng chung create cho update
    this.dialog.open(CustomertypeCreateComponent, dialogConfig);
  }
}
   // Delete employee
   deleteEmployeetype(id) {
     //token start
    this.token.checklogin()
    if (!this.token.getToken()) {
      this.router.navigate(['login'])
    } else {
    if (window.confirm('Are you sure, you want to delete?')){
      this.restApi.deleteEmployeetype(id).subscribe(data => {
        this.loadEmployeetype()
      })
    }
  }  
}
  logout(){
    this.token.signOut();
    this.router.navigate(['login'])
    //window.location.reload();
  }

}

