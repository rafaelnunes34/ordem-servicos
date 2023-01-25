import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Client } from '../model/client';
import { ClientResponse } from '../model/clientResponse';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  constructor(private http: HttpClient) { }

  public findAll(): Observable<ClientResponse> {
    return this.http.get<ClientResponse>(`${environment.API_URL}/clients?&sort=name,asc`);
  }

  public insert(client: Client): Observable<Client> {
    return this.http.post<Client>(`${environment.API_URL}/clients`, client);
  }
}
