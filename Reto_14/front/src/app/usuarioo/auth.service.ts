import { Injectable } from '@angular/core';
import { Observable, BehaviorSubject } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Usuario } from './Usuario'
import { CONSTANTS } from '../Util/Constantes';
import {jwtDecode} from 'jwt-decode';
import { tap } from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private _usuario?: Usuario;
  private _token?: string;

  constructor( private http: HttpClient) {

  }



  public get usuario(): Usuario {

    if (this._usuario != null) {
      return this._usuario;
    } else if (this._usuario == null && sessionStorage.getItem('usuario') != null) {
      //JSON.parse(sessionStorage.getItem('usuario')) as  Usuario;
      this._usuario= new Usuario();
     // console.log("------------------------------------"+JSON.parse(sessionStorage.getItem('usuario') | null) )


     const userjson = sessionStorage.getItem('usuario') ;
     return this._usuario = userjson !== null ? JSON.parse(userjson) :  Usuario ;


     //console.log("--usuario--------------------------------"+(sessionStorage.getItem('usuario')))
     //this._usuario =sessionStorage.getItem('usuario');
      //return this._usuario;
    }
    return new Usuario();
  }

public get token(): string {
  if (this._token != null) {
    return this._token;
  } else if (this._token == null && sessionStorage.getItem('token') != null) {
    this._token =''+ sessionStorage.getItem('token');
    return this._token;
  }
  return '';
}



login(usuario: Usuario): Observable<any> {
  const urlEndpoint = CONSTANTS.urlTocken;
  const credenciales = btoa('angularapp' + ':' + '12345');
  const httpHeaders = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Basic ' + credenciales
  });

  const body = {
      email: usuario.email,
      password: usuario.password,
  };

  console.log("-----------------------" + usuario.email);
  return this.http.post<any>(urlEndpoint, body, { headers: httpHeaders }).pipe(
      tap(response => {
          console.log("Token recibido:", response.access_token);
          this.guardarUsuario(response.access_token);  // Guardar los datos del usuario
          this.guardarToken(response.access_token);   // Guardar el token
      })
  );
}



guardarUsuario(accessToken: string): void {
  const decodedToken: any = jwtDecode(accessToken);
  console.log("Decoded Token:", decodedToken);  // Verifica que el token está decodificado correctamente
  this._usuario = new Usuario();
  this._usuario.firstname = decodedToken.nombre;
  this._usuario.lastname = decodedToken.apellido;
  this._usuario.email = decodedToken.email;
  this._usuario.username = decodedToken.firstname;
  this._usuario.id_user = decodedToken.id_user;
  this._usuario.roles = decodedToken.roles;

  console.log("Usuario Guardado:", this._usuario);  // Verifica que el usuario está siendo correctamente asignado

  sessionStorage.setItem('usuario', JSON.stringify(this._usuario));
}



  guardarToken(accessToken: string): void {
    this._token = accessToken;
    sessionStorage.setItem('token', accessToken);
  }

  obtenerDatosToken(accessToken: string): any {
    if (accessToken != null) {
      console.log("Datos del token...." +accessToken.toString);
      return JSON.parse(atob(accessToken.split(".")[1]));
    }
    return null;
  }



  isAuthenticated(): boolean {
    let payload = this.obtenerDatosToken(this.token);
    if (payload != null  ) {
      return true;
    }
    return false;
  }

  hasRole(role: string): boolean {
    //permite validar si el elemento esta en la lista
    if (this.usuario.roles.includes(role)) {
      return true;
    }
    //--
    return true;
  }

  logout(): void {
    this._token = '';
    this._usuario = new Usuario;
    sessionStorage.clear();
    sessionStorage.removeItem('token');
    sessionStorage.removeItem('usuario');
  }


}
