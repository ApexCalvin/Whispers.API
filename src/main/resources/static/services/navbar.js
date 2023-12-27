function searchMenu() {
    console.log("search method")
}

function accountsMenu() {
    console.log("accounts method")

    fetch('/account')
    .then(response => response.json()) // Parse the response as JSON
    .then(data => {

        const prompt = `<h4> Select Account </h4>`;

        const accounts = data.map(object => {
            return `
                <div class="postBlockFromFeed" onClick="setActiveUser(${object.id})">
                    <h4 style="text-align: center; color: #1190E7;">${object.name}</h2>
                    <p style="text-align: center; color: #1190E7;">@${object.handle}<p>
                </div>
            `;
        });

        const display = document.getElementById('columns-container');
        display.innerHTML = prompt + accounts.join('');
    })
    .catch(error => {
        console.error('Error fetching data: ', error)
    });
}

function setActiveUser(userId) {
    console.log("userId: " + userId)
    console.log("activeUserId: " + activeUserId)
    activeUserId = userId;
    console.log("changed activeUserId: " + activeUserId)
}