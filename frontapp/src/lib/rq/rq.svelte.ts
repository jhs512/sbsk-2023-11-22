import createClient from "openapi-fetch";
import type {components, paths} from "$lib/types/api/v1/schema";
import "toastr/build/toastr.css"
import toastr from "toastr";

class Rq {
    private member: components["schemas"]["MemberDto"] | null;

    constructor() {
        this.member = null;
    }

    public getMember() {
        return this.member;
    }

    public msgInfo(message: string) {
        toastr.info(message);
    }

    public msgError(message: string) {
        toastr.error(message);
    }

    public apiEndPoints() {
        return createClient<paths>({ baseUrl: "http://localhost:8090" });
    }

    public setLogined(member: components["schemas"]["MemberDto"]) {
        this.member = member;
    }

    public isLogin() {
        return this.member !== null;
    }

    public isLogout() {
        return !this.isLogin();
    }

    public initAuth() {
        this.apiEndPoints().GET("/api/v1/members/me", {
            credentials: "include"
        })
            .then((response) => {
                const data = response.data;
                const error = response.error;

                if ( data ) {
                    console.log(data.data?.item);
                    this.setLogined(data.data?.item ?? {});
                }
            });
    }
}

const rq = new Rq();

export default rq;