var activeUser = "selected";

function getPostsDesc(event) {
        event.preventDefault();

        $.ajax({
            type: "GET",
            crossDomain: true,
            headers: {
                'Accept':'application/json',
                'Content-Type':'application/json',
                'Access-Control-Allow-Origin': '*'
            },
            url: "/account/all",
            dataType: "JSON",
            success: function(response) {
                updateDisplay(response);
            },
            error: function(error) {
                updateDisplay(error);
            }
        });
}

function getPostsByHandleDesc(event) {
}

function getPost(event) {
        event.preventDefault();

        const postId = document.getElementById("account-id").value;

        $.ajax({
            type: "GET",
//            crossDomain: true,
//            headers: {
//                'Accept':'application/json',
//                'Content-Type':'application/json',
//                'Access-Control-Allow-Origin': '*'
//            },
            url: "/post/" + postId,
            dataType: "JSON",
            success: function(response) {
                updateDisplay(response);
            },
            error: function(error) {
                updateDisplay(error);
            }
        });
}

function createPost(event) {
    event.preventDefault();

    const accountId = document.getElementById("account-id").value;
    const message = document.getElementById("").value;

    const person = new Person(personIdValue, firstNameValue, lastNameValue);
    const personData = JSON.stringify(person);

    $.ajax({
        type: "POST",
        crossDomain: true,
        headers: {
            'Accept':'application/json',
            'Content-Type':'application/json',
            'Access-Control-Allow-Origin': '*'
        },
        url: "/create",
        data: personData,
        dataType: "JSON",
        success: function(response) {
            updateDisplay(response);
        },
        error: function(error) {
            updateDisplay(error)
        }
    });
}

function getAccount(event) {
}

function getAllAccounts(event) {
}

function createAccount(event) {
}

function pageLoaded() {
    alert('Page is loaded.');
}

// window.onload = pageLoaded;

function getFeed() {
            fetch('/post/desc/all', {
                method: 'GET',
            })
              .then(response => {
                if (!response.ok) {
                  throw new Error('Network response was not ok');
                }
                return response.json(); // Parse the response as JSON
              })
              .then(data => {
                updateDisplay(data);
              })
              .catch(error => {
                updateDisplay(error);
              });
}