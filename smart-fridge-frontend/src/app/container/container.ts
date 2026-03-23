import { Component } from '@angular/core';
import {MatFabButton} from '@angular/material/button';
import {ItemsComponent} from '../component/items/items.component';

@Component({
  selector: 'app-container',
  imports: [
    MatFabButton,
    ItemsComponent
  ],
  templateUrl: './container.html',
  styleUrl: './container.scss',
})
export class Container {

}
