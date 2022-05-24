let proxyObj ={}
proxyObj['/api']={
	ws:false,
	target:'http://localhost:9999',
	changeOrigin:true,
	pathRewrite:{
		'^/api':'/',
		// /api/xxxx=>/xxxx
	}
}

module.exports={
	devServer:{
		host:'localhost',
		port:8080,
		proxy:proxyObj
	}
}