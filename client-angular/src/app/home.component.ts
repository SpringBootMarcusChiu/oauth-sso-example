import { Component } from '@angular/core';
import { AppService } from './app.service'

@Component({
  selector: 'home-header',
  providers: [AppService],
  template: `
    <div class="container">
      <button *ngIf="!isLoggedIn" class="btn btn-primary" (click)="login()" type="submit">Login</button>
      <div *ngIf="isLoggedIn" class="content">
        <span>Welcome: </span>
        <a class="btn btn-default pull-right"(click)="logout()" href="#">Logout</a>
        <br/>
        <foo-details></foo-details>
      </div>
    </div>`
})
export class HomeComponent {
    public isLoggedIn = false;

    constructor(private appService: AppService) {}

    ngOnInit() {
        this.isLoggedIn = this.appService.checkCredentials();
        let i = window.location.href.indexOf('code');
        if (!this.isLoggedIn && i != -1) {
            this.appService.retrieveToken(window.location.href.substring(i + 5));
        }
    }

    login() {
        window.location.href = this.appService.authUri +
            '?response_type=code' +
            '&scope=write%20read' +
            '&client_id=' + this.appService.clientId +
            '&redirect_uri=' + this.appService.redirectUri;
    }

    logout() {
        this.appService.logout();
    }
}
