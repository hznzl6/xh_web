<template>
    <div>
        
    </div>
</template>
<script>
export default {
    data() {
        return {
            
        }
    }
}
</script>

<!-- <template>
    <div class="Manager-MassCreditTime">
        <div class="Manager-MassCreditTime_PC">
            <div class="Manager-MassBroadCast_leftTable">
                <el-table
                :data="CreditData"
                height="calc(100vh - 160px)">
                    <el-table-column
                    prop="semester"
                    label="学期">
                    </el-table-column>
                    <el-table-column
                    prop="declaration"
                    label="申报时间">
                    </el-table-column>
                    <el-table-column
                    prop="deadline"
                    label="截止时间">
                    </el-table-column>
                    <el-table-column
                    prop="status"
                    label="状态">
                    <template slot-scope="scope">
                        <el-tag v-if="scope.row.status === 1" type="success">进行中</el-tag>
                        <el-tag v-if="scope.row.status === 0" type="warning">即将开始</el-tag>
                        <el-tag v-if="scope.row.status === 2" type="danger">已结束</el-tag>
                    </template>
                    </el-table-column>
                    <el-table-column
                    prop="nums"
                    label="申报总人数">
                    </el-table-column>
                    <el-table-column
                    label="操作">
                    <template slot-scope="scope">
                        <div v-if="scope.row.status === 0">
                            <el-button disabled style="width: 100%;" size="small">查看</el-button>
                        </div>
                        <div v-if="scope.row.status === 1">
                            <el-button :disabled="!scope.row.nums" @click="opneDialogViewCredit(scope.row)" style="width: 100%;" size="small">查看</el-button>
                            <el-button :disabled="scope.row.nums>0" @click="openDialogCredit(scope.row)" style="width: 100%;margin: 0;" size="small">申报</el-button>
                        </div>
                        <div v-if="scope.row.status === 2">
                            <el-button :disabled="!scope.row.nums" @click="opneDialogViewCredit(scope.row)" style="width: 100%;" size="small">查看</el-button>
                            <el-button disabled style="width: 100%;margin: 0;" size="small">申报</el-button>
                        </div>
                    </template>
                    </el-table-column>
                </el-table>

                <el-dialog title="申报" :visible.sync="dialogApplyCredit">
                    <div style="text-align: center">
                        <el-transfer
                        style="text-align: left; display: inline-block"
                        v-model="targetValue"
                        :props="{
                            key: 'key',
                            label: 'label'
                        }"
                        filterable
                        :left-default-checked="[]"
                        :right-default-checked="[]"
                        :render-content="renderFunc"
                        :titles="['全体成员', '申报名单']"
                        :button-texts="['取消', '添加']"
                        :format="{
                            noChecked: '${total}',
                            hasChecked: '${checked}/${total}'
                        }"
                        :data="transferData">
                        <el-button :disabled="isApply" @click="ApplyCreditBtn" class="transfer-footer" slot="right-footer" size="small">申报</el-button>
                        </el-transfer>
                    </div>
                </el-dialog>
                <el-dialog title="申报名单" :visible.sync="dialogViewCredit">
                    <div style="display: flex;justify-content: flex-end;">
                        <el-button :disabled="this.toApplyData.status==2" @click="openViewCreditUpdateBtn">添加</el-button>
                    </div>
                    <el-table
                    :data="listNameTable"
                    height="500">
                    <el-table-column
                    prop="sno"
                    label="学号">
                    </el-table-column>
                    <el-table-column
                    prop="name"
                    label="名字">
                    </el-table-column>
                    <el-table-column
                    prop="belong"
                    label="班级">
                    </el-table-column>
                    <el-table-column
                    label="操作">
                    <template slot-scope="scope">
                        <el-button :disabled="isButtonDisabled()" type="danger" @click="removePerson(scope.row)">移除</el-button>
                    </template>
                    </el-table-column>
                    </el-table>
                </el-dialog>

                <el-dialog title="添加" :visible.sync="dialogViewCreditUpdate">
                    <div style="text-align: center">
                        <el-transfer
                        style="text-align: left; display: inline-block"
                        v-model="rowResulttransferData"
                        :props="{
                            key: 'key',
                            label: 'label'
                        }"
                        filterable
                        :left-default-checked="[]"
                        :right-default-checked="[]"
                        :render-content="renderFunc"
                        :titles="['全体成员', '申报名单']"
                        :button-texts="['取消', '添加']"
                        :format="{
                            noChecked: '${total}',
                            hasChecked: '${checked}/${total}'
                        }"
                        :data="rowAlltransferData">
                        <el-button @click="AddApplyBtn" class="transfer-footer" slot="right-footer" size="small">添加</el-button>
                        </el-transfer>
                    </div>
                </el-dialog>
            </div>
        </div>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                CreditData: [],
                MemberData: [],
                dialogApplyCredit: false,
                toApplyData: [],
                targetValue: [],
                savetargetValue: [],
                transferData: [],
                rowAlltransferData: [],
                rowResulttransferData: [],
                listNameTable: [],
                viewTempData: [],
                returnData: [],
                allCreditByMass: [],
                renderFunc(h, option) {
                    return <span>{ option.key+1 } - { option.label }</span>;
                },
                isApply: false,
                dialogViewCredit: false,
                dialogViewCreditUpdate: false
            }
        },
        created() {
            this.getAllCredit();
            this.getAllPassByMass();
            // this.getAllDataAndRender()
            // this.generateData()
            // this.getCreditByMass()
        },
        methods: {
            AddApplyBtn() {
                console.log(this.rowResulttransferData)
                this.returnData = {
                    // mass: "互联网社",
                    mass: this.$route.params.mass,
                    semester: this.toApplyData.semester,
                    snos: []
                };
                console.log(this.rowAlltransferData)
                for (const key in this.rowResulttransferData) {
                    // console.log("key:"+key)
                    // const targetKey = this.rowResulttransferData[key];
                    // console.log("targetKey:"+targetKey)
                    // for (const dataKey in this.rowResulttransferData) {
                    //     console.log("datakey:"+dataKey)
                    //     if (dataKey === targetKey) {
                    //         const sno = this.rowAlltransferData[dataKey+1].sno;
                    //         console.log(sno)
                    //         this.returnData.snos.push(sno);
                    //     }
                    // }

                    const targetValue = this.rowResulttransferData[key];
                    for (const dataKey in this.rowAlltransferData) {
                        if (this.rowAlltransferData[dataKey].key === targetValue) {
                            const sno = this.rowAlltransferData[dataKey].sno;
                            if (sno) {
                                this.returnData.snos.push(sno);
                            }
                        }
                    }

                    // for (const dataKey in this.rowAlltransferData) {
                    //     if (dataKey === targetKey) {
                    //         const sno = this.rowAlltransferData[dataKey].sno;
                    //         if (!this.returnData.snos) {
                    //             this.returnData.snos = [];
                    //         }
                    //         this.returnData.snos.push(sno);
                    //     }
                    // }

                    // if (this.rowAlltransferData[targetKey]) {
                    //     const sno = this.rowAlltransferData[targetKey-1].sno;
                    //     if (!this.returnData.snos) {
                    //         this.returnData.snos = [];
                    //     }
                    //     this.returnData.snos.push(sno);
                    // } else {
                    //     // console.log(`Label for key ${targetKey}: Not found`);
                    // }
                }
                // console.log(this.returnData)
                const url = process.env.VUE_APP_BASE_URI + '/shetuan-creditApply/add-credit'
                fetch(url, {
                    method: "POST",
                    headers: {
                        'Accept': 'Application/json',
                        'Content-Type': 'Application/json'
                    },
                    body: JSON.stringify(this.returnData)
                })
                .then(res => res.text())
                .then(res => {
                    if(res == 'Add success') {
                        this.$message({
                            message: '添加成功',
                            type: 'success'
                        })
                        setTimeout(function(){
                            location.reload()
                        }, 1000)
                    }
                })
            },
            isButtonDisabled() {
                // console.log(this.toApplyData.status)
                if(this.toApplyData.status == 2) return true
            },
            removePerson(row) {
                // console.log(row)
                const data = {
                    sno: row.sno,
                    // mass: "互联网社",
                    mass: this.$route.params.mass,
                    semester: this.toApplyData.semester
                }
                console.log(data)
                const url = process.env.VUE_APP_BASE_URI + '/shetuan-creditApply/remove-credit'
                fetch(url, {
                    method: "POST",
                    headers: {
                        'Accept': 'Application/json',
                        'Content-Type': 'Application/json'
                    },
                    body: JSON.stringify(data)
                })
                .then(res => res.text())
                .then(res => {
                    if(res == 'Remove success') {
                        this.$message({
                            message: '移除成功',
                            type: 'success'
                        })
                        setTimeout(function(){
                            location.reload()
                        }, 1000)
                    }
                })
            },
            openViewCreditUpdateBtn() {
                this.dialogViewCreditUpdate = true
            },
            opneDialogViewCredit(row) {
                this.toApplyData = row
                // console.log(this.toApplyData.status)
                // console.log(this.toApplyData)
                this.dialogViewCredit = true
                const excludeIds = row.list.split(',').map(id => parseInt(id)); // 获取row中的id列表
                const filteredData = this.transferData.filter(item => !excludeIds.includes(item.id));
                row.all = filteredData;
                // 找到row.all中不在this.transferData中的数据
                const complementData = this.transferData.filter(dataItem => !filteredData.some(item => item.id === dataItem.id));
                row.result = complementData;
                // // 转换row.result中的key属性为指定格式
                // const keyFormat = {};
                // row.result.forEach((item, index) => {
                //     keyFormat[index] = item.key;
                // });
                // row.result = keyFormat;
                // console.log(row);
                this.rowAlltransferData = row.all
                // this.rowResulttransferData = row.result.slice()
                

                this.listNameTable = row.result.map(item => ({ name: item.label, sno: item.sno }));


                Promise.all(this.listNameTable.map(entry => {
                    const url = process.env.VUE_APP_BASE_URI + '/shetuan-creditApply/returnBelong';
                    return fetch(`${url}?sno=${entry.sno}`, {
                        method: "POST",
                        headers: {
                            'Accept': 'text/plain;charset=UTF-8',
                            'Content-Type': 'application/json'
                        },
                    })
                    .then(response => response.text())
                    .then(data => {
                        this.$set(entry, 'belong', data);
                        // entry.belong = data; // 假设返回数据中有一个belong字段
                    });
                })).then(() => {
                    // console.log(this.listNameTable);
                    // 在这里进行表格的操作等
                });

                // this.listNameTable.forEach(entry => {
                //     const url = process.env.VUE_APP_BASE_URI + '/shetuan-creditApply/returnBelong'
                //     // console.log(entry.sno)
                //     fetch(`${url}?sno=${entry.sno}`, {
                //         method: "POST",
                //         headers: {
                //             'Accept': 'text/plain;charset=UTF-8',
                //             'Content-Type': 'application/json'
                //         },
                //     })
                //     .then(response => response.text())
                //     .then(data => {
                //         // console.log(data)
                //         entry.belong = data; // 假设返回数据中有一个belong字段
                //     })
                // });

                // console.log(this.listNameTable)
            },
            ApplyCreditBtn() {
                // console.log(this.targetValue)
                // console.log(this.transferData)
                this.returnData = {
                    // mass: "互联网社",
                    mass: this.$route.params.mass,
                    // semester: "2023年第一学期",
                    semester: this.toApplyData.semester,
                    snos: []
                };
                for (const key in this.targetValue) {
                    const targetKey = this.targetValue[key];
                    if (this.transferData[targetKey]) {
                        // const label = this.transferData[targetKey].label;
                        const sno = this.transferData[targetKey].sno;
                        if (!this.returnData.snos) {
                            // this.returnData.labels = [];
                            this.returnData.snos = [];
                        }
                        // this.returnData.labels.push(label);
                        // this.returnData.snos.push(sno);
                        this.returnData.snos.push(sno);

                        

                    } else {
                        // console.log(`Label for key ${targetKey}: Not found`);
                    }
                }
                // console.log(this.returnData);
                const url = process.env.VUE_APP_BASE_URI + '/shetuan-creditApply/apply-credit'
                fetch(url, {
                    method: "POST",
                    headers: {
                        'Accept': 'Application/json',
                        'Content-Type': 'Application/json'
                    },
                    body: JSON.stringify(this.returnData)
                })
                .then(res => res.text())
                .then(res => {
                    if(res == 'Create success') {
                        this.$message({
                            message: '申报成功',
                            type: 'success'
                        })
                        setTimeout(function(){
                            location.reload()
                        }, 1000)
                    } else if(res == 'Update success'){
                        this.$message({
                            message: '更新成功',
                            type: 'success'
                        })
                        setTimeout(function(){
                            location.reload()
                        }, 1000)
                    }
                })
            },
            // generateData() {
            //     const data = [];
            //     // memberData.forEach((item, index) => {
            //     //     data.push({
            //     //         key: index + 1,
            //     //         label: `${index + 1} - ${item.name}`
            //     //     });
            //     // });
            //     for (let i = 1; i <= 15; i++) {
            //         data.push({
            //             key: i,
            //             label: `备选项 ${ i }`
            //         });
            //     }
            //     console.log(data)
            //     this.transferData = data;
            // },
            // getAllDataAndRender() {
            //     this.transferData = this.generateData();
            //     console.log(this.transferData)
            // },
            getAllPassByMass() {
                // const mass = '互联网社'
                const mass = this.$route.params.mass
                const url = process.env.VUE_APP_BASE_URI + '/shetuan-apply/apply-getAllPassByMass'
                fetch(`${url}?mass=${mass}`, {
                    method: "POST",
                    headers: {
                        'Accept': 'Application/json',
                        'Content-Type': 'Application/json'
                    }
                })
                .then(res => res.json())
                .then(res => {
                    const memberData = res.map(item => ({
                        id: item.shetuanUser.id,
                        name: item.shetuanUser.name,
                        sno: item.shetuanUser.sno
                    }));
                    this.MemberData = memberData;
                    // console.log(memberData);
                    const data = [];
                    memberData.forEach((item, index) => {
                        data.push({
                            key: index,
                            label: `${item.name}`,
                            sno: `${item.sno}`
                        });
                    });
                    // for (let i = 1; i <= 15; i++) {
                    //     data.push({
                    //         key: i,
                    //         label: `备选项 ${ i }`
                    //     });
                    // }
                    this.transferData = data;
                    this.transferData.forEach(item => {
                        const url = process.env.VUE_APP_BASE_URI + `/shetuan-creditApply/returnID`;
                        fetch(`${url}?sno=${item.sno}`, {
                            method: 'POST',
                            headers: {
                                'Content-Type': 'application/json'
                            },
                        })
                        .then(res => res.json())
                        .then(data => {
                            // console.log(data);
                            item.id = data;
                            if (!this.returnData.snos) {
                                this.returnData.snos = [];
                            }
                            this.returnData.snos.push(item.sno);
                            // console.log(this.returnData);
                        });
                    });
                    // console.log(this.transferData)
                })
            },
            openDialogCredit(row) {
                this.toApplyData = row
                console.log(row)
                this.dialogApplyCredit = true
            },
            getAllCredit() {
                const url = process.env.VUE_APP_BASE_URI + '/shetuan-credit/credit-getAll'
                fetch(url, {
                    method: "POST",
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                })
                .then(res => res.json())
                .then(res => {
                    // console.log(res)
                    this.CreditData = res
                    // console.log(this.CreditData)
                    // const mass = '互联网社'
                    const mass = this.$route.params.mass
                    const url = process.env.VUE_APP_BASE_URI + '/shetuan-creditApply/getAllByMass-credit'
                    fetch(`${url}?mass=${mass}`, {
                        method: "POST",
                        headers: {
                            'Accept': 'Application/json',
                            'Content-Type': 'Application/json'
                        },
                    })
                    .then(res => res.json())
                    .then(res => {
                        // console.log(res)
                        // console.log(this.transferData)
                        this.allCreditByMass = res;
                        // console.log(this.allCreditByMass)
                        this.CreditData.forEach((data, index) => {
                            const matchingMass = this.allCreditByMass.find(item => item.semester === data.semester);
                            if (matchingMass) {
                                const listArray = matchingMass.list.split(',').map(Number);
                                if (index < this.CreditData.length) {
                                    // data.nums = listArray.length;
                                    this.$set(this.CreditData[index], 'nums', listArray.length);
                                    // this.CreditData[index+1].nums = listArray.length;
                                    // this.$set(entry, 'belong', data);
                                    // console.log(data.nums);
                                }
                            }
                        });
                        // console.log(this.transferData)
                        // console.log(this.allCreditByMass)
                        // 遍历this.CreditData
                        this.CreditData.forEach((creditDataItem) => {
                            // 在this.allCreditByMass中查找与creditDataItem的semester相匹配的项
                            const matchingMass = this.allCreditByMass.find(item => item.semester === creditDataItem.semester);
                            
                            // 如果找到匹配的项
                            if (matchingMass) {
                                // 获取matchingMass在this.CreditData中对应的索引
                                const dataIndex = this.CreditData.findIndex(data => data.semester === matchingMass.semester);
                                
                                // 将list字段拼接到相应的this.CreditData条目中
                                if (dataIndex !== -1) {
                                    this.CreditData[dataIndex].list = matchingMass.list;
                                }
                            }
                        });
                    })
                })
            },
        }
    }
</script>
<style>
.Manager-MassCreditTime {
    position: relative;
    margin: 12px 12px;
    min-height: calc(100vh - 144px);
    background-color: rgb(255, 255, 255, 0.6);
}
.Manager-MassCreditTime .Manager-MassCreditTime_PC {
    width: 100%;
    display: block;
}
.Manager-MassCreditTime .Manager-MassCreditTime_Move {
    width: 100%;
    display: none;
}
.Manager-MassCreditTime .Manager-MassCreditTime_leftTable {
    position: absolute;
    left: 0;
    width: 50%;
    min-height: calc(100vh - 144px);
    background-color: rgba(196, 33, 33, 0.6);
}
.Manager-MassCreditTime .Manager-MassCreditTime_rightPost {
    position: absolute;
    right: 0;
    width: calc(50% - 20px);
    min-height: calc(100vh - 144px);
    border-radius: 5px;
    background-color: rgba(255, 255, 255, 0.6);
}

@media screen and (max-width: 480px) {
    
}
</style> -->