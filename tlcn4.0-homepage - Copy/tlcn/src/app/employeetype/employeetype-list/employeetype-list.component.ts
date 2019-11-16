import { Component, OnInit, Input, AfterViewInit,ChangeDetectorRef,ElementRef } from '@angular/core';
import { EmployeetypeRestApiService } from '../employeetype-rest-api.service';
import * as $ from 'jquery';
import 'datatables.net';
import 'datatables.net-bs4';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-employeetype-list',
  templateUrl: './employeetype-list.component.html',
  styleUrls: ['./employeetype-list.component.css']
})
export class EmployeetypeListComponent implements OnInit,AfterViewInit{

  //khai báo tên gọi cho 
  clients: any[];
  dataTable: any;
  metismenu1: any;
  Content:any = [];
  info: any;
  constructor(public restApi: EmployeetypeRestApiService, private chRef: ChangeDetectorRef,private token: TokenStorageService,
    public router: Router,private elementRef: ElementRef) { }
  ngOnInit() {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()
    };
    if(!this.token.getToken()){
      this.router.navigate(['login'])
    }
     this.loadEmployeetype()
  }
  ngAfterViewInit(){
   
  }

  loadEmployeetype() {
    return this.restApi.getEmployeetypes().subscribe((data: {}) => {
      this.Content = data;
      //console.log(JSON.stringify(this.Content.content))
      this.chRef.detectChanges();
      const table: any = $('table');
      this.dataTable = table.DataTable();
      console.log(JSON.stringify(this.dataTable));
    })
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
