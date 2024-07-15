// jquery
import './js/jquery-import.js';
// Stylesheets
import "./main.scss";

// Javascript or Typescript
import "./**/*.js";
import "./**/*.ts";
import '../components/**/*.js';
import './js/**/*.js';
import buttonSimple from "./js/buttonSimple-component/buttonSimple-ServletTrigger";
import bannerDarkLight from "./js/bannerDark-Light/bannerDarkLightGeneration";
import searchField from "./js/spaHeroBanner-component/heroBanner-ServletTrigger";
import buttonBackToTop from "./js/buttonBackToTop-component/buttonBackToTop";
import CheckActivityIsOpen from "./js/spaExplore-component/spaExplore";


window.onload=function (){
    buttonSimple();
    bannerDarkLight();
    searchField();
    buttonBackToTop();
    CheckActivityIsOpen();
}



