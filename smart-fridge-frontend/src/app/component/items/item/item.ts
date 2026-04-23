import {Component, input} from '@angular/core';
import {Item} from '../../../shared/models/item/item.model';
import {DatePipe, NgOptimizedImage} from '@angular/common';
import {MatIcon} from '@angular/material/icon';
import {MatIconButton} from '@angular/material/button';

@Component({
  selector: 'app-item',
  imports: [
    NgOptimizedImage,
    DatePipe,
    MatIcon,
    MatIconButton
  ],
  templateUrl: './item.html',
  styleUrl: './item.scss',
})
export class ItemComponent {
  readonly item = input.required<Item>();

}
