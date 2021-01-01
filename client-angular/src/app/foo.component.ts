import { Component } from '@angular/core';
import { AppService } from './app.service'

@Component({
  selector: 'foo-details',
  providers: [AppService],
  template: `
    <div class="container">
      <h1 class="col-sm-12">Foo</h1>
      <div class="col-sm-12">
        <span>{{foo}}</span>
      </div>
      <div class="col-sm-12">
        <button class="btn btn-primary" (click)="getFoo()" type="submit">Get New Foo</button>
      </div>
    </div>`
})
export class FooComponent {
    public foo = 'STUB FOO';

    constructor(private appService:AppService) {}

    getFoo() {
      this.appService.getResource('http://localhost:8081/sso-resource-server/api/foos/0')
        .subscribe(data => this.foo = data,
                   error => {
                    this.foo = 'Error';
                    console.log(error);
                   });
    }
}
