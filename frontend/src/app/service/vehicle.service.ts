import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Client } from '../model/client';
import { Vehicle } from '../model/Vehicle';

@Injectable({
  providedIn: 'root'
})
export class VehicleService {

  constructor(private http: HttpClient) { }

  public insertVehiclebyClient(client: Client, vehicle: Vehicle): Observable<Vehicle> {
    return this.http.put<Vehicle>(`${environment.API_URL}/vehicles/${client.cpf}/insertVehicle`, vehicle);
  }
}
