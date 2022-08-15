import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "../../environments/environment";
import { LinkModel } from "./models/link.model";

@Injectable({
    providedIn: 'root'
})
export class MenuService {
    url = environment.api_url + '/menu';
    constructor(private http: HttpClient){}

    getMenu(): Observable<LinkModel[]> {
        return this.http.get<LinkModel[]>(this.url);
    }
}