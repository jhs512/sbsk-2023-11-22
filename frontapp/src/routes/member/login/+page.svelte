<script lang="ts">
    import "toastr/build/toastr.css"
    import toastr from "toastr";
    import createClient from "openapi-fetch";
    import type { paths } from "$lib/types/api/v1/schema";

    const { POST } = createClient<paths>({ baseUrl: "http://localhost:8090" });

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

        POST("/api/v1/members/login", {
            credentials: 'include',
            body: {
                username: form.username.value,
                password: form.password.value
            }
        })
        .then(response => {
            const data = response.data;
            const error = response.error;

            if ( data?.msg ) {
                toastr.info(data?.msg);
            }
        });
    }
</script>

<form on:submit|preventDefault={submitLoginForm}>
    <input type="text" name="username" placeholder="Username" />
    <input type="password" name="password" placeholder="Password" />
    <button type="submit">Login</button>
</form>