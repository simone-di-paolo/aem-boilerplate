export default function makaanSearchField () {

    function activeServlet() {
        let propertyType= document.querySelector("#propertyType");
        let locations=document.querySelector("#location");
        let type = propertyType.options[propertyType.selectedIndex].text;
        let location = locations.options[locations.selectedIndex].text;
        console.log(type);
        console.log(location);
        $.ajax({
            type: "GET",
            url: "/bin/GetEndPointSearchServlet?type=" + type +"&location=" + location,
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

    let buttonSearch = document.querySelector(".makaan-btn");
    if (buttonSearch) {
        buttonSearch.addEventListener("click", activeServlet);
    }

    function createCards(data) {
        const container = $('#container');
        const image_url="/content/dam/formazioneboilerplate/property-1.jpg";
        // svuoto il div riempito nella ricerca precedente
        container.empty();

        // Verifica se data è un array
        if (!Array.isArray(data)) {
            console.error('Errore: la risposta JSON non è un array', data);
            return;
        }

        if (data.length === 0) {
            const noResultsCard = $('<div class="no-results">No results found</div>');
            container.append(noResultsCard);
        } else {
            data.forEach(item => {
                const div = $('<div class="property-card"></div>');
                // Crea l'elemento immagine
                const image_property = $('<img>').attr('src', image_url).attr('alt', 'Property Image');
                const property_name = $('<h2></h2>').text(item.property_name);
                const property_type = $('<p></p>').text(item.property_type);
                const address = $('<p></p>').text(item.address);
                const info = $('<div class="info"></div>');
                const property_size = $('<p></p>').text(item.property_size + " Sqft");
                const bed_quantity = $('<p></p>').text(item.bed_quantity + " Bed");
                const bath_quantity = $('<p></p>').text(item.bath_quantity + " Bath");
                const type = $('<div class="type"></div>').text(item.type);

                div.append(image_property);
                div.append(property_name);
                div.append(type);
                div.append(address);
                //div.append(property_type);
                info.append(property_size);
                info.append(bed_quantity);
                info.append(bath_quantity);
                div.append(info);
                container.append(div);
            });
        }
    }
}
