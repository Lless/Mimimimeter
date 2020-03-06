send = function(id) {
    fetch("/vote", {
        method: 'POST',
        credentials: 'include',
        headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
        body: "choose=" + id,
        redirect: 'follow'
    }).then(response => {
        if (!response.ok) return;
        if (!response.redirected) return;
        document.location.href = response.url;
    });
}