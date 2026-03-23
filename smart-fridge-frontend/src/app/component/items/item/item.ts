import {Component, input} from '@angular/core';
import {Item} from '../../../models/item.model';

@Component({
  selector: 'app-item',
  imports: [],
  templateUrl: './item.html',
  styleUrl: './item.scss',
})
export class ItemComponent {
  item =input.required<Item>();
}
