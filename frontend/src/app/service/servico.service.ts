import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Servico } from '../model/servico';
import { ServicoResponse } from '../model/servicoResponse';

@Injectable({
  providedIn: 'root'
})
export class ServicoService {

  constructor(private http: HttpClient) { }

  findAll(description: string): Observable<ServicoResponse> {
    return this.http.get<ServicoResponse>(`${environment.API_URL}/services?description=${description}&number=0&sort=description,asc`);
  }

  findById(id: number): Observable<Servico> {
    return this.http.get<Servico>(`${environment.API_URL}/services/${id}`);
  }

  insert(servico: Servico): Observable<Servico> {
    return this.http.post<Servico>(`${environment.API_URL}/services`, servico);
  }

  update(id: number, servico: Servico): Observable<Servico> {
    return this.http.put<Servico>(`${environment.API_URL}/services/${id}`, servico);
  }
}
