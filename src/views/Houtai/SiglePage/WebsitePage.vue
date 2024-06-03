<template>
    <div>
        <el-table
        stripe
        class="table">
            <el-table-column
            label="文章归类">
            </el-table-column>
            <el-table-column>
                <template slot="header">
                    <div style="display: flex;justify-content: space-between;">
                        <el-select v-model="selectValue"></el-select>
                        <el-button size="mini">添加</el-button>
                    </div>
                </template>
                <el-table-column
                label="文章">
                </el-table-column>
                <el-table-column
                label="发布者">
                </el-table-column>
                <el-table-column
                label="访问量">
                </el-table-column>
                <el-table-column
                label="操作">
                <template>
                    <el-button type="text" size="small">编辑</el-button>
                </template>
                </el-table-column>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
export default {
    data() {
        return {
            selectValue: ''
        }
    }
}
</script>