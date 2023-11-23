function loadFeed() {
    fetch('/post/desc')
    .then(response => response.json()) // Parse the response as JSON
    .then(data => {
        const feed = data.map(object => {
            return `
                <div class="postBlockFromFeed" onclick="loadSelectedPost(${object.id})">
                    <h4 style="text-align: center; color: #1190E7;">${object.name} @${object.handle}</h2>
                    <h4 style="text-align: center;">${object.message}</h3>
                    <p style="text-align: center;">${object.date}</p>
                </div>
            `;
        });

        const display = document.getElementById('column-feed');
        display.innerHTML = feed.join('');
    })
    .catch(error => {
        console.error('Error fetching data: ', error)
    });
}

function loadSelectedPost(postId) {
    const post_url = "/post/" + postId;
    const comments_url = "/comment/post/" + postId;

    // Function to perform a fetch
    const fetchData = async (url) => {
        return fetch(url)
                .then(response => response.json())
                .then(data => {
                    console.log(data)
                    return data;
                })
                .catch(error => {
                    console.error('Error fetching data: ', error)
                });
    };

    // Make two fetch calls concurrently using Promise.all
    Promise.all([fetchData(post_url), fetchData(comments_url)])
    .then(([data1, data2]) => {
        const post = `
                    <div class="postBlockFromSelected">
                        <h4 style="text-align: center; color: #1190E7;">${data1.accountName} @${data1.accountHandle}</h2>
                        <h4 style="text-align: center;">${data1.message}</h3>
                        <p style="text-align: center;">${data1.date}</p>
                    </div>
                    `;
        const comments = data2.map(object => {
            return `
                <div class="postBlockFromFeed">
                    <h4 style="text-align: center; color: #1190E7;">${object.accountName} @${object.accountHandle}</h2>
                    <h4 style="text-align: center;">${object.message}</h3>
                    <p style="text-align: center;">${object.date}</p>
                </div>
            `;
        });

        const display = document.getElementById('column-post');
        display.innerHTML = post + comments.join('');
    })
    .catch(error => {
        console.error('Error fetching data: ', error)
    }); 
}

window.onload = loadFeed;