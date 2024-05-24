const path = require('path');
module.exports = {    
    // 入口
    entry: path.resolve(__dirname, 'src/index.js'),    
    // 出口
    output: {        
        path: path.resolve(__dirname, 'dist'),        
        filename: 'bundle.js',        
        publicPath: './'    
    }
}
