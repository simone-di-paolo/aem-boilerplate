export default function bannerDarkLight(){

    function generateDarkLightBanner(){
        let components =document.querySelectorAll(".banner-component");
        //iterazione di un multifield come se fosse una lista di componenti
        if(components){
            for(let i=0; i< components.length; i++)
            {
                if ((components[i].getAttribute("data-theme"))==="dark-theme"){
                    components[i].classList.add("dark-theme");
                }else if((components[i].getAttribute("data-theme"))==="light-theme"){
                    components[i].classList.add("light-theme");
                }
            }
        }
    }
    generateDarkLightBanner();
}