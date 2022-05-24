import axios from 'axios'
import {ElMessage} from 'element-plus'
import router from '../router/index.js'

axios.interceptors.response.use(success => {
    console.log(success)
	if (success.data.code != 0) {            
		ElMessage.error(success.data.message)
		return;
	}
	if (success.data.message) {             
		ElMessage({
			message: success.data.message,
			type: 'success'
		})
	}
	return success.data;
}, error => {                                     
	console.log(error)	
	ElMessage.error("服务器发生未知错误，请联系管理员")
	return;
})

let base = '' 
export const postKeyValueRequest = (url, params) => {
	return axios({
		method: "post",
		url: `${base}${url}`, 
		data: params,
		transformRequest: [function(data) {
			let ret = ""
			for (let i in data) {
				ret += encodeURIComponent(i) + '=' + decodeURIComponent(data[i]) + '&'
			}
			return ret
		}],
		headers: {
			'Content-Type': 'application/x-www-form-urlencoded'
		}
	})
}
export const postRequest = (url, params) => {
	return axios({
		method: "post",
		url: `${base}${url}`, 
		data: params
	})
}

export const getRequest = (url, params) => {
	return axios({
		method: "get",
		url: `${base}${url}`, 
		data: params
	})
}

export const putRequest = (url, params) => {
	return axios({
		method: "put",
		url: `${base}${url}`, 
		data: params
	})
}

export const deleteRequest = (url, params) => {
	return axios({
		method: "delete",
		url: `${base}${url}`,
		data: params
	})
}
