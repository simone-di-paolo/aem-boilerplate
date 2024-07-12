export default function buttonBackToTop () {

    // Get the button
    let mybutton = document.getElementById("buttonBackToTop");

    function scrollToTop() {
        window.scrollTo({ top: 0, behavior: 'smooth' });
    }

    function scrollFunction() {
        //ottengo l'altezza dell'hero-banner
        const heroBannerHeight = document.querySelector('.spa-hero-banner-background').offsetHeight;
        //se la posizione dello scroll verticale è maggiore di questa altezza allora appare il pulsante
        if (window.scrollY > heroBannerHeight) {
            mybutton.style.display = "block";
        } else {
            mybutton.style.display = "none";
        }
    }

    window.addEventListener('scroll',scrollFunction);

    mybutton.addEventListener('click', scrollToTop);
}

