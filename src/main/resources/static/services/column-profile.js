function loadProfile() {
    document.getElementById('profile-photo').src = `./images/${activeUserId}.jpg`;
    
    fetch('/account/' + activeUserId)
        .then(response => response.json())
        .then(data => {
            const profileInfo = `
                                <h2 style="text-align: center; color: #1190E7;">${data.name}</h2>
                                <h4 style="text-align: center;">@${data.handle}</h3>
                                `;

            const postForm = `
                                <div class="postBlockFromFeed">
                                    <form style="text-align: center;">
                                        <input id="new-comment-message" placeholder="Type Message Here">
                                        <br>
                                        <br>
                                        <button onClick="createPost()">Submit</button>
                                    </form>
                                </div>
                                `                

            const displayProfile = document.getElementById('profile-info');
            displayProfile.innerHTML = profileInfo;

            const displayPostForm = document.getElementById('createPost');
            displayPostForm.innerHTML = postForm;
        })
        .catch(error => {
            console.error('Error fetching data: ', error)
        });
}

function createPost() {
    console.log('createPost method');

    const msg = document.getElementById("new-comment-message").value;

    // Data to be sent in the request body
    const postData = {
        accountid: activeUserId,
        message: msg,
        hashtags: []
    };

    // Define the fetch options for the POST request
    const fetchOptions = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            // Add any other headers as needed
        },
        body: JSON.stringify(postData), // Convert the data to a JSON string
    };

    // Make the POST request using the Fetch API
    fetch("/post", fetchOptions)
        .then(response => {
            // Check if the request was successful (status code 200-299)
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }

            // Parse the response JSON
            return response.json();
        })
        .then(data => {
            // Handle the data from the response
            console.log('POST request successful:', data);
        })
        .catch(error => {
            // Handle any errors that occurred during the fetch
            console.error('POST request error:', error);
        });
}