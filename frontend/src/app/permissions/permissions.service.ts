import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "../../environments/environment";
import { RoleModel } from "./models/role.model";

@Injectable({
    providedIn: 'root'
})
export class PermissionsService {
    url = environment.api_url + '/permissions/user-role';
    constructor(private http: HttpClient){}

    getUserRole(): Observable<RoleModel> {
        return this.http.get<RoleModel>(this.url);
    }
}