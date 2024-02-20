<template>
  <div class="about">
    <ArticleEdit />
    <ShowArticle />
  </div>
</template>
<script>
// eslint-disable-next-line
import ArticleEdit from './ArticleEdit.vue';
import ShowArticle from './ShowArticle.vue';
export default {
  components: {
    // eslint-disable-next-line
    ArticleEdit,
    ShowArticle
  }
}
</script>
