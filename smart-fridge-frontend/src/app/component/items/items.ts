import {Component, inject, Signal} from '@angular/core';
import {Item} from './item/item';
import {ItemLoaderService} from '../../services/item-loader.service';
import {toSignal} from '@angular/core/rxjs-interop';

@Component({
  selector: 'app-items',
  imports: [
    Item
  ],
  templateUrl: './items.html',
  styleUrl: './items.scss',
})
export class Items {
  private readonly itemLoader = inject(ItemLoaderService);
  protected readonly items: Signal<Item[]> = toSignal(
    this.itemLoader.load(),
    {initialValue:[]}
  );
}
