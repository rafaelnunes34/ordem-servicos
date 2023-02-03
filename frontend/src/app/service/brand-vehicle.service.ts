import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { BrandVehicle } from '../model/brandVehicle';

@Injectable({
  providedIn: 'root'
})
export class BrandVehicleService {

  constructor(private http: HttpClient) { }

  public findAll(): Observable<BrandVehicle[]> {
    return this.http.get<BrandVehicle[]>(`${environment.API_URL}/brands`);
  }
}
