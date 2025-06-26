const btn = document.getElementById('button');
const mexAlert = document.getElementById('mex');
const overlay = document.getElementById('overlay-alert');
const x= document.getElementById('closebtn');

btn.addEventListener("click", alertmodule);
function alertmodule() {
    mexAlert.style.display = 'block';
    overlay.style.display = 'block';
}

x.addEventListener("click", closeAlert);
function closeAlert() {
    mexAlert.style.display = 'none';
    overlay.style.display = 'none';
}