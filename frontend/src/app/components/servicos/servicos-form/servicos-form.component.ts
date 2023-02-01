import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Servico } from 'src/app/model/servico';
import { ServicoService } from 'src/app/service/servico.service';

@Component({
  selector: 'app-servicos-form',
  templateUrl: './servicos-form.component.html',
  styleUrls: ['./servicos-form.component.css']
})
export class ServicosFormComponent implements OnInit {

  servico: Servico;
  servicoId: number;

  constructor(
    private servicoServico: ServicoService,
    private route: Router,
    private activatedRouter: ActivatedRoute) { }

  ngOnInit(): void {
    this.servico = new Servico();
    this.returnServiceIdRouter();
  }

  submit(): void {
    if(!this.servico.id) {
      this.insert();
    }
    else {
      this.update();
    }
    this.route.navigate(['servicos']);
  }

  private insert(): void {
    this.servicoServico.insert(this.servico).subscribe(
      {next: (response: Servico) => {
        this.servico = response;
      }}
    );
  }

  private update(): void {
    this.servicoServico.update(this.servico.id, this.servico).subscribe({
      next: (response: Servico) => {
        this.servico = response;
      }
    });
  }

  private returnServiceIdRouter(): void {
    let params: Observable<Params> = this.activatedRouter.params;
    params.subscribe(urlParams => {
      this.servicoId = urlParams["id"];
    });
    
    if(this.servicoId) {
      this.servicoServico.findById(this.servicoId).subscribe(
        {next: (response: Servico) => {
          this.servico = response;
        }}
      );
    }
  }

}
