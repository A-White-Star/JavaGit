import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import installElementPlus from './plugins/element'
import * as api from './utils/api.js'
import {initMenu} from './utils/menus.js'
import 'font-awesome/css/font-awesome.min.css'



router.beforeEach((to,from, next)=>{
	if(to.path=='/'){
		next()
	}else{
		if(!window.sessionStorage.getItem("user")){
			next('/')
		}else{
			initMenu(router,store)
			
			if(to.matched.length == 0){
				next('/index')
			}else{
				next()
			}
		}
	}
})
const app = createApp(App)
installElementPlus(app)
app.use(router)
app.use(store).mount('#app')

app.config.globalProperties.$api = api