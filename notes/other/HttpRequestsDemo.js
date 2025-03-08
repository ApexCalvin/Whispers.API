    function readById(event) {
        event.preventDefault(); //prevents the form from submitting?

        // gets value from html element named "account-id"
        const accountIdValue = document.getElementById("account-id").value;

        console.log("Account Id: " +accountIdValue)

        ajaxRequest(accountIdValue);
//        fetchRequest(accountIdValue);
//        axiosRequest(accountIdValue);
    }

    const updateDisplay = (response) => document.getElementById("account-output").innerText = JSON.stringify(response);

    function axiosRequest(accountIdValue) {
    /*
        axios.get('https://api.example.com/data')
          .then(response => {
            console.log(response.data);
          })
          .catch(error => {
            console.error('Axios error:', error);
          });
    */
    }

    function fetchRequest(accountIdValue) {
        fetch('/account/' + accountIdValue, {
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

    function ajaxRequest(accountIdValue) {
         $.ajax({
                    type: "GET",
//                    crossDomain: true,
//                    headers: {
//                        'Accept':'application/json',
//                        'Content-Type':'application/json',
//                        'Access-Control-Allow-Origin': '*'
//                    },
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

/*
    <form>
        <input id="account-id" placeholder="Id">
        <br>
        <br>
        <input id="account-name" placeholder="Name">
        <br>
        <br>
        <input id="account-handle" placeholder="Handle">
        <br>
        <br>
        <button onClick="create(event)">Create</button>
        <button onClick="readById(event)">Read</button>
        <button onClick="readAll(event)">Read All</button>
        <button onClick="update(event)">Update</button>
        <button onClick="deleteById(event)">Delete</button>
        <br>
        <br>
        <button onClick="getPostsDesc(event)">Home</button>
        <button onClick="getPostsByHandleDesc(event)">Your Posts</button>
        <button onClick="getPost(event)">Read Post</button>
        <button onClick="createPost(event)">Create Post</button>
    </form>
*/