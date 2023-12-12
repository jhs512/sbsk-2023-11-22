<script lang="ts">
    import rq from '$lib/rq/rq.svelte';

    function submitLoginForm(this: HTMLFormElement) {
        const form: HTMLFormElement = this;

        form.username.value = form.username.value.trim();

        if (form.username.value.length === 0) {
            alert('Username is required');
            form.username.focus();
            return;
        }

        form.password.value = form.password.value.trim();

        if (form.password.value.length === 0) {
            alert('Password is required');
            form.password.focus();
            return;
        }

        rq.apiEndPoints().POST("/api/v1/members/login", {
            credentials: 'include',
            body: {
                username: form.username.value,
                password: form.password.value
            }
        })
        .then(response => {
            const data = response.data;
            const error: any = response.error;

            if ( data ) {
                rq.msgInfo(data?.msg ?? '로그인 성공');
                rq.setLogined(data.data?.item ?? {});
                rq.goto('/');
            }
            else if ( error ) {
                rq.msgError(error.message);
            }
        });
    }
</script>

<form on:submit|preventDefault={submitLoginForm}>
    <input type="text" name="username" placeholder="Username" />
    <input type="password" name="password" placeholder="Password" />
    <button type="submit">Login</button>
</form>