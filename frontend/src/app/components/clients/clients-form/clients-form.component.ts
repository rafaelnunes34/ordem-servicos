import { Component, OnInit } from '@angular/core';
import { Client } from 'src/app/model/client';
import { ClientService } from 'src/app/service/client.service';

@Component({
  selector: 'app-clients-form',
  templateUrl: './clients-form.component.html',
  styleUrls: ['./clients-form.component.css']
})
export class ClientsFormComponent implements OnInit {

  public tabClient: boolean = true;
  public tabVehicle: boolean = false;
  public client: Client;

  constructor(private clientService: ClientService) { }

  ngOnInit(): void {
    this.client = new Client();
  }

  public submitClient(): void {
    this.clientService.insert(this.client).subscribe(
      {next: (response: Client) => {
        this.client = response;
        console.log("SUCESSO -> ", this.client);
      }}
    );
  }

  //Altera tab
  public activeTabClient(): void {
    if(this.tabVehicle) {
      this.tabVehicle = false;
      this.tabClient = true;
    }
  }

  public activeTabVehicle(): void {
    if(this.tabClient) {
      this.tabClient = false;
      this.tabVehicle = true;
    }
  }
}
