import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { SignInRequest } from "./models/sign-in-request.model";
import { SignInResponse } from "./models/sign-in-response.model";

@Injectable({
    providedIn: 'root'
})
export class SignInService {
    url = environment.api_url + '/auth/sign-in';
    constructor(private http: HttpClient){}

    signIn(signInRequest: SignInRequest): Observable<SignInResponse> {
        return this.http.post<SignInResponse>(this.url, signInRequest);
    }
}