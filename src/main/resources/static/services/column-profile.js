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
                                        <button onClick="console.log('Button Clicked!');">Submit</button>
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