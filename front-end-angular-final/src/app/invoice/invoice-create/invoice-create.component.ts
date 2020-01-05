import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { BranchshopRestApiService } from 'src/app/branchshop/branchshop-rest-api.service';
import { InvoiceRestApiService } from '../invoice-rest-api.service';
import { OrdertypeRestApiService } from 'src/app/ordertype/ordertype-rest-api.service';
import { DrinktypeRestApiService } from 'src/app/drinktype/drinktype-rest-api.service';
import { DrinkRestApiService } from 'src/app/drink/drink-rest-api.service';
import { Drink } from 'src/app/drink/drink';
import { MatDialog, MatDialogConfig } from "@angular/material";
import { TestpopupComponent } from 'src/app/testpopups/testpopup/testpopup.component';
import { InvoicedetailComponent } from '../invoicedetail/invoicedetail.component';
import { Breakpoints } from '@angular/cdk/layout';
import { CustomerRestApiService } from 'src/app/customer/customer-rest-api.service';
import { DatePipe } from '@angular/common';
import { CustomertypeRestApiService } from 'src/app/customertype/customertype-rest-api.service';
import { EmployeeRestApiService } from 'src/app/employee/employee-rest-api.service';


@Component({
  selector: 'app-invoice-create',
  templateUrl: './invoice-create.component.html',
  styleUrls: ['./invoice-create.component.css']
})
export class InvoiceCreateComponent implements OnInit {

  @Input() DrinktypeDetails = { drinkType: '' }
  // @Input() InvoiceDetails = {
  //   branchShop: 'hochiminh', customerPhone: null, id: null, date: 0, numberPosition: null, orderType: 'test', totalDiscount: 0,
  //   totalPrice: 0, vat: 0
  // }
  info: any;
  ContentCustomer: any = [];
  ContentEmployee: any = [];
  ContentCustomertype: any = [];
  Listphonecustomer: Array<any> = [];
  ContentList: Drink[];
  ContentDrinktype: any = [];
  ContentDrink: any = [];
  ContentInvoice: any = [];
  ContentAll: any = [];
  ContentInvoiceDetail: any = [];
  ContentInvoiceDetailById: any = [];
  numbers: number[] = [];
  ContentOrdertype: any = [];
  TestInvoiceList: any = [];
  PrintInvoiceList: any = [];
  branchshop: string;
  cashierName: string;
  customerName = null;
  numberpositon: any;
  idInvoice: any;
  totalPrice: any;
  realPay: any;
  printdiscountname: any;
  y: any;
  x: any;
  invoice: any = [];
  timeprint: any;
  discount = 0;
  discountName = '0%'
  customerType: any
  nameCustomertype: any
  totaldiscount: any
  constructor(
    private dialog: MatDialog,
    public datepipe: DatePipe,
    public restApiDrinktype: DrinktypeRestApiService,
    public restApiCustomer: CustomerRestApiService,
    public restApiEmployee: EmployeeRestApiService,
    public restApiCustomertype: CustomertypeRestApiService,
    public restApiDrink: DrinkRestApiService,
    public restApiInvoice: InvoiceRestApiService,
    public restApiOrdertype: OrdertypeRestApiService,
    private token: TokenStorageService,
    public router: Router
  ) { }
  ngOnInit() {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()
    };
    if (!this.token.getToken()) {
      this.router.navigate(['login'])
    }
    this.restApiEmployee.getEmployeebyusername().subscribe((data: {}) => {
      this.ContentEmployee = data;
      this.cashierName = this.ContentEmployee.content.name
      console.log('trong hàm' + JSON.stringify(this.TestInvoiceList.content));
    })
    this.restApiInvoice.getmaxid().subscribe((data: {}) => {
      this.TestInvoiceList = data;
      console.log('trong hàm' + JSON.stringify(this.TestInvoiceList.content));
    })

