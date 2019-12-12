import { Component, OnInit, Input, AfterViewInit, ChangeDetectorRef } from '@angular/core';
import * as $ from 'jquery';
import 'datatables.net';
import 'datatables.net-bs4';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { Router } from '@angular/router';
import { InvoiceRestApiService } from '../invoice-rest-api.service';
import { AngularCsv } from 'angular7-csv/dist/Angular-csv'
import { DatePipe } from '@angular/common';
import { BranchshopRestApiService } from 'src/app/branchshop/branchshop-rest-api.service';
@Component({
  selector: 'app-invoice-list',
  templateUrl: './invoice-list.component.html',
  styleUrls: ['./invoice-list.component.css']
})
export class InvoiceListComponent implements OnInit {

  //khai báo tên gọi cho 
  private roles: string[];
  private authority: string;
  private authorityad: string;
  private authorityhr: string;
  private authoritychef: string;
  private authoritybrm: string;
  private authorityacc: string;
  private authoritycashier: string;
  branchshop: number;
  fromdate: string;
  todate: string;
  clients: any[];
  dataTable: any;
  Content: any = [];
  ContentBranchshop: any = [];
  info: any;
  dtInvoices: any;
  count: number;
  CurrentTime: any;
  csvOptions = {
    fieldSeparator: ',',
    quoteStrings: '"',
    decimalseparator: '.',
    showLabels: true,
    showTitle: true,
    title: '',
    useBom: true,
    noDownload: false,
    headers: [" ID", "Thời gian", "Số điện thoại khách hàng", "Tên khách hàng", "Chi nhánh", "Thu ngân", "Loại đơn hàng", "Vị trí ngồi", "VAT", "Tổng giảm giá", "Tổng giá", "Thực trả"]
  };
  constructor(public restApi: InvoiceRestApiService,
    public restApiBranchshop: BranchshopRestApiService,
    private chRef: ChangeDetectorRef, private token: TokenStorageService,
    public router: Router, public datepipe: DatePipe) { }
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
      this.authoritycashier === 'cashier' || this.authorityad === 'ad') {

    } else {
      this.router.navigate(['home'])
    }
    this.loadEmployeetype()
    this.loadBranchshop()
  }
  Filter() {
    console.log(this.branchshop)
    this.count = 1;
    this.fromdate = this.datepipe.transform(this.fromdate, 'yyyy-MM-dd');
    this.todate = this.datepipe.transform(this.todate, 'yyyy-MM-dd');
    this.CurrentTime = new Date().getFullYear() + '-' + new Date().getDate() + '-' + new Date().getMonth(), 1;
    if (this.fromdate == null || this.todate == null) {
      window.alert('Không để trống trường từ ngày hoặc đến ngày')
    } else if (this.branchshop == null) {
      window.alert('Vui lòng chọn chi nhánh xuất')
    } else if (this.fromdate > this.todate) {
      window.alert('Vui lòng nhập ngày bắt đầu trước ngày kết thúc')
      console.log(this.CurrentTime)
    } else if (this.fromdate > this.CurrentTime) {
      console.log(this.fromdate + ' > ' + this.CurrentTime)
      window.alert('Vui lòng không chọn ngày bắt đầu sau ngày hôm nay')
    } else if (this.todate > this.CurrentTime) {
      console.log(this.todate + ' > ' + this.CurrentTime)
      window.alert('Vui lòng không chọn ngày kết thúc sau ngày hôm nay')
    } else {
      return this.restApi.getEmployeetypefromdatetodate(this.fromdate, this.todate, this.branchshop).subscribe((data: {}) => {
        this.Content = data;
        this.chRef.detectChanges();
        const table: any = $('table');
        this.dataTable = table.DataTable();
      })
    }

    // var d = new Date(this.fromdate);
    // this.fromdate
    // d.setHours(0, -d.getTimezoneOffset(), 0, 0)
    // this.fromdate = d.toISOString();
    console.log(this.fromdate + 'sdaf' + this.todate)
  }
  loadBranchshop() {
    return this.restApiBranchshop.getEmployeetypes().subscribe((data: {}) => {
      this.ContentBranchshop = data;
    })
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
    //token start
    this.token.checklogin()
    if (!this.token.getToken()) {
      this.router.navigate(['login'])
    } else {
      //token end
      if (window.confirm('Are you sure, you want to delete?')) {
        this.restApi.deleteEmployeetype(id).subscribe(data => {
          this.loadEmployeetype()
        })
      }
    }
  }
  logout() {
    this.token.signOut();
    this.router.navigate(['login'])
    //window.location.reload();
  }
  downloadCSV() {
    //token start
    this.token.checklogin()
    if (!this.token.getToken()) {
      this.router.navigate(['login'])
    } else {
      //token end
      new AngularCsv(JSON.stringify(this.Content.content), "InvoiceReport", this.csvOptions);
    }

  }
}



