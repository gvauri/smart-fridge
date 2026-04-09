import {IconType} from './item-icon.type';

export interface UpdateItemDTO {
  icon: IconType;
  name: string;
  description: string;
  expirationDate: Date;
  amount: number;
}
