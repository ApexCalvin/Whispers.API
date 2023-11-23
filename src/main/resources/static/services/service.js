let feedList;

function getFeedTest() {
    fetch('/post/desc')
    .then(response => response.json()) // Parse the response as JSON
    .then(data => {
        console.log(data)   
        feedList = data;
        console.log(feedList)
        const displayFeed = data.map(object => {
            return `
                <div class="postBlockFromFeed">
                    <h2 style="text-align: center;">${object.name}</h2>
                    <h3 style="text-align: center;">@${object.handle}</h3>
                    <p style="text-align: center;">${object.date}</p>
                    <p style="text-align: center;">${object.message}</p>
                </div>
            `;
        });

        const feed = document.getElementById('column-feed');
        feed.innerHTML = displayFeed.join('');
    })
    .catch(error => {
        console.error('Error fetching data: ', error)
    });
}

window.onload = getFeedTest;