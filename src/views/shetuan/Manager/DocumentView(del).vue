<template>
    <div class="Manager-Document">
        <div class="Manager-Document_leftMenu">
            <ul class="Manager-Document_leftMenu_MenuBar">
                <li @click="fetchMdFile(item.code)" v-for="item in MenuBar" :key="item.name" class="Manager-Document_leftMenu_MenuItem">
                    <span>{{ item.name }}</span>
                </li>
            </ul>
        </div>
        <div class="Manager-Document_rightWord">
            <div class="MD" v-html="parsedContent"></div>
        </div>
    </div>
</template>
<script>
import marked from 'marked';
import hljs from 'highlight.js';
import 'highlight.js/styles/github-dark.css';
export default {
  data() {
    return {
        MenuBar: [
            {
                name: '介绍',
                code: '01-intro'
            },
            {
                name: '社团资料管理',
                code: '02-massIntro'
            },
            {
                name: '社员入社审核',
                code: '03-memberCheck'
            },
            {
                name: '社员成员信息管理',
                code: '04-memberInfos'
            },
            {
                name: '活动发布管理',
                code: '05-activityPost'
            },
            {
                name: '学时审核',
                code: '06-creditCheck'
            },
            {
                name: '社团宣传和新闻动态',
                code: '07-newsPost'
            }
        ],
        content: '', // 这里可以是你的md文件内容
        parsedContent: ''
    };
  },
  mounted() {
    this.fetchMdFile("01-intro");
  },
  methods: {
    // async fetchMdFile() {
    //     // const response = await fetch('./MarkDown/Document/01-Introduce.md');
    //     const response = await fetch('./MarkDown/Document/01.md');
    //     this.content = await response.text();
    //     marked.setOptions({
    //     highlight: function (code, language) {
    //         const validLanguage = language && hljs.getLanguage(language) ? language : 'plaintext';
    //         const highlightedCode = hljs.highlight(validLanguage, code).value;
    //         return `<pre><code class="hljs ${validLanguage}">${highlightedCode}</code></pre>`;
    //     }
    //     });
    //     this.parsedContent = marked(this.content);
    // }
    async fetchMdFile(menuCode) {
        const response = await fetch(`./MarkDown/Document/${menuCode}.md`);
        this.content = await response.text();
        marked.setOptions({
            highlight: function (code, language) {
                const validLanguage = language && hljs.getLanguage(language) ? language : 'plaintext';
                const highlightedCode = hljs.highlight(validLanguage, code).value;
                return `<pre><code class="hljs ${validLanguage}">${highlightedCode}</code></pre>`;
            }
        });
        this.parsedContent = marked(this.content);
    }
  }
};
</script>

<style>
.Manager-Document {
    position: relative;
    margin: 24px 24px;
    min-height: calc(100vh - 164px);
    background-color: rgb(255, 255, 255, 0.6);
}
.Manager-Document_leftMenu {
    position: absolute;
    left: 0;
    width: 20%;
    min-height: calc(100vh - 164px);
    border-right: 1px solid #e5e9ef;
    /* background-color: rgba(196, 33, 33, 0.6); */
}
.Manager-Document_leftMenu_MenuBar {
    padding: 6px;
}
.Manager-Document_leftMenu_MenuItem {
    color: #9a9a9a;
    margin: 12px 0;
    list-style: none;
    background-color: rgb(255, 255, 255, 0.6);
    cursor: pointer;
    border-radius: 5px;
}
.Manager-Document_leftMenu_MenuItem:hover {
    background-color: #f6f6f6;
}


.Manager-Document_rightWord {
    position: absolute;
    right: 0;
    width: calc(80% - 1px);
    height: calc(100vh - 164px);
    border-radius: 5px;
    overflow-y: auto;
    /* background-color: rgba(255, 255, 255, 0.6); */
}
.MD {
    text-align: left;
    margin: 0 20px;
}
.MD img {
    width: 100%;
}
/* .MD h1, h2, h3, h4, h5, h6 {
    text-align: center;
} */
.MD h2 {
    text-align: center;
}

@media screen and (max-width: 480px) {
    .Manager-Document_leftMenu {
        width: 100px;
    }
    .Manager-Document_rightWord {
        width: calc((100% - 101px));
    }
    .Manager-Document_leftMenu_MenuItem  {
        font-size: 13px;
    }
}
</style>