import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppState } from 'src/shared/state/app/app.state';
import { AdminState } from '../../../shared/state/admin.state';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss'],
})
export class AdminComponent implements OnInit {
  public nav = [
    {
      name: 'Dashboard',
      url: '/admin/dashboard',
    },
    {
      name: 'Admin',
      children: [{ name: 'System Users', url: '/admin/api/systemUser' }],
    },
    {
      name: 'Application Data',
      children: [
        { name: 'Category', url: '/admin/api/category' },
        { name: 'Currency', url: '/admin/api/currency' },
        { name: 'Vehicle Fleet', url: '/admin/api/deliveryVehicle' },
        { name: 'Sales Charge Type', url: '/admin/api/salesChargeType' },
        { name: 'Teams', url: '/admin/api/team' },
        { name: 'Unit of Measure', url: '/admin/api/unitOfMeasure' },
        { name: 'Sales Representative', url: '/admin/api/salesRepresentative' },
      ],
    },
    {
      name: 'Products',
      url: '/admin/api/product',
    },
    {
      name: 'Purchasing',
      children: [
        { name: 'Suppliers', url: '/admin/api/supplier' },
        { name: 'Purchase Orders', url: '/admin/api/purchaseOrder' },
      ],
    },
    {
      name: 'Sales',
      children: [
        { name: 'Customers', url: '/admin/api/customer' },
        { name: 'Sales Order', url: '/admin/api/salesOrder' },
      ],
    },
    {
      name: 'Customer Payments',
      url: '/admin/api/customerPayment',
    },

    {
      name: 'Delivery Order',
      url: '/admin/api/deliveryOrder',
    },
    {
      name: 'Stock Management',
      url: '/admin/api/batch',
    },
    {
      name: 'Inventory Tranactions',
      url: '/admin/api/inventoryTransaction',
    },
  ];

  constructor(public state: AdminState, public appstate: AppState, private router: Router) {}

  ngOnInit(): void {}

  goTo(a: any) {
    if (a.url) this.router.navigate([a.url]);
  }
}
