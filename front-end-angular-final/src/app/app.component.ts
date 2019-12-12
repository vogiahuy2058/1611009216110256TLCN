import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Contentmaterial } from './material/contentmaterial';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit  {
    name = 'Angular';
    apiUrl = 'http://localhost:8888/api/v1/material/get-all';
    numbers: number[] = [];
    public minDate: Date = new Date ("05/07/2017");
    public maxDate: Date = new Date ("05/27/2017");
    public dateValue: Date = new Date ("05/16/2017");
    constructor (private httpClient: HttpClient) {
    //   for (let index = 0; index < 10000; index++) {
    //     this.numbers.push(index);
    // }
    
  }
  ngOnInit(){
    // this.fetchData();
    this.resolveAfter2Seconds(20).then(value => {
      console.log(`promise result: ${value}`);
    });
    console.log('I will not wait until promise is resolved');
  }
  resolveAfter2Seconds(x) {
    return new Promise(resolve => {
      setTimeout(() => {
        resolve(x);
      }, 2000);
    });
  }
  // waitForOneSecond() {
  //   return new Promise(resolve => {
  //     setTimeout(() => {
  //       resolve("I promise to return after one second!");
  //     }, 1000);
  //   });
  // }
  // private fetchData(){
  //   const promise =  this.httpClient.get<Contentmaterial>(this.apiUrl).toPromise();
  //   console.log(promise);  
  //   promise.then((data)=>{
  //     console.log("Promise resolved with: " + JSON.stringify(data));
  //   }, (error)=>{
  //     console.log("Promise rejected with " + JSON.stringify(error));
  //   })
  // }
  title = 'tlcn';
}
