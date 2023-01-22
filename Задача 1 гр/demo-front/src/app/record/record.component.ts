import {Component} from "@angular/core";
import {Record} from "./record";
import {RecordService} from "./RecordService";

@Component({
  selector: 'app-record',
  templateUrl: 'record.component.html'
})
export class RecordComponent {
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
