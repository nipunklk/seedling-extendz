import { HateosPagedResponse } from 'extendz/core';
import { Category } from '../api/category/category';

export class CategoryResolverData {
  subCategoryPage?: HateosPagedResponse;
  category?: Category;
}
