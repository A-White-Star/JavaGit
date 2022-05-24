import {getRequest}from './api.js'
// import ShangPinList from '../views/shangpin/list.vue'

export const initMenu = (router, store)=>{
	if(store.state.routes.length>0){
		return
	}
	
	getRequest("/api/system/config/menu").then(data=>{
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
		let nName = route.name
		let subMenu = route.subMenu
		let icon =route.icon
		
		if(subMenu && subMenu instanceof Array && subMenu.length>0){
			subMenu=formatRoutes(subMenu)
		}
		
		let fmRouter={
			path:url,
			name:nName,
			icon:icon,
			children:subMenu,
			// component:ShangPinList
			component:()=>import('../views' + url + ".vue")
			
		}
		fmRoutes.push(fmRouter)
	})
	return fmRoutes
}