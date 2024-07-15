export default function CheckActivityIsOpen(){

    function checkActivity(){
        let activity =document.querySelectorAll(".check-activity");
        //iterazione di un multifield come se fosse una lista di componenti
        if(activity){
            for(let i=0; i< activity.length; i++)
            {
                if ((activity[i].textContent === "Open Now")){
                    activity[i].classList.add("spa-card-opening");
                }else if((activity[i].textContent === "Close Now")){
                    activity[i].classList.add("spa-card-closing");
                }
            }
        }
    }
    checkActivity();
}