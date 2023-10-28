    function readById(event) {
        event.preventDefault(); //prevents the form from submitting?

        // gets html element named "account-id"
        const accountIdElement = document.getElementById("account-id");
        // gets user's input value from the html element
        const accountIdValue = accountIdElement.value;

        console.log("Account Id: " +accountIdValue)

        $.ajax({
            type: "GET",
            crossDomain: true,
            headers: {
                'Accept':'application/json',
                'Content-Type':'application/json',
                'Access-Control-Allow-Origin': '*'
            },
            url: "/account/" + accountIdValue,
            dataType: "JSON",
            success: function(response) {
                updateDisplay(response);
            },
            error: function(error) {
                updateDisplay(error);
            }
        });
    }

    const updateDisplay = (response) => document.getElementById("account-output").innerText = JSON.stringify(response);