import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Item} from '../models/item/item.model';
import {CreateItemDTO} from '../models/item/create-item.dto';
import {UpdateItemDTO} from '../models/item/update-item.dto';

@Injectable({
  providedIn: 'root',
})
export class ItemService {
  private readonly baseurl: string = 'http://localhost:8080/item';
  private readonly http = inject(HttpClient);

  getItems(): Observable<Item[]> {
    return this.http.get<Item[]>(`${this.baseurl}`);
  }

  createItem(item: CreateItemDTO): Observable<Item> {
    return this.http.post<Item>(`${this.baseurl}`, item);
  }

  updateItem(itemId: string, item: UpdateItemDTO): Observable<Item> {
    return this.http.patch<Item>(`${this.baseurl}/${itemId}`, item);
  }

  deleteItem(itemId: string): Observable<void> {
    return this.http.delete<void>(`${this.baseurl}/${itemId}`);
  }

}
