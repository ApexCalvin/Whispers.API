function loadFeed() {
    fetch('/post/desc')
        .then(response => response.json()) // Parse the response as JSON
        .then(data => {
            const feed = data.map(object => {
                return `
                    <div class="postBlockFromFeed" onClick="loadSelectedPost(${object.id})">
                        <h4 style="text-align: center; color: #1190E7;">${object.name} @${object.handle}</h2>
                        <h4 style="text-align: center;">${object.message}</h4>
                        <p style="text-align: center;">${moment(object.date).format('MMMM Do YYYY, h:mm:ss a')}</p>
                        <p style="text-align: center; color: #1190E7;"><em>Click for more details</em><p>
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