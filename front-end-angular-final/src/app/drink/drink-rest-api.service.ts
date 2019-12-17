import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable,throwError  } from 'rxjs';

import { retry, catchError } from 'rxjs/operators';
import { Router } from '@angular/router';
import { Contentdrink } from './contentdrink';
import { FormGroup, FormControl, Validators } from '@angular/forms';
@Injectable({
  providedIn: 'root'
})
export class DrinkRestApiService {

   // Define API
   apiURL = 'http://localhost:8888/api/v1/drink';
   employeetypeDetails = {id:null, description: '', drinkType: '', name: '' }
  constructor(private http: HttpClient,public router: Router) { }

   /*========================================
    CRUD Methods for consuming RESTful API
  =========================================*/
 //validate s
 form: FormGroup = new FormGroup({
  id: new FormControl(0),
  name: new FormControl('', Validators.required),
  description: new FormControl(''),
  drinkType: new FormControl(''),
 
});
initializeFormGroup() {
  this.form.setValue({
    id: null,
    name: '',
    description: '',
    drinkType: '',
  });
}
editFormGroup(object) {
  this.form.setValue({
    id: object.id,
    name: object.name,
    description: object.description,
    drinkType: object.drinkType,
    
  });
}
 //validate e
  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }  

  // HttpClient API get() method => Fetch employees list
  getEmployeetypes(): Observable<Contentdrink> {
    return this.http.get<Contentdrink>(this.apiURL + '/get-all')
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }

  getEmployeetypessort(name): Observable<Contentdrink> {
    console.log(name + 'haha');
    return this.http.get<Contentdrink>(this.apiURL + '/get-by-drinkType?drinkTypeName=' + name)
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }
  getEmployeetypeshaveprice(): Observable<Contentdrink> {
    return this.http.get<Contentdrink>(this.apiURL + '/get-haveprice')
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }

  getEmployeetypessorthaveprice(name): Observable<Contentdrink> {
    console.log(name + 'haha');
    return this.http.get<Contentdrink>(this.apiURL + '/get-by-drinkType-haveprice?drinkTypeName=' + name)
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }

  // HttpClient API get() method => Fetch employee
  getEmployeetype(id): Observable<Contentdrink> {
    return this.http.get<Contentdrink>(this.apiURL + '/get?id=' + id)
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }  

  // HttpClient API post() method => Create employee
  createEmployeetype(employee): Observable<Contentdrink> {
    console.log('haha' + JSON.stringify(employee));
    return this.http.post<Contentdrink>(this.apiURL + '/create', JSON.stringify(employee), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }  

  // HttpClient API put() method => Update employee
  updateEmployeetype(employee): Observable<Contentdrink> {
    console.log(JSON.stringify(employee))
    return this.http.put<Contentdrink>(this.apiURL + '/edit', JSON.stringify(employee), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }

  // HttpClient API delete() method => Delete employee
  deleteEmployeetype(id){
    return this.http.put<Contentdrink>(this.apiURL + '/delete?id=' + id, this.httpOptions)
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
     if(error.status === 403){
      window.alert("Bạn không có quyền để thực hiện chức năng này");
     }
     window.alert(errorMessage);
     return throwError(errorMessage);
  }
  
}


