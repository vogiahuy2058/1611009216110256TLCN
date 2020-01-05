import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable,throwError  } from 'rxjs';

import { retry, catchError } from 'rxjs/operators';
import { Router } from '@angular/router';
import { Contentmaterial } from './contentmaterial';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class MaterialRestApiService {

   // Define API
   apiURL = 'http://localhost:8888/api/v1/material';
   employeetypeDetails = {id:null, inventory: null,materialType:'',maxInventory: null,minInventory: null,name: '', unit: ''}
  constructor(private http: HttpClient,public router: Router) { }

   /*========================================
    CRUD Methods for consuming RESTful API
  =========================================*/
  form: FormGroup = new FormGroup({
    id: new FormControl(0),
    inventory: new FormControl('' , Validators.required),
    materialType: new FormControl(''),
    maxInventory: new FormControl(0 , Validators.required),
    minInventory: new FormControl(0 , Validators.required),
    name: new FormControl('', Validators.required),
    unit: new FormControl(''),
  });
  initializeFormGroup() {
    this.form.setValue({
      id:null, inventory: null,materialType:'',maxInventory: null,minInventory: null,name: '', unit: ''
    });
  }
  editFormGroup(object) {
    this.form.setValue({
      id:object.id, inventory: object.inventory,materialType:object.materialType,maxInventory: object.maxInventory,
      minInventory: object.minInventory,name: object.name, unit: object.unit
    });
  }
  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }  

  // HttpClient API get() method => Fetch employees list
  getEmployeetypes(): Observable<Contentmaterial> {
    return this.http.get<Contentmaterial>(this.apiURL + '/get-all')
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }

  // HttpClient API get() method => Fetch employee
  getEmployeetype(id): Observable<Contentmaterial> {
    return this.http.get<Contentmaterial>(this.apiURL + '/get?id=' + id)
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }  

  // HttpClient API post() method => Create employee
  createEmployeetype(employee): Observable<Contentmaterial> {
    return this.http.post<Contentmaterial>(this.apiURL + '/create', JSON.stringify(employee), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }  

  // HttpClient API put() method => Update employee
  updateEmployeetype(employee): Observable<Contentmaterial> {
    console.log(JSON.stringify(employee))
    return this.http.put<Contentmaterial>(this.apiURL + '/edit', JSON.stringify(employee), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }

  // HttpClient API delete() method => Delete employee
  deleteEmployeetype(id){
    return this.http.put<Contentmaterial>(this.apiURL + '/delete?id=' + id, this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }

  // Error handling 
  handleError(error) {
     let errorMessage = '';
     if(error.error instanceof ErrorEvent) {
       // Get client-side error
       errorMessage = error.error.message;
     } else {
       // Get server-side error
       errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
     }
     //this.router.navigateByUrl(`/login`);
     window.alert(errorMessage);
     return throwError(errorMessage);
  }
  
}



