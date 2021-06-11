import {
  ExtDatatableConfig,
  ExtEntityConfig,
  ExtRootConfig,
  EXT_DATA_TABLE_CONFIG,
  EXT_DATA_TABLE_SERVICE,
  EXT_ENTITY_SERVICE,
} from 'extendz/core';
import { DataTableHateosService } from './data-table/data-table-hateos.service';
import { EntityService } from './entity/entity.service';

export const EXT_ROOT_CONFIG: ExtRootConfig = {
  svgIconSet: 'assets/svg/api-icons.svg',
  modelsJson: 'assets/json/models.json',
};

export const DATA_TABLE_CONFIG: ExtDatatableConfig = {
  ...EXT_ROOT_CONFIG,
  placeholderImage: 'assets/img/placeholder.png',
  dataTableProjecion: 'dataTable',
  pageSizeOptions: [10, 20, 100, 500],
  showFirstLastButtons: true,
  defaultPageSize: 20,
  toolbar: {
    search: {
      width: 20,
    }
  },
};

export const ENTITY_CONFIG: ExtEntityConfig = {
  ...EXT_ROOT_CONFIG,
  placeholderImage: 'assets/img/placeholder.png',
  unitOfMeasurement: {
    model: 'unitOfMeasure',
  },
  phone: {
    model: 'country',
    defaultPhoneCode: '+94',
  },
  currency: {
    model: 'currency',
    defaultCurrency: 'LKR',
  },
  idFeild: '_links.self.href',
};

export const DATA_TABLE_SERVICE = {
  provide: EXT_DATA_TABLE_SERVICE,
  useClass: DataTableHateosService,
};

export const ENTITY_SERVICE = {
  provide: EXT_ENTITY_SERVICE,
  useClass: EntityService,
};

export const DATA_TABEL_CONFIG = { provide: EXT_DATA_TABLE_CONFIG, useValue: DATA_TABLE_CONFIG };
