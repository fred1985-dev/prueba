import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import Swal from 'sweetalert2';
import { ActivatedRoute } from '@angular/router';
import { comercianteDto } from './comercianteDto';
import { MatPaginator } from '@angular/material/paginator';
import { ComercianteService } from './comerciante.service';
import { MatTableDataSource } from '@angular/material/table';


@Component({
  selector: 'comerciante',
  templateUrl: './comerciante.component.html',
})

export class ComercianteComponent implements OnInit {
  propietarios: comercianteDto[] = [];


  dataSourcePropietarios = new MatTableDataSource<comercianteDto>([]);

  totalItems = 0; // Total de elementos para la paginación
  pageSize = 10; // Tamaño de la página
  currentPage = 0; // Página actual
  paginador: any;
  comercianteList: comercianteDto[] = [];


  @ViewChild(MatPaginator) paginator!: MatPaginator;
  constructor(
    private activatedRouter: ActivatedRoute, private comercinanteService: ComercianteService
  ) {}

  ngOnInit(): void {
    this.loadComerciantes();
  }

  // Método para cargar los datos paginados
  loadComerciantes(page: number = 0, size: number = this.pageSize): void {
    this.comercinanteService.getListComerciantePagec(page, size).subscribe(
      (response: any) => {
        this.comercianteList = response.comercianteList;
        this.dataSourcePropietarios.data = this.comercianteList;
        this.totalItems = response.totalItems;
        this.currentPage = response.currentPage;
        this.paginator.length = this.totalItems; 
      },
      (error: any) => {
        console.error('Error al obtener los datos', error);
      }
    );
  }

  // Método que se llama cuando el usuario cambia de página
  onPageChange(event: any): void {
    this.currentPage = event.pageIndex;
    this.pageSize = event.pageSize;
    this.loadComerciantes(this.currentPage, this.pageSize);
  }
}
