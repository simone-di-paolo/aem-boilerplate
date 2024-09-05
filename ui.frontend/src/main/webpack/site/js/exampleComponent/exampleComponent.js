export default function exampleComponent () {

    function activeButton() {
        $.ajax({
            type: "GET",
            url: "/bin/GetEndPointExampleComponetServlet",
            dataType: 'json',
            async: false,
            cache: false,
            contentType: "application/json",
            success: function (result) {
                console.log('Dati ricevuti:', result);
                createCards(result);
            },
            error: function (request, error) {
                ///
            }
        });
    }

    let buttonExample = document.querySelector(".btn-example");
    if (buttonExample) {
        buttonExample.addEventListener("click", activeButton);
    }

    function createCards(data) {
        const container = document.getElementById('container');
        //container.empty();

        // Verifica se data è un array
        if (!Array.isArray(data)) {
            console.error('Errore: la risposta JSON non è un array', data);
            return;
        }

        if (data.length !== 0) {
            data.forEach(item => {
                const div = document.createElement('div');
                div.className = 'film-card';

                const name_film = document.createElement('h2');
                name_film.textContent = "Name: "+item.name_film;

                const category = document.createElement('p');
                category.textContent = "Category: "+item.category;

                const year = document.createElement('p');
                year.textContent = "Year: "+item.year;

                const director = document.createElement('p');
                director.textContent = "Director: "+item.director;

                const nation = document.createElement('p');
                nation.textContent = "Nation: "+item.nation;

                // Appendo tutti gli elementi creati al div principale
                div.appendChild(name_film);
                div.appendChild(category);
                div.appendChild(year);
                div.appendChild(director);
                div.appendChild(nation);
                // Aggiungo il div principale al container
                container.appendChild(div);
            });
        }
    }
}