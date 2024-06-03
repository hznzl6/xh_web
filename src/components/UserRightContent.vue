<template>
    <div class="UserRight-Container">
        <div class="container">
            <HomePage v-if="this.publicPath == '/system/home' "/>
            <PersonalPage v-if="this.publicPath == '/system/personal'" />
            <MassList v-if="this.publicPath == '/system/massList'"/>
            <MyClub v-if="this.publicPath == '/system/myClub'"/>
            <ClubMessage v-if="this.publicPath == '/system/clubMessage'"/>
            <AiAsk v-if="this.publicPath == '/function/aiAsk'"/>
            <!-- <OnlineChat v-if="this.publicPath == '/function/onlineChat'"/> -->
            <FirthSelect v-if="this.publicPath == '/function/firthSelect'"/>
        </div>
    </div>
</template>
<script>
import HomePage from '@/views/shetuan/User/SinglePage/HomePage.vue'
import PersonalPage from '@/views/shetuan/User/SinglePage/PersonalPage.vue'
import MassList from '@/views/shetuan/User/SinglePage/MassList.vue'
import MyClub from '@/views/shetuan/User/SinglePage/MyClub.vue'
import ClubMessage from '@/views/shetuan/User/SinglePage/ClubMessage.vue'
import AiAsk from '@/views/shetuan/User/SinglePage/AiAsk.vue'
// import OnlineChat from '@/views/shetuan/User/SinglePage/OnlineChat.vue'
import FirthSelect from '@/views/shetuan/User/SinglePage/FirthSelect.vue'
export default {
    components: {
        HomePage,
        PersonalPage,
        MassList,
        MyClub,
        ClubMessage,
        AiAsk,
        // OnlineChat,
        FirthSelect
    },
    data() {
        return {
            publicPath: ''
        }
    },
    mounted() {
        if(localStorage.getItem("publicPath") == null) {
            localStorage.setItem("publicPath", '/system/home')
        }
        this.publicPath = localStorage.getItem("publicPath")
    }
}
</script>
<style>
.UserRight-Container {
    width: calc(100% - 200px);
    height: calc(100vh - 100px - 50px);
    background-color: #f1f1f1;
    position: relative;
    overflow-y: auto;
}
@media screen and (max-width: 768px) {
    .UserRight-Container {
        width: 100%;
    }
}
</style>