<template>
    <div class="Manager-MassArticle">
        <h3>文章管理</h3>
        <div class="Manager-MassArticle_PC">
            <div class="Manager-MassArticle_TableArticleData">
                <el-table
                :data="ArticleData"
                height="calc(100vh - 204px)"
                stripe>
                    <el-table-column
                    label="id"
                    prop="id"></el-table-column>
                    <el-table-column
                    label="标题"
                    prop="title"></el-table-column>
                    <el-table-column
                    label="发布者"
                    prop="author"></el-table-column>
                    <el-table-column
                    label="浏览量"
                    prop="hits"></el-table-column>
                    <el-table-column
                    label="发布时间"
                    prop="create_time"></el-table-column>
                    <el-table-column
                    label="更新时间"
                    prop="update_time"></el-table-column>
                    <el-table-column
                    label="状态"
                    prop="status">
                    <template slot-scope="scope">
                        <el-tag v-if="scope.row.status === 0" type="info">审核中</el-tag>
                        <el-tag v-if="scope.row.status === 1" type="success">通过</el-tag>
                        <el-tag v-if="scope.row.status === 2" type="danger">驳回</el-tag>
                    </template>
                    </el-table-column>
                    <el-table-column
                    label="操作"></el-table-column>
                </el-table>
            </div>
            <div class="Manager-MassArticle_TableArticlePost">
                <div style="display: flex;align-items: center;">
                    <el-input v-model="ArticleForm.title" placeholder="输入文章标题"></el-input>
                    <el-button @click="ArticlePostBtn">发布</el-button>
                </div>
                <editor
                placeholder="Type here..."
                v-model="ArticleForm.content"
                api-key="ublhvveejzddur1ry6jxrqizz5urb3ri8w38goz9kskcr0mp"
                :init="{
                    toolbar: 'undo redo | blocks fontfamily fontsize | bold italic underline strikethrough | link image media table mergetags | align lineheight | tinycomments | checklist numlist bullist indent outdent | emoticons charmap | removeformat',
                    plugins: [
                        'advlist', 'autolink', 'lists', 'link', 'image', 'charmap', 'preview',
                        'anchor', 'searchreplace', 'visualblocks', 'code', 'fullscreen',
                        'insertdatetime', 'media', 'table', 'help', 'wordcount'
                    ],
                    content_style: 'body { font-family:Helvetica,Arial,sans-serif; font-size:18px }',
                    menubar: 'file edit view insert format about tools table help',
                    directionality: 'ltr',
                    selector: 'textarea',
                    language: 'zh_CN'
                    }"
                />
            </div>
        </div>
        <div class="Manager-MassArticle_Move">
            <div class="Manager-MassArticle_TableArticlePost">
                <div style="display: flex;justify-content: space-between;">
                    <el-input placeholder="输入文章标题"></el-input>
                    <el-button @click="ArticlePostBtn">发布</el-button>
                </div>
                <editor
                placeholder="Type here..."
                v-model="ArticleForm.content"
                api-key="ublhvveejzddur1ry6jxrqizz5urb3ri8w38goz9kskcr0mp"
                :init="{
                    toolbar: 'undo redo | blocks fontfamily fontsize | bold italic underline strikethrough | link image media table mergetags | align lineheight | tinycomments | checklist numlist bullist indent outdent | emoticons charmap | removeformat',
                    plugins: [
                        'advlist', 'autolink', 'lists', 'link', 'image', 'charmap', 'preview',
                        'anchor', 'searchreplace', 'visualblocks', 'code', 'fullscreen',
                        'insertdatetime', 'media', 'table', 'help', 'wordcount'
                    ],
                    content_style: 'body { font-family:Helvetica,Arial,sans-serif; font-size:18px }',
                    menubar: 'file edit view insert format about tools table help',
                    directionality: 'ltr',
                    selector: 'textarea',
                    language: 'zh_CN'
                    }"
                />
            </div>
            <div class="Manager-MassArticle_TableArticleData">
                <el-table
                :data="ArticleData"
                style="height: calc(100vh - 204px);overflow-y: auto;width: 100%;margin: 0;"
                stripe>
                    <el-table-column
                    label="id"></el-table-column>
                    <el-table-column
                    label="标题"
                    prop="title"></el-table-column>
                    <el-table-column
                    label="发布者"></el-table-column>
                    <el-table-column
                    label="浏览量"></el-table-column>
                    <el-table-column
                    label="发布时间"></el-table-column>
                    <el-table-column
                    label="更新时间"></el-table-column>
                    <el-table-column
                    label="状态"></el-table-column>
                    <el-table-column
                    label="操作"></el-table-column>
                </el-table>
            </div>
            
        </div>
    </div>
