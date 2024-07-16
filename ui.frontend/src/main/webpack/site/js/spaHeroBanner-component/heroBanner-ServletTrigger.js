export default function searchField () {

    function activeServlet() {
        let typeLocal= document.querySelector("#typeLocal");
        let cities=document.querySelector("#city");
        let type = typeLocal.options[typeLocal.selectedIndex].text;
        let city = cities.options[cities.selectedIndex].text;
        console.log(type);
        console.log(city);
        $.ajax({
            type: "GET",
            url: "/bin/GetEndPointServlet?type=" + type +"&city=" +city,
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

    let button = document.querySelector(".spa-hero-banner-btn");
    if (button) {
        button.addEventListener("click", activeServlet);
    }

    function createCards(data) {
        const container = $('#container');
        // svuoto il div riempito nella ricerca precedente
        container.empty();

        // Verifica se data è un array
        if (!Array.isArray(data)) {
            console.error('Errore: la risposta JSON non è un array', data);
            return;
        }

        if (data.length === 0) {
            const noResultsCard = $('<div class="no-results-card">No results found</div>');
            container.append(noResultsCard);
        } else {
            data.forEach(item => {
                const div = $('<div class="card-hero"></div>');
                const name = $('<h2></h2>').text(item.name);
                const address = $('<p></p>').text(item.address);
                const phone = $('<p class="phone"></p>').text(item.phoneNumber);

                div.append(name);
                div.append(address);
                div.append(phone);
                container.append(div);
            });
        }
    }
}



