import { Component, OnInit } from '@angular/core';
import { Client } from 'src/app/model/client';
import { ClientService } from 'src/app/service/client.service';
declare var $: any;

@Component({
  selector: 'app-order-form',
  templateUrl: './order-form.component.html',
  styleUrls: ['./order-form.component.css']
})
export class OrderFormComponent implements OnInit {

  public isTabClient: boolean = true;
  public isTabVehicle: boolean = false;
  public isTabService: boolean = false;

  public cpf: string = "";
  public client: Client = {
    id: null,
    name: null,
    cpf: null,
    phone: null
  }

  constructor(
    private clientService: ClientService
  ) { }

  ngOnInit(): void {
  }

  findByClientCpf(): void {
    this.clientService.findByCpf(this.cpf).subscribe(
      {next: (response: Client) => {
        this.client = response;
        this.cpf = null;
      }}
    );
  }

  

  activeTabVehicle(): void {
    if(this.isTabClient) {
      this.isTabVehicle = true;
      this.isTabClient = false;
    }
    
  }
}
