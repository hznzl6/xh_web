<template>
    <div>
        <div class="pc-mass_news">
            <div class="pc-lunbo">Mass pc</div>
            <div class="pc-news">Mass pc</div>
        </div>
        <div class="move-mass_news">
            <div class="move-lunbo">Mass move</div>
            <div class="move-news">Mass move</div>
        </div>
    </div>
</template>

<style>
.pc-mass_news {
    height: 400px;
}
.pc-mass_news .pc-lunbo {
    height: 400px;
    width: 42%;
    float: left;
}
.pc-mass_news .pc-news {
    height: 400px;
    width: 56%;
    float: right;
}

.move-mass_news {
    height: 800px;
    display: none;
}
.move-mass_news .move-lunbo {
    height: 400px;
}
.move-mass_news .move-news {
    height: 400px;
}
@media screen and (max-width: 480px) {
    .pc-mass_news {
        display: none;
    }
    .move-mass_news {
        display: block;
    }
}
</style>