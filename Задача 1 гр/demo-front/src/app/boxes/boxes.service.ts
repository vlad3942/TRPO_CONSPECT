import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {CountDto} from "./count.dto";

@Injectable({
  providedIn: 'root'
})
export class BoxesService {
  constructor(private http: HttpClient) {
  }

  public get(): Observable<CountDto> {
    return this.http.get<CountDto>('http://localhost:8080/count')
  }
}
