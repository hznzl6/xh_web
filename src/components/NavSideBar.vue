<template>
    <div class="navSideBar">
        <div class="Container_rightSideBar">
            <div class="nav_list">
                <ul class="nav_content">
                    <li @click="activate(item.name)" class="nav_contentItem" :class="{'active': changeNav(item.name)}" v-for="item in NavItem" :key="item.name">{{item.name}}</li>
                </ul>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            NavItem: [
                {
                    name: '部门简介'
                },
                {
                    name: '部门设置'
                },
                {
                    name: '联系我们'
                }
            ],
        }
    },
    methods: {
        changeNav(name) {
            return this.isactiveNav === name
        },
        activate(name) {
            this.isactiveNav = name
            this.$router.push("/bmgk/"+name)
            location.reload()
        }
    }
}

</script>

<style>
.navSideBar .Container_rightSideBar {
    position: relative; /* 添加相对定位 */
    height: 220px;
    width: 100%;
    background: url(@/assets/img/banner/sidebar.jpg);
    background-size: cover;
}
.nav_list {
    overflow: hidden;
    z-index: 6; 
    margin-top: -30px;
    min-height: 400px;
    position: relative;
}
.nav_list::before {
    content: '';
    position: absolute;
    transform: skewY(-5deg);
    bottom: 0;
    top: 200px;
    right: 0;
    width: 100%;
    height: 500px;
    background: url(@/assets/img/banner/line.jpg) 50% repeat-y;
    background-size: contain;
    z-index: 0;
}
.nav_content {
    position: absolute;
    top: 222px;
    left: 0;
    right: 0;
    bottom: 0;
    color: #fff;
    text-align: left;
}
.nav_contentItem {
    font-size: 22px;
    height: 30px;
    padding: 12px 12px;
    list-style: none;
    cursor: pointer;
    border-top: 1px solid rgba(255,255,255,.1);
} 
</style>