import {
	createRouter,
	createWebHistory
} from 'vue-router'
import Login from '../views/Login.vue'
import Index from '../views/index.vue'


const routes = [{
		path: '/',
		name: 'Login',
		component: Login,
		hidden: true
	},
	{
		path: '/index',
		name: 'index',
		component: Index,
		hidden: true
	 }
]

const router = createRouter({
	mode:'history',
	history: createWebHistory(process.env.BASE_URL),
	routes
})

export default router
