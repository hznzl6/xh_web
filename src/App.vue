<template>
  <div id="app">
    <router-view/>
  </div>
</template>
<script>
import { mapState, mapMutations } from 'vuex';
export default {
  computed: {
    ...mapState(['isLogined']), // 将isLogined映射为计算属性
  },
  mounted() {
    if (localStorage.getItem("isLogined") === null) {
      localStorage.setItem("isLogined", 'false');
    } else {
      // 调用Vuex的mutation方法将isLogined值赋给state
      // this.SET_IS_LOGINED(isLogined);
    }
  },
  methods: {
    ...mapMutations(['SET_IS_LOGINED']),
  }
}
</script>

<style>
::-webkit-scrollbar {
  width: 5px;
  height: 5px;
}
::-webkit-scrollbar-track {
  background: #f1f1f1;
}
::-webkit-scrollbar-thumb {
  background: rgb(8, 85, 28);
}
* {
  -webkit-user-select: none;  /* Chrome、Safari、Opera */
  -moz-user-select: none;     /* Firefox */
  -ms-user-select: none;      /* IE 10+ */
  user-select: none;          /* 标准语法 */
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}

@media screen and (max-width: 480px) {
}
</style>
