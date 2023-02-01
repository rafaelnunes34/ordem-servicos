import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Servico } from 'src/app/model/servico';
import { ServicoResponse } from 'src/app/model/servicoResponse';
import { ServicoService } from 'src/app/service/servico.service';

@Component({
  selector: 'app-servicos-list',
  templateUrl: './servicos-list.component.html',
  styleUrls: ['./servicos-list.component.css']
})
export class ServicosListComponent implements OnInit {

  findDescription: string = "";
  servicos: Servico[] = [];

  constructor(
    private ServicoService: ServicoService,
    private route: Router) { }

  ngOnInit(): void {
    this.findAllByDescription();
  }

  findAllByDescription(): void {
    this.ServicoService.findAll(this.findDescription).subscribe(
      {next: (response: ServicoResponse) => {
        this.servicos = response.content;
      }}
    );
  }

  newServico(): void {
    this.route.navigate(['servicos/form']);
  }
}
