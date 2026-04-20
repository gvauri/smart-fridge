import {IconType} from './item-icon.type';

export type Item = {
  id: string;
  icon: IconType;
  name: string;
  description: string;
  buyingDate: Date;
  expirationDate: Date;
  amount: number;
}
