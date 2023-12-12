import createClient from "openapi-fetch";
import type {components, paths} from "$lib/types/api/v1/schema";
import "toastr/build/toastr.css"
import toastr from "toastr";
import { goto } from '$app/navigation';

class Rq {
    private member: components["schemas"]["MemberDto"];

    constructor() {
        let id = $state(0);
        let username = $state('');
        let createDate = $state('');
        let modifyDate = $state('');
        let authorities: string[] = $state([]);

        this.member = {
            get id() {
                return id;
            },
            set id(value: number) {
                id = value
            },
            get createDate() {
                return createDate;
            },
            set createDate(value: string) {
                createDate = value
            },
            get modifyDate() {
                return modifyDate;
            },
            set modifyDate(value: string) {
                modifyDate = value
            },
            get username() {
                return username;
            },
            set username(value: string) {
                username = value
            },
            get authorities() {
                return authorities;
            },
            set authorities(value: string[]) {
                authorities = value
            },
        };
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
        this.member.id = member.id;
        this.member.createDate = member.createDate;
        this.member.modifyDate = member.modifyDate;
        this.member.username = member.username;
        this.member.authorities = member.authorities;
    }

    public setLogout() {
        this.member.id = 0;
        this.member.createDate = "";
        this.member.modifyDate = "";
        this.member.username = "";
        this.member.authorities = [];
    }

    public isLogin() {
        return this.member.id !== 0;
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
                    this.setLogined(data.data?.item ?? {});
                }

                this.checkAuth();
            });
    }

    public logout() {
        this.apiEndPoints().POST("/api/v1/members/logout", {
            credentials: "include"
        })
            .then((response) => {
                const data = response.data;
                const error = response.error;

                this.setLogout();

                this.goto("/");
            });
    }

    public goto(path: string) {
        goto(path);
    }

    public iAmOnTheLogoutNeedPage() {
        const needLoginPagePaths = [
            "/member/login",
            "/member/join",
        ];

        return needLoginPagePaths.includes(window.location.pathname);
    }

    public iAmOnTheLoginNeedPage() {
        const needLoginPagePaths = [
            "/post/myList",
        ];

        return needLoginPagePaths.includes(window.location.pathname);
    }

    public checkAuth() {
        if ( this.isLogin() ) {
            const needToGoMainPage = this.iAmOnTheLogoutNeedPage();

            if ( needToGoMainPage ) {
                this.msgError("로그아웃 후 이용이 가능한 페이지입니다.");
                goto("/");
            }
        }
        else {
            const needToGoLoginPage = this.iAmOnTheLoginNeedPage();

            if ( needToGoLoginPage ) {
                this.msgError("로그인이 필요합니다.");
                goto("/member/login");
            }
        }
    }
}

const rq = new Rq();

export default rq;