<template>
    <div>
        <el-container>
            <el-header>
                后台
                <i @click="navMenu_Open" class="el-icon-s-unfold navMenu_Icon"></i>
                移动端菜单
                <el-drawer
                    :visible.sync="navMenu"
                    direction="ltr"
                    :before-close="navMenu_Close"
                    size="40%"
                    style="text-align: left;">
                    <el-menu
                        default-active="2"
                        class="el-menu-vertical_move"
                        @open="handleOpen"
                        @close="handleClose"
                        background-color="#545c64"
                        text-color="#fff"
                        active-text-color="#ffd04b">
                        <div class="el-menu-vertical_moveTitle"></div>
                        <el-menu-item index="1">
                            <i class="el-icon-user"></i>
                            <span slot="title">主页</span>
                        </el-menu-item>
                        <el-submenu index="2">
                            <template slot="title">
                                <i class="el-icon-location"></i>
                                <span>导航一</span>
                            </template>
                            <el-menu-item-group>
                                <el-menu-item index="1-1">选项1</el-menu-item>
                            </el-menu-item-group>
                        </el-submenu>
                        <el-menu-item index="3">
                            <i class="el-icon-document"></i>
                            <span slot="title">导航三</span>
                        </el-menu-item>
                        <el-menu-item index="4">
                            <i class="el-icon-setting"></i>
                            <span slot="title">导航四</span>
                        </el-menu-item>
                    </el-menu>
                </el-drawer>
            </el-header>
            <el-container>
                <el-aside width="200px">
                    <el-menu
                        default-active="3-2"
                        class="el-menu-vertical"
                        @open="handleOpen"
                        @close="handleClose"
                        background-color="#545c64"
                        text-color="#fff"
                        active-text-color="#ffd04b"
                        style="text-align: left;">
                        <el-menu-item @click="ClickSideMenu('1')" index="1">
                            <i class="el-icon-user"></i>
                            <span slot="title">主页</span>
                        </el-menu-item>
                        <el-menu-item @click="ClickSideMenu('2')" index="2">
                            <i class="el-icon-menu"></i>
                            <span slot="title">站点页面</span>
                        </el-menu-item>
                        <el-submenu index="3">
                            <template slot="title">
                                <i class="el-icon-document-checked"></i>
                                <span>社团管理</span>
                            </template>
                            <el-menu-item-group>
                                <el-menu-item @click="ClickSideMenu(item.index)" v-for="item in MassManager" :key="item.index" :index="item.index">{{ item.function }}</el-menu-item>
                            </el-menu-item-group>
                            <!-- <el-menu-item-group>
                                <el-menu-item index="3-1">社团管理</el-menu-item>
                            </el-menu-item-group>
                            <el-menu-item-group>
                                <el-menu-item index="3-2">活动审核</el-menu-item>
                            </el-menu-item-group>
                            <el-menu-item-group>
                                <el-menu-item index="3-3">文章审核</el-menu-item>
                            </el-menu-item-group>
                            <el-menu-item-group>
                                <el-menu-item index="3-4">学时审核</el-menu-item>
                            </el-menu-item-group> -->
                        </el-submenu>
                        <el-menu-item @click="ClickSideMenu('4')" index="4">
                            <i class="el-icon-user"></i>
                            <span slot="title">通知公告</span>
                        </el-menu-item>
                    </el-menu>
                </el-aside>
                <el-container>
                    <el-main v-show="sideBarMenu == '1'">
                        0
                    </el-main>
                    <el-main v-show="sideBarMenu == '2'">
                        <div class="houtai-main_rightTable">
                            <el-table
                                :data="filteredTableData"
                                stripe
                                height="calc(100vh - 160px)"
                                border
                                style="overflow-y: auto;width: 100%">
                                <el-table-column
                                prop="belong"
                                label="类别">
                                </el-table-column>
                                <el-table-column label="文章信息">
                                    <template slot="header" slot-scope="scope">
                                        <el-select @change="handleLoad" v-model="SelectValue" placeholder="请选择" >
                                            <el-option
                                            @click="scope"
                                            v-for="item in belongs"
                                            :key="item.value"
                                            :label="item.label"
                                            :value="item.label">
                                            </el-option>
                                        </el-select>
                                    </template>
                                    <el-table-column
                                        prop="title"
                                        label="标题">
                                    </el-table-column>
                                    <el-table-column label="页面信息">
                                        <el-table-column
                                        prop="content"
                                        label="内容">
                                        <template slot-scope="scope">
                                            <div class="ellipsis">{{ scope.row.content }}</div>
                                        </template>
                                        </el-table-column>
                                        <el-table-column
                                        prop="author"
                                        label="作者">
                                        </el-table-column>
                                        <el-table-column
                                        prop="hits"
                                        label="点击量">
                                        </el-table-column>
                                        <el-table-column
                                        prop="create_time"
                                        label="发布时间">
                                        </el-table-column>
                                        <el-table-column
                                        prop="update_time"
                                        label="更新时间">
                                        </el-table-column>
                                        <el-table-column
                                        label="操作">
                                            <template slot-scope="scope">
                                                <el-button style="width: 100%;" @click="ViewArticle(scope.row)"  size="small">查看</el-button>
                                                <el-button style="width: 100%;text-align: center;margin: 0;padding: 0;" @click="EditArticle(scope.row)" type="text" size="small">编辑</el-button>
                                            </template>
                                        </el-table-column>
                                    </el-table-column>
                                </el-table-column>
                            </el-table>
                        </div>
                        <div class="houtai-main_leftEdit">
                            <div style="display: flex;align-items: center;">
                                <el-input style="flex: 1;" v-model="Article.title" placeholder="输入标题"></el-input>
                                <el-button @click="cancleEdit" style="margin: 0;" v-if="isEdit">返回</el-button>
                                <el-button style="margin: 0;" v-if="isEdit">删除</el-button>
                                <el-button style="margin: 0;" v-if="isEdit">更新</el-button>
                                <el-button v-else>发布</el-button>
                            </div>
                            <editor
                            v-model="Article.content"
                            placeholder="Type here..."
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
                        <!-- <div v-show="sideBarMenu == '3'">
                            <h2 style="background-color: #fff;">
                                {{ this.belong }}
                                <el-button @click="saveArticle" style="float: right;margin-right: 20px;margin-top: 2px;" size="mini">保存</el-button>
                            </h2>
                            <el-input style="220px" v-model="Article.title" placeholder="输入标题"></el-input>
                            <editor
                            v-model="Article.content"
                            placeholder="Type here..."
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
                        </div> -->

                        <!-- <div v-show="sideBarMenu == '4'">
                            <div style="width: 50%;min-height: calc(100vh - 120px);background-color: aqua;">
                                <el-button @click="postArticle" style="float: right;margin-right: 20px;margin-top: 5px;" size="mini">发布</el-button>
                                <el-input style="width: 220px;float: left;" v-model="Notice.title" placeholder="输入标题"></el-input>
                                <editor
                                style="width: 100%;"
                                v-model="Notice.content"
                                placeholder="Type here..."
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
                        </div> -->
                    </el-main>
                    <el-main v-show="sideBarMenu == '3-1'">
                        1
                    </el-main>
                    <el-main v-show="sideBarMenu == '3-2'">
                        <div class="houtai-ActivityCheck">
                            <div class="houtai-ActivityCheck_leftTable">
                                <el-table
                                    :data="filteredActivityData"
                                    height="calc(100vh - 160px)"
                                    border>
                                    <el-table-column
                                    prop="mass"
                                    label="社团">
                                    </el-table-column>
                                    <el-table-column label="申请信息">
                                    <template slot="header" slot-scope="scope">
                                        <el-select @change="handleLoad" v-model="activityApplyStatus" placeholder="请选择" >
                                            <el-option
                                            @click="scope"
                                            v-for="item in activityApply"
                                            :key="item.value"
                                            :label="item.label"
                                            :value="item.value">
                                            </el-option>
                                        </el-select>
                                    </template>
                                        <el-table-column
                                            prop="event"
                                            label="活动名称">
                                        </el-table-column>
                                        <el-table-column label="活动详情">
                                            <el-table-column
                                            prop="describes"
                                            label="描述">
                                            </el-table-column>
                                            <el-table-column
                                            prop="person"
                                            label="负责人">
                                            </el-table-column>
                                            <el-table-column
                                            prop="phone"
                                            label="负责人电话">
                                            </el-table-column>
                                            <el-table-column
                                            prop="expenditure"
                                            label="申请经费">
                                            </el-table-column>
                                            <el-table-column
                                            prop="start_time"
                                            label="开始时间">
                                            </el-table-column>
                                            <el-table-column
                                            prop="end_time"
                                            label="结束时间">
                                            </el-table-column>
                                            <el-table-column
                                            prop="actual"
                                            label="实际参与人员">
                                            </el-table-column>
                                            <el-table-column
                                            prop="status"
                                            label="状态">
                                            <template slot-scope="scope">
                                                <el-tag v-if="scope.row.status === 1" type="success">已审核</el-tag>
                                                <el-tag v-if="scope.row.status === 0" type="warning">待审核</el-tag>
                                                <el-tag v-if="scope.row.status === 2" type="danger">驳回</el-tag>
                                            </template>
                                            </el-table-column>
                                            <el-table-column
                                            label="操作">
                                            <template slot-scope="scope">
                                                <div v-if="scope.row.status === 0">
                                                    <el-button @click="agreeActivityApply(scope.row)" style="width: 100%;" type="success" size="small">通过</el-button>
                                                    <el-button @click="rejectActivityApply(scope.row)" style="width: 100%;margin: 0;" type="danger" size="small">驳回</el-button>
                                                    <el-button style="width: 100%;text-align: center;margin: 0;padding: 0;" type="text" size="small">查看</el-button>
                                                </div>
                                                <div v-if="scope.row.status === 1">
                                                    <el-button style="width: 100%;" size="small">查看</el-button>
                                                </div>
                                                <div v-if="scope.row.status === 2">
                                                    <el-button style="width: 100%;" size="small">撤销驳回</el-button>
                                                </div>
                                            </template>
                                            </el-table-column>
                                        </el-table-column>
                                    </el-table-column>
                                </el-table>
                            </div>
                            <div class="houtai-ActivityCheck_rightVisual">

                            </div>
                        </div>
                    </el-main>
                    <el-main v-show="sideBarMenu == '3-3'">
                        <div class="houtai-ArticleCheck_leftTable">
                            <el-table
                                :data="filteredArticleData"
                                height="calc(100vh - 160px)"
                                border>
                                <el-table-column
                                prop="mass"
                                label="社团">
                                </el-table-column>
                                <el-table-column label="文章信息">
                                <template slot="header" slot-scope="scope">
                                    <el-select @change="handleLoad" v-model="articleApplyStatus" placeholder="请选择" >
                                        <el-option
                                        @click="scope"
                                        v-for="item in activityApply"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value">
                                        </el-option>
                                    </el-select>
                                </template>
                                    <el-table-column
                                        prop="title"
                                        label="标题">
                                    </el-table-column>
                                    <el-table-column label="文章信息">
                                        <el-table-column
                                        prop="author"
                                        label="发布者">
                                        </el-table-column>
                                        <el-table-column
                                        prop="hits"
                                        label="点击量">
                                        </el-table-column>
                                        <el-table-column
                                        prop="create_time"
                                        label="发布时间">
                                        </el-table-column>
                                        <el-table-column
                                        prop="update_time"
                                        label="修改时间">
                                        </el-table-column>
                                        <el-table-column
                                        prop="status"
                                        label="状态">
                                        <template slot-scope="scope">
                                            <el-tag v-if="scope.row.status === 1" type="success">已审核</el-tag>
                                            <el-tag v-if="scope.row.status === 0" type="warning">待审核</el-tag>
                                            <el-tag v-if="scope.row.status === 2" type="danger">驳回</el-tag>
                                        </template>
                                        </el-table-column>
                                        <el-table-column
                                        label="操作">
                                        <template slot-scope="scope">
                                            <div v-if="scope.row.status === 0">
                                                <el-button @click="agreeArticleApply(scope.row)" style="width: 100%;" type="success" size="small">通过</el-button>
                                                <el-button @click="rejectArticle(scope.row)" style="width: 100%;margin: 0;" type="danger" size="small">驳回</el-button>
                                                <el-button style="width: 100%;text-align: center;margin: 0;padding: 0;" type="text" size="small">查看</el-button>
                                            </div>
                                            <div v-if="scope.row.status === 1">
                                                <el-button style="width: 100%;" size="small">查看</el-button>
                                            </div>
                                            <div v-if="scope.row.status === 2">
                                                <el-button style="width: 100%;" size="small">撤销驳回</el-button>
                                            </div>
                                        </template>
                                        </el-table-column>
                                    </el-table-column>
                                </el-table-column>
                            </el-table>
                        </div>
                        <div class="houtai-ArticleCheck_rightVisual">

                        </div>
                    </el-main>
                    <el-main v-show="sideBarMenu == '3-4'">
                        <div class="houtai-CreditCheck_leftTable">
                            <el-table
                                :data="filteredCreditData"
                                height="calc(100vh - 160px)"
                                border>
                                <el-table-column
                                prop="semester"
                                label="学期">
                                </el-table-column>
                                <el-table-column label="申报信息">
                                    <template slot="header" slot-scope="scope">
                                        <el-select @change="handleLoad" v-model="creditSelectStatus" placeholder="请选择" >
                                            <el-option
                                            @click="scope"
                                            v-for="item in creditStatus"
                                            :key="item.value"
                                            :label="item.label"
                                            :value="item.value">
                                            </el-option>
                                        </el-select>
                                        <el-button @click="openDialogCreditVisible">创建申报</el-button>
                                    </template>
                                    <el-table-column
                                        prop="massNums"
                                        label="申报社团数量">
                                    </el-table-column>
                                    <el-table-column label="申报信息">
                                        <el-table-column
                                        prop="founder"
                                        label="创建人">
                                        </el-table-column>
                                        <el-table-column
                                        prop="applyNums"
                                        label="申报总人数">
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
                                        label="操作">
                                        <template slot-scope="scope">
                                            <div v-if="scope.row.status === 0">
                                                <el-button @click="EditCredit(scope.row)" style="width: 100%;" size="small">修改</el-button>
                                            </div>
                                            <div v-if="scope.row.status === 1">
                                                <el-button @click="ViewCredit(scope.row)" style="width: 100%;" size="small">查看</el-button>
                                                <el-button @click="EditCredit(scope.row)" style="width: 100%;margin: 0;" size="small">修改</el-button>
                                            </div>
                                            <div v-if="scope.row.status === 2">
                                                <el-button @click="ViewCredit(scope.row)" style="width: 100%;" size="small">查看</el-button>
                                                <el-button @click="EditCredit(scope.row)" style="width: 100%;margin: 0;" size="small">修改</el-button>
                                            </div>
                                        </template>
                                        </el-table-column>
                                    </el-table-column>
                                </el-table-column>
                            </el-table>

                            <el-dialog title="创建申报" :visible.sync="dialogCreditVisible">
                                <el-form :model="DialogCreditTableData">
                                    <el-form-item label="学期" label-width="120px">
                                        <el-input v-model="DialogCreditTableData.semester" autocomplete="off"></el-input>
                                    </el-form-item>
                                    <el-form-item label="创建人" label-width="120px">
                                        <el-input v-model="DialogCreditTableData.founder" autocomplete="off"></el-input>
                                    </el-form-item>
                                    <el-form-item label="申报时间" label-width="120px">
                                        <el-date-picker
                                        v-model="DialogCreditTableData.declaration"
                                        type="datetime"
                                        placeholder="选择开始时间"
                                        default-time="12:00:00">
                                        </el-date-picker>
                                    </el-form-item>
                                    <el-form-item label="截止时间" label-width="120px">
                                        <el-date-picker
                                        v-model="DialogCreditTableData.deadline"
                                        type="datetime"
                                        placeholder="选择截止时间"
                                        default-time="12:00:00">
                                        </el-date-picker>
                                    </el-form-item>
                                </el-form>
                                <div style="display: flex;justify-content: flex-end;">
                                    <el-button @click="createCredit">创建</el-button>
                                </div>
                            </el-dialog>

                            <el-dialog title="修改" :visible.sync="dialogViewCreditData">
                                <el-form :model="DialogCreditViewData">
                                    <el-form-item label="学期" label-width="120px">
                                        <el-input v-model="DialogCreditViewData.semester" autocomplete="off"></el-input>
                                    </el-form-item>
                                    <el-form-item label="创建人" label-width="120px">
                                        <el-input disabled v-model="DialogCreditViewData.founder" autocomplete="off"></el-input>
                                    </el-form-item>
                                    <el-form-item label="申报时间" label-width="120px">
                                        <el-date-picker
                                        v-model="DialogCreditViewData.declaration"
                                        type="datetime"
                                        placeholder="选择开始时间"
                                        default-time="12:00:00">
                                        </el-date-picker>
                                    </el-form-item>
                                    <el-form-item label="截止时间" label-width="120px">
                                        <el-date-picker
                                        v-model="DialogCreditViewData.deadline"
                                        type="datetime"
                                        placeholder="选择截止时间"
                                        default-time="12:00:00">
                                        </el-date-picker>
                                    </el-form-item>
                                </el-form>
                                <div style="display: flex;justify-content: flex-end;">
                                    <el-button @click="UpdateCreditBtn">修改</el-button>
                                </div>
                            </el-dialog>

                            <el-dialog title="查看" :visible.sync="dialogViewCreditTable">
                                <!-- this.rowCreditInView -->
                                <el-table
                                    :data="filteredCheckData"
                                    height="500"
                                    border>
                                    <el-table-column
                                    prop="semester"
                                    label="学期">
                                    </el-table-column>
                                    <el-table-column label="申报信息">
                                        <template slot="header" slot-scope="scope">
                                            <el-select @change="handleLoad" v-model="creditCheckStatus" placeholder="请选择" >
                                                <el-option
                                                @click="scope"
                                                v-for="item in creditCheckData"
                                                :key="item.value"
                                                :label="item.label"
                                                :value="item.value">
                                                </el-option>
                                            </el-select>
                                        </template>
                                        <el-table-column
                                        prop="mass"
                                        label="社团">
                                        </el-table-column>
                                        <el-table-column
                                        prop="applyNums"
                                        label="申报总人数">
                                        </el-table-column>
                                        <el-table-column
                                        prop="apply_time"
                                        label="申报时间">
                                        </el-table-column>
                                        <el-table-column
                                        prop="update_time"
                                        label="更新时间">
                                        </el-table-column>
                                        <el-table-column
                                        prop="status"
                                        label="状态">
                                        <template slot-scope="scope">
                                            <el-tag v-if="scope.row.status === 1" type="success">已审核</el-tag>
                                            <el-tag v-if="scope.row.status === 0" type="warning">未审核</el-tag>
                                            <el-tag v-if="scope.row.status === 2" type="danger">驳回</el-tag>
                                        </template>
                                        </el-table-column>
                                        <el-table-column
                                        label="操作">
                                        <template slot-scope="scope">
                                            <el-button v-if="scope.row.status == 0 || scope.row.status == 1 || scope.row.status == 2" @click="ViewListName(scope.row)" type="text" style="width: 100%;" size="small">查看名单</el-button>
                                            <el-button v-if="scope.row.status == 0" @click="PassListCredit(scope.row)" style="width: 100%;margin: 0;" size="small">通过</el-button>
                                            <el-button v-if="scope.row.status == 1 || scope.row.status == 2" style="width: 100%;margin: 0;" @click="ReBackListCredit(scope.row)" size="small" type="danger">撤销</el-button>
                                        </template>
                                        </el-table-column>
                                    </el-table-column>
                                </el-table>
                            </el-dialog>
                            <el-dialog title="查看名单" :visible.sync="dialogPeopleList">
                                <el-table
                                    :data="creditPeopleList"
                                    height="500"
                                    border>
                                    <el-table-column
                                    prop="sno"
                                    label="学号">
                                    </el-table-column>
                                    <el-table-column label="名单信息">
                                        <el-table-column
                                        prop="name"
                                        label="名字">
                                        </el-table-column>
                                        <el-table-column
                                        prop="belong"
                                        label="班级">
                                        </el-table-column>
                                        <el-table-column
                                        prop="qq"
                                        label="qq">
                                        </el-table-column>
                                        <el-table-column
                                        prop="mail"
                                        label="邮箱">
                                        </el-table-column>
                                        <el-table-column
                                        prop="phone"
                                        label="手机">
                                        </el-table-column>
                                    </el-table-column>
                                </el-table>
                            </el-dialog>
                        </div>
                    </el-main>
                    <el-main v-show="sideBarMenu == '4'">
                        5
                    </el-main>
                    <el-footer>&copy;Ganxy|2024</el-footer>
                </el-container>
            </el-container>
        </el-container>
    </div>
