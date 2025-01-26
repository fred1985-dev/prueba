import { Injectable } from '@angular/core';
import { Observable, BehaviorSubject } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Usuario } from './Usuario'
import { CONSTANTS } from '../Util/Constantes';

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
     // encriptanmos la contraseña de la aplicaicon
    const credenciales = btoa('angularapp' + ':' + '12345');
    const httpHeaders = new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded',
      'Authorization': 'Basic ' + credenciales
    });
    let params = new URLSearchParams();
    params.set('grant_type', 'password');
    params.set('email', usuario.email);
    params.set('password', usuario.password);
    console.log(params.toString());
    return this.http.post<any>(urlEndpoint, params.toString(), { headers: httpHeaders });
  }


   guardarUsuario(accessToken: string): void {
    let payload = this.obtenerDatosToken(accessToken);
    this._usuario = new Usuario();
    this._usuario.firstname= payload.nombre;
    this._usuario.lastname= payload.apellido;
    this._usuario.email = payload.email;
    this._usuario.username = payload.user_name;
    this._usuario.id_user =payload.id_user;
    console.log(" ser_name; "+ payload.user_name+ "  iduser "+ payload.id_user)
    //spring lo llama authorities nosotros  lo guardamos roles
    this._usuario.roles = payload.authorities;
    //sessionStorage es de java scrpt nos permite guardar datos session
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
    if (payload != null && payload.user_name && payload.user_name.length > 0) {
      return true;
    }
    return false;
  }

  hasRole(role: string): boolean {
    //permite validar si el elemento esta en la lista
    if (this.usuario.roles.includes(role)) {
      return true;
    }
    return false;
  }

  logout(): void {
    this._token = '';
    this._usuario = new Usuario;
    sessionStorage.clear();
    sessionStorage.removeItem('token');
    sessionStorage.removeItem('usuario');
  }


}
