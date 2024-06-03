<!-- <template>
    <div class="container"
      @mousedown="startDrag"
      @mousemove="drag"
      @mouseup="endDrag"
    >
      <ul>
        <li ref="card" v-show="currentPage == 1" class="item" :style="{ transform: cardTransform }">123</li>
        <li ref="card" v-show="currentPage == 2" class="item" :style="{ transform: cardTransform }">456</li>
        <li ref="card" v-show="currentPage == 3" class="item" :style="{ transform: cardTransform }">789</li>
      </ul>
    </div>
  </template>
  <script>
  export default {
    data() {
      return {
        currentPage: 1,
        isDragging: false,
        startPos: null,
        currentPos: null,
        cardTransform: '', // 卡片元素的样式属性transform
      };
    },
    methods: {
      startDrag(event) {
        this.isDragging = true;
        this.startPos = event.clientX;
      },
      drag(event) {
        if (this.isDragging) {
          this.currentPos = event.clientX;
          const diff = this.currentPos - this.startPos;
          const maxDiff = Math.max(-60, Math.min(60, diff));
          this.updateCardTransform(maxDiff);
          this.moveCardWithMouse();
        }
      },
      moveCardWithMouse() {
        const diff = this.currentPos - this.startPos;
        const cardElement = this.$refs.card;
        // 移动卡片位置
        cardElement.style.transform = `translateX(${diff}px)`;
      },
      endDrag() {
        if (this.isDragging) {
          const diff = this.currentPos - this.startPos;
          if (diff > 60) {
            this.previousPage();
          } else if (diff < -60) {
            this.nextPage();
          } else {
            this.restoreCardPosition();
          }
          this.isDragging = false;
          this.startPos = null;
          this.currentPos = null;
        }
      },
      updateCardTransform(diff) {
        this.cardTransform = `translateX(${diff}px)`;
      },
      restoreCardPosition() {
        this.cardTransform = '';
      },
      nextPage() {
        if (this.currentPage < 3) {
          this.currentPage += 1;
          this.restoreCardPosition();
        }
      },
      previousPage() {
        if (this.currentPage > 1) {
          this.currentPage -= 1;
          this.restoreCardPosition();
        }
      },
    },
  };
  </script>
  <style>
  .container {
    width: 300px;
    white-space: nowrap;
    overflow-x: hidden;
    margin: 0 auto;
  }
  .item {
    display: inline-block;
    width: 60px;
    height: 120px;
    background-color: aquamarine;
  }
  </style> -->
  <!-- <template>
    <div>
      <header class="top-header" :class="{ 'initial-bg': isInitialBg }" v-scroll="handleScroll">
        header 内容
      </header>
      <div style="height: 100vh;width: 100%;background-color: aqua;"></div>
      <div style="height: calc(100vh - 100px);width: 100%;background-color: blueviolet;">
      
      </div>
    </div>
  </template>
  <style scoped>
  .top-header {
    height: 100px;
    width: 100%;
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    z-index: 999;
  }
  .initial-bg {
    background: linear-gradient(to bottom, rgba(0, 0, 0, 0.5), rgba(194, 194, 194, 0.1));
  }
  </style>
  <script>
  export default {
    data() {
      return {
        isInitialBg: true
      };
    },
    mounted() {
      window.addEventListener('scroll', this.handleScroll);
    },
    beforeDestroy() {
      window.removeEventListener('scroll', this.handleScroll);
    },
    methods: {
      handleScroll() {
        const scrollTop = window.pageYOffset || document.documentElement.scrollTop;
        const headerHeight = 100;
        const scrollThreshold = window.innerHeight - headerHeight;
        if (scrollTop <= scrollThreshold) {
          this.isInitialBg = true;
        } else {
          this.isInitialBg = false;
        }
      }
    }
  };
  </script> -->
  <!-- <template>
    <div class="flex-container">
      <div class="news">
        <section class="section-news tl">
          <div class="tab_news">
            新闻
          </div>
        </section>
      </div>
      <div class="news">
        <section class="section-news tl">
          <div class="notice_wrap">
            通知公告
          </div>
        </section>
      </div>
      <div class="foot">
        页尾
      </div>
    </div>
  </template>
  <style scoped>
    .flex-container {
      display: flex;
      flex-wrap: wrap;
      width: 100%;
      justify-content: space-between;
    }
    .news {
      flex-basis: 100%;
      height: 400px;
    }
    .foot {
      flex-basis: 100%;
      height: 60px;
    }
    .tab_news,
    .notice_wrap {
      height: 400px;
      flex-basis: 100%;
    }
    @media (min-width: 768px) {
      .tab_news,
      .notice_wrap {
        flex-basis: 50%;
      }
    }
    @media (min-width: 1000px) {
      .tab_news {
        flex-basis: 60%;
      }
      .notice_wrap {
        flex-basis: 30%;
      }
    }
  </style> -->

  <!-- <template>
    <div style="height: 100vh;width: 100%;background-color: blueviolet;perspective: 1000px;">
      <span @click="toggleFlip">旋转</span>
      <div class="card" :class="{'card': true, 'flipped': isFlipped}">
        <div class="login">
          login
        </div>
        <div class="rigister">
          register
        </div>
      </div>
    </div>

  </template>
  <script>
  export default {
    data() {
      return {
        isFlipped: false
      }
    },
    methods: {
      toggleFlip() {
        this.isFlipped = !this.isFlipped;
      },
    }
  }
  </script>
  <style>
  .card {
    width: 60%;
    height: 20vh;
    transition: transform 0.8s;
    transform-style: preserve-3d;
    margin: 0 auto;
    position: relative;
    top: 20vh;
    background-color: aquamarine;
  }
  .login, .register {
    position: absolute;
    width: 100%;
    height: 100%;
    backface-visibility: hidden;
  }

  .login {
    transform: rotateY(0deg);
    z-index: 2;
  }
  .register {
    transform: rotateY(180deg);
    z-index: 1;
  }
  .flipped {
    transform: rotateY(180deg);
  }

  </style> -->


  <template>
  <div class="card-container">
    <div :class="{'card': true, 'flipped': isFlipped}">

      <div class="card-front">

        <button @click="toggleFlip">旋转</button>
        <el-card>123</el-card>
      </div>

      <div class="card-back">

        <button @click="toggleFlip">返回</button>
        <el-card>456</el-card>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      isFlipped: false
    };
  },
  methods: {
    toggleFlip() {
      this.isFlipped = !this.isFlipped;
    }
  }
};
</script>
<style>
.card-container {
  width: 300px;
  height: 400px;
  perspective: 1000px;
  margin: 0 auto;
}
.card {
  width: 100%;
  height: 100%;
  transition: transform 0.8s;
  transform-style: preserve-3d;
}
.card-front,
.card-back {
  position: absolute;
  width: 100%;
  height: 100%;
  backface-visibility: hidden;
}
.card-front {
  transform: rotateY(0deg);
  z-index: 2;
}
.card-back {
  transform: rotateY(180deg);
  z-index: 1;
}
.flipped {
  transform: rotateY(180deg);
}
</style>