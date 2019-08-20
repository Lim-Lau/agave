export default {
    mobile(rule, value, callback) {
        let reg = /^(13[0-9]|14[5|6|7|9]|15[0|1|2|3|5|6|7|8|9]|16[5|6]|17[0|1|2|3|4|5|6|7|8]|18[0|1|2|3|4|5|6|7|8|9]|19[1|8|9])\d{8}$/;
        if (reg.test(value)) {
            callback();
        } else {
            callback(new Error("请输入正确的手机号码"));
        }
    },
    bankNo(rule, value, callback) {
        let reg = /^([1-9]{1})(\d{14}|\d{18})$/;
        //银行卡号不是必填，填了就要校验
        if(!value){
            callback();
        }else if (reg.test(value)) {
            callback();
        } else {
            callback(new Error("请输入正确的银行卡号"));
        }
    },
    idCardNo(rule, value, callback){
        let reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
        if (reg.test(value)) {
            callback();
        } else {
            callback(new Error("请输入正确的身份证号"));
        }
    },
    decimalValid(rule,value,callback){
        let reg = /^([1-9]\d*|0)(\.\d{1,2})?$/;
        if(reg.test(value)){
            callback();
        }else{
            callback(new Error("请输入正确的数字"));
        }
    },
    numValid(rule,value,callback){
        let reg = /^[1-9]\d*$/;
        console.log(value,reg.test(value));
        if(reg.test(value)){
            callback();
        }else{
            callback(new Error("请输入正确的数字"));
        }
		}
}