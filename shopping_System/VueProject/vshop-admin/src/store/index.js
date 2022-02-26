import {createStore} from 'vuex'

const store = createStore({
  state () {
    return {
      routes: []
    }
  },
  mutations: {
    initRoutes(state,data){
		state.routes=data;
	}
  }
})

export default store