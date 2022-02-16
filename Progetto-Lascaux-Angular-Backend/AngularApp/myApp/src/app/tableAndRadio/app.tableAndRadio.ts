import {Component} from '@angular/core';
import { myComponent } from '../searchEngine/app.searchEngine';
//import {movies} from '../searchEngine/app.searchEngine';


@Component({
    selector: 'tableAndRadioB',
    templateUrl: './app.tableAndRadio.html',   //// il file HTML e TS devono chiamarsi nello stesso modo
    styleUrls: ['./tableAndRadio.css']

})



export class tableAndRadio{




movies;


constructor(){



  var request = new XMLHttpRequest();
  var serverResponse;

  request.open("GET", "http://localhost:8080/retriveMovie");
  request.onload = () => {
    serverResponse = request.responseText;
    var myObj = JSON.parse(serverResponse);
    this.movies = myObj;


  };
  request.send();


}



onClickName(){

  var request = new XMLHttpRequest();
  var serverResponse;
  request.open("GET", "http://localhost:8080/orderBy?id=name");
  request.onload = () => {
    serverResponse = request.responseText;
    var myObj = JSON.parse(serverResponse);
    this.movies = myObj;


  };
  request.send();


}



onClickYear(){

  var request = new XMLHttpRequest();
  var serverResponse;
  request.open("GET", "http://localhost:8080/orderBy?id=year");
  request.onload = () => {
    serverResponse = request.responseText;
    var myObj = JSON.parse(serverResponse);
    this.movies = myObj;


  };
  request.send();




}

onClickRate(){


  var request = new XMLHttpRequest();
  var serverResponse;
  request.open("GET", "http://localhost:8080/orderBy?id=rate");
  request.onload = () => {
    serverResponse = request.responseText;
    var myObj = JSON.parse(serverResponse);
    this.movies = myObj;


  };
  request.send();


}


}





