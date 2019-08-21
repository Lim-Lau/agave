module.exports = {
  publicPath: "./",
  lintOnSave: false,
  devServer: {
    disableHostCheck: true,
    port: process.env.PORT || 8080,
    https: false,
    hotOnly: false,
    proxy: {
      api: {
				target: "http://localhost:8888",
				//target: "http://emas.kerbores.com/",
        changeOrigin: true,
        pathRewrite: {
          "^/api": ""
        }
      }
    }
  }
}