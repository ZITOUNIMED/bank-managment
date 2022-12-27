import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "../../environments/environment";
import { TraceDataModel } from "./models/trace-data.model";

@Injectable({
    providedIn: 'root'
})
export class LogsService {
    url = environment.base_url + '/logs';
    constructor(private http: HttpClient){}

    getTracesData(): Observable<TraceDataModel[]>{
        return this.http.get<TraceDataModel[]>(this.url);
    }

    export(): Observable<any>{
        return this.http.get(this.url + '/export', {
            responseType: 'blob'
          });
    }
}