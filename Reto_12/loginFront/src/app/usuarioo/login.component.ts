import { Component, OnInit } from '@angular/core';
import { Usuario } from './Usuario';
import Swal from 'sweetalert2';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']

})
export class LoginComponent implements OnInit {

  constructor(private authService: AuthService, private router: Router ) {
    this.usuario = new Usuario();
  }

  titulo: string = 'Digite tu documento de identidad del propietario o<br> representante legal y la contraseña';
  usuario: Usuario  =new Usuario();

  ngOnInit(): void {
   this.usuario;
   if (this.authService.isAuthenticated()) {
    Swal.fire('Login', `Hola ${this.authService.usuario.username} ya estás autenticado!`, 'info');
    this.router.navigate(['/propietario']);
  }

  }


  login(): void{
    console.log(this.usuario);
    if (this.usuario.email === '' || this.usuario.password === '') {
      Swal.fire('Error Login', 'email o password vacías!', 'error');
      return;
    }

     this.authService.login(this.usuario).subscribe(response =>{
      console.log("response-.."+response)
      //console.log(response.access_token.split(".")[1])
      this.authService.guardarUsuario(response.access_token);
      this.authService.guardarToken(response.access_token);
      let usuario = this.authService.usuario;
      this.router.navigate(['/comerciante'])
       Swal.fire('Login', `Hola ${usuario.username} ya estás autenticado!`, 'success');

     }, err => {
      if (err.status == 400) {
        Swal.fire('Error Login', 'email o clave incorrectas!', 'error');
      }
    }


     )
  }
}
