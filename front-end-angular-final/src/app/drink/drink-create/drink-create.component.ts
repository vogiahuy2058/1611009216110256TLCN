import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { DrinkRestApiService } from '../drink-rest-api.service';
import { DrinktypeRestApiService } from 'src/app/drinktype/drinktype-rest-api.service';
import { MatDialogRef } from '@angular/material';
import { NotificationService } from 'src/app/testpopups/notification.service';
import { CheckService } from 'src/app/check.service';

@Component({
  selector: 'app-drink-create',
  templateUrl: './drink-create.component.html',
  styleUrls: ['./drink-create.component.css']
})
export class DrinkCreateComponent implements OnInit {

  // @Input() employeetypeDetails = { description: '', drinkType: '', name: '' }
  id: any;
  info: any;
  Content: any = [];
  //validate s dùng cho selectbox
  contentDetail: any = []
  selectedProfile: any = []
  constructor(
    public CheckRegion: CheckService,
    public restApi: DrinkRestApiService,
    public restApi1: DrinktypeRestApiService,
    private token: TokenStorageService,
    public router: Router,
    private notificationService: NotificationService,
    //component create chọn làm popup
    public dialogRef: MatDialogRef<DrinkCreateComponent>
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
    this.loadEmployeetype()
    this.id = this.restApi.employeetypeDetails.id
  }
  loadEmployeetype() {
    return this.restApi1.getEmployeetypes().subscribe((data: {}) => {
      this.Content = data;
      //validate s 
      this.contentDetail = this.Content.content;
      if (!this.restApi.form.get('id').value) {
        this.selectedProfile = this.contentDetail[0].name;
        // this.gender = true
        console.log('drinktype' + this.selectedProfile)
      } else {
        this.selectedProfile = this.restApi.form.get('drinkType').value

        // this.gender = this.restApi.form.get('sex').value
        console.log('drinktype' + this.selectedProfile)
      }
      //validate e
      console.log('trong hàm' + this.Content);
    })
  }
//validate s 
onSubmit() {
  if (this.restApi.form.valid) {
    //nếu có selectbox
    this.restApi.form.get('drinkType').setValue(this.selectedProfile)
    
    if (!this.restApi.form.get('id').value) {
    
      this.restApi.createEmployeetype(this.restApi.form.value).subscribe((data: {}) => {
        this.notificationService.success('Thêm thành công');
        this.restApi.form.reset(); //thêm vào
        this.onClose();
        this.CheckRegion.danhco = 'drink';
        this.router.navigate(['/home'])
      })
    } else {

      this.restApi.updateEmployeetype(this.restApi.form.value).subscribe((data: {}) => {
        this.notificationService.success('Sửa thành công');
        this.restApi.form.reset();  //thêm vào
        this.onClose();
        this.CheckRegion.danhco = 'drink';
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
      //////////
      this.restApi.form.reset();//thêm vào
      this.notificationService.success('Thêm thành công');
      this.onClose();
      //biến này set theo tên của folder tổng
      this.CheckRegion.danhco = 'drink';
      this.router.navigate(['/home'])
    })
  }
  modifyEmployeetype() {
    this.restApi.createEmployeetype(this.restApi.employeetypeDetails).subscribe((data: {}) => {
      this.notificationService.success('Sửa thành công');
      this.onClose();
      //biến này set theo tên của folder tổng
      this.CheckRegion.danhco = 'drink';
      this.router.navigate(['/home'])
    })
  }
  onClose() {
    //validate s
    this.restApi.form.reset(); //thêm bước này
    //validate e
    this.dialogRef.close();
    this.CheckRegion.danhco = 'drink';
    this.router.navigate(['/home'])
  }
   //validate s
  onClear() {
    this.restApi.form.reset();
    this.restApi.initializeFormGroup();
    this.notificationService.success('Nhập lại thành công');
  }//validate e
}


