<template>
    <div class="ArticleShow-Container">
        <h2 style="text-align: center;">{{ Article.title }}</h2>
        <div v-html="Article.content"></div>
    </div>
</template>

<script>
import axios from 'axios';
import '@/assets/css/articleShow.css'
export default {
    data() {
        return {
            Article: ''
        }
    },
    created() {
        this.getOne()
    },
    methods: {
        getOne() {
            const url = '/api/backstage-article/getOne'
            axios.post(`${url}?mass=${localStorage.getItem('ArticleMass')}&title=${localStorage.getItem('ArticleTitle')}`, {}, {
                headers: {
                    'verifyCode': '2024'
                }
            }).then(res => {
                this.Article = res.data
            })
        }
    }
}
</script>