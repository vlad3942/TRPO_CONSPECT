import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {Box} from "./box";


@Injectable({
  providedIn: 'root'
})
export class PyramidService {

	constructor(private http: HttpClient) {}

	getMaxPyramid(): Observable<Box[]> {
		return this.http.get<Box[]>('http://localhost:8080/max-pyramid');
	}
}
