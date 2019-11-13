import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { TabletypeRestApiService } from '../tabletype-rest-api.service';
import { Hero } from '../hero';
import { HeroService } from '../hero.service';

@Component({
  selector: 'app-tabletype-create',
  templateUrl: './tabletype-create.component.html',
  styleUrls: ['./tabletype-create.component.css']
})
export class TabletypeCreateComponent implements OnInit {

  @Input() employeetypeDetails = { name: ''}
  info: any;
  heroes: Hero[];
  name: string;
  empoloyeeID : number;
  empList: Array<Hero> = []; 
  constructor(
    public restApi: TabletypeRestApiService, 
    public heroService: HeroService,
    private token: TokenStorageService,
    public router: Router
  ) { }

  ngOnInit() {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()
    };
    if(!this.token.getToken()){
      this.router.navigate(['login'])
    }
    //this.getHeroes();
  }
  // getHeroes(): void {
  //   this.heroService.getHeroes()
  //   .subscribe(heroes => this.heroes = heroes);
  // }
  savetable() {
    this.restApi.createEmployeetypelist(this.heroes).subscribe((data: {}) => {
      //this.router.navigate(['/tabletype-list'])
    })
  }
  add(name: string): void {
    name = name.trim();
  
    // if (!name) { return; }
    // this.heroService.addHero({ name } as Hero)
    //   .subscribe(hero => {
    //     this.heroes.push(hero);
    //   });
   let customObj = new Hero();
   customObj.name = name;
   for (let Hero of this.empList) {
    if(Hero.name == name){
      window.alert('sản phẩm đã tồn tại');
      return;
    }
  }
   customObj.id = 12; 
   this.empList.push(customObj);
   this.name ="";
   this.empoloyeeID = 0; 
   console.log('haha' + JSON.stringify(this.empList));
  }

  delete(hero: Hero): void {
    this.empList = this.empList.filter(h => h !== hero);
    //this.heroService.deleteHero(hero).subscribe();
    console.log('haha' + JSON.stringify(this.empList));
  }

  addEmployeetype(dataEmployeetype) {

    this.restApi.createEmployeetype(this.employeetypeDetails).subscribe((data: {}) => {
      this.router.navigate(['/tabletype-list'])
    })
  }

}


