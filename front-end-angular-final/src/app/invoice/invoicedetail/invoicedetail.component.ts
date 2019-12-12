import { Component, OnInit, Input } from '@angular/core';
import { MatDialogRef } from '@angular/material';
import { DepartmentService } from 'src/app/testpopups/department.service';
import { NotificationService } from 'src/app/testpopups/notification.service';
import { InvoiceRestApiService } from '../invoice-rest-api.service';
import { RestApiDrinkpriceService } from 'src/app/drinkprice/rest-api-drinkprice.service';



// import { EmployeeService } from '../../shared/employee.service';
// import { DepartmentService } from '../../shared/department.service';
// import { NotificationService } from '../../shared/notification.service';

@Component({
  selector: 'app-invoicedetail',
  templateUrl: './invoicedetail.component.html',
  styleUrls: ['./invoicedetail.component.css']
})
export class InvoicedetailComponent implements OnInit {

  @Input() InvoiceDetail = { amount: 1, discount: 0, drinkId: 0, invoiceId: 0, price: 0, unitPrice: 0, drinkName: '', id: 0, serial: 0 }
  ContentDrinkPrice: any = {}
  Content: any = {}
  constructor(private service: InvoiceRestApiService,
    private restApiDrinkPrice: RestApiDrinkpriceService,
    private notificationService: NotificationService,
    public dialogRef: MatDialogRef<InvoicedetailComponent>) { }



  ngOnInit() {
    //this.service.getEmployees();
    this.InvoiceDetail.invoiceId = this.service.form.get('invoiceId').value;
    this.InvoiceDetail.drinkId = this.service.form.get('drinkId').value;
    this.InvoiceDetail.serial = this.service.form.get('serial').value;
    this.InvoiceDetail.drinkName = this.service.form.get('drinkName').value;
    this.loadUnitPriceByDrinkId();
  }

  loadUnitPriceByDrinkId() {
    this.restApiDrinkPrice.getEmployeetype(this.InvoiceDetail.drinkId).subscribe((data: {}) => {
      this.ContentDrinkPrice = data;
      this.InvoiceDetail.unitPrice = this.ContentDrinkPrice.content.price;
      console.log(this.InvoiceDetail.unitPrice);
    })
  }

  onClear() {
    this.service.form.reset();
    //this.service.initializeFormGroup();
    this.notificationService.success(':: Submitted successfully');
  }
  Decrease() {
    var soluong = this.InvoiceDetail.amount;
    if (soluong > 1) {
      soluong = soluong - 1;
      this.InvoiceDetail.amount = soluong;
      //console.log(this.InvoiceDetail.amount);
    }else{
      var r = confirm("Bạn có muốn hủy thức uống này ?");
      if (r == true) {
        this.onClose();
      }   
    }
  }
  Increase() {
    var soluong = this.InvoiceDetail.amount;
    soluong = soluong + 1;
    this.InvoiceDetail.amount = soluong;
    //console.log(this.InvoiceDetail.amount);
  }

  onSubmit() {
    if (this.service.form.valid) {
      if (!this.service.form.get('$key').value)
        this.service.insertEmployee(this.service.form.value);
      else
        this.service.updateEmployee(this.service.form.value);
      this.service.form.reset();
      //this.service.initializeFormGroup();
      this.notificationService.success(':: Submitted successfully');
      this.onClose();
    }
  }
  insertNumber() {
    this.InvoiceDetail.price = this.InvoiceDetail.amount * this.InvoiceDetail.unitPrice
    
    this.service.InvoiceDetails.totalPrice = this.service.InvoiceDetails.totalPrice + this.InvoiceDetail.price
    console.log('list ne : ' + JSON.stringify(this.service.InvoiceDetailList))
    this.service.createInvoiceDetail(this.InvoiceDetail).subscribe((data: {}) => {
      this.service.getmaxidinvoicedetail().subscribe((data: {}) => {
        this.Content = data;
        this.InvoiceDetail.id = this.Content.content;
        console.log('id ne mn: ' + this.InvoiceDetail.id)
        this.service.InvoiceDetailList.push(this.InvoiceDetail);
        console.log(this.service.InvoiceDetailList)
      })
      this.notificationService.success('Thêm thành công');
      this.onClose();
    })
  }
  onClose() {
    this.service.form.reset();
    //this.service.initializeFormGroup();
    this.dialogRef.close();
  }

}




