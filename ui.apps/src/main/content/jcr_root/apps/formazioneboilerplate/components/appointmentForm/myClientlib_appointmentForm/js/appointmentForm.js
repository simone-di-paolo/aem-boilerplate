document.addEventListener("DOMContentLoaded", function () {
    const button = document.getElementById("submitButtonForm");

    button.addEventListener("click", function () {
        let isValid = true;

        const fields = ["guardianName", "guardianEmail", "childName", "childAge", "message"];

        fields.forEach(className => {
            const input = document.querySelector(`.${className}`);
            const errorDiv = document.querySelector(`.${className}-error`);

            if (!input.value.trim()) {
                errorDiv.textContent = "Please fill the field";
                isValid = false;
            } else {
                errorDiv.textContent = "";
            }
        });

        if (!isValid) {
            return;
        }

        const guardianName = document.querySelector(".guardianName").value;
        const guardianEmail = document.querySelector(".guardianEmail").value;
        const childName = document.querySelector(".childName").value;
        const childAge = document.querySelector(".childAge").value;
        const message = document.querySelector(".message").value;

        const url = `/content/formazioneboilerplate/us/en/form-appointment-retrieve.html?guardianName=${encodeURIComponent(guardianName)}&guardianEmail=${encodeURIComponent(guardianEmail)}&childName=${encodeURIComponent(childName)}&childAge=${encodeURIComponent(childAge)}&message=${encodeURIComponent(message)}`;

        window.location.href = url;
    });
});
