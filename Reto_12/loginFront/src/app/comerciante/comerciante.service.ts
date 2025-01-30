import { Injectable } from '@angular/core';
import { CONSTANTS } from '../Util/Constantes';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { Router } from '@angular/router';
import { AuthService } from '../usuarioo/auth.service';
import Swal from 'sweetalert2';
@Injectable({
  providedIn: 'root'
})
export class ComercianteService {

  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  private urlEndPoint: string = CONSTANTS.ENDPOINT_COMERCIANTE;

  constructor(private http: HttpClient, private router: Router, private authService: AuthService) { }
 
  private agregarAuthorizationHeader(): HttpHeaders {
    let token = this.authService.token;
    let headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  
    if (token) {
      console.log(".....token autenticado:", token);
      headers = headers.set('Authorization', `Bearer ${token}`);
    }
  
    return headers;
  }
  

  private isNoAutorizado(e: any): boolean {
    if (e.status == 401) {
      if (this.authService.isAuthenticated()) {
        this.authService.logout();
      }
      this.router.navigate(['/login']);
      return true;
    }

    if (e.status == 403) {
      Swal.fire('Acceso denegado', `Hola ${this.authService.usuario.username} no tienes acceso a este recurso!`, 'warning');
      this.router.navigate(['/comerciantes']);
      return true;
    }
    return false;
  }


  // Método para obtener la lista paginada de comerciante
  getListComerciantePagec(page: number, size: number): Observable<any> {


    console.log('Headers enviados:', this.agregarAuthorizationHeader());
    return this.http.get<any>(`${this.urlEndPoint}/getListComerciantePage/?page=${page}&size=${size}`, { headers: this.agregarAuthorizationHeader() }).pipe(
      map(response => {
        return {
          comercianteList: response.comercianteList,  // Accede a la lista de propietarios
          currentPage: response.currentPage,                   // Página actual
          totalItems: response.totalItems,                     // Total de elementos
          totalPages: response.totalPages                      // Total de páginas
        };
      }),
      catchError(e => {
        if (this.isNoAutorizado(e)) {
          return throwError(e);
        }
        console.log(e.error.mensaje);
        Swal.fire('Error al consultar', e.error.mensaje, 'error');
        return throwError(e);
      })
    );
  }




}