</template>
<script>
import Editor from '@tinymce/tinymce-vue'
export default {
    components: {
        'editor': Editor
    },
    data() {
        return {
            ArticleForm: {
                title: '',
                content: '',
                author: 'Ganxy',
                // mass: '互联网社'
                mass: this.$route.params.mass,
            },
            dialogFormPost: false,
            content: '',
            ArticleData: []
        }
    },
    created() {
        this.getAllArticleByMass()
    },
    methods: {
        getAllArticleByMass() {
            const url = process.env.VUE_APP_BASE_URI + '/wenzhang-artile/getAll-ByMass'
            // const mass = '互联网社'
            const mass = this.$route.params.mass
            fetch(`${url}?mass=${mass}`, {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
            })
            .then(res => res.json())
            .then(res => {
                this.ArticleData = res
            })
        },
        ArticlePostBtn() {
            // console.log(this.ArticleForm)
            
            const url = process.env.VUE_APP_BASE_URI + '/wenzhang-artile/post-article'
            fetch(url, {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(this.ArticleForm)
            })
            .then(res => res.text())
            .then(res => {
                if(res == "Post success") {
                    this.$message({
                        message: '发布成功',
                        type: 'success'
                    })
                    setTimeout(function(){
                        location.reload()
                    }, 1000)
                } else if(res == 'Update success') {
                    this.$message({
                        message: '更新成功',
                        type: 'success'
                    })
                    setTimeout(function(){
                        location.reload()
                    }, 1000)
                }
            })
        }
    }
}
</script>
<style>
.Manager-MassArticle {
    position: relative;
    margin: 24px 24px;
    min-height: calc(100vh - 164px);
    background-color: rgb(255, 255, 255, 0.6);
}
.Manager-MassArticle .Manager-MassArticle_PC {
    width: 100%;
    display: block;
}
.Manager-MassArticle .Manager-MassArticle_Move {
    width: 100%;
    display: none;
}
.Manager-MassArticle_TableArticlePost .tox {
    min-height: calc(100vh - 244px);
}
.Manager-MassArticle .Manager-MassArticle_TableArticleData {
    position: absolute;
    left: 0;
    width: 50%;
    min-height: calc(100vh - 164px);
    /* background-color: rgba(196, 33, 33, 0.6); */
}
.Manager-MassArticle .Manager-MassArticle_TableArticlePost {
    position: absolute;
    right: 0;
    width: calc(50% - 20px);
    min-height: calc(100vh - 164px);
    border-radius: 5px;
    /* background-color: rgba(255, 255, 255, 0.6); */
}
.tox-statusbar__branding a {
    display: none;
}
@media screen and (max-width: 480px) {
    .Manager-MassArticle  .Manager-MassArticle_PC {
        display: none;
    }
    .Manager-MassArticle .Manager-MassArticle_Move {
        display: block;
    }
    .Manager-MassArticle .Manager-MassArticle_Move .Manager-MassArticle_TableArticlePost {
        position: relative;
        left: 0;
        width: 100%;
    }
    .Manager-MassArticle .Manager-MassArticle_Move .Manager-MassArticle_TableArticleData {
        right: 0;
        width: 100%;
        margin-top: 15px;
    }
    
    .Manager-MassArticle_TableArticlePost .tox .tox-menubar {
        display: flex;
    }
}
</style>