import { Component } from '@angular/core';
import {Item} from './item/item';

@Component({
  selector: 'app-items',
  imports: [
    Item
  ],
  templateUrl: './items.html',
  styleUrl: './items.scss',
})
export class Items {

}
