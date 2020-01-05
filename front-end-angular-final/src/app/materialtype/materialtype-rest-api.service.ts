import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable,throwError  } from 'rxjs';

import { retry, catchError } from 'rxjs/operators';
import { Router } from '@angular/router';
import { Contentmaterialtype } from './contentmaterialtype';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class MaterialtypeRestApiService {

   // Define API
   apiURL = 'http://localhost:8888/api/v1/material-type';
   employeetypeDetails = {id:null, name: ''}
  constructor(private http: HttpClient,public router: Router) { }

   /*========================================
    CRUD Methods for consuming RESTful API
  =========================================*/

  form: FormGroup = new FormGroup({
    id: new FormControl(0),
    name: new FormControl('', Validators.required)
  });
  initializeFormGroup() {
    this.form.setValue({
      id: null,
      name: '',
    });
  }
  editFormGroup(object) {
    this.form.setValue({
      id: object.id,
      name: object.name,
    });
  }
  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }  

  // HttpClient API get() method => Fetch employees list
  getEmployeetypes(): Observable<Contentmaterialtype> {
    return this.http.get<Contentmaterialtype>(this.apiURL + '/get-all')
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }

  // HttpClient API get() method => Fetch employee
  getEmployeetype(id): Observable<Contentmaterialtype> {
    return this.http.get<Contentmaterialtype>(this.apiURL + '/get?id=' + id)
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }  

  // HttpClient API post() method => Create employee
  createEmployeetype(employee): Observable<Contentmaterialtype> {
    return this.http.post<Contentmaterialtype>(this.apiURL + '/create', JSON.stringify(employee), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }  

  // HttpClient API put() method => Update employee
  updateEmployeetype(employee): Observable<Contentmaterialtype> {
    console.log(JSON.stringify(employee))
    return this.http.put<Contentmaterialtype>(this.apiURL + '/edit', JSON.stringify(employee), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }

  // HttpClient API delete() method => Delete employee
  deleteEmployeetype(id){
    return this.http.put<Contentmaterialtype>(this.apiURL + '/delete?id=' + id, this.httpOptions)
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

