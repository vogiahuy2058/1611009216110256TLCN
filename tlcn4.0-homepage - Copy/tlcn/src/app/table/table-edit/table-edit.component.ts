import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { TableRestApiService } from '../table-rest-api.service';
import { TabletypeRestApiService } from 'src/app/tabletype/tabletype-rest-api.service';

@Component({
  selector: 'app-table-edit',
  templateUrl: './table-edit.component.html',
  styleUrls: ['./table-edit.component.css']
})
export class TableEditComponent implements OnInit {

  info: any;
  id = this.actRoute.snapshot.params['id'];
  //name = this.actRoute.snapshot.params['name'];
  Content: any = {}
  Content1: any = [];
  
  @Input() employeetypeDetails = { id: this.id, name: '',note:'',numberOfChair: null,tableType: '' }
  constructor(public restApi: TableRestApiService,
    public restApi1: TabletypeRestApiService,
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
      this.employeetypeDetails.note = this.Content.content.note;
      this.employeetypeDetails.numberOfChair = this.Content.content.numberOfChair; 
      this.employeetypeDetails.tableType = this.Content.content.tableType;  
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
        this.router.navigate(['/table-list'])
      })
    }
  }

}


