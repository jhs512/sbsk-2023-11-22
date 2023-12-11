<script>
    let posts = $state([]);

    $effect(() => {
        fetch('http://localhost:8090/api/v1/posts/mine', {
            credentials: 'include',
        })
            .then(response => response.json())
            .then(rs => posts = rs.data.items);
    });
</script>

<div>
    <h1>Posts</h1>

    <hr>
    <ul>
        {#each posts as post}
            <li>
                <a href="/post/{post.id}">{post.id}. {post.title}</a>
            </li>
        {/each}
    </ul>
</div>
