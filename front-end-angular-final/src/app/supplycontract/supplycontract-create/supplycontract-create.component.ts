import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { BranchshopRestApiService } from 'src/app/branchshop/branchshop-rest-api.service';
import { SupplierRestApiService } from 'src/app/supplier/supplier-rest-api.service';
import { SupplycontractRestApiService } from '../supplycontract-rest-api.service';
import { CheckService } from 'src/app/check.service';
import { NotificationService } from 'src/app/testpopups/notification.service';
import { MatDialogRef } from '@angular/material';

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
  //Content2:any = [this.employeetypeDetaillist];
  Content:any = [];
  Content1:any = [];
  constructor(
    public CheckRegion: CheckService,
    public restApi2: BranchshopRestApiService,
    public restApi: SupplycontractRestApiService,
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
  }
  loadEmployeetype() {
    return this.restApi1.getEmployeetypes().subscribe((data: {}) => {
      this.Content = data;
      console.log('trong hàm'+this.Content);
    })
  }
  loadEmployeetype1() {
    return this.restApi2.getEmployeetypes().subscribe((data: {}) => {
      this.Content1 = data;
      console.log('trong hàm'+this.Content1);
    })
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
  onClose() {
    this.dialogRef.close();
    this.CheckRegion.danhco = 'supplycontract';
    this.router.navigate(['/home'])
  }

}


