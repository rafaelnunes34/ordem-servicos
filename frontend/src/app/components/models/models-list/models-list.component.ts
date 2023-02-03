import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ModelVehicle } from 'src/app/model/modelVehicle';
import { ModelVehicleService } from 'src/app/service/model-vehicle.service';

@Component({
  selector: 'app-models-list',
  templateUrl: './models-list.component.html',
  styleUrls: ['./models-list.component.css']
})
export class ModelsListComponent implements OnInit {

  txtModelVehicle: string = "";
  modelVehicles: ModelVehicle[] = [];

  constructor(
    private modelVehicleService: ModelVehicleService,
    private route: Router) { }

  ngOnInit(): void {
    this.findAllModelVehicles();
  }

  newFormModelVehicle(): void {
    this.route.navigate(['/models/form']);
  }

  findAllModelVehicles(): void {
    this.modelVehicleService.findVehiclesByModel(this.txtModelVehicle).subscribe(
      {next: (response: ModelVehicle[]) => {
        this.modelVehicles = response;
      }}
    );
  }
}
