<script lang="ts">
    import createClient from "openapi-fetch";
    import type { paths } from "$lib/types/api/v1/schema";
    
    const { GET } = createClient<paths>({ baseUrl: "http://localhost:8090" });

    let posts: any = $state([]);

    $effect(() => {
        GET("/api/v1/posts/mine", {
            credentials: "include"
        }).then(response => {
            posts = response.data?.data?.items;
        })
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
