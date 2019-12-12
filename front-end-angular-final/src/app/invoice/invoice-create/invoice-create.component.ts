import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
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
  ContentList: Drink[];
  ContentDrinktype: any = [];
  ContentDrink: any = [];
  ContentInvoice: any = [];
  ContentInvoiceDetail: any = [];
  ContentInvoiceDetailById: any = [];
  numbers: number[] = [];
  ContentOrdertype: any = [];
  TestInvoiceList: any = [];
  y: any;
  x: any;

  constructor(
    private dialog: MatDialog,
    public restApiDrinktype: DrinktypeRestApiService,
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
    this.restApiInvoice.getmaxid().subscribe((data: {}) => {
      this.TestInvoiceList = data;
      console.log('trong hàm' + JSON.stringify(this.TestInvoiceList.content));
    })

    console.log('haha');
    this.loadEmployeetype()
    this.loadOrdertype()
    // this.loadEmployeetype1()
    this.loadDrinktype()
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
      console.log(JSON.stringify(this.restApiInvoice.InvoiceDetailList))
      this.restApiInvoice.InvoiceDetails.totalPrice = this.restApiInvoice.InvoiceDetails.totalPrice - invoicedetail.unitPrice
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
    this.restApiInvoice.InvoiceDetails.totalPrice = this.restApiInvoice.InvoiceDetails.totalPrice + invoicedetail.unitPrice
  }
  loadEmployeetype() {
    return this.restApiInvoice.getEmployeetypesfalse().subscribe((data: {}) => {
      this.ContentInvoice = data;
      console.log('trong hàm 1' + JSON.stringify(this.ContentInvoice));
    })
  }
  loadInvoiceDetail() {
    return this.restApiInvoice.getInvoiceDetail(this.restApiInvoice.InvoiceDetails.id).subscribe((data: {}) => {
      this.ContentInvoiceDetail = data;
      this.restApiInvoice.InvoiceDetailList = this.ContentInvoiceDetail.content;
      this.restApiInvoice.InvoiceDetails.totalPrice = 0;
      for (let e1 of this.restApiInvoice.InvoiceDetailList) {
        this.restApiInvoice.InvoiceDetails.totalPrice = this.restApiInvoice.InvoiceDetails.totalPrice + e1.price
      }
      this.restApiInvoice.InvoiceDetails.realPay = this.restApiInvoice.InvoiceDetails.totalPrice - this.restApiInvoice.InvoiceDetails.totalPrice*(5/100)
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
      console.log('vô rồi nè mày' + this.ContentDrink);
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
  selectData(id) {
    var check = 0;
    var j = 0;
    var i = 0;
    this.restApiInvoice.InvoiceDetails.id = id;
    this.restApiInvoice.InvoiceDetails.orderType = 'Ngồi tại bàn';
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
    if(this.restApiInvoice.InvoiceDetails.id === null){
      window.alert('Bạn chưa chọn hóa đơn nào')
    }else if (this.restApiInvoice.InvoiceDetailList.length === 0) {
      window.alert('Bạn chưa có bất kì thức uống nào trong đơn hàng này')
    } else {
      this.restApiInvoice.InvoiceDetails.paymentStatus = true;
      this.restApiInvoice.editEmployeetypelist(this.restApiInvoice.InvoiceDetailList).subscribe((data: {}) => {
      })
      this.restApiInvoice.updateEmployeetype(this.restApiInvoice.InvoiceDetails).subscribe((data: {}) => {
        this.loadEmployeetype();
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

      })
    }
  }
  // Test(){
  //   this.restApiInvoice.InvoiceDetailList.push(this.InvoiceDetail);
  //   console.log(JSON.stringify(this.restApiInvoice.InvoiceDetailList));
  // }
}