    console.log('haha');
    this.loadEmployeetype()
    this.loadOrdertype()
    // this.loadEmployeetype1()
    this.loadDrinktype()
    this.loadCustomer()
  }
  loadOrdertype() {
    return this.restApiOrdertype.getEmployeetypes().subscribe((data: {}) => {
      this.ContentOrdertype = data;
      console.log('trong hàm' + JSON.stringify(this.ContentInvoice));
    })
  }
  Delete(invoicedetail) {
    var r = confirm("Bạn có muốn xóa sản phẩm này ?");
    console.log(invoicedetail.id)
    if (r == true) {
      this.restApiInvoice.deteleInvoiceDetailbyid(invoicedetail.drinkId, invoicedetail.id, invoicedetail.invoiceId).subscribe(data => {
        this.loadInvoiceDetail();
      })
    }
  }
  Decrease(invoicedetail) {
    if (invoicedetail.amount > 1) {
      invoicedetail.amount = invoicedetail.amount - 1;
      invoicedetail.price = invoicedetail.amount * invoicedetail.unitPrice;
      let index = this.restApiInvoice.InvoiceDetailList.findIndex(car => car.serial === invoicedetail.serial);
      this.restApiInvoice.InvoiceDetailList[index] = invoicedetail;
      this.PrintInvoiceList = this.restApiInvoice.InvoiceDetailList;
      
      console.log(JSON.stringify(this.restApiInvoice.InvoiceDetailList))
      this.restApiInvoice.InvoiceDetails.totalPrice = this.restApiInvoice.InvoiceDetails.totalPrice - invoicedetail.unitPrice
      this.restApiInvoice.InvoiceDetails.realPay = this.restApiInvoice.InvoiceDetails.totalPrice - this.restApiInvoice.InvoiceDetails.totalPrice * this.discount
      this.totalPrice = this.restApiInvoice.InvoiceDetails.totalPrice;
      this.realPay = this.restApiInvoice.InvoiceDetails.realPay;
    } else {
      var r = confirm("Bạn có muốn xóa sản phẩm này ?");
      if (r == true) {
        this.restApiInvoice.deteleInvoiceDetailbyid(invoicedetail.drinkId, invoicedetail.id, invoicedetail.invoiceId).subscribe(data => {
          this.loadInvoiceDetail();
        })
      }
    }
  }
  Increase(invoicedetail) {
    invoicedetail.amount = invoicedetail.amount + 1;
    invoicedetail.price = invoicedetail.amount * invoicedetail.unitPrice;
    let index = this.restApiInvoice.InvoiceDetailList.findIndex(car => car.serial === invoicedetail.serial);
    this.restApiInvoice.InvoiceDetailList[index] = invoicedetail;
    this.PrintInvoiceList = this.restApiInvoice.InvoiceDetailList;
    this.restApiInvoice.InvoiceDetails.totalPrice = this.restApiInvoice.InvoiceDetails.totalPrice + invoicedetail.unitPrice
    this.restApiInvoice.InvoiceDetails.realPay = this.restApiInvoice.InvoiceDetails.totalPrice - this.restApiInvoice.InvoiceDetails.totalPrice * this.discount
    this.totalPrice = this.restApiInvoice.InvoiceDetails.totalPrice;
    this.realPay = this.restApiInvoice.InvoiceDetails.realPay;
  }
  loadEmployeetype() {
    return this.restApiInvoice.getEmployeetypesfalse().subscribe((data: {}) => {
      this.ContentInvoice = data;
      console.log('trong hàm 1' + JSON.stringify(this.ContentInvoice));
    })
  }

  loadCustomer() {
    return this.restApiCustomer.getEmployeetypes().subscribe((data: {}) => {
      this.ContentCustomer = data;
      for (let e1 of this.ContentCustomer.content) {
        this.Listphonecustomer.push(e1.phone)
      }
      console.log('trong hàm 1' + JSON.stringify(this.Listphonecustomer));
    })
  }
  search = (text$: Observable<string>) =>
    text$.pipe(
      debounceTime(200),
      distinctUntilChanged(),
      map(term => term.length < 2 ? []
        : this.Listphonecustomer.filter(v => v.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10))
    )
  loadInvoiceDetail() {
    return this.restApiInvoice.getInvoiceDetail(this.restApiInvoice.InvoiceDetails.id).subscribe((data: {}) => {
      this.ContentInvoiceDetail = data;
      this.restApiInvoice.InvoiceDetailList = this.ContentInvoiceDetail.content;
      this.PrintInvoiceList = this.restApiInvoice.InvoiceDetailList;
      this.restApiInvoice.InvoiceDetails.totalPrice = 0;
      for (let e1 of this.restApiInvoice.InvoiceDetailList) {
        this.restApiInvoice.InvoiceDetails.totalPrice = this.restApiInvoice.InvoiceDetails.totalPrice + e1.price
      }
      console.log(this.discount + 't ne' )
      this.restApiInvoice.InvoiceDetails.realPay = this.restApiInvoice.InvoiceDetails.totalPrice - this.restApiInvoice.InvoiceDetails.totalPrice * this.discount
      this.totalPrice = this.restApiInvoice.InvoiceDetails.totalPrice;
      this.realPay = this.restApiInvoice.InvoiceDetails.realPay;
      console.log('trong hàm' + JSON.stringify(this.restApiInvoice.InvoiceDetailList));
    })
  }
  loadInvoiceDetail1() {
    return this.restApiInvoice.getInvoiceDetail1(this.restApiInvoice.InvoiceDetails.id).subscribe((data: {}) => {
      this.ContentInvoiceDetailById = data;
      this.restApiInvoice.InvoiceDetailListCheck = this.ContentInvoiceDetailById.content;
      //console.log('trong hàm' + JSON.stringify(this.restApiInvoice.InvoiceDetailList));
    })
  }


  // loadEmployeetype1() {
  //   return this.restApi2.getEmployeetypes().subscribe((data: {}) => {
  //     this.Content1 = data;
  //     console.log('trong hàm'+this.Content1);
  //   })
  // }
  onCreate(drinkId, invoiceId, drinkName) {
    var serial = this.restApiInvoice.InvoiceDetailList.length + 1;
    this.restApiInvoice.initializeFormGroup(drinkId, invoiceId, serial, drinkName);
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";
    this.dialog.open(InvoicedetailComponent, dialogConfig);
  }
  loadDrinktype() {
    return this.restApiDrinktype.getEmployeetypes().subscribe((data: {}) => {
      this.ContentDrinktype = data;
    })
  }
  loadDrinkBySort(name) {
    console.log('vô rồi nè');
    return this.restApiDrink.getEmployeetypessorthaveprice(name).subscribe((data: {}) => {
      this.ContentDrink = data;
      console.log('vô rồi nè' + this.ContentDrink);
    })
  }
  loadDrink() {
    return this.restApiDrink.getEmployeetypeshaveprice().subscribe((data: {}) => {
      this.ContentDrink = data;
    })
  }

  // addEmployeetype(dataEmployeetype) {

  //   this.restApi.createEmployeetype(this.employeetypeDetails).subscribe((data: {}) => {
  //     this.router.navigate(['/invoice-list'])
  //   })
  // }
  loadlaitrang() {

    console.log(this.DrinktypeDetails.drinkType);
    if (this.restApiInvoice.InvoiceDetails.id != null) {
      if (this.DrinktypeDetails.drinkType == "1") {
        this.loadDrink();
        console.log('vô rồi');
      } else {
        this.loadDrinkBySort(this.DrinktypeDetails.drinkType);
        console.log('ra rồi');
      }
    } else {
      window.alert('Bạn cần chọn hóa đơn trước!');
    }
  }
  check() {
    console.log('trúng rồi');
    console.log(this.restApiInvoice.InvoiceDetails.customerPhone)
    for (let e1 of this.ContentCustomer.content) {
      console.log(e1.phone + ' ' + this.restApiInvoice.InvoiceDetails.customerPhone)
      if(e1.phone === this.restApiInvoice.InvoiceDetails.customerPhone){
        console.log(e1.customerType)
        this.customerName = e1.name;
        this.restApiCustomertype.getEmployeetypes().subscribe((data: {}) => {
          this.ContentCustomertype = data;
          console.log('trúng rồi' + this.ContentCustomertype.content);
          for (let e2 of this.ContentCustomertype.content) {
            console.log(e2.name + ' '+ e1.customerType)
            if(e2.name === e1.customerType){
              this.discount = e2.discountValue
              this.discountName = e2.discountName
              this.printdiscountname = this.discountName
              this.restApiInvoice.InvoiceDetails.realPay = this.restApiInvoice.InvoiceDetails.totalPrice - this.restApiInvoice.InvoiceDetails.totalPrice * this.discount
              this.realPay = this.restApiInvoice.InvoiceDetails.realPay
              console.log(this.discount + ' ' + this.discountName)
              break;
            }
          }
          console.log('trong hàm 1' + JSON.stringify(this.Listphonecustomer));
        })
        break;
      }
      
    }
  }
  selectData(invoice) {
    var check = 0;
    var j = 0;
    var i = 0;
    this.restApiInvoice.InvoiceDetails.id = invoice.id;
    this.restApiInvoice.InvoiceDetails.orderType = 'Ngồi tại bàn';
    this.restApiInvoice.InvoiceDetails.numberPosition = invoice.numberPosition;
    this.branchshop = invoice.branchShop;
    this.numberpositon = invoice.numberPosition;
    this.idInvoice = invoice.id;
    this.loadInvoiceDetail();
    this.loadInvoiceDetail1();
    // this.InvoiceDetails.totalPrice = 0;
    for (let e1 of this.restApiInvoice.InvoiceDetailList) {
      // this.InvoiceDetails.totalPrice = this.InvoiceDetails.totalPrice + e1.price
      i = i + 1
      j = 0
      for (let e2 of this.restApiInvoice.InvoiceDetailListCheck) {
        j = j + 1
        if (e1.amount !== e2.amount && i === j) {
          check = 1;
          console.log('check hang : ' + check + JSON.stringify(e1) + JSON.stringify(e2))
        } else {
          console.log('huy nè' + check)
        }
      }
    }
    if (check === 1) {
      var r = confirm("Bạn có muốn lưu danh sách thức uống ?");
      if (r == true) {
        console.log(JSON.stringify(this.restApiInvoice.InvoiceDetailList))
        this.restApiInvoice.editEmployeetypelist(this.restApiInvoice.InvoiceDetailList).subscribe((data: {}) => {
        })
      }
    }
  }

  CreateInvoiceFirst() {

    this.restApiInvoice.callinvoiceId(this.restApiInvoice.InvoiceDetails.id);
    this.restApiInvoice.InvoiceDetails.id = 0;
    this.restApiInvoice.createEmployeetype(this.restApiInvoice.InvoiceDetails).subscribe((data: {}) => {
      this.loadEmployeetype();
    })
    this.restApiInvoice.InvoiceDetails.id = this.restApiInvoice.forminvoice.get('invoiceId').value;
  }
  thanhToan() {
    //this.loadInvoiceDetail();
    this.totaldiscount = this.restApiInvoice.InvoiceDetails.realPay * this.discount
    this.totalPrice = this.restApiInvoice.InvoiceDetails.totalPrice
    this.numberpositon = this.restApiInvoice.InvoiceDetails.numberPosition
    if(this.restApiInvoice.InvoiceDetails.customerPhone === ""){
      this.restApiInvoice.InvoiceDetails.customerPhone = null;
    }
    if (this.restApiInvoice.InvoiceDetails.id === null) {
      window.alert('Bạn chưa chọn hóa đơn nào')
    } else if (this.restApiInvoice.InvoiceDetailList.length === 0) {
      window.alert('Bạn chưa có bất kì thức uống nào trong đơn hàng này')
    } else {
      this.restApiInvoice.InvoiceDetails.paymentStatus = true;
      this.restApiInvoice.editEmployeetypelist(this.restApiInvoice.InvoiceDetailList).subscribe((data: {}) => {
      })
      this.restApiInvoice.updateEmployeetype(this.restApiInvoice.InvoiceDetails).subscribe((data: {}) => {
        this.loadEmployeetype();
        this.print();
        this.restApiInvoice.InvoiceDetailList = [];
        this.restApiInvoice.InvoiceDetails.id = null;
        this.restApiInvoice.InvoiceDetails.date = 0;
        this.restApiInvoice.InvoiceDetails.numberPosition = null;
        this.restApiInvoice.InvoiceDetails.paymentStatus = false;
        this.restApiInvoice.InvoiceDetails.customerPhone = null;
        this.restApiInvoice.InvoiceDetails.realPay = 0;
        this.restApiInvoice.InvoiceDetails.totalDiscount = 0;
        this.restApiInvoice.InvoiceDetails.totalPrice = 0;
        this.restApiInvoice.InvoiceDetails.vat = 0;
        this.discountName = '0%';
        this.discount = 0;

      })
    }
  }
  // Test(){
  //   this.restApiInvoice.InvoiceDetailList.push(this.InvoiceDetail);
  //   console.log(JSON.stringify(this.restApiInvoice.InvoiceDetailList));
  // }
  print(): void {
    this.timeprint = new Date().getTime();
    this.timeprint = this.datepipe.transform(this.timeprint,'HH:mm:ss dd/MM/yyyy');
    // this.invoice = {
    //   "serieCorrelative": null, "issueDate": "2018-12-06", "referenceDate": "2018-12-06", "typeDocument": "01",
    //   "currency": "PEN", "subTotal": 8310.02, "subTotalString": "8310.02", "igv": 1495.80, "igvString": "1495.80",
    //   "total": 9805.82, "totalString": "9805.82",
    //   "supplier": { "numberDocument": "20603422806", "typeDocument": 0, "nameLegal": " ", "nameCommercial": "INKAS DEV", "email": null },
    //   "customer": {
    //     "numberDocument": "20603422806", "typeDocument": 6,
    //     "nameLegal": "COMPAÑIA PERUANA DE INVESTIGACION & DESARROLLO TECNOLOGICO S.A.C.", "nameCommercial": "COMPAÑIA PERUANA DE INVESTIGACION & DESARROLLO TECNOLOGICO S.A.C.", "email": null
    //   },
    //   "items": [
    //     { "numberLine": 1, "quantity": 1, "unitCode": "NIU", "nameProduct": "BALDE JOY 13 LL", "valueUnit": null, "valueUnitString": null, "priceUnit": 7.02, "priceUnitString": "7.02", "igvItem": null, "igvItemString": null, "taxName": "IGV", "taxValue": null, "codigoTipoAfectacionIGV": "10" },
    //     { "numberLine": 2, "quantity": 10, "unitCode": "NIU", "nameProduct": "TAPER G&G CUADRADO C/. REJILLA 1.6 LT", "valueUnit": null, "valueUnitString": null, "priceUnit": 10.68, "priceUnitString": "10.68", "igvItem": null, "igvItemString": null, "taxName": "IGV", "taxValue": null, "codigoTipoAfectacionIGV": "10" },
    //     { "numberLine": 2, "quantity": 10, "unitCode": "NIU", "nameProduct": "TAPER G&G CUADRADO C/. REJILLA 1.6 LT", "valueUnit": null, "valueUnitString": null, "priceUnit": 10.68, "priceUnitString": "10.68", "igvItem": null, "igvItemString": null, "taxName": "IGV", "taxValue": null, "codigoTipoAfectacionIGV": "10" },
    //     { "numberLine": 2, "quantity": 10, "unitCode": "NIU", "nameProduct": "TAPER G&G CUADRADO C/. REJILLA 1.6 LT", "valueUnit": null, "valueUnitString": null, "priceUnit": 10.68, "priceUnitString": "10.68", "igvItem": null, "igvItemString": null, "taxName": "IGV", "taxValue": null, "codigoTipoAfectacionIGV": "10" },
    //     { "numberLine": 2, "quantity": 10, "unitCode": "NIU", "nameProduct": "TAPER G&G CUADRADO C/. REJILLA 1.6 LT", "valueUnit": null, "valueUnitString": null, "priceUnit": 10.68, "priceUnitString": "10.68", "igvItem": null, "igvItemString": null, "taxName": "IGV", "taxValue": null, "codigoTipoAfectacionIGV": "10" },
    //     { "numberLine": 2, "quantity": 10, "unitCode": "NIU", "nameProduct": "TAPER G&G CUADRADO C/. REJILLA 1.6 LT", "valueUnit": null, "valueUnitString": null, "priceUnit": 10.68, "priceUnitString": "10.68", "igvItem": null, "igvItemString": null, "taxName": "IGV", "taxValue": null, "codigoTipoAfectacionIGV": "10" },
    //     { "numberLine": 2, "quantity": 10, "unitCode": "NIU", "nameProduct": "TAPER G&G CUADRADO C/. REJILLA 1.6 LT", "valueUnit": null, "valueUnitString": null, "priceUnit": 10.68, "priceUnitString": "10.68", "igvItem": null, "igvItemString": null, "taxName": "IGV", "taxValue": null, "codigoTipoAfectacionIGV": "10" },
    //     { "numberLine": 2, "quantity": 10, "unitCode": "NIU", "nameProduct": "TAPER G&G CUADRADO C/. REJILLA 1.6 LT", "valueUnit": null, "valueUnitString": null, "priceUnit": 10.68, "priceUnitString": "10.68", "igvItem": null, "igvItemString": null, "taxName": "IGV", "taxValue": null, "codigoTipoAfectacionIGV": "10" },
    //     { "numberLine": 2, "quantity": 10, "unitCode": "NIU", "nameProduct": "TAPER G&G CUADRADO C/. REJILLA 1.6 LT", "valueUnit": null, "valueUnitString": null, "priceUnit": 10.68, "priceUnitString": "10.68", "igvItem": null, "igvItemString": null, "taxName": "IGV", "taxValue": null, "codigoTipoAfectacionIGV": "10" },
    //     { "numberLine": 2, "quantity": 10, "unitCode": "NIU", "nameProduct": "TAPER G&G CUADRADO C/. REJILLA 1.6 LT", "valueUnit": null, "valueUnitString": null, "priceUnit": 10.68, "priceUnitString": "10.68", "igvItem": null, "igvItemString": null, "taxName": "IGV", "taxValue": null, "codigoTipoAfectacionIGV": "10" },
    //     { "numberLine": 3, "quantity": 100, "unitCode": "NIU", "nameProduct": "CAJA ORGANIZADORA DE USO PESADO BUNKER 1", "valueUnit": null, "valueUnitString": null, "priceUnit": 96.92, "priceUnitString": "96.92", "igvItem": null, "igvItemString": null, "taxName": "IGV", "taxValue": null, "codigoTipoAfectacionIGV": "10" }],
    //   "serie": "F421", "correlative": 7, "originClient": "API", "issueTime": "23:14",
    //   "invoiceName": "FACTURA DE  VENTA ELECTRÓNICA", "address": "LIMA", "cashier": "prueba", "posName": "PV LIMA 01",
    //   "totalLetter": "NUEVE MIL OCHOCIENTOS CINCO 82/100 SOLES.", "isProduction": false, "status": "06",
    //   "logoWidth": "20%", "logoTop": "-20", "logoLeft": "0"
    // };
    // this.restApiInvoice.getAllInvoice(id).subscribe((data: {}) => {
    //   this.invoice = data;

    //   console.log("print id la " + id);
    //   console.log(this.invoice)
    // })
    setTimeout(() => {
      let printContents, popupWin;
      printContents = document.getElementById('print-section').innerHTML;
      // printContents = printContents.replace('|NUM_INVOICE|', 12345);
      popupWin = window.open('', '_blank', 'top=0,left=0,height=100%,width=auto');
      popupWin.document.open();
      popupWin.document.write(`
          <html>
            <head>
              <title>Print tab</title>
              <style>
                .invoice-box{
                  max-width:800px;
                  
                  padding:30px;
                  border:1px solid #eee;
                  box-shadow:0 0 10px rgba(0, 0, 0, .15);
                  font-size:16px;
                  line-height:24px;
                  font-family:'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;
                  color:#555;
              }
              
              .invoice-box table{
                  width:100%;
                  line-height:inherit;
                  text-align:left;
              }
              
              .invoice-box table td{
                  vertical-align:top;
              }
              
              .invoice-box table tr td:nth-child(2){
                  text-align:right;
              }
             
              
              .invoice-box table tr.top table td.title{
                  font-size:45px;
                  line-height:45px;
                  color:#333;
              }
              
              .invoice-box table tr.information table td{
                  padding-bottom:10px;
                  
              }
              .invoice-box table tr.information table {
                padding-bottom:40px;
                
              }
              
              .invoice-box table tr.heading td{
                  background:#eee;
                  border-bottom:1px solid #ddd;
                  font-weight:bold;
              }
              
             
              
              .invoice-box table tr.item td{
                  border-bottom:1px solid #eee;
              }
              
              .invoice-box table tr.item.last td{
                  border-bottom:none;
              }
              
              .invoice-box table tr.total td:nth-child(2){
                  border-top:2px solid #eee;
                  font-weight:bold;
              }
              .invoice-box table tr.name-invoice{
                background: #eee;
                border-bottom: 1px solid #ddd;
                font-weight: bold;
              }
              @media only screen and (max-width: 600px) {
                  .invoice-box table tr.top table td{
                      width:100%;
                      display:block;
                      text-align:center;
                  }
                  
                  .invoice-box table tr.information table td{
                      width:100%;
                      display:block;
                      text-align:center;
                  }
    
                  
              }
    
              #border-document {
                  border: 2px solid #F0F0F0;
                  padding: 10px;
                  border-radius: 25px;
              }
              #border-total {
                  border: 2px solid #F0F0F0;
                  padding: 10px;
                  border-radius: 25px;
              }
    
              .client-data{
                  width: 65% ;
              }
              .company-data{
                  width: 62% ;
              }
              .invoice-box table tr.heading td.head-serial{
                width: 5% ;
                text-align:center;
            }
              .invoice-box table tr.heading td.head-description{
                  width: 60% ;
                  text-align:center;
              }
              .invoice-box table tr.heading td.head-quantity{
                  width: 5% ;
                  text-align:center;
              }
              .invoice-box table tr.heading td.head-price{
                  width: 15% ;
                  text-align:center;
              }
              .invoice-box table tr.heading td.head-total{
                  width: 20% ;
                  text-align:center;
              }
              .invoice-box table tr.item td.serial{
                width: 5% ;
                text-align:center;
            }
              .invoice-box table tr.item td.description{
                  width: 60% ;
                  text-align:center;
              }
              .invoice-box table tr.item td.quantity{
                  width: 5% ;
                  text-align:center;
              }
              .invoice-box table tr.item td.price{
                  width: 15% ;
                  text-align:right;
              }
              .invoice-box table tr.item td.total{
                  width: 20% ;
                  text-align:right;
              }
              .invoice-box table tr.total td {              
                  text-align:left;
                  border-top: 0px !important;
              }
              .invoice-box table tr.qr td {              
                  text-align:center;              
              }
    
     
              </style>
            </head>
        <body onload="window.print();window.close()">${printContents}</body>
          </html>`
      );
      popupWin.document.close();
    }, 200);

  }
}



