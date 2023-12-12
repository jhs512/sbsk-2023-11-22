<script lang="ts">
    import '$lib/app.css';
	import rq from '$lib/rq/rq.svelte';
    
    const {children}: any = $props();

    $effect(() => {
        rq.initAuth();
    });
</script>

<header>
    <ul>
        <li><a href="/">홈</a></li>
        {#if rq.isLogout()}
        <li><a href="/member/login">로그인</a></li>
        {/if}
        {#if rq.isLogin()}
        <li><a on:click|preventDefault={() => rq.logout()}>로그아웃</a></li>
        <li><a href="/post/myList">내글</a></li>
        {/if}
    </ul>

    <hr class="my-5">

    <div>    
        <h2>id : {rq.getMember()?.id}</h2>
        <h2>createDate : {rq.getMember()?.createDate}</h2>
        <h2>updateDate : {rq.getMember()?.modifyDate}</h2>
        <h2>username : {rq.getMember()?.username}</h2>
        <h2>authorities : {rq.getMember()?.authorities}</h2>
    </div>
</header>

<hr class="my-5">

<main>
    {@render children()}
</main>
