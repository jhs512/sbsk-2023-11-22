<script lang="ts">
    import rq from "$lib/rq/rq.svelte";
    import type { components } from "$lib/types/api/v1/schema";

    let posts: components["schemas"]["PostDto"][] = $state([]);

    $effect(() => {
        rq.apiEndPoints().GET("/api/v1/posts/mine", {
            credentials: "include"
        }).then(response => {
            posts = response.data?.data?.items ?? [];
        })
    });
</script>

<div>
    <h1>Posts</h1>

    <ul>
        {#each posts as post}
            <li>
                <a href="/post/{post.id}">{post.createDate}. {post.title}</a>
            </li>
        {/each}
    </ul>
</div>
