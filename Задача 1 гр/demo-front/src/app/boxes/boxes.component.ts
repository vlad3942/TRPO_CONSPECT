import {Component, OnInit} from "@angular/core";
import {BoxesService} from "./boxes.service";

@Component({
  selector: 'app-boxes',
  templateUrl: 'boxes.component.html'
})
export class BoxesComponent implements OnInit {

  count: number

  constructor(private counter: BoxesService) {
    this.count = -1
  }

  ngOnInit(): void {
    this.counter.get().subscribe(
      data => {
        this.count = data.count;
      }
    )
  }
}
