<template>
    <div>
        <div v-if="!isView" class="MassList-Conatainer">
            <div @click="viewIntro(club)" class="Mass-Card" v-for="club in clubs" :key="club.id">
                <div class="Card-Image" :style="{ backgroundImage: 'url(' + club.image + ')' }"></div>
                <div class="Club-Name">{{ club.mass }}</div>
            </div>
        </div>
        
        <div v-else>
            <MassIntro />
        </div>
    </div>
</template>

<style>
.MassList-Conatainer {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-around;
    width: calc(100% - 24px);
    margin: 12px;
    overflow-x: hidden;
    overflow-y: hidden;
}
.Mass-Card {
    width: calc(33.33% - 16px);
    margin: 8px;
    text-align: center;
    position: relative;
    cursor: pointer;
    overflow: hidden;
    transition: transform 0.3s;
    background-color: rgb(200, 242, 228);
}
@media (max-width: 768px) {
    .Mass-Card {
        width: calc(50% - 16px);
    }
}
.Mass-Card:hover {
    transform: scale(1.05);
}
.Card-Image {
    width: 100%;
    height: 200px;
    background-size: cover;
    background-position: center;
    transition: transform 0.5s;
}
.Club-Name {
    padding: 12px 0;
    font-size: 18px;
}
</style>
<script>
import axios from 'axios';
import MassIntro from './module/MassIntro.vue';

export default {
    components: {
        MassIntro
    },
    data() {
        return {
            clubs: [
                // { id: 1, name: '社团1', image: 'http://ganxy03.cn/img/background-image.jpg' },
            ],
            isView: false
        };
    },
    created() {
        this.getAll()
    },
    methods: {
        getAll() {
            const url = '/api/backstage-massList/getAll'
            axios.post(`${url}`, {}, {
                headers: {
                    'verifyCode': '2024'
                }
            }).then(res => {
                this.clubs = res.data
                console.log(res.data)
            })
        },
        viewIntro(item) {
            console.log(item)
            localStorage.setItem('mass', item.mass)
            this.isView = true
        }
    }
}
</script>