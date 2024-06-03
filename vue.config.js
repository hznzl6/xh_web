const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  chainWebpack: config => {
    config.plugin('html').tap(args => {
      args[0].title = '互联网信息协会';
      return args;
    });
  },
  devServer: {
    host: '0.0.0.0',
    port: 40127,
    client: {
      webSocketURL: 'ws://0.0.0.0:40127/ws',
    },
    headers: {
      'Access-Control-Allow-Origin': '*',
    },
    historyApiFallback: true,
    allowedHosts: "all",
    proxy: {
      '/api': {
        target: process.env.VUE_APP_BASE_URI,
        changeOrigin: true,
        pathRewrite: {
          '^/api': ''
        }
      }
    }
  }
})
