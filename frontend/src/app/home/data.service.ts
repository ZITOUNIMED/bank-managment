import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "../../environments/environment";

@Injectable({
    providedIn: 'root'
})
export class DataService {
    url = environment.base_url + environment.central_admin_path + '/api';
    constructor(private http: HttpClient){}

    getData(): Observable<any> {
        return this.http.get(this.url+'/data');
    }
}