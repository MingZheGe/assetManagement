import Vue from 'vue'
import VueRouter from 'vue-router'
import bill from '../views/bill.vue'
import loan from '../views/loan.vue'
import login from '../views/login.vue'
import add from '../views/add.vue'
import register from '../views/register.vue'
import test from '../views/test.vue'
import test2 from '../views/test2.vue'
import statistic from '../views/statistic.vue'
import stock from '../views/stock.vue'
import fund from '../views/fund.vue'
import welcome from '../views/testWelcome.vue'

Vue.use(VueRouter)

const routes = [
    {
        path: '',
        redirect: '/login',
        
    },
  
    {
        path: '/bill',
        component: bill
    },
    {
        path: '/welcome',
        component: welcome
    },
    {
        path: '/fund',
        component: fund
    },
    {
        path: '/stock',
        component: stock
    },
    {
        path: '/loan',
        component: loan
    },
    {
        path: '/statistic',
        component:statistic
    },
    {
        path: '/test',
        component: test
    },
    {
        path: '/test2',
        component: test2
    },
    {
        path: '/register',
        component: register
    },
    {
        path: '/add',
        component: add
    },
    {
        path: '/login',
        component: login
    }
]

const router = new VueRouter({
    routes
})
export default router