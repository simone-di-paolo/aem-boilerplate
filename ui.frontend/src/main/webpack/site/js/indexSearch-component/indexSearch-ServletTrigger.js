export default function indexSearchField () {

    function activeServlet() {
        let searchTerm= document.querySelector("#searchKeywordIndex");
        let fulltext = searchTerm.value;
        console.log(fulltext);
        $.ajax({
            type: "GET",
            url: "/bin/IndexSearchServlet?fulltext=" + fulltext,
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

    let buttonSearch = document.querySelector(".makaan-custom-btn");
    if (buttonSearch) {
        buttonSearch.addEventListener("click", activeServlet);
    }

    function createCards(data) {
        const container = $('#container-custom');
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
                const image_property = $('<img>').attr('src', item.imagePath).attr('alt', 'Property Image');
                const title = $('<h2></h2>').text(item.title);
                //const text = $('<p></p>').html(item.text);
                const text = $(item.text);
                const content = $('<div class="content-text"></div>');

                div.append(image_property);
                content.append(title);
                //div.append(type);
                content.append(text);
                div.append(content)
                container.append(div);
            });
        }
    }
}
