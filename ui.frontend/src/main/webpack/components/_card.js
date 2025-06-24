document.addEventListener("DOMContentLoaded", function() {
    const toggleButton = document.querySelector(".card-button");

    if (toggleButton) {
        toggleButton.addEventListener("click", function() {
            const labelOn = toggleButton.getAttribute("data-label-on");
            const labelOff = toggleButton.getAttribute("data-label-off");
            if (toggleButton.textContent.trim() === labelOn) {
                toggleButton.textContent = labelOff;
            } else {
                toggleButton.textContent = labelOn;
            }
        });
    }
});