</template>
<script>
import { mapState, mapMutations } from 'vuex';
import Editor from '@tinymce/tinymce-vue'
// import { t } from '@wangeditor/editor'
  export default {
    components: {
        'editor': Editor
    },
    data() {
        return {
            navMenu: false,
            belong: '部门概况',
            tableData: [],
            ActivityData: [],
            ArticleData: [],
            CreditData: [],
            selectedBelong: '部门概况',
            belongs: [
                {
                    label: '部门概况',
                },
                {
                    label: '服务指南',
                },
                {
                    label: '规章制度',
                },
                {
                    label: '信息公示',
                },
                {
                    label: '下载专区',
                },
                {
                    label: '联系我们',
                }
            ],
            activityApplyStatus: 0,
            articleApplyStatus: 0,
            creditSelectStatus: 1,
            activityApply: [
                {
                    label: '待审核',
                    value: 0
                }, 
                {
                    label: '已审核',
                    value: 1
                }, 
                {
                    label: '驳回',
                    value: 2
                },
            ],
            creditStatus: [
                {
                    label: '即将开始',
                    value: 0
                }, 
                {
                    label: '进行中',
                    value: 1
                }, 
                {
                    label: '已结束',
                    value: 2
                },
            ],
            SelectValue: '部门概况',
            Article: {
                content: '',
                title: ''
            },
            sideBarMenu: '3-2',
            MassManager: [
                {
                    index: '3-1',
                    function: '社团管理'
                },
                {
                    index: '3-2',
                    function: '活动审核'
                },
                {
                    index: '3-3',
                    function: '文章审核'
                },{
                    index: '3-4',
                    function: '学时审核'
                }
            ],
            Notice: {
                title: '',
                content: ''
            },
            isEdit: false,
            dialogCreditVisible: false,
            DialogCreditTableData: {
                semester: '',
                founder: '',
                declaration: '',
                deadline: ''
            },
            dialogViewCreditData: false,
            DialogCreditViewData: {},
            dialogViewCreditTable: false,
            allCreditInHoutai: [],
            rowCreditInView: [],
            creditCheckStatus: 0,
            creditCheckData: [
                {
                    label: '未审核',
                    value: 0
                }, 
                {
                    label: '已审核',
                    value: 1
                }, 
                {
                    label: '驳回',
                    value: 2
                },
            ],
            creditPeopleList: [],
            dialogPeopleList: false,
            AccountInfo: {}
        }
    },
    created() {
        // this.getArticle()
        console.log("isHoutai:"+this.$store.state.isHoutai)
        this.getAccountInfo(this.$route.params.account)
        this.getAllSites()
        this.getAllActivityApply()
        this.getAllArticle()
        this.getAllCredit()
        this.judgeCreditStatus()
        this.getAllCreditInHoutai()
    },
    computed: {
        ...mapState(['isHoutai']),
        filteredTableData() {
            return this.tableData.filter(item => item.belong === this.selectedBelong);
        },
        filteredActivityData() {
            return this.ActivityData.filter(item => item.status === this.activityApplyStatus);
        },
        filteredArticleData() {
            return this.ArticleData.filter(item => item.status === this.articleApplyStatus);
        },
        filteredCreditData() {
            return this.CreditData.filter(item => item.status === this.creditSelectStatus);
        },
        // this.rowCreditInView filteredCheckData
        filteredCheckData() {
            return this.rowCreditInView.filter(item => item.status === this.creditCheckStatus);
        },
    },
    methods: {
        ...mapMutations(['SET_IS_HOUTAI']),
        getAccountInfo(account) {
            const url = process.env.VUE_APP_BASE_URI + '/houtai-account/account-getInfo'
            fetch(`${url}?account=${account}`, {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
            })
            .then(res => res.json())
            .then(res => {
                this.AccountInfo = res
                console.log(res)
            })
        },
        ReBackListCredit(row) {
            const mass = row.mass
            const semester = row.semester 
            const url = process.env.VUE_APP_BASE_URI + '/shetuan-creditApply/ReBackCredit'
            fetch(`${url}?mass=${mass}&semester=${semester}`, {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
            })
            .then(res => res.text())
            .then(res => {
                if(res == 'ReBack success') {
                    this.$message({
                        message: '撤销成功',
                        type: 'success'
                    })
                    setTimeout(function() {
                        location.reload()
                    }, 1000)
                }
            })
        },
        PassListCredit(row) {
            // console.log(row)
            const mass = row.mass
            const semester = row.semester 
            const url = process.env.VUE_APP_BASE_URI + '/shetuan-creditApply/passCredit'
            fetch(`${url}?mass=${mass}&semester=${semester}`, {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
            })
            .then(res => res.text())
            .then(res => {
                if(res == 'Pass success') {
                    this.$message({
                        message: '审核成功',
                        type: 'success'
                    })
                    setTimeout(function() {
                        location.reload()
                    }, 1000)
                }
            })
        },
        ViewListName(row) {
            // console.log(row)
            const ids = row.list
            const url = process.env.VUE_APP_BASE_URI + '/shetuan-creditApply/getCreditPeople'
            fetch(`${url}?ids=${ids}`, {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
            })
            .then(res => res.json())
            .then(res => {
                this.creditPeopleList = res
                // console.log(res)
            })
            this.dialogPeopleList = true
        },
        async getAllCreditInHoutai() {
            await this.getAllCredit();
            // console.log(this.CreditData)
            const url = process.env.VUE_APP_BASE_URI + '/shetuan-creditApply/getAllBySemester'
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
                this.allCreditInHoutai = res
                this.CreditData.forEach(creditItem => {
                    const matchedItems = res.filter(item => item.semester === creditItem.semester);
                    creditItem.massNums = matchedItems.length;

                    let applyNumsSum = 0;
                    matchedItems.forEach(item => {
                        const listArray = item.list.split(",");
                        applyNumsSum += listArray.length;
                    });
                    creditItem.applyNums = applyNumsSum;

                });
                // console.log(this.CreditData)
                // console.log(res.list)
                // this.rowCreditInView.massNums
                res.forEach(item => {
                    // console.log(item);
                    // console.log(item.list);
                    
                    const listArray = item.list.split(",");
                    item.applyNums = listArray.length;
                });

                // const listArray = res.list.split(",");
                // this.allCreditInHoutai.applyNums = listArray.length
            })
        },
        ViewCredit(row) {
            this.dialogViewCreditTable = true
            // console.log(this.allCreditInHoutai)
            // console.log(row)
            // 遍历allCreditInHoutai数组，找到符合条件的元素
            const filteredData = this.allCreditInHoutai.filter(item => item.semester === row.semester);
            
            // 将符合条件的元素赋值给rowCreditInView
            this.rowCreditInView = filteredData;
            // this.rowCreditInView.massNums = filteredData.length
            // console.log(filteredData.length)
            // console.log(this.rowCreditInView)
            // rowCreditInView
            // console.log(this.filteredCreditData)
        },
        UpdateCreditBtn() {
            let declarationDate = new Date(this.DialogCreditViewData.declaration);
            let deadlineDate = new Date(this.DialogCreditViewData.deadline);
            let formattedDeclaration = `${declarationDate.getFullYear()}-${(declarationDate.getMonth() + 1).toString().padStart(2, '0')}-${declarationDate.getDate().toString().padStart(2, '0')} ${declarationDate.getHours().toString().padStart(2, '0')}:${declarationDate.getMinutes().toString().padStart(2, '0')}:${declarationDate.getSeconds().toString().padStart(2, '0')}`;
            let formattedDeadline = `${deadlineDate.getFullYear()}-${(deadlineDate.getMonth() + 1).toString().padStart(2, '0')}-${deadlineDate.getDate().toString().padStart(2, '0')} ${deadlineDate.getHours().toString().padStart(2, '0')}:${deadlineDate.getMinutes().toString().padStart(2, '0')}:${deadlineDate.getSeconds().toString().padStart(2, '0')}`;
            this.DialogCreditViewData.declaration = formattedDeclaration;
            this.DialogCreditViewData.deadline = formattedDeadline;

            const url = process.env.VUE_APP_BASE_URI + '/shetuan-credit/credit-create'
            fetch(url, {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(this.DialogCreditViewData)
            })
            .then(res => res.text())
            .then(res =>{
                if(res == 'Create success'){
                    this.$message({
                        message: '创建成功',
                        type: 'success'
                    })
                    setTimeout(function() {
                        location.reload()
                    }, 1000)
                } else if(res == 'Update success') {
                    this.$message({
                        message: '更新成功',
                        type: 'success'
                    })
                    setTimeout(function() {
                        location.reload()
                    }, 1000)
                }
                this.judgeCreditStatus()
            })
        },
        EditCredit(row) {
            this.DialogCreditViewData = row
            this.dialogViewCreditData = true
        },
        judgeCreditStatus() {
            const url = process.env.VUE_APP_BASE_URI + '/shetuan-credit/credit-judgtStatus'
            fetch(url, {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
            })
            .then(res => res.text())
            .then(res => {
                if(res == 'Judge success') {
                    // console.log("状态更新成功")
                    return null
                }
            })
        },
        // CreditData
        async getAllCredit() {
            const url = process.env.VUE_APP_BASE_URI + '/shetuan-credit/credit-getAll'
            return fetch(url, {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
            })
            .then(res => res.json())
            .then(res => {
                this.CreditData = res
                // console.log(this.CreditData)
            })
        },
        createCredit() {
            let declarationDate = new Date(this.DialogCreditTableData.declaration);
            let deadlineDate = new Date(this.DialogCreditTableData.deadline);
            let formattedDeclaration = `${declarationDate.getFullYear()}-${(declarationDate.getMonth() + 1).toString().padStart(2, '0')}-${declarationDate.getDate().toString().padStart(2, '0')} ${declarationDate.getHours().toString().padStart(2, '0')}:${declarationDate.getMinutes().toString().padStart(2, '0')}:${declarationDate.getSeconds().toString().padStart(2, '0')}`;
            let formattedDeadline = `${deadlineDate.getFullYear()}-${(deadlineDate.getMonth() + 1).toString().padStart(2, '0')}-${deadlineDate.getDate().toString().padStart(2, '0')} ${deadlineDate.getHours().toString().padStart(2, '0')}:${deadlineDate.getMinutes().toString().padStart(2, '0')}:${deadlineDate.getSeconds().toString().padStart(2, '0')}`;
            this.DialogCreditTableData.declaration = formattedDeclaration;
            this.DialogCreditTableData.deadline = formattedDeadline;
            const url = process.env.VUE_APP_BASE_URI + '/shetuan-credit/credit-create'
            fetch(url, {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(this.DialogCreditTableData)
            })
            .then(res => res.text())
            .then(res =>{
                if(res == 'Create success'){
                    this.$message({
                        message: '创建成功',
                        type: 'success'
                    })
                    setTimeout(function() {
                        location.reload()
                    }, 1000)
                } else if(res == 'Update success') {
                    this.$message({
                        message: '更新成功',
                        type: 'success'
                    })
                    setTimeout(function() {
                        location.reload()
                    }, 1000)
                }
                this.judgeCreditStatus()
            })
        },
        rejectArticle(row) {
            const url = process.env.VUE_APP_BASE_URI + '/wenzhang-artile/reject-article'
            const mass = row.mass
            const title = row.title
            fetch(`${url}?mass=${mass}&title=${title}`, {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            })
            .then(res => res.text())
            .then(res => {
                if(res == 'Reject success') {
                    this.$message({
                        message: '驳回成功',
                        type: 'success'
                    })
                }
                setTimeout(function() {
                    location.reload()
                }, 1000)
            })
        },
        agreeArticleApply(row) {
            const url = process.env.VUE_APP_BASE_URI + '/wenzhang-artile/agree-article'
            const mass = row.mass
            const title = row.title
            fetch(`${url}?mass=${mass}&title=${title}`, {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            })
            .then(res => res.text())
            .then(res => {
                if(res == 'Agree success') {
                    this.$message({
                        message: '审核成功',
                        type: 'success'
                    })
                }
                setTimeout(function() {
                    location.reload()
                }, 1000)
            })
        },
        getAllArticle() {
            const url = process.env.VUE_APP_BASE_URI + '/wenzhang-artile/getAll'
            fetch(url, {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            })
            .then(res => res.json())
            .then(res => {
                this.ArticleData = res
            })
        },
        rejectActivityApply(row) {
            const mass = row.mass
            const event = row.event
            const url = process.env.VUE_APP_BASE_URI + '/shetuan-activity/rejectEvent'
            fetch(`${url}?mass=${mass}&event=${event}`, {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            })
            .then(res => res.text())
            .then(res => {
                if(res == 'Reject success') {
                    this.$message({
                        message: '驳回成功',
                        type: 'success'
                    })
                }
                setTimeout(function() {
                    location.reload()
                }, 1000)
            })
        },
        agreeActivityApply(row) {
            const mass = row.mass
            const event = row.event
            const url = process.env.VUE_APP_BASE_URI + '/shetuan-activity/agreeEvent'
            fetch(`${url}?mass=${mass}&event=${event}`, {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            })
            .then(res => res.text())
            .then(res => {
                if(res == 'Agree success') {
                    this.$message({
                        message: '通过成功',
                        type: 'success'
                    })
                }
                setTimeout(function() {
                    location.reload()
                }, 1000)
            })
        },
        getAllActivityApply() {
            const url = process.env.VUE_APP_BASE_URI + '/shetuan-activity/getAllEvent'
            fetch(url, {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            })
            .then(res => res.json())
            .then(res => {
                this.ActivityData = res
                // console.log(this.ActivityData)
            })
        },
        cancleEdit() {
            this.isEdit = false
            this.Article.title = ''
            this.Article.content = ''
        },
        EditArticle(row) {
            this.Article.title = row.title
            this.Article.content = row.content
            this.isEdit = true
        },
        ViewArticle(row) {
            // console.log(row.content)
            this.$alert(row.content, '查看内容', {
                dangerouslyUseHTMLString: true,
                callback: action => {  // 添加一个callback函数
                    if(action === 'close') {
                        // 在关闭时会执行这里的代码
                        // console.log('弹窗被关闭了');
                    }
                }
            })
        },
        getAllSites() {
            const url = process.env.VUE_APP_BASE_URI + '/houtai-siteArticle/siteArticle-getAllArticle'
            fetch(url, {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            })
            .then(res => res.json())
            .then(res => {
                this.tableData = res
                // console.log(this.tableData)
            })
        },
        handleLoad(value) {
            // console.log(value)
            this.selectedBelong = value
        },
        handleOpen(key, keyPath) {
            console.log(key, keyPath);
        },
        handleClose(key, keyPath) {
            console.log(key, keyPath);
        },
        navMenu_Open() {
            this.navMenu = true
        },
        navMenu_Close(done) {
            done()
        },
        toBelong(belong) {
            this.sideBarMenu = 3
            this.belong = belong
        },
        getArticle() {
            const url = process.env.VUE_APP_BASE_URI + '/houtai-siteArticle/siteArticle-getArticle'
            const belong = this.belong
            const title = '部门设置'
            fetch(`${url}?belong=${belong}&title=${title}`, {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            })
            .then(res => res.json())
            .then(res => {
                this.Article.content = res.content
                this.Article.title = res.title
            })
        },
        saveArticle() {
            const url = process.env.VUE_APP_BASE_URI + '/houtai-siteArticle/siteArticle-save'
            const value = {
                belong: this.belong,
                content: this.Article.content,
                title: this.Article.title
            }
            fetch(url, {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(value)
            })
            .then(res => res.text())
            .then(res => {
                console.log(res)
            })
        },
        ClickSideMenu(index) {
            // console.log(index)
            this.sideBarMenu = index
        },
        postArticle() {
            const value = {
                title: this.Notice.title,
                content: this.Notice.content
            }
            const url = process.env.VUE_APP_BASE_URI + '//gonggao-notice/notice-post'
            fetch(url, {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(value)
            })
            .then(res => res.text())
            .then(res => {
                console.log(res)
            })
        },
        openDialogCreditVisible() {
            this.dialogCreditVisible = true
        }
    }
  }
