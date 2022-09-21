import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { UserModel } from "./model/user-model";

@Injectable({
    providedIn: 'root'
})
export class UsersService {
    url = environment.api_url + '/users';
    constructor(private http: HttpClient){}

    getUsers(): Observable<UserModel[]>{
        return this.http.get<UserModel[]>(this.url);
    }
}