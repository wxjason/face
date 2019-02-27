function setCookie(acount, password, exdays) {
  let exdate = new Date();//获取时间
  exdate.setTime(exdate.getTime() + 24 * 60 * 60 * 1000 * exdays);//保存的天数
  //字符串拼接cookie
  window.document.cookie = "account=" + acount + ";path=/;expires=" + exdate.toGMTString();
  window.document.cookie = "password=" + password + ";path=/;expires=" + exdate.toGMTString();
}

function getCookie() {
  if (document.cookie.length > 0) {
    let arr = document.cookie.split('; ');//这里显示的格式需要切割一下自己可输出看下
    let user = {};
    for (let i = 0; i < arr.length; i++) {
      let arr2 = arr[i].split('=');//再次切割
      //判断查找相对应的值
      if (arr2[0] === 'account') {
        user.account = arr2[1];//保存到保存数据的地方
      } else if (arr2[0] === 'password') {
        user.password = arr2[1];
      }
    }
    if (!user.account || !user.password) {
      return null;
    }
    return user;
  }
}

//清除cookie
function clearCookie() {
  this.setCookie("", "", -1);//修改值都为空，天数为负1天就好了
}

export default {
  setCookie,
  getCookie,
  clearCookie
}
