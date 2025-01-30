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
    this.router.navigate(['/comerciantes']);
  }

  }


  login(): void{
    console.log("datos usuario"+this.usuario);
    console.log(this.usuario.email);
    console.log(this.usuario.password);
    if (this.usuario.email === '' || this.usuario.password === '') {
      Swal.fire('Error Login', 'email o password vacías!', 'error');
      return;
    }

    this.authService.login(this.usuario).subscribe(response => {
      console.log("Respuesta completa:", response); // Muestra toda la respuesta para ver la estructura
      console.log("Token:", response.access_token);
      console.log("Información:", response.informacion);
    
      // Asegúrate de que la propiedad `username` esté en la respuesta
      let username = response.informacion?.principal?.username;
      console.log("Username:", username); // Verifica si username tiene el valor correcto
    
      if (username) {
        // Guardar el token y redirigir solo si el username está disponible
        this.authService.guardarToken(response.access_token);
        this.router.navigate(['/comerciante']);
        Swal.fire('Login', `Hola ${username}, ya estás autenticado!`, 'success');
      } else {
        // En caso de que el username no esté disponible
        Swal.fire('Error Login', 'No se encontró el usuario!', 'error');
      }
    }, err => {
      if (err.status == 400) {
        Swal.fire('Error Login', 'Email o clave incorrectas!', 'error');
      }
    });
  }    
}
