import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "../../environments/environment";
import { FonctionalityModel } from "./models/fonctionality.model";
import { RoleModel } from "../shared/models/role.model";

@Injectable({
    providedIn: 'root'
})
export class PermissionsService {
    url = environment.base_url + '/permissions';
    constructor(private http: HttpClient){}

    getRoles(): Observable<RoleModel[]> {
        return this.http.get<RoleModel[]>(this.url + '/roles');
    }

    getFonctionalities(): Observable<FonctionalityModel[]> {
        return this.http.get<FonctionalityModel[]>(this.url + '/fonctionalities');
    }

    addRole(role: RoleModel): Observable<any>{
        return this.http.post(this.url + '/add-role', role);
    }
    saveRole(role: RoleModel): Observable<any>{
        return this.http.post(this.url + '/save-role', role);
    }
}