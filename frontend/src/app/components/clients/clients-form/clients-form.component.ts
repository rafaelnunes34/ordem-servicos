import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Observable } from 'rxjs';
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
  public idClient: number;

  public strModelVehicle: string = '';
  public listVehicles: ModelVehicle[] = [];
  public modelVehicle: ModelVehicle;
  public vehicle: Vehicle;

  constructor(
    private clientService: ClientService,
    private modelVehicleService: ModelVehicleService,
    private vehicleService: VehicleService,
    private route: Router,
    private activatedRouter: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.client = new Client();
    this.modelVehicle = new ModelVehicle();
    this.vehicle = new Vehicle();

    this.returnIdClientRouter();
  }

  private returnIdClientRouter(): void {
    let params: Observable<Params> = this.activatedRouter.params;
    params.subscribe(urlParams => {
      this.idClient = urlParams["id"];
    });
    
    if(this.idClient) {
      this.findByClient();
    }
  }

  public nextTabVehicle(): void {
    if (!this.client.id) {
      this.insertClient();
    }
    else {
      this.updateClient();
    }
  }

  public previousTabClient(): void {
    this.activeTabClient();
  }

  private insertClient(): void {
    this.clientService.insert(this.client).subscribe(
      {
        next: (response: Client) => {
          this.client = response;
          if (this.client.id) {
            this.activeTabVehicle();
          }
        }
      }
    );
  }

  private updateClient(): void {
    this.clientService.update(this.client.id, this.client).subscribe(
      {next: (response: Client) => {
        this.client = response;
        if (this.client.id) {
          this.activeTabVehicle();
        }
      }}
    );
  }

  public insertVehicleToClient(): void {
    if (this.modelVehicle) {
      this.vehicle.model = this.modelVehicle;
      if(this.client.id) {
        this.vehicleService.insertVehiclebyClient(this.client, this.vehicle).subscribe(
          {
            next: (response: Vehicle) => {
              this.vehicle = response;
              this.route.navigate(['clients']);
            }
          }
        );
      }
    }
    else {
      alert("ERROOOOOOO");
    }
  }

  private findByClient(): void {
    this.clientService.findById(this.idClient).subscribe(
      {next: (response: Client) => {
        this.client = response;
      }}
    );
  }

  public selectedModelVehicle(model: ModelVehicle): void {
    this.modelVehicle = model;
  }

  public findVehicleByModel(): void {
    this.modelVehicleService.findVehiclesByModel(this.strModelVehicle).subscribe(
      {
        next: (response: ModelVehicle[]) => {
          this.listVehicles = response;
        }
      }
    );

  }

  //Altera tab
  public activeTabClient(): void {
    if (this.tabVehicle) {
      this.tabVehicle = false;
      this.tabClient = true;
    }
  }

  public activeTabVehicle(): void {
    if (this.tabClient) {
      this.tabClient = false;
      this.tabVehicle = true;
    }
  }
}
