import {Component, inject} from '@angular/core';
import {ItemComponent} from './item/item';
import {ItemLoaderService} from '../../services/item-loader.service';
import {toSignal} from '@angular/core/rxjs-interop';

@Component({
  selector: 'app-items',
  imports: [
    ItemComponent
  ],
  templateUrl: './items.component.html',
  styleUrl: './items.component.scss',
})
export class ItemsComponent {
  private readonly itemLoader = inject(ItemLoaderService);
  protected readonly items = toSignal(
    this.itemLoader.load()
  );
}
