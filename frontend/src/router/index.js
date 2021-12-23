import Vue from 'vue'
import Router from 'vue-router'
import lregister from '@/components/lregister'
import main from '@/components/main'

Vue.use(Router)

export default new Router({
    routes: [
        {
            path: "/",
            redirect: {
                name: "lregister"
            }
        },
        {
            path: '/lregister',
            name: 'lregister',
            component: lregister,
            props: true
        },
    
        {
            path: '/main',
            name: 'main',
            component: main,
            props: true
        }
    ]
})