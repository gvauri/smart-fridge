import {Component, input} from '@angular/core';
import {Item} from '../../../shared/models/item.model';
import {DatePipe, NgOptimizedImage} from '@angular/common';

@Component({
  selector: 'app-item',
  imports: [
    NgOptimizedImage,
    DatePipe
  ],
  templateUrl: './item.html',
  styleUrl: './item.scss',
})
export class ItemComponent {
  readonly item = input.required<Item>();

}
