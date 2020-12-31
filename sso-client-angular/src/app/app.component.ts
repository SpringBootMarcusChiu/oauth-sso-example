import {Component, OnInit} from '@angular/core';
import {AppService} from './app.service'

@Component({
  selector: 'app-root',
  template: `
    <nav class="navbar navbar-default">
      <div class="navbar-brand">
        <p>ORGANIZATION: {{organization}}</p>
      </div>
    </nav>
    <router-outlet></router-outlet>`
})
export class AppComponent implements OnInit {
  public organization = "";

  constructor(private appService: AppService) { }

  ngOnInit() {
    this.organization = this.appService.getOrganization();
  }
}
