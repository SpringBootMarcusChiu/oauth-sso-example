import {Injectable} from '@angular/core';
import { Cookie } from 'ng2-cookies';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { JwtHelperService } from "@auth0/angular-jwt";

@Injectable()
export class AppService {
   public jwtHelper = new JwtHelperService();
   public authUri = 'http://localhost:8083/auth/realms/baeldung/protocol/openid-connect/auth';
   public tokenUri = 'http://localhost:8083/auth/realms/baeldung/protocol/openid-connect/token';
   public clientId = 'client-angular';
   public clientSecret = 'client-angular-secret';
   public organization = "none";
   public redirectUri = 'http://localhost:8084/';

  constructor(private http: HttpClient) {}

  retrieveToken(code) {
    let params = new URLSearchParams();
    params.append('grant_type','authorization_code');
    params.append('client_id', this.clientId);
    params.append('client_secret', this.clientSecret);
    params.append('redirect_uri', this.redirectUri);
    params.append('code', code);

    let headers = new HttpHeaders({
        'Content-type': 'application/x-www-form-urlencoded; charset=utf-8',
        'Authorization': 'Basic ' + btoa(this.clientId + ":secret")});

    this.http.post(this.tokenUri, params.toString(), { headers: headers })
             .subscribe(data => this.saveToken(data), err => alert('Invalid Credentials'));
  }

  saveToken(token) {
    var expireDate = new Date().getTime() + (1000 * token.expires_in);
    Cookie.set("access_token", token.access_token, expireDate);
    console.log('Obtained Access token');
    this.getOrganization();
    window.location.href = 'http://localhost:8084';
  }

  getResource(resourceUrl): Observable<any> {
    var headers = new HttpHeaders({
        'Content-type': 'application/x-www-form-urlencoded; charset=utf-8',
        'Authorization': 'Bearer ' + Cookie.get('access_token')});
    return this.http.get(resourceUrl, { headers: headers, responseType: 'text' });
  }

  checkCredentials() {
    return Cookie.check('access_token');
  }

  logout() {
    Cookie.delete('access_token');
    window.location.reload();
  }

  getOrganization() {
    	var token = Cookie.get("access_token");
    	var payload = this.jwtHelper.decodeToken(token);
    	console.log(payload);
    	this.organization = payload.organization;
    	return this.organization;
  }
}
