export default function buttonSimple () {

    function activeServlet() {
        let risultato;
        $.ajax({
            type: "GET",
            url: "/bin/GetEndPointServlet",
            async: false,
            cache: false,
            contentType: "application/json",
            success: function (result) {
                risultato = result;
                console.log(risultato);
            },
            error: function (request, error) {
                ///
            }
        });
    }

    let button = document.querySelector("#buttonSimple");
    if (button) {
        button.addEventListener("click", activeServlet);
    }
}
