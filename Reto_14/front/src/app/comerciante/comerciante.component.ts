import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import Swal from 'sweetalert2';
import { ActivatedRoute } from '@angular/router';
import { comercianteDto } from './comercianteDto';
import { MatPaginator } from '@angular/material/paginator';
import { ComercianteService } from './comerciante.service';
import { MatTableDataSource } from '@angular/material/table';
import { Comerciante } from './Comerciante';
import { HttpClient } from '@angular/common/http';
import { CONSTANTS } from '../Util/Constantes';
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
    private  http: HttpClient, activatedRouter: ActivatedRoute, private comercinanteService: ComercianteService
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

  delete(comerciante: comercianteDto): void {
    Swal.fire({
      title: 'Está seguro',
      text: `Seguro que desea eliminar el registro ${comerciante.razonSocial}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Sí, eliminar!',
      cancelButtonText: 'No, cancelar!'
    }).then(result => {
      if (result.isConfirmed) {
        this.comercinanteService.delete(comerciante.comerciante_id).subscribe(() => {
        //  this.propietariosList = this.propietariosList.filter(p => p.propietario_id !== propietario.propietario_id);
          Swal.fire('Comerciante borrado!', 'Comerciante eliminado con éxito.', 'success');
        });
      } else if (result.dismiss === Swal.DismissReason.cancel) {
        Swal.fire('Cancelado', 'La eliminación ha sido cancelada.', 'error');
      }
    });
  
  }


    // Método para la descarga
    exportarCSV() {
      this.http.get(CONSTANTS.EXPORTAR_CSV, { responseType: 'blob' }).subscribe(
        (response) => {
          // Manejo de la respuesta, en este caso descargando el archivo
          const blob = new Blob([response], { type: 'text/csv' });
          const link = document.createElement('a');
          link.href = window.URL.createObjectURL(blob);
          link.download = 'comerciantes_activos.csv';
          link.click();
        },
        (error) => {
          console.error('Error al exportar el CSV:', error);
        }
      );
    }
}

