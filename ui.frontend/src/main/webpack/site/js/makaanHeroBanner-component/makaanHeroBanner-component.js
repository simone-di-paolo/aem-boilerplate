document.addEventListener("DOMContentLoaded", carouselSlide)
export default function carouselSlide () {

    let slideIndex = 1;


    const prevButton = document.querySelector('.prev');
    const nextButton = document.querySelector('.next');

    if(prevButton && nextButton){
        showSlides(slideIndex);

        //al momento del click applico la funzione plusSlides con paramentro=-1
        prevButton.addEventListener('click', function() {
            plusSlides(-1);
        });

        nextButton.addEventListener('click', function() {
            plusSlides(1);
        });

        function plusSlides(n) {
            showSlides(slideIndex += n);
        }

        function currentSlide(n) {
            showSlides(slideIndex = n);
        }

        function showSlides(n) {
            let i;
            let slides = document.getElementsByClassName("banner-image");
            if (n > slides.length) {slideIndex = 1}
            if (n < 1) {slideIndex = slides.length}
            for (i = 0; i < slides.length; i++) {
                slides[i].style.display = "none";
            }
            slides[slideIndex-1].style.display = "block";
        }
    }

}
