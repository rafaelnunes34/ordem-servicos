import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Client } from 'src/app/model/client';
import { ClientResponse } from 'src/app/model/clientResponse';
import { ClientService } from 'src/app/service/client.service';

@Component({
  selector: 'app-clients-list',
  templateUrl: './clients-list.component.html',
  styleUrls: ['./clients-list.component.css']
})
export class ClientsListComponent implements OnInit {

  public listClients: Client[] = [];

  constructor(
    private clientService: ClientService,
    private route: Router
  ) { }

  ngOnInit(): void {
    this.clientService.findAll().subscribe(
      {next: (response: ClientResponse) => {
        this.listClients = response.content;
      }}
    );
  }

  public newClient(): void {
    this.route.navigate(['/clients/form']);
  }

}
