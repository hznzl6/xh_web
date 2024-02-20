<template>
    <div style="width: 100%;" v-loading="loading">
        <div class="articleTitle">
            {{ this.articleTitle }}
        </div>
        <div class="articleContent">
            <div v-html="this.articleContent"></div>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            articleTitle: '',
            articleContent: '',
            articleAuthor: '',
            articleHits: '',
            create_time: '',
            update_time: '',
            loading: true
        }
    }, 
    created() {
        this.getArticle(this.$route.params.sign)
        // setTimeout(() => {
            
        //     this.loading = false
        // }, 1500);
        
        // this.articleTitle = localStorage.getItem("title")
        // this.articleContent = localStorage.getItem("content")
    },
    methods: {
        getArticle(sign) {
            
            setTimeout(() => {
                fetch(process.env.VUE_APP_BASE_URI + `/wenzhang-artile/getOneArticle?sign=${sign}`, {
                    method: "POST",
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    }
                })

                .then(res => res.json())
                .then(res => {
                    this.articleTitle = res.title
                    this.articleContent = res.content
                    // localStorage.setItem("author", res.author)
                    // localStorage.setItem("content", res.content)
                    // localStorage.setItem("create_time", res.create_time)
                    // localStorage.setItem("hits", res.hits)
                    // localStorage.setItem("mass", res.mass)
                    // localStorage.setItem("title", res.title)
                    // localStorage.setItem("update_time", res.update_time)
                    this.loading = false
                })
                .catch(error => {
                    console.error('Error fetching article:', error)
                    this.$router.push('/404')
                })
            }, 1200);
            
        }
    }
}
</script>

<style>
.articleContent {
    width: 1280px;
    margin: 0 auto;
    text-align: left;
}
.articleContent img {
    width: 100%;
}
.el-loading-mask {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}
</style>