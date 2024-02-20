<template>
    <div>
        <div class="topTitle">
            <h2 style="line-height: 60px;"><el-button style="float: right;font-size: 16px;font-weight: 400;margin-top: 10px" @click="updateIntro">更新</el-button></h2>
            <!-- <el-button style="float: right;margin-right: 50px;" @click="postArticle"></el-button> -->
        </div>
        <div class="toolBar-container">
            <Toolbar class="toolBar"
                :editor="editor"
                :defaultConfig="toolbarConfig"
                :mode="mode"
            />
        </div>
        <div>
            <div class="ArticleEdit">
                <input v-model="title" type="text" placeholder="Type Title..." class="article-title">
                <Editor
                    style="overflow-y: hidden;min-height: 800px;"
                    v-model="html"
                    :defaultConfig="editorConfig"
                    :mode="mode"
                    @onCreated="onCreated"
                />
            </div>
            <p style="margin-bottom: 12px">&copy;Ganxy|2024</p>
        </div>
    </div>
</template>

<script>
import Vue from 'vue'
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'

export default Vue.extend({
    components: { Editor, Toolbar },
    data() {
        return {
            editor: null,
            title: '',
            html: '',
            toolbarConfig: { },
            editorConfig: { placeholder: 'Type here...'},
            mode: 'default',
            resStatus: '',
            mass: ''
        }
    },
    created() {
        this.GetIntroByMass(this.$route.params.mass)
        this.mass = this.$route.params.mass
    },
    methods: {
        onCreated(editor) {
            this.editor = Object.seal(editor)
        },
        // postArticle() {
        //     const value = {
        //         author: 'Ganxy',
        //         content: this.html,
        //         mass: '互联网社',
        //         title: this.title
        //     }
        //     // console.log(value)
        //     fetch("http://localhost:40125/wenzhang-artile/post-article", {
        //         method: "POST",
        //         headers: {
        //             'Accept': 'application/json',
        //             'Content-Type': 'application/json'
        //         },
        //         body: JSON.stringify(value)
        //     })
        //     .then(res => {
        //         // console.log(res.status); // 获取状态码
        //         this.resStatus = res.status
        //     })
        //     // eslint-disable-next-line
        //     .then(res => {
        //         if(this.resStatus == "200") {
        //             this.$message({
        //                 message: '发布成功',
        //                 type: 'success'
        //             })
        //         } else if(this.resStatus == "400") {
        //             this.$message({
        //                 message: '文章已存在',
        //                 type: 'warning'
        //             })
        //         }
        //     })
        // },
        GetIntroByMass(mass) {
            const url = process.env.VUE_APP_BASE_URI + '/shetuan-Intro/intro-getIntro'
            fetch(`${url}?mass=${mass}`, {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            })
            .then(res => res.json())
            .then(res => {
                // console.log(res)
                this.title = res[0].title
                this.html = res[0].html
            })
        },
        updateIntro() {
            const url = process.env.VUE_APP_BASE_URI + '/shetuan-Intro/intro-update'
            const value = {
                mass: this.mass,
                title: this.title,
                html: this.html,
            }
            if(value.mass == '互联网社') {
                this.$message({
                    message: '这页不许动',
                    type: 'warning'
                })
                return
            }
            fetch(url, {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(value)
            })
            .then(res => res.text()) 
            .then(res => {
                console.log(res)
                this.$message({
                    message: '更新成功',
                    type: 'success'
                })
                const url = "/mass/" + this.mass
                this.$router.push(url)
            })
        },
    },
    beforeDestroy() {
        const editor = this.editor
        if (editor == null) return
        editor.destroy()
    }
})
</script>

<style src="@wangeditor/editor/dist/css/style.css"></style>

<style>
* {
    padding: 0;
    margin: 0;
}
.topTitle {
    position: fixed;
    top: 0px;
    background-color: #fff;
    width: 100%;
    height: 60px;
    z-index: 999;
}
.toolBar-container {
    width: 100%;height: 40px;
    border-top: 1px solid #ccc;
    border-bottom: 1px solid #ccc;
    top: 60px;
    position: fixed;
    z-index: 999;
    background-color: #fff;
}
.toolBar {
    border-top: 1px solid #ccc;
    border-bottom: 1px solid #ccc;
    width: 1400px;
    position: fixed;
    top: 60px;
    left: 10%;
    z-index: 999;
    background-color: #fff;
    max-height: 40px;
}
.article-title {
    width: 100%;
    font-size: 32px;
    border: none;
    outline: none;
    padding-bottom: 15px;
    font-weight: 700;
    border-bottom: 1px solid #e8e8e8;
}

.ArticleEdit {
    width: 60%;
    box-shadow: 0 2px 10px rgb(0 0 0 / 12%);
    border: 1px solid #e8e8e8;
    margin: 120px auto 60px auto;
    text-align: left;
    padding: 30px;
}
@media screen and (max-width: 480px) {
    .ArticleEdit {
        width: 80%;
    }
    
}
</style>
