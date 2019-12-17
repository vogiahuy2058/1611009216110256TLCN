import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { BranchshopRestApiService } from 'src/app/branchshop/branchshop-rest-api.service';
import { SupplierRestApiService } from 'src/app/supplier/supplier-rest-api.service';
import { SupplycontractRestApiService } from '../supplycontract-rest-api.service';
import { CheckService } from 'src/app/check.service';
import { NotificationService } from 'src/app/testpopups/notification.service';
import { MatDialogRef } from '@angular/material';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-supplycontract-create',
  templateUrl: './supplycontract-create.component.html',
  styleUrls: ['./supplycontract-create.component.css']
})
export class SupplycontractCreateComponent implements OnInit {

  //@Input() employeetypeDetails = { branchShop: '', date:'', supplier:'', totalPrice:null}
  //@Input() employeetypeDetaillist = {  amount: null, deliveryTime:'', materialId:null, paymentTime:'', supplyContractId: null, unitPrice:null}

  info: any;
  id:any;
  contentDetail1: any = []
  selectedProfile1: any = []
  contentDetail: any = []
  selectedProfile: any = []
  //Content2:any = [this.employeetypeDetaillist];
  Content:any = [];
  Content1:any = [];
  constructor(
    public CheckRegion: CheckService,
    public restApi2: BranchshopRestApiService,
    public restApi: SupplycontractRestApiService,
    public datepipe: DatePipe,
    public restApi1: SupplierRestApiService, 
    private token: TokenStorageService,
    public router: Router,
    private notificationService: NotificationService,
    //component create chọn làm popup
    public dialogRef: MatDialogRef<SupplycontractCreateComponent>
  ) { }

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
    this.loadEmployeetype1()
    this.id = this.restApi.employeetypeDetails.id
    this.restApi.form.get('date').setValue(this.datepipe.transform(this.restApi.form.get('date').value, 'yyyy-MM-dd'))
  }
  loadEmployeetype() {
    return this.restApi1.getEmployeetypes().subscribe((data: {}) => {
      this.Content = data;
      this.contentDetail = this.Content.content;
      if (!this.restApi.form.get('id').value) {
        this.selectedProfile = this.contentDetail[0].name;
      } else {
        this.selectedProfile = this.restApi.form.get('supplier').value
      }
      
      
      console.log('trong hàm'+this.Content);
    })
  }
  loadEmployeetype1() {
    return this.restApi2.getEmployeetypes().subscribe((data: {}) => {
      this.Content1 = data;
      this.contentDetail1 = this.Content1.content;
      if (!this.restApi.form.get('id').value) {
        this.selectedProfile1 = this.contentDetail1[0].name;
      } else {
        this.selectedProfile1 = this.restApi.form.get('branchShop').value
      }
      console.log('trong hàm'+this.Content1);
    })
  }
  onSubmit() {
    if (this.restApi.form.valid) {
      this.restApi.form.get('branchShop').setValue(this.selectedProfile1)
      this.restApi.form.get('supplier').setValue(this.selectedProfile)
      this.restApi.form.get('date').setValue(this.datepipe.transform(this.restApi.form.get('date').value, 'yyyy-MM-dd'))
      if (!this.restApi.form.get('id').value) {
        this.restApi.createEmployeetype(this.restApi.form.value).subscribe((data: {}) => {
          this.notificationService.success('Thêm thành công');
          this.restApi.form.reset(); //thêm vào
          this.onClose();
          this.CheckRegion.danhco = 'supplycontract';
          this.router.navigate(['/home'])
        })
      } else {
        
        this.restApi.updateEmployeetype(this.restApi.form.value).subscribe((data: {}) => {
          this.notificationService.success('Sửa thành công');
          this.restApi.form.reset();  //thêm vào
          this.onClose();
          this.CheckRegion.danhco = 'supplycontract';
          this.router.navigate(['/home'])
        })
      }
      // this.restApi.updateEmployee(this.restApi.form.value);
      //this.service.initializeFormGroup();
      // this.notificationService.success(':: Submitted successfully');
      // this.onClose();
      // this.CheckRegion.danhco = 'employeetype';
      // this.router.navigate(['/home'])
    }
  }
  addEmployeetype() {
    this.restApi.employeetypeDetails.id = 0;
    this.restApi.createEmployeetype(this.restApi.employeetypeDetails).subscribe((data: {}) => {
      this.notificationService.success('Thêm thành công');
      this.onClose();
      //biến này set theo tên của folder tổng
      this.CheckRegion.danhco = 'supplycontract';
      this.router.navigate(['/home'])
    })
  }
  modifyEmployeetype() {
    this.restApi.createEmployeetype(this.restApi.employeetypeDetails).subscribe((data: {}) => {
      this.notificationService.success('Sửa thành công');
      this.onClose();
       //biến này set theo tên của folder tổng
      this.CheckRegion.danhco = 'supplycontract';
      this.router.navigate(['/home'])
    })
  }
  onClear() {
    this.restApi.form.reset();
    this.restApi.initializeFormGroup();
    this.notificationService.success('Nhập lại thành công');
  }
  onClose() {
    this.restApi.form.reset(); //thêm bước này
    this.dialogRef.close();
    this.CheckRegion.danhco = 'supplycontract';
    this.router.navigate(['/home'])
  }

}


