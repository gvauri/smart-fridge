import {Component, inject, input} from '@angular/core';
import {Item} from '../../../shared/models/item/item.model';
import {DatePipe, NgOptimizedImage} from '@angular/common';
import {MatIcon} from '@angular/material/icon';
import {MatIconButton} from '@angular/material/button';
import {MatDialog} from '@angular/material/dialog';
import {ItemService} from '../../../shared/services/item.service';
import {UpdateItem} from '../../update-item/update-item';

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
  private readonly dialog = inject(MatDialog);

  readonly item = input.required<Item>();

  protected openUpdateDialog(): void {
    this.dialog.open(UpdateItem, {data: this.item()});
  }

  protected delete(): void {
    this.itemService.deleteItem(this.item().id).subscribe();
  }
}
