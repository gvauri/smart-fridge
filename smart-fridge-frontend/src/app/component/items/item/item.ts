import {Component, inject, input} from '@angular/core';
import {Item} from '../../../shared/models/item/item.model';
import {DatePipe, NgOptimizedImage} from '@angular/common';
import {MatIcon} from '@angular/material/icon';
import {MatIconButton} from '@angular/material/button';
import {ItemService} from '../../../shared/services/item.service';

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
  private readonly itemService = inject(ItemService);

  readonly item = input.required<Item>();

  protected delete() {
    this.itemService.deleteItem(this.item().id)
  }
}
