import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/auth/token-storage.service';

import { MaterialRestApiService } from '../material-rest-api.service';
import { MaterialtypeRestApiService } from 'src/app/materialtype/materialtype-rest-api.service';
import { UnitRestApiService } from 'src/app/unit/unit-rest-api.service';
import { CheckService } from 'src/app/check.service';
import { NotificationService } from 'src/app/testpopups/notification.service';
import { MatDialogRef } from '@angular/material';

@Component({
  selector: 'app-material-create',
  templateUrl: './material-create.component.html',
  styleUrls: ['./material-create.component.css']
})
export class MaterialCreateComponent implements OnInit {

  // @Input() employeetypeDetails = { inventory: null,materialType:'',maxInventory: null,minInventory: null,name: '', unit: ''}
  id: any;
  info: any;
  Content: any = [];
  Content1: any = [];
  contentDetail1: any = []
  selectedProfile1: any = []
  contentDetail: any = []
  selectedProfile: any = []
  constructor(
    public CheckRegion: CheckService,
    public restApi: MaterialRestApiService,
    public restApi1: MaterialtypeRestApiService,
    public restApi2: UnitRestApiService,
    private token: TokenStorageService,
    public router: Router,
    private notificationService: NotificationService,
    //component create chọn làm popup
    public dialogRef: MatDialogRef<MaterialCreateComponent>
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
    this.loadtabletype();
    this.loadtabletype1();
    this.id = this.restApi.employeetypeDetails.id
  }
  loadtabletype() {
    this.restApi1.getEmployeetypes().subscribe((data: {}) => {
      this.Content = data;
      this.contentDetail = this.Content.content;
      if (!this.restApi.form.get('id').value) {
        this.selectedProfile = this.contentDetail[0].name;
      } else {
        this.selectedProfile = this.restApi.form.get('materialType').value
      }
    })
  }
  loadtabletype1() {
    this.restApi2.getEmployeetypes().subscribe((data: {}) => {
      this.Content1 = data;
      this.contentDetail1 = this.Content1.content;
      if (!this.restApi.form.get('id').value) {
        this.selectedProfile1 = this.contentDetail1[0].name;
      } else {
        this.selectedProfile1 = this.restApi.form.get('unit').value
      }
    })
  }
  onSubmit() {
    if (this.restApi.form.valid) {
      this.restApi.form.get('unit').setValue(this.selectedProfile1)
      this.restApi.form.get('materialType').setValue(this.selectedProfile)
      if (this.restApi.form.get('inventory').value > this.restApi.form.get('maxInventory').value) {
        window.alert('thực tồn không được vượt quá tồn tối đa')
      } else if (this.restApi.form.get('inventory').value < this.restApi.form.get('minInventory').value) {
        window.alert('thực tồn không được nhỏ hơn tồn tối thiểu')
      } else if (this.restApi.form.get('maxInventory').value < this.restApi.form.get('minInventory').value) {
        window.alert('tồn tối thiếu phải bé hơn tồn tối đa')
      } else {
        if (!this.restApi.form.get('id').value) {
          this.restApi.createEmployeetype(this.restApi.form.value).subscribe((data: {}) => {
            this.notificationService.success('Thêm thành công');
            this.restApi.form.reset(); //thêm vào
            this.onClose();
            this.CheckRegion.danhco = 'material';
            this.router.navigate(['/home'])
          })
        } else {

          this.restApi.updateEmployeetype(this.restApi.form.value).subscribe((data: {}) => {
            this.notificationService.success('Sửa thành công');
            this.restApi.form.reset();  //thêm vào
            this.onClose();
            this.CheckRegion.danhco = 'material';
            this.router.navigate(['/home'])
          })
        }
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
      //////////
      this.notificationService.success('Thêm thành công');
      this.onClose();
      //biến này set theo tên của folder tổng
      this.CheckRegion.danhco = 'material';
      this.router.navigate(['/home'])
    })
  }
  modifyEmployeetype() {
    this.restApi.createEmployeetype(this.restApi.employeetypeDetails).subscribe((data: {}) => {
      this.notificationService.success('Sửa thành công');
      this.onClose();
      //biến này set theo tên của folder tổng
      this.CheckRegion.danhco = 'material';
      this.router.navigate(['/home'])
    })
  }
  onClear() {
    this.restApi.form.reset();
    this.restApi.initializeFormGroup();
    this.notificationService.success('Nhập lại thành công');
  }
  onClose() {
    this.restApi.form.reset();
    this.dialogRef.close();
    this.CheckRegion.danhco = 'material';
    this.router.navigate(['/home'])
  }

}



