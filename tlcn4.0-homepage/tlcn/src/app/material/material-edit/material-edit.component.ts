import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { MaterialRestApiService } from '../material-rest-api.service';
import { MaterialtypeRestApiService } from 'src/app/materialtype/materialtype-rest-api.service';

@Component({
  selector: 'app-material-edit',
  templateUrl: './material-edit.component.html',
  styleUrls: ['./material-edit.component.css']
})
export class MaterialEditComponent implements OnInit {

  info: any;
  id = this.actRoute.snapshot.params['id'];
  //name = this.actRoute.snapshot.params['name'];
  Content: any = {}
  Content1: any = [];
  
  @Input() employeetypeDetails = { id: this.id, inventory: null,materialType:'',maxInventory: null,minInventory: null,name: '' }
  constructor(public restApi: MaterialRestApiService,
    public restApi1: MaterialtypeRestApiService,
    public actRoute: ActivatedRoute,
    private token: TokenStorageService,
    public router: Router) { }

  ngOnInit() {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()
    };
    if(this.info.token == null){
      this.router.navigate(['login'])
    }
    this.loadtabletype();
    this.restApi.getEmployeetype(this.id).subscribe((data: {}) => {
      this.Content = data;    
      this.employeetypeDetails.name = this.Content.content.name;
      this.employeetypeDetails.inventory = this.Content.content.inventory;
      this.employeetypeDetails.materialType = this.Content.content.materialType; 
      this.employeetypeDetails.maxInventory = this.Content.content.maxInventory;  
      this.employeetypeDetails.minInventory = this.Content.content.minInventory;  
    })
    
  }
  loadtabletype(){
    this.restApi1.getEmployeetypes().subscribe((data: {}) => {
      this.Content1 = data;
    })
  }
  //@Input() EmployeetypeData = {id: this.id, name: this.name};
  // Update employee data
  updateEmployeetype() {
    
    if(window.confirm('Are you sure, you want to update?')){
      this.restApi.updateEmployeetype(this.employeetypeDetails).subscribe(data => {
        this.router.navigate(['/material-list'])
      })
    }
  }

}



