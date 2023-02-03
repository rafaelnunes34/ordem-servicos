import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private route: Router) { }

  ngOnInit(): void {
  }

  public navigateClients(): void {
    this.route.navigate(['clients']);
  }

  public navigateServicos(): void {
    this.route.navigate(['servicos']);
  }

  public navigateModels(): void {
    this.route.navigate(['models']);
  }

}
