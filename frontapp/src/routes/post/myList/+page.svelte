<script lang="ts">
	import rq from '$lib/rq/rq.svelte';
	import type { components } from '$lib/types/api/v1/schema';
	import { onMount } from 'svelte';

	let posts: components['schemas']['PostDto'][] = $state([]);

	onMount(async () => {
		const { data, error } = await rq.apiEndPoints().GET('/api/v1/posts/mine', {
			credentials: 'include'
		});

		if (data) posts = data.data.items;
	});
</script>

<div>
	<h1>Posts</h1>

	<ul>
		{#each posts as post}
			<li>
				<a href="/post/{post.id}">{post.id}. {post.title}</a>
			</li>
		{/each}
	</ul>
</div>
