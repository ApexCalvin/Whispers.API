function loadProfile() {
    
    console.log('load profile') 
    document.getElementById('profile-photo').src = `images/${activeUserId}.jpg`;
    
    fetch('/account/' + activeUserId)
    .then(response => response.json())
    .then(data => {
        console.log(data)
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
                                    <button onClick="console.log('Button Clicked!');">Submit</button>
                                </form>
                            </div>
                            `                

        const display = document.getElementById('profile-info');
        display.innerHTML = profileInfo + postForm;
    })
    .catch(error => {
        console.error('Error fetching data: ', error)
    });
}