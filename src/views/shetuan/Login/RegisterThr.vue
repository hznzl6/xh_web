<template>
    <div class="stepsdemo-content">
        <p>学号:</p>
        <el-input disabled v-model="sno" clearable placeholder="输入学号" maxlength="13">

        </el-input>
        <p>姓名:</p>
        <el-input disabled v-model="name" clearable placeholder="输入姓名" maxlength="6">

        </el-input>
        <p>性别:</p>
        <div>
            <el-radio disabled v-model="sex" label="男">男</el-radio>
            <el-radio disabled v-model="sex" label="女">女</el-radio>
        </div>
        <p>班级:</p>
        <el-select disabled v-model="belong" filterable placeholder="请选择">
            <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
            </el-option>
        </el-select>
    </div>
</template>
<script>
export default {
    data() {
        return {
            sno: localStorage.getItem('sno'),
            name: localStorage.getItem('name'),
            sex: localStorage.getItem('sex'),
            belong: localStorage.getItem('belong'),
            options: [
                {
                value: '选项1',
                label: '黄金糕'
                }, {
                value: '选项2',
                label: '双皮奶'
                }, {
                value: '选项3',
                label: '蚵仔煎'
                }, {
                value: '选项4',
                label: '龙须面'
                }, {
                value: '选项5',
                label: '北京烤鸭'
                }
            ]
        }
    }
}
</script>

<style>

</style>