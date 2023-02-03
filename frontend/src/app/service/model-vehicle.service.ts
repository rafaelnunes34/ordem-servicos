import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ModelVehicle } from '../model/modelVehicle';

@Injectable({
  providedIn: 'root'
})
export class ModelVehicleService {

  constructor(private http: HttpClient) { }

  public findVehiclesByModel(model: string): Observable<ModelVehicle[]> {
    return this.http.get<ModelVehicle[]>(`${environment.API_URL}/models?modelVehicle=${model}`);
  }

  public findById(id: number):  Observable<ModelVehicle> {
    return this.http.get<ModelVehicle>(`${environment.API_URL}/models/${id}`);
  }

  public insert(modelVehicle: ModelVehicle): Observable<ModelVehicle> {
    return this.http.post<ModelVehicle>(`${environment.API_URL}/models`, modelVehicle);
  }

  public update(id: number, modelVehicle: ModelVehicle): Observable<ModelVehicle> {
    return this.http.put<ModelVehicle>(`${environment.API_URL}/models/${id}`, modelVehicle);
  }
}
