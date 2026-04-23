import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, Observable, switchMap, tap} from 'rxjs';
import {Item} from '../models/item/item.model';
import {CreateItemDTO} from '../models/item/create-item.dto';
import {UpdateItemDTO} from '../models/item/update-item.dto';
import {format} from 'date-fns';

@Injectable({
  providedIn: 'root',
})
export class ItemService {
  private readonly baseurl: string = 'http://localhost:8080/item';
  private readonly http = inject(HttpClient);
  private readonly refresh$ = new BehaviorSubject<void>(undefined);

  readonly items$: Observable<Item[]> = this.refresh$.pipe(
    switchMap(() => this.http.get<Item[]>(this.baseurl))
  );

  refresh(): void {
    this.refresh$.next();
  }

  createItem(item: CreateItemDTO): Observable<Item> {
    const body = {
      ...item,
      expirationDate: format(item.expirationDate, 'yyyy-MM-dd'),
      buyingDate: format(item.buyingDate, 'yyyy-MM-dd'),
    }
    return this.http.post<Item>(`${this.baseurl}`, body)
      .pipe(
        tap(()=> this.refresh())
      );
  }

  updateItem(itemId: string, item: UpdateItemDTO): Observable<Item> {
    return this.http.patch<Item>(`${this.baseurl}/${itemId}`, item)
      .pipe(
        tap(()=> this.refresh())
      );
  }

  deleteItem(itemId: string): Observable<void> {
    return this.http.delete<void>(`${this.baseurl}/${itemId}`)
      .pipe(
        tap(()=> this.refresh())
      );
  }

}
