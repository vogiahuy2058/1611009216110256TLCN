import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';

import { retry, catchError } from 'rxjs/operators';
import { Router } from '@angular/router';
import { Contentsupplycontract } from './contentsupplycontract';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class SupplycontractRestApiService {
  employeetypeDetails = { id: null, branchShop: '', date: '', supplier: '', totalPrice: null }
  // Define API
  apiURL = 'http://localhost:8888/api/v1/supply-contract';

  constructor(private http: HttpClient, public router: Router) { }

  /*========================================
   CRUD Methods for consuming RESTful API
 =========================================*/
  form: FormGroup = new FormGroup({
    id: new FormControl(0),
    supplier: new FormControl(''),
    date: new FormControl('', Validators.required),
    branchShop: new FormControl(''),
    totalPrice: new FormControl(0)
  });
  initializeFormGroup() {
    this.form.setValue({
      id: null,
      branchShop: '',
      date: '',
      supplier: '',
      totalPrice: null
    });
  }
  editFormGroup(object) {
    this.form.setValue({
      id: object.id,
      branchShop: object.branchShop,
      date: object.date,
      supplier: object.supplier,
      totalPrice: object.totalPrice
    });
  }
  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  // HttpClient API get() method => Fetch employees list
  getEmployeetypes(): Observable<Contentsupplycontract> {
    return this.http.get<Contentsupplycontract>(this.apiURL + '/get-all')
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  // HttpClient API get() method => Fetch employee
  getEmployeetype(id): Observable<Contentsupplycontract> {
    return this.http.get<Contentsupplycontract>(this.apiURL + '/get?id=' + id)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  // HttpClient API post() method => Create employee
  createEmployeetype(employee): Observable<Contentsupplycontract> {
    console.log('haha' + JSON.stringify(employee));
    return this.http.post<Contentsupplycontract>(this.apiURL + '/create', JSON.stringify(employee), this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  // HttpClient API put() method => Update employee
  updateEmployeetype(employee): Observable<Contentsupplycontract> {
    console.log(JSON.stringify(employee))
    return this.http.put<Contentsupplycontract>(this.apiURL + '/edit', JSON.stringify(employee), this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  // HttpClient API delete() method => Delete employee
  deleteEmployeetype(id) {
    return this.http.put<Contentsupplycontract>(this.apiURL + '/delete?id=' + id, this.httpOptions)
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
    if (error.status === 500) {
      window.alert("Email đã tồn tại vùi lòng nhập email khác!");
    }
    window.alert(errorMessage);
    return throwError(errorMessage);
  }

}


