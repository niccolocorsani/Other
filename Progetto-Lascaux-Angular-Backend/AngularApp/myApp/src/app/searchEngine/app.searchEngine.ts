import { Component } from '@angular/core';
import { ViewChild } from '@angular/core'
import { ElementRef, Renderer2 } from '@angular/core';

@Component({
  selector: 'my-component',
  templateUrl: './app.searchEngine.html'


})
//export var movies:any ;

export class myComponent {
  title = 'myApp';
  movies;
  //// To read constantly the value of the input
  onEvent(event: any) {
    this.title = event.target.value;
  }
  onClickSearch() {

    var request = new XMLHttpRequest();
    var serverResponse;
    request.open("GET", "http://localhost:8080/findMovie?id=" + this.title);
    request.onload = () => {
      serverResponse = request.responseText;
      var myObj = JSON.parse(serverResponse);
      alert("Title:  "+myObj.title+"\nRating:  "+myObj.rating+" \nYear:  "+myObj.year+"\nDescription:   "+myObj.description);

    };
    request.send();



  }

}
