import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { SupplierRestApiService } from '../supplier-rest-api.service';
import { CheckService } from 'src/app/check.service';
import { MatDialogRef } from '@angular/material';

@Component({
  selector: 'app-supplier-view',
  templateUrl: './supplier-view.component.html',
  styleUrls: ['./supplier-view.component.css']
})
export class SupplierViewComponent implements OnInit {

  info: any;
  //id = this.actRoute.snapshot.params['id'];
  //name = this.actRoute.snapshot.params['name'];
  Content: any = {}
  
  //@Input() employeetypeDetails = { address:'',email:'', id: this.id,name:'',note:'',phone:'',taxCode:'',totalPurchase: null }
  constructor(
    public CheckRegion: CheckService,
    public restApi: SupplierRestApiService,
    public actRoute: ActivatedRoute,
    private token: TokenStorageService,
    public router: Router,
    public dialogRef: MatDialogRef<SupplierViewComponent>) { }

  ngOnInit() {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()
    };
    if(this.info.token == null){
      this.router.navigate(['login'])
    }
    // this.restApi.getEmployeetype(this.id).subscribe((data: {}) => {
    //   this.Content = data;    
    //   this.employeetypeDetails.name = this.Content.content.name;
    //   this.employeetypeDetails.address = this.Content.content.address;
    //   this.employeetypeDetails.email = this.Content.content.email;
    //   this.employeetypeDetails.note = this.Content.content.note;
    //   this.employeetypeDetails.phone = this.Content.content.phone;  
    //   this.employeetypeDetails.taxCode = this.Content.content.taxCode;
    //   this.employeetypeDetails.totalPurchase = this.Content.content.totalPurchase;
    // })
  }
  onClose() {
    this.dialogRef.close();
    this.CheckRegion.danhco = 'supplier';
    this.router.navigate(['/home'])
  }
  //@Input() EmployeetypeData = {id: this.id, name: this.name};
  // Update employee data
  // updateEmployeetype() {
    
  //   if(window.confirm('Are you sure, you want to update?')){
  //     this.restApi.updateEmployeetype(this.employeetypeDetails).subscribe(data => {
  //       this.router.navigate(['/supplier-list'])
  //     })
  //   }
  // }

}



