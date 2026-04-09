import {inject, Injectable} from '@angular/core';
import {Item} from '../models/item/item.model';
import {HttpClient} from '@angular/common/http';
import {map, Observable} from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ItemLoaderService {
  private readonly http = inject(HttpClient);
  private readonly url = 'http://localhost:9080/items'

  load(): Observable<Item[]> {
    return this.http
      .get<Item[]>(this.url)
      .pipe(
        map(items => items.map(
          item => ({
            ...item,
            buying_date: new Date(item.buying_date),
            expiration_date: new Date(item.expiration_date)
          }))
        )
      )
  }
}
