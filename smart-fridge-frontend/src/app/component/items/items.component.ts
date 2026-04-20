import {Component, inject} from '@angular/core';
import {ItemComponent} from './item/item';
import {toSignal} from '@angular/core/rxjs-interop';
import {ItemService} from '../../shared/services/item.service';

@Component({
  selector: 'app-items',
  imports: [
    ItemComponent
  ],
  templateUrl: './items.component.html',
  styleUrl: './items.component.scss',
})
export class ItemsComponent {
  private readonly itemService = inject(ItemService);
  protected readonly items = toSignal(
    this.itemService.getItems()
  );
}
