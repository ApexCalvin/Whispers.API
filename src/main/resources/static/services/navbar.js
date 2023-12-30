function searchMenu() {
    console.log("searchMenu method")

    const searchMenu =  `
                        <div id="search-Menu" class="postBlockFromFeed">
                            <input id="search-value">
                            <button onClick="searchHandle()">Handle Search</button>
                            <button onClick="searchHashtag()";>Hashtag Search</button>
                        </div>
                        `;

    const display = document.getElementById('search-div');
    display.innerHTML = searchMenu;
}

function searchHandle() {
    console.log("searchHandle method");

    //let searchValue = document.getElementById("search-value");
    let searchValue = "homelander";
 
    let url = "/handle/" + searchValue;

    fetch(url)
        .then(response => response.json()) // Parse the response as JSON
        .then(data => {
            const feed = data.map(object => {
                return `
                    <div class="postBlockFromFeed" onClick="loadSelectedPost(${object.id})">
                        <h4 style="text-align: center; color: #1190E7;">${object.name} @${object.handle}</h2>
                        <h4 style="text-align: center;">${object.message}</h4>
                        <p style="text-align: center;">${moment(object.date).format('MMMM Do YYYY, h:mm:ss a')}</p>
                    </div>
                `;
            });

            const display = document.getElementById('column-details');
            display.innerHTML = feed.join('');
        })
        .catch(error => {
            console.error('Error fetching data: ', error)
        });
}

function searchHashtag() {
    console.log("searchHashtag method")

    //let searchValue = document.getElementById("search-value");
    let searchValue = "first";

    let url = "/hashtag/"+searchValue+"/posts";

    fetch(url)
    .then(response => response.json()) // Parse the response as JSON
    .then(data => {
        const feed = data.map(object => {
            return `
                <div class="postBlockFromFeed" onClick="loadSelectedPost(${object.id})">
                    <h4 style="text-align: center; color: #1190E7;">${object.name} @${object.handle}</h2>
                    <h4 style="text-align: center;">${object.message}</h4>
                    <p style="text-align: center;">${moment(object.date).format('MMMM Do YYYY, h:mm:ss a')}</p>
                </div>
            `;
        });

        const display = document.getElementById('column-details');
        display.innerHTML = feed.join('');
    })
    .catch(error => {
        console.error('Error fetching data: ', error)
    });
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
                        <img id="accounts-photo" src="./images/${object.id}.jpg" alt="Image of Profile" style="border-radius: 50%; width: 100px; height: auto;">
                        <div>
                            <h4 style="text-align: center; color: #1190E7;">${object.name}</h2>
                            <p style="text-align: center; color: #1190E7;">@${object.handle}<p>
                        </div>    
                    </div>
                `;
            });

            const display = document.getElementById('nav-select');
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
    document.getElementById('nav-select').style.display = '';
    loadPage()
}