import { Component } from '@angular/core';
import {MatFabButton} from '@angular/material/button';
import {Items} from '../component/items/items';

@Component({
  selector: 'app-container',
  imports: [
    MatFabButton,
    Items
  ],
  templateUrl: './container.html',
  styleUrl: './container.scss',
})
export class Container {

}
