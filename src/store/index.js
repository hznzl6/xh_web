import Vue from 'vue'  
import Vuex from 'vuex'  
  
Vue.use(Vuex)  

const isLogined = localStorage.getItem('isLogined') === 'true' || false;
const isHoutai = localStorage.getItem('isHoutai') === 'true' || false;
const isManager = localStorage.getItem('isManager') === 'true' || false;
const isPerfect = localStorage.getItem('isPerfect') === 'true' || false;


const isNeedLogin = localStorage.getItem('needLogin') === 'true' || false;


export default new Vuex.Store({
  state: {
    isLogined: isLogined,
    isPerfect: isPerfect,
    isHoutai: isHoutai,
    isManager: isManager,
    QQAvatar: 'http://ganxy03.cn/img/noface.png',
    isNeedLogin: isNeedLogin
  },
  getters: {
  },  
  mutations: {  
    SET_IS_LOGINED(state, value) {
      state.isLogined = value;
    },
    SET_IS_HOUTAI(state, value) {
      state.isHoutai = value;
    },
    SET_IS_MANAGER(state, value) {
      state.isManager = value;
    },
    SET_IS_PERFECT(state, value) {
      state.isPerfect = value;
    },
    SET_ISQQAVATAR(state, value) {
      state.QQAvatar = value
    },
    SET_ISNeedLogin(state, value) {
      state.isNeedLogin = value
    },
  },
  actions: {
    setQQAvatar({ commit}, value) {
      commit('SET_ISQQAVATAR', value)
    }
  },  
  modules: {  
  },  
  created() {
  }  
})