document.addEventListener("DOMContentLoaded", () => {

    const signupbutton = document.getElementById('signupbutton')
    const closebutton = document.getElementById('closebutton')
    const alert = document.getElementById('alert')
    const overlay = document.getElementById('overlay')

    signupbutton.addEventListener("click", openAlert);
    function openAlert() {
        const alertMessage = document.getElementById('alert-message');
        alert.style.display = 'block';
        overlay.style.display = 'block';
        const emailValue = document.getElementById('newsletter-email').value;
        if (emailValue !== ""){
            alertMessage.textContent = "L’email " + emailValue + " è stata inserita correttamente";}
        else {  alertMessage.textContent = "Inserire un'email valida";}

    }

    signupbutton.addEventListener("click", changeColor);
    function changeColor(){
        const colors = ["#0000FF", "#FFFF00", "#7300ff", "#0090ff", "#00FFFF", "#FF00FF", "#aa00ff","#00fff0", "#ff00ee", "#ab7a6d"];
        const randomColor = Math.floor(Math.random() * colors.length);
        this.style.backgroundColor = colors[randomColor];
    }

    closebutton.addEventListener("click", closeAlert);
    function closeAlert() {
        alert.style.display = 'none';
        overlay.style.display = 'none';
    }


});
