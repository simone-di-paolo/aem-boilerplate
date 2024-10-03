document.addEventListener("DOMContentLoaded", (event) => {
    //per come ho scritto il codice html negli sly, mi accorgo di aver creato un multifield dal <div class='owl-carousel-item' e non dall'<img> in esso contenuto
    // Perciò provo a modificare la fonte da cui prendo gli Elementi per l'array immaginiCarosello.
    // Quindi inserisco nel metodo la classe del div:
    const immaginiCarosello = document.getElementsByClassName('owl-carousel-item');
    let indiceImmagine = 0;
    const prevButton = document.getElementById('prev-button');
    const nextButton = document.getElementById('next-button');

    // Elimino la costante output: invece che iniettare codice nell'HTML provo ad aggiungere una funzione per MOSTRARE l'immagine
    function mostraImmagine(indice) {
        // aggiungo quindi delle classi CSS al div di riferimento che richiamerò per MOSTRARE o NASCONDERE quel div:
        for (let i = 0; i < immaginiCarosello.length; i++) {
            immaginiCarosello[i].classList.add('hidden');
            immaginiCarosello[i].classList.remove('visible');
        }
        // nel for faccio scorrere tutti gli elementi dell'array/multifield e li nascondo
        //mentre mostro soltanto l'elemento corrente: "indice" per l'appunto
        immaginiCarosello[indice].classList.remove('hidden');
        immaginiCarosello[indice].classList.add('visible');
    }

    /*
    function viewNextImage() {
        if (indiceImmagine<immaginiCarosello.length) {
            output.innerHTML = immaginiCarosello[indiceImmagine];
            indiceImmagine++;
        } else {
            output.innerHTML = immaginiCarosello[0];
        }
    }
    nextButton.addEventListener('click',viewNextImage);
});*/
    function viewNextImage() {
        // provo a capovolgere la vecchia funzione in modo da far incrementare l'indice immagini fuori dall'if
        indiceImmagine++;
        // e quindi se l'indice supera il numero di immagini, ritorna alla prima
        if (indiceImmagine >= immaginiCarosello.length) {
            indiceImmagine = 0;
        }
        // applico quindi la funzione dichiarata prima per mostrare esattamente l'immagine corrente
        mostraImmagine(indiceImmagine);
    }

    // definisco la funzione per visualizzare l'immagine precedente facendo il contrario della funzione next
    function viewPreviousImage() {
        // Cioè devo decrementare l'indice
        indiceImmagine--;
        // e se arrivo all'inizio dell'array devo ritornare all'ultimo elemento
        if (indiceImmagine < 0) {
            indiceImmagine = immaginiCarosello.length - 1;
        }
        // e di nuovo applico la funzione per mostrare l'immagine precedente
        mostraImmagine(indiceImmagine);
    }

    // Visto che abbiamo dichiarato l'indiceImmagine come l'immagine alla posizione 0 e abbiamo definito la funzione mostraImmagine per mostrare gli elementi
    // parto con il mostrare la prima immagine
    mostraImmagine(indiceImmagine);

    nextButton.addEventListener('click', viewNextImage);
    prevButton.addEventListener('click', viewPreviousImage);
});
