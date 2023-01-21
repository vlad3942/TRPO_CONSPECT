import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Record} from "./record";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class RecordService {
  constructor(private http: HttpClient) {
  }

  public get(): Observable<Record[]> {
    return this.http.get<Record[]>('http://localhost:8080/get-records')
  }
}
