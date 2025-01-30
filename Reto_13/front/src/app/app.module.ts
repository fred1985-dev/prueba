import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RouterModule, Routes} from '@angular/router';
//importar http
import { AdministracionComponent } from './administracion/administracion/administracion.component';
import localesES from '@angular/common/locales/es';
import {registerLocaleData} from '@angular/common';
registerLocaleData(localesES, 'es')

//importamos router
import { RolComponent } from './administracion/rol/rol.component';
import { UsersComponent } from './administracion/users/users.component';
import { LoginComponent } from './usuarioo/login.component';
import { AuthGuard } from './usuarioo/guards/auth.guard';
import { RoleGuard } from './usuarioo/guards/role.guard';
//componentes
import { FooterComponent } from './footer/footer.component';
import { HeaderComponent } from './header/header.component';
import { ComercianteComponent } from './comerciante/comerciante.component';

//Sdrvicios
import { ComercianteService } from './comerciante/comerciante.service';


//importar http
import {HttpClientModule} from '@angular/common/http';


// Material Form Controls
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { MatSliderModule } from '@angular/material/slider';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
// Material Navigation
import { MatMenuModule } from '@angular/material/menu';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
// Material Layout
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatListModule } from '@angular/material/list';
import { MatStepperModule } from '@angular/material/stepper';
import { MatTabsModule } from '@angular/material/tabs';
import { MatTreeModule } from '@angular/material/tree';
// Material Buttons & Indicators

import { MatButtonModule } from '@angular/material/button';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatBadgeModule } from '@angular/material/badge';
import { MatChipsModule } from '@angular/material/chips';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatRippleModule } from '@angular/material/core';
// Material Popups & Modals
import { MatBottomSheetModule } from '@angular/material/bottom-sheet';
import { MatDialogModule } from '@angular/material/dialog';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatTooltipModule } from '@angular/material/tooltip';
// Material Data tables
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
//tree
import {A11yModule} from '@angular/cdk/a11y';
import {DragDropModule} from '@angular/cdk/drag-drop';
import {PortalModule} from '@angular/cdk/portal';
import {ScrollingModule} from '@angular/cdk/scrolling';
import {CdkStepperModule} from '@angular/cdk/stepper';
import {CdkTableModule} from '@angular/cdk/table';
import {CdkTreeModule} from '@angular/cdk/tree';
import { PaginatorComponent } from './paginator/paginator.component';

//formulario
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

const routes: Routes =[
  {path:'comerciante', component:ComercianteComponent},
//  {path:'comerciante', component:ComercianteComponent, canActivate: [AuthGuard, RoleGuard],  data: { role: 'ADMIN' } },

  {path:'', redirectTo:'/login', pathMatch:'full'},
  { path: 'administracion', component: AdministracionComponent, canActivate: [AuthGuard, RoleGuard], data: { role: 'ADMIN' } },
  { path: 'roles', component: RolComponent, canActivate: [AuthGuard, RoleGuard], data: { role: 'ADMIN' } },
  {path:'login', component: LoginComponent},
  { path: 'users', component: UsersComponent, canActivate: [AuthGuard, RoleGuard], data: { role: 'ADMIN' } }
]


@NgModule({
  declarations: [
    AppComponent,
    PaginatorComponent,
    LoginComponent,
    HeaderComponent,
    FooterComponent,
    RolComponent,
    UsersComponent,
    AdministracionComponent,
    ComercianteComponent

  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MatTreeModule,
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot(routes),
    MatAutocompleteModule,
    MatCheckboxModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatInputModule,
    MatRadioModule,
    MatSelectModule,
    MatSliderModule,
    MatSlideToggleModule,
    MatMenuModule,
    MatSidenavModule,
    MatToolbarModule,
    MatCardModule,
    MatDividerModule,
    MatExpansionModule,
    MatGridListModule,
    MatListModule,
    MatStepperModule,
    MatTabsModule,
    MatTreeModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatBadgeModule,
    MatChipsModule,
    MatIconModule,
    MatProgressSpinnerModule,
    MatProgressBarModule,
    MatRippleModule,
    MatBottomSheetModule,
    MatDialogModule,
    MatSnackBarModule,
    MatTooltipModule,
    MatPaginatorModule,
    MatSortModule,
    MatTableModule,
    
  
  ],
  exports: [
    A11yModule,
    CdkStepperModule,
    CdkTableModule,
    CdkTreeModule,
    DragDropModule,
    MatAutocompleteModule,
    MatBadgeModule,
    MatBottomSheetModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatCardModule,
    MatCheckboxModule,
    MatChipsModule,
    MatStepperModule,
    MatDatepickerModule,
    MatDialogModule,
    MatDividerModule,
    MatExpansionModule,
    MatGridListModule,
    MatIconModule,
    MatInputModule,
    MatListModule,
    MatMenuModule,
    MatPaginatorModule,
    MatProgressBarModule,
    MatProgressSpinnerModule,
    MatRadioModule,
    MatRippleModule,
    MatSelectModule,
    MatSidenavModule,
    MatSliderModule,
    MatSlideToggleModule,
    MatSnackBarModule,
    MatSortModule,
    MatTableModule,
    MatTabsModule,
    MatToolbarModule,
    MatTooltipModule,
    MatTreeModule,
    PortalModule,
    ScrollingModule
  ],
  providers: [
    ComercianteService

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
