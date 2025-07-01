document.addEventListener("DOMContentLoaded", () => {

    const btn = document.getElementById('bottone');
    const datanascita = document.getElementById('datanascita');
    const indirizzo = document.getElementById('indirizzo');
    const checkbox = document.getElementById('checkbox');
    const newsletter = document.getElementById('newsletter');

    btn.addEventListener("click", apripagina);
    function apripagina() {
    window.open("/content/formazioneboilerplate/us/en/task2/form-recupera-dati.html", "_blank");

    }

});