</script>
<style>
.el-header, .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
    height: 60px;
}
.navMenu_Icon {
    float: left;
    font-size: 32px;
    line-height: 60px;
    display: none;
}
.el-main {
    min-height: calc(100vh - 160px);
    background: url("http://ganxy03.cn/img/background-image.jpg");
    background-size: cover;
    padding: 20px;
    display: flex;
}
.houtai-main_rightTable {
    width: 50%;
    height: calc(100vh - 160px);
    /* background-color: rgb(197, 129, 41); */
}
.el-message-box {
    width: 760px;
    max-height: 70vh;
    overflow-y: auto;
}
.ellipsis {
    white-space: nowrap;         /* 不换行 */
    overflow: hidden;            /* 溢出内容隐藏 */
    text-overflow: ellipsis;     /* 显示省略号 */
}
.houtai-main_leftEdit {
    width: 50%;
    height: calc(100vh - 160px);
    background-color: antiquewhite;
}
.houtai-main_leftEdit .tox {
    min-height: calc(100vh - 200px);
}
.el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 200px;
    min-height: calc(100vh - 60px);
}
.el-menu-vertical {
    min-height: calc(100vh - 60px);
    overflow-x: hidden;
    overflow-y: hidden;
}
.el-menu-vertical_move {
    min-height: 100vh
}
.el-menu-vertical_move .el-drawer .el-drawer__header, #el-drawer__title {
    display: none;
}
.el-menu-vertical_moveTitle {
    height: 40px;
    width: 100%;
}

