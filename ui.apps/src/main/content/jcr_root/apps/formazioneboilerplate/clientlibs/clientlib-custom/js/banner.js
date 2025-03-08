document.addEventListener("DOMContentLoaded", (event) => {

    const arrayImagesCarousel = document.getElementsByClassName('carousel-item'),
        previousButton= document.getElementById('previous-button'),
        nextButton = document.getElementById('next-button');
    let indexImage=0;

    // Function for showing current Image
    function showImage(index) {
        // Hide all Images, adding the class 'hidden' to the elements of "arrayImagesCarousel"
        for (let i = 0; i < arrayImagesCarousel.length; i++) {
            arrayImagesCarousel[i].classList.add('hidden');
            arrayImagesCarousel[i].classList.remove('visible');
        }
        // Show only current Image, removing default 'hidden' class to it
        arrayImagesCarousel[index].classList.remove('hidden');
        arrayImagesCarousel[index].classList.add('visible');
    }

    // Function for showing Next Image
    function viewNextImage() {
        // Going on in the array of Images
        indexImage++;
        // After the last Image, return to the first one
        if (indexImage >= arrayImagesCarousel.length) {
            indexImage = 0;
        }
        // Show the Image with that index
        showImage(indexImage);
    }

    // Function for showing Previous Image
    function viewPreviousImage() {
        // Going back in the array of Images
        indexImage--;
        // After the fist Image, return to the last one
        if (indexImage < 0) {
            indexImage = arrayImagesCarousel.length - 1;
        }
        //  Show the Image with that index
        showImage(indexImage);
    }

    // Show the first Image
    showImage(indexImage);

    // Add events to buttons
    nextButton.addEventListener('click', viewNextImage);
    previousButton.addEventListener('click', viewPreviousImage);
});
