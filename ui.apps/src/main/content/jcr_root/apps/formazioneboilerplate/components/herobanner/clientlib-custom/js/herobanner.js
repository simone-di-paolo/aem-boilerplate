document.addEventListener("DOMContentLoaded", () => {

    const btn = document.getElementById('button');
    const mexAlert = document.getElementById('mex');
    const overlay = document.getElementById('overlay-alert');
    const closeBtn = document.getElementById('closebtn');
    const numerorandom = document.getElementById('Nrandom');
    const lista = document.getElementById('lista');


    function numRandom() {
        let min = 0;
        let max = 100;
        return Math.floor(Math.random() * (max - min + 1)) + min;
    }

    btn.addEventListener("click", mostraNumeroRandom);
    function mostraNumeroRandom() {
        const numero = numRandom();
        if (numero % 2 === 0) {
            numerorandom.textContent = "Il tuo numero fortunato è " + numero + " ed è pari";
        } else {
            numerorandom.textContent = "Il tuo numero fortunato è " + numero + " ed è dispari";
        }
        let listanumeri = [];
        for (let i = 0; i <= numero; i++) {
            if (i % 2 === 0)
                listanumeri.push(" "+ i);
        }
        lista.textContent = "I numeri pari da 0 a " +numero+ " sono " + listanumeri;
        mexAlert.style.display = 'block';
        overlay.style.display = 'block';
    }

    closeBtn.addEventListener("click", closeAlert);
    function closeAlert() {
        mexAlert.style.display = 'none';
        overlay.style.display = 'none';
    }

});
