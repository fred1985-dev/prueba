import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import { ActivatedRoute } from '@angular/router';
import { comercianteDto } from './comercianteDto';
@Component({
  selector: 'paginator-nav',
  templateUrl: './comerciante.component.html',
})
export class ComercianteComponent  implements OnInit  {

  comercianteList: comercianteDto[] = [];
  paginador: any;

  constructor(
    private activatedRouter: ActivatedRoute,
  ) {
  }



  ngOnInit(): void {

    this.activatedRouter.paramMap.subscribe(params => {
      let page: number = 0;
      if (params.get('page')) {
        page = Number(params.get('page'));
      }
    });

   
  }
}


