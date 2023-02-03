import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { BrandVehicle } from 'src/app/model/brandVehicle';
import { ModelVehicle } from 'src/app/model/modelVehicle';
import { BrandVehicleService } from 'src/app/service/brand-vehicle.service';
import { ModelVehicleService } from 'src/app/service/model-vehicle.service';

@Component({
  selector: 'app-models-form',
  templateUrl: './models-form.component.html',
  styleUrls: ['./models-form.component.css']
})
export class ModelsFormComponent implements OnInit {

  modelVehicle: ModelVehicle;
  modelVehicleId: number;
  brandId: number;

  brandsVehicle: BrandVehicle[] = [];

  constructor(
    private modelVehicleService: ModelVehicleService,
    private brandVehicleService: BrandVehicleService,
    private route: Router,
    private activatedRouter: ActivatedRoute) { }

  ngOnInit(): void {
    this.modelVehicle = new ModelVehicle();
    this.returnIdRouterModelVehicle();
    this.findAllBrandsVehicle();

    if(this.modelVehicle.id) {
      this.brandId = this.modelVehicle.brand.id;
    }
    this.changeBrandVehicle()
  }

  submit(): void {
    if(!this.modelVehicle.id) {
      this.insertModelVehicle();
      console.log("AQUI", this.modelVehicle);
    }
    else {
      this.updateModelVehicle();
    }
    this.route.navigate(['models']);
  }

  changeBrandVehicle(): void {
    this.brandsVehicle.forEach(brand => {
      if(this.brandId == brand.id) {
        this.modelVehicle.brand = brand;
      }
    })
    console.log("Fabricante selecionado", this.brandId);
  }

  private insertModelVehicle(): void {
    this.modelVehicleService.insert(this.modelVehicle).subscribe(
      {next: (response: ModelVehicle) => {
        this.modelVehicle = response;
      }}
    );
  }

  private updateModelVehicle(): void {
    this.modelVehicleService.update(this.modelVehicleId, this.modelVehicle).subscribe(
      {next: (response: ModelVehicle) => {
        this.modelVehicle = response;
      }}
    );
  }

  private returnIdRouterModelVehicle(): void {
    let params: Observable<Params> = this.activatedRouter.params;
    params.subscribe(urlParams => {
      this.modelVehicleId = urlParams["id"];
    });
    
    if(this.modelVehicleId) {
      this.findByIdVehicle();
    }
  }

  private findByIdVehicle(): void {
    this.modelVehicleService.findById(this.modelVehicleId).subscribe(
      {next: (response: ModelVehicle) => {
        this.modelVehicle = response;
      }}
    );
  }

  private findAllBrandsVehicle(): void {
    this.brandVehicleService.findAll().subscribe(
      {next: (response: BrandVehicle[]) => {
        this.brandsVehicle = response;
      }}
    );
  }

}