.tox-statusbar__branding a {
    display: none;
}




.houtai-ActivityCheck {
    width: 100%;
    height: calc(100vh - 160px);
    display: flex;
}
.houtai-ActivityCheck_leftTable {
    width: 60%;
    height: calc(100vh - 160px);
    overflow-y: auto;
    /* background-color: #B3C0D1; */
}

.houtai-ActivityCheck_rightVisual {
    width: 40%;
    height: calc(100vh - 160px);
    overflow-y: auto;
    /* background-color: antiquewhite; */
}

.houtai-ArticleCheck_leftTable {
    width: 60%;
    height: calc(100vh - 160px);
    overflow-y: auto;
}
.houtai-ArticleCheck_rightVisual {
    width: 40%;
    height: calc(100vh - 160px);
    overflow-y: auto;
}
.houtai-CreditCheck_leftTable {
    width: 60%;
    height: calc(100vh - 160px);
    overflow-y: auto;
}
@media screen and (max-width: 480px) {
    .navMenu_Icon {
        display: block;
    }
    .el-aside {
        display: none;
    }
    .el-main {
        display: block;
        padding: 12px;
    }
    .houtai-main_rightTable {
        width: 100%;
    }
    .houtai-main_leftEdit {
        width: 100%;
    }



    .houtai-ActivityCheck {
        display: block;
    }
    .houtai-ActivityCheck_leftTable {
        width: 100%;
    }
    .houtai-ActivityCheck_rightVisual {
        width: 100%;
    }
}
</style>

<!-- <template>
    <div>
        <el-container>
            <el-main>
                <el-header>header</el-header>
                <el-aside>side</el-aside>
                main
                <el-footer>footer</el-footer>
            </el-main>
        </el-container>
    </div>
</template>

<style>
.el-header, .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
    height: 60px;
}
.el-header {
    position: absolute;
    top: 0;
    width: 100%;
    background-color: rgb(161,188,209,0.4);
}
.el-main {
    padding: 0;
    position: relative;
    min-height: 100vh;
    background: url("http://ganxy03.cn/img/background-image.jpg");
    background-size: cover;
    padding: 0;
}
.el-aside {
    position: absolute;
    top: 60px;
    left: 0;
    margin: 0;
    background-color: rgb(161,188,209,0.4);
    color: #333;
    text-align: center;
    line-height: 200px;
    min-height: calc(100vh - 60px);
    z-index: 2;
}
.el-footer {
    position: absolute;
    bottom: 0;
    width: 100%;
    z-index: 1;
    background-color: rgb(161,188,209,0.4);
}
</style> -->