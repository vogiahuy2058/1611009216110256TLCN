import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';

import { retry, catchError } from 'rxjs/operators';
import { Router } from '@angular/router';
import { Contentsupplier } from './contentsupplier';
import { Supplier } from './supplier';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class SupplierRestApiService {

  // Define API
  apiURL = 'http://localhost:8888/api/v1/supplier';
  employeetypeDetails = { id: null, address: '', email: '', name: '', note: '', phone: '', taxCode: '', totalPurchase: null }
  constructor(private http: HttpClient, public router: Router) { }
  /*========================================
   CRUD Methods for consuming RESTful API
 =========================================*/
  form: FormGroup = new FormGroup({
    id: new FormControl(0),
    address: new FormControl('', Validators.required),
    email: new FormControl('', Validators.email),
    name: new FormControl('', Validators.required),
    note: new FormControl(''),
    phone: new FormControl('', [Validators.required, Validators.minLength(10)]),
    taxCode: new FormControl('', Validators.required),
    totalPurchase: new FormControl(0)
  });
  initializeFormGroup() {
    this.form.setValue({
      id: null, address: '', email: '', name: '', note: '', phone: '', taxCode: '', totalPurchase: null
    });
  }
  editFormGroup(object) {
    this.form.setValue({
      id:object.id, address:object.address,email:object.email,name:object.name,note:object.note,phone:object.phone,
      taxCode:object.taxCode,totalPurchase: object.totalPurchase
    });
  }
  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  // HttpClient API get() method => Fetch employees list
  getEmployeetypes(): Observable<Contentsupplier> {
    return this.http.get<Contentsupplier>(this.apiURL + '/get-all')
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  // HttpClient API get() method => Fetch employee
  getEmployeetype(id): Observable<Contentsupplier> {
    return this.http.get<Contentsupplier>(this.apiURL + '/get?id=' + id)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  // HttpClient API post() method => Create employee
  createEmployeetype(employee): Observable<Contentsupplier> {
    return this.http.post<Contentsupplier>(this.apiURL + '/create', JSON.stringify(employee), this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  // HttpClient API put() method => Update employee
  updateEmployeetype(employee): Observable<Contentsupplier> {
    console.log(JSON.stringify(employee))
    return this.http.put<Contentsupplier>(this.apiURL + '/edit', JSON.stringify(employee), this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  // HttpClient API delete() method => Delete employee
  deleteEmployeetype(id) {
    return this.http.put<Contentsupplier>(this.apiURL + '/delete?id=' + id, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  // Error handling 
  handleError(error) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
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


