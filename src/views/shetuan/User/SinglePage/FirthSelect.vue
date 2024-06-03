<template>
    <div class="firstselect">
        <el-row class="box">    
            <el-col :span="24" class="timu"  style="overflow-y: scroll;">
                <SelectContent />
            </el-col>
        </el-row>
    </div>
</template>
<script>
import SelectContent from '@/views/shetuan/User/SinglePage/SelectContent.vue'
export default {
  components: {
    SelectContent
    },
  data() {
    return {
      
      
      qusNum:[
        {
            id:1,
            active: '',
        },
        {
            id:2,
        },
        {
            id:3,
        },
        {
            id:4,
        },
        {
            id:5,
        },
        
        {
            id:6,
        },
        {
            id:7,
        },
        {
            id:8,
        },
        {
            id:9,
        },
        {
            id:10,
        },  
      ],
      
      currentNumber: 1

    }
  },
  methods: {
    select(id) {
      console.log(id)
      this. currentNumber = id

    },
    linkToIndex(id) {
      console.log(id)
    }
  },
}
</script>

<style>


.box{
  width: 100%;
  height: calc(99.8vh - 100px - 50px);  /* background-color: aqua; */
  border-radius: 4px;
  border: 1px solid #EBEEF5;
  margin: 0 auto;

}

</style>