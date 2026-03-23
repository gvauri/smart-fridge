import {Component, input} from '@angular/core';
import {Item} from '../../../models/item.model';
import {NgOptimizedImage} from '@angular/common';
import {format} from 'date-fns';

@Component({
  selector: 'app-item',
  imports: [
    NgOptimizedImage
  ],
  templateUrl: './item.html',
  styleUrl: './item.scss',
})
export class ItemComponent {
  readonly item = input.required<Item>();

  protected formatDate(date: Date) {
    return format(date, 'dd.MM.yyyy');
  }
}
