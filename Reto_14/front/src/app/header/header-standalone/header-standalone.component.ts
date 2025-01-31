import {Component} from '@angular/core';
import {MatChipsModule} from '@angular/material/chips';
@Component({
  selector: 'app-header-standalone',
  standalone: true,
  imports: [ MatChipsModule],
  templateUrl: './header-standalone.component.html',
  styleUrls: ['./header-standalone.component.css'],

})
export class HeaderStandaloneComponent  {

  constructor() { }



}
