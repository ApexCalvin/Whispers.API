let activeUserId = 3;

function loadPage() {
    console.log('inside loadPage()')
    loadProfile()
    loadFeed()
}

window.onload = loadPage;