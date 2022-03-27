import {
	createRouter,
	createWebHistory
} from 'vue-router'
import Login from '../views/Login.vue'
import Index from '../views/index.vue'
// import ShangPinAdmin from '../views/index.vue'
// import ShangPinList from '../views/shangpin/list.vue'
// import ShangPinAdd from '../views/shangpin/add.vue'
// import DingDanAdmin from '../views/index.vue'
// import DingDanList from '../views/dingdan/list.vue'
// import DingDanReport from '../views/dingdan/report.vue'
// import DingDanAdd from '../views/dingdan/add.vue'



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
	// {	//商品管理菜单
	// 	path: '/index',
	// 	name: '商品管理',
	// 	component: ShangPinAdmin,
	// 	hidden: false,
	// 	children: [{
	// 			path: '/shangpin/list',
	// 			name: '商品列表',
	// 			component: ShangPinList
	// 		},
	// 		{
	// 			path: '/shangpin/add',
	// 			name: '添加商品',
	// 			component: ShangPinAdd
	// 		}
	// 	]
	// },
	// {   //订单管理
	// 	path: '/index',
	// 	name: '订单管理',
	// 	component: DingDanAdmin,
	// 	hidden: false,
	// 	children: [{
	// 			path: '/dingdan/list',
	// 			name: '订单查询',
	// 			component: DingDanList
	// 		},
	// 		{
	// 			path: '/dingdan/report',
	// 			name: '订单报表',
	// 			component: DingDanReport
	// 		},
	// 		{
	// 			path: '/dingdan/add',
	// 			name: '添加新订单',
	// 			component: DingDanAdd
	// 		}]
	// }
]

const router = createRouter({
	mode:'history',
	history: createWebHistory(process.env.BASE_URL),
	routes
})

export default router
