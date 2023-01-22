import {PyramidService} from "./pyramid.service";
import {Box} from "./box";
import {Component, OnInit} from "@angular/core";


@Component({
	selector: 'app-pyramid',
	templateUrl: './pyramid.component.html'
})
export class PyramidComponent implements OnInit {
	boxes: Box[];

	constructor(private serv: PyramidService) {
		this.boxes = [];
	}

	ngOnInit(): void {
		this.serv.getMaxPyramid().subscribe(data => {
			this.boxes = data;
		})
	}

}
