document.addEventListener("DOMContentLoaded", function() {
    const toggleButton = document.querySelector(".card-button");

    if (toggleButton) {
        toggleButton.addEventListener("click", function () {
            const labelOn = toggleButton.getAttribute("data-label-on");
            const labelOff = toggleButton.getAttribute("data-label-off");
            if (toggleButton.textContent.trim() === labelOn) {
                toggleButton.textContent = labelOff;
            } else {
                toggleButton.textContent = labelOn;
            }
        });
    }

    const modal = document.getElementById("modal");
    const openModal = document.getElementById("openModal");
    const closeModal = document.getElementById("closeModal");
    const questionIds = ["question1", "question2", "question3", "question4"];
    const overlay = document.querySelector(".modal-overlay-hide");

    function hideAllQuestions() {
        questionIds.forEach(id => {
            document.getElementById(id).style.display = "none";
        });
    }

    function showRandomQuestion() {
        const randomIndex = Math.floor(Math.random() * questionIds.length);
        const randomQuestionId = questionIds[randomIndex];
        document.getElementById(randomQuestionId).style.display = "block";
    }

    openModal.onclick = function () {
        hideAllQuestions();
        showRandomQuestion();
        modal.style.display = "block";
        overlay.style.display = "block";
    }

    closeModal.onclick = function () {
        modal.style.display = "none";
        overlay.style.display = "none";
        hideAllQuestions();

    }
});
