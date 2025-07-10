document.addEventListener("DOMContentLoaded", () => {
    const btn = document.getElementById('btn');

    btn.addEventListener("click", (event) => {
        event.preventDefault(); // permette di arrestare e non inviare link come get

        const datadinascita = document.getElementById('datadinascita').value;
        const nazionalita = document.getElementById('nazionalita').value;
        const indirizzo = document.getElementById('indirizzo').value;
        const checkbox = document.getElementById('checkbox').value;
        const newsletter = document.getElementById('newsletter').value;


        sessionStorage.setItem('datadinascita', datadinascita);
        sessionStorage.setItem('nazionalita', nazionalita);
        sessionStorage.setItem('indirizzo', indirizzo);
        sessionStorage.setItem('checkbox', checkbox);
        sessionStorage.setItem('newsletter', newsletter);


        window.open("/content/formazioneboilerplate/us/en/form-recupera-dati.html", "_blank");
    });
});
