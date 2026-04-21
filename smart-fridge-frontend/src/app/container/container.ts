import {Component, inject} from '@angular/core';
import {MatFabButton} from '@angular/material/button';
import {ItemsComponent} from '../component/items/items.component';
import {MatDialog} from '@angular/material/dialog';
import {AddItem} from '../component/add-item/add-item';

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
  private readonly dialog = inject(MatDialog);

  protected openAddDialog() {
    this.dialog.open(AddItem,{
      width: 'auto',
    });
  }
}
