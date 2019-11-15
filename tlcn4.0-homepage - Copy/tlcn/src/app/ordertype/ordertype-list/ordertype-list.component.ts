import { Component, OnInit, Input, AfterViewInit,ChangeDetectorRef } from '@angular/core';
import * as $ from 'jquery';
import 'datatables.net';
import 'datatables.net-bs4';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { Router } from '@angular/router';
import { OrdertypeRestApiService } from '../ordertype-rest-api.service';
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
  constructor(public restApi: OrdertypeRestApiService, private chRef: ChangeDetectorRef,private token: TokenStorageService,
    public router: Router) { }
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

  loadEmployeetype() {
    return this.restApi.getEmployeetypes().subscribe((data: {}) => {
      this.Content = data;
      this.chRef.detectChanges();
      const table: any = $('table');
      this.dataTable = table.DataTable();
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


