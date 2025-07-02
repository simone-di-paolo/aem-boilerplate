/* document.addEventListener("DOMContentLoaded", function () {
    const button = document.querySelector(".OpenButton");

    if (button) {
        button.addEventListener("click", function () {
            window.location.href = "/content/formazioneboilerplate/us/en/survey.html";
        });
    }
}); */

document.addEventListener("DOMContentLoaded", function () {
    const button = document.querySelector(".OpenButton");

    if (button) {
        button.addEventListener("click", function () {
            const name = document.querySelector(".survey .name")?.textContent.trim() || "";
            const surname = document.querySelector(".survey .surname")?.textContent.trim() || "";
            const birthdate = document.querySelector(".survey .birthdate")?.textContent.trim() || "";
            const birthplace = document.querySelector(".survey .birthplace")?.textContent.trim() || "";

            const url = `/content/formazioneboilerplate/us/en/dataRetrieve.html?name=${encodeURIComponent(name)}&surname=${encodeURIComponent(surname)}&birthdate=${encodeURIComponent(birthdate)}&birthplace=${encodeURIComponent(birthplace)}`;
            console.log("Redirecting to URL:", url);
            window.location.href = url;
        });
    }
});