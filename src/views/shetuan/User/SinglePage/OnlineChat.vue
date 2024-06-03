<template>
    <div class="onlinechat">
        <div style="position: fixed;width: 100%;bottom: 0;">
            <div class="input">
                <el-input
                type="textarea"
                :autosize="{ minRows: 1, maxRows: 8}"
                placeholder="请输入内容"
                v-model="input"
                resize="none">
                </el-input>
                <div class="outline"><i class="el-icon-picture-outline" style="position: absolute;bottom: 2px;right: 100px;"></i></div>
                <div class="outline"><i class="el-icon-folder-opened" style="position: absolute;bottom: 2px;right: 70px;"></i></div>
                <div class="position"><i class="el-icon-s-promotion" style="position: absolute;bottom: 2px;right: 20px;"></i></div>
                
            </div>
        </div>
        <!-- <div class="sidebar" >
            <div>
                <el-collapse v-model="activeNames" @change="handleChange">
                        <el-collapse-item  title="我的好友" name="1">
                            <div>好友1</div>
                            <div>好友2</div>
                            <div>好友1</div>
                            <div>好友2</div>
                        </el-collapse-item>
                    </el-collapse>
            </div>
            <ul style="padding: 0;">
                <li class="sidebar-list">社团通知</li>
                <li class="sidebar-list">树洞倾诉</li>
                <li class="sidebar-list">联系我们</li>
            </ul>
            
        </div> -->
    </div>
</template>
<script>

export default {
   
  data() {
    return {
      input: '',
      activeNames: ['1'],
    }
   
  },
  methods: {
      handleChange(val) {
        console.log(val);
      }
    }
}
</script>
<style>
.onlinechat .input{
    /* display: block; */
    /* display: inline-block; */
    /* position: fixed; */
    position: relative;
    bottom: 55px;
    width: 60%;
    left: calc(10% + 50px);
    /* background-color: aqua; */
    /* margin: 0 90px; */
    /* display: flex;
    justify-content: center;
    align-items: center; */
    /* background-color: #fff; */
}
.onlinechat .input i{
    margin: 0 10px;
    /* text-align: right; */
    /* display: block; */
    /* vertical-align: middle; */
}

/* .el-icon-picture-outline:before{
    position: absolute;bottom: 2px;right: 100px;width: 50px;height: 20px;
} */
.onlinechat .outline{
    font-size: 20px;
}
.onlinechat .outline:hover{
    cursor: pointer;
}
.onlinechat .position{
    font-size: 25px;
    color: rgb(27, 5, 99);
}
.onlinechat .position:hover{
    cursor: pointer;
}
.aiask .el-icon-search{
    position: absolute;
    bottom: 2px;
    right: 0;
}
.onlinechat #story{
    /* 去除边框 */
border: none;
resize: none;
/* 去除选中后的边框 */
outline:none;
/* margin: 0 auto; */
/* padding:0 auto; */
padding: 12px;
}
 textarea {
    /* bottom: 10px; */
    /* margin-top: 10px; */
    font-size: 16px;
    max-height: 150px;
    overflow: hidden;
    width: 100%;
    padding: 0;
    margin: 0;
    border: none;
  }
  .sidebar{
    height: calc(99.8vh - 100px - 50px);
    width: 15%;
    float: right;
    /* background-color: aqua; */
  }
  li{
    list-style: none;
  }
  .sidebar-list{
    height: 40px;
    width: 99%;
    background-color: #bab6b6;
    /* border: 1px solid #838080; */
    /* border-radius: 10px; */
  }
  .sidebar-list:hover{
    background-color: #838383;
  }
  .el-collapse-item__header {
    text-align: center;
    width: 99%;
    background-color: #bab6b6;
    /* border: 1px solid #838080; */
    border-bottom:none;
  }
  .el-collapse-item__header:hover{
    background-color: #838383;
  }
  .el-collapse-item__content{
    background-color: #bab6b6;
    overflow-y: scroll;
    height: 40px;
  }

  
/* .aiask{
    background-color: #fff;
} */
</style>