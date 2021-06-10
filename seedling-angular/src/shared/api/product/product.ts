import { ObjectWithLinks } from 'extendz/core';
import { Brand } from '../brand/brand';
import { Price, StockKeepingUnit } from '../stock-keeping-unit/stock-keeping-unit';

export class Product extends ObjectWithLinks {
  name?: string;
  image?: string;
  url?: string;
  stockKeepingUnits?: StockKeepingUnit[];
  price?: Price;
  brand?: Brand;
}
