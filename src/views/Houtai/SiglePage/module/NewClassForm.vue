<template>
    <div class="newClass">
        <el-select @change="handleIput" style="margin: 3px 0;" v-model="selectValue">
            <el-option
            v-for="item in selectData"
            :key="item.value"
            :label="item.label"
            :value="item.value"></el-option>
        </el-select>
        <el-input @change="handleIput" v-model="inputValue" style="margin: 3px 0;"></el-input>
    </div>
</template>
<script>
export default {
    data() {
        return {
            selectValue: '',
            selectData: [
                {
                    label: '2020级',
                    value: '2020'
                },
                {
                    label: '2021级',
                    value: '2021'
                },
                {
                    label: '2022级',
                    value: '2022'
                },
                {
                    label: '2023级',
                    value: '2023'
                }
            ],
            inputValue: ''
        }
    },
    methods: {
        handleIput() {
            localStorage.setItem('newClassIndex', this.selectValue)
            localStorage.setItem('newClassName', this.inputValue)
        }
    }
}
</script>