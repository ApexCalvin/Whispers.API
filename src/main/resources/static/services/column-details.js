function loadSelectedPost(postId) {
    const post_url = "/post/" + postId;
    const comments_url = "/post/" + postId + "/comments";

    const fetchData = async (url) => {
        return fetch(url)
                .then(response => response.json())
                .then(data => {
                    //console.log(data)
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
                        <p style="text-align: center;">${moment(data1.date).format('MMMM Do YYYY, h:mm:ss a')}</p>
                    </div>
                    `;

        const comments = data2.map(object => {
            return `
                <div class="postBlockFromFeed">
                    <h4 style="text-align: center; color: #1190E7;">${object.accountName} @${object.accountHandle}</h2>
                    <h4 style="text-align: center;">${object.message}</h3>
                    <p style="text-align: center;">${moment(object.date).format('MMMM Do YYYY, h:mm:ss a')}</p>
                </div>
            `;
        });

        console.log("Post id = " + data1.id)
        console.log("Active user id = " + activeUserId)

        const commentForm = `
                            <div class="postBlockFromFeed">
                                <form style="text-align: center;">
                                    <input type="text" id="new-comment-message" placeholder="Type Comment Here"/>
                                    <br>
                                    <br>
                                    <button type="button" onClick="createCommentTest(document.getElementById('new-comment-message').value)">Submit</button>
                                </form>
                            </div>
                            `

        const display = document.getElementById('column-post');
        display.innerHTML = post + comments.join('') + commentForm;
    })
    .catch(error => {
        console.error('Error fetching data: ', error)
    }); 
}

//${data1.id}, 
//${activeUserId},

class Comment {
    constructor(accountId, postId, message) {
        this.accountId = accountId;
        this.postId = postId;
        this.message = message;
    }
}

//function createCommentTest(postId, activeUserId, message) {
function createCommentTest(message) {
    //console.log('[in test method refe] Post = '+ postId + ', Active = ' + activeUserId)
    console.log("inside createCommentTest()");
    
    //const s = document.getElementById("new-comment-message").value;
    console.log("msg = " + message)


    // const sa = s.value;
    // console.log('msg = ' + message)
    // console.log('msg = ' + sa)
}

function createComment(postId, activeUserId) {

    //console.log('[create comment method] Post = '+ postId + ', Active = ' + activeUserId)
    
    //event.preventDefault(); //??
    // console.log("createComment " + postId + activeUserId);

    // const message = document.getElementById("new-comment-message").value;

    // const comment = new Comment(activeUserId, postId, message);
    // const commentData = JSON.stringify(comment);
    // console.log(commentData);

    // $.ajax({
    //     type: "POST",
    //     url: "/comment",
    //     data: commentData,
    //     dataType: "JSON",
    //     success: function(response) {
    //         console.log(response);
    //     },
    //     error: function(error) {
    //         console.error('Error fetching data: ', error)
    //     }
    // });
}