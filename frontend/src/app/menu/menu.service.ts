import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";

@Injectable({
    providedIn: 'root'
})
export class MenuService {
    url = environment.api_url + '/menu';
    constructor(private http: HttpClient){}

    getMenu(): Observable<any> {
        return this.http.get(this.url);
    }
}