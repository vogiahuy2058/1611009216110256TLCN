import { Component, OnInit, Input, AfterViewInit,ChangeDetectorRef } from '@angular/core';
import * as $ from 'jquery';
import 'datatables.net';
import 'datatables.net-bs4';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { Router } from '@angular/router';
import { OrdertypeRestApiService } from '../ordertype-rest-api.service';
import { MatDialog, MatDialogConfig } from "@angular/material";
//Chọn Component làm popup
import { OrdertypeCreateComponent } from '../ordertype-create/ordertype-create.component';
@Component({
  selector: 'app-ordertype-list',
  templateUrl: './ordertype-list.component.html',
  styleUrls: ['./ordertype-list.component.css']
})
export class OrdertypeListComponent implements OnInit{

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
  constructor(public restApi: OrdertypeRestApiService, private chRef: ChangeDetectorRef,private token: TokenStorageService,
    public router: Router, private dialog: MatDialog) { }
  ngOnInit() {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()
    };
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
    if (this.authorityacc === 'acc' ||
       this.authorityad === 'ad' ) {
      
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
    this.restApi.employeetypeDetails.id = null;
        this.restApi.employeetypeDetails.name = '';
        const dialogConfig = new MatDialogConfig();
        dialogConfig.disableClose = true;
        dialogConfig.autoFocus = true;
        dialogConfig.width = "60%";
        //Chọn Component làm popup
        this.dialog.open(OrdertypeCreateComponent, dialogConfig);
      }
      onUpdate(employeetype){
        this.restApi.employeetypeDetails = employeetype;
        const dialogConfig = new MatDialogConfig();
        dialogConfig.disableClose = true;
        dialogConfig.autoFocus = true;
        dialogConfig.width = "60%";
        //Chọn Component làm popup dùng chung create cho update
        this.dialog.open(OrdertypeCreateComponent, dialogConfig);
      }
   // Delete employee
   deleteEmployeetype(id) {
    if (window.confirm('Are you sure, you want to delete?')){
      this.restApi.deleteEmployeetype(id).subscribe(data => {
        this.loadEmployeetype()
      })
    }
  }  
  logout(){
    this.token.signOut();
    this.router.navigate(['login'])
    //window.location.reload();
  }

}


