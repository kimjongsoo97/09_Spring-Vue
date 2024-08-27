import {defineProps} from "vue";

export default [
    {
        path:'/auth/login',
        name:'login',
        component:()=> import('../pages/auth/LoginPage.vue'),
    },
    {
        path:'/auth/join',
        name:'join',
        component:()=> import('../pages/auth/JoinPage.vue'),
    },
    {
        path:'/auth/profile',
        name:'profile',
        component:()=> import('../pages/auth/ProfilePage.vue')
    },
    {
        path:'/auth/changePassword',
        name:'changePassword',
        component:()=> import('../pages/auth/ChangePasswordPage.vue')
    },


];
