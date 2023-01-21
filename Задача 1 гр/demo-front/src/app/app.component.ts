import {Component, OnInit} from '@angular/core';
import {Record} from './record'
import {RecordService} from "./RecordService";
import {Observable} from "rxjs";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  public records: Record[];
  //public recs: Observable<Record[]>

  constructor(private service: RecordService) {
    this.records = [];
    //this.recs = service.get()
  }

  ngOnInit(): void {
    this.service.get().subscribe(data => {
      this.records = data
    })
  }

}
