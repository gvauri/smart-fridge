import {IconType} from './item-icon.type';

export interface CreateItemDTO {
  icon: IconType;
  name: string;
  description: string;
  buyingDate: Date;
  expirationDate: Date;
  amount: number;
}
