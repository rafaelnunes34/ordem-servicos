import { Component, OnInit } from '@angular/core';
import { Client } from 'src/app/model/client';
import { ModelVehicle } from 'src/app/model/modelVehicle';
import { Vehicle } from 'src/app/model/Vehicle';
import { ClientService } from 'src/app/service/client.service';
import { ModelVehicleService } from 'src/app/service/model-vehicle.service';
import { VehicleService } from 'src/app/service/vehicle.service';

@Component({
  selector: 'app-clients-form',
  templateUrl: './clients-form.component.html',
  styleUrls: ['./clients-form.component.css']
})
export class ClientsFormComponent implements OnInit {

  public tabClient: boolean = true;
  public tabVehicle: boolean = false;
  public client: Client;

  public strModelVehicle: string = '';
  public listVehicles: ModelVehicle[] = [];
  public modelVehicle: ModelVehicle;
  public vehicle: Vehicle;

  constructor(
    private clientService: ClientService,
    private modelVehicleService: ModelVehicleService,
    private vehicleService: VehicleService
    ) { }

  ngOnInit(): void {
    this.client = new Client();
    this.modelVehicle = new ModelVehicle();
    this.vehicle = new Vehicle();
  }

  public submitClient(): void {
    this.clientService.insert(this.client).subscribe(
      {next: (response: Client) => {
        console.log("primeiro");
        this.client = response;
        this.insertVehicleByClient(this.client);
      }}
    );
  }

  public selectedModelVehicle(model: ModelVehicle): void {
    this.modelVehicle = model;
  }

  public findVehicleByModel(): void {
    this.modelVehicleService.findVehiclesByModel(this.strModelVehicle).subscribe(
      {next: (response: ModelVehicle[]) => {
        this.listVehicles = response;
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

  private insertVehicleByClient(client: Client): void {
    if(this.modelVehicle != null) {
      this.vehicle.model = this.modelVehicle;
      this.vehicleService.insertVehiclebyClient(this.client, this.vehicle).subscribe(
        {next: (response: Vehicle) => {
          console.log("segundo");
          this.vehicle = response;
          console.log("Novo veiculo " + this.vehicle);
        }}
      );
    }
    else {
      alert("ERROOOOOOO");
    }
  }
}
