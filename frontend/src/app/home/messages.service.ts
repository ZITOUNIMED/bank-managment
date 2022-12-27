import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "../../environments/environment";

@Injectable({
    providedIn: 'root'
})
export class MessagesService {
    url = environment.base_url + environment.customer_path;
    constructor(private http: HttpClient){}

    getMessages(): Observable<any> {
        return this.http.get(this.url+'/messages');
    }
}