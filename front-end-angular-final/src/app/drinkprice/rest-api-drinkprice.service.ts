import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable,throwError  } from 'rxjs';

import { retry, catchError } from 'rxjs/operators';
import { Router } from '@angular/router';
import { Contentdrinkprice } from './contentdrinkprice';

@Injectable({
  providedIn: 'root'
})
export class RestApiDrinkpriceService {

   // Define API
   apiURL = 'http://localhost:8888/api/v1/drink-price';

  constructor(private http: HttpClient,public router: Router) { }

   /*========================================
    CRUD Methods for consuming RESTful API
  =========================================*/

  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }  

  // HttpClient API get() method => Fetch employees list
  getEmployeetypes(): Observable<Contentdrinkprice> {
    return this.http.get<Contentdrinkprice>(this.apiURL + '/get-all')
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }

  getEmployeetypessort(name): Observable<Contentdrinkprice> {
    console.log(name + 'haha');
    return this.http.get<Contentdrinkprice>(this.apiURL + '/get-by-drinkType?drinkTypeName=' + name)
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }

  // HttpClient API get() method => Fetch employee
  getEmployeetype(id): Observable<Contentdrinkprice> {
    return this.http.get<Contentdrinkprice>(this.apiURL + '/get?drinkId=' + id)
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }  

  // HttpClient API post() method => Create employee
  createEmployeetype(employee): Observable<Contentdrinkprice> {
    console.log('haha' + JSON.stringify(employee));
    return this.http.post<Contentdrinkprice>(this.apiURL + '/create', JSON.stringify(employee), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }  

  // HttpClient API put() method => Update employee
  updateEmployeetype(employee): Observable<Contentdrinkprice> {
    console.log(JSON.stringify(employee))
    return this.http.put<Contentdrinkprice>(this.apiURL + '/edit', JSON.stringify(employee), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }

  // HttpClient API delete() method => Delete employee
  deleteEmployeetype(id){
    return this.http.put<Contentdrinkprice>(this.apiURL + '/delete?id=' + id, this.httpOptions)
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
     if(error.status === 500){
      window.alert("Email đã tồn tại vùi lòng nhập email khác!");
     }
     window.alert(errorMessage);
     return throwError(errorMessage);
  }
  
}



