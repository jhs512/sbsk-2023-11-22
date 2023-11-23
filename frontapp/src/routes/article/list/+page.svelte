<script>
    let articles = $state([]);

    $effect(() => {
        fetch('http://localhost:8090/api/v1/articles')
            .then(response => response.json())
            .then(json => articles = json);
    });
</script>

<div>
    <h1>Articles</h1>

    {JSON.stringify(articles)}
    <hr>
    <ul>
        {#each articles as article}
            <li>
                <a href="/articles/{article.id}">{article.id}</a>
            </li>
        {/each}
    </ul>
</div>
