<template>
    <div class="Manager-MassHome">
        <div class="Manager-MassHome_PC">
            <div class="Manager-MassHome_left">
                <div class="Manager-MassHome_leftLeft">
                    <h2>{{ this.MassIntro.mass }}</h2>
                    <h3>主页正在快马加鞭地开发中...</h3>
                </div>
                <ul class="Manager-MassHome_leftRight">
                    <h3>社团信息</h3> 
                    <h5 style="text-align: left;margin-bottom: 12px;">{{ this.MassIntro.mass }}<span style="float: right;cursor: pointer;font-weight: 400;">修改</span></h5>
                    <li>社团负责人:<span>{{ this.MassIntro.charge }}</span></li>
                    <li>负责人学号:<span>{{ this.MassIntro.sno }}</span></li>
                    <li>负责人手机:<span>{{ this.MassIntro.phone }}</span></li>
                    <li>指导老师:<span>{{ this.MassIntro.teacher }}</span></li>
                    <li>老师工号:<span>{{ this.MassIntro.gonghao }}</span></li>
                    <li>成立时间:<span>{{ this.MassIntro.setup }}</span></li>
                    <li>成员数量:<span>{{ this.MassIntro.memnums }}</span></li>
                    <li>活动数量:<span>{{ this.MassIntro.acnums }}</span></li>
                    <li>文章数量:<span>{{ this.MassIntro.artnums }}</span></li>
                    <li>文章访问量:<span>{{ this.MassIntro.hitnums }}</span></li>
                    <li>互动评论量:<span>{{ this.MassIntro.comnums }}</span></li>
                    <li>消息广播次数:<span>{{ this.MassIntro.bronums }}</span></li>
                    <li>活动累计人次:<span>{{ this.MassIntro.nums0 }}</span></li>
                    <li>学时申报人次:<span>{{ this.MassIntro.crenums }}</span></li>
                </ul>
            </div>
            <div class="Manager-MassHome_right">
                <h2>社团介绍页<small>(对外展示)</small></h2>
                <div class="Manager-MassHome_MassIntro" v-html="this.MassIntro.html"></div>
            </div>
        </div>
        <div class="Manager-MassHome_Move">
            <div class="Manager-MassHome_right">
                <h2>社团介绍页<small>(对外展示)</small></h2>
                <div class="Manager-MassHome_MassIntro" v-html="this.MassIntro.html"></div>
            </div>
            <div class="Manager-MassHome_left">
            </div>
        </div>
    </div>
</template>
<script>
export default {
    data() {
        return {
            MassIntro: {
                // title: '',
                // html: '',
                // charge: '',
                // sno: '',
                // phone: '',
                // mass: '',
                // teacher: '',
                // gonghao: '',
                // setup: '',
                // memnums: '',
                // acnums: '',
                // artnums: '',
                // hitnums: '',
                // comnums: '',
                // bronums: '',
                // nums0: '',
                // crenums: '',
                // update_time: ''
            }
        }
    },
    created() {
        this.GetIntroByMass()
    },
    methods: {
        GetIntroByMass() {
            const url = process.env.VUE_APP_BASE_URI + '/shetuan-Intro/intro-getIntro'
            // const mass = '互联网社'
            const mass = this.$route.params.mass
            fetch(`${url}?mass=${mass}`, {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            })
            .then(res => {
                if(res.status == 200) {
                    return res.json()
                } else {
                    throw new Error('社团不存在');
                }
            })
            .then(res => {
                // console.log(res)
                this.MassIntro = res[0]
                // this.MassIntro.title = res[0].title
                // this.MassIntro.html = res[0].html
                // this.MassIntro.charge = res[0].charge
                // this.MassIntro.mass = res[0].mass
                // this.MassIntro.teacher = res[0].teacher
                // this.MassIntro.update_time = res[0].update_time
            })
            .catch(error => {
                console.error(error);
                this.$message({
                    message: "社团不存在",
                    type: 'error'
                });
                // this.$router.push("/")
            });
        }
    }
}
</script>
<style>
.Manager-MassHome .Manager-MassHome_PC,.Manager-MassHome_Move {
    display: flex;
    margin: 24px 24px;
    height: calc(100vh - 168px);
    /* background-color: rgb(95, 141, 187, 0.7); */
    /* overflow: auto; */
}
.Manager-MassHome .Manager-MassHome_Move {
    display: none;
}
.Manager-MassHome_PC .Manager-MassHome_left {
    display: flex;
    width: 65%;
    background-color: rgb(250,250,250,0.6);
    border-radius: 5px;
    min-height: calc(100vh - 168px);
}
.Manager-MassHome_PC .Manager-MassHome_right {
    margin-left: 15px;
    width: calc(35% - 15px);
    min-height: calc(100vh - 168px);
    border-radius: 5px;
    background-color: rgb(250,250,250,0.6);
    overflow: auto;
}

.Manager-MassHome_Move .Manager-MassHome_left {
    margin-top: 15px;
    width: 100%;
    min-height: calc(100vh - 168px);
    background-color: rgb(250,250,250,0.6);
    border-radius: 5px;
}
.Manager-MassHome_leftLeft {
    width: 70%;
    min-height: calc(100vh - 168px);
}
.Manager-MassHome_leftRight {
    width: calc(30% - 1px);
    min-height: calc(100vh - 168px);
    list-style: none;
    /* border-left: 1px solid #e5e9ef; */
}
.Manager-MassHome_leftRight li {
    font-weight: 700;
    text-align: left;
    margin: 12px 0;
    padding: 0 12px;
}
.Manager-MassHome_leftRight li span {
    font-weight: 400;
    float: right;
    margin-left: 2px;
}
.Manager-MassHome_Move .Manager-MassHome_right {
    width: 100%;
    min-height: calc(100vh - 168px);
    border-radius: 5px;
    background-color: rgb(250,250,250,0.6);
}
.Manager-MassHome_MassIntro {
    text-align: left;
    padding: 0 18px;
}
.Manager-MassHome_MassIntro img{
    width: 100%;
}
@media screen and (max-width: 480px) {
    .Manager-MassHome .Manager-MassHome_PC {
        display: none;
    }
    .Manager-MassHome .Manager-MassHome_Move {
        display: block;
    }
}
</style>