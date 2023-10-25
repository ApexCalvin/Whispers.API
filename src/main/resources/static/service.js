    function readById(event) {
        event.preventDefault();

        // gets html element named "account-id"
        const accountIdElement = document.getElementById("account-id");
        // gets user's input value from the html element
        const accountIdValue = personIdElement.value;

        $.ajax({
            type: "GET",
            crossDomain: true,
            headers: {
                'Accept':'application/json',
                'Content-Type':'application/json',
                'Access-Control-Allow-Origin': '*'
            },
            url: "/account/" + personIdValue,
            dataType: "JSON",
            success: function(response) {
                updateDisplay(response);
            },
            error: function(error) {
                updateDisplay(error);
            }
        });
    }