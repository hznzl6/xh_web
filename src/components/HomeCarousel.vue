<template>
    <div class="homeCarousel-Container">
        <!-- <div id="slide">
            <div class="item" v-for="(item, index) in 4" :key="index">{{ item }}</div>
        </div> -->
        <div class="button">
            <!-- <div class="s-button" @click="moveFirstItem" >&lt;</div>
            <div class="s-button" @click="moveLastItem">&gt;</div> -->
        </div>
    </div>
</template>
<script>
export default {
    data() {
        return {
        };
    },
    methods: {
        moveFirstItem() {
        let lists = document.querySelectorAll('.item');
        document.querySelector('#slide').appendChild(lists[0]);
        },
        moveLastItem() {
        let lists = document.querySelectorAll('.item');
        document.querySelector('#slide').prepend(lists[lists.length - 1]);
        }
    }
};
</script>
<style>
.homeCarousel-Container {
    width: 70%;
    height: 160px;
    margin: 0 auto;
    background-color: rgba(255, 255, 255, 0.1);
}
.homeCarousel-Container .button{
  width: 100%;
  bottom: 50px;
  /* margin-top: 300px; */
  position: relative;
  text-align:center
}
</style>
    