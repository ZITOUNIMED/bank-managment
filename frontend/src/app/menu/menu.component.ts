import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MenuService } from './menu.service';
import { LinkModel } from './models/link.model';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {

  links: LinkModel[] = [];

  constructor(private menuService: MenuService, private router: Router) { }

  ngOnInit(): void {
    this.menuService.getMenu().subscribe(links => (this.links = links));
  }

  openLink(link: LinkModel): void {
    this.router.navigate([link.path]);
  }
}
