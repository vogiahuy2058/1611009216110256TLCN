import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { EmployeeRestApiService } from '../employee-rest-api.service';
import { EmployeetypeRestApiService } from '../../employeetype/employeetype-rest-api.service';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { BranchshopRestApiService } from 'src/app/branchshop/branchshop-rest-api.service';
import { CheckService } from 'src/app/check.service';
import { NotificationService } from 'src/app/testpopups/notification.service';
import { MatDialogRef } from '@angular/material';

@Component({
  selector: 'app-employee-create',
  templateUrl: './employee-create.component.html',
  styleUrls: ['./employee-create.component.css']
})
export class EmployeeCreateComponent implements OnInit {

  // @Input() employeetypeDetails = { branchShop: '', email:'', employeeType:'', name:''}
  info: any;
  id: any;
  Content:any = [];
  Content1:any = [];
  contentDetail1: any = []
  selectedProfile1: any = []
  contentDetail: any = []
  selectedProfile: any = []
  constructor(
    public CheckRegion: CheckService,
    public restApi2: BranchshopRestApiService,
    public restApi: EmployeeRestApiService,
    public restApi1: EmployeetypeRestApiService, 
    private token: TokenStorageService,
    public router: Router,
    private notificationService: NotificationService,
    //component create chọn làm popup
    public dialogRef: MatDialogRef<EmployeeCreateComponent>
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
    this.id = this.restApi.employeetypeDetails.id
    this.loadEmployeetype()
    this.loadEmployeetype1()
  }
  loadEmployeetype() {
    return this.restApi1.getEmployeetypes().subscribe((data: {}) => {
      this.Content = data;
      this.contentDetail = this.Content.content;
      if (!this.restApi.form.get('id').value) {
        this.selectedProfile = this.contentDetail[0].name;
      } else {
        this.selectedProfile = this.restApi.form.get('employeeType').value

      }
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
    })
  }
  onSubmit() {
    if (this.restApi.form.valid) {
     
      this.restApi.form.get('employeeType').setValue(this.selectedProfile)
      this.restApi.form.get('branchShop').setValue(this.selectedProfile1)
      if (!this.restApi.form.get('id').value) {
        this.restApi.createEmployeetype(this.restApi.form.value).subscribe((data: {}) => {
          this.notificationService.success('Thêm thành công');
          this.restApi.form.reset(); //thêm vào
          this.onClose();
          this.CheckRegion.danhco = 'employee';
          this.router.navigate(['/home'])
        })
      } else {
        
        this.restApi.updateEmployeetype(this.restApi.form.value).subscribe((data: {}) => {
          this.notificationService.success('Sửa thành công');
          this.restApi.form.reset();  //thêm vào
          this.onClose();
          this.CheckRegion.danhco = 'employee';
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
       //validate s
    this.restApi.form.reset(); //thêm bước này
    //validate e
      this.notificationService.success('Thêm thành công');
      this.onClose();
      //biến này set theo tên của folder tổng
      this.CheckRegion.danhco = 'employee';
      this.router.navigate(['/home'])
    })
  }
  modifyEmployeetype() {
    this.restApi.createEmployeetype(this.restApi.employeetypeDetails).subscribe((data: {}) => {
      this.notificationService.success('Sửa thành công');
      this.onClose();
      //biến này set theo tên của folder tổng
      this.CheckRegion.danhco = 'employee';
      this.router.navigate(['/home'])
    })
  }
  onClose() {
     //validate s
     this.restApi.form.reset(); //thêm bước này
     //validate e
    this.dialogRef.close();
    this.CheckRegion.danhco = 'employee';
    this.router.navigate(['/home'])
  }
  onClear() {
    this.restApi.form.reset();
    this.restApi.initializeFormGroup();
    this.notificationService.success('Nhập lại thành công');
  }
}

