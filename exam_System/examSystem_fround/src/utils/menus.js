import {getRequest}from './api.js'
// import ShangPinList from '../views/shangpin/list.vue'

export const initMenu = (router, store)=>{
	if(store.state.routes.length>0){
		return
	}
	
	getRequest("/api/system/config/caidan").then(data=>{
		let respData = data.responseData
		if(respData){
			let fmtRoutes = formatRoutes(respData)
			for(let x of fmtRoutes){
				router.addRoute(x)
			}
			//router.addRoute(fmtRoutes)
			store.commit('initRoutes',fmtRoutes)
		}
	})
}

export const formatRoutes = (routes)=>{
	//console.log(routes)
	let fmRoutes=[]
	routes.forEach(route=>{
		let url = route.url
		let mingCheng = route.mingCheng
		let ziCaiDan = route.ziCaiDan
		let icon =route.icon
		
		if(ziCaiDan && ziCaiDan instanceof Array && ziCaiDan.length>0){
			ziCaiDan=formatRoutes(ziCaiDan)
		}
		
		let fmRouter={
			path:url,
			name:mingCheng,
			icon:icon,
			children:ziCaiDan,
			// component:ShangPinList
			component:()=>import('../views' + url + ".vue")
			
		}
		fmRoutes.push(fmRouter)
	})
	return fmRoutes
}